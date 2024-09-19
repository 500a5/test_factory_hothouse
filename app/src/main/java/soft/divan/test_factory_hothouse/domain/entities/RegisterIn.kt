package soft.divan.test_factory_hothouse.domain.entities

import com.google.gson.annotations.SerializedName

data class RegisterIn(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String
)