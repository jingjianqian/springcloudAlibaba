package top.jingjianqian.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RestController;
import top.jingjianqian.ribbon.MyRules;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
@RibbonClient(name="CLOUD-PAYMENT-SERVICE",configuration = MyRules.class)
public class OrderSpringApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderSpringApp.class,args);
    }
}
