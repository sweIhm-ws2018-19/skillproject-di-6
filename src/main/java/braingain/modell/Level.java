package braingain.modell;

//TODO: Auto-generated Javadoc
/**
 * The Enum Level.
 */
public enum Level {

	/** Level einfach */
	EINFACH(new String[] { "Einfach", "einfach", "simpel" }),

	/** Level mittel. */
	MITTEL(new String[] { "Mittel", "mittel", "mittelschwer" }),

	/** Level anspruchsvoll. */
	Schwer(new String[] { "Schwer", "schwer", "schwierig" }),

	/** Level schwer. */
	Experte(new String[] { "Experte", "experte", "Expert", });

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return value[0];
	}
}
