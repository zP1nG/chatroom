import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadInfoThread  implements Runnable {
	private ObjectInputStream in;
	private MainFrame b;
    private boolean flag=true;
    public ReadInfoThread (ObjectInputStream in,MainFrame b) {
        this.in = in;
        this.b = b;
    }
    public  void  setFlag(boolean flag){
        this.flag=flag;
    }
	@Override
	public void run() {
		while (flag) {
			try {
				Message message = (Message)in.readObject();
				System.out.println(message.getInfo());
				b.writeinfo(message.getInfo());
			} catch (IOException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (in!=null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
