package top.kwseeker.dubbo.msa.msauserprovider.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import top.kwseeker.dubbo.msa.common.entity.User;
import top.kwseeker.dubbo.msa.common.service.UserService;

//下面的写法创建两个不同版本的Bean并没有效果
//@Service(version="0.0.2")
//@Scope("prototype")
//@Component("newUserService")
public class UserServiceTestImpl implements UserService {
    @Override
    public void addUser(User user) {
        System.out.println("0.0.2版本addUser接口");
    }

    @Override
    public User findUserById(int id) {
        System.out.println("0.0.2版本findUserById接口");
        User user = new User();
        user.setId(0);
        user.setName("Test");
        user.setAge(0);
        return user;
    }

    @Override
    public Integer findUserCount() {
        System.out.println("0.0.2版本findUserCount接口");
        return 10;
    }
}
