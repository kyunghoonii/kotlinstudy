package com.example.demo.login.service.impl

import com.example.demo.aop.LoginLoggingStartEnd
import com.example.demo.aop.Tx
import com.example.demo.common.CommonResponse
import com.example.demo.exception.KhErrorCode
import com.example.demo.login.LoginRequest
import com.example.demo.login.mapper.LoginMapper
import com.example.demo.login.service.LoginService
import com.example.demo.util.CommonUtil
import com.example.demo.util.CommonUtil.LogBanner.banner as banner
import io.micrometer.core.annotation.Timed
import mu.KotlinLogging
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime
import kotlin.time.measureTimedValue

@Service
class LoginServiceImpl(
    private val loginMapper: LoginMapper,
    private val passwordEncoder : BCryptPasswordEncoder
) : LoginService {
    private val log = KotlinLogging.logger{}

    @Timed(value = "login.password.check", extraTags = ["layer","serviceimpl"])
    @LoginLoggingStartEnd
    override fun checkPw(body: LoginRequest): CommonResponse<Boolean> = loggingStopWatch {
        return@loggingStopWatch try{
            val savedPassword:String = getSavedPassword(body)
            val match:Boolean = passwordEncoder.matches(body.password, savedPassword);
            if(match){
                log.info{ "login success" }
                CommonResponse(data = true, code = "0000", msg = "일치")
            } else {
                log.info{ "login fail" }
                CommonResponse(data = false, code = "1001", msg = "불일치")
            }
        } catch(e:Exception){
            log.info(e.message)
            CommonResponse(data = false, code = KhErrorCode.INVALID_INPUT.code, msg = KhErrorCode.INVALID_INPUT.message)
        }
    }

    fun getSavedPassword(body: LoginRequest):String = Tx.run {
        return@run loginMapper.getPassword(body.id)
    }

    fun <T> loggingStopWatch(function:() -> T): T {
        val (result, duration) = measureTimedValue { function() }
        log.info { banner("Logic Duration : ${duration.inWholeMilliseconds}ms") }
        return result
    }
}