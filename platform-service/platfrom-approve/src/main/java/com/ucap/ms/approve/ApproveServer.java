package com.ucap.ms.approve;
/**
 * @AUTH jingjanqian
 * @time 20210805
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class ApproveServer {
    public static void main(String[] args){
        SpringApplication.run(ApproveServer.class,args);
    }
}
