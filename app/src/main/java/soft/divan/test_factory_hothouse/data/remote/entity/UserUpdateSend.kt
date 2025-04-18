package soft.divan.test_factory_hothouse.data.remote.entity

import com.google.gson.annotations.SerializedName
import soft.divan.test_factory_hothouse.domain.model.Avatars

data class UserUpdateSend(
    @SerializedName("avatars")
    val avatars: Avatars
)