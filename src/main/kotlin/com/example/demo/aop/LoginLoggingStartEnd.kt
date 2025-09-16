package com.example.demo.aop

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginLoggingStartEnd
