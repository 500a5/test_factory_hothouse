package soft.divan.test_factory_hothouse.data.remote.entity

import com.google.gson.annotations.SerializedName

data class GetCurrentUserProfile(
    @SerializedName("profile_data")
    val profileData: UserProfileSend
)