package study.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class Nio_5_Transfer {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		RandomAccessFile aFile = new RandomAccessFile("f:/test.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

//		inChannel.transferFrom(src, position, count);
//		inChannel.transferTo(position, count, target);
		

	}

}
