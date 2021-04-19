package com.bjsxt.web.filter;

import com.bjsxt.commons.Constants;
import com.bjsxt.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 判断当前客户端浏览器是否登录的Filter
 */
@WebFilter(urlPatterns = {"*.do","*.jsp"})
public class UserLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    //系统有两个资源不可以做限制,否则永远登录不上去,分别是：login.do,login.jsp做放行,获取验证码的是validateCode.do也需要放行

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String uri = request.getRequestURI();
        //判断当前请求的是否为login.jsp或者login.do,如果请求的是用户登录的资源，那么需要放行
        if (uri.indexOf("login.jsp") != -1 || uri.indexOf("login.do") != -1 || uri.indexOf("validateCode.do") != -1){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            HttpSession session = request.getSession();
            Users users = (Users) session.getAttribute(Constants.USER_SESSION_KEY);
            if (users != null){
                filterChain.doFilter(servletRequest,servletResponse);
            } else{
                request.setAttribute(Constants.REQUEST_MSG,"用户尚未登录，请登录！");
                request.getRequestDispatcher("login.jsp").forward(servletRequest,servletResponse);
            }
        }
    }
}
