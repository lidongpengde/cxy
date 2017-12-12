package com.cxy.service.Impl;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by lidongpeng on 2017/12/12.
 */
public class MailService {
    private static final String HOST = "smtp.163.com";
    private static final Integer PORT = 25;
    private static final String USERNAME = "15953185712@163.com";
    private static final String PASSWORD = "czxsqm521";
    private static final String EMAILFORM = "15953185712@163.com";
    private static JavaMailSenderImpl mailSender = createMailSender();
    private static MailService mailService=new MailService();
    private  MailService(){

    }

    public static MailService getInstance() {
        if (mailService==null){
            mailService=new MailService();
        }
        return mailService;
    }
    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "25000");
        p.setProperty("mail.smtp.auth", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     *
     * @param project
     * @param receiver
     * @param Subject
     * @param text
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     * @throws javax.mail.MessagingException
     */
    public  void sendMail(String project,String receiver,String Subject,String text) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(EMAILFORM, project);
        messageHelper.setTo(receiver);
        messageHelper.setSubject(Subject);
        messageHelper.setText(text, true);
        mailSender.send(mimeMessage);
    }
}
