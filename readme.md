# Spring Cloud

### Eureka Server细节说明

##### 1. application.yml

eureka server分为single模式和ha模式，对应的配置参数也有区别

###### single模式

```properties
##放弃自我发布
eureka.client.register-with-eureka=false
##设置不获取注册服务的信息
eureka.client.fetch-registry=false 
```

###### ha模式

```properties
##自我注册发布开启，因为默认开启，可以不用管
eureka.client.register-with-eureka=true

##自我保护机制，注册服务器会维护和服务之间的心跳，当超过一定的阈值时设置当前服务是否需要保护起来，如果是测试环境建议为false,生成环境建议为true开启保护，防止网络波动。如果设置关闭保护那就需要设置对应的心跳阈值来控制
##eureka.server.renewal-percent-threshold=0.5
eureka.server.enable-self-preservation=false

##将此eureka服务注册的地址，可以注册多个（会自动过滤自己）
##PS: 注意发布地址后面有/eureka，不是服务启动地址
eureka.client.serviceUrl.defaultZone=http://eurekanode2:2222/eureka/,http://eurekanode1:2223/eureka/ 
```

###### ip地址

```properties
##生产环境一般用IP地址来定义服务
eureka.instance.hostname=127.0.0.1
eureka.instance.prefer-ip-address= ture
```

![1585818317858](https://github.com/TrimGHU/reborn/blob/master/1585818317858.png)



##### 2. 加入权限

加入security的依赖

```xml
<dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-security</artifactId>
</dependency>
```

加入yml配置即可

```properties
##是否启用eureka认证security:
security:
	basic:
  		enabled: true
	user:
  		name: XXX
  		password: XXX
```



##### 3. MAVEN打包可运行JAR包（题外话）

IntelliJ IDE默认maven打包是无法运行的，打出来的jar运行也是提示无主清单属性的

需要在pom里配置main class以及加入引用jar配置，maven install出来的jar才可以执行

```
<build>
        <finalName>eureka-server</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                    <mainClass>com.XXX.EurekaServerApplication</mainClass>
                    <layout>ZIP</layout>
                </configuration>
                <executions>
                    <execution>
                        <configuration>
                            <classifier>exec</classifier>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

### Eureka Client细节说明

##### 1. application.yml

###### 正常使用

```yml
eureka:
  client:
    serviceUrl:
      ## 当注册eureka服务是集群时，往一个地址注入，集群内会自动同步注册
      defaultZone: http://127.0.0.1:1002/eureka
      ## 当eureka服务需要用户名和密码时使用下面的方式
      ## http://user:password@localhost:8761/eureka
```

###### 自定义元数据

```properties
## 元数据配置，不影响正常eureka使用，可以用于云商发布版本 或者 提供给客户端的常量等等
eureka：
	instance：
		metadataMap:
			username: xxx
			key: xxx
```

##### 2. 启动类Application.class

@EnableEurekaClient 只发现eureka的注册服务。

@EnableDiscoveryClient 能发现很多种类的注册服务。

![1585821358532](https://github.com/TrimGHU/reborn/blob/master/1585821358532.png)

### Consul

##### 1. 服务注册与发现

##### 2. 分布式锁

### Ali-Nacos

##### 1. 服务注册与发现

##### 2. 配置中心（类似Spring Cloud Config）

##### 3. 集群部署











