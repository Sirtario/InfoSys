package lagerverwaltung;

/**
 * Repräsentiert einen Mitarbeiter
 * @author Phillip Eckstein
 */
public class Mitarbeiter
{
	private final String id;
	
	private final String name;
	
	/**
	 * Gibt die ID des Mitarbeiters zurück
	 * @return Id des Mitarbeiters
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gibt den Namen des Mitarbeites zurück
	 * @return name des Mitarbeiters
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Erstellt einen Mitarbeiter mitsamt ID und Namen
	 * @param id = die eindeutige Identifikation eines Mitarbeiters, kann später nicht mehr geändert werden
	 * @param name + Name des Mitarbeiters
	 */
	public Mitarbeiter(String id, String name) 
	{
		this.id = id;
		this.name = name;
	}
}
