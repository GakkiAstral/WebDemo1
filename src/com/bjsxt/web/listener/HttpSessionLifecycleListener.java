package com.bjsxt.web.listener;

import com.bjsxt.commons.Constants;
import com.bjsxt.pojo.Users;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 解决HttpSession被反复销毁的问题
 */
@WebListener
public class HttpSessionLifecycleListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //获取ServletContext对象，然后将所对应的HttpSession删除掉
        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();
        Users users = (Users)session.getAttribute(Constants.USER_SESSION_KEY);
        servletContext.removeAttribute(users.getUserid()+"");
    }
}
