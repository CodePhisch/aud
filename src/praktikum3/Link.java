package praktikum3;

public class Link<T>
{
	public T daten;
	public Link<T> naechster;

	public Link(T daten, Link<T> naechster)
	{
		assert(daten != null);

		this.daten = daten;
		this.naechster = naechster;
	}

	public String toString()
	{
		return daten.toString();
	}
}