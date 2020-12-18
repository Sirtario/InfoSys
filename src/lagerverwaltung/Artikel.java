package lagerverwaltung;

public class Artikel 
{
	private final String id;
	
	private String name;
	
	private String beschreibung;
	
	/**
	 * Gibt den Namen des Artikels zurück
	 * @return Name des Artikels
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen des Artikels
	 * @param name = neuer Name des Artikels
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt die bescgreubung des Artikels zurück
	 * @return beschreibung des Artikels
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Setzt die Beschreibung des Artikels
	 * @param beschreibung = neue beschreibung des Artikels
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * Gibt di Id des Artikels zurück
	 * @return die Id de Artikels
	 */
	public String getId() {
		return id;
	}

	/**
	 * Erstellt einen neuen Artikel mit Name, Id und Beschreibung
	 * @param id Id des Artikels
	 * @param name Name des Artikels
	 * @param beschreibung Beschreibung des Artikels
	 */
	public Artikel(String id, String name, String beschreibung) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
	}
	
}
