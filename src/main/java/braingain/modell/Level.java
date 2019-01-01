package braingain.modell;

/**
 * The Enum Level.
 */
public enum Level {

	/** Level Einfach */
	EINFACH("Einfach"),

	/** Level Mittel. */
	MITTEL("Mittel"),

	/** Level Schwer. */
	SCHWER("Schwer"),

	/** Level Experte. */
	EXPERTE("Experte");

	/** Welche Namen es noch haben kann. */
	private String value;

	/**
	 * Instantiates a new level.
	 *
	 * @param kategorien the kategorien
	 */
	Level(String level) {
		this.value = level;
	}
	
	public static Level getLevel(String s) {
		Level level = null;
		for (Level levelLoop : Level.values()) {
			if (s.equals(levelLoop.value)) {
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
	public String value() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return value;
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
