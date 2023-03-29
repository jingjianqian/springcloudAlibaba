package top.joker.wechat;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class MyMailSender extends JavaMailSenderImpl {


    @Override
    public void send(SimpleMailMessage... simpleMessages)  {
        try {
            super.send(simpleMessages);
        }catch (Exception e){
            System.out.println("fuck");
        }

    }
}
