package com.seven.interceptor;

import com.seven.domain.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginInteractor
 * @Description TODO
 * @Author ying
 * @Date 2021/7/21 10:54
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /*该方法叫做预处理方法*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入登录拦截器LoginInterceptor----------------------------");
        /*1.从session内获取用户信息，如果用户信息不为空，说明已经登录*/
        User user = (User) request.getSession().getAttribute("loginUser");
        System.out.println("session的用户获取:"+user);
        if(user==null)
        {
            /*若没有登录，返回登录页面*/
            System.out.println("没有登录，不允许访问");
            response.sendRedirect("/toLogin");  /*进入toLogin的controller*/
            return false;
        }
        else {
            System.out.println("已经登录，用户为:"+user.getUserName());
            /*返回true允许访问*/
            return true;
        }
    }

    /*后处理回调方法：在处理器之后，渲染视图之前运行*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*System.out.println("登录拦截器的postHandle方法---------------------------------");*/
    }


    /*在请求流程处理完成之后调用的方法*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*System.out.println("登录拦截器的afterCompletion方法---------------------------------");*/
    }

}

