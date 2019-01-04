package braingain.modell;

/**
 * The Enum BackPackWords.
 */
public enum BackPackWords {
	
	/** The alexa echo. */
	ALEXA_ECHO("ein Alexa Echo"),
	
	/** The bett. */
	BETT("Bett"),
	
	/** The computer. */
	COMPUTER("Computer"),
	
	/** The dino. */
	DINO("Dino"),
	
	/** The eisen. */
	EISEN("Eisen"),
	
	/** The feuerzeug. */
	FEUERZEUG("Feuerzeug"),
	
	/** The gabel. */
	GABEL("Gabel"),
	
	/** The haus. */
	HAUS("Haus"),
	
	/** The ingwer. */
	INGWER("Ingwer"),
	
	/** The jacke. */
	JACKE("Jacke"),
	
	/** The kamera. */
	KAMERA("Kamera"),
	
	/** The leselampe. */
	LESELAMPE("Leselampe"),
	
	/** The muetze. */
	MUETZE("Muetze"),
	
	/** The nachthemd. */
	NACHTHEMD("Nachthemd"),
	
	/** The ohrringe. */
	OHRRINGE("Ohrringe"),
	
	/** The postkarte. */
	POSTKARTE("Postkarte"),
	
	/** The qualle. */
	QUALLE("Qualle"),
	
	/** The rucksack. */
	RUCKSACK("Rucksack"),
	
	/** The sonnenschirm. */
	SONNENSCHIRM("Sonnenschirm"),
	
	/** The trompete. */
	TROMPETE("Trompete"),
	
	/** The uhr. */
	UHR("Uhr"),
	
	/** The vogel. */
	VOGEL("Vogel"),
	
	/** The wein. */
	WEIN("Wein"),
	
	/** The xylophon. */
	XYLOPHON("Xylophon"),
	
	/** The yogamatte. */
	YOGAMATTE("Yogamatte"),
	
	/** The zitrone. */
	ZITRONE("Zitrone"),
	
	;
	
	
	/** The name. */
	String name;
	
	/**
	 * Instantiates a new back pack word.
	 *
	 * @param name the name
	 */
	BackPackWords(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return this.name;
	}
	
	
	
}
