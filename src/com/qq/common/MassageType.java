package com.qq.common;

public interface MassageType {
	String message_success = "1"; // 密码正确
	String message_fail = "2";    //密码错误
	String message_data = "3";     //消息数据
	String message_get_onlinefriend = "4";  //客户端向服务器要求在线好友
	String message_reply_onlinefriend = "5";//服务器向客户端返回在线好友

}
