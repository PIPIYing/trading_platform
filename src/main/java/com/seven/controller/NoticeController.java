package com.seven.controller;

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

  /*获取所有公告*/
  @RequestMapping("/notice")
  public String toNotice(Model model) {
    List<Notice> notices = noticeService.getAllNotice();
    model.addAttribute("notices",notices);
    return "notice";
  }

//  /*访问添加公告页面*/
//  @RequestMapping("/addNotice")
//  public String toAddNotice(){
//    return "notice/addNotice";
//  }

  /*添加公告操作*/
  @RequestMapping("/addNoticeSubmit")
  @ResponseBody
  public Map<String,Object> addNotice(Notice notice) {
    HashMap<String,Object> map = new HashMap<>();
    /*调用NoticeService里面的添加管理员的业务*/
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
  @RequestMapping("/deleteNoticeByIds")
  @ResponseBody
  public Map<String,Object> deleteNootices(String ids)
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

//  /*访问修改公告*/
//  @RequestMapping("/queryNotice")
//  public String queryNotice(String id, Model model){
//    model.addAttribute("id",id);
//    return "";
//  }

  /*修改公告*/
  @RequestMapping("/")
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
