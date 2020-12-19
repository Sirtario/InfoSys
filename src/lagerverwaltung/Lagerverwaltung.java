package lagerverwaltung;

import java.io.PrintWriter;
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
	private PrintWriter writer;

	/**
	 * Erstellt eine neue instanz der Lagerverwaltung
	 */
	public Lagerverwaltung() {
		try
		{
			writer = new PrintWriter("log.txt");
		}
		catch(Exception e)
		{
			//todo: errorhandling
		}
		
		berechtigteMitarbeiter = new HashSet<Mitarbeiter>();
		lagerPosten = new ArrayList<Lagerposten>();
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
		berechtigteMitarbeiter.add(arbeiter);
		writer.println("Berechtigung Erteilt: "+ arbeiter.getName());
	}
	
	/**
	 * Entzieht einem Mitarbeiter die Berechtigung
	 * 
	 * @param arbeiter Der Mitarbeiter der die berechtigung engtzogen bekommen soll
	 */
	public void BerechtigungEntziehen(Mitarbeiter arbeiter) 
	{
		if (!berechtigteMitarbeiter.remove(arbeiter)) throw new IllegalArgumentException("Mitarbeiter not found");
		
		writer.println("Entziehe Mitarbeiter Berechtigung: "+ arbeiter.getName());
	}
	
	/**
	 * Gibt den Lagerbestand in die standart konsole aus
	 */
	public void LagerbestandAusgeben() 
	{
		
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
		
	} 

	
/**
 * Führt eine Bestellung aus und entfernt die Posten aus dem Lager
 * 
 * Bestellungen können nur ausgeführt werden wenn alle posten vollständig vorhanden sind
 * @param arbeiter Der Mitarbeiter, der die bestellung bearbeitet
 * @param posten Liste von Posten, die bestellt sind
 * @return Bestätigung der Bestellung
 */
	public Bestellbestaetigung BestellungAusführen(Mitarbeiter arbeiter,List<Bestellposten> posten) 
	{
		return null;
		//Todo:
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
}
