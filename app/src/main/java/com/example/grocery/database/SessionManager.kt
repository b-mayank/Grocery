package com.example.grocery.database

import android.content.Context
import com.example.grocery.models.User


class SessionManager(var mContext: Context) {

    var sharedPreferences = mContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    companion object{
        const val FILE_NAME = "User"
        const val KEY_UID = "uid"
        const val KEY_NAME = "name"
        const val KEY_EMAIL = "email"
        const val KEY_MOBILE = "mobile"
        const val KEY_TOKEN = "token"
        const val KEY_IS_LOGGED_IN = "logged_in"

    }

    fun setUser(user: User , token : String){
        editor.putString(KEY_NAME,user.firstName)
        editor.putString(KEY_EMAIL,user.email)
        editor.putString(KEY_MOBILE,user.mobile)
        editor.putString(KEY_UID,user._id)
        editor.putString(KEY_TOKEN,token)
        editor.putBoolean(KEY_IS_LOGGED_IN,true)

        editor.commit()
    }

    fun isLoggedIn():Boolean{
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN,false)
    }

    fun getUser(): User {
        var userId = sharedPreferences.getString(KEY_UID,null)
        var name = sharedPreferences.getString(KEY_NAME,null)
        var email = sharedPreferences.getString(KEY_EMAIL,null)
        var mobile = sharedPreferences.getString(KEY_MOBILE,null)
        var user = User(_id = userId , firstName = name , email = email , mobile = mobile)
        return user
    }

    fun logout(){
        editor.clear()
        editor.commit()
    }

}