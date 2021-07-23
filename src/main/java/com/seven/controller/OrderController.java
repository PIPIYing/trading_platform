package com.seven.controller;

import com.github.pagehelper.PageInfo;
import com.seven.domain.Good;
import com.seven.domain.Order;
import com.seven.service.GoodService;
import com.seven.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class OrderController {
  @Autowired
  OrderService orderService;

  @Autowired
  GoodService goodService;

  /*获取所有订单*/
  @RequestMapping("/orderIndex")
  public String orderIndex()
  {
    return "order/orderIndex";
  }

  @RequestMapping("/orderAll")
  @ResponseBody
  public Map<String,Object> orderAll(int page,int limit)
  {
    Map<String,Object> map = new HashMap<>();
    System.out.println("进入查询所有订单controller——————————————————");
    List<Order> orderList = orderService.getAllOrder(page,limit);
    System.out.println(orderList);
    PageInfo<Order> pageInfo = new PageInfo<>(orderList);

    map.put("data",pageInfo.getList());
    map.put("count",pageInfo.getTotal());
    map.put("code",0);
    map.put("msg","");
    return map;
  }


  /*添加订单*/
  @RequestMapping("/addOrder")
  @ResponseBody
  public Map<String,Object> addOrder(Order order) {
    System.out.println("进入添加订单controller——————————————————");
    System.out.println("添加订单: " + order);
    System.out.println("添加订单的goodid: " + order.getGoodId());
    System.out.println("添加订单的goodid: " + order.getGoodName());
    Good good = goodService.searchGoodById(order.getGoodId());
    int amount = good.getAmount();
    int id = good.getId();
    int newAmount = amount - order.getAmount();
    boolean updateResult = goodService.updateGoodAmount(id, newAmount);
    HashMap<String,Object> map = new HashMap<>();
    if(updateResult==true)
    {
      boolean addResult = orderService.addOrder(order);
      if(addResult==true)
      {
        map.put("code",0);
      }
      else {
        map.put("code",-1);
      }
    }
    else {
      map.put("code",-1);
    }
    return map;
  }

  /*根据id删除订单*/
  @RequestMapping("/deleteOrder")
  @ResponseBody
  public Map<String,Object> deleteOrders(String ids)
  {
    System.out.println(ids);
    System.out.println("进入删除订单的控制器");
    HashMap<String,Object> map = new HashMap<>();

    /*需要把ids转化为集合*/
    List<String> idlist = Arrays.asList(ids.split(","));
    List<Integer> idList = new ArrayList<>();

    /*需要把String集合转化为整数集合*/
    for (String id:idlist)
    {
      idList.add(Integer.parseInt(id));
    }

    System.out.println(idList);
    boolean deleteresult = orderService.deleteOrder(idList);

    if(deleteresult==true)
    {
      map.put("code",0);
    }
    else {
      map.put("code",-1);
    }
    return map;
  }

  //查询订单
  @RequestMapping("/searchOrder")
  @ResponseBody
  public Map<String,Object> searchOrder(int order_id, int page, int limit)
  {
    Map<String,Object> map = new HashMap<>();

    List<Order> orderList = orderService.searchOrder(order_id,page,limit);
    PageInfo<Order> pageInfo2 = new PageInfo<>(orderList);
    map.put("data",pageInfo2.getList());
    map.put("count",pageInfo2.getTotal());
    map.put("code",0);
    map.put("msg","");
    return map;
  }
}
