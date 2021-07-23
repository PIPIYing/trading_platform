package com.seven.dao;

import com.seven.domain.Notice;

import java.sql.Timestamp;
import java.util.List;

public interface NoticeDao {
  /*查询所有公告*/
  public List<Notice> getAllNotice();

  /*添加公告*/
  public int addNotice(int userId, String authorName, String title, String content, Timestamp newestTime);

  /*删除公告*/
  public int deleteNotice(List<Integer> ids);

  /*查询公告*/
  public List<Notice> searchNotice(int id);

  /*修改公告*/
  public int updateNotice(int id, String title, String content, Timestamp newestTime);
}
