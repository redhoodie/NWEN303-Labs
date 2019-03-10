package part1;

import static part1.ForkJoinExercise.*;

import java.util.List;

public class Versions {
	public static String parallel1() {
		List<String>abcd=runInParallel(()->doA(),()->doB(),()->doC(),()->doD());
		String a=abcd.get(0); String b=abcd.get(1);
		String c=abcd.get(2); String d=abcd.get(3);
		List<String>res=runInParallel(()->doAB(a,b),()->doCD(c,d));
		return doAll(res.get(0),res.get(1));
	}
	public static String parallel2() {
		List<String>res=runInParallel(
				()->{
					List<String>ab=runInParallel(()->doA(),()->doB());
					return doAB(ab.get(0),ab.get(1));
				},
				()->{
					List<String>cd=runInParallel(()->doC(),()->doD());
					return doCD(cd.get(0),cd.get(1));
				});
		return doAll(res.get(0),res.get(1));
	}
	public static String sequential() {
		String a=doA(); String b=doB();
		String c=doC(); String d=doD();
		String ab=doAB(a,b);
		String cd=doCD(c,d);
		return doAll(ab,cd);
	}
}
