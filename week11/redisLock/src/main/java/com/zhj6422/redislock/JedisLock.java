package com.zhj6422.redislock;

import java.util.Collections;
import java.util.UUID;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class JedisLock implements DistrubuteLock{

  private static final String LOCK_SUCCESS = "OK";

  private static final Long RELEASE_SUCCESS = 1L;

  private static final int DEFAULT_EXPIRE_TIME = 30000;

  private Jedis client;

  private String key;

  private int expireTime;

  private String lockValue;



  /*
   * client：Jedis客户端
   * key：分布式锁的key
   * expireTime：超时时间
   * */
  public JedisLock(Jedis client, String key, int expireTime) {
    this.client = client;
    this.key = key;
    this.expireTime = expireTime <= 0 ? DEFAULT_EXPIRE_TIME : expireTime;
  }

  @Override
  public void acquire() {
    // 随机生成id作为锁的value
    lockValue = UUID.randomUUID().toString();
    SetParams params = new SetParams();
    params.nx().px(expireTime);
    String result = client.set(key, lockValue, params);
    while (!LOCK_SUCCESS.equals(result)) {
      try {
        Thread.sleep(10);
      }  catch (InterruptedException e) {
        e.printStackTrace();
      }
      result = client.set(key, lockValue, params);
    }
  }

  @Override
  public boolean tryAcquire() {
    lockValue = UUID.randomUUID().toString();
    SetParams params = new SetParams();
    params.nx().px(expireTime);
    String result = client.set(key, lockValue, params);
    return LOCK_SUCCESS.equals(result);
  }

  @Override
  public boolean release() {
    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    Object result = client.eval(script, Collections.singletonList(key), Collections.singletonList(lockValue));
    return RELEASE_SUCCESS.equals(result);
  }


}
