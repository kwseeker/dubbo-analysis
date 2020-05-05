package top.kwseeker.dubbo.msa.msauserprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class MsaUserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaUserProviderApplication.class, args);
    }

}
