package com.sen.itoken.service.sso.service.consumer;

import com.sen.itoken.service.sso.service.consumer.fallback.FallbackRedisService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: Sen
 * @Date: 2019/8/24 03:17
 * @Description:
 * 消费Redis提供的服务（服务消费者）
 */
@FeignClient(value = "itoken-service-redis",fallback = FallbackRedisService.class)
public interface RedisService {
    /**
     * 调取redis服务存数据
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    @RequestMapping(value = "put", method = RequestMethod.POST)
    String pull(@RequestParam(value = "key") String key,
                @RequestParam(value = "value") String value,
                @RequestParam(value = "seconds") long seconds);

    /**
     * 调取redis服务取数据
     * @param key
     * @return
     */
    @RequestMapping(value = "get",method = RequestMethod.GET)
    String get(@RequestParam(value = "key") String key);

}
