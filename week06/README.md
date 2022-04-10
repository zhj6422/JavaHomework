# 第六周作业

这周事情比较多，没有完成其他选做作业，找时间回顾并补上



**6.（必做）**基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。

- 用户表：id、用户名、密码、电话、账户余额、创建时间、修改时间
- 商品表：id、商品名、价格、库存量、创建时间、修改时间
- 订单表：id、下单用户id、订单涉及商品、订单总金额、订单状态、创建时间、修改时间

```sql
CREATE DATABASE `e_commerce_db`;

USE `e_commerce_db`;

CREATE TABLE IF NOT EXISTS `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(16) NOT NULL,
    `password` varchar(16) NOT NULL,
    `phone` varchar(16) NOT NULL,
    `money` int NOT NULL,
    `create_time` int NOT NULL,
    `update_time` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `commodity` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(16) NOT NULL,
    `price` int NOT NULL,
    `stock` int NOT NULL,
    `create_time` int NOT NULL,
    `update_time` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `order` (
    `id` int NOT NULL AUTO_INCREMENT,
    `user_id` int NOT NULL,
    `commodities` varchar(21812) NOT NULL,
    `order_price` int NOT NULL,
    `status` int NOT NULL,
    `create_time` int NOT NULL,
    `update_time` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```



**7.（选做）**尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的 SQL 测试简单的增删改查。

- MySQL安装

  [https://blog.csdn.net/zhangbeizhen18/article/details/116035930](https://blog.csdn.net/zhangbeizhen18/article/details/116035930)

- 增加数据

  ```sql
  INSERT INTO `user`(name, password, phone, money, create_time, update_time)  VALUES ("zhj6422", "zhj6422", "12010302930", 9999, unix_timestamp(now()) , unix_timestamp(now()));
  
  INSERT INTO `commodity` (name, price, stock, create_time, update_time) VALUES ("书包", 100, 900, unix_timestamp(now()) , unix_timestamp(now()));
  
  INSERT INTO `order`(`user_id`, commodities, order_price, status, create_time, update_time) VALUES (1, "书包", 100, 1, unix_timestamp(now()) , unix_timestamp(now()));
  ```

- 删除数据

  ```sql
  DELETE FROM `order` WHERE id = 1;
  ```

- 修改数据

  ```sql
  UPDATE `order` SET order_price = 200 WHERE id = 2;
  ```

- 查询数据

  ```sql
  SELECT * FROM `order`;
  ```

  