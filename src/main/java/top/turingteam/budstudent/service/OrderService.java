package top.turingteam.budstudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.turingteam.budstudent.pojo.entity.Advertisement;
import top.turingteam.budstudent.pojo.entity.Order;

import java.util.List;

/**
* @author howe
*/
public interface OrderService extends IService<Order> {
    List<Order> getOrderList();

    void createOrder(Advertisement ad);
}
