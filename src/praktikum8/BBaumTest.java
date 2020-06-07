package praktikum8;

public class BBaumTest
{
	public static void main(String[] args)
	{
		// B-Baum erzeugen
		BBaum<Integer> baum = new BBaum<Integer>(
			new BKnoten<Integer>(new Integer[]{ 19, 34 }, new BKnoten[]{
				new BKnoten<Integer>(new Integer[]{ 4, 7, 10, 14 }, new BKnoten[]{
					new BKnoten<Integer>(new Integer[]{ 1, 2, 3 } ),
					new BKnoten<Integer>(new Integer[]{ 5, 6 } ),
					new BKnoten<Integer>(new Integer[]{ 8, 9 } ),
					new BKnoten<Integer>(new Integer[]{ 11, 12, 13 } ),
					new BKnoten<Integer>(new Integer[]{ 15, 16, 17, 18 } )
				} ),
				new BKnoten<Integer>(new Integer[]{ 22, 27, 30 }, new BKnoten[]{
					new BKnoten<Integer>(new Integer[]{ 20, 21 } ),
					new BKnoten<Integer>(new Integer[]{ 23, 24, 25, 26 } ),
					new BKnoten<Integer>(new Integer[]{ 28, 29 } ),
					new BKnoten<Integer>(new Integer[]{ 31, 32, 33 } )
				} ),
				new BKnoten<Integer>(new Integer[]{ 39, 42 }, new BKnoten[]{
					new BKnoten<Integer>(new Integer[]{ 35, 36, 37, 38 } ),
					new BKnoten<Integer>(new Integer[]{ 40, 41 } ),
					new BKnoten<Integer>(new Integer[]{ 43, 44, 45 } )
				} )
			} )
		);

		// B-Baum traversieren
		System.out.println("Traversieren:");

		baum.traversieren();

		// Nach Elementen suchen
		System.out.println("\n\nSuchen:");

		boolean suchen = true;
		for (int a = -50; a < 100; a++)
			suchen &= (baum.suchen(a) == ((a >=1 ) && (a <= 45)));

		System.out.println(suchen ? "Ok" : "Fehler!");
	}
}