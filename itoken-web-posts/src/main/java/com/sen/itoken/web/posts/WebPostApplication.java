package com.sen.itoken.web.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * @Auther: Sen
 * @Date: 2019/8/26 22:01
 * @Description: 文章服务消费者
 */
@SpringBootApplication(scanBasePackages = "com.sen.itoken")
@EnableDiscoveryClient
@EnableFeignClients
public class WebPostApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebPostApplication.class, args);
    }
}
