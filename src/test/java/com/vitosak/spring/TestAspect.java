package com.vitosak.spring;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
public class TestAspect {
    @Before("execution(* *.something(..))")
    public void logSuccesfullCalling() {
        throw new AspectInvokationSuccesful("");
    }
}
