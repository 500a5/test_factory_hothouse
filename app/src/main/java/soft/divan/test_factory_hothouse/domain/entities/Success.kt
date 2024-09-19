package soft.divan.test_factory_hothouse.domain.entities

import com.google.gson.annotations.SerializedName

data class Success(
    @SerializedName("is_success")
    val isSuccess: Boolean
)