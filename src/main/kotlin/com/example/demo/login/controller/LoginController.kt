package com.example.demo.login.controller

import com.example.demo.login.service.LoginService
import com.example.demo.common.CommonResponse
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
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

//    @PostMapping("/passwordCheck")
//    fun passwordCheck(@RequestBody ): ResponseEntity<CommonResponse<Boolean>> {
//        val res: CommonResponse<Boolean> = loginService.checkPw(id, password)
//        return ResponseEntity.ok(CommonResponse.success(data = res.data, code=res.code, msg=res.msg))
//    }
}