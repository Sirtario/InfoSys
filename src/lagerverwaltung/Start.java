package lagerverwaltung;

import java.util.ArrayList;
import java.util.LinkedList;
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
		lager.berechtigungErteilen(mitarbeiter2);

		lager.wareneingangBuchen(mitarbeiter1, artikel2, 150, 2);
		lager.wareneingangBuchen(mitarbeiter1, artikel1, 300, 2);
		lager.addToLagerPosten(new Lagerposten(artikel1, 45, 3.02));
		lager.wareneingangBuchen(mitarbeiter2, artikel1, 23700, 0.21);
		lager.lagerbestandAusgeben();



		//Ausgabe aller Mitarbeiter
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

		System.out.println("----");
		System.out.println("Lagerposten");
		for (Lagerposten lagposten : lager.getLagerPostenListe())
		{
			System.out.println(lagposten.getArtikel().getId() + " " + lagposten.getPreis() + " " + lagposten.getLagerbestand());
		}
		System.out.println("----");

		//Testen ohne Berechtigung
		lager.berechtigungEntziehen(mitarbeiter1);
		lager.wareneingangBuchen(mitarbeiter1, artikel2, 20, 0.5);
		Bestellposten besposten3 = new Bestellposten("2", 2);
		List<Bestellposten> Bestellung2 = new ArrayList<>();
		Bestellung2.add(besposten3);
		lager.bestellungAusfuehren(mitarbeiter1, Bestellung2);

		System.out.println("Lagerposten");
		for (Lagerposten lagposten : lager.getLagerPostenListe())
		{
			System.out.println(lagposten.getArtikel().getId() + " " + lagposten.getPreis() + " " + lagposten.getLagerbestand());
		}

		//Verursacht eine Exception, weil Bestellpostenliste leer ist
		//List<Bestellposten> leereListe = new ArrayList<>();
		//lager.bestellungAusfuehren(mitarbeiter1, leereListe);

		//Verursacht ebenfalls eine Exception, weil das Lager leer ist
		//lager.getLagerPostenListe().clear();
		//lager.bestellungAusfuehren(mitarbeiter1, besPostenListe);

		//Mitarbeiter hinzufügen, der leere Strings hat
		//Mitarbeiter mitarbeiter3 = new Mitarbeiter("", "");
		//lager.berechtigungErteilen(mitarbeiter3);

		//Mitarbeiter Rechte geben, der bereits Rechte hat
		//Sollte keinerlei Effekt haben (außer aufs Log)
		lager.berechtigungErteilen(mitarbeiter1);
		lager.berechtigungErteilen(mitarbeiter1);
		System.out.println("----");
		System.out.println("Mitarbeiter");
		for (Mitarbeiter arbeiter : lager.getBerechtigteMitarbeiter())
		{
			System.out.println(arbeiter.getId() + " " + arbeiter.getName());
		}
		System.out.println("----");

		//Sollte Exception werfen, da der Mitarbeiter beim zweiten Löschen schon keine Berechtigung mehr hat
		//lager.berechtigungEntziehen(mitarbeiter1);
		//lager.berechtigungEntziehen(mitarbeiter1);

		//Sollte Exception werfen, da Anzahl nicht <= 0 sein darf
		//lager.wareneingangBuchen(mitarbeiter1, artikel1, -5, 50);
		//Sollte Exception werfen, da Preis nicht < 0 sein darf
		//lager.wareneingangBuchen(mitarbeiter1, artikel1, 27, -5);

		lager.getLagerPostenListe().clear();
		lager.addToLagerPosten(new Lagerposten(artikel1, 50, 2));
		Bestellposten besposten4 = new Bestellposten("1", 50);
		Bestellposten besposten5 = new Bestellposten("2" , 50);
		List<Bestellposten> ListeOhneVeraenderung = new ArrayList<>();
		ListeOhneVeraenderung.add(besposten4);
		ListeOhneVeraenderung.add(besposten5);
		System.out.println("Hier darf keine Veränderung passieren!");
		for (Lagerposten lagposten : lager.getLagerPostenListe())
		{
			System.out.println(lagposten.getArtikel().getId() + " " + lagposten.getPreis() + " " + lagposten.getLagerbestand());
		}
		Bestellbestaetigung bestellbestaetigung2 = lager.bestellungAusfuehren(mitarbeiter1, ListeOhneVeraenderung);
		for (Lagerposten lagposten : lager.getLagerPostenListe())
		{
			System.out.println(lagposten.getArtikel().getId() + " " + lagposten.getPreis() + " " + lagposten.getLagerbestand());
		}

	}



}
