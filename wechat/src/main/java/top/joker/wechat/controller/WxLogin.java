package top.joker.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wx")
public class WxLogin {
    @PostMapping(value="/Login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject login(@RequestBody JSONObject data){
        System.out.println(data);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",data.getString("code"));
        return jsonObject;
    }
}
