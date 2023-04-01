package top.joker.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/wx")
@RefreshScope
public class WxLogin {
    //小程序APPid
    @Value("${wechat.params.appid:null}")
    private String appid;
    //小程序密钥
    @Value("${wechat.params.secret:null}")
    private String secret;
    //授权方式
    @Value("${wechat.apis.code2sessiongrant_type:null}")
    private String code2sessiongrant_type;
    //小程序登录接口地址
    @Value("${wechat.apis.code2session:null}")
    private String code2sessionApi;

    @Value("${wechat.apis.sendMessage:null}")
    private String sendMessage;
    private RestTemplate restTemplate;

    private String TOKEN;
    @Value("${wechat.apis.gettoken:null}")
    private String getAccessTokenApi;

    @Value("${wechat.apis.grant_type:null}")
    private String grant_type;

    /**
     * 小程序登录接口
     * @param data 接受wx.login返回的jscode
     * @return 返回用户的opneid以及会话密钥session_key
     */
    @PostMapping(value="/Login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject login(@RequestBody JSONObject data){
        StringBuilder sb = new StringBuilder();
        sb.append(this.code2sessionApi)
                .append("?appid=").append(this.appid)
                .append("&secret=").append(this.secret)
                .append("&grant_type").append(this.code2sessiongrant_type)
                .append("&js_code=").append(data.getString("code"));
        JSONObject jsonObject = JSONObject.parseObject(restTemplate.getForObject(sb.toString(),String.class));
        System.out.print(jsonObject);
        return jsonObject;
    }

    /**
     * 获取小程序的token
     * @return 返回token字符串
     */
    private String getAccessToken(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getAccessTokenApi)
                .append("?appid=").append(this.appid)
                .append("&secret=").append(this.secret)
                .append("&grant_type").append(this.grant_type);
        return restTemplate.getForObject(stringBuilder.toString(),String.class);
    }

    /**
     * 模拟发送信息，通过小用户的openid调用sendUniformMessage接口发送订阅信息
     * @param openId
     * @return
     */
    @PostMapping(value = "sendUniformMessage")
    public Boolean sendUniformMessage(@RequestBody String openId){
        StringBuilder stringBuilder = new StringBuilder();


        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        String param = JSONObject.toJSONString("123");
        //okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(MediaType.APPLICATION_JSON,param);
        //Request request = new Request.Builder().url(baseUrl + cancelOrder).post(requestBody).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //提交参数设置
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("title", "zimug 发布文章第二篇");
        map.add("body", "zimug 发布文章第二篇 测试内容");

        // 组装请求体
        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<MultiValueMap<String, String>>(map, headers);


        String access_token = this.getAccessToken();
        stringBuilder.append(this.getAccessTokenApi)
                .append("?access_token=")
                .append(access_token)
                .append("&touser=")
                .append(openId);
        return false;
    }
}
