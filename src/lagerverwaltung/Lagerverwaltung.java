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
	 * 
	 */
	public void LagerbestandAusgeben() 
	{
		
	}
	
	/**
	 * 
	 * @param arbeiter
	 * @param art
	 * @param anzahl
	 * @param preis
	 */
	public void WareneingangBuchen(Mitarbeiter arbeiter, Artikel art, int anzahl, double preis) 
	{
		
	} 

	
/**
 * 
 * @param arbeiter
 * @return
 */
	public Bestellbestaetigung BestellungAusführen(Mitarbeiter arbeiter,List<Bestellposten> posten) 
	{
		return null;
		//Todo:
	}
	
	public void AddToLagerPosten(Lagerposten posten) 
	{
		
	}

	public List<Lagerposten> getLagerPosten() {
		return lagerPosten;
	}
}
