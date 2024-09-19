package soft.divan.test_factory_hothouse.domain.entities

import com.google.gson.annotations.SerializedName

data class RefreshToken(
    @SerializedName("refresh_token")
    val refreshToken: String
)