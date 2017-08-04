package com.cxy.filter;


import com.cxy.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by lidp on 2017/3/19.
 */
public class AuthFilter implements Filter {

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        //线上换成这个，不然重定向找不到路径
        final String loginUrlOnline="http://go366.club/user/tologin";
        //调试用这个
        final String loginUrltest="/";
        HttpServletRequest req = (HttpServletRequest)arg0;
        HttpServletResponse resp =(HttpServletResponse) arg1;
        HttpSession session = req.getSession();
        String host = req.getHeader("Host");
        User password = (User) session.getAttribute("const_user");
        if (password == null || "".equals(password)) {
            // 跳转到登陆页面
            resp.sendRedirect(loginUrlOnline);
        } else {
            arg2.doFilter(req, resp);
        }
    }
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }
}
