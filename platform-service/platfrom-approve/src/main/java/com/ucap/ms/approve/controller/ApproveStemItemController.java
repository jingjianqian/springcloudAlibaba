package com.ucap.ms.approve.controller;


import com.alibaba.fastjson.JSONObject;
import com.ucap.ms.approve.vo.ApproveAuditItemApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/approveStepItem")
public class ApproveStemItemController {

    @Resource
    private ApproveAuditItemApi approveAuditItemApi;

    @RequestMapping(value = "/auditItemsNotifyDept" , method = RequestMethod.POST)
    public JSONObject auditItemsNotifyDept(@RequestParam String deptCode){
        return JSONObject.parseObject(approveAuditItemApi.getAccessToken(Boolean.TRUE).toString());
    }
}
