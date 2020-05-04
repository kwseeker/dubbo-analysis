package top.kwseeker.dubbo.msa.common.service;

import top.kwseeker.dubbo.msa.common.entity.User;

public interface UserService {

    void addUser(User user);
    User findUserById(int id);
    Integer findUserCount();
}
