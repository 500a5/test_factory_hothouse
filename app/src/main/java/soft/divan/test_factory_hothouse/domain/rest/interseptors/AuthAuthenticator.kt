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


/**
 * Поставщик AuthServiceApi: Мы создаем функцию () -> AuthServiceApi, которая будет возвращать экземпляр AuthServiceApi по мере необходимости.
 * Это позволяет избежать создания AuthServiceApi в момент создания AuthAuthenticator,
 * что устраняет циклическую зависимость.
 */
class AuthAuthenticator(
    private val tokenManager: TokenManager,
    private val authServiceApiProvider: () -> AuthServiceApi
) : Authenticator {

    private val tokenLock = Mutex() // Механизм синхронизации для блокировки

    override fun authenticate(route: Route?, response: Response): Request? {
        // Проверяем количество попыток по заголовку "Retry-Attempt"
        val attemptCount = response.request().header("Retry-Attempt")?.toIntOrNull() ?: 0
        if (attemptCount >= 2) {
            return null // Прекращаем попытки после двух неудачных запросов
        }

        return runBlocking {
            // Синхронизация запросов за новым токеном
            tokenLock.withLock {
                val token = tokenManager.getToken().first()

                val newTokenResponse = authServiceApiProvider().refreshToken(RefreshToken(token?.refreshToken ?: ""))

                if (!newTokenResponse.isSuccessful || newTokenResponse.body() == null) {
                    tokenManager.deleteToken() // Удаляем токен, если запрос неудачный
                    return@runBlocking null
                }

                val newToken = newTokenResponse.body()!!

                // Сохраняем новый токен
                tokenManager.saveToken(
                    Tokens(
                        refreshToken = newToken.refreshToken,
                        accessToken = newToken.accessToken
                    )
                )

                // Создаем новый запрос с обновленным токеном
                response.request().newBuilder()
                    .header("Authorization", "Bearer ${newToken.accessToken}")
                    .build()
            }
        }
    }

}