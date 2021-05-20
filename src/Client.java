import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements Serializable{//�ͻ���
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		ExecutorService es = Executors.newSingleThreadExecutor();
		try {
			Socket socket = new Socket("LocalHost", 9988);
			System.out.println("���������ӳɹ�");
			ObjectOutputStream out =new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in =new ObjectInputStream(socket.getInputStream());
            //====ģ���û���¼====
            //����������͵�¼��Ϣ
            
            PassWordWin a = new PassWordWin();
            //Scanner input = new Scanner(a.getinfo());
            a.show_surface();
            MainFrame b = a.mainFrame;
            Message m = new Message (MessageType.TYPE_LOGIN, null);
            out.writeObject(m);
            m = (Message) in.readObject();            
            //ģ���û���¼����
            //������ȡ��Ϣ���߳�
            es.execute(new ReadInfoThread(in, b));
            //ͨ�����߳�ʵ�ַ�����Ϣ
            boolean flag = true;
            while (flag) {
            	if (b.info=="") {
            		continue;
            	}
            	m = new Message();
            	m.setInfo(b.info);
                m.setType(MessageType.TYPE_SEND);
                out.writeObject(m);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

