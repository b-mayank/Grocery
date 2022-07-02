package com.example.grocery.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.R
import com.example.grocery.activities.SubCategoryActivity
import com.example.grocery.models.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_row_category.view.*


class CategoriesAdapter (var mContext: Context):
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var mList: ArrayList<Category> = ArrayList()

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(category: Category){

            itemView.text_view_title.text = category.catName
            //var catId:String = category.catId.toString()
            Picasso
                .get().load("http://rjtmobile.com/grocery/images/"+category.catImage)
                .placeholder(R.drawable.grocery_icon)
                .error(R.drawable.ic_launcher_foreground)
                .into(itemView.image_view)

            itemView.card_one.setOnClickListener {
                var intent = Intent(mContext, SubCategoryActivity::class.java)
                intent.putExtra("CATID",category.catId)
                intent.putExtra("CAT_NAME",category.catName)
                mContext.startActivity(intent)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.single_row_category,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var category = mList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: ArrayList<Category>){
        mList = list
        notifyDataSetChanged()
    }
}