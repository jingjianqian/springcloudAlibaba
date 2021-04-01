package top.jingjianqian.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jingjianqian.springcloud.entities.CommonResult;
import top.jingjianqian.springcloud.entities.Payment;
import top.jingjianqian.springcloud.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @RequestMapping("/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        if(result > 0){
            return new CommonResult(200,"插入数据成功！",result);
        }else{
            return new CommonResult(444,"插入数据失败！",null);
        }
    };
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
      Payment payment = paymentService.getPaymentById(id);

      if(payment!=null){
          return new CommonResult(200,"查询数据成功！",payment);
      }else{
          return new CommonResult(400,"查询数据失败！",null);
      }
    };

}
