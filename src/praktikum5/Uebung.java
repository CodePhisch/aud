package praktikum5;

public class Uebung {

	public static void rev1(int n) {
		System.out.print(n % 10);
		if (n > 9)
			rev1(n / 10);
	}
	
	public static int rev2(int n) {
		if (n <= 9)
			return n;
		
		int logn = (int)Math.log10(n);
		int zehnHochLogn = (int)Math.pow(10, logn);
		
		return (n % 10) * zehnHochLogn + rev2(n / 10);
	}
	
	public static void iterativ(int n) {
		System.out.print(n % 10);
		while(n >= 10) {
			n /= 10;
			System.out.print(n % 10);
		} 
		System.out.println();
	}
	
	public static int funk1(int n) {
		System.out.println("tuwas1()");
		if (n <= 1) {
			return n;
		} else {
			return n + funk1(n - 1);
		}
	}
	
	public static void proz2(int n) {
		System.out.println("tuwas2()");
		if (n > 0) {
			proz2(n - 1);
			proz2(n - 1);
		}
	}

	public static int ulamRek(int n) {
		System.out.println(n);
		if(n == 1) return 1;
		if(n % 2 == 0) {
			return ulamRek(n / 2);
		} else {
			return ulamRek(3 * n + 1);
		}
	}
	
	public static void ulamIte(int n) {
		do {
			System.out.println(n);
			if(n % 2 == 0) {
				n /= 2;
			} else {
				n = 3 * n + 1;
			}
		} while(n != 1);
		
		System.out.println(1);
	}
	
	public static void main(String[] args) {
		rev1(1234);
		System.out.println("\n" + rev2(1234));
		iterativ(1234);
		
		rev1(5678);
		System.out.println("\n" + rev2(5678));
		iterativ(5678);
		
		// funk1(3);
		// proz2(3);
		
		System.out.println("--- Ulam rekursiv ---");
		ulamRek(18);
		System.out.println("--- Ulam iterativ ---");
		ulamIte(18);
	}
}
