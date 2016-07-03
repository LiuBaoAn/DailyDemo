package test.findmax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;


public class MultiReader {

	public static void main(String[] args) throws Exception {
		File file = new File("d:/test0.txt");
		
		FileInputStream in1 = new FileInputStream(file);
		FileInputStream in2 = new FileInputStream(file);
		FileInputStream in3 = new FileInputStream(file);
		FileInputStream in4 = new FileInputStream(file);
		
		BufferedReader r1 = new BufferedReader(new InputStreamReader(in1));
		BufferedReader r2 = new BufferedReader(new InputStreamReader(in2));
		BufferedReader r3 = new BufferedReader(new InputStreamReader(in3));
		BufferedReader r4 = new BufferedReader(new InputStreamReader(in4));

		for(int i =0; i<9; i++) {
			char[] c = new char[10];
			int read1 = r1.read(c);
			System.out.println(c);
			int read2 = r2.read(c);
			System.out.println(c);
			int read3 = r3.read(c);
			System.out.println(c);
			int read4 = r4.read(c);
			System.out.println(c);
		}
		
	}
}
