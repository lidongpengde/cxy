package com.cxy.test;

import com.cxy.common.UserTools;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lidongpeng on 2017/10/11.
 */
public class IpCountUtil {

    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/cxy";
        String username = "root";
        String password = "";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public   int insert(IpCountModel ipCountModel) {
        Connection conn = getConn();
        int i = 0;
        String sql = "insert into ipcount (ipnumber,ipaddress,createtime,ipgroup) values(?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, ipCountModel.getCount());
            pstmt.setString(2, ipCountModel.getIpAddress());
            pstmt.setString(3, UserTools.getCurrentDate());
            pstmt.setString(4, ipCountModel.getGroup());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    @Test
    public void readFile01() throws IOException {
        FileReader fr=new FileReader("E:/sso/ssotest/ip统计.md");
        BufferedReader br=new BufferedReader(fr);
        String line="";
        String[] arrs=null;
        while ((line=br.readLine())!=null) {
            line=line.trim();
            if (!StringUtils.isEmpty(line)){
                arrs=line.split(" ");
                IpCountModel ipCountModel=new IpCountModel();
                ipCountModel.setCount(arrs[0]);
                ipCountModel.setIpAddress(arrs[1]);
                insert(ipCountModel);
                System.out.println("次数"+arrs[0] + " ip地址 " + arrs[1]);
            }
        }
        br.close();
        fr.close();
    }
}
