package com.vitosak.spring.setup;

import models.EveryThingModel;
import models.SimpleModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/v1")
public class TestController {
    private final TestRepo repo;

    public TestController(TestRepo repo) {this.repo = repo;}

    @GetMapping("/heart-beat")
    public ResponseEntity<Void> getResponse() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/aspect")
    public ResponseEntity<String> getAspect() {
        return ResponseEntity.ok("");
    }

    @GetMapping("simple-model")
    public ResponseEntity<SimpleModel> getSimpleModel(){
       throw new RuntimeException();
    }

    @PostMapping("everything-model")
    public ResponseEntity<EveryThingModel> getEveryThingModel(){
       throw new RuntimeException();
    }

    @PostMapping("/db/everything-model")
    public ResponseEntity<EveryThingModel> getFromDatabase(){
       throw new RuntimeException();
    }

    //TODO: add test for composite key models
}
