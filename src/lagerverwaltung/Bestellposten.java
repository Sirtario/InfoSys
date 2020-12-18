package lagerverwaltung;

public class Bestellposten {

    final private String artikelId;
    private int anzahl;


    /**
     * Erstellung Bestellposten-Objekt
     * @param artikelId ID des Bestellpostens
     * @param anzahl Anzahl der Bestellposten
     */
    public Bestellposten(String artikelId, int anzahl)
    {
        this.artikelId = artikelId;
        this.anzahl = anzahl;
    }


    /**
     * Rückgabe der ArtikelID
     * @return artikelId
     */
    public String getArtikelId() {
        return artikelId;
    }


    /**
     * Rückgabe der Anzahl der Gesamtposten
     * @return anzahl
     */
    public int getAnzahl() {
        return anzahl;
    }

}
