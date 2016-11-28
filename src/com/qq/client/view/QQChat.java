package com.qq.client.view;

/*
 * qq 聊天界面
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.qq.common.*;
public class QQChat extends JFrame implements ActionListener{
JScrollPane scrollPane = null;
JTextArea textArea = null;
JTextField textField = null;
JButton button = null;
JPanel panel = null;
String owner,friend;
Message msg;

public QQChat(String owner,String friend)
{
	
	this.setTitle(owner + "正在和" + friend + "聊天...");
	this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq.png"));
	
	this.owner = owner;
	this.friend = friend;
	
	textArea = new JTextArea();
	textArea.setEditable(false);
	textArea.setLineWrap(true);
	scrollPane = new JScrollPane(textArea);
	this.add(scrollPane,BorderLayout.CENTER);
	
	panel = new JPanel();
	panel.setLayout(new FlowLayout());
	textField = new JTextField(10);
	button = new JButton("发送");
	button.addActionListener(this);
	panel.add(textField);
	panel.add(button);
	
	this.add(panel,BorderLayout.SOUTH);
	
	//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(300,200);
	show();
}

	
	
	/*public static void main(String[] args) {
		QQChat app = new QQChat("1","2");
  
	}*/

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button)
		{
			//制作message包
			msg = new Message();
			msg.setSender(owner);
			msg.setReciever(friend);
			msg.setMsg_type(MassageType.message_data);
			msg.setData(textField.getText());
			//更改界面
			String content =  owner + ": " +textField.getText() + "\n";
			textArea.append(content);
			textField.setText("");
			try {
				ObjectOutputStream  oos = new ObjectOutputStream(ManageClientToServer.getThread(owner).socket.getOutputStream());
				oos.writeObject(msg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				
			}
			
		}
		
	}



	
	

}
