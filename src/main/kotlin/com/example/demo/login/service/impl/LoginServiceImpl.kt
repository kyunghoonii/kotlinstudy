package com.example.demo.login.service.impl

import com.example.demo.aop.LoginLoggingStartEnd
import com.example.demo.common.CommonResponse
import com.example.demo.exception.KhErrorCode
import com.example.demo.login.LoginRequest
import com.example.demo.login.mapper.LoginMapper
import com.example.demo.login.service.LoginService
import io.micrometer.core.annotation.Timed
import mu.KotlinLogging
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(private val loginMapper: LoginMapper) : LoginService {
    private val log = KotlinLogging.logger{}

    @Timed(value = "login.password.check", extraTags = ["layer","serviceimpl"])
    @LoginLoggingStartEnd
    override fun checkPw(body: LoginRequest): CommonResponse<Boolean> {
        return try{
            val savedPassword:String = loginMapper.getPassword(body.id);
            val encoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
            val match:Boolean = encoder.matches(body.password, savedPassword);
            if(match){
                log.info("login success")
                CommonResponse(data = true, code = "0000", msg = "일치")
            } else {
                log.info("login fail")
                CommonResponse(data = false, code = "1001", msg = "불일치")
            }
        } catch(e:Exception){
            log.info(e.message)
            CommonResponse(data = false, code = KhErrorCode.INVALID_INPUT.code, msg = KhErrorCode.INVALID_INPUT.message)
        }
    }
}