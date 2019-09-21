package com.cxy.filter;


import com.cxy.entity.User;
import com.cxy.service.IuserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by lidp on 2017/3/19.
 */
public class AuthFilter implements Filter {

    private IuserService userService;

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        //线上换成这个，不然重定向找不到路径
        final String loginUrlOnline="http://go366.club/user/tologin";
        String pre="http://";
        //调试用这个
        final String loginUrltest="/user/tologin";
        HttpServletRequest req = (HttpServletRequest)arg0;
        HttpServletResponse resp =(HttpServletResponse) arg1;
        final String host=req.getContextPath();
        HttpSession session = req.getSession();
        String loginaddress =host+loginUrltest;

        Integer userType = (Integer) req.getSession().getAttribute("user_type");
        if(userType==null){
            req.getSession().setAttribute("user_type",1);
        }
        Cookie[] cookies = req.getCookies();
        String cookieValue = null;
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("const_user")){
                cookieValue =  cookie.getValue();
                break;
            }
        }
        getUerBean(req);
       // User user = (User) session.getAttribute("const_user");
        User user = userService.getUserBykey(cookieValue);
        if (user == null || "".equals(user)) {
            // 跳转到登录页面
            //resp.sendRedirect(prefix+host+loginUrltest);
            resp.sendRedirect(loginaddress);
        } else {
            arg2.doFilter(req, resp);
        }
    }
    public void init(FilterConfig config) throws ServletException {
        // TODO Auto-generated method stub

    }
    public void getUerBean(HttpServletRequest request){
        ServletContext context = request.getSession().getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(context);
        if (cxt != null){
            userService = (IuserService) cxt.getBean(IuserService.class);
        }
    }
}
