package com.example.demo.exception

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class KhExceptionHandler {
    private val log = KotlinLogging.logger{}

    @ExceptionHandler(KhException::class)
    protected fun handleCustomException(e: KhException): ResponseEntity<ErrorResponse> {
        val errorCode = e.errorCode
        log.error("CustomException occurred: {}", errorCode.message)
        val response = ErrorResponse.of(errorCode)
        return ResponseEntity(response, errorCode.status)
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        log.error("Unhandled Exception occurred: {}", e.message, e)
        val errorCode = KhErrorCode.INTERNAL_ERROR
        val response = ErrorResponse.of(errorCode)
        return ResponseEntity(response, errorCode.status)
    }

    data class ErrorResponse(
        val code: String,
        val message: String
    ) {
        companion object {
            fun of(errorCode: KhErrorCode): ErrorResponse {
                return ErrorResponse(
                    code = errorCode.code,
                    message = errorCode.message
                )
            }
        }
    }
}