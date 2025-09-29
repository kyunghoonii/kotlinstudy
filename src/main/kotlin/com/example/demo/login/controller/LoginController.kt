package com.example.demo.login.controller

import com.example.demo.login.service.LoginService
import com.example.demo.common.CommonResponse
import com.example.demo.login.JoinRequest
import com.example.demo.login.LoginRequest
import io.micrometer.core.annotation.Timed
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/login")
class LoginController(
    private val loginService: LoginService
) {
    private val log = KotlinLogging.logger{}

    @PostMapping("/password")
    @Timed(value = "login.password.check", extraTags = ["layer","controller"])
    fun password(@RequestBody body: LoginRequest): ResponseEntity<CommonResponse<Boolean>> {
        return ResponseEntity.ok(loginService.checkPw(body))
    }

    @PostMapping("/join")
    @Timed(value = "login.join.check", extraTags = ["layer","controller"])
    fun join(@RequestBody body: JoinRequest) : ResponseEntity<CommonResponse<Boolean>> {
        return ResponseEntity.ok(loginService.join(body))
    }
}