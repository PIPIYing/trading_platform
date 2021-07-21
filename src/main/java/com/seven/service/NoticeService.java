package com.seven.service;

import com.seven.dao.NoticeDao;
import com.seven.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
  @Autowired
  NoticeDao noticeDao;

  /*查询所有公告*/
  public List<Notice> getAllNotice(){
    return noticeDao.getAllNotice();
  };

  /*添加公告*/
  public Boolean addNotice(Notice notice){
    int addResult = noticeDao.addNotice(notice.getId(),notice.getUser_id(),notice.getAuthorName(),notice.getTitle(),notice.getContent());

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

  /*修改公告*/
  public Boolean updateNotice(int id,String title,String content) {
    int updateResult = noticeDao.updateNotice(id,title,content);
    if(updateResult>0)
    {
      return true;
    }
    else {
      return false;
    }
  }
}
