package com.cxy.filter;

/**
 * Created by lidongpeng on 2017/8/18.
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

    private String encoding = null;

    /**
     * 初始化方法,从web.xml中获取配置的初始化参数
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encode");

    }

    /**
     * 设置编码
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        httpRequest.setCharacterEncoding(encoding);
        httpResponse.setCharacterEncoding(encoding);

        //调用doFIlter方法,如果还有别的过滤器会自动向下调用
        chain.doFilter(httpRequest, httpResponse);

    }

    @Override
    public void destroy() {

    }


}
