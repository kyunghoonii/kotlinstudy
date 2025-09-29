package com.example.demo.common

import com.example.demo.exception.KhExceptionHandler
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CommonResponse<T>(
    var resultCode: String,
    var resultMsg: String,
    var resultData: T? = null
) {
    companion object{
        @JvmStatic // Java 코드와의 상호운용성을 위해 추가하면 좋음
        fun <T> success(resultCode:String, resultMsg:String, resultData: T?): CommonResponse<T> {
            return CommonResponse(resultCode, resultMsg, resultData)
        }

        // 성공 시 응답 (데이터 미포함)
        @JvmStatic
        fun <T> success(resultCode:String, resultMsg:String): CommonResponse<T> {
            return CommonResponse(resultCode, resultMsg, null)
        }

        // 성공 시 응답 (데이터 미포함)
        @JvmStatic
        fun <T> success(): CommonResponse<T> {
            return CommonResponse("0000", "성공.", null)
        }

        // 실패 시 응답
        @JvmStatic
        fun <T> fail(resultCode: String, resultMsg: String, resultData: T?=null): CommonResponse<T> {
            return CommonResponse(resultCode, resultMsg, resultData)
        }

        @JvmStatic
        fun <T> fail(resultCode: String, resultMsg: String): CommonResponse<T> {
            return CommonResponse(resultCode, resultMsg)
        }

    }
}
