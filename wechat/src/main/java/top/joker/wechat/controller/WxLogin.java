package top.joker.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/wx")
@RefreshScope
public class WxLogin {
    @Value("${wechat.params.appid:null}")
    private String appid;
    @Value("${wechat.params.secret:null}")
    private String secret;
    @Value("${wechat.apis.code2sessiongrant_type:null}")
    private String grant_type;

    @Value("${wechat.apis.code2session:null}")
    private String code2sessionApi;

    @Autowired
    private RestTemplate restTemplate;


        @PostMapping(value="/Login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject login(@RequestBody JSONObject data){
        JSONObject jsonObject = new JSONObject();
        StringBuilder sb = new StringBuilder();
        sb.append(this.code2sessionApi)
                .append("?appid=").append(this.appid)
                .append("&secret=").append(this.secret)
                .append("&js_code=").append(data.getString("code"));
        jsonObject = JSONObject.parseObject(restTemplate.getForObject(sb.toString(),String.class));
        System.out.print(jsonObject);
        return jsonObject;
    }
}
