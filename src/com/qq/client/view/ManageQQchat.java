package com.qq.client.view;
import java.util.*;
public class ManageQQchat {

	public static HashMap<String,QQChat> map = new HashMap<String,QQChat>();
	
	public static void putQQChat(String name,QQChat qqchat)
	{
		map.put(name, qqchat);
	}
	
	public static QQChat getQQChat(String name)
	{
		return map.get(name);
	}
}
