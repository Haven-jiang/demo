package com.Haven.service.impl;

import com.Haven.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long delete(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    @Override
    public Boolean setExpire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long incExpire(String key, long time) {
        Long count = redisTemplate.opsForValue().increment(key, 1);
        if (count != null && count == 1) this.setExpire(key, time);
        return count;
    }

    @Override
    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    @Override
    public Object getHash(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public Boolean setHash(String key, String hashcode, Object value, long time) {
        redisTemplate.opsForHash().put(key, hashcode, value);
        return this.setExpire(key, time);
    }

    @Override
    public void setHash(String key, String hashcode, Object value) {
        redisTemplate.opsForHash().put(key, hashcode, value);
    }

    @Override
    public Map getAllHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Boolean setAllHash(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        return this.setExpire(key, time);
    }

    @Override
    public void setAllHash(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

}
