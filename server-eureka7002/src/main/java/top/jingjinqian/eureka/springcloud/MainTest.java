package top.jingjinqian.eureka.springcloud;


import com.sun.jersey.api.client.AsyncViewUniformInterface;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class MainTest {

    public static void main(String[] args) throws Exception {

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKOJybB/0CQYeStbZZmr1SfgAKUou3cV7jT9abur5lin6Ct27CZe7XzmEYJu8fYnVmTYK1o6sioWP3Ji1ueEohDo/bzizFZDoxHp2pbKTefpc0HK6A+bLXo13xdBd+4up88Gz4YQmfHLbPhY+83z78stU/91ap10o/3XPfQ4xHBwIDAQAB";
        String path;
        if (new File("D:\\app\\data\\code\\java\\tesst").exists()) {
            path = "D:\\app\\data\\code\\java\\tesst";
        } else {
            path = ValidCode.class.getResource("D:\\app\\data\\code\\java\\tesst").getPath().substring(1);
        }
        String jpgPath = new File("validCode.jpg").getAbsolutePath();
        System.out.println("验证码识别训练文件路径:[{}],验证码图片路径:[{}]"+" "+path+"  "+jpgPath);

        FileUtils.download("http://pm.ucap.com.cn/ajax/Authorization/GetValidateCode/" + (int) Math.floor(Math.random() * (100000 + 1)),jpgPath);
        ValidCode validCodeOcr = new ValidCode(URLDecoder.decode(path), "eng");
        String validCode = validCodeOcr.ocr(jpgPath);
        validCode = validCode.replaceAll("[^\\d]", "");
        System.out.println("验证码为:" + validCode);
        RSAEncrypt rsaEncrypt = new top.jingjinqian.eureka.springcloud.RSAEncrypt();
        String pwd = "jjq*#%0615";
        pwd = rsaEncrypt.encrypt(pwd, publicKey);

        validCode = rsaEncrypt.encrypt(validCode, publicKey);
        Map<String, Object> map = new HashMap<>();
        map.put("UserId", "jingjq");
        map.put("Password", pwd);
        map.put("ValidateCode", validCode);
        map.put("loginType", "CA");
        map.put("CAPassword", "");
        AsyncViewUniformInterface httpRequest = null;
        CloseableHttpResponse response = (CloseableHttpResponse) httpRequest.post("http://pm.ucap.com.cn/MvcPages/Home/LoginPost", map);
        HttpEntity post = response.getEntity();
        String resultMsg = EntityUtils.toString(post);
        if (response.getStatusLine().getStatusCode() <= 200 && !resultMsg.contains("error")) {
            System.out.println("登录成功");

        }
        if (response.getStatusLine().getStatusCode() == 500) {
            System.out.println("服务器异常[" + resultMsg + "]");
            System.exit(0);
        }
        if (resultMsg.contains("密码错误")) {
            System.out.println("密码错误[" + resultMsg + "]");
            System.exit(0);
        }
        System.out.println("登录失败[" + resultMsg + "]");


    }


}
