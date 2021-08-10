package com.ucap.ms.approve.controller;


import com.alibaba.fastjson.JSONObject;
import com.ucap.ms.approve.api.vo.ApproveAuditItemApi;
import com.ucap.ms.base.enums.CacheCodeEnum;
import com.ucap.ms.cache.aspect.CacheAspectAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/approveStepItem")
public class ApproveStemItemController {


    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ApproveAuditItemApi approveAuditItemApi;


    @CacheAspectAnnotation(dataKey = "auditItemsNotifyDept", cacheCode = CacheCodeEnum.INNERWEB)
    @RequestMapping(value = "/auditItemsNotifyDept" , method = RequestMethod.POST)
    public JSONObject auditItemsNotifyDept(@RequestParam String deptCode){
        return approveAuditItemApi.getAuditItemsLimit(deptCode);
    }
}
