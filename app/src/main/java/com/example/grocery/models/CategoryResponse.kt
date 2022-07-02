package com.example.grocery.models

import java.io.Serializable


data class CategoryResponse(
    val count: Int ?=null,
    val `data`: ArrayList<Category>,
    val error: Boolean ?=null,
)

data class Category(
    val __v: Int ?=null,
    val _id: String ?=null,
    val catDescription: String ?=null,
    val catId: Int ?=null,
    val catImage: String ?=null,
    val catName: String ?=null,
    val position: Int ?=null,
    val slug: String ?=null,
    val status: Boolean ?=null,
)

data class ProductResponse(
    val count: Int ?= null,
    val `data`: ArrayList<Product>,
    val error: Boolean ?= null
)

data class Product(
    val __v: Int ?= null,
    val _id: String ?= null,
    val catId: Int ?= null,
    val created: String ?= null,
    val description: String ?= null,
    val image: String ?= null,
    val mrp: Double ?= null,
    val position: Int ?= null,
    val price: Double ?= null,
    val productName: String ?= null,
    var quantity: Int? = 0,
    val status: Boolean ?= null,
    val subId: Int ?= null,
    val unit: String ?= null,

    ):Serializable{
    companion object{
        const val KEY_PRODUCT = "product_detail"
    }
}


data class SubCategoryResponse(
    val count: Int ? = null,
    val `data`: ArrayList<SubCategory>,
    val error: Boolean ? = null
)

data class SubCategory(
    val __v: Int ? = null,
    val _id: String ? = null,
    val catId: Int ? = null,
    val position: Int ? = null,
    val status: Boolean ? = null,
    val subDescription: String ? = null,
    val subId: Int,
    val subImage: String ? = null,
    val subName: String
):Serializable{
    companion object{
        const val KEY_SUB_ID = "subId"
    }
}



data class ProductDetailResponse(
    val `data`: ProductDetail ? = null,
    val error: Boolean ? = null
)

data class ProductDetail(
    val __v: Int ? = null,
    val _id: String ? = null,
    val catId: Int ? = null,
    val created: String ? = null,
    val description: String ? = null,
    val image: String ? = null,
    val mrp: Int ? = null,
    val position: Int ? = null,
    val price: Int ? = null,
    val productName: String ? = null,
    val quantity: Int ? = null,
    val status: Boolean ? = null,
    val subId: Int ? = null,
    val unit: String ? = null
)