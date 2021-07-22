package com.seven.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Author ying
 * @Date 2021/7/22 14:33
 **/

@Controller
public class UploadController {
    @RequestMapping("/toUpload")
    public String toUpload()
    {
        System.out.println("进入上传文件controller————————————————");
        return "upload";
    }


    @RequestMapping("/upload")
    public String upload(Model model, MultipartFile file, HttpServletRequest request)
    {
        System.out.println(file);
        if(file!=null)
        {
            //先指定上传路径，获取对应物理路径
            String targetDirectory = request.getSession().getServletContext().getRealPath("/uploadFiles");
            String temFileName = file.getOriginalFilename();

            int dot = temFileName.lastIndexOf('.');
            String ext = "";  //文件后缀名
            if ((dot > -1) && (dot < (temFileName.length() - 1))) {
                ext = temFileName.substring(dot + 1);
            }
            // 其他文件格式不处理
            if ("png".equalsIgnoreCase(ext) || "jpg".equalsIgnoreCase(ext) || "gif".equalsIgnoreCase(ext)) {
                // 重命名上传的文件名
                String targetFileName = renameFileName(temFileName);
                // 保存的新文件
                File target = new File(targetDirectory, targetFileName);
                String path = target.getAbsolutePath();
                System.out.println(path);
                try {
                    // 保存文件
                    FileUtils.copyInputStreamToFile(file.getInputStream(), target);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("上传成功");
                model.addAttribute("tips","上传成功");
                return path;
            }
            System.out.println("选择的文件不是图片");
            model.addAttribute("tips","选择的文件不是图片，请重新选择！");
            return null;
        }
        else {
            System.out.println("未选择文件");
            model.addAttribute("tips","未选择文件");
            return null;
        }
    }

    /*文件重命名方法*/
    public static String renameFileName(String fileName) {
        String formatDate = new SimpleDateFormat("yyMMddHHmmss").format(new Date()); // 当前时间字符串
        int random = new Random().nextInt(10000);
        String extension = fileName.substring(fileName.lastIndexOf(".")); // 文件后缀

        return formatDate + random + extension;
    }
}
