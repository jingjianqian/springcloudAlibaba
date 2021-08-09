package com.ucap.ms.approve.controller;


import com.alibaba.fastjson.JSONObject;
import com.ucap.ms.approve.api.vo.ApproveAuditItemApi;
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

    @RequestMapping(value = "/auditItemsNotifyDept" , method = RequestMethod.POST)
    public JSONObject auditItemsNotifyDept(@RequestParam String deptCode){
        System.out.println(restTemplate.toString());
        return approveAuditItemApi.getAuditItemsLimit(deptCode);
    }
}
