package com.seven.dao;

import com.seven.domain.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDao {
  /*查询所有订单*/
  public List<Order> getAllOrder();

  /*添加订单*/
  public int addOrder(int id, int user_id, int good_id, String uesrName, String goodName, int amount, int totalPrice, String phone, String address, String remark, Timestamp createTime);

  /*查询某个订单*/
  public Order getOrder();

//  /*删除某个订单*/
//  public boolean deleteOrderById(int id);

  /*批量删除订单*/
  public int deleteOrderByIds(List<Integer> ids);
}
