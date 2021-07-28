package top.jingjianqian.springcloudalibaba.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import top.jingjianqian.common.springcloud.entities.Payment;
import top.jingjianqian.springcloudalibaba.dao.PaymentDao;
import top.jingjianqian.springcloudalibaba.service.PaymentService;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public Payment getPaymentById(@Param("id") Long  id){
        return  paymentDao.getPaymentById(id);
    };
}
