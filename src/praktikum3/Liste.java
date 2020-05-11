package praktikum3;

public class Liste<T>
{
	protected Link<T> anfang;
	protected Link<T> ende;

	public Liste()
	{
		// Leere Liste: alle Zeiger sind null (Standardwerte)
	}

	// Einfachster Fall und gleichzeitig Sonderfall:
	// Element am Anfang einfügen
	public void einfuegen(final T daten)
	{
		// Funktioniert immer
		anfang = new Link<T>(daten, anfang);

		// Bei einer leeren Liste muss auch ende gesetzt werden
		if (ende == null)
			ende = anfang;
	}

	// Einfachster Fall und gleichzeitig Sonderfall:
	// Element am Anfang entfernen
	public T entfernen()
	{
		T opfer = null;

		// Clowns könnten diese Methode bei leerer Liste aufrufen
		if (!istLeer())
		{
			// Bei einer Liste mit nur noch einem Element muss
			// auch ende gelöscht werden
			if (anfang == ende)
				ende = null;

			opfer = anfang.daten;
			anfang = anfang.naechster;
		}

		return opfer;
	}

	// Einfacher Fall: Anfügen am Ende der Liste
	public void anfuegen(final T daten)
	{
		// Neuen Link anlegen
		Link<T> neu = new Link<T>(daten, null);

		// Wenn die Liste leer ist, müssen anfang und ende gesetzt werden
		if (istLeer())
		{
			// Neuen Link als anfang der Liste
			anfang = ende = neu;
		}
		else
		{
			// Anfügen des Elements an das bisherige ende
			ende = ende.naechster = neu;
		}
	}

	// EINFÜGEN AN EINER BESTIMMTEN STELLE
	// Der Programmcode ist so gestaltet, dass er fehlerhafte Eingaben
	// (z.B. eine negative oder zu hohe Position) elegant verarbeitet
	// ohne abzustürzen.
	public void einfuegen(final T daten, int position)
	{
		// Wenn die Liste leer oder die Position 0 ist, entspricht dies
		// einem Einfügen am Anfang
		if (istLeer() || (position <= 0))
		{
			einfuegen(daten);

			return;
		}

		// VORGÄNGER ZUR POSITION FINDEN
		// Dessen Vorgänger muss es geben: lediglich bei einer leeren
		// Liste oder für position==0 gibt es keinen. Genau diese Fälle
		// wurden aber oben bereits behandelt! Wird eine zu hohe position
		// übergeben, wird automatisch das letzte Element zum Vorgänger.
		Link<T> vorgaenger = anfang;

		while ((--position > 0) && (vorgaenger.naechster != null))
			vorgaenger = vorgaenger.naechster;

		// Neues Element erzeugen
		vorgaenger.naechster = new Link<T>(daten, vorgaenger.naechster);

		// Beim Einfügen als letztes Element muss auch ende gesetzt werden
		if (vorgaenger == ende)
			ende = vorgaenger.naechster;
	}

	public T entfernen(int position)
	{
		// Wenn die Liste leer oder die position < 0 ist, wird kein Element
		// entfernt
		if (istLeer() || (position < 0))
			return null;

		// Wenn die position 0 ist, wird am Anfang der Liste gelöscht
		if (position == 0)
			return entfernen();

		// VORGÄNGER ZUR POSITION FINDEN
		// Bei einer leeren Liste oder für position==0 gibt es keinen Vorgänger;
		// genau diese Fälle wurden oben bereits behandelt. Es gibt aber auch
		// keinen Vorgäner, wenn position zu groß ist! In diesem Fall wird das
		// letzte Element NICHT Vorgänger, damit nur tatsächlich existierende
		// Elemente aus der Liste entfernt werden.
		Link<T> vorgaenger = anfang;

		while ((--position > 0) && (vorgaenger != null))
			vorgaenger = vorgaenger.naechster;

		// Gibt es ein Element zum löschen?
		if ((vorgaenger == null) || (vorgaenger.naechster == null))
			return null;

		final T opfer = vorgaenger.naechster.daten;

		// Element löschen und ggf. ende anpassen beim Löschen des letzten
		// Listen-Elements
		if ((vorgaenger.naechster = vorgaenger.naechster.naechster) == null)
			ende = vorgaenger;

		return opfer;
	}

	// Liefert die Position eines bestimmten Elements oder -1 zurück.
	// Existiert ein Element mehrfach, wird die Position des ersten Vorkommens
	// zurückgegeben.
	public int suchen(final T daten)
	{
		// Liste leer?
		if (istLeer())
			return -1;

		Link<T> zeiger = anfang;
		int position = 0;

		// Abfrage auf Gleichheit ist möglich mit equals (Operation von der Klasse Object)
		while ((zeiger != null) && !zeiger.daten.equals(daten))
		{
			position++;
			zeiger = zeiger.naechster;
		}

		return (zeiger == null) ? -1 : position;
	}

	public ListeIterator<T> iterator()
	{
		return new ListeIterator<T>(anfang, ende);
	}

	// Prüfen, ob Liste leer ist
	public boolean istLeer()
	{
		return anfang == null;
	}

	public void verketten(Liste<T> zweiteListe)
	{
		assert(zweiteListe != null);

		this.ende.naechster = zweiteListe.anfang;
	}

	public int entferneWerte(final T opfer)
	{
		int anzGeloeschte = 0;
		
		Link<T> link = this.anfang;
		Link<T> vorgaenger = null;
		
		while(link != null) {
			if(link.daten.equals(opfer)) {
				// Erstes Element soll gelöscht werden
				if(vorgaenger == null) {
					this.anfang = this.anfang.naechster;
				// Letztes Element soll gelöscht werden
				} else if(link.naechster == null) {
					vorgaenger.naechster = null;
					this.ende = vorgaenger;
				// Sonstiges Element soll gelöscht werden
				} else {
					vorgaenger.naechster = link.naechster;
				}
				anzGeloeschte++;
			} else {
				vorgaenger = link;
			}
			
			link = link.naechster;
		}
		

		return anzGeloeschte;
	}
}