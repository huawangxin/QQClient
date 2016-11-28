package com.qq.client.view;
import java.awt.event.*;
import com.qq.common.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
/**
 * 
 * QQ登录界面
 *
 */
public class QQlogin extends JFrame implements ActionListener{
//定义北部需要的组建
	JLabel label = null;
//定义中部的组建
	JTabbedPane tabpane = null;
	JPanel panel1,panel2,panel3;
	JLabel label1,label2,label3,label4,label5;
	JTextField textField;
	JPasswordField passwd;
	JCheckBox box1,box2;
//定义南部的组建
	JPanel panel = null;
	JButton button1,button2,button3 = null;
	
	public QQlogin ()
	{
		this.setTitle("QQ");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq.png"));
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		// 处理北面
		label = new JLabel(new ImageIcon("images/logpic.png"));
		c.add(label,BorderLayout.NORTH);
		//处理中部
		panel1 = new JPanel(new GridLayout(3,3));		
		label1 = new JLabel("QQ号码",JLabel.CENTER);
		label2 = new JLabel("QQ密码",JLabel.CENTER);
		JButton button3 = new JButton("忘记密码");
		JButton button4  = new JButton("删除号码");
		JButton button5  = new JButton("申请密码保护");
		button5.setForeground(Color.blue);
		textField = new JTextField(10);
		passwd = new JPasswordField();
		box1 = new JCheckBox("隐身登录");
		box2 = new JCheckBox("记住密码");
		
		panel1.add(label1);
		panel1.add(textField);
		panel1.add(button4);
		panel1.add(label2);
		panel1.add(passwd);
		panel1.add(button3);
		panel1.add(box1);
		panel1.add(box2);
		panel1.add(button5);
		
		tabpane = new JTabbedPane();
		tabpane.add("QQ号码",panel1);
		tabpane.add("手机号码",new JPanel());
		tabpane.add("QQ邮箱",new JPanel());
		c.add(tabpane,BorderLayout.CENTER);
		//处理南面
		panel = new JPanel(new FlowLayout());
		button1 = new JButton("登录");
		button1.addActionListener(this);
		button2 = new JButton("取消");
		button3 = new JButton("注册向导");
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		c.add(panel,BorderLayout.SOUTH);
		
		//Frame属性设置
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,300);
		show();
	}
    	
	public static void main(String[] args) 
	{
		QQlogin app = new QQlogin();
	}

	
	public void actionPerformed(ActionEvent e) {
		//点击登陆按钮后
		if(e.getSource() == button1)
		{ QQUserCheck check = new QQUserCheck();
		  //获取用户名密码，并封装
		 String name = textField.getText().trim();
		 String password = passwd.getText().trim();
		  QQUser u = new QQUser(name,password);
		  System.out.println(name + " " +password);
		  //检查是否为合法用户
		  boolean legal_usr = check.sendLoginfoToServer(u);
		  if(legal_usr)
		  {System.out.println("允许用户登陆");
		  //创建好友列表
		  QQlist first_list = new QQlist(name);
		   ManageQQlist.putQQlist(name, first_list);
		  //发送数据包查询好友列表
		  try {
			  Message msg = new Message();
			  msg.setMsg_type(MassageType.message_get_onlinefriend);
			  msg.setSender(name);
			ObjectOutputStream oos = new ObjectOutputStream(ManageClientToServer.getThread(name).socket.getOutputStream());
			oos.writeObject(msg);//发出查询数据包
			System.out.println("发送查询数据包");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   
		   this.dispose();
		  }
		  else
		  {JOptionPane.showMessageDialog(null, "密码错误，请重试");}
		}
		
	}

}
