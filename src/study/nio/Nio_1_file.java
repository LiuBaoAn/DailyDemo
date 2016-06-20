package study.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Nio_1_file {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("f:/test.txt", "rw");
		System.out.println(randomAccessFile.getFilePointer());
		randomAccessFile.seek(3); // 
		
		System.out.println(randomAccessFile.getFilePointer());
		
		randomAccessFile.read();
		System.out.println(randomAccessFile.getFilePointer());
		
		randomAccessFile.write("qwertyuiop[".getBytes());
		System.out.println(randomAccessFile.getFilePointer());
		
		
		
		
		
//		FileInputStream inputStream = new FileInputStream("");
//		inputStream.getChannel();
		
	}

}
