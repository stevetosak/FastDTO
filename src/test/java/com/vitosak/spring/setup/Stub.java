package com.vitosak.spring.setup;

import org.springframework.stereotype.Component;

@Component
public class Stub {
    public void something(){
        throw new RuntimeException("something went wrong");
    }
}
