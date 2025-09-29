package com.example.demo.login.service

import com.example.demo.common.CommonResponse
import com.example.demo.login.JoinRequest
import com.example.demo.login.LoginRequest

interface LoginService {
    fun checkPw(body: LoginRequest): CommonResponse<Boolean>
    fun join(body: JoinRequest): CommonResponse<Boolean>
}