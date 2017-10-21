package com.cxy.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lidongpeng on 2017/10/20.
 */
public class LocationUtil {
    public static String  getLocation(String ip) throws IOException {
       final String APIKEY="1d27cdc315426dd956cff45328333d9f";
        DefaultHttpClient httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());
        HttpResponse response;
        String line=null;
        HttpGet httpget = new HttpGet("http://restapi.amap.com/v3/ip?key="+APIKEY+"&ip=" + ip);
        response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(entity.getContent(), "UTF-8"), 8 * 1024);
                line = bufferedReader.readLine();
                System.out.println(line);
                 bufferedReader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return line;
    }
}
