package com.example.demo.login.mapper

import org.apache.ibatis.annotations.Mapper

@Mapper
interface LoginMapper {
    fun getPassword(id:String): String
}