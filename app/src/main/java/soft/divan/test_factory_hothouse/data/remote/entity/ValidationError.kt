package soft.divan.test_factory_hothouse.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ValidationError(
    @SerializedName("loc")
    val loc: List<String>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("type")
    val type: String
)