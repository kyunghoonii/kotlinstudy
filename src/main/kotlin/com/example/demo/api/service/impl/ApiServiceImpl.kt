package com.example.demo.api.service.impl

import com.example.demo.aop.LoginLoggingStartEnd
import com.example.demo.api.controller.ApiController
import com.example.demo.api.mapper.ApiMapper
import com.example.demo.api.service.ApiService
import io.micrometer.core.annotation.Timed
import mu.KotlinLogging
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ApiServiceImpl(private val apiMapper: ApiMapper): ApiService {
    private val log = KotlinLogging.logger{}

    @Timed(value = "api.test", extraTags = ["layer","serviceimpl"])
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