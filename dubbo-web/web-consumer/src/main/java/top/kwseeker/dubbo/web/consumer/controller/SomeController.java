package top.kwseeker.dubbo.web.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.kwseeker.dubbo.web.api.SomeService;

@Controller
public class SomeController {

    @Autowired
    private SomeService someService;

    @RequestMapping("/some.do")
    public String someHandle() {
        String ret = someService.hello("Lee");
        System.out.println("web-consumer SomeController.someHandle() ret=" + ret);
        return "index.jsp";
    }
}
