/*
 * QQ �û���֤����¼��ť����
 */

package com.qq.client.view;
import com.qq.common.*;
import java.io.*;
import java.net.*;

public class QQUserCheck {

	public  Socket socket;
	
	
	public boolean sendLoginfoToServer (Object o)
	{
		boolean legal_usr = false;
		boolean server_ok = false;
		try {
			//��������
			 socket = new Socket("127.0.0.1",9999);
			System.out.println("���������������");
			server_ok = true;
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(o);
			System.out.println("���ݷ������");
			//���շ��������ص�����
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Message msg = (Message) ois.readObject();
			// ������ȷ
			if(msg.getMsg_type().equals("1"))
			{ 
				legal_usr = true;
				ClientToServer ctsThread = new ClientToServer(socket);
				ctsThread.start();
				ManageClientToServer.addThread(((QQUser)o).getName(), ctsThread);
				}
			//�������
			else if(msg.getMsg_type().equals("2"))
			{legal_usr = false;}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return legal_usr;
	}
	
}
