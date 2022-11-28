package top.joker.mailcentor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
class MailCentorApplicationTests {

    @Autowired
    private JavaMailSenderImpl mailSender;
    @Test
    void contextLoads() {
    }

}
