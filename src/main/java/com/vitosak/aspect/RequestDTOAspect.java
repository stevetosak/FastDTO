package com.vitosak.aspect;

import com.vitosak.annotations.RequestDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class RequestDTOAspect {
    @Autowired
    private ApplicationContext applicationContext;


    //otprilika
    @Around("execution(* *(.., @com.vitosak.annotations.RequestDTO (*), ..)) && args(dtoParam,..)")
    public Object injectRequestDTO(ProceedingJoinPoint pjp, @RequestDTO Object dtoParam) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = pjp.getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        Parameter[] params = method.getParameters();
        Object[] originalArgs = pjp.getArgs();

        for(int i = 0; i < params.length; i++) {
            Parameter param = params[i];
            if(param.isAnnotationPresent())
        }

    }
}
