package com.ucap.ms.approve;
/**
 * @AUTH jingjanqian
 * @time 20210805
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ApproveServer {
    public static void main(String[] args){
        SpringApplication.run(ApproveServer.class,args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
