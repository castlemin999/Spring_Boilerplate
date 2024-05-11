package com.spring.boilerplate.global.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;

@Slf4j
public class ConvertUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * [공통함수]
     * Object 형을 Map 형태로 변환 함수
     * 필드 변수까지 전부 변환
     * @param obj {Object}
     * @return Map
     */
    public static HashMap<String, Object> convertObjectToMap(Object obj) {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            HashMap<String, Object> resultMap = new HashMap<>();
            for (Field field : fields) {
                field.setAccessible(true);
                resultMap.put(field.getName(), field.get(obj));
            }
            return resultMap;
        } catch (IllegalArgumentException | IllegalAccessException e) {
            log.warn(e.getMessage(), e.getStackTrace()[0].toString());
        }
        return null;
    }
}
