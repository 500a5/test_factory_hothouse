package soft.divan.test_factory_hothouse.domain.entities

import com.google.gson.annotations.SerializedName

data class UploadImage(
    @SerializedName("filename")
    val filename: String,
    @SerializedName("base_64")
    val base64: String
)