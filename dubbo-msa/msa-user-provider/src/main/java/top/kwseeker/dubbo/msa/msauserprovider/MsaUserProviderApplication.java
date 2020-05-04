package top.kwseeker.dubbo.msa.msauserprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableCaching
@EnableDubbo
@SpringBootApplication
public class MsaUserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaUserProviderApplication.class, args);
    }

}
