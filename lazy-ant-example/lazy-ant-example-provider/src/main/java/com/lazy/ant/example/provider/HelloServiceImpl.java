package com.lazy.ant.example.provider;

import com.lazy.ant.example.api.IHelloService;

/**
 * <p></p>
 *
 * @author laizhiyuan
 * @date 2018/7/10.
 */
public class HelloServiceImpl implements IHelloService {

    @Override
    public void sayHello(String name) {
        System.out.println(name + " sayHello");
    }
}
