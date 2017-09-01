package com.cxy.test;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Created by lidongpeng on 2017/9/1.
 */
public class URLToDomain {
    /**
     * 根据url获取domain
     * @param urlAddress
     * @return
     * @throws IOException
     */
    @Test
    public String parseDomain(String urlAddress) throws IOException {
            Long START=System.currentTimeMillis();
            //String urlAddress = "http://www.roseindia.net/jsf/JSFLoginApplication.shtml";
            URL url = new URL(urlAddress);
            System.out.println(url.getHost());
            System.out.println(System.currentTimeMillis()-START);
            return  url.getHost();
    }
}
