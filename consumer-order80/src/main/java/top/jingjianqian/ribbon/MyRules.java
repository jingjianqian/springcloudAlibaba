package top.jingjianqian.ribbon;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class MyRules {

    @Bean
    public IRule myRule(){
        return  new RandomRule();
    }
}
