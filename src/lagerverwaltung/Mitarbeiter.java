package lagerverwaltung;
/**
 * Reperesentiert einen Mitarbeiter
 * @author Phillip Eckstein
 *
 */
public class Mitarbeiter {
	private final String id;
	
	private String name;
	
	/**
	 * Gibt die ID des Mitarbeiters zur�ck
	 * @return Id des Mitarbeiters
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Gibt den Namen des Mitarbeites zur�ck
	 * @return name des Mitarbeiters
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param id = die eindeutige Identifikation eines Mitarbeiters, kann sp�ter nicht mehr ge�ndert werden
	 * @param name + Name des Mitarbeiters
	 */
	public Mitarbeiter(String id, String name) 
	{
		this.id = id;
		this.name = name;
	}
}
