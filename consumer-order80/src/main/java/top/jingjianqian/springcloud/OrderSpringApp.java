package top.jingjianqian.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
public class OrderSpringApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderSpringApp.class,args);
    }
}
