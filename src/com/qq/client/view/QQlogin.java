package com.qq.client.view;
import java.awt.event.*;
import com.qq.common.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
/**
 * 
 * QQ��¼����
 *
 */
public class QQlogin extends JFrame implements ActionListener{
//���山����Ҫ���齨
	JLabel label = null;
//�����в����齨
	JTabbedPane tabpane = null;
	JPanel panel1,panel2,panel3;
	JLabel label1,label2,label3,label4,label5;
	JTextField textField;
	JPasswordField passwd;
	JCheckBox box1,box2;
//�����ϲ����齨
	JPanel panel = null;
	JButton button1,button2,button3 = null;
	
	public QQlogin ()
	{
		this.setTitle("QQ");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq.png"));
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		// ������
		label = new JLabel(new ImageIcon("images/logpic.png"));
		c.add(label,BorderLayout.NORTH);
		//�����в�
		panel1 = new JPanel(new GridLayout(3,3));		
		label1 = new JLabel("QQ����",JLabel.CENTER);
		label2 = new JLabel("QQ����",JLabel.CENTER);
		JButton button3 = new JButton("��������");
		JButton button4  = new JButton("ɾ������");
		JButton button5  = new JButton("�������뱣��");
		button5.setForeground(Color.blue);
		textField = new JTextField(10);
		passwd = new JPasswordField();
		box1 = new JCheckBox("�����¼");
		box2 = new JCheckBox("��ס����");
		
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
		tabpane.add("QQ����",panel1);
		tabpane.add("�ֻ�����",new JPanel());
		tabpane.add("QQ����",new JPanel());
		c.add(tabpane,BorderLayout.CENTER);
		//��������
		panel = new JPanel(new FlowLayout());
		button1 = new JButton("��¼");
		button1.addActionListener(this);
		button2 = new JButton("ȡ��");
		button3 = new JButton("ע����");
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		c.add(panel,BorderLayout.SOUTH);
		
		//Frame��������
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
		//�����½��ť��
		if(e.getSource() == button1)
		{ QQUserCheck check = new QQUserCheck();
		  //��ȡ�û������룬����װ
		 String name = textField.getText().trim();
		 String password = passwd.getText().trim();
		  QQUser u = new QQUser(name,password);
		  System.out.println(name + " " +password);
		  //����Ƿ�Ϊ�Ϸ��û�
		  boolean legal_usr = check.sendLoginfoToServer(u);
		  if(legal_usr)
		  {System.out.println("�����û���½");
		  //���������б�
		  QQlist first_list = new QQlist(name);
		   ManageQQlist.putQQlist(name, first_list);
		  //�������ݰ���ѯ�����б�
		  try {
			  Message msg = new Message();
			  msg.setMsg_type(MassageType.message_get_onlinefriend);
			  msg.setSender(name);
			ObjectOutputStream oos = new ObjectOutputStream(ManageClientToServer.getThread(name).socket.getOutputStream());
			oos.writeObject(msg);//������ѯ���ݰ�
			System.out.println("���Ͳ�ѯ���ݰ�");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   
		   this.dispose();
		  }
		  else
		  {JOptionPane.showMessageDialog(null, "�������������");}
		}
		
	}

}
