package soft.divan.test_factory_hothouse.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import soft.divan.test_factory_hothouse.data.remote.entity.CheckAuthCode
import soft.divan.test_factory_hothouse.data.remote.entity.LoginOut
import soft.divan.test_factory_hothouse.data.remote.entity.PhoneBase
import soft.divan.test_factory_hothouse.data.remote.entity.RefreshToken
import soft.divan.test_factory_hothouse.data.remote.entity.RegisterIn
import soft.divan.test_factory_hothouse.data.remote.entity.Success
import soft.divan.test_factory_hothouse.data.remote.entity.Token

interface AuthServiceApi {
    @POST("send-auth-code/")
    suspend fun sendVerificationCode(@Body phoneBase: PhoneBase): Response<Success>

    @POST("check-auth-code/")
    suspend fun checkAuthCode(@Body checkAuthCode: CheckAuthCode): Response<LoginOut>

    @POST("register/")
    suspend fun registerUser(@Body registerIn: RegisterIn): Response<Token>

    @POST("refresh-token/")
    suspend fun refreshToken(@Body refreshToken: RefreshToken): Response<Token>

    @GET("check-jwt/")
    suspend fun checkJwt(): Response<Unit>
}