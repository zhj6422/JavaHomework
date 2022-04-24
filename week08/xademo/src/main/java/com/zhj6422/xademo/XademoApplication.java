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
