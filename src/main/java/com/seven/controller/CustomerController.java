package com.seven.controller;

import com.github.pagehelper.PageInfo;
import com.seven.domain.Good;
import com.seven.domain.Order;
import com.seven.domain.Record;
import com.seven.service.GoodService;
import com.seven.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    GoodService goodService;

    @Autowired
    OrderService orderService;

    /*客户访问商品页*/
    @RequestMapping("/goodShow")
    public String goodShow()
    {
        return "toCustomer/goodShow";
    }

    /*客户访问页*/
    @RequestMapping("/noticeShow")
    public String noticeShow()
    {
        return "toCustomer/noticeIndex";
    }

    /*客户访问购买记录页*/
    @RequestMapping("/buyRecord")
    public String buyRecord()
    {
        return "toCustomer/buyRecord";
    }

    /*客户访问页*/
    @RequestMapping("/buyRecordGet")
    @ResponseBody
    public Map<String,Object> buyRecordGet(int userId,int page,int limit)
    {
        Map<String,Object> map = new HashMap<>();
        System.out.println("进入查询订单controller——————————————————");

        List<Order> orderList = orderService.getOrder(userId, page, limit);
        System.out.println(orderList);
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }


    /*客户访问填写订单页*/
    @RequestMapping("/orderDetail")
    public String orderDetail(int id, Model model)
    {
        System.out.println("进入订单IDcontroller——————————————————");
        if(id==0)
        {
            id=0;
        }

        System.out.println("id: " + id);
        Good good = goodService.searchGoodById(id);
        System.out.println(good.getName() + good.getAmount());
        model.addAttribute("good", good);
        return "toCustomer/orderDetail";
    }

}
