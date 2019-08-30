package com.sen.itoken.web.admin.service;

import com.sen.itoken.web.admin.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: Sen
 * @Date: 2019/8/25 19:01
 * @Description: 消费redis服务
 */
@FeignClient(value = "itoken-service-redis",fallback = RedisServiceFallback.class)
public interface RedisService {

    @RequestMapping(value = "put",method = RequestMethod.POST)
    String put(@RequestParam(value = "key") String key,
               @RequestParam(value = "value") String value,
               @RequestParam(value = "seconds") long seconds);
    @RequestMapping(value = "get",method = RequestMethod.GET)
    String get(@RequestParam(value = "key") String key);
}
