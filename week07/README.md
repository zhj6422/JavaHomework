**2.（必做）**按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率

- 关闭自动提交，等所有insert完成之后再提交

  ```sql
  DROP PROCEDURE IF EXISTS orders_initData;
  DELIMITER $
  CREATE PROCEDURE orders_initData()
  BEGIN
      DECLARE i INT DEFAULT 1;
      set autocommit=0;
      WHILE i<=1000000 DO
              insert into `order` (`user_id` , `commodities` , `order_price` , `status`, `create_time` , `update_time` )
              VALUES (CEILING(rand()*100), '{"书包": "1"}', 100, 0, unix_timestamp(now()) , unix_timestamp(now()));
          SET i = i+1;
      END WHILE;
      commit;
  END $
  CALL orders_initData();
  ```

  完成时间为44.146s

- 不关闭自动提交

  ```sql
  DROP PROCEDURE IF EXISTS orders_initData_with_autocommit;
  DELIMITER $
  CREATE PROCEDURE orders_initData_with_autocommit()
  BEGIN
      DECLARE i INT DEFAULT 1;
      WHILE i<=1000000 DO
              insert into `order` (`user_id` , `commodities` , `order_price` , `status`, `create_time` , `update_time` )
              VALUES (CEILING(rand()*100), '{"书包": "1"}', 100, 0, unix_timestamp(now()) , unix_timestamp(now()));
          SET i = i+1;
      END WHILE;
  END $
  CALL orders_initData_with_autocommit();
  ```

  时间：已经超出20000s（还没结束）





**9.（必做）**读写分离 - 动态切换数据源版本 1.0

使用AbstractRoutingDataSource来根据自定义的规则切换数据源

1. 创建[Enum](./DSDemo/src/main/java/com/zhj6422/dsdemo/config/DataSourceEnum.java)，用于标志选择的数据源
2. 创建[ContextHolder](./DSDemo/src/main/java/com/zhj6422/dsdemo/config/DatabaseContextHolder.java)，用于切换数据源；创建[RoutingDataSource](./DSDemo/src/main/java/com/zhj6422/dsdemo/config/RoutingDataSource.java)类继承自AbstractRoutingDataSource；配置[DataSourceConfig](./DSDemo/src/main/java/com/zhj6422/dsdemo/config/DataSourceConfig.java)和[MybatisConfig](./DSDemo/src/main/java/com/zhj6422/dsdemo/config/MybatisConfig.java)
3. 创建[注解](./DSDemo/src/main/java/com/zhj6422/dsdemo/annotation)以及配置[AOP](./DSDemo/src/main/java/com/zhj6422/dsdemo/aspect)
4. 创建[entity](./DSDemo/src/main/java/com/zhj6422/dsdemo/entity)、[dao](./DSDemo/src/main/java/com/zhj6422/dsdemo/dao/)、[service](./DSDemo/src/main/java/com/zhj6422/dsdemo/service/)
5. [测试](.\DSDemo\src\test\java\com\zhj6422\dsdemo)
