package com.sen.itoken.service.redis.service;

/**
 * @Auther: Sen
 * @Date: 2019/8/24 00:09
 * @Description:
 */
public interface RedisService {
    /**
     * 往redis里面存数据
     * @param key
     * @param value
     * @param seconds
     */
    void put(String key, Object value, long seconds);

    /**
     * 从redis取数据
     * @param key
     * @return
     */
    Object get(String key);

}
