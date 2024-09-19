package soft.divan.test_factory_hothouse.domain.entities

import com.google.gson.annotations.SerializedName

data class GetCurrentUserProfile(
    @SerializedName("profile_data")
    val profileData: UserProfileSend
)