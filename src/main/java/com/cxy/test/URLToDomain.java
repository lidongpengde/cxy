package com.cxy.test;

import org.junit.Test;
import java.io.IOException;
import java.net.URL;


/**
 * Created by lidongpeng on 2017/9/1.
 */
public class URLToDomain {
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
        observe();

    }
    public void observe() {
        final long free = Runtime.getRuntime().freeMemory();
        final long total = Runtime.getRuntime().totalMemory();


        System.out.println(System.getProperties().getProperty("user.home"));
    }

}
