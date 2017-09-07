package com.cxy.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * A example that demonstrates how HttpClient APIs can be used to perform
 * form-based logon.
 * based on 2008-2009 version 4 API http://hc.apache.org/
 */
public class ClientFormLogin {

    public static void main(String[] args) throws Exception {

        DefaultHttpClient httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());
        HttpResponse response;

        HttpGet httpget = new HttpGet("http://login.atguat.com.cn/getQrcode.no?width=165&height=165");

         response = httpclient.execute(httpget);
        synchronized (httpclient){
        HttpPost httppost = new HttpPost("http://login.atguat.com.cn/LoadingBarcodeLogin.no?loginStep=waitScan&sendtimestamp=1504776400993");
        response = httpclient.execute(httppost);
        }
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(entity.getContent(),"UTF-8"), 8 * 1024);
                StringBuilder entityStringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    entityStringBuilder.append(line + "/n");
                }
                // 利用从HttpEntity中得到的String生成JsonObject
                System.out.println(entityStringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Login form get: " + response.getStatusLine());
        if (entity != null) {
            entity.consumeContent();
        }
        System.out.println("Initial set of cookies:");
        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
        if (cookies.isEmpty()) {
            System.out.println("None");
        } else {
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("- " + cookies.get(i).toString());
            }
        }}
  /*      List<Cookie> cookies ;
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost("http://localhost:8080/httpclienttest");

        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("key", "username"));
        nvps.add(new BasicNameValuePair("IDToken2", "password"));

        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

        HttpResponse response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        System.out.println("Login form get: " + response.getStatusLine());
        if (entity != null) {
            entity.consumeContent();
        }

        System.out.println("Post logon cookies:");
        cookies = httpclient.getCookieStore().getCookies();
        if (cookies.isEmpty()) {
            System.out.println("None");
        } else {
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("- " + cookies.get(i).toString());
            }
        }
    }*/
}