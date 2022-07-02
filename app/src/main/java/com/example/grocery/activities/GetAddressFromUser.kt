package com.example.grocery.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.grocery.R
import com.example.grocery.app.Endpoints
import kotlinx.android.synthetic.main.activity_get_address_from_user.*
import org.json.JSONObject

class GetAddressFromUser : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_address_from_user)

        supportActionBar?.hide()


        init()
    }

    private fun init(){
        button_save_address.setOnClickListener{
            var name = edit_text_name.text.toString()
            var mobile = edit_text_mobile.text.toString()
            var houseNo = edit_text_house.text.toString()
            var streetName = edit_text_street.text.toString()
            var location = edit_text_location.text.toString()
            var city = edit_text_city.text.toString()
            var pincode = edit_text_pincode.text.toString()
            var type = edit_text_type.text.toString()
            var userID = "62b1a9c35f652a0017b0fc20"

            var params = JSONObject()
            params.put("name",name)
            params.put("mobile",mobile)
            params.put("houseNo",houseNo)
            params.put("streetName",streetName)
            params.put("location",location)
            params.put("city",city)
            params.put("pincode",pincode)
            params.put("type",type)
            params.put("userId",userID)

            var requestQueue = Volley.newRequestQueue(this)
            var request = JsonObjectRequest(
                Request.Method.POST,
                Endpoints.saveAddress(),
                params,
                {
                    Toast.makeText(this@GetAddressFromUser,"Saved",Toast.LENGTH_SHORT).show()

                },
                {
                    Toast.makeText(applicationContext,it.message,Toast.LENGTH_SHORT).show()
                }
            )

            requestQueue.add(request)


        }

    }
}