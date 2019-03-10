package part1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestLab1 {

	@Test
	void test() {
		//First, run the code just to trigger the JIT. How to run it?
		long t0 = System.currentTimeMillis(); //take the time at start
		//run one version
		Versions.sequential();
		long t1 = System.currentTimeMillis();//take the time again
		long timeSeq = t1 - t0;
		
		
		//run another version... take the time again..
		t0 = System.currentTimeMillis(); //take the time at start
		//run one version
		Versions.parallel1();
		t1 = System.currentTimeMillis();//take the time again
		long timePar1 = t1 - t0;

		//is there a better way?
		//run also the third version
		//run another version... take the time again..
		t0 = System.currentTimeMillis(); //take the time at start
		//run one version
		Versions.parallel2();
		t1 = System.currentTimeMillis();//take the time again
		long timePar2 = t1 - t0;

		//print out results (only to give us a sense of accomplishment)
		System.out.println("Time Seq=" + timeSeq);
		System.out.println("Time Par1=" + timePar1);
		System.out.println("Time Par2=" + timePar2);
		//assert the performance relationship
		assertTrue(timeSeq > timePar1);
		assertTrue(timeSeq > timePar2);
		assertTrue(timePar1 > timePar2);
	}
}
