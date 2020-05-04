package top.kwseeker.dubbo.msa.msauserconsumer.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.kwseeker.dubbo.msa.common.entity.User;
import top.kwseeker.dubbo.msa.common.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Reference   // Dubbo的注解   <dubbo:reference />
    private UserService userService;

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
}
