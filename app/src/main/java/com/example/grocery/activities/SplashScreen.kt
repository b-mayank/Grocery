package com.example.grocery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.grocery.R
import com.example.grocery.database.SessionManager

class SplashScreen : AppCompatActivity() {

    lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()


        sessionManager = SessionManager(this)

        init()
    }

    private fun init(){
        Handler().postDelayed({

            if(sessionManager.isLoggedIn()){

                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }
            else{
                val intent = Intent(this@SplashScreen,LoginActivity::class.java)
                startActivity(intent)
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                finish()
            }

        },3000)
    }

    override fun finish() {
        super.finish()
    }
}