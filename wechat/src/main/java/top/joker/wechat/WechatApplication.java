package top.joker.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@NacosConfigurationProperties(groupId = "wechat",dataId = "wechat")
public class WechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }


    @Bean
    public RestTemplate GetRestTemplate() {
        return new RestTemplate();
    }

}
