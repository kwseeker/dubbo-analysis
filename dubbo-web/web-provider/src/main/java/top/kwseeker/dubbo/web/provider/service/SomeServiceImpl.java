package top.kwseeker.dubbo.web.provider.service;

import top.kwseeker.dubbo.web.api.SomeService;

public class SomeServiceImpl implements SomeService {

    public String hello(String name) {
        System.out.println("web-provider SomeServiceImpl.hello() ...");
        return "hello" + name;
    }
}
