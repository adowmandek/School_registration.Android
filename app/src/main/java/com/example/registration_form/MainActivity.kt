package com.example.registration_form

import API.ApiClient
import API.ApiInterface
import Models.LoginRequest
import Models.LoginResponse
import Models.RegistrationRequest
import Models.RegistrationResponse
import Repository.UserRepository
import ViewModel.UserView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.ViewBinding
import com.example.registration_form.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.CodeHiveRegistration

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel:UserView.UserViewModel by viewModels()
    lateinit var tvName: TextView
    lateinit var tvPassword: TextView
    lateinit var tvNationality: TextView
    lateinit var tvPhone: TextView
    lateinit var tvEmail: TextView
    lateinit var btnl: Button
    lateinit var tvGender: TextView
    lateinit var btnSignUp: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        castViews()
        onClick()
    }

    fun castViews() {
        tvName = findViewById(R.id.tvName)
        tvPassword = findViewById(R.id.tvPassword)
        tvPhone = findViewById(R.id.tvPhoneNumber)
        tvEmail = findViewById(R.id.tvEmail)
        btnl = findViewById(R.id.btnl)
        btnSignUp = findViewById(R.id.btnSignup)
        val nationality = arrayOf("Kenyan", "Ugandan", "Rwandan", "Sudanese", "South Sudanese")
        val nationalityAdapter =
            ArrayAdapter<String>(baseContext, android.R.layout.simple_spinner_item, nationality)
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    fun onClick() {
        btnl.setOnClickListener {
            var name = tvName.text.toString()
            var password = tvPassword.text.toString()
            var phone = tvPhone.text.toString()
            var email = tvEmail.text.toString()
            if (name.isEmpty() || password.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                binding.tvName.setError("please enter your name")
                binding.tvPhoneNumber.setError("You must enter you ID number")
                binding.tvPhoneNumber.setError("please enter your phone number")
                binding.tvEmail.setError("please enter your email")
                Toast.makeText(baseContext, "This are your details :", Toast.LENGTH_LONG).show()
            }
            var intent = Intent(baseContext, CodeHiveRegistration::class.java)
            startActivity(intent)
            var regRequest = RegistrationRequest(
                name = name,email = email, password = password,phoneNumber = password)
            userViewModel.loginStudent(RegistrationRequest)

    val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun registerUser(registrationRequest: RegistrationRequest):
            Response<RegistrationResponse> =
        withContext(Dispatchers.IO) {
            var response = retrofit.(registrationRequest)
            return@withContext response
        }
    suspend fun loginUser(loginRequest: LoginRequest):
            Response<LoginResponse> = withContext(Dispatchers.IO){
        var response=retrofit.(loginRequest)
        return@withContext response
    }



    class UserViewModel: ViewModel() {
        var userRepository=UserRepository()
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


data class Details(
    var name: String,
    var password: String,
    var phone: String,
    var email: String

)}}




