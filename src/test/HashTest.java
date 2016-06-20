package test;

public class HashTest {

	public static void main(String[] args) {
//		System.out.println("".hashCode());
//		System.out.println("123".hashCode());
//		System.out.println("abc".hashCode());
//		System.out.println("123abc".hashCode());
		hhash("");
		hhash("123");
		hhash("abc");
		hhash("123abc");
		HashTest hashTest = new HashTest();
		System.out.println(hashTest.hashCode());
		Object o = "123";
		System.out.println(o.hashCode());
		
	}

	public static void hhash(String str) {

		int off = 0;
		int h = 0;
		char val[] = str.toCharArray();

		for (int i = 0; i < str.length(); i++) {
			h = 31 * h + val[off];
			off++;
		}
		System.out.println(h);
	}
}
