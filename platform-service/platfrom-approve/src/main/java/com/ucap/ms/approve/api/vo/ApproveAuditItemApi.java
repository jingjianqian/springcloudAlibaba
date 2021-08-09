package com.ucap.ms.approve.api.vo;

import com.alibaba.fastjson.JSONObject;
import com.ucap.ms.approve.config.ConfigClientController;
import com.ucap.ms.base.enums.CacheCodeEnum;
import com.ucap.ms.cache.util.CommonCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Component
public class ApproveAuditItemApi {

    private static Logger log = LoggerFactory.getLogger(ApproveAuditItemApi.class);


    @Resource
    private ConfigClientController configClientController;

    @Resource
    ApproveAuditItemsSourceApi approveAuditItemsSourceApi;

    private static final String GRANT_TYPE = "grant_type";
    private static final String GRANT_TYPE_VALUE = "client_credentials";
    private static final String CLIENT_ID = "client_id";
    //private static final String CLIENT_ID_VALUE = "c185efc2-3d71-41c7-89f4-efff1a156428";
    private static final String CLIENT_ID_VALUE = "c185efc2-3d71-41c7-89f4-efff1a156428";
    private static final String CLIENT_SECRET = "client_secret";
    //private static final String CLIENT_SECRET_VALUE = "f6817738-cb3a-4a3b-9792-cd7edc9d62a3";
    private static final String CLIENT_SECRET_VALUE = "f6817738-cb3a-4a3b-9792-cd7edc9d62a3";
    private final String KEY_AUDIT_ITEM_API_TOKEN = "audit_item_api_token";

    @Resource
    RestTemplate restTemplate;

    /**
     * 获取accessToken
     * @return
     * @param refreshToken
     */
    public String getAccessToken(Boolean refreshToken){
        try {
            if(!Boolean.TRUE.equals(refreshToken)){
                String accessToken  = (String) CommonCacheUtil.getCache(CacheCodeEnum.INNERWEB.getValue()).get(KEY_AUDIT_ITEM_API_TOKEN);
                if(accessToken !=null){
                    return accessToken;
                }
            }
//            Map<String, Object> map = requestParamsDept(accessToken, deptCode, timestamp);
//            //String accessToken  = (String) CommonCacheUtil.getCache(CacheCodeEnum.INNERWEB.getValue()).get(KEY_AUDIT_ITEM_API_TOKEN);
//            MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
//            postParameters.add(GRANT_TYPE, GRANT_TYPE_VALUE);
//            postParameters.add(CLIENT_ID, CLIENT_ID_VALUE);
//            postParameters.add(CLIENT_SECRET, CLIENT_SECRET_VALUE);
//
//            RequestEntity<Map<String, Object>> requestEntity = RequestEntity.post(new URL(configClientController.getGET_DEPTAUDITITEM_URL()).toURI())
//                    .contentType(MediaType.APPLICATION_JSON_UTF8)
//                    .accept(MediaType.APPLICATION_JSON_UTF8)
//                    .body(map);
//
//            ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);

            return configClientController.toString();




        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return "{'status':'11111'}";
        }
    }



    public JSONObject getAuditItemsLimit(String deptCode){
        //String accessToken = approveAuditItemsSourceApi.getAccessToken();
        JSONObject returnJson = approveAuditItemsSourceApi.getAuditItemsDeptLimit(deptCode,0L);
        return  returnJson;
    }



}
