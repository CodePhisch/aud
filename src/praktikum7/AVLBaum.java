package praktikum7;

import java.util.Deque;
import java.util.LinkedList;

public class AVLBaum<T extends Comparable<T>>
{
	private AVLKnoten<T> wurzel;
	private boolean hoeheGeaendert;

	// Wird nur für grafische Oberfläche benötigt, ohne
	// diese Methode könnte die gesamte Implementierung
	// des Baumes geheim gehalten werden. Alle öffentlichen
	// Methoden sind parameterlos oder besitzen als
	// einzigen Parameter einen Schlüsselwert
	public AVLKnoten<T> getWurzel()
	{
		return wurzel;
	}

	public boolean istLeer()
	{
		return (wurzel == null);
	}


	// Methoden zum Suchen
	public boolean suchen(final T daten)
	{
		AVLKnoten<T> aktuellerKnoten = wurzel;
		while(aktuellerKnoten != null) {
			// Gesuchtes Element ist kleiner als aktueller Knoten
			if(daten.compareTo(aktuellerKnoten.getDaten()) < 0) {
				aktuellerKnoten = aktuellerKnoten.getKnotenLinks();
			// Gesuchtes Element ist größer als aktueller Knoten
			} else if(daten.compareTo(aktuellerKnoten.getDaten()) > 0) {
				aktuellerKnoten = aktuellerKnoten.getKnotenRechts();
			// Element gefunden
			} else {
				return true;
			}
		}

		return false;
	}


	// Methoden zum Einfügen
	public void einfuegen(final T daten)
	{
		// Setzen der Merker-Variable hoeheGeandert auf false
		// Das wird zwar nach einem Links- oder Rechts-Ausgleich gemacht,
		// aber diese finden nicht statt, wenn ein bereits existierender
		// Schlüssel wiederholt eingefügt wird!
		hoeheGeaendert = false;

		// Beim Einfügen wird der Baum neu zusammengesetzt, um Rotationen
		// zu ermöglichen. Daher tritt hier kein Sonderfall auf, aber die
		// Wurzel muss neu zugewiesen werden.
		wurzel = einfuegenKnoten(daten, wurzel);
	}

	private AVLKnoten<T> einfuegenKnoten(final T daten, AVLKnoten<T> teilbaum)
	{
		if (teilbaum == null)
		{
			hoeheGeaendert = true;

			return new AVLKnoten<T>(daten, null, null);
		}

		// Vergleichs-Ergebnis zwischenspeichern, da compareTo()
		// aufwändig sein kann, und das Ergebnis mehrfach benötigt
		// wird
		final int cmp = daten.compareTo(teilbaum.getDaten());

		if (cmp < 0)
		{
			// Einzufügende Daten sind KLEINER als Daten im aktuellen Knoten
			// und müssen daher im LINKEN Teilbaum eingefügt werden
			teilbaum.setKnotenLinks(einfuegenKnoten(daten, teilbaum.getKnotenLinks()));
			if (hoeheGeaendert)
				teilbaum = linksAusgleich(teilbaum);
		}
		else
			if (cmp > 0)
			{
				// Einzufügende Daten sind GROESSER als Daten im aktuellen Knoten
				// und müssen daher im RECHTEN Teilbaum eingefügt werden
				teilbaum.setKnotenRechts(einfuegenKnoten(daten, teilbaum.getKnotenRechts()));
				if (hoeheGeaendert)
					teilbaum = rechtsAusgleich(teilbaum);
			}

		return teilbaum;
	}

	private AVLKnoten<T> linksAusgleich(AVLKnoten<T> k)
	{
		AVLKnoten<T> x;

		switch (k.getBalance())
		{
			case +1:
			{
				k.setBalance(0);				// Der mit k beginnende Teilbaum ist jetzt balanciert
				hoeheGeaendert = false;
				break;
			}

			case 0:								// Der mit k beginnende Teilbaum ist jetzt linkslastig
			{
				k.setBalance(-1);
				break;
			}

			case -1:							// Ausgleich notwendig
			{
				x = k.getKnotenLinks();

				if (x.getBalance() == -1)		// Fall 3a
				{
					k = rechtsRotation(k, x);
				}
				else
					if (x.getBalance() == +1)	// Fall 3b
					{
						k = lrDoppelRotation(k, x);
						k.setBalance(0);
					}

				k.setBalance(0);
				hoeheGeaendert = false;
			}
		}

		return k;
	}

	private AVLKnoten<T> rechtsAusgleich(AVLKnoten<T> k)
	{
		AVLKnoten<T> x;

		switch (k.getBalance())
		{
			case -1:
			{
				k.setBalance(0);				// Der mit k beginnende Teilbaum ist jetzt balanciert
				hoeheGeaendert = false;
				break;
			}

			case 0:								// Der mit k beginnende Teilbaum ist jetzt rechtslastig
			{
				k.setBalance(+1);
				break;
			}

			case +1:							// Ausgleich notwendig
			{
				x = k.getKnotenRechts();

				if (x.getBalance() == +1)		// Fall 3a
				{
					k = linksRotation(k, x);
				}
				else
					if (x.getBalance() == -1)	// Fall 3b
					{
						k = rlDoppelRotation(k, x);
					}

				k.setBalance(0);
				hoeheGeaendert = false;
			}
		}

		return k;
	}

	private AVLKnoten<T> rechtsRotation(AVLKnoten<T> k, AVLKnoten<T> x)
	{
		k.setKnotenLinks(x.getKnotenRechts());
		x.setKnotenRechts(k);
		k.setBalance(0);

		return x;
	}

	private AVLKnoten<T> lrDoppelRotation(AVLKnoten<T> k, AVLKnoten<T> x)
	{
		AVLKnoten<T> y = x.getKnotenRechts();
		x.setKnotenRechts(y.getKnotenLinks());
		y.setKnotenLinks(x);
		k.setKnotenLinks(y.getKnotenRechts());
		y.setKnotenRechts(k);

		switch (y.getBalance())
		{
			case -1:
			{
				k.setBalance(+1);
				x.setBalance(0);
				break;
			}
			case +1:
			{
				k.setBalance(0);
				x.setBalance(-1);
				break;
			}
			case 0:
			{
				k.setBalance(0);
				x.setBalance(0);
			}
		}

		return y;
	}

	private AVLKnoten<T> linksRotation(AVLKnoten<T> k, AVLKnoten<T> x)
	{
		k.setKnotenRechts(x.getKnotenLinks());
		x.setKnotenLinks(k);
		k.setBalance(0);

		return x;
	}

	private AVLKnoten<T> rlDoppelRotation(AVLKnoten<T> k, AVLKnoten<T> x)
	{
		AVLKnoten<T> y = x.getKnotenLinks();
		x.setKnotenLinks(y.getKnotenRechts());
		y.setKnotenRechts(x);
		k.setKnotenRechts(y.getKnotenLinks());
		y.setKnotenLinks(k);

		switch (y.getBalance())
		{
			case +1:
			{
				k.setBalance(-1);
				x.setBalance(0);
				break;
			}
			case -1:
			{
				k.setBalance(0);
				x.setBalance(+1);
				break;
			}
			case 0:
			{
				k.setBalance(0);
				x.setBalance(0);
			}
		}

		return y;
	}


	// Methode zum Traversieren

	// Pre-Order
	public String traversierePreOrder()
	{
		String ausgabe = "";
		// Stack initialisieren
		Deque<AVLKnoten<T>> stack = new LinkedList<AVLKnoten<T>>();
		
		// Wurzel auf Stack schreiben falls vorhanden
		if(wurzel == null) return ausgabe;
		stack.push(wurzel);
		
		// Solange wiederholen bis Stack leer ist
		while(!stack.isEmpty()) {
			// Aktuell oberstes Element ausgeben
			AVLKnoten<T> top = stack.peek();
			ausgabe += top.getDaten();
			// Oberstes Element vom Stack entfernen
			stack.pop();
			
			// Wenn es rechts weitergeht, dieses Element zunächst auf den Stack schreiben
			if(top.getKnotenRechts() != null) 
				stack.push(top.getKnotenRechts());
			// Dann wenn es links auch noch weiter geht, dieses Element ebenfalls auf den Stack schreiben
			if(top.getKnotenLinks() != null) 
				stack.push(top.getKnotenLinks());
		}
		
		return ausgabe;
	}
}