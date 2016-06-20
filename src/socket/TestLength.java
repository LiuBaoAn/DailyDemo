package socket;

import java.io.UnsupportedEncodingException;

public class TestLength {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "|ss|执行|";
		System.out.println(s.getBytes("utf-8").length);

	}

}
