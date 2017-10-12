package com.cxy.test;

/**
 * Created by lidongpeng on 2017/10/11.
 */
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 远程执行linux的shell script
 * @author Ickes
 * @author2 hpp
 * @since  V0.2
 */
public class RemoteExecuteCommand {
    //字符编码默认是utf-8
    private static String  DEFAULTCHART="UTF-8";
    private static Connection conn;
    private String ip;
    private String userName;
    private String userPwd;

    public RemoteExecuteCommand(String ip, String userName, String userPwd) {
        this.ip = ip;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public RemoteExecuteCommand() {

    }

    /**
     * 远程登录linux的主机
     * @author Ickes
     * @since  V0.1
     * @return
     *      登录成功返回true，否则返回false
     */
    public Boolean login(){
        boolean flg=false;
        try {
            conn = new Connection(ip);
            conn.connect();//连接
            flg=conn.authenticateWithPassword(userName, userPwd);//认证
            if (flg){
                System.out.println("认证成功！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flg;
    }
/*    *//**
     * @author Ickes
     * 远程执行shll脚本或者命令
     * @param cmd
     *      即将执行的命令
     * @return
     *      命令执行完后返回的结果值
     * @since V0.1
     *//*
    public String execute(String cmd){
        String result="";
        try {
            if(login()){
                Session session= conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result=processStdout(session.getStdout(),DEFAULTCHART);
                //如果为得到标准输出为空，说明脚本执行出错了
                if(StringUtils.isEmpty(result)){
                    result=processStdout(session.getStderr(),DEFAULTCHART);
                }
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }*/


/*    *//**
     * @author Ickes
     * 远程执行shll脚本或者命令
     * @param cmd
     *      即将执行的命令
     * @return
     *      命令执行成功后返回的结果值，如果命令执行失败，返回空字符串，不是null
     * @since V0.1
     *//*
    public String executeSuccess(String cmd){
        String result="";
        try {
            if(login()){
                Session session= conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result=processStdout(session.getStdout(),DEFAULTCHART);
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }*/

    /**
     * 解析脚本执行返回的结果集
     * @author Ickes
     * @param in 输入流对象
     * @param charset 编码
     * @since V0.1
     * @return
     *       以纯文本的格式返回
     */
    public static String processStdout(InputStream in, String charset,String group){
        IpCountUtil ipCountUtil=new IpCountUtil();
        InputStream    stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,charset));
            String line=null;
            String[] arrs=null;
            while((line=br.readLine()) != null){
                line=line.trim();
                if (!StringUtils.isEmpty(line)){
                    arrs=line.split(" ");
                    IpCountModel ipCountModel=new IpCountModel();
                    ipCountModel.setCount(arrs[0]);
                    ipCountModel.setIpAddress(arrs[1]);
                    ipCountModel.setGroup(group);
                    ipCountUtil.insert(ipCountModel);
                }
                buffer.append(line+"\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }



    public static void main(String[] args) {
        List<String> frontIps=new ArrayList<>();
        frontIps.add("10.58.22.216");
        frontIps.add("10.58.22.63");
        frontIps.add("10.58.50.75");
        frontIps.add("10.58.50.76");
        frontIps.add("10.58.50.8");
        frontIps.add("10.58.50.9");
        frontIps.add("10.58.188.221");
        frontIps.add("10.58.188.222");
        frontIps.add("10.58.209.12");
        for (String ip:frontIps) {


        RemoteExecuteCommand rec=new RemoteExecuteCommand(ip, "gome_guest","searchlog678!");
        //执行命令
        try {
            if(rec.login()){

                System.out.println("=====第一个步骤=====");
                Session session= conn.openSession();//打开一个会话
                String loginFrontCommand="cat /app/atglogs/sso/loginFront*/access/access_2017_10_11.log |awk '{print $1}'|sort|uniq -c|sort -rn|head -n 50";
                String registerFrontCommand="cat /app/atglogs/sso/registerFront*/access/access_2017_10_11.log |awk '{print $1}'|sort|uniq -c|sort -rn|head -n 50";
                //TODO:loginfront
                session.execCommand(loginFrontCommand);//执行命令
                String result=processStdout(session.getStdout(),DEFAULTCHART,"loginFront");
                session.close();
                session= conn.openSession();
                //TODO:registerfront
                session.execCommand(registerFrontCommand);//执行命令
                String result1=processStdout(session.getStdout(),DEFAULTCHART,"registerFront");
                //如果为得到标准输出为空，说明脚本执行出错了
                if(StringUtils.isEmpty(result)){
                    System.out.println("脚本出错");
                    result=processStdout(session.getStderr(),DEFAULTCHART,"error");
                }
                System.out.println(result);
                session.close();

/*                System.out.println("=====第二个步骤=====");
                Session session2= conn.openSession();//打开一个会话
                //TODO:多条命令
                session2.execCommand("cd /home/ubuntu/Desktop/music_rec/user_sim/result;cat xyy_result_m10d.json");//执行命令
                String result2=processStdout(session2.getStdout(),DEFAULTCHART);
                //如果为得到标准输出为空，说明脚本执行出错了
                if(StringUtils.isEmpty(result2)){
                    System.out.println("脚本出错");
                    result2=processStdout(session2.getStderr(),DEFAULTCHART);
                }
                System.out.println(result2);
                session2.close();*/


                conn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }



    public static void setCharset(String charset) {
        DEFAULTCHART = charset;
    }
    public Connection getConn() {
        return conn;
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPwd() {
        return userPwd;
    }
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}

