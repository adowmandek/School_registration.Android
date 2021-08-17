package Models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val message:String,
    @SerializedName("access_token") val access_TOKEN:String,
    @SerializedName("student_id")
    val id:String,
)
