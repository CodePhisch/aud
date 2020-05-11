package praktikum3;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;

public class ListeGUI extends Frame
{
	//Attribute
	protected Label wertFuehrungstext;
	protected TextField wertTextfeld;
	protected Label positionFuehrungstext;
	protected TextField positionTextfeld;
	protected Button einfuegenDruckknopf;
	protected Button anhaengenDruckknopf;
	protected Button entfernenDruckknopf;
	protected Button alleEntfernenDruckknopf;
	protected Button traversierenDruckknopf;
	protected Button suchenDruckknopf;
	protected Label ausgabeFuehrungstext;
	protected TextArea ausgabeTextbereich;

	protected Liste<Character> eineListe;
	protected ListeCanvas eineZeichenflaeche; //Zeichenfläche für die Animation

	//Operationen
	public ListeGUI()
	{
		setTitle("Verkettete Liste");
		setLayout(null);
		setSize(492,315);
		add(eineZeichenflaeche = new ListeCanvas());
		eineZeichenflaeche.setBounds(32,32,430,80);
		add(wertFuehrungstext= new Label());
		wertFuehrungstext.setText("Wert:");
		wertFuehrungstext.setBounds(30,112,58,23);
		add(wertTextfeld = new TextField());
		wertTextfeld.setBounds(90,112,45,23);
		add(positionFuehrungstext= new Label());
		positionFuehrungstext.setText("Position:");
		positionFuehrungstext.setBounds(30,137,58,23);
		add(positionTextfeld = new TextField());
		positionTextfeld.setBounds(90,137,45,23);
		add(einfuegenDruckknopf = new Button());
		einfuegenDruckknopf.setLabel("Einfügen");
		einfuegenDruckknopf.setBounds(30,170,100,23);
		add(anhaengenDruckknopf = new Button());
		anhaengenDruckknopf.setLabel("Anhängen");
		anhaengenDruckknopf.setBounds(140,170,100,23);
		add(entfernenDruckknopf = new Button());
		entfernenDruckknopf.setLabel("Entfernen");
		entfernenDruckknopf.setBounds(250,170,100,23);
		add(alleEntfernenDruckknopf = new Button());
		alleEntfernenDruckknopf.setLabel("Alle entfernen");
		alleEntfernenDruckknopf.setBounds(360,170,100,23);
		add(traversierenDruckknopf = new Button());
		traversierenDruckknopf.setLabel("Traversieren");
		traversierenDruckknopf.setBounds(30,220,100,23);
		add(suchenDruckknopf = new Button());
		suchenDruckknopf.setLabel("Suchen");
		suchenDruckknopf.setBounds(140,220,100,23);
		add(ausgabeFuehrungstext= new Label());
		ausgabeFuehrungstext.setText("Ausgabe:");
		ausgabeFuehrungstext.setBounds(30,255,58,23);
		add(ausgabeTextbereich = new TextArea("", 1, 1000, TextArea.SCROLLBARS_NONE));
		ausgabeTextbereich.setBounds(90,255,370,23);
		ausgabeTextbereich.setEnabled(false);

		// Registrieren der Ereignisabhörer
		AktionsAbhoerer einAktionsAbhoerer = new AktionsAbhoerer();
		einfuegenDruckknopf.addActionListener(einAktionsAbhoerer);
		anhaengenDruckknopf.addActionListener(einAktionsAbhoerer);
		entfernenDruckknopf.addActionListener(einAktionsAbhoerer);
		alleEntfernenDruckknopf.addActionListener(einAktionsAbhoerer);
		suchenDruckknopf.addActionListener(einAktionsAbhoerer);
		traversierenDruckknopf.addActionListener(einAktionsAbhoerer);

		// Test für Verkettung zweier Listen
		eineListe = new Liste<Character>();
		eineListe.anfuegen(new Character('A'));
		eineListe.anfuegen(new Character('B'));
		Liste<Character> zweiteListe = new Liste<Character>();
		zweiteListe.anfuegen(new Character('C'));
		zweiteListe.anfuegen(new Character('D'));
		eineListe.verketten(zweiteListe);

		eineZeichenflaeche.zeichneNeu(eineListe);

		addWindowListener(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
	}

	//Innere Klasse für die Ereignisabhörer
	class AktionsAbhoerer implements ActionListener
	{
		private Character getCharacter()
		{
			final String s = wertTextfeld.getText();

			return (s.length() > 0) ? s.charAt(0) : '?';
		}

		private int getPosition()
		{
			try
			{
				return Integer.valueOf(positionTextfeld.getText());
			}
			catch(NumberFormatException e)
			{
				ausgabeTextbereich.setText("Es wurde kein Position angegeben. Standardwert: 0 (Listenanfang)");
			}

			return 0;
		}

		private void finish()
		{
			wertTextfeld.setText("");
			positionTextfeld.setText("");

			eineZeichenflaeche.zeichneNeu(eineListe);
		}

		private void finish(final String ausgabe)
		{
			ausgabeTextbereich.setText(ausgabe);

			finish();
		}

		public void actionPerformed(ActionEvent event)
		{
			Object o = event.getSource();
			ausgabeTextbereich.setText("");

			if (o == einfuegenDruckknopf)
			{
				eineListe.einfuegen(getCharacter(), getPosition());
				finish();

				return;
			}

			if (o == anhaengenDruckknopf)
			{
				eineListe.anfuegen(getCharacter());
				finish();

				return;
			}

			if (o == entfernenDruckknopf)
			{
				if (eineListe.entfernen(getPosition()) == null)
					ausgabeTextbereich.setText("Es wurde kein Opfer gefunden!");

				finish();

				return;
			}

			if (o == suchenDruckknopf)
			{
				final int position = eineListe.suchen(getCharacter());
				finish(position == -1 ? "Der Wert wurde nicht gefunden." : "Der gesuchte Wert wird zum ersten Mal an Position " + position + " gefunden.");

				return;
			}

			if (o == alleEntfernenDruckknopf)
			{
				finish(eineListe.entferneWerte(getCharacter()) + " Elemente wurden entfernt.");

				return;
			}

			if (o == traversierenDruckknopf)
			{
				String ausgabe = "";

				ListeIterator<Character> e = eineListe.iterator();
				while (e.hasNext())
					ausgabe += e.next() + " ";

				finish(ausgabe);

				return;
			}

			finish();
		}
	}
}