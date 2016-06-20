import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
					System.out.println();
					System.out.println();
					Socket socket = server.accept();
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter writer = new PrintWriter(socket.getOutputStream());
					
					StringBuilder message = new StringBuilder();
					String readLine = null;
					while ((readLine = reader.readLine()) != null) {
						if(message.length() != 0) {
							message.append(System.getProperty("line.separator"));
						}
						message.append(readLine);
					}
					socket.shutdownInput();
					System.out.println("---------------------------------------------------------------------------------------");
					System.out.println(message);
					System.out.println("---------------------------------------------------------------------------------------");
					
					Object[] objects = UTTaskCTLEUtil.parseMessage(message.toString());
					if(objects != null) {
						writer.println(UTTaskCTLEUtil.formatResponseMessage("", "", "接收成功！"));
					} else {
						writer.println(UTTaskCTLEUtil.formatResponseMessage("", "", "接收失败！"));
					}
					writer.flush();
					reader.close();
					writer.close();
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
