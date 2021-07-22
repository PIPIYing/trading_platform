package com.seven.controller;

import com.seven.domain.Good;
import com.seven.domain.Record;
import com.seven.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    GoodService goodService;

    /*客户访问商品页*/
    @RequestMapping("/goodShow")
    public String goodShow()
    {
        return "toCustomer/goodShow";
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

    /*生成订单的库存处理*/
    @RequestMapping("/buy")
    public void buy(Good good)
    {
        System.out.println("进入生成订单controller——————————————————");
        System.out.println(good.getAmount());

        /*逻辑： 根据goodid获取good的amount
        Good good = goodService.searchGoodById(id);
        int amount = good.getAmount();
        int id = good.getId();
        ，减去现在的amount
        int newAmount = amount - amount(现)
        传递给 goodid amount(现)
        * boolean updateResult = goodService.updateGoodAmount(id, newAmount);
        * 实现修改库存
        HashMap<String,Object> map = new HashMap<>();
        if(updateResult==true)
        {
            再新增订单
        }
        else {
            map.put("code",-1);
            return map;
        }
        * */
    }
}
