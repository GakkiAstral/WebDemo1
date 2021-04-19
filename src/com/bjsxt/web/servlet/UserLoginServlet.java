package com.bjsxt.web.servlet;

import com.bjsxt.commons.Constants;
import com.bjsxt.exception.UserNotFoundException;
import com.bjsxt.pojo.Users;
import com.bjsxt.service.UserLoginService;
import com.bjsxt.service.impl.UserLoginServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 处理用户登录请求
 */
@WebServlet("/login.do")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpwd");
        String code = req.getParameter("code");

        try{
            HttpSession session = req.getSession();
            String codeTemp = (String)session.getAttribute(Constants.VALIDATE_CODE_KEY);
            if (codeTemp.equals(code)){
                UserLoginService userLoginService = new UserLoginServiceImpl();
                Users users = userLoginService.userLogin(username,userpwd);
                //账号密码确认通过后，建立客户端与服务端的会话状态

                session.setAttribute(Constants.USER_SESSION_KEY,users);
                //使用抽象类Constants,定义参数，防止使用硬编码

                //做一个用户只能在一处登录的限制
                ServletContext servletContext = this.getServletContext();
                HttpSession temp = (HttpSession) servletContext.getAttribute(users.getUserid()+"");
                if (temp != null){
                    servletContext.removeAttribute(users.getUserid()+"");
                    temp.invalidate();
                }
                servletContext.setAttribute(users.getUserid()+"",session);

                //使用重定向方式跳转到首页
                resp.sendRedirect("main.jsp");
            }else {
                req.setAttribute(Constants.REQUEST_MSG,"验证码有误，请重新输入");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        }catch (UserNotFoundException e){
            req.setAttribute("msg",e.getMessage());
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }
}
