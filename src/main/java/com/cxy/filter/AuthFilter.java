package com.cxy.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by lidp on 2017/3/19.
 */
public class AuthFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest)request;
        HttpServletResponse httpResponse=(HttpServletResponse)response;
        HttpSession session=httpRequest.getSession();
        if(session.getAttribute("username")!=null){
            filterChain.doFilter(request, response);
        }
        else{
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    public void destroy() {

    }
}
