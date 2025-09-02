package com.example.demo.api.mapper

import org.apache.ibatis.annotations.Mapper

@Mapper
interface ApiMapper {
    fun test():String
}