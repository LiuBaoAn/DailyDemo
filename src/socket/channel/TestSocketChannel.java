package socket.channel;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;


public class TestSocketChannel {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		try {
			String test = "test";
			
			SocketChannel channel = SocketChannel.open();
			SocketAddress remote = new InetSocketAddress("", 12000);
			channel.connect(remote);
			
			ByteBuffer dst = ByteBuffer.allocate(40);
			channel.read(dst);
			
			channel.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

}
