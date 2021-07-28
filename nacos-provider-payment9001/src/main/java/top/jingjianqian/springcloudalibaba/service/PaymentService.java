package top.jingjianqian.springcloudalibaba.service;

import org.apache.ibatis.annotations.Param;
import top.jingjianqian.common.springcloud.entities.Payment;


public interface PaymentService {

    public Payment getPaymentById(@Param("id") Long  id);
}
