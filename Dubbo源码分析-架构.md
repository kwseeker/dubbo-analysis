# Dubbo系统架构

[Dubbo 2.7.3 src](https://github.com/apache/dubbo/releases/tag/dubbo-2.7.3)

**分布式技术图谱**：

![](imgs/分布式系统技术图谱.png)

**Dubbo三大核心功能**：

+ RPC
+ 智能容错与负载均衡
+ 服务自动注册与发现



## 系统架构简介

### 设计原则 

+ **微内核+插件**

  方便用户自定义拓展功能。

+ **采用URL作为配置信息的统一格式**

### 三大领域模型

+ **Protocol服务域**

+ **Invoker实体域**

  Provider的代理对象

+ **Invocation会话域**

### 四大组件

+ **Registry**

+ **Consumer**

+ **Provider**

+ **Monitor**


### 十层架构(重要)

![](https://dubbo.apache.org/docs/zh-cn/dev/sources/images/dubbo-framework.jpg)

#### Bussiness

+ **Service** 

  对应示例代码中公共模块中定义的Interface接口和Provider中定义的接口实现类。

  + **Interface**

    业务服务接口

  + **Implement**

    业务服务实现类

#### RPC

+ **Config**

  对应xml中的`dubbo:application`、`dubbo:service`、`dubbo:reference`、`dubbo:registry`等配置。基础篇中提到上面的标签都对应着一个XxxConfig类。这个Config就是指的这些实现类。

  上图中只列举了其中两个Config类：ReferenceConfig和ServiceConfig；其实还有十多个。

+ **Proxy**

  之前了解RPC框架的实现其实原理都类似，包括SpringCloud Feign，消费者都是生成代理类，代理类方法和远程服务接口方法相同，内部实现则是依赖TCP\HTTP通信框架将请求方法参数发送给远程，远程收到请求方法和参数，调用本地方法获取结果，然后将结果通过TCP\HTTP通信框架发送回去。消费者端接收到请求后再将结果反序列化成结果对象。

  Proxy就是消费者端的代理层。图中Proxy节点是消费者端的代理对象，Invoker是提供者端的代理对象。

  TODO: 源码分析Proxy和Invoker的区别？不妨根据使用经验大胆猜测Proxy是消费者端向Provider发送远程调用的代理对象，而Invoker是Provider异步返回结果时调用回调方法的接口抽象？

+ **Registry**

  注册中心，实现服务注册与发现。

+ **Cluster**

  实现分布式方面控制，包括服务路由、降级、限流、容错、负载均衡等实现。

+ **Monitor**

  一个用于监控的独立的服务

+ **Protocol**

  封装RPC调用，Dubbo支持８种RPC通信协议。图中表示默认的Dubbo通信协议。

#### Remoting

+ **Exchange**

  封装请求响应模式，**同步转异步**，以 `Request`, `Response` 为中心，扩展接口为 `Exchanger`, `ExchangeChannel`, `ExchangeClient`, `ExchangeServer`

+ **Transport**

  RPC调用网络传输实现，默认是Netty实现，还支持Mina。

+ **Serialize**

  网络传输过程中将对象数据转为流数据的序列化方法实现。

### 源码结构

```txt
dubbo-dubbo-2.7.3/
├── CHANGES.md
├── CODE_OF_CONDUCT.md
├── codestyle
│   ├── checkstyle-suppressions.xml
│   ├── checkstyle.xml
│   ├── dubbo_codestyle_for_idea.xml
│   └── manage_profiles.png
├── CONTRIBUTING.md
├── dubbo-all
│   └── pom.xml
├── dubbo-bom
│   └── pom.xml
├── dubbo-cluster
│   ├── pom.xml
│   └── src
├── dubbo-common
│   ├── pom.xml
│   └── src
├── dubbo-compatible
│   ├── pom.xml
│   ├── README.md
│   └── src
├── dubbo-config
│   ├── dubbo-config-api
│   ├── dubbo-config-spring
│   └── pom.xml
├── dubbo-configcenter				//dubbo支持的配置中心实现
│   ├── dubbo-configcenter-api
│   ├── dubbo-configcenter-apollo
│   ├── dubbo-configcenter-consul
│   ├── dubbo-configcenter-etcd
│   ├── dubbo-configcenter-nacos
│   ├── dubbo-configcenter-zookeeper
│   └── pom.xml
├── dubbo-container
│   ├── dubbo-container-api
│   ├── dubbo-container-log4j
│   ├── dubbo-container-logback
│   ├── dubbo-container-spring
│   └── pom.xml
├── dubbo-demo
│   ├── dubbo-demo-annotation
│   ├── dubbo-demo-api
│   ├── dubbo-demo-interface
│   ├── dubbo-demo-xml
│   ├── pom.xml
│   └── README.md
├── dubbo-dependencies
│   ├── dubbo-dependencies-zookeeper
│   └── pom.xml
├── dubbo-dependencies-bom
│   └── pom.xml
├── dubbo-distribution
│   ├── pom.xml
│   └── src
├── dubbo-filter
│   ├── dubbo-filter-cache
│   ├── dubbo-filter-validation
│   └── pom.xml
├── dubbo-metadata-report
│   ├── dubbo-metadata-definition			//服务注册与发现服务节点定义
│   ├── dubbo-metadata-definition-protobuf
│   ├── dubbo-metadata-report-api
│   ├── dubbo-metadata-report-consul
│   ├── dubbo-metadata-report-etcd
│   ├── dubbo-metadata-report-nacos
│   ├── dubbo-metadata-report-redis
│   ├── dubbo-metadata-report-zookeeper
│   └── pom.xml
├── dubbo-monitor
│   ├── dubbo-monitor-api
│   ├── dubbo-monitor-default
│   └── pom.xml
├── dubbo-plugin
│   ├── dubbo-qos							//服务质量和管控平台通信
│   └── pom.xml
├── dubbo-registry
│   ├── dubbo-registry-api
│   ├── dubbo-registry-consul
│   ├── dubbo-registry-default
│   ├── dubbo-registry-etcd3
│   ├── dubbo-registry-multicast
│   ├── dubbo-registry-multiple
│   ├── dubbo-registry-nacos
│   ├── dubbo-registry-redis
│   ├── dubbo-registry-sofa
│   ├── dubbo-registry-zookeeper
│   └── pom.xml
├── dubbo-remoting				//dubbo远程调用协议实现
│   ├── dubbo-remoting-api
│   ├── dubbo-remoting-etcd3
│   ├── dubbo-remoting-grizzly
│   ├── dubbo-remoting-http
│   ├── dubbo-remoting-mina
│   ├── dubbo-remoting-netty
│   ├── dubbo-remoting-netty4
│   ├── dubbo-remoting-p2p
│   ├── dubbo-remoting-zookeeper
│   └── pom.xml
├── dubbo-rpc					//dubbo中支持的其他rpc通信协议的封装
│   ├── dubbo-rpc-api
│   ├── dubbo-rpc-dubbo
│   ├── dubbo-rpc-hessian
│   ├── dubbo-rpc-http
│   ├── dubbo-rpc-injvm				//不是远程调用是进程内本地调用
│   ├── dubbo-rpc-jsonrpc
│   ├── dubbo-rpc-memcached
│   ├── dubbo-rpc-native-thrift
│   ├── dubbo-rpc-redis
│   ├── dubbo-rpc-rest
│   ├── dubbo-rpc-rmi
│   ├── dubbo-rpc-thrift
│   ├── dubbo-rpc-webservice
│   ├── dubbo-rpc-xml
│   └── pom.xml
├── dubbo-serialization
│   ├── dubbo-serialization-api
│   ├── dubbo-serialization-avro
│   ├── dubbo-serialization-fastjson
│   ├── dubbo-serialization-fst
│   ├── dubbo-serialization-gson
│   ├── dubbo-serialization-hessian2
│   ├── dubbo-serialization-jdk
│   ├── dubbo-serialization-kryo
│   ├── dubbo-serialization-native-hession
│   ├── dubbo-serialization-protobuf-json
│   ├── dubbo-serialization-protostuff
│   ├── dubbo-serialization-test
│   └── pom.xml
├── Jenkinsfile
├── LICENSE
├── licenseCheck.sh
├── mvnw
├── mvnw.cmd
├── NOTICE
├── pom.xml
├── PULL_REQUEST_TEMPLATE.md
├── README.md
└── SECURITY.md
```



### 源码调试

以2.7.3版本分析。



## Dubbo内核实现机制

**Dubbo所有功能都是基于Dubbo内核构建起来的，Dubbo内核包括SPI、AOP、IoC、Compiler。**

其中SPI是Dubbo内核的核心，内核其他部分则是通过SPI实现的。

### SPI

之前在Feign（Feign源码解析中有使用demo）、Java NIO（Selector接口在不同系统有不同的实现，而且需要在运行时通过分析系统类型进行选择加载系统对应实现类）和JDBC中遇到过，但是没有研究过SPI实现机制，这里又出现了，所以这次还是把它的原理搞清楚，便于后续代码理解，JDK SPI分析另起一个Markdown分析，《JDK SPI.md》。

#### JDK SPI 使用

示例代码：`dubbo-analysis/dubbo-src-analysis/jdk-spi`

还是以JDBC为例，JDK只是定义了java.sql.Driver的接口规范，但是没有实现，而具体的实现由各个数据库方案商提供（spi-common），用户（jdk-spi）通过JDK SPI机制引入依赖，让后通过`ServiceLoader$load()`加载各种实现，并遍历获取各种Driver接口实现。

#### Dubbo SPI 使用

示例代码：`dubbo-analysis/dubbo-src-analysis/kernel-spi`

**针对JDK SPI的使用缺陷(会全部加载实例化)，Dubbo对SPI做了优化。使用key-value的方式列举实现类，加载并实例时通过key指定要加载并实例化的实现类，而不是像JDK SPI标准用法那样遍历逐个加载实例化。**

**Dubbo 的 SPI 规范**:
１）接口名:可以随意定义
２）实现类名:在接口名前添加一个用于表示自身功能的“标识前辍”字符串（这个标识用于表示接口实现的别名）
３）提供者配置文件路径:在依次查找的目录为
		META-INF/dubbo/internal
		META-INF/dubbo
		META-INF/services
４）提供者配置文件名称:接口的全限定性类名,无需扩展名
５）提供者配置文件内容:文件的内容为 key=value 形式,value 为该接口的实现类的全限类性类名,key 可以随意,但一般为该实现类的“标识前辍”(首字母小写)。一个类名占一行。
６）提供者加载:ExtensionLoader 类相当于 JDK SPI 中的 ServiceLoader 类,用于加载提供者配置文件中所有的实现类,并创建相应的实例。

接口里面可以通过@SPI指定默认加载的实现类，某个实现类的key可以有多个。

```java
@SPI("Oracle")              //指定默认的Driver实现类,这个注解是必须加的
public interface Driver {
  ...
}
```

实现类列表配置

```txt
//META-INF/dubbo/internal/top.kwseeker.dubbo.src.spi.Driver
mysql=top.kwseeker.dubbo.src.spi.MySQLDriver
oracle, Oracle=top.kwseeker.dubbo.src.spi.OracleDriver
```

使用

```java
// 获取SPI接口Order的扩展加载实例
// 检查入参class是否为Interface，是否有加@SPI注解
ExtensionLoader<Driver> loader = ExtensionLoader.getExtensionLoader(Driver.class);
// 使用key加载对应的实现类
Driver mysqlDriver = loader.getExtension("mysql");
mysqlDriver.connect("", null);
Driver oracleDriver = loader.getExtension("oracle");
oracleDriver.connect("", null);
// 使用＠SPI默认指定的实现类
Driver driver = loader.getExtension("true");	//true表示使用＠SPI指定的实现类
driver.connect("", null);
```

### AOP

### IoC

### Compiler

