package com.qq.client.view;
/**
 * QQ联系人界面
 */
import java.awt.*;
import com.qq.common.*;
import javax.swing.*;
import java.awt.event.*;

public class QQlist extends JFrame implements ActionListener,MouseListener{
	
	JButton button1,button2,button3,friend_button1,friend_button2,friend_button3;
	JScrollPane scrollpane;
	JPanel panel1,panel2,friend_panel,stranger_panel,pissoff_panel;
	//Container c=null;
	CardLayout cardManager;
	String ownerID = null;
	//好友
	JLabel labels[] = null;
public QQlist( String ownerID)
{
	//Container c = getContentPane();
	this.ownerID = ownerID;
	cardManager = new CardLayout();
	this.setLayout(cardManager);
	this.setTitle(ownerID + "的QQlist");
	this.setIconImage(Toolkit.getDefaultToolkit().getImage("images/qq.png"));
	//处理第一张card
	friend_panel = new JPanel(new BorderLayout());
	friend_button1 = new JButton("我的好友");
	friend_button2 = new JButton("陌生人");
	friend_button2.addActionListener(this);
	friend_button3 = new JButton("黑名单");
	//加入我的好友按钮
	friend_panel.add(friend_button1,BorderLayout.NORTH);
	//处理中间部分
	panel1 = new JPanel(new GridLayout(30,1,5,5));	
	labels = new JLabel[30];
	for(int i=0;i<labels.length;i++)
	{
		labels[i] = new JLabel(new ImageIcon("images/friend2.png"),JLabel.LEFT);
		labels[i].addMouseListener(this);
		labels[i].setText(" "+i);
		labels[i].setEnabled(false);
		System.out.println("建立好友列表");
		if(labels[i].getText().trim().equals(ownerID))
		{
			labels[i].setEnabled(true);
		}
		panel1.add(labels[i]);
	}
	scrollpane = new JScrollPane(panel1);
	friend_panel.add(scrollpane);
	//处理南部
	panel2 = new JPanel(new GridLayout(2,1));
	panel2.add(friend_button2);
	panel2.add(friend_button3);
	friend_panel.add(panel2,BorderLayout.SOUTH);
	
	
	//处理第二张card
	
	button1 = new JButton("我的好友");
	button1.addActionListener(this);
	button2 = new JButton("陌生人");
	button3 = new JButton("黑名单");
	
	stranger_panel = new JPanel(new BorderLayout());
	JPanel temp = new JPanel(new GridLayout(2,1));
	temp.add(button1);
	temp.add(button2);
	stranger_panel.add(temp,BorderLayout.NORTH);
	
    JPanel center_panel = new JPanel(new GridLayout(20,1,5,5));
    JLabel stranger_labels[] = new JLabel[20];
    for(int i=0;i<stranger_labels.length;i++)
    {
    	stranger_labels[i] = new JLabel(new ImageIcon("images/stranger.png"),JLabel.LEFT);
    	stranger_labels[i].addMouseListener(this);
    	stranger_labels[i].setText(" " + i);
    	center_panel.add(stranger_labels[i]);
    }
    
    scrollpane = new JScrollPane(center_panel);
    stranger_panel.add(scrollpane,BorderLayout.CENTER);
    stranger_panel.add(button3,BorderLayout.SOUTH);
	
	this.add(friend_panel,"1");
    this.add(stranger_panel,"2");
	setSize(200,500);
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	show();
}

	/*public static void main(String[] args)
	{
		QQlist ap = new QQlist("1");
	}*/

	public void updateQQlist(Message msg)
	{
		String friendList = msg.getData(); //所含好友的String ‘1 2 3 4 11’
		System.out.println("客户端收到好友列表" + friendList);
		String friendonline[] = friendList.split(" ");
		System.out.println(friendonline.length);
		for(int i=0;i<friendonline.length;i++)
		{
			
			System.out.println("在线好友" + friendonline[i]);
			labels[Integer.parseInt(friendonline[i])].setEnabled(true);
			System.out.println(i);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == friend_button2){cardManager.show(this.getContentPane(),"2");}
		else if(e.getSource() == button1){cardManager.show(this.getContentPane(),"1");}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getClickCount() == 2)
		{
		String friendNO = ((JLabel)arg0.getSource()).getText().trim();
		QQChat chat = new QQChat(ownerID,friendNO);
		
		ManageQQchat.putQQChat(ownerID+" "+friendNO,chat);
		
		System.out.println("和" + friendNO + "聊天");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		JLabel lb = (JLabel)arg0.getSource();
		lb.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		JLabel lb = (JLabel)arg0.getSource();
		lb.setForeground(Color.black);
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
