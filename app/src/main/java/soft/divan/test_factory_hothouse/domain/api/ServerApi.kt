package soft.divan.test_factory_hothouse.domain.api


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import soft.divan.test_factory_hothouse.domain.entities.CheckAuthCode
import soft.divan.test_factory_hothouse.domain.entities.GetCurrentUserProfile
import soft.divan.test_factory_hothouse.domain.entities.LoginOut
import soft.divan.test_factory_hothouse.domain.entities.PhoneBase
import soft.divan.test_factory_hothouse.domain.entities.RefreshToken
import soft.divan.test_factory_hothouse.domain.entities.RegisterIn
import soft.divan.test_factory_hothouse.domain.entities.Success
import soft.divan.test_factory_hothouse.domain.entities.Token
import soft.divan.test_factory_hothouse.domain.entities.UserUpdate
import soft.divan.test_factory_hothouse.domain.entities.UserUpdateSend


interface ServerApi {


    @GET("me/")
    suspend fun getCurrentUser(): Response<GetCurrentUserProfile>

    @PUT("me/")
    suspend fun updateUser(@Body userUpdate: UserUpdate): Response<UserUpdateSend>


}