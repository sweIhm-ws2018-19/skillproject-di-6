package main.java.braingain.Modell;

//TODO: Auto-generated Javadoc
/**
* The Enum Level.
*/
public enum Level {

	/** Level einfach */
	EINFACH(new String[] { "einfach", "simpel" }),
	
	/** Level mittel. */
	MITTEL(new String[] { "mittel", "mittelschwer" }),
	
	/** Level anspruchsvoll. */
	ANSPRUCHSVOLL(new String[] { "anspruchsvoll" }),
	
	/** Level schwer. */
	SCHWER(new String[] { "schwer", "schwierig" });

	/** Welche Namen es noch haben kann. */
	private String[] value;

	/**
	 * Instantiates a new level.
	 *
	 * @param kategorien the kategorien
	 */
	Level(String[] kategorien) {
		this.value = kategorien;
	}

	/**
	 * Value.
	 *
	 * @return alle Values
	 */
	public String[] value() {
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return value[0];
	}
}
