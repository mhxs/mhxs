package com.example.redisdemo.service;

import com.example.redisdemo.entiey.StockEntity;
import com.example.redisdemo.mapper.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Resource
    private StockMapper stockMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void buyProduct(Long id) throws InterruptedException {
        String lock = "lock:product:" + id;
        String distributedLock = distributedLock(lock, 20, 100, 20000);
        if (distributedLock == null) {
            log.info("系统繁忙");
            return;
        }

        try {
            StockEntity stockEntity = stockMapper.find(id);
            log.info("库存剩余：{}", stockEntity.getStockNum());
            if (stockEntity.getStockNum() <= 0) {
                return;
            }
            stockMapper.reduceStock(id);
        } finally {
            releaselock(lock, distributedLock);
        }

    }

    @Override
    public void publish(String message) {
        redisTemplate.convertAndSend("mytopic", message);
    }

    /**
     * @param lockName           锁名称
     * @param lockTimeOutSecond  锁的超时时间 -- 为了防止服务重启从而redis锁无法释放的问题
     * @param retryMilliSecond   锁的重试时间 -- 多少毫秒尝试获取锁
     * @param retryMaxTimeSecond 最大尝试时间 -- 超过该时间默认获取锁失败
     * @return
     * @throws InterruptedException
     */
    private String distributedLock(String lockName, int lockTimeOutSecond, int retryMilliSecond, int retryMaxTimeSecond) throws InterruptedException {
        long startTimeMillis = System.currentTimeMillis();
        Boolean lockFlag;

        //尝试获取分布式锁
        while (true) {
            String value = UUID.randomUUID().toString().replaceAll("-", "");
            lockFlag = redisTemplate.opsForValue().setIfAbsent(lockName, value, lockTimeOutSecond, TimeUnit.SECONDS);

            if (lockFlag) {
                //如果获取到锁，则跳出自旋
                return value;
            }

            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - startTimeMillis > (retryMaxTimeSecond)) {
                //超过阻塞最大时间,锁定失败
                return null;
            }

            //如果没有获取到锁，sleep1秒再尝试
            Thread.sleep(retryMilliSecond);
        }
    }

    /**
     * 释放锁
     *
     * @param lockName   锁的key
     * @param identifier 释放锁的标识 --- 保证谁加的锁，谁才能释放锁
     * @return true:释放成功,false:释放失败
     */
    public boolean releaselock(String lockName, String identifier) {
        if (StringUtils.isEmpty(lockName) || StringUtils.isEmpty(identifier)) {
            return false;
        }
        Object lockId = redisTemplate.opsForValue().get(lockName);
        String lockIdentifier = lockId == null ? "" : lockId.toString();
        if (identifier.equals(lockIdentifier)) {
            redisTemplate.delete(lockName);
            return true;
        }
        return false;
    }
}
