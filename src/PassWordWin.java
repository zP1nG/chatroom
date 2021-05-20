import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
@SuppressWarnings("serial")
public class PassWordWin extends JFrame{
	public PassWordWin(){}
	private String info = "";
	public MainFrame mainFrame  = new MainFrame();
	public void show_surface() {
		PassWordWin frm = new PassWordWin();
		frm.setSize(335, 205);
		frm.setTitle("UpperStarChat v1.0");
		Container c = frm.getContentPane();
		c.setBackground(new Color(135,206,250));
		frm.setLayout(null);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("HIT.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		frm.setLayout(null);
		
		JLabel label = new JLabel(new ImageIcon(img));
		frm.add(label);
		label.setBounds(30, 40, 75, 75);
		//用户名文本框
		JLabel L1 = new JLabel("用户名: ");
		L1.setBounds(120, 50, 55, 20);
		frm.setResizable(false);
		JTextField t1 = new JTextField(50);
		t1.setBounds(180, 50, 100, 20);
		//密码文本框
		JLabel L2 = new JLabel("密码: ");
		L2.setBounds(120, 80, 55, 20);
		JPasswordField t2 = new JPasswordField(50);
		t2.setEchoChar('*');
		t2.setBounds(180, 80, 100, 20);
		//确定按钮
		JButton btn = new JButton("确定");
		btn.addActionListener((ActionEvent e)->{
			JOptionPane.showMessageDialog(null, "登录成功" , "UpperStarChat v1.0" , JOptionPane.INFORMATION_MESSAGE);
			frm.dispose();
			mainFrame.show_surface();
			
		});
		/*btn3.addActionListener((ActionEvent e)->{//第二个界面按下发送了
			this.info = b.getinfo();
			System.out.println("a"+b.getinfo());
			System.out.println("1");
		});*/
		btn.setBounds(120, 110, 80, 30);
		JButton btn2 = new JButton("清除");
		btn2.addActionListener((ActionEvent e)->{
			t1.setText("");
			t2.setText("");
		});
		btn2.setBounds(210, 110, 80, 30);
		frm.add(t1);
		frm.add(L1);
		frm.add(L2);
		frm.add(t2);
		frm.add(btn);
		frm.add(btn2);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
	}
	public String getinfo() {//返回发送的消息
		return this.info;
	}
	/*public String getUsername() {
		return this.username;
	}*/
	/*public void writeinfo(String info) {
		b.t1.setText(info);
	}*/
	/*public static void main(String args[]) {
		PassWordWin a = new PassWordWin();
		a.show_surface();
		while(true)
		{
			if (a.getinfo()=="")
				continue;
			System.out.println(a.getinfo());
		}
	}*/
	
}
