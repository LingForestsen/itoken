package com.sen.itoken.web.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: Sen
 * @Date: 2019/8/29 03:07
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "com.sen.itoken")
@EnableDiscoveryClient
@EnableFeignClients
public class WebBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebBackendApplication.class, args);
    }
}
