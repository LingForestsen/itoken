package com.sen.itoken.web.admin.fallback;

import com.sen.itoken.web.admin.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * @Auther: Sen
 * @Date: 2019/8/25 19:07
 * @Description: redis服务熔断
 */
@Component
public class RedisServiceFallback implements RedisService {

    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
