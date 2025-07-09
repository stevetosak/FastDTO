package com.vitosak.controller;

import com.vitosak.Person;
import com.vitosak.annotations.RequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestDTO(configName = "test") Person p) {
        System.out.println(p.toString());
        return ResponseEntity.ok("Hello World");
    }
}
