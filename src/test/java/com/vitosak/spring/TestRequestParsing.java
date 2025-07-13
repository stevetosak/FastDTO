package com.vitosak.spring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

//TODO: load the aspect into spring
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringJUnitWebConfig(locations= "classpath:com/vitosak/spring/setup")
public class TestRequestParsing {

    @LocalServerPort
    int port;

    TestRestTemplate client = new TestRestTemplate();


    @BeforeAll
    static void setUp() {

    }

    @Test
    void shouldReturnResponse(){
    }
}
