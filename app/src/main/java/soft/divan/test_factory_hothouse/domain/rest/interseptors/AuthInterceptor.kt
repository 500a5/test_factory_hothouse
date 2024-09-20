package soft.divan.test_factory_hothouse.domain.rest.interseptors

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import soft.divan.test_factory_hothouse.data.dataStore.TokenManager

class AuthInterceptor( private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = runBlocking {
            tokenManager.getToken().first()
        }
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer ${token?.accessToken}")
        return chain.proceed(request.build())

    }
}