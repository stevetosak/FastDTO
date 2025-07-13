package com.vitosak.spring.setup;

import com.vitosak.spring.TestAspect;
import com.vitosak.aspect.RequestDTOAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfiguration {
    @Bean
    public RequestDTOAspect requestDTOAspect() {
        return new RequestDTOAspect();
    }
    @Bean
    public TestAspect testAspect() {
        return new TestAspect();
    }
}
