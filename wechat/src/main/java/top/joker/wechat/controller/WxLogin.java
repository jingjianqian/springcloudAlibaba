package top.joker.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class WxLogin {
    @GetMapping ("/Login")
    public String login(@RequestBody(required = false) JSONObject data){
        System.out.println(data);
        return "1";
    }
}
