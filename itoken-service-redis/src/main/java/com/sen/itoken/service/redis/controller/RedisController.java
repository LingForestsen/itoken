package com.sen.itoken.service.redis.controller;

import com.sen.itoken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Sen
 * @Date: 2019/8/24 00:17
 * @Description:
 */
@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "put",method = RequestMethod.POST)
    public String pull(String key, String value, long seconds) {
        redisService.put(key, value, seconds);
        return "ok";
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String get(String key) {
        Object object = redisService.get(key);
        if (object != null) {
            return String.valueOf(object);
        }
        return null;
    }
}
