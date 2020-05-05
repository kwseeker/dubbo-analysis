package top.kwseeker.dubbo.msa.msauserprovider.service;

import top.kwseeker.dubbo.msa.common.service.BizService;

public class VipBizServiceImpl implements BizService {

    @Override
    public void bizServe() {
        System.out.println("调用为VIP用户提供的服务");
    }
}
