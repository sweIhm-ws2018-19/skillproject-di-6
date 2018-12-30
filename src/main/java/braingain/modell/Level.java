package braingain.modell;

/**
 * The Enum Level.
 */
public enum Level {

	/** Level Einfach */
	EINFACH(new String[] { "Einfach", "einfach", "simpel" }),

	/** Level Mittel. */
	MITTEL(new String[] { "Mittel", "mittel", "mittelschwer" }),

	/** Level Schwer. */
	SCHWER(new String[] { "Schwer", "schwer", "schwierig" }),

	/** Level Experte. */
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
	
	public static Level getLevel(String s) {
		Level level = null;
		for (Level levelLoop : Level.values()) {
			for (String value : levelLoop.value()) {
				if (value.equals(s))
					level = levelLoop;
			}
		}
		return level;
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

	public static String getAllLevels() {
		String s = "";
		int l = values().length;
		for (int i = 0; i < l - 2; i++) {
			s += values()[i] + ", ";
		}
		s += values()[l - 2] + " und " + values()[l - 1] + ". ";
		return s;
	}

}
