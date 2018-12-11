package braingain.modell;

//TODO: Auto-generated Javadoc
/**
 * The Enum Kategorie.
 */
public enum Kategorie {

	/** Kategorie logik. */
	LOGIK(new String[] { "Logik", "logik", "logic" }),

	/** Kategorie mathe. */
	MATHE(new String[] { "Mathe", "mathe", "mathematik" }),

	/** Kategorie geografie. */
	GEOGRAFIE(new String[] { "Geographie", "geo", "geografie", "geographie", "erdkunde" }),

	/** Kategorie gedaechtnistraining. */
	GEDAECHTNISTRAINING(new String[] { "Gedaechtnistraining", "gedaechtnistraining", "gehirntraining" });

	/** Welche Namen die Kategorie noch haben kann. */
	private String[] value;

	/**
	 * Instantiates a new kategorie.
	 *
	 * @param kategorien the kategorien
	 */
	Kategorie(String[] kategorien) {
		this.value = kategorien;
	}

	/**
	 * Value.
	 *
	 * @return Alle Values
	 */
	public String[] value() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return value[0];
	}
}
