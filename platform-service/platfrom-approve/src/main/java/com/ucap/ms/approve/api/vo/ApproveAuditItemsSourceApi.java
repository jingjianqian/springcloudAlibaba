package com.ucap.ms.approve.api.vo;

import com.alibaba.fastjson.JSONObject;
import com.ucap.ms.approve.api.payload.RequestTokenPayload;
import com.ucap.ms.approve.config.ConfigClientController;
import com.ucap.ms.approve.exception.RequestInferfaceException;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApproveAuditItemsSourceApi {

    @Resource
    public  ConfigClientController configClientController;
    @Resource
    public   RestTemplate restTemplate;

    public  JSONObject getAuditItemsDeptLimit(String dept_code,Long timestamp){
        try {
            String accessToken = getAccessToken();
            if(accessToken == null){
                throw new RequestInferfaceException("获取 access token 失败");
            }
            Map<String, Object> map = requestParamsDept(accessToken, dept_code, timestamp);

            RequestEntity<Map<String, Object>> requestEntity = RequestEntity.post(new URL(configClientController.getGET_DEPTAUDITITEM_URL()).toURI())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .body(map);
            ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
            JSONObject jsonObject = JSONObject.parseObject(exchange.getBody());
            return jsonObject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public  String getAccessToken(){
        String access_token = null;
        try{
            MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
            postParameters.add(configClientController.getGRANT_TYPE(), configClientController.getGRANT_TYPE_VALUE());
            postParameters.add(configClientController.getCLIENT_ID(), configClientController.getCLIENT_ID_VALUE());
            postParameters.add(configClientController.getCLIENT_SECRET(), configClientController.getCLIENT_SECRET_VALUE());
            RequestEntity<MultiValueMap<String, Object>> requestEntity = RequestEntity.post(new URL(configClientController.getGET_TOKEN_URL()).toURI())
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .body(postParameters);
            ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
            RequestTokenPayload payload = JSONObject.parseObject(exchange.getBody(),RequestTokenPayload.class);
            if(!payload.isOk()){
                throw new RequestInferfaceException(payload.getStatus().getText());
            }
            access_token = payload.getCustom().getAccessToken();
            //CommonCacheUtil.getCache(CacheCodeEnum.INNERWEB.getValue()).add(configClientController.getKEY_AUDIT_ITEM_API_TOKEN(), access_token, 20L);
        }catch (Exception e){
            e.printStackTrace();
        }
        return access_token;
    }


    private  Map<String, Object> requestParamsDept(String accessToken, String deptCode, Long timestamp) {
        Map<String, Object> map = new HashMap<>();

        map.put(configClientController.getACCESS_TOKEN(),accessToken);
        Map<String, Object> param = new HashMap<>();
        param.put(configClientController.getDEPT_CODE(), deptCode);
        if(timestamp != null){
            param.put("TIME_STAMP",timestamp);
        }
        param.put("ITEM_LIMIT",30);//2020-12-31 新接口最大只能100
        param.put("IS_HISTORY", "0");
        map.put("param",param);
        return map;
    }
}
