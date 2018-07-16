package com.lazy.ant.example.consumer;

import com.lazy.ant.example.api.IHelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/7/12.
 */
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/lazyAnt-demo-consumer.xml"});
        context.start();
        IHelloService helloService = (IHelloService) context.getBean("helloService"); // get remote service proxy

        while (true) {
            try {
                Thread.sleep(1000);
                helloService.sayHello("laizhiyuan");
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
