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

![1585818317858](https://github.com/TrimGHU/reborn/blob/master/images/1585818317858.png)



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

![1585821358532](https://github.com/TrimGHU/reborn/blob/master/images/1585821358532.png)

### Consul

##### 1. 服务注册与发现

##### 2. 分布式锁

### Ali-Nacos

##### 1. 服务注册与发现

##### 2. 配置中心（类似Spring Cloud Config）

##### 3. 集群部署



### Ribbon & Feign

##### 1. 负载均衡

> Ribbon is a client side load balancer which gives you a lot of control over the behaviour of HTTP and TCP clients. 

Ribbon是一个客户端的负载均衡器，内置多种负载均衡算法

```properties
##轮询 
RoundRobinRule 
##随机
RandomRule  
##会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务,以及并发的连接数量超过阈值的服务,然后对剩余的服务列表按照轮询策略进行访问
AvailabilityFilteringRule 
##根据平均响应时间计算所有服务的权重,响应时间越快,服务权重越大,被选中的机率越高;刚启动时,如果统计信息不足,则使用RoundRobinRule策略,等统计信息足够时,会切换到WeightedResponseTimeRule
WeightedResponseTimeRule 
##先按照RoundRobinRule的策略获取服务,如果获取服务失败,则在指定时间内会进行重试,获取可用的服务
RetryRule
##会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务,然后选择一个并发量最小的服务
BestAvailableRule 
##默认规则,复合判断server所在区域的性能和server的可用性选择服务器
ZoneAvoidanceRule
```

```java
@Bean
public IRule cusomRule(){
	return new RoundRobinRule();
}
```

>Feign is a declarative web service client.  It makes writing web service clients easier.  To use Feign create an interface and annotate it.  It has pluggable annotation support including Feign annotations and JAX-RS annotations. 

Feign是整合了Ribbon的声明式客户端，因为其负载均衡由ribbon完成，所以配置方法和其一致。



##### 2. 引入和调用

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-ribbon</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```

```JAVA
@FeignClient(name = "user")
public interface UserService {

    @GetMapping("/name")
    String getName();
}
```

```JAVA
@Autowired
private RestTemplate restTemplate;

@GetMapping("/name-ribbin")
	public String getUserNameRibbin(){
	return restTemplate.getForObject("http://user/name",String.class);
}
```

```properties
##Feign默认hystrix是关闭的
feign:
  hystrix:
    enabled: true
```



##### 3. 饥饿加载模式

开启饥饿加载模式是指在服务启动时就创建ribbonclient或feignclient，而不是服务调用时创建。

```properties
ribbon.eager-load.enabled=true
ribbon.eager-load.clients=hello-service, user-service
```

##### 4. Hystrix的相关

###### 服务降级 fallback / 依赖隔离 / 熔断(见下方)



### Hystrix

>Hystrix is a library that helps you control the interactions between these distributed services by adding latency tolerance and fault tolerance logic. Hystrix does this by isolating points of access between the services, stopping cascading failures across them, and providing fallback options, all of which improve your system’s overall resiliency.

##### 1. 引入

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-hystrix</artifactId>
</dependency>
```

##### 2. 使用和配置

`@SpringCloudApplication` 类上加入组合注解或者`@EnableCircuitBreaker`，开启熔断

`@HystrixCommand(fallbackMethod = "xxxx") `使用在方法上可以配置超时时间，配合Ribbon使用

`@FeignClient(name = "user", fallback = xxxx.class)` 使用在远程调用的Interface上，配合Feign使用

###### 超时配置

```java
@HystrixCommand(fallbackMethod="fallback",
	commandProperties = {
	     @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000" )
	}
)
```

+

```java
@HystrixCommand(fallbackMethod="fallback",commandKey="||userGetKey||")
```

```properties
hystrix.command.||userGetKey||.execution.isolation.thread.timeoutInMilliseconds = 13000
```

+

```properties
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=3000
```

+

```properties
hystrix.command.||UserRemoteClient#getUser(Long)||.execution.isolation.thread.timeoutInMilliseconds = 300
```

+

```properties
hystrix.command.||service-id||.execution.isolation.thread.timeoutInMilliseconds=3000
```

##### 3. 隔离策略

###### 线程池（默认）

​	即每组服务都定义隔离开的一个线程池，每个线程池之间互不影响。

​	此方法对服务器的性能有一定的损耗，因为多线程之间的数据互通会产生一定的延迟，并且易生成碎片	

###### 信号量

​    即对每个资源调用限制并发数，缺点是无法对超时自动降级，需要等待客户端自己超时处理

##### 4. 熔断

​    断路器的三个重要参数：快照时间窗、请求总数下限、错误百分比下限。 

###### 快照时间窗

​	 断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。 

###### 请求总数下限

​	 在快照时间窗内，必须满足请求总数下限才有资格根据熔断。默认为20，意味着在10秒内，如果该hystrix命令的调用此时不足20次，即时所有的请求都超时或其他原因失败，断路器都不会打开。 

###### 错误百分比下限

​	 当请求总数在快照时间窗内超过了下限，比如发生了30次调用，如果在这30次调用中，有16次发生了超时异常，也就是超过50%的错误百分比，在默认设定50%下限情况下，这时候就会将断路器打开。 

##### 5. 请求合并

​	支持在时间窗内依赖同以服务的多个请求合并，独立线程池批量请求服务端（服务端支持批量请求接口），处理完毕之后一起返回。

​	其实请求合并的作用还是需要仔细当前场景下是否适合，因为毕竟是在一个时间窗口内的，如果一个高延迟的服务合并确实会起到一定的作用，但是作为一个低延迟高低并发的接口呢？这就不一定了。关键在于2点因素，**服务的处理时间** 和 **窗口时间内的并发量**。

![hystrix-collapse-after](https://github.com/TrimGHU/reborn/blob/master/images/hystrix-collapse-after.png)

##### 6. Dashboard

######  Actuator 

	 ###### Turbine 



### Ali-Sentinel

##### 1. 应用

##### 2. 隔离策略

###### 信号量 + 并发线程数限制

##### 3. 比较Hystrix

![sentinel-hystrix](https://github.com/TrimGHU/reborn/blob/master/images/sentinel-hystrix.png)








