package com.qq.client.view;

import com.qq.common.*;
import java.net.*;
import java.io.*;

public class ClientToServer extends Thread{

public Socket socket;

public ClientToServer(Socket socket)
{
	this.socket = socket;
	
}
public void run()
{

	while(true)
	{
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Message msg = (Message)ois.readObject();
			
			if(msg.getMsg_type().equals(MassageType.message_data))//�������Ϣ��
			{
			System.out.println("�յ����Ѱ�");
			QQChat chat = ManageQQchat.getQQChat(msg.getReciever()+" "+msg.getSender());
			chat.textArea.append(msg.getSender() + ": " +msg.getData()+"\n");
			}else if(msg.getMsg_type().equals(MassageType.message_reply_onlinefriend)) //�ض����ݰ�
			{
				System.out.println("�յ����ݰ�");
				
				String reciver = msg.getReciever();
				//�޸���Ӧ�ĺ����б�
				
				QQlist list = ManageQQlist.map.get(reciver);
				if(list!=null)
				{
				list.updateQQlist(msg);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

}
