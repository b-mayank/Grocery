package com.example.grocery.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.grocery.R
import com.example.grocery.database.DbHelper
import com.example.grocery.models.OrderSummary
import kotlinx.android.synthetic.main.activity_confirm_order.*

class COnfirmOrder : AppCompatActivity() {

    lateinit var dbHelper: DbHelper
    var order_summary: OrderSummary? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)

        supportActionBar?.hide()

        dbHelper = DbHelper(this)

        order_summary = dbHelper.getOrderSummary()





        init()
    }

    private fun init(){

        var address1 = intent.getStringExtra("address1")
        var address2 = intent.getStringExtra("address2")
        var address3 = intent.getStringExtra("address3")
        var address4 = intent.getStringExtra("address4")

        text_view_amount.text = getString(R.string.RS)+order_summary?.orderAmount.toString()

        text_view_address.text = address1+" "+address2+" "+address3+" "+address4




        button_confirm_order.setOnClickListener{
            card_view_confirm.visibility = View.VISIBLE
            button_confirm_order.visibility = View.GONE
        }

        text_view_explore.setOnClickListener {
            startActivity(Intent(this@COnfirmOrder,MainActivity::class.java))
            card_view_confirm.visibility = View.INVISIBLE
            finish()
        }

    }
}