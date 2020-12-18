package lagerverwaltung;

/**
 * Ein Lagerposten-Objekt; stellt Lagerposten eines Artikels mitsamt Informationen dar.
 * @author Maximilian Schumann
 */
public class Lagerposten {


    private int lagerbestand;
    private double preis;
    private Artikel artikel;


    /**
     * Erstellt ein Lagerposten-Objekt
     * @param artikel Lagerposten erhält bei Erstellung ein Artikel-Objekt
     * @param lagerbestand Lagerposten erhält bei Erstellung Lagerbestand eines Artikels
     * @param preis Lagerposten erhält bei Erstellung den preis eines Artikels
     */
    public Lagerposten(Artikel artikel, int lagerbestand, double preis)
    {
        this.artikel = artikel;
        this.lagerbestand = lagerbestand;
        this.preis = preis;
    }


    /**
     * Gibt Anzahl des Lagerbestands des Lagerpostens zurück
     * @return lagerbestand
     */
    public int getLagerbestand() {
        return lagerbestand;
    }


    /**
     * Setzt den Lagerbestand des Lagerpostens
     * @param lagerbestand Anzahl des Lagerbestands
     */
    public void setLagerbestand(int lagerbestand) {
        this.lagerbestand = lagerbestand;
    }


    /**
     * Gibt den Artikel des Lagerpostens zurück
     * @return Artikel des Lagerpostens
     */
    public Artikel getArtikel() {
        return artikel;
    }


    /**
     * Gibt den Preis den Lagerpostens zurück
     * @return Preis des Lagerpostens
     */
    public double getPreis() {
        return preis;
    }


    /**
     * Setzt den Preis des Lagerpostens
     * @param preis Preis des Lagerpostens
     */
    public void setPreis(double preis) {
        this.preis = preis;
    }
}
