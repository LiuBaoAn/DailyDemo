package test.matcher;

public class TestMatch {

	public static void main(String[] args) {
		String regex = "^\\d%?$|^[1-9]\\d%?$|100%?";
		System.out.println("123%".matches(regex ));
		System.out.println("23%".matches(regex ));
		System.out.println("0%".matches(regex ));
		System.out.println("23%1".matches(regex ));
		System.out.println("3%".matches(regex ));
		System.out.println("100%".matches(regex ));
		System.out.println("12.3%".matches(regex ));
		System.out.println(".123%".matches(regex ));
		System.out.println("0.123%".matches(regex ));
		System.out.println("23%2".matches(regex ));
		System.out.println("23".matches(regex ));
		System.out.println("100".matches(regex ));
		System.out.println("0".matches(regex ));
		System.out.println("".matches(regex ));
	}

}
