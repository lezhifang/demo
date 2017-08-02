package com.example.demo.tools;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;


/**
 * Created by LZF on 2017/6/16.
 */
@Component
public class JedisAdapter implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);
    private JedisPool pool;

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("redis://localhost:6379/15");
    }

    public long sadd(String key, String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.sadd(key, value);//添加成功返回1  否则返回0
        }catch(Exception e){
            logger.error("发生异常" + e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0;
    }

    public long expire(String key, int time){//time为s
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.expire(key, time);//设置成功返回1  否则返回0
        }catch(Exception e){
            logger.error("发生异常" + e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0;
    }

    public long lpush(String key, String value){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.lpush(key, value);
        }catch(Exception e){
            logger.error("发生异常" + e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0;
    }
    public String lpop(String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.lpop(key);
        }catch(Exception e){
            logger.error("发生异常" + e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    public List<String> lrange(String key, int start, int end){
            Jedis jedis = null;
            try{
                jedis = pool.getResource();
                return jedis.lrange(key, start, end);
            }catch(Exception e){
                logger.error("发生异常" + e.getMessage());
            }finally {
                if(jedis != null){
                    jedis.close();
                }
            }
        return null;
    }

    public Jedis getJedis() {
        return pool.getResource();
    }

    public Boolean exists(String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.exists(key);
        }catch(Exception e){
            logger.error("发生异常" + e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    public long llen(String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.llen(key);
        }catch(Exception e){
            logger.error("发生异常" + e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return 0;
    }

}



