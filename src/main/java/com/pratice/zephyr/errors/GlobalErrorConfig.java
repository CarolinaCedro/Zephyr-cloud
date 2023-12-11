package com.pratice.zephyr.errors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalErrorConfig {

    @Bean
    public GlobalErrorAttributes globalErrorAttributes() {
        return new GlobalErrorAttributes();
    }
}
