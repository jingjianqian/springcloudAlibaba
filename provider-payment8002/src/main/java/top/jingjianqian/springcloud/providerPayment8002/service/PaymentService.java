package top.jingjianqian.springcloud.providerPayment8002.service;

import org.apache.ibatis.annotations.Param;
import top.jingjianqian.common.springcloud.entities.Payment;


public interface PaymentService {
    int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long  id);
}
