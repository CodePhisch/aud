package praktikum5;

public class Fibonacci {
	
	public static long fibonacciOhneRekursion(int n) {
		long fib1 = 0;
		long fib2 = 1;
		
		for(int i = 0; i < n - 2; i++) {
			long neu = fib1 + fib2;
			fib1 = fib2;
			fib2 = neu;
		}
		if(n == 1) {
			return fib1;
		} else {
			return fib2;
		}
	}
	
	public static long fibonacciMitRekursion(int n) {
		if(n == 1) return 0;
		if(n == 2) return 1;
		return fibonacciMitRekursion(n - 1) + fibonacciMitRekursion(n - 2);
	}
	
	public static void main(String[] args) {
		int max = 50;
		StopUhr su = new StopUhr();
		
		su.start();
		for(int i = 1; i <= max; i++) {
			System.out.println("Die " + i + "te Fibonacci-Zahl lautet: " + fibonacciOhneRekursion(i) + "(ohne Rekursion)");
		}
		su.stop();
		System.out.println("Dauer ohne Rekusion: " + su.getDuration() + "s");
		System.out.println("---------------------------------------------");
		
		su.start();
		for(int j = 1; j <= max; j++) {
			System.out.println("Die " + j + "te Fibonacci-Zahl lautet: " + fibonacciMitRekursion(j) + "(mit Rekursion)");
		}
		su.stop();
		System.out.println("Dauer mit Rekusion: " + su.getDuration() + "s");
	}
}
