package com.spring.boilerplate.global.api.code;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * [API Response]
 * Error 관련 코드 정의
 * @since 2024-04-11
 * @author Jayden (최성민)
 * @version 1.0
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode {

    //************************************************ Client Error ************************************************//
    NOT_FOUND(404, "ERR_CLIENT_001", "Not Found Exception"),                        // 서버로 요청한 리소스가 존재하지 않음
    NULL_POINT(404, "ERR_CLIENT_002", "Null Point Exception"),                      // NULL Point Exception 발생
    BAD_REQUEST(400, "ERR_CLIENT_003", "Bad Request Exception"),                    // 잘못된 서버 요청
    BAD_REQUEST_METHOD(400, "ERR_CLIENT_004", "Bad Request HTTP Method"),           // 잘못된 HTTP Request Method 요청
    BAD_REQUEST_HEADER(400, "ERR_CLIENT_005", "Request header is insufficient"),    // Request Header 정보가 잘못된 경우
    BAD_REQUEST_BODY(400, "ERR_CLIENT_006", "Request body is insufficient"),        // Request Body가 정보가 잘못된 경우
    BAD_REQUEST_PARAMETER(400, "ERR_CLIENT_007", "No request parameter"),           // 파라미터가 존재하지 않는 경우
    ILLEGAL_ARGUMENT(400, "ERR_CLIENT_008", "Illegal or inappropriate argument"),   // 메소드에 잘못된 인자가 넘어 가는 경우
    JSON_PARSE(400, "ERR_CLIENT_009", "Json parsing Exception"),                    // Json 파싱 에러가 발생 하는 경우
    ID_DUPLICATED(400, "ERR_CLIENT_010", "Id is duplicated"),                       // ID 중복이 발생 하는 경우
    NICKNAME_DUPLICATED(400, "ERR_CLIENT_011", "Nickname is duplicated"),           // 닉네임 중복이 발생 하는 경우
    OTHER_ERROR(400, "ERR_CLIENT_099", "Other Error"),                              // 그외 모든 에러


    //************************************************ Auth Error ************************************************//
    UNAUTHORIZED(401,"ERR_AUTH_001", "Account is Unauthorized"),                        // 인증 되지 않은 사용자
    TOKEN_NOT_ALLOWED(415, "ERR_AUTH_002","Token is Not Allowed" ),                     // 허용 되지 않은 토큰
    TOKEN_EXPIRED(401,"ERR_AUTH_003", "Token is Expired"),                              // 토큰 만료
    TOKEN_NUll(401,"ERR_AUTH_004", "Token does not exist"),                             // 헤더에 토큰이 존재하지 않는 경우
    NOT_ACCESS_TOKEN(401, "ERR_AUTH_005", "It's not AccessToken"),                      // Access Token이 아닌 경우
    NOT_REFRESH_TOKEN(401, "ERR_AUTH_006", "It's not RefreshToken"),                    // Refresh Token이 아닌 경우
    NOT_EXIST_AUTH_IN_TOKEN(415, "ERR_AUTH_007", "Account does not exist in Token"),    // 토큰 내에 사용자 정보가 없는 경우
    NOT_MATCHED_AUTH(401, "ERR_AUTH_008", "Account in the token does not match"),       // 토큰 내의 사용자 정보와 요청 사용자의 정보가 일치 하지 않는 경우
    NOT_MATCHED_REFRESH(409, "ERR_AUTH_009", "RefreshToken does not match"),            // DB의 RefreshToken과 일치 하지 않는 경우
    PARSING_ERROR(415, "ERR_AUTH_010", "Token Parsing Error"),                          // 토큰 Parsing 중 에러가 발생하는 경우
    OTHER_TOKEN_ERROR(401, "ERR_AUTH_011", "Other Token Error"),                        // 그외 토큰 관련 에러
    LOGIN_DUPLICATED(409, "ERR_AUTH_014", "Login was duplicated"),                      // 로그인이 중복되는 경우

    //************************************************ Server Error ************************************************//
    INTERNAL_SERVER(500, "ERR_SERVER_001", "Internal Server Error"),                    // 서버 내부 에러
    ;

    // 에러 상태 코드
    private int statusCode;

    // 다국어 처리 코드
    private String messageCode;

    // 에러 코드 메시지
    private String message;

    // 생성자 구성
    ErrorCode(final int statusCode, final String messageCode, final String message) {
        this.statusCode = statusCode;
        this.messageCode = messageCode;
        this.message = message;
    }

}
