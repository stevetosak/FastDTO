package com.vitosak.spring;

import com.vitosak.spring.setup.Stub;
import com.vitosak.spring.setup.TestApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = { TestApp.class }
)
@ExtendWith({ SpringExtension.class })
public class TestRequestParsing {

    @Autowired
    TestRestTemplate client;

    @Nested
    class TestBootstraping {
        @Test
        void shouldReturn200OK() {
            ResponseEntity<String> response = client.getForEntity("/test/v1/heart-beat", String.class);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        }
    }

    @Nested
    class TestAspectBootstraping {
        @Autowired
        Stub stub;

        @Test
        void shouldThrowAspectSuccesful(){
            Assertions.assertThrows(AspectInvokationSuccesful.class, () -> stub.something());
        }
    }

    @Nested
    class TestParsing{
        @Test
        void shouldReturnSimpleModel() {
            Assertions.assertThrowsExactly(
                    AspectInvokationSuccesful.class,
                    () -> client.getForObject("/test/v1/aspect", String.class)
            );
        }

        @Test
        void shouldReturnEverythingEntityModel() {

        }

        @Test
        void whenRequestedDBOp_thenReturnEverythingModel() {

        }

        @Test
        void shouldReturnModelWithCompositeKeys() {

        }
    }
}