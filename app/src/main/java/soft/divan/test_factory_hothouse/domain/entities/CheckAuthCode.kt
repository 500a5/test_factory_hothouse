package soft.divan.test_factory_hothouse.domain.entities

import com.google.gson.annotations.SerializedName

data class CheckAuthCode(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("code")
    val code: String
)