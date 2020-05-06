package top.kwseeker.dubbo.src.service.impl;

import top.kwseeker.dubbo.src.service.SomeService;

public class SomeServiceImpl implements SomeService {

    @Override
    public String hello(String name) {
        System.out.println("执行提供者的hello() " + name);
        return "hello, " + name;
    }
}
