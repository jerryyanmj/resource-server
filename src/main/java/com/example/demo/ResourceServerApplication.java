package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@RestController
public class ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServerApplication.class, args);
    }

    @RequestMapping(value = "/products")
    public String[] getProductName(@RequestHeader(name = "Authorization", required = true) String token) {
        return new String[]{"Honey", "Message 2", token};
    }

    @Bean
    public CommonsRequestLoggingFilter httpRequestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeHeaders(true);
        filter.setIncludePayload(true);
        filter.setIncludeQueryString(true);
        filter.setBeforeMessagePrefix("============ Incoming Request Begins ============\n");
        filter.setAfterMessageSuffix("\n============ Incoming Request Ends ============");
        return filter;
    }
}
