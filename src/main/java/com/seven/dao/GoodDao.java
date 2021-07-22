package com.seven.dao;

import com.seven.domain.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodDao {
    /*分页查询商品信息*/
    public List<Good> getAllGood();

    /*根据名字查询商品*/
    public List<Good> searchGood(@Param("name") String name);

    /*根据商品id获取商品信息*/
    public Good searchGoodById(@Param("id") int id);

    /*添加商品*/
    public int addGood(String name, int amount, int price, String description, String image);

    /*修改商品数据*/
    /*public int updateGood(int amount, int price, String description);*/

    /*修改商品库存*/
    public int updateGoodAmount(int id, int amount);

    /*删除商品*/
    public int deleteGood(List<Integer> ids);
}
