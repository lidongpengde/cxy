package com.gome.userCenter.service.test;

import java.util.List;

import com.cxy.dao.LineInfoMapper;
import com.cxy.entity.LineInfo;
import com.cxy.service.ILineInfoService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ForgetPwdTest {

	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springcontexttest.xml");
	ILineInfoService lineInfoService=(ILineInfoService) context.getBean("lineInfoService");
	
	@Test
	public void testFpMobile(){
		LineInfoMapper lineInfoMapper=(LineInfoMapper) context.getBean("lineInfoMapper");

		LineInfo lineInfo=lineInfoService.queryLineInfoById(2);
		System.out.println(lineInfo);
	}
	
	@Test
	public void testFpEmail(){
		System.out.println();
	}
}
