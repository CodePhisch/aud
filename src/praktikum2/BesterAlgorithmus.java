package praktikum2;

public class BesterAlgorithmus
{
	public static double g1(int n)
	{
		return 1000 * n;
	}

	public static double g2(int n)
	{
		return 100 * n * ( Math.log10(n + 1) / Math.log10(2) );
	}

	public static double g3(int n)
	{
		return 10 * ( n * n );
	}

	public static double g4(int n)
	{
		return Math.pow(n, 3);
	}

	public static double g5(int n)
	{
		return Math.pow(2, n);
	}

	public static double g6(int n)
	{
		double factorial = 1.0;
		
		for(int i = 1; i <= n; i++) {
			factorial *= i;
		}
		
		return factorial;
	}
	
	public static int gewinnerFuerN(int n)
	{
		double[] time = new double[6];
		time[0] = g1(n);
		time[1] = g2(n);
		time[2] = g3(n);
		time[3] = g4(n);
		time[4] = g5(n);
		time[5] = g6(n);
		
		int minIndex = 0;
		double minValue = time[0];
		
		for(int i = 1; i < time.length; i++) {
			if(time[i] < minValue) {
				minIndex = i;
			}
		}
		
		return minIndex + 1;
	}

	public static void main(String[] args)
	{
		for(int n = 1; n <= 2000; n++) {
			System.out.println("Zahl: " + n + " -> Algorithmus: " + gewinnerFuerN(n));
		}
	}
}