package soft.divan.test_factory_hothouse.data.remote.rest

import okhttp3.OkHttpClient
import soft.divan.test_factory_hothouse.data.remote.rest.interseptors.AuthAuthenticator
import soft.divan.test_factory_hothouse.data.remote.rest.interseptors.AuthInterceptor
import soft.divan.test_factory_hothouse.data.remote.rest.interseptors.LoggingInterceptor

class OkHttpClientFactory(
    private val loggingInterceptor: LoggingInterceptor,
    private val authInterceptor: AuthInterceptor,
    private val authAuthenticator: AuthAuthenticator
) {

    operator fun invoke(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor())
        .addInterceptor(authInterceptor)
        .authenticator(authAuthenticator)
        .build()
}