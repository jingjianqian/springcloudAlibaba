package top.jingjianqian.springcloud.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import top.jingjianqian.common.springcloud.entities.Payment;
import top.jingjianqian.springcloud.dao.PaymentDao;
import top.jingjianqian.springcloud.service.PaymentService;

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
