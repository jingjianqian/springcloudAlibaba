package top.jingjianqian.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.jingjianqian.common.springcloud.entities.CommonResult;
import top.jingjianqian.common.springcloud.entities.Payment;
import top.jingjianqian.springcloud.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result > 0){
            return new CommonResult(200,serverPort+"端口插入数据成功！",result);
        }else{
            return new CommonResult(444,serverPort+"端口插入数据失败！",null);
        }
    };
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
      Payment payment = paymentService.getPaymentById(id);

      if(payment!=null){
          return new CommonResult(200,serverPort+"端口查询数据成功！",payment);
      }else{
          return new CommonResult(400,serverPort+"端口查询数据失败！",null);
      }
    };

}
