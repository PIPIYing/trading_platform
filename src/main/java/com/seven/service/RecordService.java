package com.seven.service;

import com.seven.dao.RecordDao;
import com.seven.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName RecordService
 * @Description TODO
 * @Author ying
 * @Date 2021/7/22 17:59
 **/

@Service
public class RecordService {

    @Autowired
    RecordDao recordDao;

    /*查询记录*/
    public List<Record> getAllRecord(int userId)
    {
        System.out.println("进入查询记录service——————————————————");
        return recordDao.getAllRecord(userId);
    }

    /*更新记录*/
    public Boolean updateRecord(int userId, String goodName, String createTime)
    {
        System.out.println("进入更新记录service——————————————————");
        int updateResult = recordDao.updateRecord(userId, goodName, createTime);
        if(updateResult>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    /*新增记录*/
    public Boolean addRecord(int userId, String goodName, String createTime)
    {
        System.out.println("进入新增记录service——————————————————");

        int addResult = recordDao.addRecord(userId, goodName, createTime);
        if(addResult>0)
        {
            return true;
        }
        else {
            return false;
        }

    }

}
