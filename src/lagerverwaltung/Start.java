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

		lager.BerechtigungErteilen(mitarbeiter1);
		//lager.BerechtigungEntziehen(mitarbeiter1);
		lager.BerechtigungErteilen(mitarbeiter2);


//		System.out.println("Lagerposten");
//		System.out.println(lagposten1.getArtikel().getName() + " " + lagposten1.getLagerbestand() + " " + lagposten1.getPreis());
//		System.out.println(lagposten2.getArtikel().getName() + " " + lagposten2.getLagerbestand() + " " + lagposten2.getPreis());
//		System.out.println("----");

		System.out.println("Mitarbeiter");
		System.out.println(mitarbeiter1.getId() + " " + mitarbeiter1.getName());
		System.out.println(mitarbeiter2.getId() + " " + mitarbeiter1.getName());
		System.out.println("----");

		System.out.println("Bestellposten");
		System.out.println(besposten1.getArtikelId() + " " + besposten1.getAnzahl());
		System.out.println(besposten2.getArtikelId() + " " + besposten2.getAnzahl());
		System.out.println("----");

		System.out.println("Artikel");
		System.out.println(artikel1.getId() + " " + artikel1.getName() + " " + artikel1.getBeschreibung());
		System.out.println(artikel2.getId() + " " + artikel2.getName() + " " + artikel2.getBeschreibung());
		System.out.println("----");

		lager.WareneingangBuchen(mitarbeiter1, artikel2, 150, 2);
		lager.WareneingangBuchen(mitarbeiter1, artikel1, 300, 2);
		lager.LagerbestandAusgeben();


		Bestellbestaetigung bestellbestaetigung = lager.BestellungAusfuehren(mitarbeiter1, besPostenListe);
		System.out.println(bestellbestaetigung.getGesamtpreis());
		System.out.println(bestellbestaetigung.isAusgefuehrt());

		//System.out.println(lager.getLagerPosten().get(0).getLagerbestand()); //Testzwecke
	}


}
