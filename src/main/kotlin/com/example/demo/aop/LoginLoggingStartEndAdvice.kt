package com.example.demo.aop

import com.example.demo.util.CommonUtil
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class LoginLoggingStartEndAdvice {
    private val log = KotlinLogging.logger{}

    @Around("@annotation(com.example.demo.aop.LoginLoggingStartEnd)")
    fun atTarget(pjp: ProceedingJoinPoint):Any?{
        log.info(CommonUtil.LogBanner.banner("Start"))

        val result = pjp.proceed()

        log.info(CommonUtil.LogBanner.banner("End"))
        return result
    }
}