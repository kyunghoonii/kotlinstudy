package com.example.demo.login

data class JoinRequest(
    var userNo :Long,
    val userId :String,
    var userPw :String,
    val userName :String,
    val userEmail :String,
    val userPhone :String
)
