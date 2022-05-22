**8.（必做）**基于 Redis 封装分布式数据操作：

- 在 Java 中实现一个简单的分布式锁；
- 在 Java 中实现一个分布式计数器，模拟减库存。

1. 定义DistrubuteLock接口，有三个方法：

   - acquire：获取锁

   - tryAcquire：尝试获取锁

   - release：释放锁

2. JedisLock实现DistrubuteLock接口，实现三个方法

3. UseJedis使用JedisLock来模拟减count



wrk测试缓存各个过程

1. 纯ORM

   ```c
   ❯ wrk -c200 -d30s  http://localhost:8080/user/list
   Running 30s test @ http://localhost:8080/user/list
     2 threads and 200 connections
     Thread Stats   Avg      Stdev     Max   +/- Stdev
       Latency    27.35ms   26.17ms 327.78ms   81.26%
       Req/Sec     4.23k     1.84k   11.00k    66.61%
     251765 requests in 30.08s, 55.27MB read
     Socket errors: connect 0, read 127, write 2, timeout 0
   Requests/sec:   8368.52
   Transfer/sec:      1.84MB
   ```

2. 开启spring cache

   ```c
   ❯ wrk -c200 -d30s  http://localhost:8080/user/list
   Running 30s test @ http://localhost:8080/user/list
     2 threads and 200 connections
     Thread Stats   Avg      Stdev     Max   +/- Stdev
       Latency    37.01ms   95.21ms   1.04s    97.16%
       Req/Sec     4.51k     0.97k    7.13k    78.83%
     261637 requests in 30.10s, 57.44MB read
     Socket errors: connect 0, read 15, write 0, timeout 0
   Requests/sec:   8693.33
   Transfer/sec:      1.91MB
   ```

   