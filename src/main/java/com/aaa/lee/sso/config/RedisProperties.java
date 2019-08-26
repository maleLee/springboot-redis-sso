package com.aaa.lee.sso.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/7/27 10:30
 * @Description
 *      从application.properties中获取自定义的属性信息
 *      @Component:把该配置类作为组件放入Spring的IOC中
 *                 当springboot启动的时候，会自主的加载该组件信息
 *      @ConfigurationProperties:
 *          指定properties文件，并且从中读取该文件中的属性
 *          如果没有指定读取哪个properties配置文件，则会选择默认的properties文件进行读取(application.properties)
 *          获取属性的规则:
 *              1.指定properties文件中key的前缀
 *              2.把key作为属性的形式放入属性类中
 *              3.springboot会自动映射该key的值，并且赋值给属性
 *                  相当于@Value("${key}")
 *            prefix可以省略，默认属性就是prefix
 **/
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String nodes;
    private Integer maxAttmpts;
    private Integer expire;
    private Integer commandTimeout;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public Integer getMaxAttmpts() {
        return maxAttmpts;
    }

    public void setMaxAttmpts(Integer maxAttmpts) {
        this.maxAttmpts = maxAttmpts;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public Integer getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(Integer commandTimeout) {
        this.commandTimeout = commandTimeout;
    }
}
