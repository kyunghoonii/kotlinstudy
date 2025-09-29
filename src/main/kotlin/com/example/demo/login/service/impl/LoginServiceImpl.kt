package com.example.demo.login.service.impl

import com.example.demo.aop.LoginLoggingStartEnd
import com.example.demo.aop.Tx
import com.example.demo.common.CommonResponse
import com.example.demo.exception.KhErrorCode
import com.example.demo.exception.KhException
import com.example.demo.login.JoinRequest
import com.example.demo.login.LoginRequest
import com.example.demo.login.mapper.LoginMapper
import com.example.demo.login.service.LoginService
import com.example.demo.util.CommonUtil.LogBanner.banner as banner
import io.micrometer.core.annotation.Timed
import mu.KotlinLogging
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.time.measureTimedValue

@Service
class LoginServiceImpl(
    private val loginMapper: LoginMapper,
    private val passwordEncoder : BCryptPasswordEncoder
) : LoginService {
    private val log = KotlinLogging.logger{}
    private val USER_JOIN_PARAM_CHECK:String = "1"

    @Timed(value = "login.password.check", extraTags = ["layer","serviceimpl"])
    @LoginLoggingStartEnd
    override fun checkPw(body: LoginRequest): CommonResponse<Boolean> = loggingStopWatch {
        return@loggingStopWatch try{
            val savedPassword:String = loginMapper.getPassword(body.userId)
            val match:Boolean = passwordEncoder.matches(body.userPw, savedPassword);
            if(match){
                log.info{ "login success" }
                CommonResponse(resultCode = "0000", resultMsg = "일치", resultData = true)
            } else {
                log.info{ "login fail" }
                CommonResponse(resultCode = "1001", resultMsg = "불일치", resultData = true)
            }
        } catch(e:Exception){
            log.info(e.message)
            CommonResponse(resultCode = KhErrorCode.INVALID_INPUT.code, resultMsg = KhErrorCode.INVALID_INPUT.message, resultData = false)
        }
    }

    fun getSavedPasswordTransactionalTest(id: String):String = Tx.run {
        return@run loginMapper.getPassword(id)
    }

    fun <T> loggingStopWatch(function:() -> T): T {
        val (result, duration) = measureTimedValue { function() }
        log.info { banner("Logic Duration : ${duration.inWholeMilliseconds}ms") }
        return result
    }

    @Transactional
    override fun join(body: JoinRequest): CommonResponse<Boolean> {
        try {
            paramCheck(USER_JOIN_PARAM_CHECK, body)
            val count = loginMapper.accountCheck(body.userId)
            if(count>0) return CommonResponse(resultCode=KhErrorCode.USER_ALREADY_EXISTS.code, resultMsg=KhErrorCode.USER_ALREADY_EXISTS.message, resultData=false)

            val joinRequest:JoinRequest = body.copy()
            joinRequest.userPw = passwordEncoder.encode(joinRequest.userPw)
            val success = loginMapper.insertAccount(joinRequest)

            log.info { "join success" }
            return if(success>0) CommonResponse.success()
            else CommonResponse.fail(KhErrorCode.ACCOUNT_INSERT_FAIL.code, KhErrorCode.ACCOUNT_INSERT_FAIL.message)
        } catch (e:Exception){
            log.error { e.message }
            return CommonResponse.fail(KhErrorCode.ACCOUNT_INSERT_FAIL.code, KhErrorCode.ACCOUNT_INSERT_FAIL.message)
        }
    }

    fun paramCheck(case: String, reqData: JoinRequest):Boolean {
         return when (case){
             USER_JOIN_PARAM_CHECK -> { // 회원가입
                 val requiredParams = mapOf(
                     "userId" to reqData.userId,
                     "userPw" to reqData.userPw,
                     "userPhone" to reqData.userPhone,
                     "userEmail" to reqData.userEmail,
                     "userName" to reqData.userName
                 )
                 val missing = requiredParams
                     .filter { (_, value) -> value.isBlank() }
                     .keys
                 if(missing.isNotEmpty()){
                     throw KhException(KhErrorCode.PARAMETER_ERROR, "${KhErrorCode.PARAMETER_ERROR.message} : ${missing.joinToString()}")
                 }
                 return true
             }
             else -> {false}
         }
    }
}