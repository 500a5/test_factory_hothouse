package soft.divan.test_factory_hothouse.domain.entities

import com.google.gson.annotations.SerializedName

data class UserProfileSend(
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("online")
    val online: Boolean,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("avatars")
    val avatars: Avatars,
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
    @SerializedName("last")
    val last: String?,
    @SerializedName("created")
    val created: String,
    @SerializedName("completed_task")
    val completedTask: Int = 0
)