package soft.divan.test_factory_hothouse.data.remote.api

import soft.divan.test_factory_hothouse.data.remote.rest.RetrofitFactory


class ApiFactory(private val retrofitFactory: RetrofitFactory) {

    val serverApi: ServerApi =
        retrofitFactory().create(ServerApi::class.java)

    val authServiceApi: AuthServiceApi = retrofitFactory().create(AuthServiceApi::class.java)
}