import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**/
public class Server {
	public static void main(String[] args) {
        Vector<UserThread> vector = new Vector<UserThread>();
        ExecutorService es = Executors.newFixedThreadPool(5);
        try {
			ServerSocket server = new ServerSocket(9988);
			System.out.println("服务器已启动");
			while (true) {
				Socket socket = server.accept();
				UserThread user = new UserThread(socket, vector);
				es.execute(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
}
