package top.joker.wechat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

@SpringBootTest
class MailCentorApplicationTests{

    @Autowired
    private MyMailSender mailSender;

    @Test
    void contextLoads() {
        //创建简单的邮件发送对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("18697998680@163.com");           // 设置发件人邮箱（若配置默认邮箱则不用再设置）
        message.setTo("529463245@qq.com");            // 设置收件人邮箱
        message.setCc("xiaofeng500@qq.com");            // 设置抄报人邮箱（可以不填写）
        message.setBcc("575814158@qq.com");             // 设置密送人邮箱（可以不填写）
        message.setSubject("缴费通知");                  // 设置邮件主题
        message.setText("您手机已欠费100元，请及时缴费！"); // 设置邮件文本内容
        message.setSentDate(new Date());                // 设置邮件发送时间
        //发送
//        .send(message);
        mailSender.send(message);

    }

}
