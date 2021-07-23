package com.seven.service;

import com.github.pagehelper.PageHelper;
import com.seven.dao.NoticeDao;
import com.seven.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class NoticeService {
  @Autowired
  NoticeDao noticeDao;

  /*查询所有公告*/
  public List<Notice> getAllNotice(int page, int limit){
    System.out.println("进入查询所有订单service——————————————————");
    /*计算当前页是从第几条数据开始查询*/
    PageHelper.startPage(page,limit);
    return noticeDao.getAllNotice();
  };

  /*添加公告*/
  public Boolean addNotice(Notice notice){
    Timestamp createTime = new Timestamp(System.currentTimeMillis());
    int addResult = noticeDao.addNotice(notice.getUserId(),notice.getAuthorName(),notice.getTitle(),notice.getContent(),createTime);

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

  /*删除公告*/
  public Boolean deleteNotice(List<Integer> ids){
    int deleteResult = noticeDao.deleteNotice(ids);
    if(deleteResult>0)
    {
      return true;
    }
    else {
      return false;
    }
  }

  /*搜索订单*/
  public List<Notice> searchNotice(int id, int page, int limit)
  {
    PageHelper.startPage(page, limit);
    return noticeDao.searchNotice(id);
  }

  /*修改公告*/
  public Boolean updateNotice(int id,String title,String content) {
    Timestamp newestTime = new Timestamp(System.currentTimeMillis());
    int updateResult = noticeDao.updateNotice(id,title,content,newestTime);
    if(updateResult>0)
    {
      return true;
    }
    else {
      return false;
    }
  }
}
