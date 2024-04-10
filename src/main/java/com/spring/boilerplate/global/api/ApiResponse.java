package com.spring.boilerplate.global.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [API Response]
 * API 응답 공통 포맷 정의
 * @author Jayden
 * @version 1.0
 * @since 2024-04-10
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final int SUCCESS_STATUS_CODE = 200;
    private static final int FAIL_STATUS_CODE = 400;
    private String status;          // 응답 성공 실패 여부
    private Integer statusCode;     // Http Status
    private T value;                // 응답 데이터

    private ApiResponse(String status, Integer statusCode, T value) {
        this.status = status;
        this.statusCode = statusCode;
        this.value = value;
    }

    /**
     * [Success Response]
     * API response data 값이 존재할 때 호출
     * @param statusCode, value
     * @return ApiResponse<T>
     */
    public static <T> ApiResponse<T> success(Integer statusCode, T value) {
        return new ApiResponse<>(SUCCESS_STATUS, statusCode, value);
    }

    /**
     * [Success Response]
     * API response data 값이 필요 없을 때 호출
     * @return ApiResponse<T>
     */
    public static ApiResponse<?> success() {
        return new ApiResponse<>(SUCCESS_STATUS, SUCCESS_STATUS_CODE, null);
    }

    /**
     * [Fail Response]
     * API 응답에 실패 했을 때 호출
     * @param statusCode, errorResponse
     * @return ApiResponse<ErrorResponse>
     */
    public static ApiResponse<ErrorResponse> fail(Integer statusCode, ErrorResponse errorResponse) {
        return new ApiResponse<>(FAIL_STATUS, statusCode, errorResponse);
    }

}
