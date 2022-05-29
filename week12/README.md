**1.（必做）**配置 redis 的主从复制，sentinel 高可用，Cluster 集群。

- mac安装redis

  ```
  // 安装
  brew install redis
  // 启动，看看是否安装成功
  redis-server
  ```

## 配置主从复制

- 修改配置文件

  ```
  # 配置端口
  port 6379
  # pid文件
  pidfile "/var/run/redis_6379.pid"
  # 配6379端口的redis文件夹路径
  dir "/Users/zhuhongji/logs/redis0"
  ```

  然后注释掉最底下上一次启动自动生成的配置部分。

  6380配置文件同6379

- 启动redis

  ```
  redis-server -p 6379
  
  redis-server -p 6380
  ```

  这样就开启了两个redis，当前状态下互不相干，角色都是master

- 将6380设置为6379的从库

  在6380的命令行下，输入

  ```
  127.0.0.1:6380> slaveof 127.0.0.1 6379
  OK
  ```

  从库无法写入了

  ```
  127.0.0.1:6380> set aa 123
  (error) READONLY You can't write against a read only replica.
  ```

  在主库6379写入，看6380变化

  ```
  127.0.0.1:6379> set aa 123
  OK
  ```

  ```
  127.0.0.1:6380> keys *
  1) "port"
  2) "aa"
  127.0.0.1:6380> get aa
  "123"
  ```



## 配置 sentinel 高可用

- 配置sentinel配置文件

  ```
  # 监控master节点是哪个，最小的集群是多大（这里是2）
  sentinel monitor mymaster 127.0.0.1 6379 2
  # 监听状态的时间 60s
  sentinel down-after-milliseconds mymaster 60000
  # 允许failover时间 180s
  sentinel failover-timeout mymaster 180000
  # 选举主从之后，从库从主库同步数据，同时允许同步的节点数目
  sentinel parallel-syncs mymaster 1
  ```

- 启动sentinel

  ```
  redis-sentinel sentinel.conf
  # or
  redis-server redis.conf --sentinel
  ```

- 如果主库6379宕机，6380过了配置的60s之后，会变成主库

- 6379再次启动，变成6380的从库



## 配置Cluster集群

- 修改配置文件

  配置6个节点，port从6379到6384，分别设置好配置文件，然后启动redis

  ```
  cluster-enabled yes # 是否以集群的形式启动
  cluster-config-file redis-nodes.conf
  cluster-require-full-coverage no    # 集群中是否16384个槽都可用或所有master节点都没有问题才对外提供服务，保证集群的完整性 
  cluster-node-timeout 15000    # 各个节点相互发送消息的频率，单位为毫秒。某节点发现与其他节点最后通信时间超过cluster-node-timeout/2时会发送ping命令，同时带上slots槽数组（2KB）和整个集群1/10的状态数据（10个节点的状态数据约1KB），该参数也会影响故障转移时间
  ```

- 启动集群

  ```
  redis-cli --cluster create 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 --cluster-replicas 1
  ```

- reids会将6个节点中的三个设置为master，另三个分别作为它们的slave

  ```
  Master[0] -> Slots 0 - 5460
  Master[1] -> Slots 5461 - 10922
  Master[2] -> Slots 10923 - 16383
  Adding replica 127.0.0.1:6383 to 127.0.0.1:6379
  Adding replica 127.0.0.1:6384 to 127.0.0.1:6380
  Adding replica 127.0.0.1:6382 to 127.0.0.1:6381
  ```

  





**6.（必做）**搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github。

- 创建连接与会话

  ```java
  // 创建连接和会话
  ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
  ActiveMQConnection conn = (ActiveMQConnection) factory.createConnection();
  conn.start();
  Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
  ```

- 根据使用queue还是topic，创建Destination

  ```java
  Destination destination = new ActiveMQTopic("test.topic");
  Destination destination = new ActiveMQQueue("test.queue");
  ```

- 由destination创建消费者，并绑定监听器，监听器对MQ获取的信息进行处理

  ```java
  // 创建消费者
  MessageConsumer consumer = session.createConsumer(destination);
  final AtomicInteger count = new AtomicInteger(0);
  MessageListener listener = new MessageListener() {
    public void onMessage(Message message) {
      try {
        // 打印所有的消息内容
        System.out.println(count.incrementAndGet() + " => receive from " + destination.toString() + ": " + message);
      } catch (Exception e) {
        e.printStackTrace(); // 不要吞任何这里的异常，
      }
    }
  };
  // 绑定消息监听器
  consumer.setMessageListener(listener);
  ```

- 由destination创建生产者，向MQ里塞消息，并等待20s

  ```java
   // 创建生产者，生产100个消息
  MessageProducer producer = session.createProducer(destination);
  int index = 0;
  while (index++ < 100) {
    TextMessage message = session.createTextMessage(index + " message.");
    producer.send(message);
  }
  Thread.sleep(20000);
  session.close();
  conn.close();
  ```

  