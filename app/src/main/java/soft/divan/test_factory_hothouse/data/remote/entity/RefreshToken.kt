package soft.divan.test_factory_hothouse.data.remote.entity

import com.google.gson.annotations.SerializedName

data class RefreshToken(
    @SerializedName("refresh_token")
    val refreshToken: String
)