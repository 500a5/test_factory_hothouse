package soft.divan.test_factory_hothouse.data.remote.entity

import com.google.gson.annotations.SerializedName

data class HTTPValidationError(
    @SerializedName("detail")
    val detail: List<ValidationError>?
)
