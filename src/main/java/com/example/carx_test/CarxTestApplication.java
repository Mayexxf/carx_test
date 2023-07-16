package com.example.carx_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarxTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarxTestApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<JsonSizeFilter> jsonSizeFilter() {
        FilterRegistrationBean<JsonSizeFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JsonSizeFilter());
        registrationBean.addUrlPatterns("/");
        return registrationBean;
    }
}
