package top.kwseeker.dubbo.msa.msauserprovider.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.kwseeker.dubbo.msa.common.entity.User;
import top.kwseeker.dubbo.msa.common.service.UserService;
import top.kwseeker.dubbo.msa.msauserprovider.dao.UserDao;

import java.util.concurrent.TimeUnit;

@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    //TODO
    @CacheEvict(value = "realTimeCache", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
    }

    @Cacheable(value = "realTimeCache", key = "'user_' + #id")
    @Override
    public User findUserById(int id) {
        return userDao.selectUserById(id);
    }

    private volatile Object count;
    //双重检测机制防止高并发场景缓存穿透
    @Override
    public Integer findUserCount() {
        BoundValueOperations<Object, Object> ops = redisTemplate.boundValueOps("count");
        //从缓存获取数据
        count= ops.get();
        if(count==null) {
            synchronized (this) {
                count = ops.get();
                if(count == null) {
                    count = userDao.selectUserCount();
                    //查询结果存入缓存
                    ops.set(count, 10, TimeUnit.SECONDS);
                }
            }
        }
        return (Integer) count;
    }
}
