package com.example.demo.login.mapper

import com.example.demo.login.JoinRequest
import org.apache.ibatis.annotations.Mapper

@Mapper
interface LoginMapper {
    fun getPassword(id:String): String
    fun accountCheck(id:String): Int
    fun insertAccount(joinRequest: JoinRequest): Int
}