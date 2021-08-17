package Repository

import API.ApiClient
import API.ApiInterface
import Models.LoginRequest
import Models.LoginResponse
import Models.RegistrationRequest
import Models.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {

    val retrofit =ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun registerUser(registrationRequest: RegistrationRequest):
            Response<RegistrationResponse> =
        withContext(Dispatchers.IO) {
            var response = retrofit.re(registrationRequest)
            return@withContext response
        }
    suspend fun loginUser(loginRequest: LoginRequest):
            Response<LoginResponse> = withContext(Dispatchers.IO){
        var response=retrofit.logInStudent(loginRequest)
        return@withContext response
    }

}