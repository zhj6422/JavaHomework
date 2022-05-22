package com.zhj6422.redislock;

import javax.jws.soap.SOAPBinding.Use;
import redis.clients.jedis.Jedis;

public class UseJedis {

  private static int count = 10;
  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(UseJedis::decCount);
    Thread thread2 = new Thread(UseJedis::decCount);
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    Thread thread3 = new Thread(UseJedis::decCount);
    thread3.start();
    thread3.join();
  }

  public static void decCount(){
    Jedis jedis = new Jedis("localhost");
    DistrubuteLock lock = new JedisLock(jedis, "jedis", 30000);
    lock.acquire();
    try{
      count--;
      System.out.println("数量减一，count：" + count);
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }finally{
      lock.release();
    }
  }

}
