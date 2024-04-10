package com.spring.boilerplate.global.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [API Response]
 * API 응답 실패에 대한 공통 포맷 정의
 * @author Jayden
 * @since 2024-04-10
 * @version 1.0
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;

    @Builder
    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
