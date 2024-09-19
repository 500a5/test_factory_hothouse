package soft.divan.test_factory_hothouse.domain.entities

import com.google.gson.annotations.SerializedName

data class ValidationError(
    @SerializedName("loc")
    val loc: List<String>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("type")
    val type: String
)