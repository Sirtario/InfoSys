package lagerverwaltung;

/**
 * Bestellbestaetigung-Objekt mitsamt Information, ob bereits ausgeführt und Gesamtpreis
 * @author Maximilian Schumann
 */
public class Bestellbestaetigung {

    private boolean ausgefuehrt;
    private double gesamtpreis;

    /**
     * Erstellung eines Bestellbestaetigung-Objektes:
     * @param ausgefuehrt - Bestellbestätigung bereits gegeben?
     * @param gesamtpreis - Gesamtpreis der Bestellung
     */
    public Bestellbestaetigung(boolean ausgefuehrt, double gesamtpreis)
    {
        this.ausgefuehrt = ausgefuehrt;
        this.gesamtpreis = gesamtpreis;
    }


    /**
     * Rückgabe, ob Bestellbestätigung
     * @return von ausgefuehrt
     */
    public boolean isAusgefuehrt()
    {
        return ausgefuehrt;
    }


    /**
     * Rückgabe des Gesamtpreises
     * @return gesamtpreis
     */
    public double getGesamtpreis()
    {
        return gesamtpreis;
    }

}
