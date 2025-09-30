package com.example.demo.login.mapper

import com.example.demo.login.JoinRequest
import org.apache.ibatis.annotations.Mapper

@Mapper
interface LoginMapper {
    fun getPassword(userId:String): String
    fun accountCheck(userId:String): Int
    fun insertAccount(joinRequest: JoinRequest): Int
    fun delete(userId:String):Int
}