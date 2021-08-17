package com.example.registration_form

import API.ApiClient
import API.ApiInterface
import Models.LoginRequest
import Models.LoginResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Attributes

class Sign_up : AppCompatActivity() {
    lateinit var tvPassword: TextView
    lateinit var btnSignup: Button
    lateinit var tvEmail: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        castViews()
        logInStudent()
    }

    fun castViews() {
        tvPassword = findViewById(R.id.tvPassword)
        btnSignup = findViewById(R.id.btnSignup)
    }

    fun logInStudent() {
//        var tvEmail.text.toString()
        var Password = tvPassword.text.toString()
        btnSignup.setOnClickListener {

            fun logInStudent() {
                var email = tvEmail.text.toString()
                var password = tvPassword.text.toString()
                btnSignup.setOnClickListener {
                    if (email.isEmpty()) {
                        tvEmail.setError("This field is compulsory")
                    }
                    if (password.isEmpty()) {
                        tvPassword.setError("This field is compulsory")
                    }
                }
                val loginRequest = LoginRequest(
                    email = email, password = password
                )
                var retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
                var request =retrofit.MainActivity(loginRequest)
                request.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                baseContext,
                                "your login was succesful!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        } else {
                            try {
                                val error = JSONObject(response.errorBody()!!.string())
                                Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG)
                                    .show()
                            } catch (e: Exception) {
                                Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()

                            }
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()


                    }
                })

            }
        }
    }
}
