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
 * @author Maximilian Schumann
 *
 * Die Lagerverwaltungs-Klasse nach den Vorgaben des InfoSys-Projekt
 * Programmierung 1, WS 2020/2021
 */
public class Lagerverwaltung {
	
	private final Set<Mitarbeiter> berechtigteMitarbeiter;
	/* berechtigteMitarbeiter Set von Mitarbeiter nicht von String, da wir den Namen der Mitarbeiter zum Loggen benutzen
	und kein Auflösen von ID -> Name nötig wird; prinzipiell würde ansonsten die (hoffentlich eindeutige) ID des Mitarbeiters
	hier gespeichert werden */
	private final List<Lagerposten> lagerPostenListe;
	//Variablenname "lagerposten" (laut Klassendiagramm) leicht irreführend/nichtsaussagend über Datentyp, deshalb Umbenennung
	private final String dateiName;
	//private PrintWriter writer;
	//Wir benutzen diese Variable nicht, und erzeugen sie stattdessen in einer Methode, sobald geschrieben wird.


	/**
	 * Erstellt eine neue instanz der Lagerverwaltung.
	 * dateiName zum Loggen wird festgelegt.
	 */
	public Lagerverwaltung() {
		berechtigteMitarbeiter = new HashSet<>();
		lagerPostenListe = new ArrayList<>();
		dateiName = "log.txt"; //Datei, in der geloggt wird
	}


	/**
	 * Erteilt einem Mitarbeiter Berechtigung, die Lagerverwaltung nutzen zu dürfen.
	 * @param arbeiter Der Mitarbeiter, der Befugnisse erhalten soll.
	 */
	public void berechtigungErteilen(Mitarbeiter arbeiter)
	{
		if (arbeiter.getName() == null || arbeiter.getId() == null) throw new IllegalArgumentException("Name oder Id ist null");

		berechtigteMitarbeiter.add(arbeiter);
		schreibNachrichtInDatei("Berechtigung erteilt für " + arbeiter.getName());
	}


	/**
	 * Entzieht einem Mitarbeiter die Rechte auf die Lagerverwaltung.
	 * @param arbeiter Der Mitarbeiter der die berechtigung entzogen bekommen soll.
	 */
	public void berechtigungEntziehen(Mitarbeiter arbeiter)
	{
		if (arbeiter.getName() == null || arbeiter.getId() == null) throw new IllegalArgumentException("Name oder ID ist null");

		if (!berechtigteMitarbeiter.remove(arbeiter)) throw new IllegalArgumentException("Mitarbeiter not found");
		
		schreibNachrichtInDatei("Berechtigung entzogen für "+ arbeiter.getName());
	}


	/**
	 * Gibt den aktuellen Lagerbestand in die Standardkonsole aus.
	 */
	public void lagerbestandAusgeben()
	{
		for(Lagerposten posten : lagerPostenListe)
		{
			System.out.println("Artikelname: " + posten.getArtikel().getName()+ " Stückzahl: " +posten.getLagerbestand()+ " Preis: " + posten.getPreis());
		}
	}


	/**
	 * Bucht einen Wareneingang. 
	 * Wenn es bereits einen Lagerposten mit diesem Artikel gibt, wird der alte Preis mit dem neuen Preis überschrieben.
	 * @param arbeiter Der Mitarbeiter, der die Buchung vornimmt
	 * @param artikel Der Artikel, der eingebucht wird
	 * @param anzahl die Stückzahl des Artikels
	 * @param preis Der Preis des Artikels
	 */
	public void wareneingangBuchen(Mitarbeiter arbeiter, Artikel artikel, int anzahl, double preis)
	{
		//Wareneingang nur zulässig, wenn ein berechtiger Mitarbeiter dies ausführt
		if(berechtigteMitarbeiter.contains(arbeiter)) 
		{
			var posten = new Lagerposten(artikel, anzahl, preis);
			addToLagerPosten(posten);
			schreibNachrichtInDatei("Mitarbeiter " + arbeiter.getName() + " hat Ware eingebucht: " + "ID: " + artikel.getId() + ", " + artikel.getName()+ " Anzahl: " +anzahl+ " Preis: " + preis + "€");
		}
	} 

	
	/**
 	* Führt eine Bestellung aus und ändert/entfernt den/die Lagerposten.
 	* Bestellung kann nur ausgeführt werden, wenn ArtikelID dem System überhaupt bekannt ist UND Lagerbestand >= Bestellpostenmenge
 	* @param arbeiter Der Mitarbeiter, der die bestellung bearbeitet
 	* @param besPostenListe Liste von Posten, die bestellt sind
 	* @return Bestätigung der Bestellung, true wenn erfolgreich, false wenn nicht erfolgreich
 	*/
	public Bestellbestaetigung bestellungAusfuehren(Mitarbeiter arbeiter, List<Bestellposten> besPostenListe)
	{
		if (lagerPostenListe.size() == 0) throw new IllegalArgumentException("LagerPosten ist leer!");
		//Ohne vorhandene Lagerposten kann auch keine Bestellung ausgeführt werden!

		if (besPostenListe.size() == 0) throw new IllegalArgumentException("Bestellpostenliste ist leer!");
		//Ohne vorhandene Bestellposten kann keine Bestellung ausgeführt werden!

		if (!berechtigteMitarbeiter.contains((arbeiter)))
		{
			schreibNachrichtInDatei("Mitarbeiter " + arbeiter.getName() + " hat versucht, eine Bestellung auszuführen, obwohl er nicht befugt ist.");
			return new Bestellbestaetigung(false, 0);
		}

		float gesamtpreis = 0;

		for (Bestellposten besPosten : besPostenListe) //besPostenListe enthält mehrere Bestellposten, die abzuarbeiten sind
		{
			for (Lagerposten lagPosten : lagerPostenListe) //Finden eines Lagerpostens, der die ArtikelID hat, die auch der aktuelle Bestellposten sucht
			{
				if (lagPosten.getArtikel().getId().equals(besPosten.getArtikelId())) //Hat Artikel des Bestellpostens gleiche Artikel wie aktueller Lagerposten?
				{
					if (lagPosten.getLagerbestand() >= besPosten.getAnzahl()) //Gibt es von Artikel mehr oder genau so viel Bestand, als Bestellposten verlangt?
					{
						lagPosten.setLagerbestand(lagPosten.getLagerbestand() - besPosten.getAnzahl()); //Lagerpostenbestand wird um Anzahl des Bestellpostens verringert
						gesamtpreis += lagPosten.getPreis() * besPosten.getAnzahl();
						if (lagPosten.getLagerbestand() == 0) { lagerPostenListe.remove(lagPosten); } //Ist der Bestand eines Lagerpostens 0, gibt es diesen effektiv nicht mehr
						break; //Damit, bei erfolgreichem Abziehen, nicht unnötig nach weiteren Übereinstimmungen der ArtikelID gesucht wird
					} else
					{
						schreibNachrichtInDatei("Mitarbeiter " + arbeiter.getName()+ " hat erfolglos Bestellung ausgeführt, Fehler: ArtikelID " + besPosten.getArtikelId() + " zu wenig im Lager.");
						return new Bestellbestaetigung(false, 0);
						//Bestellposten konnte nicht verarbeitet werden, da zu wenig Bestand vorhanden ist
					}
				} else if (lagPosten == lagerPostenListe.get(lagerPostenListe.size()-1)) //Nur, wenn das gesamte Lager durchsucht worden ist, sind Fehlschläge erlaubt
				{
					schreibNachrichtInDatei("Mitarbeiter " + arbeiter.getName() + " hat erfolglos Bestellung ausgeführt, Fehler: ArtikelID " + besPosten.getArtikelId() + " System nicht bekannt.");
					return new Bestellbestaetigung(false, 0);
					//Ist auch nur irgendeine ArtikelID dem System aktuell unbekannt, bricht der gesamte Bestellposten ab
				}
			}
		}

		//Bestellung ist erfolgreich gewesen und jetzt wird geloggt, welche Artikel rausgingen
		//StringBuilder Vorschlag von IDE; soll laut Recherche wohl bessere Performanz aufweisen und weniger fehleranfällig sein
		StringBuilder IDs = new StringBuilder(); //StringBuilder-Objekt mutable, Strings nicht
		IDs.append(besPostenListe.get(0).getArtikelId()); //Mindestens ein Artikel wird bestellt worden sein

		if (besPostenListe.size() > 1) //Gibt es mehr als einen Artikel, werden diese nacheinander angehangen
		{
			for (int i = 1; i < besPostenListe.size(); i++) //int i = 1, weil Artikel mit Index 0 bereits in IDs ist
			{
				IDs.append(", ").append(besPostenListe.get(i).getArtikelId()); //IDs wird ein Komma und die ID des anzuhängenden Artikels angehängt
			}
		}

		schreibNachrichtInDatei("Mitarbeiter " + arbeiter.getName() + " hat erfolgreich Bestellung ausgeführt. ArtikelIDs: " + IDs + ", Gesamtpreis: " + gesamtpreis +"€");
		return new Bestellbestaetigung(true, gesamtpreis);
		//Bestellung erfolgreich
	}


	/**
	 * Fügt Lagerposten der Lagerverwaltung hinzu
	 * @param posten der Lagerposten, der hinzugefügt wird
	 */
	public void addToLagerPosten(Lagerposten posten)
	{
		if(posten == null) throw new IllegalArgumentException("posten cannot be null");
		
		if(PostenAllreadyExists(posten)) 
		{
			UpdatePosten(posten);
		}
		else 
		{			
			lagerPostenListe.add(posten);
		}
	}
	
	/**
	 * Prüft ob ein Posten mit dem selben artikel bereits existiert
	 * @param posten Posten mit artikel, der geprüft werden soll
	 * @return True wenn es artikel bereits gibt, False wenn nicht
	 */
	private boolean PostenAllreadyExists(Lagerposten posten) 
	{
		for(Lagerposten p : lagerPostenListe) 
		{
			if(p.getArtikel().getId() == posten.getArtikel().getId()) 
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Updated einen vorhandenen Posten mit den Informationen im gegebenen Posten. 
	 * Posten werden anhand der in ihnen gespeicherten Artikel und deren ID verglichen.
	 * Es Wird Der lagerbestand und der preis geupdatet.
	 * 
	 * @param postenupdate ein Lagerposten der die informtionen zum updaten enthält.
	 */
	private void UpdatePosten(Lagerposten postenupdate) 
	{
		Lagerposten postenToBeUpdated = null;
		
		for(Lagerposten p : lagerPostenListe) 
		{
			if(p.getArtikel().getId() == postenupdate.getArtikel().getId()) 
			{
				postenToBeUpdated = p;
			}
		}
		
		if(postenToBeUpdated == null) 
		{
			//Kein übereinstimmender Posten gefunden
			throw new NullPointerException("Could not find Posten to Update");
		}
		
		postenToBeUpdated.setLagerbestand(postenToBeUpdated.getLagerbestand()+postenupdate.getLagerbestand());
		postenToBeUpdated.setPreis(postenupdate.getPreis());
	}

	/**
	 * Gibt die Lagerposten als Liste zurück.
	 * @return Liste mit Lagerposten-Objekten
	 */
	public List<Lagerposten> getLagerPostenListe() {
		return lagerPostenListe;
	}


	/**
	 * Gibt die berechtigten Mitarbeiter als Set zurück.
	 * @return Set mit Mitarbeiter-Objekten
	 */
	public Set<Mitarbeiter> getBerechtigteMitarbeiter() {
		return berechtigteMitarbeiter;
	}


	/**
	 * Haengt eine Nachricht an die Log-Datei an.
	 * Es wird automatisch vor die zu loggende Nachricht ein Timestamp angehangen im ISO8601-Format.; JJJJ-MM-SS hh:mm:ss.fff
	 * Quelle: https://howtodoinjava.com/java/io/java-append-to-file/
	 * @param msg die Nachricht, die geloggt werden soll.
	 */
	private void schreibNachrichtInDatei(String msg)
	{
		//Try-Catch-Konstrukt, damit - sollte irgendein Fehler passieren - das Programm nicht abstürzt
		try 
		{
			//Try-With-Resources-Konstrukt, damit die Ressource nach Nutzung wieder freigegeben wird
			try (PrintWriter writer = new PrintWriter(new FileWriter(dateiName, true)))
			{
				writer.println((new Timestamp(System.currentTimeMillis()).toString()+ " " + msg));
			}
		}
		catch(Exception e) //Exception e -> irgendeine Exception; es werden alle hier abgefangen
		{
			System.out.println("Could not write to file");
		}
	}	
}
