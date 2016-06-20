package socket.coding;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;


public class TestSocketReadByte {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		try {
			
			BufferedWriter writer = null;
			Socket socket = new Socket("localhost", 11111);
			// socket的输入输出
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "gbk"));
			String message = "000000048|10101|<name='中华人民共和国'/>|";
			
			writer.write(message);
			writer.flush();
			socket.shutdownOutput();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

}
