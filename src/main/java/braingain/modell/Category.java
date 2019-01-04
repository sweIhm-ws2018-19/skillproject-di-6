package braingain.modell;

/**
 * The Enum Kategorie.
 */
public enum Category {

	/** Kategorie logik. */
	LOGIK("Logik"),

	/** Kategorie mathe. */
	MATHE("Mathe"),

	/** Kategorie geografie. */
	GEOGRAFIE("Geographie"),

	/** Kategorie gedaechtnistraining. */
	GEDAECHTNISTRAINING("Gedaechtnistraining"),

	;

	/** Welche Namen die Kategorie hat. */
	private String value;

	/**
	 * Instantiates a new kategorie.
	 *
	 * @param kategorien the kategorien
	 */
	Category(String kategorie) {
		this.value = kategorie;
	}

	/**
	 * Value.
	 *
	 * @return Den Namen
	 */
	public String getValue() {
		return value;
	}

	public static Category getCategory(String s) {
		Category category = null;
		for (Category categoryLoop : Category.values()) {
			if (s.equals(categoryLoop.toString())) {
				category = categoryLoop;
			}
		}
		return category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return value;
	}

	public static String getAllCategories() {
		String s = "";
		int k = values().length;
		for (int i = 0; i < k - 2; i++) {
			s += Category.values()[i] + ", ";
		}
		s += values()[k - 2] + " und " + values()[k - 1];
		return s;
	}

}
