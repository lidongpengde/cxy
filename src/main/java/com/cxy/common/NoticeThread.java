package com.cxy.common;

import com.cxy.dao.NoticeMapper;
import com.cxy.entity.Notice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NoticeThread extends  Thread {

    private Notice notice;
    public  NoticeThread(Notice notice) {
        this.notice = notice;
        start();
    }
    @Override
    public  void run(){
        String url = "jdbc:mysql://47.95.239.247:3306/go366?useUnicode=true&characterEncoding=utf8";
        String name = "com.mysql.jdbc.Driver";
        String user = "aaa";
        String password = "a7695895";
        Connection connection = null;
        try{
            Class.forName(name);
            connection = DriverManager.getConnection(url,user,password);//获取链接
            connection.setAutoCommit(false);//关闭自动提交，不然conn.commit()运行到这句会报错
        }catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //sql前缀
        String prefix = "INSERT INTO notice (user_id,business_id,isread,classes,messages) VALUES ";
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            connection.setAutoCommit(false);
            // 比起st，pst会更好些
            PreparedStatement pst = (PreparedStatement) connection.prepareStatement("");//准备执行语句
            // 外层循环，总提交事务次数
            suffix = new StringBuffer();
            // 构建SQL后缀
            suffix.append("('" +notice.getUserid()+"','"+notice.getBusinessid()+"'"+ ",'"+notice.getIsread()+"'"+",'"+notice.getClasses()+"'"+",'"+notice.getMessages()+"'"+"),");
            // 构建完整SQL
            String sql = prefix + suffix.substring(0, suffix.length() - 1);
            // 添加执行SQL
            pst.addBatch(sql);
            // 执行操作
            pst.executeBatch();
            // 提交事务
            connection.commit();
            // 头等连接
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
