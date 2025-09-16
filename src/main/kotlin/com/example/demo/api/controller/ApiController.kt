package com.example.demo.api.controller

import com.example.demo.api.service.ApiService
import io.micrometer.core.annotation.Timed
import mu.KotlinLogging
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/")
class ApiController(
    private val apiService: ApiService
) {
    private val log = KotlinLogging.logger{}

    @Timed(value = "api.test", extraTags = ["layer","controller"])
    @GetMapping("/test")
    fun test():String{
        return apiService.test()
    }
}