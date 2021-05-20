import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	public String info = "";
	public JTextArea t1;
	public JTextArea t2 = new JTextArea();
	public JButton btn;
	public MainFrame(){
		;
	}
	public void show_surface() {
		this.setSize(800,700);
		this.setTitle("UpperStarChat v1.0");
		this.setResizable(false);
		Container c = this.getContentPane();
		c.setBackground(new Color(135,206,250));
		this.setLayout(null);
		this.t1 = new JTextArea();
		t1.setBounds(20, 20, 745, 455);
		t1.setLineWrap(true);
		this.add(t1);
		//JTextArea t2 = new JTextArea();
		t2.setBounds(20, 490, 745, 150);
		t2.setLineWrap(true);
		this.add(t2);
		btn = new JButton("·¢ËÍ");
		btn.addActionListener((ActionEvent e)->{
			this.info = t2.getText();
			//System.out.println(t2.getText());
			//System.out.print(1);
			/*if (!this.info.equals("1")) {
        		System.out.println(this.info);
        	}*/
			//System.out.println(this.info);
			//t2.setText("");
		});
		btn.setBounds(685, 610, 80, 30);
		this.add(btn,0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public String getinfo() {
		return this.info;
	}
	public void writeinfo(String info) {
		t1.setText(info);
	}
	/*public static void main(String args[]) {
		MainFrame a =new MainFrame();
		a.show_surface();
	}*/
}
