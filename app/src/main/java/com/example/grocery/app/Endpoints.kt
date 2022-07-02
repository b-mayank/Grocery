package com.example.grocery.app

class Endpoints {

    companion object{
        private const val URL_CATEGORY = "category"
        private const val URL_SUB_CATEGORY = "subcategory/"
        private const val URL_PRODUCT_BY_SUB = "products/sub/"
        private const val URL_PRODUCTDETAIL_BY_ID = "products/id"
        private val URL_ADDRESS = "address"
        private val URL_REGISTER = "auth/register"
        private val URL_LOGIN = "auth/login"


        fun getCategoryUrl(): String{
            return Config.BASE_URL + URL_CATEGORY
        }

        fun getSubCategoryByCatId(catId: Int?): String{
            return Config.BASE_URL + URL_SUB_CATEGORY + catId
        }

        fun getProductBySubId(subId: Int): String{
            return Config.BASE_URL+ URL_PRODUCT_BY_SUB + subId
        }

        fun getProductByID(id : String):String{
            return Config.BASE_URL+ URL_PRODUCTDETAIL_BY_ID+id
        }
        fun getAddressByUserId(addressId:String):String
        {
            return Config.BASE_URL+ URL_ADDRESS +"/"+addressId
        }
        fun deleteAddress(addressId: String):String{
            return Config.BASE_URL + URL_ADDRESS +"/"+addressId
        }

        fun saveAddress():String{
            return Config.BASE_URL+ URL_ADDRESS
        }

        fun getLoginUrl(): String{
            return Config.BASE_URL + URL_LOGIN
        }
        fun getRegisterUrl(): String{
            return Config.BASE_URL + URL_REGISTER
        }

    }
}