package com.ucap.ms.approve.vo;

import com.ucap.ms.approve.config.ConfigClientController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ApproveAuditItemApi {

    private static Logger log = LoggerFactory.getLogger(ApproveAuditItemApi.class);


    @Resource
    private ConfigClientController configClientController;



    /**
     * 获取accessToken
     * @return
     * @param refreshToken
     */
    public String getAccessToken(Boolean refreshToken){
        try {

            return configClientController.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return "{'status':'11111'}";
        }
    }
}
