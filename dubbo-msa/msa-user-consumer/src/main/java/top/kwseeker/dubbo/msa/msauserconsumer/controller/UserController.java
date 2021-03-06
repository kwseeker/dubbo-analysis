package top.kwseeker.dubbo.msa.msauserconsumer.controller;

import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.kwseeker.dubbo.msa.common.entity.User;
import top.kwseeker.dubbo.msa.common.service.BizService;
import top.kwseeker.dubbo.msa.common.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    //关闭服务检查，当服务provider还未启动时，consumer仍然可以成功启动
    //@Reference(check = false)           //@Reference 对应 <dubbo:reference>标签
    @Reference(check = false, version = "0.0.2")
    private UserService userService;
    @Reference(check = false, group = "bizService.normal"
            , loadbalance = "roundrobin"
            , methods = {@Method(name="bizServe", loadbalance = "leastactive")}     //注意＠Reference methods赋值方式
    )
    private BizService bizService;

    @PostMapping("/register")
    public String someHandle(User user, Model model) {
        userService.addUser(user);
        model.addAttribute("user", user);
        return "/welcome.jsp";
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public User findHandle(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }

    @RequestMapping("/count")
    @ResponseBody
    public Integer countHandle() {
        return userService.findUserCount();
    }

    @RequestMapping("/bizServe")
    @ResponseBody
    public String bizServe() {
        bizService.bizServe();
        return "done";
    }
}
