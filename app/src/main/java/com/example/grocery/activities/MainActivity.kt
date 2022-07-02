package com.example.grocery.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.grocery.R
import com.example.grocery.adapters.CategoriesAdapter
import com.example.grocery.app.Endpoints
import com.example.grocery.database.SessionManager
import com.example.grocery.models.Category
import com.example.grocery.models.CategoryResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sessionManager: SessionManager


    lateinit var categoriesAdapter: CategoriesAdapter
    var mList : ArrayList<Category> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)

        init()

        supportActionBar?.setTitle("Click Click Cafe")


    }

    private fun init(){

        getData()
        categoriesAdapter = CategoriesAdapter(this)
        recycler_view.adapter = categoriesAdapter
        recycler_view.layoutManager = GridLayoutManager(this,2)

    }

//

    private fun getData() {
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET, Endpoints.getCategoryUrl(),
            {
                progressBar.visibility = View.GONE

                var gson = Gson()
                var categoryResponse = gson.fromJson(it, CategoryResponse::class.java)

                categoriesAdapter.setData(categoryResponse.data!!)

            },
            {
                progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.button_logout -> {
                sessionManager.logout()
                finish()
                System.exit(0)
            }


        }
        return super.onOptionsItemSelected(item)
    }
}