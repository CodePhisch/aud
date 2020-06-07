package praktikum8;

class BBaum<T extends Comparable<T>>
{
	public BKnoten<T> wurzel;

	public BBaum(BKnoten<T> wurzel)
	{
		assert(wurzel != null);

		this.wurzel = wurzel;
	}

	// Wrapper-Methode
	public void traversieren()
	{
		traversieren(wurzel);
	}

	// Eigentliche Implementierung (rekursiv)
	private void traversieren(BKnoten<T> knoten)
	{
		for(int i = 0; i < knoten.kinder.length; i++) {
			if(knoten.kinder[i] != null) {
				traversieren(knoten.kinder[i]);
			}
			if(i < knoten.elemente.length) {
				System.out.println(knoten.elemente[i]);
			}
		}
	}

	// Wrapper-Methode
	public boolean suchen(final T daten)
	{
		assert(daten != null);

		return suchen(daten, wurzel);
	}

	// Eigentliche Implementierung (iterativ)
	private boolean suchen(final T daten, BKnoten<T> knoten)
	{
		for(int i = 0; i < knoten.elemente.length; i++) {
			// Gesuchtes Element gefunden
			if(daten.compareTo(knoten.elemente[i]) == 0) {
				return true;
			// Aktuelles Element größer als gesuchtes Element
			} else if(daten.compareTo(knoten.elemente[i]) < 0) {
				if(knoten.kinder[i] == null) return false;
				return suchen(daten, knoten.kinder[i]);
			}
		}
		
		// Wenn kein größeres Element vorhanden ist, wird versucht im letzten Teilbaum weiter zu suchen
		if(knoten.kinder[knoten.kinder.length - 1] == null) {
			return false;
		} else {
			return suchen(daten, knoten.kinder[knoten.kinder.length - 1]);
		}
		
	}
}