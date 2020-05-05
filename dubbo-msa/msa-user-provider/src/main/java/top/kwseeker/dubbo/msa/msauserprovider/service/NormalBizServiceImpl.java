package top.kwseeker.dubbo.msa.msauserprovider.service;

import top.kwseeker.dubbo.msa.common.service.BizService;

/**
 * 为普通用户提供的业务服务
 */
public class NormalBizServiceImpl implements BizService {

    @Override
    public void bizServe() {
        System.out.println("调用为普通用户提供的服务");
    }
}
