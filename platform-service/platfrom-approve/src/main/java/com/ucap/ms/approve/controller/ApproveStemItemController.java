package com.ucap.ms.approve.controller;


import com.ucap.ms.base.data.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(consumes = "application/json;charset=UTF-8",
        produces = "application/json;charset=UTF-8",
        protocols = "http",
        value = "项目事项库管理",
        tags="项目事项库管理接口")
@RequestMapping("/approveStepItem")
public class ApproveStemItemController {


    @ApiIgnore
    @ApiOperation(value = "根据编码查询事项接口信息")
    @ApiImplicitParam(name = "deptCode", value = "实施主体编码",required = true, dataType = "String")
    @RequestMapping(value = "auditItemsNotifyDept" , method = RequestMethod.POST)
    public ResultModel auditItemsNotifyDept(@RequestParam String depeCode){

        return null;
    }
}
