package com.cxy.common;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


public class IpAddress {
	//获取客户端的IP地址
	public static String getIpAddr(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	
	//获取客户端的IP地址
	public static String getIpAddrForManyIps(HttpServletRequest request) {
		String ip=getIpAddr(request);
		if(!StringUtils.isEmpty(ip))
			ip=ip.split(",")[0];
		return ip;
	}
		
}
