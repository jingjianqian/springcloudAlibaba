package top.jingjianqian.springcloud.dao;

import org.apache.ibatis.annotations.Param;
import top.jingjianqian.springcloud.entities.Payment;

public interface PaymentDao {
    int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long  id);
}
