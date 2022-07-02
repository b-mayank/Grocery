package com.example.grocery.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.grocery.R
import com.example.grocery.adapters.ProductAdapter
import com.example.grocery.app.Endpoints
import com.example.grocery.models.Product
import com.example.grocery.models.ProductResponse
import com.example.grocery.models.SubCategory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_product_list.view.*

class ProductListFragment : Fragment() {

    private var subId: Int = 0



    lateinit var productAdapter: ProductAdapter
    var mList : ArrayList<Product> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subId = it.getInt(SubCategory.KEY_SUB_ID)

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_product_list, container, false)
        //view.text_view.text = subId.toString()
        init(view)
        return view
    }

    private fun init(view:View){
        getProducts(subId)
        productAdapter = ProductAdapter(activity)
        view.recycler_view.adapter = productAdapter
        view.recycler_view.layoutManager = LinearLayoutManager(activity)
    }

    companion object {
        @JvmStatic
        fun newInstance(subId: Int) =
            ProductListFragment().apply {
                arguments = Bundle().apply {
                    putInt(SubCategory.KEY_SUB_ID, subId)
                }
            }
    }

    fun getProducts(subId: Int){
        var requestQueue = Volley.newRequestQueue(activity)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getProductBySubId(subId),
            {
                var gson = Gson()
                var productResponse = gson.fromJson(it, ProductResponse::class.java)
                productAdapter.setData(productResponse.data!!)
            },{

            }
        )
        requestQueue.add(request)

    }


}