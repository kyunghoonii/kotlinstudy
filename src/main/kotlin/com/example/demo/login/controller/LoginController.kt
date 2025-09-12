package com.example.demo.login.controller

import com.example.demo.login.service.LoginService
import com.example.demo.common.CommonResponse
import com.example.demo.login.LoginRequest
import mu.KotlinLogging
import org.json.JSONObject
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController



@RestController
@RequestMapping("/v1/login")
class LoginController(
    private val loginService: LoginService
) {
    private val log = KotlinLogging.logger{}

    @PostMapping("/password")
    fun password(@RequestBody body: LoginRequest): ResponseEntity<CommonResponse<Boolean>> {
        log.info("------------password check init------------")
        return ResponseEntity.ok(loginService.checkPw(body))
    }
}