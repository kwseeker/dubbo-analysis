package top.kwseeker.dubbo.msa.msauserprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableCaching
@EnableDubbo
@ImportResource(locations = {"classpath:spring-provider.xml"})
@SpringBootApplication
public class MsaUserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaUserProviderApplication.class, args);
    }

}
