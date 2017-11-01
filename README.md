#### qb-logger包使用

    基于log4j2 2.9版本开发，加入error级别日志自定义邮件系统
    
    支持http异步输出
    
    项目启动需要设置环境参数，如设置了环境classpath则不需要设置。直接创建log4j2.xml和qb.logger.propertes文件
    类似如下：
    
```java
System.setProperty("log4j.configurationFile",System.getProperty("user.dir") + "/conf/qbao/log4j2.xml");
System.setProperty("qb-logger",System.getProperty("user.dir") + "/conf/qbao/qb-logger.properties");

```
##### qb-logger.properties配置文件

```properties
properties 文件配置

#邮件功能开启
email.enable=true

#邮件服务器smtp服务地址
email.smtp.host=smtp.exmail.qq.com

#发送账号
email.user=zabbix@qbao.com
#密码
email.password=******
#发送邮件标题
email.subject=zabbix-logger-error
#接收人
email.send.user=songjie@qbao.com

```

##### log4j2.xml配置文件demo
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appenders>
        <Http name="Http" url="http://localhost:29010/compass/log">
            <JsonLayout properties="true"/>
        </Http>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%m%n"/>
        </Console>
    </appenders>

    <loggers>
        <!--logger 继承root  此配置是输出com.spring.mvc 下面的error及以上的日志到 RollingFile 项-->
        <!--additivity ＝ true 则root日志也输出。false则不执行root日志-->
        <Logger name="com.qbao" level="info" additivity="false">
            <AppenderRef ref="Http"/>
            <AppenderRef ref="Console"/>
        </Logger>
    </loggers>
</configuration>
```

    