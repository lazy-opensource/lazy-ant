package com.lazy.ant.example.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/7/10.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"META-INF/spring/lazyAnt-demo-provider.xml"});
        context.start();

        System.in.read();
    }
}