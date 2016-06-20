package timeunit;

import java.util.concurrent.TimeUnit;

public class TestTimeUnit {
	public static void main(String[] args) {
		System.out.println(TimeUnit.MINUTES.toMillis(1));
		System.out.println(new Long(TimeUnit.MINUTES.toMillis(5)).intValue());
		System.out.println((int) TimeUnit.MINUTES.toMillis(5));
	}
}
