package com.seven.dao;

import com.seven.domain.Record;
import java.util.Date;
import java.util.List;

public interface RecordDao {
    /*新增浏览记录*/
    public int addRecord(int userId, String goodName, String createTime);

    /*更新浏览记录*/
    public int updateRecord(int userId, String goodName, String createTime);

    /*查询个人浏览记录*/
    public List<Record> getAllRecord(int userId);

    /*查询浏览记录是否存在*/
    public List<Record> getRecord(int userId, String goodName);
}
