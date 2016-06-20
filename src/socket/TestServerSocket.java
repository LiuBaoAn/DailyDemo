package socket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestServerSocket {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(11111, 3, InetAddress.getByName("localhost"));
			if(server != null) {
				while (true) {
					Socket socket = server.accept();
					InputStream inputStream = socket.getInputStream();
					
//					byte[] messageByte = new byte[24000];
//					byte[] buffer = new byte[24000];
//				    int len = -1;
//				    while ((len = inputStream.read(buffer)) != -1) {
//				    	System.arraycopy(buffer, 0, messageByte, 0, buffer.length);
//				    }
//				    String message = new String(messageByte);
				    
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					String message = UTTaskCTLEUtil.readMessageFromInput(reader);
					reader.close();
					
//					socket.shutdownInput();
					System.out.println("---------------------------------------------------------------------------------------");
					System.out.println(message);
					System.out.println("---------------接收的message的长度：" + message.length() + "-----------------------------------------------------------------------");

					FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/2.txt"));
					fileOutputStream.write(message.getBytes());
					fileOutputStream.flush();
					fileOutputStream.close();
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
