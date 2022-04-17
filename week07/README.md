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

  

