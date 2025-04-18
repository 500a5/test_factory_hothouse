package soft.divan.test_factory_hothouse.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CheckAuthCode(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("code")
    val code: String
)