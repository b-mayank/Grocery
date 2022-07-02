package com.example.grocery.models

data class RegisterationResponse(
    var data: User,
    var error : Boolean,
    var message : String
)

data class User(

    var _id : String ?= null,
    var createdAt : String ?= null,
    var email : String ?= null,
    var firstName : String ?= null,
    var mobile : String ?= null,
    var password : String ?= null,


    )

data class LoginResponse(
    val token: String,
    val user : User
)