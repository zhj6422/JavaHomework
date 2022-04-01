# 第五周作业
## 作业编号2
题目：写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。

作业代码：[https://github.com/zhj6422/JavaHomework/tree/main/week05/code](https://github.com/zhj6422/JavaHomework/tree/main/week05/code)

先构建各个实体类Student、Klass、School

1. 通过xml配置：

   新建一个applicationContext.xml文件

   xml文件中配置bean：

   示例：

   ```xml
   <bean id="student123"
       class="com.zhj6422.beanCode.model.Student">
       <property name="id" value="123" />
       <property name="name" value="KK123" />
     </bean>
   ```

2. 通过注解配置

   在类上面通过@Component、@Service、@Controller、@Repository注解，将类自动装配为Bean

   代码中示例：Food类

3. Java Config配置

   通过新建一个配置类，给这个类加上@Configuration注解，然后在类里面通过@Bean自动装配bean

   代码中示例：FoodConfig类



## 作业编号8

题目：给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

作业代码：[https://github.com/zhj6422/JavaHomework/tree/main/week05/school-spring-boot-starter](https://github.com/zhj6422/JavaHomework/tree/main/week05/school-spring-boot-starter)

1. 创建Maven项目，引入依赖

   ```xml
   <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-autoconfigure</artifactId>
         <version>2.6.1</version>
       </dependency>
       <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-logging</artifactId>
         <version>2.6.1</version>
       </dependency>
       <dependency>
         <groupId>org.projectlombok</groupId>
         <artifactId>lombok</artifactId>
         <version>1.18.22</version>
       </dependency>
       <!--将被@ConfigurationProperties注解的类的属性注入到元数据-->
       <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-configuration-processor</artifactId>
         <version>2.6.1</version>
         <optional>true</optional>
         <scope>provided</scope>
       </dependency>
       <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-test</artifactId>
         <version>2.6.5</version>
         <scope>test</scope>
       </dependency>
       <dependency>
         <groupId>org.junit.jupiter</groupId>
         <artifactId>junit-jupiter-api</artifactId>
         <version>5.8.2</version>
         <scope>test</scope>
       </dependency>
       <dependency>
         <groupId>org.springframework</groupId>
         <artifactId>spring-test</artifactId>
         <version>5.3.17</version>
         <scope>test</scope>
       </dependency>
   ```

2. 构建Student、Klass、School类，这些是starter封装的内容

3. 构建[SchoolProperties类](https://github.com/zhj6422/JavaHomework/blob/main/week05/school-spring-boot-starter/src/main/java/com/zhj6422/starter/SchoolProperties.java)，这个类里面的内容是可以让调用者通过配置文件修改的内容

   定义一些属性，需要getter、setter，代码中通过lombok的@Data注解完成

   添加注解@ConfigurationProperties(prefix="school")，表示匹配调用者配置文件中的前缀是school

4. 构建[SchoolAutoConfiguration类](https://github.com/zhj6422/JavaHomework/blob/main/week05/school-spring-boot-starter/src/main/java/com/zhj6422/starter/SchoolAutoConfiguration.java)

   这个是自动配置的入口，在这里我们可以拿到用户配置的属性内容，然后创建出对应的bean

   需要配置的注解有：

   - @Configuration 表示这是一个配置类
   - @EnableConfigurationProperties(SchoolProperties.class) 将第三步的类加载进来，给这个配置类调用
   - @ConditionalOnClass(School.class) 表示School这个类需要先存在，才能配置
   - @ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true") 表示需要在配置文件中添加enabled属性，这个属性值为true才能配置

   配置类自动注入SchoolProperties，通过SchoolProperties构建一个School的Bean给用户

5. 在resources的META-INF路径下添加[spring.factories](https://github.com/zhj6422/JavaHomework/blob/main/week05/school-spring-boot-starter/src/main/resources/META-INF/spring.factories)和[additional-spring-configuration-metadata.json文件](https://github.com/zhj6422/JavaHomework/blob/main/week05/school-spring-boot-starter/src/main/resources/META-INF/additional-spring-configuration-metadata.json)

   spring.factories：指定需要自动注册到 Spring 容器的 bean 和一些配置信息

   ```xml
   org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.zhj6422.starter.SchoolAutoConfiguration
   ```

   additional-spring-configuration-metadata.json：配置一些属性的默认值

   ```json
   {
     "properties": [
       {
         "name": "school.studentIds",
         "type": "java.lang.String",
         "description": "Description for school.studentIds.",
         "defaultValue": "1,2"
       },
       {
         "name": "school.studentNames",
         "type": "java.lang.String",
         "description": "Description for school.studentNames.",
         "defaultValue": "student1,student2"
       },
       {
         "name": "school.klassIds",
         "type": "java.lang.String",
         "description": "Description for school.myClassIds.",
         "defaultValue": "1,2"
       },
       {
         "name": "school.klassNames",
         "type": "java.lang.String",
         "description": "Description for school.myClassNames.",
         "defaultValue": "class1, class2"
       },
       {
         "name": "school.studentOfClass[0].klassId",
         "type": "java.lang.String",
         "description": "Description for school.studentOfClass[0].myClassId.",
         "defaultValue": "1"
       },
       {
         "name": "school.studentOfClass[1].studentId",
         "type": "java.lang.String",
         "description": "Description for school.studentOfClass[1].studentIds.",
         "defaultValue": "2"
       },
       {
         "name": "school.studentOfClass[1].klassId",
         "type": "java.lang.String",
         "description": "Description for school.studentOfClass[1].myClassId.",
         "defaultValue": "2"
       },
       {
         "name": "school.studentOfClass[0].studentId",
         "type": "java.lang.String",
         "description": "Description for school.studentOfClass[0].studentIds.",
         "defaultValue": "1"
       }
     ]
   }
   ```

6. 测试：

   在application.properties中写入一些参数的配置

   创建测试类[SchoolStarterTest](https://github.com/zhj6422/JavaHomework/blob/main/week05/school-spring-boot-starter/src/test/java/com/zhj6422/starter/SchoolStarterTest.java)，注入SchoolAutoConfiguration类

   让Springboot创建一个School，看是否能够执行到SchoolAutoConfiguration中的输出内容

   修改application.properties的值，查看变化









## 作业编号10

题目：研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
1）使用 JDBC 原生接口，实现数据库的增删改查操作。
2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
3）配置 Hikari 连接池，改进上述操作。提交代码到 GitHub。

作业代码：[https://github.com/zhj6422/JavaHomework/tree/main/week05/databaseDemo](https://github.com/zhj6422/JavaHomework/tree/main/week05/databaseDemo)

### JDBC原生接口

代码：[https://github.com/zhj6422/JavaHomework/blob/main/week05/databaseDemo/src/main/java/com/zhj6422/databaseDemo/JDBCMethod.java](https://github.com/zhj6422/JavaHomework/blob/main/week05/databaseDemo/src/main/java/com/zhj6422/databaseDemo/JDBCMethod.java)

- 使用DriverManager获取连接Connection
- 使用连接创建Statement
- Statement执行sql语句获取结果ResultSet
- 将结果输出



### PrepareStatement

代码：[https://github.com/zhj6422/JavaHomework/blob/main/week05/databaseDemo/src/main/java/com/zhj6422/databaseDemo/PrepareStatementMethod.java](https://github.com/zhj6422/JavaHomework/blob/main/week05/databaseDemo/src/main/java/com/zhj6422/databaseDemo/PrepareStatementMethod.java)

- 使用DriverManager获取连接Connection
- 先定义好sql语句
- 用连接和sql语句创建prepareStatement
- 对prepareStatement传入参数，并加入命令batch中
- prepareStatement执行batch



### Hikari

代码：[https://github.com/zhj6422/JavaHomework/blob/main/week05/databaseDemo/src/main/java/com/zhj6422/databaseDemo/HikariMethod.java](https://github.com/zhj6422/JavaHomework/blob/main/week05/databaseDemo/src/main/java/com/zhj6422/databaseDemo/HikariMethod.java)

[https://github.com/zhj6422/JavaHomework/blob/main/week05/databaseDemo/src/main/java/com/zhj6422/databaseDemo/HikariMethodWithPrepareStatement.java](https://github.com/zhj6422/JavaHomework/blob/main/week05/databaseDemo/src/main/java/com/zhj6422/databaseDemo/HikariMethodWithPrepareStatement.java)

- pom文件引入依赖

  ```xml
  <dependency>
  			<groupId>com.zaxxer</groupId>
  			<artifactId>HikariCP</artifactId>
  			<version>4.0.3</version>
  		</dependency>
  ```

- 在resources中新增一个配置文件hikari.properties，配置url，driver以及连接数据库相关的信息

- 用HikariConfig拿到hikari.properties的配置，使用HikariDataSource通过配置信息建立连接

- 接下去的操作就跟之前的一样了

  



















