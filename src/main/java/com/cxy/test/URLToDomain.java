package com.cxy.test;

import com.sun.istack.internal.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Created by lidongpeng on 2017/9/1.
 */
public class URLToDomain {
    @NotNull
    String aaa;
    /**
     * 根据url获取domain
     * @return
     * @throws IOException
     */

    public void parseDomain() throws IOException {
            Long START=System.currentTimeMillis();
            String urlAddress = "http://xueshu.baidu.com/";
            URL url = new URL(urlAddress);
            System.out.println(url.getHost());
            System.out.println(System.currentTimeMillis()-START);
    }
    @Test
    public  void main() {
        int a = 0;
        Integer integera = null;
        System.out.println("a="+a+"integera="+integera);

    }

}
