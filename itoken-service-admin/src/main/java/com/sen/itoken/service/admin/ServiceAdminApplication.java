package com.sen.itoken.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @Auther: Sen
 * @Date: 2019/8/22 17:20
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "com.sen.itoken")
@EnableEurekaClient
@MapperScan(basePackages = {"com.sen.itoken.service.admin.mapper","com.sen.itoken.common.mapper"})
public class ServiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class, args);
    }
}
