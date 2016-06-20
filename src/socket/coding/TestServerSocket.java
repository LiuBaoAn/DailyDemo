package socket.coding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import socket.UTTaskCTLEUtil;

public class TestServerSocket {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(11111, 3, InetAddress.getByName("localhost"));
			if(server != null) {
				while (true) {
					Socket socket = server.accept();
					InputStream inputStream = socket.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
					String message = UTTaskCTLEUtil.readMessageFromInput(reader);
					
					System.out.println("-----------------------------------------------------------------");
					System.out.println(message);
					System.out.println("---------------接收的message的长度：" + message.length() + "--------------------------------");
					
					
					reader.close();
					socket.close();
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
