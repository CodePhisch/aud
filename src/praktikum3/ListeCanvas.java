package praktikum3;

import java.awt.*;

public class ListeCanvas extends Canvas
{
	private Liste eineListe;

	public void zeichneNeu(Liste eineListe)
	{
		this.eineListe = eineListe;

		repaint();
	}

	public void paint(Graphics g)
	{
		ListeIterator it = eineListe.iterator();
		int x = 0;
		int y = 30;
		int position = 0;

		while (it.hasNext())
		{
			g.setColor(new Color(0xE8, 0xE8, 0xFF));
			g.fillRect(x+1, y+1, 30, 20);

			g.setColor(Color.blue);
			g.drawRect(x, y, 30, 20);

			g.drawString(it.next().toString(), x + 1, y + 19);

			if (it.hasNext())
			{
				g.setColor(Color.black);
				g.drawLine(x + 30, y + 10, x + 45, y + 10);
				g.drawLine(x + 45, y + 10, x + 40, y + 5);
				g.drawLine(x + 45, y + 10, x + 40, y + 15);
			}

			g.setColor(Color.gray);
			g.drawString("" + position++, x - 1, y + 35);

			x = x + 45;
		}
	}
}