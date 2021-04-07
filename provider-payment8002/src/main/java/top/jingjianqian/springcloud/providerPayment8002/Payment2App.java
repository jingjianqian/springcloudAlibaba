package top.jingjianqian.springcloud.providerPayment8002;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Payment2App {
    public static void main(String[] args) {
        SpringApplication.run(Payment2App.class,args);
    }
}
