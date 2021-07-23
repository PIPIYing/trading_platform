package com.seven.controller;

import com.github.pagehelper.PageInfo;
import com.seven.domain.Notice;
import com.seven.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class NoticeController {
  @Autowired
  NoticeService noticeService;

  /*获取所有订单*/
  @RequestMapping("/noticeIndex")
  public String noticeIndex()
  {
    return "notice/noticeIndex";
  }

  @RequestMapping("/noticeAll")
  @ResponseBody
  public Map<String,Object> noticeAll(int page,int limit)
  {
    Map<String,Object> map = new HashMap<>();

    List<Notice> noticeList = noticeService.getAllNotice(page, limit);
    PageInfo<Notice> pageInfo = new PageInfo<>(noticeList);
    System.out.println(noticeList);
    map.put("data",pageInfo.getList());
    map.put("count",pageInfo.getTotal());
    map.put("code",0);
    map.put("msg","");
    return map;
  }

  /*访问添加公告页面*/
  @RequestMapping("/addNotice")
      public String toAddNotice(){
     return "notice/noticeAdd";
  }

  /*添加公告操作*/
  @RequestMapping("/addNoticeSubmit")
  @ResponseBody
  public Map<String,Object> addNotice(Notice notice) {
    HashMap<String,Object> map = new HashMap<>();
    boolean addResult = noticeService.addNotice(notice);

    if(addResult==true)
    {
      map.put("code",0);
    }
    else {
      map.put("code",-1);
    }
    return map;
  }

  /*根据id删除公告*/
  @RequestMapping("/deleteNotice")
  @ResponseBody
  public Map<String,Object> deleteNotice(String ids)
  {
    System.out.println(ids);
    System.out.println("进入删除公告的控制器");
    HashMap<String,Object> map = new HashMap<>();

    /*需要把ids转化为集合*/
    List<String> idlist = Arrays.asList(ids.split(","));
    List<Integer> idList = new ArrayList<>();

    /*需要把String集合转化为整数集合*/
    for (String id:idlist)
    {
      idList.add(Integer.parseInt(id));
    }

    System.out.println(idList);
    boolean deleteresult = noticeService.deleteNotice(idList);

    if(deleteresult==true)
    {
      map.put("code",0);
    }
    else {
      map.put("code",-1);
    }
    return map;
  }

  /*访问修改公告*/
  @RequestMapping("/updateNotice")
  public String queryNotice(String id, Model model){
    model.addAttribute("id",id);
    return "notice/noticeUpdate";
  }

  //查询
  @RequestMapping("/searchNotice")
  @ResponseBody
  public Map<String,Object> searchOrder(int id, int page, int limit)
  {
    Map<String,Object> map = new HashMap<>();

    List<Notice> noticeList = noticeService.searchNotice(id,page,limit);
    PageInfo<Notice> pageInfo2 = new PageInfo<>(noticeList);
    map.put("data",pageInfo2.getList());
    map.put("count",pageInfo2.getTotal());
    map.put("code",0);
    map.put("msg","");
    return map;
  }

  /*修改公告*/
  @RequestMapping("/updateNoticeSubmit")
  @ResponseBody
  public Map<String,Object> updateNotice(int id,String title,String content) {
    boolean updateResult = noticeService.updateNotice(id,title,content);
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
}
