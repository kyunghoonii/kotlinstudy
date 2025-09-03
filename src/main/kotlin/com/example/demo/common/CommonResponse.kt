package com.example.demo.common

import com.example.demo.exception.KhExceptionHandler
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CommonResponse<T>(
    var code: String,
    var msg: String,
    var data: T? = null
) {
    companion object{
        @JvmStatic // Java 코드와의 상호운용성을 위해 추가하면 좋음
        fun <T> success(code:String, msg:String, data: T?): CommonResponse<T> {
            return CommonResponse(code, msg, data)
        }

        // 성공 시 응답 (데이터 미포함)
        @JvmStatic
        fun <T> success(code:String, msg:String): CommonResponse<T> {
            return CommonResponse(code, msg, null)
        }

        // 실패 시 응답
        @JvmStatic
        fun <T> fail(code: String, msg: String, data: T?=null): CommonResponse<T> {
            return CommonResponse(code, msg, data)
        }

        @JvmStatic
        fun <T> fail(code: String, msg: String): CommonResponse<T> {
            return CommonResponse(code, msg)
        }
    }
}
