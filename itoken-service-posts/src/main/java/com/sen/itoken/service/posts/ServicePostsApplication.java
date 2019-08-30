package com.sen.itoken.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 16:08
 * @Description: 文章服务
 */
@SpringBootApplication(scanBasePackages = "com.sen.itoken")
@EnableSwagger2
@EnableEurekaClient
@MapperScan(basePackages = {"com.sen.itoken.service.posts.mapper","com.sen.itoken.common.mapper"})
public class ServicePostsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePostsApplication.class, args);
    }
}
