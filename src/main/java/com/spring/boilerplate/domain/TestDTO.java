package com.spring.boilerplate.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TestDTO {
    private String name;
    private int age;
}
