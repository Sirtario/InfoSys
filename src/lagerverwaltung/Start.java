package lagerverwaltung;

import java.util.ArrayList;
import java.util.List;

public class Start {

	public static void main(String[] args) {
		Lagerverwaltung lager = new Lagerverwaltung();

		Mitarbeiter mitarbeiter1 = new Mitarbeiter("1", "Hans");
		Mitarbeiter mitarbeiter2 = new Mitarbeiter("2", "Lucas");

		Artikel artikel1 = new Artikel("1", "Duschgel", "Ein Duschgel für den Mann");
		Artikel artikel2 = new Artikel("2", "Spekulatius", "Spekulatius zur Weihnachtszeit");

		Bestellposten besposten1 = new Bestellposten("1", 50);
		Bestellposten besposten2 = new Bestellposten("2", 27);

		List<Bestellposten> besPostenListe = new ArrayList<>();
		besPostenListe.add(besposten1);
		besPostenListe.add(besposten2);

		lager.berechtigungErteilen(mitarbeiter1);
		//lager.BerechtigungEntziehen(mitarbeiter1);
		lager.berechtigungErteilen(mitarbeiter2);

		lager.wareneingangBuchen(mitarbeiter1, artikel2, 150, 2);
		lager.wareneingangBuchen(mitarbeiter1, artikel1, 300, 2);
		lager.addToLagerPosten(new Lagerposten(artikel1, 45, 3.02));
		lager.wareneingangBuchen(mitarbeiter2, artikel1, 23700, 0.21);
		lager.lagerbestandAusgeben();




		System.out.println("Mitarbeiter");
		for (Mitarbeiter arbeiter : lager.getBerechtigteMitarbeiter())
		{
			System.out.println(arbeiter.getId() + " " + arbeiter.getName());
		}
		System.out.println("----");


		System.out.println("Bestellposten");
		System.out.println(besposten1.getArtikelId() + " " + besposten1.getAnzahl());
		System.out.println(besposten2.getArtikelId() + " " + besposten2.getAnzahl());
		System.out.println("----");

		System.out.println("Lagerposten");
		for (Lagerposten lagposten : lager.getLagerPostenListe())
		{
			System.out.println(lagposten.getArtikel().getId() + " " + lagposten.getPreis() + " " + lagposten.getLagerbestand());
		}
		System.out.println("----");

		System.out.println("Artikel");
		System.out.println(artikel1.getId() + " " + artikel1.getName() + " " + artikel1.getBeschreibung());
		System.out.println(artikel2.getId() + " " + artikel2.getName() + " " + artikel2.getBeschreibung());
		System.out.println("----");


		Bestellbestaetigung bestellbestaetigung = lager.bestellungAusfuehren(mitarbeiter1, besPostenListe);
		System.out.println(bestellbestaetigung.getGesamtpreis());
		System.out.println(bestellbestaetigung.isAusgefuehrt());

		//System.out.println(lager.getLagerPosten().get(0).getLagerbestand()); //Testzwecke
	}


}
