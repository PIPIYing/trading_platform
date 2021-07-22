package com.seven.controller;

import com.github.pagehelper.PageInfo;
import com.seven.domain.Good;
import com.seven.domain.Record;
import com.seven.service.GoodService;
import com.seven.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @ClassName GoodController
 * @Description TODO
 * @Author ying
 * @Date 2021/7/22 11:23
 **/

@Controller
public class GoodController {

    @Autowired
    GoodService goodService;

    @Autowired
    RecordService recordService;

    /*访问商品管理页*/
    @RequestMapping("/goodIndex")
    public String goodIndex()
    {
        return "good/goodIndex";
    }

    /*访问添加商品页*/
    @RequestMapping("/goodAdd")
    public String goodAdd()
    {
        return "good/goodAdd";
    }

    /*访问修改商品页*/
    @RequestMapping("/goodUpdate")
    public String goodUpdate(String id, Model model)
    {
        model.addAttribute("id",id);
        return "good/goodUpdate";
    }

    /*访问商品详情页*/
    @RequestMapping("/goodDetail")
    public String goodDetail(int id, Model model)
    {
        System.out.println("进入搜索商品IDcontroller——————————————————");
        if(id==0)
        {
            id=0;
        }

        Good good = goodService.searchGoodById(id);
        System.out.println(good.getName() + good.getAmount());
        model.addAttribute("good", good);

        return "good/goodDetail";
    }

    /*查询所有商品*/
    @RequestMapping("/goodAll")
    @ResponseBody
    public Map<String,Object> goodAll(int page, int limit)
    {
        System.out.println("进入查询所有商品controller——————————————————");
        Map<String,Object> map = new HashMap<>();

        List<Good> goodList1 = goodService.getAllGood(page,limit);
        PageInfo<Good> pageInfo1 = new PageInfo<>(goodList1);
        map.put("data",pageInfo1.getList());
        map.put("count",pageInfo1.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    /*搜索商品（根据name）*/
    @RequestMapping("/goodSearch")
    @ResponseBody
    public Map<String,Object> goodSearch(String name, int page, int limit)
    {
        System.out.println("进入搜索商品controller——————————————————");
        System.out.println("name: " + name);
        Map<String,Object> map = new HashMap<>();
        if(name==null||name.trim().length()==0)
        {
            name=null;
        }

        List<Good> goodList2 = goodService.searchGood(name, page, limit);
        PageInfo<Good> pageInfo2 = new PageInfo<>(goodList2);
        map.put("data",pageInfo2.getList());
        map.put("count",pageInfo2.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    /*添加商品操作*/
    @RequestMapping("/goodAddSumbit")
    @ResponseBody
    public Map<String,Object> goodAddSumbit(Good good)
    {
        System.out.println("进入添加商品controller——————————————————");
        HashMap<String,Object> map = new HashMap<>();
        boolean addResult = goodService.addGood(good);

        if(addResult==true)
        {
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }

    /*修改商品库存*/
    @RequestMapping("/goodAmountUpdateSumbit")
    @ResponseBody
    public Map<String,Object> goodAmountUpdateSumbit(int id, int amount)
    {
        System.out.println("进入修改商品库存controller——————————————————");
        boolean updateResult = goodService.updateGoodAmount(id, amount);

        HashMap<String,Object> map = new HashMap<>();
        if(updateResult==true)
        {
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }

    /*删除商品*/
    @RequestMapping("/goodDelete")
    @ResponseBody
    public Map<String,Object> goodDelete(String ids)
    {
        System.out.println(ids);
        System.out.println("进入删除商品controller————————————————");
        HashMap<String,Object> map = new HashMap<>();

        List<String> idlist = Arrays.asList(ids.split(","));
        List<Integer> idList = new ArrayList<>();

        for (String id:idlist)
        {
            idList.add(Integer.parseInt(id));
        }

        boolean deleteResult = goodService.deleteGood(idList);

        if(deleteResult==true)
        {
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }
}
