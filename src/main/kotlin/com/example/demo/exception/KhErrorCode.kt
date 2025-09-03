package com.example.demo.exception

import org.springframework.http.HttpStatus

enum class KhErrorCode(
    val status: HttpStatus,
    val code: String,
    val message: String
){
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "C4001", "입력 값이 유효하지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "C4010", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "C4030", "접근 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "C4040", "리소스를 찾을 수 없습니다."),
    CONFLICT(HttpStatus.CONFLICT, "C4090", "요청이 현재 상태와 충돌합니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C4050", "허용되지 않은 메서드입니다."),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "C4150", "지원하지 않는 미디어 타입입니다."),

    // 비즈니스(샘플)
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "U4091", "이미 존재하는 사용자입니다."),
    ITEM_OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "I4002", "재고가 부족합니다."),

    // 서버
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S5000", "서버 내부 오류가 발생했습니다.");
}