package com.example.demo.api.service.impl

import com.example.demo.api.mapper.ApiMapper
import com.example.demo.api.service.ApiService
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger{}

@Service
class ApiServiceImpl(private val apiMapper: ApiMapper): ApiService {
    override fun test():String{
        val name:String = apiMapper.test()
        return name
    }
}