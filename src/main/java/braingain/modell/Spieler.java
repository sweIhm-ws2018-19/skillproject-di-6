package braingain.modell;

/**
 * The Class Spieler.
 */
public class Spieler {
	
	private final String name;
	private int punktestand;
	private int highscore;
	
	/**
	 * Initialisiert einen neuen Spieler, mit Namen, PunkteStand und Highscore.
	 *
	 * @param name the name
	 * @param punktestand the punktestand
	 * @param highscore the highscore
	 */
	public Spieler(String name, int punktestand, int highscore) {
		
		this.name = name;
		this.punktestand = punktestand;
		this.highscore = highscore;
		
	}
	
	/**
	 * Initialisiert einen neuem Spieler, nur mit Namen und gibt ihm keine Punkte und keinen Highscore
	 * 
	 * @param name the name
	 */
	
	public Spieler(String name) {
		this.name = name;
		this.punktestand = 0;
		this.highscore = 0;
	}
	
	/**
	 * Diese Methode testet, ob man richtig oder falsch geantwortet hat.
	 *
	 * @param richtigBeantwortet the richtig beantwortet
	 */
	public void beantwortet(boolean richtigBeantwortet){
		punktestand = richtigBeantwortet ? punktestand + 1 : punktestand - 1;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the punktestand.
	 *
	 * @return the punktestand
	 */
	public int getPunktestand() {
		return punktestand;
	}

	/**
	 * Gets the highscore.
	 *
	 * @return the highscore
	 */
	public int getHighscore() {
		return highscore;
	}

	/**
	 * Sets the highscore.
	 */
	public void setHighscore() {
		if(punktestand > highscore) {
			highscore = punktestand;
		}
	}

}
