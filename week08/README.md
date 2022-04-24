**2.（必做）**设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

使用Shardingsphere-Proxy来实现分库分表

1. 创建数据库：

   ```sql
   ## 数据库
   create schema demo_ds_0;
   create schema demo_ds_1;
   
   ## 数据库1创建order表
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_0 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_1 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_2 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_3 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_4 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_5 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_6 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_7 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_8 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_9 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_10 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_11 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_12 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_13 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_14 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_15 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   ## 数据库2创建order表
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_0 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_1 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_2 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_3 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_4 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_5 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_6 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_7 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_8 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_9 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_10 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_11 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_12 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_13 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_14 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_15 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   
   ## 数据库1创建order_item表
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_0 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_1 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_2 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_3 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_4 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_5 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_6 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_7 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_8 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_9 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_10 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_11 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_12 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_13 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_14 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_0.t_order_item_15 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   
   ## 数据库2创建order_item表
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_0 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_1 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_2 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_3 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_4 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_5 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_6 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_7 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_8 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_9 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_10 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_11 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_12 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_13 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_14 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   CREATE TABLE IF NOT EXISTS demo_ds_1.t_order_item_15 (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_item_id));
   ```

2. 下载Shardingsphere-Proxy，并下载mysql-connector的jar包放入shardingsphere-proxy的lib目录下

3. 修改Shardingsphere-Proxy的配置：

   server.yaml

   ```yaml
   rules:
     - !AUTHORITY
       users:
         - root@127.0.0.1:root #配置用户名、ip以及密码
       provider:
         type: ALL_PRIVILEGES_PERMITTED
   props: 
     sql-show: true
     max-connections-size-per-query: 1
     acceptor-size: 16  # The default value is available processors count * 2.
     executor-size: 16  # Infinite by default.
     proxy-frontend-flush-threshold: 128  # The default value is 128.
       # LOCAL: Proxy will run with LOCAL transaction.
       # XA: Proxy will run with XA transaction.
       # BASE: Proxy will run with B.A.S.E transaction.
     proxy-transaction-type: LOCAL
     proxy-opentracing-enabled: false
     proxy-hint-enabled: false
     query-with-cipher-column: false
     check-table-metadata-enabled: false
   ```

   config-sharding.yaml

   ```yaml
   schemaName: sharding_db
   
   dataSources:
    ds_0:
      url: jdbc:mysql://127.0.0.1:3306/demo_ds_0?serverTimezone=UTC&useSSL=false
      username: root
      password: 123456
      connectionTimeoutMilliseconds: 30000
      idleTimeoutMilliseconds: 60000
      maxLifetimeMilliseconds: 1800000
      maxPoolSize: 5
      minPoolSize: 1
    ds_1:
      url: jdbc:mysql://127.0.0.1:3306/demo_ds_1?serverTimezone=UTC&useSSL=false
      username: root
      password: 123456
      connectionTimeoutMilliseconds: 30000
      idleTimeoutMilliseconds: 60000
      maxLifetimeMilliseconds: 1800000
      maxPoolSize: 5
      minPoolSize: 1
   
   rules:
   - !SHARDING
    tables:
      t_order:
        actualDataNodes: ds_${0..1}.t_order_${0..15}
        tableStrategy:
          standard:
            shardingColumn: order_id
            shardingAlgorithmName: t_order_inline
        keyGenerateStrategy:
          column: order_id
          keyGeneratorName: snowflake
      t_order_item:
        actualDataNodes: ds_${0..1}.t_order_item_${0..15}
        tableStrategy:
          standard:
            shardingColumn: order_id
            shardingAlgorithmName: t_order_item_inline
        keyGenerateStrategy:
          column: order_item_id
          keyGeneratorName: snowflake
    bindingTables:
      - t_order,t_order_item
    defaultDatabaseStrategy:
      standard:
        shardingColumn: user_id
        shardingAlgorithmName: database_inline
    defaultTableStrategy:
      none:
    
    shardingAlgorithms:
      database_inline:
        type: INLINE
        props:
          algorithm-expression: ds_${user_id % 2}
      t_order_inline:
        type: INLINE
        props:
          algorithm-expression: t_order_${order_id % 16}
      t_order_item_inline:
        type: INLINE
        props:
          algorithm-expression: t_order_item_${order_id % 16}
    
    keyGenerators:
      snowflake:
        type: SNOWFLAKE
        props:
          worker-id: 123
   ```

4. 启动Shardingsphere-Proxy

   ```
   # 到bin目录下 后面可以指定端口号
   ./start.bat 13306
   ```

5. 启动Mysql，尝试连接Shardingsphere-Proxy

   ```
   mysql -uroot -h127.0.0.1 -P13306 -p
   ```

   能够成功连上Shardingsphere-Proxy

6. 尝试对数据库操作

   ```sql
   mysql> select * from t_order;
   Empty set (0.10 sec)
   
   mysql> insert into t_order (user_id, status) values (1, "OK");
   Query OK, 1 row affected (0.37 sec)
   
   mysql> select * from t_order;
   +--------------------+---------+--------+
   | order_id           | user_id | status |
   +--------------------+---------+--------+
   | 725016357606785024 |       1 | OK     |
   +--------------------+---------+--------+
   1 row in set (0.01 sec)
   
   
   mysql> insert into t_order (user_id, status) values (2, "OK"), (3, "NG"), (4, "OK"), (5, "OK");
   Query OK, 4 rows affected (0.17 sec)
   
   mysql> select * from t_order;
   +--------------------+---------+--------+
   | order_id           | user_id | status |
   +--------------------+---------+--------+
   | 725016744929787905 |       2 | OK     |
   | 725016744929787907 |       4 | OK     |
   | 725016357606785024 |       1 | OK     |
   | 725016744929787906 |       3 | NG     |
   | 725016744929787908 |       5 | OK     |
   +--------------------+---------+--------+
   5 rows in set (0.01 sec)
   ```

   在真实数据库中查看：

   ```sql
   mysql> use demo_ds_0;
   Database changed
   mysql> show tables;
   +---------------------+
   | Tables_in_demo_ds_0 |
   +---------------------+
   | t_order_0           |
   | t_order_1           |
   | t_order_10          |
   | t_order_11          |
   | t_order_12          |
   | t_order_13          |
   | t_order_14          |
   | t_order_15          |
   | t_order_2           |
   | t_order_3           |
   | t_order_4           |
   | t_order_5           |
   | t_order_6           |
   | t_order_7           |
   | t_order_8           |
   | t_order_9           |
   | t_order_item_0      |
   | t_order_item_1      |
   | t_order_item_10     |
   | t_order_item_11     |
   | t_order_item_12     |
   | t_order_item_13     |
   | t_order_item_14     |
   | t_order_item_15     |
   | t_order_item_2      |
   | t_order_item_3      |
   | t_order_item_4      |
   | t_order_item_5      |
   | t_order_item_6      |
   | t_order_item_7      |
   | t_order_item_8      |
   | t_order_item_9      |
   +---------------------+
   32 rows in set (0.00 sec)
   
   mysql> select * from t_order_1;
   +--------------------+---------+--------+
   | order_id           | user_id | status |
   +--------------------+---------+--------+
   | 725016744929787905 |       2 | OK     |
   +--------------------+---------+--------+
   1 row in set (0.00 sec)
   
   mysql> use  demo_ds_1;
   Database changed
   mysql> select * from t_order_0;
   +--------------------+---------+--------+
   | order_id           | user_id | status |
   +--------------------+---------+--------+
   | 725016357606785024 |       1 | OK     |
   +--------------------+---------+--------+
   1 row in set (0.00 sec)
   ```

   

**6.（必做）**基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。

使用ShardingSphere实现分布式事务

1. 创建数据库

   ```sql
   ## 数据库1
   create schema ds_0;
   CREATE TABLE IF NOT EXISTS ds_0.t_order_0 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS ds_0.t_order_1 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   
   ## 数据库2
   create schema ds_1;
   CREATE TABLE IF NOT EXISTS ds_1.t_order_0 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   CREATE TABLE IF NOT EXISTS ds_1.t_order_1 (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
   
   ```

   

2. pom文件中引入依赖

   ```xml
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId> 
       <version>8.0.14</version>
   </dependency>
   
   <dependency>
       <groupId>org.apache.shardingsphere</groupId>
       <artifactId>shardingsphere-jdbc-core</artifactId>
       <version>5.0.0-alpha</version>
   </dependency>
   
   <dependency>
       <groupId>org.apache.shardingsphere</groupId>
       <artifactId>shardingsphere-transaction-xa-core</artifactId>
       <version>5.0.0-alpha</version>
   </dependency>
   
   <dependency>
       <groupId>com.zaxxer</groupId>
       <artifactId>HikariCP</artifactId>
       <version>2.2.5</version>
   </dependency>
   ```

3. yml中增加配置：

   ```yaml
   dataSources:
     ds_0: !!com.zaxxer.hikari.HikariDataSource
       driverClassName: com.mysql.cj.jdbc.Driver
       jdbcUrl: jdbc:mysql://localhost:3306/ds_0?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true
       username: root
       password: 123456
       autoCommit: false
     ds_1: !!com.zaxxer.hikari.HikariDataSource
       driverClassName: com.mysql.cj.jdbc.Driver
       jdbcUrl: jdbc:mysql://localhost:3316/ds_1?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true
       username: root
       password:
       autoCommit: false
   
   rules:
     - !SHARDING
       tables:
         t_order:
           actualDataNodes: ds_${0..1}.t_order_${0..1}
           databaseStrategy:
             standard:
               shardingColumn: user_id
               shardingAlgorithmName: database_inline
           tableStrategy:
             standard:
               shardingColumn: order_id
               shardingAlgorithmName: t_order_inline
       bindingTables:
         - t_order
   
       shardingAlgorithms:
         database_inline:
           type: INLINE
           props:
             algorithm-expression: ds_${user_id % 2}
         t_order_inline:
           type: INLINE
           props:
             algorithm-expression: t_order_${order_id % 2}
   
   props:
     sql-show: true
   ```

4. 代码：

   ```java
   package com.zhj6422.xademo;
   
   import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
   import org.apache.shardingsphere.transaction.core.TransactionType;
   import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   
   import javax.sql.DataSource;
   import java.io.File;
   import java.io.IOException;
   import java.sql.Connection;
   import java.sql.PreparedStatement;
   import java.sql.SQLException;
   import java.sql.Statement;
   
   @SpringBootApplication
   public class XademoApplication {
   
       public static void main(String[] args) throws IOException, SQLException {
           DataSource dataSource = getShardingDatasource();
           cleanupData(dataSource);
   
           TransactionTypeHolder.set(TransactionType.LOCAL);
   
           Connection conn = dataSource.getConnection();
           String sql = "insert into t_order (order_id, user_id, status) VALUES (?, ?, ?);";
   
           System.out.println("第一次XA");
           try (PreparedStatement statement = conn.prepareStatement(sql)) {
               conn.setAutoCommit(false);
               for (int i = 1; i < 11; i++) {
                   statement.setLong(1, i);
                   statement.setLong(2, i);
                   statement.setString(3, "OK");
                   statement.executeUpdate();
               }
               conn.commit();
           }
   
           System.out.println("第一次XA成功！！！");
   
           // 设置id+5，如果设置XA事务成功，则所有的数据都不会插入
           // 设置id+5，如果设置XA事务不成功，则id大于10的数据就会插入到数据库
           // 程序运行完毕后，查看数据库，没有id大于10的数据，所以XA设置成功
           System.out.println("第二次XA");
           try (PreparedStatement statement = conn.prepareStatement(sql)) {
               conn.setAutoCommit(false);
               for (int i = 1; i < 11; i++) {
                   statement.setLong(1, i+5);
                   statement.setLong(2, i+5);
                   statement.setString(3, "NG");
                   statement.executeUpdate();
               }
               conn.commit();
           } catch (Exception e) {
               System.out.println("第二次XA失败！！！回滚");
               conn.rollback();
           } finally {
               conn.close();
           }
           System.out.println("第二次XA成功");
       }
   
       private static void cleanupData(DataSource dataSource) {
           System.out.println("删除数据");
           try (Connection conn = dataSource.getConnection(); Statement statement = conn.createStatement()) {
               statement.execute("delete from t_order;");
               conn.commit();
           } catch (SQLException e) {
               e.printStackTrace();
           }
           System.out.println("删除成功");
       }
   
       static private DataSource getShardingDatasource() throws IOException, SQLException {
           String fileName = "src/main/resources/application.yml";
           File yamlFile = new File(fileName);
           return YamlShardingSphereDataSourceFactory.createDataSource(yamlFile);
       }
   }
   ```

5. 执行结果

   数据库1：

   ```sql
   mysql> use ds_0;
   Database changed
   mysql> show tables;
   +----------------+
   | Tables_in_ds_0 |
   +----------------+
   | t_order_0      |
   | t_order_1      |
   +----------------+
   2 rows in set (0.00 sec)
   
   mysql> select * from t_order_0;
   +----------+---------+--------+
   | order_id | user_id | status |
   +----------+---------+--------+
   |        2 |       2 | OK     |
   |        4 |       4 | OK     |
   |        6 |       6 | OK     |
   |        8 |       8 | OK     |
   |       10 |      10 | OK     |
   +----------+---------+--------+
   5 rows in set (0.00 sec)
   ```

   数据库2：

   ```sql
   mysql> use ds_1;
   Database changed
   mysql> show tables;
   +----------------+
   | Tables_in_ds_1 |
   +----------------+
   | t_order_0      |
   | t_order_1      |
   +----------------+
   2 rows in set (0.00 sec)
   
   mysql> select * from t_order_1;
   +----------+---------+--------+
   | order_id | user_id | status |
   +----------+---------+--------+
   |        1 |       1 | OK     |
   |        3 |       3 | OK     |
   |        5 |       5 | OK     |
   |        7 |       7 | OK     |
   |        9 |       9 | OK     |
   +----------+---------+--------+
   5 rows in set (0.00 sec)
   ```

