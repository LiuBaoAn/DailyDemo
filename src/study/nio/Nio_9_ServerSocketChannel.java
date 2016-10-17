package study.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector（选择器）是Java NIO中能够检测一到多个NIO通道，
 * 并能够知晓通道是否为诸如读写事件做好准备的组件。
 * 这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
 * @author Liu
 *
 */
public class Nio_9_ServerSocketChannel {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocketChannel channel = ServerSocketChannel.open();
//		channel.bind(new InetSocketAddress("localhost", 11111));
		channel.accept();
		channel.configureBlocking(false); // 非阻塞
		
		
	}
	
	
	public static void mainTest(String[] args) throws FileNotFoundException, IOException {

		ServerSocketChannel channel = ServerSocketChannel.open();
//		channel.bind(new InetSocketAddress("localhost", 11111));
//		channel.socket().bind(new InetSocketAddress("localhost", 11111));

		channel.accept();
		channel.configureBlocking(false); // 非阻塞
		
		
		while (true) {
			SocketChannel socketChannel = channel.accept(); // 如果非阻塞，会立即返回，socketChannel可能为空
			if(socketChannel != null) { 
				
			}
			
		}
		
		
//		channel.close(); // 关闭
		
	}

}
