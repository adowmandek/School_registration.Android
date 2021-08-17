package ViewModel

import API.ApiClient
import API.ApiInterface
import Models.LoginRequest
import Models.LoginResponse
import Models.RegistrationRequest
import Models.RegistrationResponse
import Repository.UserRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserView {
    val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun registerUser(registrationRequest: RegistrationRequest):
            Response<RegistrationResponse> =
        withContext(Dispatchers.IO) {
            var response = retrofit.registerStudent(registrationRequest)
            return@withContext response
        }
    suspend fun loginUser(loginRequest: LoginRequest):
            Response<LoginResponse> = withContext(Dispatchers.IO){
        var response=retrofit.logInStudent(loginRequest)
        return@withContext response
    }
    class UserViewModel: ViewModel() {
        var userRepository= UserRepository()
        var registrationResponseLiveData=MutableLiveData<RegistrationResponse>()
        var regErrorLiveData=MutableLiveData<String>()
        var loginResponseLiveData=MutableLiveData<LoginResponse>()
        var logErrorLiveData=MutableLiveData<String>()
        fun registerStudent(registrationRequest: RegistrationRequest){
            viewModelScope.launch {
                var response=userRepository.registerUser(registrationRequest)
                if (response.isSuccessful){
                    registrationResponseLiveData.postValue(response.body())
                }
                else{
                    regErrorLiveData.postValue(response.errorBody()?.string())
                }
            }
        }
        fun loginStudent(loginRequest: LoginRequest){
            viewModelScope.launch {
                var response=userRepository.loginUser(loginRequest)
                if (response.isSuccessful){
                    loginResponseLiveData.postValue(response.body())
                }
                else{
                    logErrorLiveData.postValue(response.errorBody()?.string())
                }
            }
        }
    }








}