import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

class UserThread implements Runnable{
	private String name;//代表客户端是唯一的昵称
	private Socket socket;//代表此线程任务服务的客户端socket
	private Vector<UserThread> vector;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean flag=true;//循环的标记
	
	public UserThread(Socket socket, Vector<UserThread> vector) {
		super();
		this.socket = socket;
		this.vector = vector;
		this.vector.add(this);
	}
	
	@Override
	public void run() {
		// 1.谁连接了
		System.out.println("客户端"+socket.getInetAddress().getHostAddress()+"已连接");
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			//3.循环读取消息
			while (flag) {
				Message m = (Message) in.readObject();				
				//4. 判断消息类型
				switch (m.getType()) {
				case MessageType.TYPE_SEND:
					int size = vector.size();
					UserThread ut;
					for (int i = 0;i<size;i++) {
						ut = vector.get(i);
						if (ut!= this) {
							ut.out.writeObject(m);//找到了客户端所对应的中转线程，
                            // 通过中转线程把消息发送给客户端
							break;
						}
					}
					break;

				case MessageType.TYPE_LOGIN:
					//String info = m.getfrom();
					m.setInfo("欢迎");
					out.writeObject(m);
					break;
				default:
					break;
				
			}
				}
		} catch (EOFException e) {
			flag = false;
			System.out.println("客户端"+socket.getInetAddress().getHostAddress()+"已断开");
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