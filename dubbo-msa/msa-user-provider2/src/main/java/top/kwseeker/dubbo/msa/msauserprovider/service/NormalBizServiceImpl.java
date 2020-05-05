package top.kwseeker.dubbo.msa.msauserprovider.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import top.kwseeker.dubbo.msa.common.service.BizService;

/**
 * 为普通用户提供的业务服务
 */
@Service(protocol = {"dubbo","rmi"}, group = "bizService.normal")   //多协议支持
@Component
public class NormalBizServiceImpl implements BizService {

    @Override
    public void bizServe() {
        System.out.println("第二个服务实例，调用为普通用户提供的服务");
    }
}
