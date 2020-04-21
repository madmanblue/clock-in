package com.future.eurekaserver.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
@PropertySource(value = "classpath:self.properties")
public class A implements DisposableBean, InitializingBean, BeanPostProcessor {

    @Value("${sun.self.url}")
    private String url;

    public A() {
//        Integer a = new Integer(10);
//        Integer b = new Integer(10);
//        log.info("a==b : {}",(a == b));
//        Integer c = 10;
//        Integer d = 10;
//        log.info("c==d : {}", (c == d));
//        log.info("a==c:{}", (a == c));
//        log.info("a.equals(b) : {}", a.equals(b));
//        log.info("A : {}", "construct");
        log.info("url : {}", url);

    }

    @PostConstruct
    public void initMethod(){
        log.info("A : {}", "initMethod");
        log.info("url : {}", url);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("A : {}", "preDestroy");
        log.info("url : {}", url);
    }

    @Override
    public void destroy() throws Exception {
        log.info("A : {}", "DisposableBean destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("A : {}", "InitializingBean afterPropertiesSet");
        log.info("url : {}", url);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("A : {}", "BeanPostProcessor postProcessBeforeInitialization");
        log.info("url : {}", url);
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("A : {}", "BeanPostProcessor postProcessAfterInitialization");
        return bean;
    }

    public static void main(String[] args) {

    }

    public static void testOldForEach(){
        long startTime = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("执行时间：" + excTime + "s");
    }

    public static void testNewForEach(){
        long startTime = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("执行时间：" + excTime + "s");
    }
}
