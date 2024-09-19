package soft.divan.test_factory_hothouse.domain.entities

import com.google.gson.annotations.SerializedName

data class HTTPValidationError(
    @SerializedName("detail")
    val detail: List<ValidationError>?
)
