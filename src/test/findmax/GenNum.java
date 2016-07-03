package test.findmax;

import java.io.File;
import java.io.PrintWriter;

public class GenNum {

	public static void main(String[] args) throws Exception {
		java.util.Random random=new java.util.Random();// 定义随机类
		for (int j = 0; j < 10; j++) {
			File file = new File("d:/test" + j + ".txt");
//			FileOutputStream out = new FileOutputStream(file);
			PrintWriter writer = new PrintWriter(file);
			for(int i =0; i<90000000; i++) {
				int result=random.nextInt(9000000);// 返回[0,10)集合中的整数，注意不包括10
				writer.print(result);
				writer.print(",");
			}
			
			writer.flush();
			writer.close();
			System.out.println("finish " + j + " file!");
		}
	}
}
