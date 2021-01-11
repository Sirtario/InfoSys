package lagerverwaltung;

/**
 * Stellt einen Bestellposten dar mitsamt ArtikelID und Anzahl des geforderten Artikels
 * @author Maximilian Schumann
 */
public class Bestellposten
{

    private final String artikelId;
    private final int anzahl;
    //Kann final sein, da Bestellposten bei der Abarbeitung sowieso nur komplett oder gar nicht ausgeführt werden
    //Zudem kann ein Bestellposten auch gar nicht verändert werden (bisher nicht vorgesehen)


    /**
     * Erstellung Bestellposten-Objekt
     * @param artikelId ID des Bestellpostens
     * @param anzahl Anzahl des Artikels im Bestellposten
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
