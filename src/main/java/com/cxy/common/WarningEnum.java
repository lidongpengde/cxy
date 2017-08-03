package com.cxy.common;


/**
 * @author lidongpeng
 */
public enum WarningEnum {
	unfinished_order(100,"有未完成订单"),
	update_failed(101,"更新失败"),
	update_success(200,"更新成功"),
	no_privilege(102,"没有权限操作"),
	mobile_used_byother(3,"手机号已经被其他人使用"),
	send_max_date(4,"发送次数超过限制"),
	too_often(5,"发送的短信太频繁"),
	mobile_not_actived(6,"手机未激活"),
	system_error(7,"系统错误"),
	empty_mobile(9,"手机号为空");

	private int code;
	private String msg;

	private WarningEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
