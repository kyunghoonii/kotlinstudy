package com.example.demo.login.service

import com.example.demo.common.CommonResponse

interface LoginService {
    fun checkPw(id:String, password:String): CommonResponse<Boolean>
}