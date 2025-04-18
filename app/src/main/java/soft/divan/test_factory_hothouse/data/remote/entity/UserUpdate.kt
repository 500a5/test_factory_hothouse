package soft.divan.test_factory_hothouse.data.remote.entity

import com.google.gson.annotations.SerializedName

data class UserUpdate(
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("vk")
    val vk: String?,
    @SerializedName("instagram")
    val instagram: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("avatar")
    val avatar: UploadImage?
)