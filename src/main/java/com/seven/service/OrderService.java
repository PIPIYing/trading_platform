package com.seven.service;

import com.seven.dao.OrderDao;
import com.seven.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderService{
  @Autowired
  OrderDao orderDao;

  /*查询所有订单*/
  public List<Order> getAllOrder(){
    return orderDao.getAllOrder();
  };

  /*添加订单*/
  public Boolean addOrder(Order order) {
    int id = 1;
    Timestamp createTime = new Timestamp(System.currentTimeMillis());
    int addResult = orderDao.addOrder(id,order.getUesr_id(),order.getGood_id(),order.getUserName(),order.getGoodName(),order.getAmount(),order.getTotalPrice(),order.getPhone(),order.getAddress(),order.getRemark(),createTime);
    /*根据返回的整数判断是否添加成功*/
    if(addResult>0)
    {
      /*返回true表示添加成功*/
      return true;
    }
    else
    {
      return false;
    }
  }

  /*删除订单*/
  public Boolean deleteOrder(List<Integer> ids){
    int deleteResult = orderDao.deleteOrderByIds(ids);
    if(deleteResult>0) return true;
    else return false;
  }
}
