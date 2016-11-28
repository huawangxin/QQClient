package com.qq.client.view;
import java.util.*;
public class ManageQQlist {
	public static HashMap<String,QQlist> map = new HashMap<String,QQlist>();
	
	public static void putQQlist(String name,QQlist list)
	{
		map.put(name, list);
	}
	
	public static QQlist getQQlist(String name)
	{
		return map.get(name);
		
	}
}
