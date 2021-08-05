package com.ucap.ms.approve.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApproveAuditItemApi {

    private static Logger log = LoggerFactory.getLogger(ApproveAuditItemApi.class);
    /**
     * from nacos config
     */
    @Value("${test}")
    private String GET_TOKEN_URL;
    @Value("${}")
    private  String GET_DEPTAUDITITEM_URL;

}
