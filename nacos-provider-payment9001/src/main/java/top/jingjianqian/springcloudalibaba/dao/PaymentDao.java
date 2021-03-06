package top.jingjianqian.springcloudalibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jingjianqian.common.springcloud.entities.Payment;

@Mapper
public interface PaymentDao {

    public  int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long  id);
}
