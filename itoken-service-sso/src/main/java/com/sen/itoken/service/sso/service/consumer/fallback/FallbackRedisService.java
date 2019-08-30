package com.sen.itoken.service.sso.service.consumer.fallback;

import com.sen.itoken.service.sso.service.consumer.RedisService;
import org.springframework.stereotype.Component;

/**
 * @Auther: Sen
 * @Date: 2019/8/24 03:25
 * @Description:
 * 熔断方法
 */
@Component
public class FallbackRedisService implements RedisService {
    @Override
    public String pull(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
