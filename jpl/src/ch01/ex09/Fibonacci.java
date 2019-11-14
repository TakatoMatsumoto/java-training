package ch01.ex09;

public class Fibonacci {
	static final int MAX_INDEX = 9;
	static int number[] = new int[MAX_INDEX];

	static final String TITLE = "Fibonacci series";
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		System.out.println(TITLE);
		number[0] = lo;
		int i = 1;
		while (hi < 50) {
			hi = lo + hi;
			lo = hi - lo;
			number[i++] = lo;
		}
		if(number == null || number.length==0) {
			throw new IllegalArgumentException();
		}else {
			for (int i1 = 0; i1 < number.length; i1++) {
				System.out.println(number[i1] + ",");
			}
		}
	}
}
