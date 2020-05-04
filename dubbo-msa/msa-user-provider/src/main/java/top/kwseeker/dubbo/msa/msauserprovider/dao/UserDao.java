package top.kwseeker.dubbo.msa.msauserprovider.dao;

import org.apache.ibatis.annotations.Mapper;
import top.kwseeker.dubbo.msa.common.entity.User;

@Mapper
public interface UserDao {

    void insertUser(User user);
    Integer selectUserCount();
    User selectUserById(int id);
}
