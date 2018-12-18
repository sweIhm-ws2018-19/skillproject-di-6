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
	SCHWER(new String[] { "Schwer", "schwer", "schwierig" }),

	/** Level schwer. */
	EXPERTE(new String[] { "Experte", "experte", "Expert", });

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

	public static String getLevels() {
		String s = "";
		int l = values().length;
		for (int i = 0; i < l - 2; i++) {
			s += values()[i] + ", ";
		}
		s += values()[l - 2] + " und " + values()[l - 1] + ". ";
		return s;
	}

}
