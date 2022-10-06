package com.stussy.stussyclone20220929.aop;

import com.stussy.stussyclone20220929.dto.CMRespDto;
import com.stussy.stussyclone20220929.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class ValidationAop {

    @Pointcut("@annotation(com.stussy.stussyclone20220929.aop.annotation.ValidAspect)")
    private void pointCut() {}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

        for(Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class){
                bindingResult = (BeanPropertyBindingResult) arg;
                break;
            }
        }

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<String, String>();

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });

            throw new CustomValidationException("Validation failed", errorMap);
        }
    }

    @AfterReturning(value = "pointCut()", returning = "returnObj") // Before 사용 시 같이 사용
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        log.info("Validation success: {}", returnObj);
    }

}

//if(bindingResult.hasErrors()) {
//        log.error("유효성 검사 오류 발생");
//        Map<String, String> errorMap = new HashMap<String, String>();
//
//        List<List<FieldError>> codeList = new ArrayList<List<FieldError>>();
//        codeList.add(new ArrayList<FieldError>());
//        codeList.add(new ArrayList<FieldError>());
//
//        bindingResult.getFieldErrors().forEach(error -> {
//        errorMap.put(error.getField(), error.getDefaultMessage());
//
//        if (error.getCode().equals("Pattern")) {
//        codeList.get(0).add(error);
//        } else if (error.getCode().equals("NotBlank")) {
//        codeList.get(1).add(error);
//        }
//        });
//
//        log.info("codeList: {}", codeList);
//
//        codeList.forEach(errorMapList -> {
//        errorMapList.forEach(error -> {
//        errorMap.put(error.getField(), error.getDefaultMessage());
//        });
//
//        log.info("error: {}", errorMap);
//        });
//        }

//            bindingResult.getFieldErrors().forEach(error -> {
//                log.info("Error: 코드({}), 필드명({}), 메시지({})", error.getCode(), error.getField(), error.getDefaultMessage());
//                if(!error.getCode().equals("NotBlank")){
//                    errorMap.put(error.getField(), error.getDefaultMessage());
//                } else {
//                    errorMap.put(error.getField(), error.getDefaultMessage());
//                }
//            });
//            bindingResult.getFieldErrors().forEach(error -> {
//                log.info("Error: 코드({}), 필드명({}), 메시지({})", error.getCode(), error.getField(), error.getDefaultMessage());
//                if(error.getCode().equals("NotBlank")){
//                    errorMap.put(error.getField(), error.getDefaultMessage());
//                }
//            });
//            return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "유효성 검사 실패", errorMap));
