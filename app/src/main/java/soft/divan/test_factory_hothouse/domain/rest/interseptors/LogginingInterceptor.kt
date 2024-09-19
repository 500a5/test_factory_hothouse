package soft.divan.test_factory_hothouse.domain.rest.interseptors


import okhttp3.logging.HttpLoggingInterceptor
import soft.divan.test_factory_hothouse.BuildConfig

class LoggingInterceptor {
    operator fun invoke(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }
}