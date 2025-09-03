package com.example.demo.api.service.impl

import com.example.demo.api.mapper.ApiMapper
import com.example.demo.api.service.ApiService
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class ApiServiceImpl(private val apiMapper: ApiMapper): ApiService {
    private val log = KotlinLogging.logger{}

    override fun test():String{
        var name:String = "default"
        try{
            name = apiMapper.test()
        } catch(e: Exception){
            log.info { e.message }
        }
        return name
    }
}