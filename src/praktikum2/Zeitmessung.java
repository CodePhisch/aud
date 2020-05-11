package praktikum2;

public class Zeitmessung
{
	private static double tuwas()
	{
		return Math.random();
	}

	// Linear
	public static double func1(int n)
	{
		double summe = 0;

		for (int a = 0; a < n; a++)
			summe += tuwas();

		return summe;
	}

	// Quadratisch
	public static double func2(int n)
	{
		double summe = 0;

		for (int a = 0; a < n; a++)
			for(int b = 0; b < n; b++)
				summe += tuwas();

		return summe;
	}

	// log2(n)
	public static double func6(int n)
	{
		double summe = 0;

		while (n > 0)
		{
			summe += tuwas();
			n /= 2;
		}

		return summe;
	}

	public static void main(String[] args)
	{
		StopUhr su = new StopUhr();
		
		for(int n = 10; n <= 1000000000; n *= 10) {
			su.start();
			func1(n);
			su.stop();
			System.out.print("n = " + n + " -> A1: " + su.getDuration() + "s ");
			
			/*
			su.start();
			func2(n);
			su.stop();
			System.out.print(", A2: " + su.getDuration() + "s ");
			*/
			
			su.start();
			func6(n);
			su.stop();
			System.out.print(", A6: " + su.getDuration() + "s ");
			System.out.println();
		}
	}
}