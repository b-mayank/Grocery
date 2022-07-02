package com.example.grocery.activities

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.grocery.R
import com.example.grocery.adapters.AddressAdapter
import com.example.grocery.app.Endpoints
import com.example.grocery.database.DbHelper
import com.example.grocery.models.AddressResponse
import com.example.grocery.models.OrderSummary
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_place_order.*


class PlaceOrderActivity : AppCompatActivity() {

    lateinit var dbHelper: DbHelper
    var order_summary: OrderSummary? = null
    lateinit var addressAdapter: AddressAdapter
    var userId = "62b1a9c35f652a0017b0fc20"



    override fun onCreate(savedInstanceState: Bundle?) {


        dbHelper = DbHelper(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order)

        order_summary = dbHelper.getOrderSummary()

        supportActionBar?.hide()


        init()
    }

    private fun init(){

        dbHelper.getOrderSummary()

        // text_view_product_price.text = getString(R.string.RS)+order_summary?.totalPrice.toString()
        text_view_product_price.text = getString(R.string.RS)+order_summary?.totalPrice.toString()
        text_view_delivery_charge.text = order_summary?.deliveryCharges.toString()
        //text_view_mrp.text = order_summary?.totalMrp.toString()
        text_view_total_price_to_pay.text = getString(R.string.RS)+order_summary?.orderAmount.toString()

        text_view_mrp.text = order_summary?.totalMrp.toString()
        text_view_mrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        text_view_discount.text = order_summary?.discount.toString()
        text_view_discount.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG


        text_view_add_address.setOnClickListener {
            startActivity(Intent(applicationContext,GetAddressFromUser::class.java))
            finish()
        }

        getData()

        addressAdapter = AddressAdapter(this)
        recycler_view.adapter = addressAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)



    }

    override fun onRestart() {
        super.onRestart()
    }

    private fun getData(){
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getAddressByUserId(userId),
            {
                var gson = Gson()
                var addressResponse = gson.fromJson(it, AddressResponse::class.java)
                addressAdapter.setData(addressResponse.data)
            },
            {

            }

        )

        requestQueue.add(request)
    }


}