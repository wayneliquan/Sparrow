package com.wayne.sparrow.learn;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {

    @Test
    public void connRedis() {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.set("name", "wayne");
        System.out.println(jedis.get("name"));
        jedis.close();
    }

    @Test
    public void connPoolRedis() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(30);
        config.setMaxIdle(10);

        JedisPool jedisPool = new JedisPool(config, "127.0.0.1");

        Jedis jedis = null;

        jedis = jedisPool.getResource();
        jedis.set("name","wayne2");
        System.out.println(jedis.get("name"));
        jedis.close();
        if (!jedisPool.isClosed()) {
            jedisPool.close();
        }
    }
}
