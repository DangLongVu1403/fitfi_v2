package com.example.ff.Login_Register_ForgotPass

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ff.Interface.ApiService
import com.example.ff.Models.ApiResponse
import com.example.ff.Models.LoginRequest
import com.example.ff.R
import com.example.ff.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var biding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(biding.root)

        biding.btnLogin.setOnClickListener {
            val phoneNumber = biding.edtSDT.text.toString()
            val password = biding.password.text.toString()
            val context: Context = this


            val loginRequest = LoginRequest(phoneNumber, password)

            val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:3200/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)

            val call = apiService.loginUser(loginRequest)

            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        // Xử lý phản hồi thành công
                        val message = response.body()?.message ?: "Unknown response"
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        // TODO: Xử lý message
                    } else {
                        // Xử lý phản hồi không thành công
                        // TODO: Xử lý lỗi
                        Toast.makeText(context, "press a", Toast.LENGTH_SHORT).show();
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    // Xử lý lỗi khi request không thành công
                    // TODO: Xử lý lỗi
                    Toast.makeText(context, "press f", Toast.LENGTH_SHORT).show();
                }
            })
        }



    }
}