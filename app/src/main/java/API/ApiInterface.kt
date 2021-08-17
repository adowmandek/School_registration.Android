package API

import Models.LoginRequest
import Models.LoginResponse
import Models.RegistrationRequest
import Models.RegistrationResponse
import com.example.registration_form.Sign_up
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

        @POST("/students/register")

        fun Sign_up(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>

        @POST("/students/register")
        fun MainActivity(@Body loginRequest: LoginRequest): Call<LoginResponse>

}