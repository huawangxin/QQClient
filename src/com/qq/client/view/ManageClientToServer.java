package com.qq.client.view;

import java.util.HashMap;

public class ManageClientToServer {
	
    public static HashMap<String,ClientToServer> map = new HashMap<String,ClientToServer>();
    
	public static void addThread(String name,ClientToServer newThread)
	{
		map.put(name, newThread);
	}

	public static ClientToServer getThread(String name)
	{
		return map.get(name);
	}
}
