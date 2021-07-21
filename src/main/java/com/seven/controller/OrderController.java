package com.seven.controller;

import com.seven.domain.Order;
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

  /*获取所有订单*/
  @RequestMapping("/order")
  public String toOrder(Model model) {
    List<Order> orders = orderService.getAllOrder();
    model.addAttribute("orders",orders);
    return "order";
  }

  /*添加订单*/
  @RequestMapping("/addOrder")
  @ResponseBody
  public Map<String,Object> addOrder(Order order) {
    HashMap<String,Object> map = new HashMap<>();
    boolean addResult = orderService.addOrder(order);
    if(addResult==true)
    {
      map.put("code",0);
    }
    else {
      map.put("code",-1);
    }
    return map;
  }

  /*根据id删除订单*/
  @RequestMapping("/deleteOrderByIds")
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
}
