package com.spring.boilerplate.domain;

import com.spring.boilerplate.global.api.ApiResponse;
import com.spring.boilerplate.global.api.ErrorData;
import com.spring.boilerplate.global.api.constant.ErrorConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class TestController {

    @GetMapping("/success")
    public ResponseEntity<ApiResponse<TestDTO>> success() {
        TestDTO testDTO = TestDTO.builder().name("testA").age(30).build();
        return ResponseEntity.ok(ApiResponse.success(testDTO));
    }

    @GetMapping("/fail")
    public ResponseEntity<ApiResponse<ErrorData>> fail() {
        ErrorData errorData =
                ErrorData.builder()
                        .errorCode(ErrorConstant.BAD_REQUEST.getErrorCode())
                        .errorMessage(ErrorConstant.BAD_REQUEST.getErrorMessage())
                        .build();
        return ResponseEntity.status(ErrorConstant.BAD_REQUEST.getStatusCode()).body(ApiResponse.fail(ErrorConstant.BAD_REQUEST.getStatusCode(), errorData));
    }

}
