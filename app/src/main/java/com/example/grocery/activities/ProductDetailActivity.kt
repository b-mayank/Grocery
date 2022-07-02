package com.example.grocery.activities

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grocery.R
import com.example.grocery.app.Config
import com.example.grocery.database.DbHelper
import com.example.grocery.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity(){


    var product_detail: Product? = null
    lateinit var dbHelper: DbHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        dbHelper = DbHelper(this)

        product_detail = intent.getSerializableExtra(Product.KEY_PRODUCT) as Product

        text_view_product_title.text = product_detail?.productName
        text_view_product_desc.text = product_detail?.description
        text_view_product_price.text = product_detail?.price.toString()
        text_view_product_unit.text = product_detail?.unit.toString()
        text_view_product_mrp.text = getString(R.string.RS)+product_detail?.mrp.toString()
        text_view_product_mrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        var discount = product_detail?.mrp!!- product_detail?.price!!
        var percent_discount = (discount*100)/product_detail?.mrp!!

        text_view_product_discount.text = percent_discount.toString()+"%off"

        supportActionBar?.setTitle(product_detail?.productName)

        Picasso.get().load(Config.IMAGE_URL+ product_detail!!.image)
            .placeholder(R.drawable.grocery_icon)
            .error(R.drawable.ic_launcher_foreground)
            .into(product_image)


        init()



    }

    private fun init(){
        button_add_to_cart.setOnClickListener {



//
            var pid = product_detail?._id
            var p_name = product_detail?.productName
            var p_price = product_detail?.price
            var p_quantity = product_detail?.quantity
            var p_image_url = product_detail?.image
            var p_mrp = product_detail?.mrp

            var product = Product(_id = pid, productName = p_name , price = p_price , quantity = p_quantity , image = p_image_url, mrp = p_mrp)

            dbHelper.addItemInCart(product)

            // Toast.makeText(this@ProductDetailActivity,"Added",Toast.LENGTH_SHORT).show()


            startActivity(Intent(this@ProductDetailActivity,CartActivity::class.java))
        }



    }



}