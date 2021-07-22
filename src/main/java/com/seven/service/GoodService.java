package com.seven.service;

import com.github.pagehelper.PageHelper;
import com.seven.dao.GoodDao;
import com.seven.domain.Good;
import com.seven.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName GoodService
 * @Description TODO
 * @Author ying
 * @Date 2021/7/22 11:24
 **/

@Service
public class GoodService {
    @Autowired
    GoodDao goodDao;

    /*分页查询商品信息*/
    public List<Good> getAllGood(int page, int limit)
    {
        System.out.println("进入查询所有商品service——————————————————");
        PageHelper.startPage(page,limit);
        return goodDao.getAllGood();
    }

    /*根据名字查询商品*/
    public List<Good> searchGood(String name, int page, int limit)
    {
        System.out.println("进入搜索商品service——————————————————");
        PageHelper.startPage(page, limit);
        return goodDao.searchGood(name);
    }

    /*根据商品id获取商品信息*/
    public Good searchGoodById(int id)
    {
        System.out.println("进入搜索商品byID service——————————————————");
        return goodDao.searchGoodById(id);
    }

    /*添加商品*/
    public Boolean addGood(Good good)
    {
        System.out.println("进入添加商品service—————————————————");
        int addResult = goodDao.addGood(good.getName(), good.getAmount(), good.getPrice(), good.getDescription(), good.getImage());

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

    /*修改商品库存*/
    public Boolean updateGoodAmount(int id, int amount)
    {
        int updateReuslt = goodDao.updateGoodAmount(id, amount);

        if(updateReuslt>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    /*删除商品*/
    public Boolean deleteGood(List<Integer> ids)
    {
        int deletResult = goodDao.deleteGood(ids);

        if(deletResult>0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
