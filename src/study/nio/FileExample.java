package study.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileExample {

	public static void main(String[] args) {
		String mode = "rw";
		String name = "";
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(name, mode);
			
			FileChannel channel = randomAccessFile.getChannel();
			
			System.out.println("Illegal mode \"" + mode
					       + "\" must be one of "
					       + "\"r\", \"rw\", \"rws\","
					       + " or \"rwd\"");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
