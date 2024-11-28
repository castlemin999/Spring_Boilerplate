package com.spring.boilerplate.global.exception.handler;

import com.spring.boilerplate.global.api.ApiResponse;
import com.spring.boilerplate.global.api.ErrorData;
import com.spring.boilerplate.global.api.constant.ErrorConstant;
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
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(NullPointerException.class)
    protected ApiResponse<ErrorData> handleNullPointerException(NullPointerException exception) {
        log.error("HttpRequestMethodNotSupportedException", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorConstant.NULL_POINT.getErrorCode())
                .errorMessage(ErrorConstant.NULL_POINT.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorConstant.NULL_POINT.getStatusCode(), response);
    }

    /**
     * 잘못 된 HTTP Request Method 로 요청 하는 경우
     * @param exception HttpRequestMethodNotSupportedException
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ApiResponse<ErrorData> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error("HttpRequestMethodNotSupportedException", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorConstant.BAD_REQUEST_METHOD.getErrorCode())
                .errorMessage(ErrorConstant.BAD_REQUEST_METHOD.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorConstant.BAD_REQUEST_METHOD.getStatusCode(), response);
    }

    /**
     * API 호출 시 Header 내에 데이터 값이 유효하지 않은 경우
     * @param exception MissingRequestHeaderException
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ApiResponse<ErrorData> handleMissingRequestHeaderException(MissingRequestHeaderException exception) {
        log.error("MissingRequestHeaderException", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorConstant.BAD_REQUEST_HEADER.getErrorCode())
                .errorMessage(ErrorConstant.BAD_REQUEST_HEADER.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorConstant.BAD_REQUEST_HEADER.getStatusCode(), response);
    }

    /**
     * 메소드에 잘못 된 인자가 넘어가는 경우
     * @param exception IllegalArgumentException
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(IllegalArgumentException.class)
    protected ApiResponse<ErrorData> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error("IllegalArgumentException", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorConstant.ILLEGAL_ARGUMENT.getErrorCode())
                .errorMessage(ErrorConstant.ILLEGAL_ARGUMENT.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorConstant.ILLEGAL_ARGUMENT.getStatusCode(), response);
    }

    /**
     * 클라이언트에서 request로 파라미터 데이터가 넘어오지 않았을 경우
     * @param exception MissingServletRequestParameterException
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ApiResponse<ErrorData> handleMissingRequestHeaderExceptionException(MissingServletRequestParameterException exception) {
        log.error("MissingServletRequestParameterException", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorConstant.BAD_REQUEST_PARAMETER.getErrorCode())
                .errorMessage(ErrorConstant.BAD_REQUEST_PARAMETER.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorConstant.BAD_REQUEST_PARAMETER.getStatusCode(), response);
    }

    /**
     * 잘못된 서버 요청일 경우 발생한 경우
     * @param exception HttpClientErrorException
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    protected ApiResponse<ErrorData> handleBadRequestException(HttpClientErrorException exception) {
        log.error("HttpClientErrorException", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorConstant.BAD_REQUEST.getErrorCode())
                .errorMessage(ErrorConstant.BAD_REQUEST.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorConstant.BAD_REQUEST.getStatusCode(), response);
    }

    /**
     * 잘못된 주소로 요청 한 경우
     * @param exception NoHandlerFoundException
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiResponse<ErrorData> handleNoHandlerFoundExceptionException(NoHandlerFoundException exception) {
        log.error("NoHandlerFoundException", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorConstant.NOT_FOUND.getErrorCode())
                .errorMessage(ErrorConstant.NOT_FOUND.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorConstant.NOT_FOUND.getStatusCode(), response);
    }

    /**
     * 모든 Exception 처리
     * @param exception Exception
     * @return ApiResponse<ErrorData>
     */
    @ExceptionHandler(Exception.class)
    protected ApiResponse<ErrorData> handleAllException(Exception exception) {
        log.error("Exception", exception);
        ErrorData response = ErrorData.builder()
                .errorCode(ErrorConstant.OTHER_ERROR.getErrorCode())
                .errorMessage(ErrorConstant.OTHER_ERROR.getErrorMessage())
                .build();

        return ApiResponse.fail(ErrorConstant.OTHER_ERROR.getStatusCode(), response);
    }

}
