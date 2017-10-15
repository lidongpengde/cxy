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
        Son son=new Son();
        Parent parent=son;
        System.out.println(1);

    }
    public void observe() {
        String oldfileName="abc.jgp";
        String prefix=oldfileName.substring(oldfileName.lastIndexOf("."));
        System.out.println(prefix);


    }

}
