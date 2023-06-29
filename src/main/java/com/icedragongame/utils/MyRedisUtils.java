package com.icedragongame.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author gengxuelong
 * @date 2023/6/29 21:47
 */
@Component
@SuppressWarnings(value = { "unchecked","rawtypes" })
public class MyRedisUtils {
    @Autowired
    RedisTemplate redisTemplate;

    /*object----------------*/
    public <T> void setObject(String key,T obj){
        redisTemplate.opsForValue().set(key,obj);
    }
    public <T> T getObject(String key){
        ValueOperations<String,T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
    public boolean deleteObject(String key){
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }
    public long deleteObjectCollection(Collection keyList){
        return redisTemplate.delete(keyList);
    }

    /*time----------------*/
    public void expire(String key,long timeoutSec){
        redisTemplate.expire(key,timeoutSec, TimeUnit.SECONDS);
    }

    /*list--------------------*/
    public <T> long setList(String key, List<T> list){
        return redisTemplate.opsForList().rightPushAll(key,list);
    }
    public <T> List<T> getList(String key){
        ListOperations<String,T> listListOperations = redisTemplate.opsForList();
        return listListOperations.range(key,0,-1);
    }

    /*set----------------*/
    public <T> void setSet(String key, Set<T> set){
        BoundSetOperations<String,T> boundSetOperations = redisTemplate.boundSetOps(key);
        for (T t : set) {
            boundSetOperations.add(t);
        }
    }
    public <T> Set<T> getSet(String key){
        return redisTemplate.opsForSet().members(key);
    }

    /*map------------*/
    public <K,T> void setMap(String key, Map<K,T> map){
        redisTemplate.opsForHash().putAll(key,map);
    }
    public <K,T>  Map<K,T> getMap(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    public <K,T> void setMapValue(String key, K k,T t){
        redisTemplate.opsForHash().put(key,k,t);
    }

    public <K,T> void increaseMapValue(String key, K k,int num){
        redisTemplate.opsForHash().increment(key,k,num);
    }
    public <K,T> void increaseOneToMapValue(String key, K k){
        redisTemplate.opsForHash().increment(key,k,1);
    }
    public <K,T> void setMultiMapValue(String key,Map<K,T> map){
        redisTemplate.opsForHash().putAll(key,map);
    }
    public <K,T>  T getMapValue(String key,K k) {
        HashOperations<String, K, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, k);
    }
    public <K> void deleteMapValue(String key,K k){
        redisTemplate.opsForHash().delete(key,k);
    }
    public <K,T> List<T> getMultiMapValue(String key,List<K> kList){
        HashOperations<String,K,T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.multiGet(key,kList);
    }










}
