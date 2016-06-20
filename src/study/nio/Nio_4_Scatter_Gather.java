package study.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Nio_4_Scatter_Gather {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		RandomAccessFile aFile = new RandomAccessFile("f:/test.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		System.out.println(inChannel.size());
		System.out.println(inChannel.getClass().getName());  //sun.nio.ch.FileChannelImpl
		
//		inChannel.transferFrom(src, position, count);
//		inChannel.transferTo(position, count, target);
		
		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {

			System.out.println("Read " + bytesRead);
			buf.flip();
	
			while(buf.hasRemaining()){
				System.out.print((char) buf.get());
			}
	
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}

}
