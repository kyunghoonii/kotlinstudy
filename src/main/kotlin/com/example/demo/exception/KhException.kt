package com.example.demo.exception

class KhException(
    val errorCode: KhErrorCode,
    override val message: String = errorCode.message,
    val detail: String? = null,
    val payload: Any? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)