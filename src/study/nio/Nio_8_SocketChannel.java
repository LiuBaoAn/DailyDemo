package study.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Java NIO中的SocketChannel是一个连接到TCP网络套接字的通道，两种创建方式：
 * 打开一个SocketChannel并连接到互联网上的某台服务器。
 * 一个新连接到达ServerSocketChannel时，会创建一个SocketChannel。
 * @author Liu
 *
 */
public class Nio_8_SocketChannel {
	
	public static void main(String[] args) throws IOException {
		
	}
	
	
	public static void mainTest2(String[] args) throws FileNotFoundException, IOException {
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false); // 非阻塞模式，在connect建立之前就继续下面的操作
		channel.connect(new InetSocketAddress("localhost", 11111));
		
		while( ! channel.finishConnect()) { // 没有完成连接之前，可以阻塞，确定连接

			ByteBuffer buffer = ByteBuffer.allocate(48);
			
			int read;
			while((read = channel.read(buffer)) != -1 ) { // 非阻塞可能没有读到内容
				
			}
			
			ByteBuffer writeBuffer = ByteBuffer.allocate(48);
			
			String data = "ehwowoeowej";
			
			writeBuffer.clear();
			writeBuffer.put(data.getBytes());
			
			writeBuffer.flip(); // 切换到读模式：position 设为0
			
			while(writeBuffer.hasRemaining()) { // 非阻塞可能没有写完就返回
				channel.write(writeBuffer);
			}
		}
		
		channel.close();
	}
	
	public static void mainTest(String[] args) throws FileNotFoundException, IOException {
		SocketChannel channel = SocketChannel.open();
		channel.connect(new InetSocketAddress("localhost", 11111));
		
		ByteBuffer buffer = ByteBuffer.allocate(48);
		
		int read;
		while((read = channel.read(buffer)) != -1 ) {
			
		}
		
		ByteBuffer writeBuffer = ByteBuffer.allocate(48);
		
		String data = "ehwowoeowej";
		
		writeBuffer.clear();
		writeBuffer.put(data.getBytes());
		
		writeBuffer.flip(); // 切换到读模式：position 设为0
		
		while(writeBuffer.hasRemaining()) {
			channel.write(writeBuffer);
		}
		
		channel.close();
	}

}
