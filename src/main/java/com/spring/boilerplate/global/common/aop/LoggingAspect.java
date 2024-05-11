package com.spring.boilerplate.global.common.aop;

import com.spring.boilerplate.global.common.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class LoggingAspect {

    // 적용 할 대상 지정
    @Pointcut("within(com.spring.boilerplate.domain.*.controller..*)")
    public void pointCut(){}

    @Around("com.spring.boilerplate.global.common.aop.LoggingAspect.pointCut()")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> clazz = pjp.getTarget().getClass();
//        Logger logger = LogManager.getLogger(clazz);
        Object result = null;
        long start = System.currentTimeMillis();
        String requestUrl = getRequestUrl(pjp, clazz);
        try {
            result = pjp.proceed(pjp.getArgs());
            return result;
        } finally {
            long end = System.currentTimeMillis();
            long timeMs = end - start;
            log.info("============================================= Request & Response Data =============================================");
            log.info("URL : {} ", requestUrl);
            log.info("Request : {}", getParams(pjp));
            log.info("Response : {}", result);
            log.info("END : {}Ms", timeMs);
            log.info("===================================================================================================================");
        }
    }

    private String getRequestUrl(JoinPoint joinPoint, Class<?> clazz) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class); // class annotation
        String baseUrl = requestMapping.value()[0];
        String url = Stream.of(GetMapping.class, PutMapping.class, PostMapping.class, PatchMapping.class, DeleteMapping.class, RequestMapping.class)
                .filter(method::isAnnotationPresent) // annotation이 부여되고 있는지 확인
                .map(mappingClass -> getUrl(method, mappingClass, baseUrl))
                .findFirst()
                .orElse(null);
        return url;
    }

    private String getUrl(Method method, Class<? extends Annotation> annotationClass, String baseUrl){
        Annotation annotation = method.getAnnotation(annotationClass);
        String[] value;
        String httpMethod = null;
        try {
            value = (String[])annotationClass.getMethod("value").invoke(annotation); // method Annotation
            httpMethod = (annotationClass.getSimpleName().replace("Mapping", "")).toUpperCase(); // HTTP Method
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            return null;
        }
        return String.format("[%s] %s%s", httpMethod, baseUrl, value.length > 0 ? value[0] : "");
    }

    private Map<String, Object> getParams(JoinPoint joinPoint) {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames(); // method parameter names
        Object[] args = joinPoint.getArgs(); // method parameter values

        Map<String, Object> params = new HashMap<>();
        String argClass = "";

        if (args[0] == null) {
            params.put("Param", null);
        } else {
            for (int i = 0; i < parameterNames.length; i++) {
                argClass = args[i].getClass().getName().toUpperCase();
                if (argClass.contains("DTO")) {
                    params.put(parameterNames[i], JSONObject.toJSONString(ConvertUtil.convertObjectToMap(args[i])));
                } else {
                    params.put(parameterNames[i], args[i]);
                }
            }
        }

        return params;
    }
}
