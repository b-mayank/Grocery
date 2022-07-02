package com.example.grocery.models

import java.io.Serializable

data class OrderSummary(
    var totalMrp: Int?=null,
    var totalPrice: Int?= null,
    var discount: Int?= null,
    var deliveryCharges: Int?= null,
    var orderAmount: Int?= null,
    var totalItems : Int ?= null



): Serializable{
    companion object{
        const val KEY_ORDER_SUMMARY = "order_summary"
    }
}