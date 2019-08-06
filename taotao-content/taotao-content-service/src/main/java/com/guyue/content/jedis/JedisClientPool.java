package com.guyue.content.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Component
public class JedisClientPool implements JedisClient{
    @Autowired
    private JedisPool jedisPool;
    @Override
    public String set(String key, String value) {
        Jedis jd = jedisPool.getResource();
        String result = jd.set(key, value);
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jd = jedisPool.getResource();
        String result = jd.get(key);
        return result;
    }

    @Override
    public Long het(String key) {
        Jedis jd = jedisPool.getResource();
        Long result = jd.del(key);
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Jedis jd = jedisPool.getResource();
        Boolean falg = jd.exists(key);
        return falg;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jd = jedisPool.getResource();
        Long result = jd.expire(key, seconds);
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jd = jedisPool.getResource();
        Long result = jd.ttl(key);
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jd = jedisPool.getResource();
        Long result = jd.incr(key);
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jd = jedisPool.getResource();
        Long result = jd.hset(key,field,value);
        return result;
    }

    @Override
    public String hget(String key, String field) {
        Jedis jd = jedisPool.getResource();
        String result = jd.hget(key, field);
        return result;
    }

    @Override
    public Long hdel(String key, String... field) {
        Jedis jd = jedisPool.getResource();
        Long result = jd.hdel(key,field);
        return result;
    }
}
