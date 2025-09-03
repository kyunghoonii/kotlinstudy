package com.example.demo.login.service.impl

import com.example.demo.common.CommonResponse
import com.example.demo.exception.KhErrorCode
import com.example.demo.exception.KhExceptionHandler
import com.example.demo.login.mapper.LoginMapper
import com.example.demo.login.service.LoginService
import mu.KotlinLogging
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(private val loginMapper: LoginMapper) : LoginService {
    private val log = KotlinLogging.logger{}

    override fun checkPw(id:String, password: String): CommonResponse<Boolean> {
        return try{
            val savedPassword:String = loginMapper.getPassword(id);
            val encoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
            val match:Boolean = encoder.matches(password, savedPassword);
            if(match){
                CommonResponse(data = true, code = "0000", msg = "일치")
            } else {
                CommonResponse(data = false, code = "1001", msg = "불일치")
            }
        } catch(e:Exception){
            log.info { e.message }
            CommonResponse(data = false, code = KhErrorCode.INVALID_INPUT.code, msg = KhErrorCode.INVALID_INPUT.message)
        }
    }
}