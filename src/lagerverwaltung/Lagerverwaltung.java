package lagerverwaltung;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jdk.internal.org.jline.terminal.impl.ExecPty;

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
		
		SchreibNachrichtInDatei("log.txt", ("Einem Mitarbeiter wurde die Berechtigung entzogen"+arbeiter.getName()));
	}
	
	/**
	 * Gibt den Lagerbestand in die standart konsole aus
	 */
	public void LagerbestandAusgeben() 
	{
		
	}
	
	/**
	 * Bucht einen Wareneingang. 
	 * Wenn es schon Posten mit dem artikel gibt wird der alte preis mit dem neuen Preis �berschrieben
	 * @param arbeiter Der Mitarbeiter, der die Buchung vornimmt
	 * @param art Der aertikel, der gebucht wird
	 * @param anzahl die St�ckzahl des Artikels
	 * @param preis Der neue Preis
	 */
	public void WareneingangBuchen(Mitarbeiter arbeiter, Artikel art, int anzahl, double preis) 
	{
		
	} 

	
/**
 * F�hrt eine Bestellung aus und entfernt die Posten aus dem Lager
 * 
 * Bestellungen k�nnen nur ausgef�hrt werden wenn alle posten vollst�ndig vorhanden sind
 * @param arbeiter Der Mitarbeiter, der die bestellung bearbeitet
 * @param posten Liste von Posten, die bestellt sind
 * @return Best�tigung der Bestellung
 */
	public Bestellbestaetigung BestellungAusf�hren(Mitarbeiter arbeiter,List<Bestellposten> posten) 
	{
		return null;
		//Todo:
	}
	
	/**
	 * F�gt Lagerposten der Lagerverwaltung hinzu
	 * @param posten der lagerposten, der hinzu gef�gt wird
	 */
	public void AddToLagerPosten(Lagerposten posten) 
	{
		if(posten == null) throw new IllegalArgumentException("posten cannot be null");
		
		lagerPosten.add(posten);
	}

	/**
	 * Gibt die Lagerposten zur�ck
	 * @return Liste mit Lagerposten
	 */
	public List<Lagerposten> getLagerPosten() {
		return lagerPosten;
	}
	
	private void SchreibNachrichtInDatei(String dateiname, String msg)
	{
		try 
		{
			try (PrintWriter w = new PrintWriter(dateiname))
			{
				w.println(msg);
			}
		}
		catch(Exception e)
		{
			System.out.println("Could not write to file");
		}
	}	
}
