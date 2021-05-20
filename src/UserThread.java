import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

class UserThread implements Runnable{
	private String name;//����ͻ�����Ψһ���ǳ�
	private Socket socket;//������߳��������Ŀͻ���socket
	private Vector<UserThread> vector;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean flag=true;//ѭ���ı��
	
	public UserThread(Socket socket, Vector<UserThread> vector) {
		super();
		this.socket = socket;
		this.vector = vector;
		this.vector.add(this);
	}
	
	@Override
	public void run() {
		// 1.˭������
		System.out.println("�ͻ���"+socket.getInetAddress().getHostAddress()+"������");
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			//3.ѭ����ȡ��Ϣ
			while (flag) {
				Message m = (Message) in.readObject();				
				//4. �ж���Ϣ����
				switch (m.getType()) {
				case MessageType.TYPE_SEND:
					int size = vector.size();
					UserThread ut;
					for (int i = 0;i<size;i++) {
						ut = vector.get(i);
						if (ut!= this) {
							ut.out.writeObject(m);//�ҵ��˿ͻ�������Ӧ����ת�̣߳�
                            // ͨ����ת�̰߳���Ϣ���͸��ͻ���
							break;
						}
					}
					break;

				case MessageType.TYPE_LOGIN:
					//String info = m.getfrom();
					m.setInfo("��ӭ");
					out.writeObject(m);
					break;
				default:
					break;
				
			}
				}
		} catch (EOFException e) {
			flag = false;
			System.out.println("�ͻ���"+socket.getInetAddress().getHostAddress()+"�ѶϿ�");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			flag = false;
		}
		
	}
	;
}