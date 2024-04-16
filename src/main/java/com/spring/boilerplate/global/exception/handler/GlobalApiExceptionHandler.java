package com.spring.boilerplate.global.exception.handler;

import com.spring.boilerplate.global.api.ApiResponse;
import com.spring.boilerplate.global.api.ErrorResponse;
import com.spring.boilerplate.global.api.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * [Global Exception]
 * Controller 내에서 발생하는 모든 Exception 처리
 * @author Jayden
 * @since 2024-04-16
 * @version 1.0
 */
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
@RestControllerAdvice
public class GlobalApiExceptionHandler {

    /**
     * NULL 값이 발생한 경우
     * @param exception NullPointerException
     * @return ApiResponse<ErrorResponse>
     */
    @ExceptionHandler(NullPointerException.class)
    protected ApiResponse<ErrorResponse> handleNullPointerException(NullPointerException exception) {
        log.error("HttpRequestMethodNotSupportedException", exception);
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(ErrorCode.NULL_POINT.getErrorCode())
                .errorMessage(ErrorCode.NULL_POINT.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorCode.NULL_POINT.getStatusCode(), response);
    }

    /**
     * 잘못 된 HTTP Request Method 로 요청 하는 경우
     * @param exception HttpRequestMethodNotSupportedException
     * @return ApiResponse<ErrorResponse>
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ApiResponse<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error("HttpRequestMethodNotSupportedException", exception);
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(ErrorCode.BAD_REQUEST_METHOD.getErrorCode())
                .errorMessage(ErrorCode.BAD_REQUEST_METHOD.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorCode.BAD_REQUEST_METHOD.getStatusCode(), response);
    }

    /**
     * API 호출 시 Header 내에 데이터 값이 유효하지 않은 경우
     * @param exception MissingRequestHeaderException
     * @return ApiResponse<ErrorResponse>
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ApiResponse<ErrorResponse> handleMissingRequestHeaderException(MissingRequestHeaderException exception) {
        log.error("MissingRequestHeaderException", exception);
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(ErrorCode.BAD_REQUEST_HEADER.getErrorCode())
                .errorMessage(ErrorCode.BAD_REQUEST_HEADER.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorCode.BAD_REQUEST_HEADER.getStatusCode(), response);
    }

    /**
     * 메소드에 잘못 된 인자가 넘어가는 경우
     * @param exception IllegalArgumentException
     * @return ApiResponse<ErrorResponse>
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ApiResponse<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error("IllegalArgumentException", exception);
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(ErrorCode.ILLEGAL_ARGUMENT.getErrorCode())
                .errorMessage(ErrorCode.ILLEGAL_ARGUMENT.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorCode.ILLEGAL_ARGUMENT.getStatusCode(), response);
    }

    /**
     * 클라이언트에서 request로 파라미터 데이터가 넘어오지 않았을 경우
     * @param exception MissingServletRequestParameterException
     * @return ApiResponse<ErrorResponse>
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ApiResponse<ErrorResponse> handleMissingRequestHeaderExceptionException(MissingServletRequestParameterException exception) {
        log.error("MissingServletRequestParameterException", exception);
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(ErrorCode.BAD_REQUEST_PARAMETER.getErrorCode())
                .errorMessage(ErrorCode.BAD_REQUEST_PARAMETER.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorCode.BAD_REQUEST_PARAMETER.getStatusCode(), response);
    }

    /**
     * 잘못된 서버 요청일 경우 발생한 경우
     * @param exception HttpClientErrorException
     * @return ApiResponse<ErrorResponse>
     */
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    protected ApiResponse<ErrorResponse> handleBadRequestException(HttpClientErrorException exception) {
        log.error("HttpClientErrorException", exception);
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(ErrorCode.BAD_REQUEST.getErrorCode())
                .errorMessage(ErrorCode.BAD_REQUEST.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorCode.BAD_REQUEST.getStatusCode(), response);
    }

    /**
     * 잘못된 주소로 요청 한 경우
     * @param exception NoHandlerFoundException
     * @return ApiResponse<ErrorResponse>
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiResponse<ErrorResponse> handleNoHandlerFoundExceptionException(NoHandlerFoundException exception) {
        log.error("NoHandlerFoundException", exception);
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(ErrorCode.NOT_FOUND.getErrorCode())
                .errorMessage(ErrorCode.NOT_FOUND.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorCode.NOT_FOUND.getStatusCode(), response);
    }

    /**
     * 모든 Exception 처리
     * @param exception Exception
     * @return ApiResponse<ErrorResponse>
     */
    @ExceptionHandler(Exception.class)
    protected ApiResponse<ErrorResponse> handleAllException(Exception exception) {
        log.error("Exception", exception);
        ErrorResponse response = ErrorResponse.builder()
                .errorCode(ErrorCode.OTHER_ERROR.getErrorCode())
                .errorMessage(ErrorCode.OTHER_ERROR.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorCode.OTHER_ERROR.getStatusCode(), response);
    }

}
