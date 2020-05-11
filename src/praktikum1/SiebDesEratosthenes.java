package praktikum1;

import java.util.Scanner;

public class SiebDesEratosthenes {

	public static void main(String[] args) {
		// Maximale Zahl einlesen und überprüfen
		Scanner sc = new Scanner(System.in);
		System.out.print("Bitte maximale Zahl eingeben: ");
		int n = 0;
		try {
			n = sc.nextInt();
			sc.close();
		} catch(Exception e) {
			System.out.println("Fehlerhafte Eingabe!");
			return;
		}
		
		if(n <= 0) {
			System.out.println("Zahl liegt außerhalb des zugelassenen Wertebereichs.");
			return;
		}
		
		int counter = 0;
		
		// Sieb initialisieren
		boolean[] sieb = new boolean[n];
		sieb[0] = true;
		
		// Eigentlicher Algorithmus
		for(int i = 1; i <= n; i++) {
			if(sieb[i - 1] != true) {
				counter++;
				if(Math.pow(i, 2) <= n) {
					for(int j = i * 2; j <= n; j+= i) {
						sieb[j - 1] = true;
					}
				}
			} 
		}
		
		System.out.println(counter);
	}
}