package top.jingjianqian.springcloudalibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import top.jingjianqian.common.springcloud.entities.CommonResult;
import top.jingjianqian.common.springcloud.entities.Payment;
import top.jingjianqian.springcloudalibaba.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


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
