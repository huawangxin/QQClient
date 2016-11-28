/*
 * 点击登录按钮，发起服务器连接，发送帐号密码数据，返回登录验证结果
 */

package com.qq.client.view;
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import com.qq.common.*;
public class QQClient {

//	public QQClient()
//	{
//		try {
//			Socket socket = new Socket("127.0.0.1",9998);
//			System.out.println("client ok");
//			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//			QQUser usr = new QQUser("colin","028372");
//			oos.writeObject(usr);
//			
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "ok");
        
	}

}
