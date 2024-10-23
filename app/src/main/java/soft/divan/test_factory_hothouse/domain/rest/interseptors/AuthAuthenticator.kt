package soft.divan.test_factory_hothouse.domain.rest.interseptors

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import soft.divan.test_factory_hothouse.data.dataStore.TokenManager
import soft.divan.test_factory_hothouse.data.entity.Tokens
import soft.divan.test_factory_hothouse.domain.api.AuthServiceApi
import soft.divan.test_factory_hothouse.domain.entities.RefreshToken
import soft.divan.test_factory_hothouse.domain.entities.Token


/**
 * Поставщик AuthServiceApi: Мы создаем функцию () -> AuthServiceApi, которая будет возвращать экземпляр AuthServiceApi по мере необходимости.
 * Это позволяет избежать создания AuthServiceApi в момент создания AuthAuthenticator,
 * что устраняет циклическую зависимость.
 */
class AuthAuthenticator(
    private val tokenManager: TokenManager,
    private val authServiceApi: Lazy<AuthServiceApi>
) : Authenticator {

    private val tokenLock = Mutex() // Механизм синхронизации для блокировки

    override fun authenticate(route: Route?, response: Response): Request? {

        // Проверяем количество попыток по заголовку "Retry-Attempt"
        val attemptCount = response.request().header("Retry-Attempt")?.toIntOrNull() ?: 0
        if (attemptCount >= 5) {
            return null // Прекращаем попытки после пяти неудачных запросов
        }

        return runBlocking {
            tokenLock.withLock {
                val token = tokenManager.getToken().first()

                // Получаем новый токен
                val newToken = refreshAuthToken(token?.refreshToken ?: "") ?: return@runBlocking null

                // Создаем новый запрос с обновленным токеном
                createAuthenticatedRequest(response, newToken.accessToken)
            }
        }
    }

    private suspend fun refreshAuthToken(refreshToken: String): Token? {
        val response = authServiceApi.value.refreshToken(RefreshToken(refreshToken))

        return if (response.isSuccessful && response.body() != null) {
            // Сохраняем новый токен
            val newToken = response.body()!!
            tokenManager.saveToken(
                Tokens(
                    refreshToken = newToken.refreshToken,
                    accessToken = newToken.accessToken
                )
            )
            newToken
        } else {
            // Удаляем токен, если запрос неудачный
            tokenManager.deleteToken()
            null
        }
    }

    private fun createAuthenticatedRequest(response: Response, accessToken: String): Request {
        return response.request().newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }
}