package top.jingjianqian.springcloud.providerPayment8002.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import top.jingjianqian.common.springcloud.entities.Payment;
import top.jingjianqian.springcloud.providerPayment8002.dao.PaymentDao;
import top.jingjianqian.springcloud.providerPayment8002.service.PaymentService;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return  paymentDao.create(payment);
    };
    public Payment getPaymentById(@Param("id") Long  id){
        return  paymentDao.getPaymentById(id);
    };
}
