package com.example.grocery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.grocery.R
import com.example.grocery.app.Endpoints
import com.example.grocery.models.User
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        init()
    }

    private fun init(){

        text_view_click.setOnClickListener {
            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
        }
        button_register.setOnClickListener{


            var firstName = edit_text_fname.text.toString().trim()
            var email = edit_text_email.text.toString().trim()
            var mobile = edit_text_mobile.text.toString().trim()
            var password = edit_text_pass.text.toString().trim()



            if(firstName.isEmpty()||email.isEmpty()
                ||mobile.isEmpty()||password.isEmpty())
            {
                Toast.makeText(applicationContext,"Fields are empty",Toast.LENGTH_SHORT).show()
            }

            if(mobile.length!=10){
                Toast.makeText(applicationContext,"Invalid Phone number",Toast.LENGTH_SHORT).show()
            }
            if(password.length<6){
                Toast.makeText(applicationContext,"Password is too weak",Toast.LENGTH_SHORT).show()
            }
            else{
                var user = User(firstName = firstName, email = email, mobile = mobile, password = password)

                postRequest(user)
            }
        }
    }

    private  fun postRequest(user: User){
        var params = HashMap<String,String>()
        params["firstName"] = user.firstName!!
        params["email"] = user.email!!
        params["mobile"] = user.mobile!!
        params["password"] = user.password!!

        var jsoObject = JSONObject(params as Map<*,*>?)

        var requestQueue = Volley.newRequestQueue(this)
        var request = JsonObjectRequest(
            Request.Method.POST,
            Endpoints.getRegisterUrl(),
            jsoObject,
            {
                startActivity(Intent(applicationContext,LoginActivity::class.java))
                Toast.makeText(applicationContext,"Registered",Toast.LENGTH_SHORT).show()

            },
            {
                Log.d("RegError",it.message.toString())
            }
        )

        requestQueue.add(request)

    }
}