package part1;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForkJoinExercise {
	public static String id(String a, int iterations) {
		Random r = new Random();
		int x = r.nextInt(5);
		for (int i = 0; i <= iterations * 10000000; i++) { x += r.nextInt(5); }
		if(x > 100) { return a; }
		//TODO: try instead to comment all the above code and use the following
		//try {Thread.sleep(iterations*10);}
		//catch (InterruptedException e) {
		// Thread.currentThread().interrupt();
		// throw new Error(e);
		// }
		return a;
	}

	public static String doA() {
		return id("A", 1);
	}
	public static String doB() {
		return id("B", 10);
	}
	public static String doC() {
		return id("C", 2);
	}
	public static String doD() {
		return id("D", 5);
	}
	public static String doAB(String a,String b) {
		return id(a + b, 1);
	}
	public static String doCD(String c,String d) {
		return id(c + d, 10);
	}
	public static String doAll(String ab,String cd) {
		return id(ab + cd,1);
	}
	@SafeVarargs public static <T> List<T> runInParallel(Supplier<T> ...ts) {
		return Stream.of(ts).parallel()
				.map(f -> f.get())
				.collect(Collectors.toList());
	}
}