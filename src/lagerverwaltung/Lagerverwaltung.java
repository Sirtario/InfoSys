package lagerverwaltung;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Phillip Eckstein
 *
 */
public class Lagerverwaltung {
	
	private Set<Mitarbeiter> berechtigteMitarbeiter;
	private List<Lagerposten> lagerPosten;

	private final String dateiName;
	//private PrintWriter writer;
	//Wir benutzen diese Variable nicht, und erzeugen sie stattdessen in einer Methode, sobald geschrieben wird.

	/**
	 * Erstellt eine neue instanz der Lagerverwaltung
	 */
	public Lagerverwaltung() {
		berechtigteMitarbeiter = new HashSet<Mitarbeiter>();
		lagerPosten = new ArrayList<Lagerposten>();
		dateiName = "log.txt";
	}
	
	public Set<Mitarbeiter> getBerechtigteMitarbeiter() {
		return berechtigteMitarbeiter;
	}
	
	/**
	 * Erteilt einem Mitarbeiter Berechtigung
	 * @param arbeiter Der Mitarbeiter, der Berechtigung erhalten soll
	 */
	public void BerechtigungErteilen(Mitarbeiter arbeiter)
	{
		if (arbeiter.getName() == null || arbeiter.getId() == null) throw new IllegalArgumentException("Name oder Id ist null");

		berechtigteMitarbeiter.add(arbeiter);
		SchreibNachrichtInDatei("Berechtigung erteilt für " + arbeiter.getName());
	}


	/**
	 * Entzieht einem Mitarbeiter die Berechtigung
	 * 
	 * @param arbeiter Der Mitarbeiter der die berechtigung engtzogen bekommen soll
	 */
	public void BerechtigungEntziehen(Mitarbeiter arbeiter) 
	{
		if (arbeiter.getName() == null || arbeiter.getId() == null) throw new IllegalArgumentException("Name oder Id ist null");

		if (!berechtigteMitarbeiter.remove(arbeiter)) throw new IllegalArgumentException("Mitarbeiter not found");
		
		SchreibNachrichtInDatei("Berechtigung entzogen für "+ arbeiter.getName());
	}
	
	/**
	 * Gibt den Lagerbestand in die standart konsole aus
	 */
	public void LagerbestandAusgeben() 
	{
		for(Lagerposten posten : lagerPosten) 
		{
			System.out.println("Artikelname: "+ posten.getArtikel().getName()+" Stückzahl: "+posten.getLagerbestand()+" Preis: "+ posten.getPreis());
		}
	}
	
	/**
	 * Bucht einen Wareneingang. 
	 * Wenn es schon Posten mit dem artikel gibt wird der alte preis mit dem neuen Preis überschrieben
	 * @param arbeiter Der Mitarbeiter, der die Buchung vornimmt
	 * @param art Der aertikel, der gebucht wird
	 * @param anzahl die Stückzahl des Artikels
	 * @param preis Der neue Preis
	 */
	public void WareneingangBuchen(Mitarbeiter arbeiter, Artikel art, int anzahl, double preis) 
	{
		if(berechtigteMitarbeiter.contains(arbeiter)) 
		{
			var posten = new Lagerposten(art, anzahl, preis);
			AddToLagerPosten(posten);
			SchreibNachrichtInDatei("Mitarbeiter " + arbeiter.getName()+" hat waren eingebucht:" +art.getName()+" Anzahl: "+anzahl+" Preis:"+preis);
		}
		
	} 

	
	/**
 	* Führt eine Bestellung aus und entfernt die Posten aus dem Lager
 	*
 	* Bestellung kann nur ausgeführt werden, wenn ArtikelID dem System überhaupt bekannt ist UND Lagerbestand >= Bestellpostenmenge
 	* @param arbeiter Der Mitarbeiter, der die bestellung bearbeitet
 	* @param besPostenListe Liste von Posten, die bestellt sind
 	* @return Bestätigung der Bestellung
 	*/
	public Bestellbestaetigung BestellungAusfuehren(Mitarbeiter arbeiter, List<Bestellposten> besPostenListe)
	{
		if (lagerPosten.size() == 0) throw new IllegalArgumentException("LagerPosten ist leer!");
		//Ohne vorhandene Lagerposten kann auch keine Bestellung ausgeführt werden!

		int gesamtpreis = 0;

		for (Bestellposten besPosten : besPostenListe) //besPostenListe enthält mehrere Bestellposten, die abzuarbeiten sind
		{
			for (Lagerposten lagPosten : lagerPosten) //Finden eines Lagerpostens, der die ArtikelID hat, die auch der aktuelle Bestellposten sucht
			{
				if (lagPosten.getArtikel().getId().equals(besPosten.getArtikelId())) //Hat Artikel des Bestellpostens gleiche Artikel wie aktueller Lagerposten?
				{
					if (lagPosten.getLagerbestand() >= besPosten.getAnzahl()) //Gibt es von Artikel mehr Bestand, als Bestellposten verlangt?
					{
						lagPosten.setLagerbestand(lagPosten.getLagerbestand() - besPosten.getAnzahl()); //Lagerpostenbestand wird um Anzahl des Bestellpostens verringert
						gesamtpreis += lagPosten.getPreis() * besPosten.getAnzahl();
					} else
					{
						return new Bestellbestaetigung(false, 0);
						//Bestellposten konnte nicht durchgeführt werden, da zu wenig Bestand vorhanden ist
					}
				} else
				{
					return new Bestellbestaetigung(false, 0);
					//Ist auch nur irgendeine ArtikelID dem System aktuell unbekannt, bricht der gesamte Bestellposten ab
				}
			}
		}
		return new Bestellbestaetigung(true, gesamtpreis);
		//Bestellung erfolgreich
	}
	
	/**
	 * Fügt Lagerposten der Lagerverwaltung hinzu
	 * @param posten der lagerposten, der hinzu gefügt wird
	 */
	public void AddToLagerPosten(Lagerposten posten) 
	{
		if(posten == null) throw new IllegalArgumentException("posten cannot be null");
		
		lagerPosten.add(posten);
	}

	/**
	 * Gibt die Lagerposten zurück
	 * @return Liste mit Lagerposten
	 */
	public List<Lagerposten> getLagerPosten() {
		return lagerPosten;
	}

	/**
	 * Haengt eine Nachricht an die log Datei an
	 * 
	 * quelle: https://howtodoinjava.com/java/io/java-append-to-file/
	 * @param msg die nachricht, die gelogt werden soll 
	 */
	private void SchreibNachrichtInDatei(String msg)
	{
		try 
		{
			try (PrintWriter w = new PrintWriter(new FileWriter(dateiName, true)))
			{
				w.println((new Timestamp(System.currentTimeMillis()).toString()+ " "+ msg));
			}
		}
		catch(Exception e)
		{
			System.out.println("Could not write to file");
		}
	}	
}
