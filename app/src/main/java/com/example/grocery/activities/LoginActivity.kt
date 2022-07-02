package com.example.grocery.activities
import android.content.Intent
import com.example.grocery.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.grocery.app.Endpoints
import com.example.grocery.database.SessionManager
import com.example.grocery.models.LoginResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    lateinit var  sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        sessionManager = SessionManager(this)

        init()
    }

    private fun init(){

        text_view_clcik_button.setOnClickListener {
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        }
        button_login.setOnClickListener{
            var email = edit_text_email.text.toString().trim()
            var pass = edit_text_pass.text.toString().trim()

            if(email.isEmpty()||pass.isEmpty())
            {
                Toast.makeText(this@LoginActivity,"Field are empty",Toast.LENGTH_SHORT).show()
            }
            else
            {
                postRequest(email,pass)
                Log.d("login","login button clicked")
            }

        }
    }

    private fun postRequest(email:String , pass: String){
        var params = HashMap<String,String>()
        params["email"] = email
        params["password"] = pass

        var jsoObject = JSONObject(params as Map<*,*>?)

        var requestQueue = Volley.newRequestQueue(this)
        var request = JsonObjectRequest(
            Request.Method.POST,
            Endpoints.getLoginUrl(),
            jsoObject,
            {
                var gson = Gson()
                var loginResponse = gson.fromJson(it.toString(), LoginResponse::class.java)
                sessionManager.setUser(loginResponse.user,loginResponse.token)

                startActivity(Intent(applicationContext,MainActivity::class.java))
            },
            {
                Toast.makeText(applicationContext,"Invalid Credentials",Toast.LENGTH_SHORT).show()
            }
        )

        requestQueue.add(request)
    }
}