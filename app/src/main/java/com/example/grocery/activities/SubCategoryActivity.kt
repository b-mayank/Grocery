package com.example.grocery.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.grocery.R
import com.example.grocery.adapters.MyFragmentAdapter
import com.example.grocery.app.Endpoints
import com.example.grocery.models.SubCategoryResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sub_category.*


class SubCategoryActivity : AppCompatActivity() {

    lateinit var myFragmentAdapter: MyFragmentAdapter
    //var cat_Id = intent.getStringExtra("CATID")

    var cat_id = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)

        cat_id = intent.getIntExtra("CATID",1)

        var sub_cat_name = intent.getStringExtra("CAT_NAME")

        supportActionBar?.setTitle(sub_cat_name)

        init()
    }

    private fun init()
    {
        getData()
        myFragmentAdapter = MyFragmentAdapter(supportFragmentManager)
    }

    private fun getData() {
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getSubCategoryByCatId(cat_id),
            {
                var gson = Gson()
                var subCategoryResponse = gson.fromJson(it, SubCategoryResponse::class.java)
                for(i in 0 until subCategoryResponse.data.size){
                    myFragmentAdapter.addFragment(subCategoryResponse.data[i])
                }
                view_pager.adapter = myFragmentAdapter
                tab_layout.setupWithViewPager(view_pager)
            },
            {

            }
        )
        requestQueue.add(request)
    }


}