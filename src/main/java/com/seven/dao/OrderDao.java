package com.seven.dao;

import com.seven.domain.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDao {
  /*查询所有订单*/
  public List<Order> getAllOrder();

  /*根据userid查询所有订单*/
  public List<Order> getOrder(int userId);

  /*添加订单*/
  public int addOrder(int userId, int goodId, String uesrName, String goodName, int amount, int totalPrice, String phone, String address, String remark, Timestamp createTime);

  /*查询订单*/
  public List<Order> searchOrder(int id);

  /*批量删除订单*/
  public int deleteOrderByIds(List<Integer> ids);
}
