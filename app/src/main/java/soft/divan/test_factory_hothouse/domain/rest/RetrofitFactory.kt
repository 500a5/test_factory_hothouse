package soft.divan.test_factory_hothouse.domain.rest


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import soft.divan.test_factory_hothouse.BuildConfig

class RetrofitFactory(private val okHttpClientFactory: OkHttpClientFactory) {
    operator fun invoke(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.HOST)
        .client(okHttpClientFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
