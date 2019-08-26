package com.aaa.lee.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/7/27 11:03
 * @Description
 *      redis的集群配置
 *      如何把一个普通的Java类做成springboot的配置类？
 *          @SpringBootApplication
 *          配置类就相当于xml文件，然后@Bean相当于xml文件中<bean></bean>标签
 *      JedisCluster:
 *          对redis集群做增删查
 *
 **/
@SpringBootApplication
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * @author Seven Lee
     * @description
     *      获取JedisCluster对象
     * @param
     * @date 2019/7/27
     * @return redis.clients.jedis.JedisCluster
     * @throws
     **/
    @Bean
    public JedisCluster getJedisCluster() {
        // 1.通过redisProperties获取Host和Port
        String nodes = redisProperties.getNodes();
        // 2.切割nodes，以","作为切割符进行切割，返回的是数组
            // split:[192.168.127.14:6380,192.168.127.14:6381,192.168.127.14:6382....]
        String[] split = nodes.split(",");
        // 3.创建Set集合对象(泛型HostAndPort)
        Set<HostAndPort> hostAndPortSet = new HashSet<HostAndPort>();
        // 4.循环split数组
        for (String hostPort : split) {
            // 5.再次进行分割，以":"为分隔符，获取到的仍然是一个数组
                // strings:[192.168.127.140,6380]
            String[] strings = hostPort.split(":");
            // 6.创建HostAndPort对象(属于redis中的jedis包)
                // 没有无参的构造方法，需要传入两个值
                // (1)Ip--String  (2)Port--Integer
            // 7.把Host和Port放入HostAndPort对象中
            HostAndPort hostAndPort = new HostAndPort((strings[0]).trim(), Integer.parseInt((strings[1]).trim()));
            // 8.把HostAndPort对象放入Set集合中
            hostAndPortSet.add(hostAndPort);
        }
        // 9.创建JedisCluster对象
            // 也没有无参构造方法
        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet, redisProperties.getCommandTimeout(), redisProperties.getMaxAttmpts());
        // 10.返回jedisCluster对象
        return jedisCluster;
    }

}
