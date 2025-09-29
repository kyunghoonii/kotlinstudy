package com.example.demo.exception

import org.springframework.http.HttpStatus

enum class KhErrorCode(
    val status: HttpStatus,
    val code: String,
    val message: String
){
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "5000", "입력 값이 유효하지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "5001", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "5002", "접근 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "5003", "리소스를 찾을 수 없습니다."),
    CONFLICT(HttpStatus.CONFLICT, "5004", "요청이 현재 상태와 충돌합니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "5005", "허용되지 않은 메서드입니다."),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "5006", "지원하지 않는 미디어 타입입니다."),

    // 비즈니스
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "7000", "이미 존재하는 사용자입니다."),
    ACCOUNT_INSERT_FAIL(HttpStatus.CONFLICT, "7001", "사용자 등록에 실패하였습니다."),
    ITEM_OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "7002", "재고가 부족합니다."),
    PARAMETER_ERROR(HttpStatus.BAD_REQUEST, "7003", "필수 파라미터가 없습니다."),

    // 서버
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S5000", "서버 내부 오류가 발생했습니다.");
}