package study.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Selector（选择器）是Java NIO中能够检测一到多个NIO通道，
 * 并能够知晓通道是否为诸如读写事件做好准备的组件。
 * 这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
 * @author Liu
 *
 */
public class Nio_7_FileChannel {
	
	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.bind(new InetSocketAddress("localhost", 11111));
		channel.accept();
		channel.configureBlocking(false); // 非阻塞
		
		SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_WRITE, "hello world");
		
		System.out.println(selectionKey.attachment());
		
		System.out.println(selectionKey.readyOps());
		
		while(true) {
			int readyChannelNum = selector.select();
			if(readyChannelNum == 0) continue;
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			if(iterator.hasNext()) {
				SelectionKey key = iterator.next();
				if(key.isAcceptable()) {
					
				} else if(key.isConnectable()) {
					
				} else if(key.isReadable()) {
					
				} else if(key.isWritable()) {
					
				}
				iterator.remove(); // 必须要移除，不然下次还是就绪的
			}
		}
		
	}
	
	
	public static void mainTest(String[] args) throws FileNotFoundException, IOException {
		// 创建selector处理三个channel
		// 1、创建
		Selector selector = Selector.open();
		SelectableChannel channel = null;
		// 2、向selector注册管道：管道必须是非阻塞的，FileChannel不能切换为非阻塞
		// 第二个参数是：interest集合，Selector监听channel的什么事件感兴趣，可以为：Connect、Accept、Read、Write，可以位或连接
		SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE); // 读就绪
		
		// interest集合
		int interestSet = selectionKey.interestOps();
		boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
		boolean isInterestedInConnect = (interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;
		boolean isInterestedInRead    = (interestSet & SelectionKey.OP_READ) == SelectionKey.OP_READ;
		boolean isInterestedInWrite   = (interestSet & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE;

		// ready集合
		int readyOps = selectionKey.readyOps();
		selectionKey.isAcceptable();
		selectionKey.isConnectable();
		selectionKey.isReadable();
		selectionKey.isWritable();
		
		// 获取channel和Selector
		channel = selectionKey.channel();
		selector = selectionKey.selector();
		
		// 附加的对象
		Object attachObject = new Object();
		selectionKey.attach(attachObject);
		Object attachment = selectionKey.attachment();
		
		selectionKey = channel.register(selector, SelectionKey.OP_READ, attachObject);
		
//		int select()
//		int select(long timeout)
//		int selectNow()
		
		int select = selector.select(); // 监听事件：管道就绪
		Set<SelectionKey> selectedKeys = selector.selectedKeys(); // 如果有事件
		Iterator<SelectionKey> iterator = selectedKeys.iterator();
		while(iterator.hasNext()) {
			SelectionKey key = iterator.next();
			if(key.isAcceptable()) {
				
			} else if(key.isConnectable()) {
				
			} else if(key.isReadable()) {
				
			} else if(key.isWritable()) {
				
			}
			iterator.remove(); // 必须要移除，不然下次还是就绪的
		}
		
//		selector.wakeup() // 从当前或下一个select()中返回
		selector.close(); // 关闭当前的selector
	}

}
