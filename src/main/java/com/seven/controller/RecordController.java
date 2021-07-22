package com.seven.controller;

import com.seven.domain.Record;
import com.seven.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RecordController
 * @Description TODO
 * @Author ying
 * @Date 2021/7/22 17:59
 **/

@Controller
public class RecordController {

    @Autowired
    RecordService recordService;

    /*访问浏览记录*/
    @RequestMapping("/recordIndex")
    public String recordIndex()
    {
        return "record/recordIndex";
    }

    /*查询记录*/
    @RequestMapping("/recordAll")
    @ResponseBody
    public List<Record> recordAll(int userId)
    {
        System.out.println("进入查询记录controller——————————————————");

        List<Record> recordList = recordService.getAllRecord(userId);
        return recordList;
    }

    /*查询记录by userId and goodname*/
    /*@RequestMapping("/recordSearch")
    @ResponseBody
    public Boolean recordSearch(int userId, String goodName)
    {
        System.out.println("进入查询记录by userId and goodname controller——————————————————");

        List<Record> recordList = recordService.getRecord(userId, goodName);
        System.out.println(recordList);
        if(recordList.size() == 0) {
            return false;
        }else {
            return true;
        }
    }*/

    /*更新记录*/
    @RequestMapping("/updateRecord")
    @ResponseBody
    public Map<String,Object> updateRecord(int userId, String goodName, String createTime)
    {
        System.out.println("进入更新记录controller——————————————————");

        /*Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createTime = df.format(date);*/

        boolean updateResult = recordService.updateRecord(userId, goodName, createTime);

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

    /*增加记录*/
    @RequestMapping("/addRecord")
    @ResponseBody
    public Map<String,Object> addRecord(int userId, String goodName, String createTime)
    {
        System.out.println("进入增加记录controller——————————————————");

        HashMap<String,Object> map = new HashMap<>();

        List<Record> recordList = recordService.getRecord(userId, goodName);
        System.out.println(recordList);
        if(recordList.size() != 0) {
            boolean addResult = recordService.addRecord(userId, goodName, createTime);

            if(addResult==true)
            {
                map.put("code",0);
            }
            else {
                map.put("code",-1);
            }
        }else {
            map.put("code",-1);
        }
        return map;
    }
}
