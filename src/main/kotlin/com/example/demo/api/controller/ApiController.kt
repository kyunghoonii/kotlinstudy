package com.example.demo.api.controller

import com.example.demo.api.service.ApiService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/")
class ApiController(
    private val apiService: ApiService
) {
    private val log = KotlinLogging.logger{}

    @GetMapping("/test")
    fun test():String{
        return apiService.test()
    }
}