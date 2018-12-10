package braingain.modell;

import java.util.ArrayList;

/**
 * The Class Spielrunde.
 */
public class Spielrunde {

	public ArrayList<Spieler> spieler;
	public ArrayList<Frage> fragen;
	private int anzahlSpieler;
	private int aktuellerSpieler;
	private int counter;
	Kategorie kategorie;
	Level level;

	/**
	 * Initialisiert eine neue Spielrunde, hier werden die Array-Lists erstellt für
	 * Spieler und Fragen.
	 */
	public Spielrunde() {
		spieler = new ArrayList<Spieler>();
		fragen = new ArrayList<Frage>();
		counter = 0;
		aktuellerSpieler = 0;
	}

	/**
	 * Fügt einen Spieler hinzu.
	 *
	 * @param Den Spieler der hinzugefügt werden soll
	 * @return true, wenn der Spieler hinzugefügt werden konnte
	 */
	public boolean addPlayer(Spieler s) {
		boolean succeded = false;
		if (spieler.size() < anzahlSpieler) {
			succeded = this.spieler.add(s);
		}
		return succeded;
	}

	/**
	 * Fügt eine Frage hinzu.
	 *
	 * @param Die Frage die hinzugefügt werden soll
	 * @return true, wenn die Frage hinzugefügt wernden konnte
	 */
	public boolean addFrage(Frage f) {
		return fragen.add(f);
	}

	/**
	 * Diese Methode ermittelt den/die Spieler mit der höchsten Punktezahl.
	 *
	 * @return Den/Die Spieler mit der höchsten Punktezahl
	 */
	public ArrayList<Spieler> getHighscoreSpieler() {
		ArrayList<Spieler> highscoreSpieler = new ArrayList<Spieler>();
		highscoreSpieler.add(spieler.get(0));
		for (Spieler s : spieler) {
			if (s.getPunktestand() > highscoreSpieler.get(0).getPunktestand()) {
				highscoreSpieler.clear();
				highscoreSpieler.add(s);
			} else if (s.getPunktestand() == highscoreSpieler.get(0).getPunktestand()) {
				highscoreSpieler.add(s);
			}
		}
		return highscoreSpieler;
	}

	/**
	 * Diese Methode ermittelt die höchste Punktzahl und die Spieler, welche diese
	 * haben.
	 *
	 * @return Ein String-Array, an der ersten Stelle steht die Punktzahl und danach
	 *         die jeweiligen Spieler mit dieser Punktzahl
	 */
	public String[] getHighscore() {
		ArrayList<Spieler> highscoreSpieler = getHighscoreSpieler();
		int highscore = highscoreSpieler.get(0).getPunktestand();
		int anzahlGewinner = highscoreSpieler.size();
		String[] ausgaben = new String[anzahlGewinner + 1];
		ausgaben[0] = Integer.toString(highscore);
		for (int i = 1; i < anzahlGewinner; i++) {
			ausgaben[i] = highscoreSpieler.get(i).getName();
		}

		return ausgaben;
	}

	/**
	 * Ermittelt die nächste Frage.
	 *
	 * @return Die nächste Frage die hinzugefügt wurde
	 */
	public String fetchFrage() {
		String newFrage = fragen.get(counter).getFrage();
		return newFrage;
	}
	
	/**
	 * Aktualisiert alle Fragen
	 */
	
	public void refreshFragen(){
		fragen = Frage.alleFragen.get(kategorie).get(level);
	}

	/**
	 * Sets the anzahl spieler.
	 *
	 * @param anzahlSpieler the new anzahl spieler
	 */
	public void setAnzahlSpieler(int anzahlSpieler) {
		this.anzahlSpieler = anzahlSpieler;
	}

	/**
	 * Gets the anzahl spieler.
	 *
	 * @return the anzahl spieler
	 */
	public int getAnzahlSpieler() {
		return anzahlSpieler;
	}

	/**
	 * Sets the kategorie.
	 *
	 * @param Die Kategorie die gesetzt werden soll
	 * @return true, wenn diese Kategorie existiert
	 */
	public boolean setKategorie(String kategorie) {
		boolean isKategorieSet = false;
		Kategorie temp = getKategorie(kategorie.toLowerCase());
		if (temp != null) {
			this.kategorie = temp;
			isKategorieSet = true;
		}
		return isKategorieSet;
	}

	/**
	 * Ermittelt den Namen der eingespeicherten Kategorie.
	 *
	 * @param Den eingespeicherten Namen der Kategorie den man haben will.
	 * @return Den eingespeicherten Namen der Kategorie
	 */
	public Kategorie getKategorie(String propose) {
		for (Kategorie k : Kategorie.values()) {
			for (String s : k.value()) {
				if (s.equals(propose)) {
					return k;
				}
			}
		}
		return null;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the level
	 * @return true, if successful
	 */
	public boolean setLevel(String level) {
		boolean levelIsSet = false;
		Level temp = getLevel(level.toLowerCase());
		if (temp != null) {
			this.level = temp;
			levelIsSet = true;
		}
		return levelIsSet;
	}

	/**
	 * Ermittelt den Namen des eingespeicherten Levels.
	 *
	 * @param Den eingespeicherten Namen des Levels den man haben will.
	 * @return Den eingespeicherten Namen des Levels
	 */
	public Level getLevel(String propose) {
		for (Level k : Level.values()) {
			for (String s : k.value()) {
				if (s.equals(propose))
					return k;
			}
		}
		return null;
	}

	/**
	 * Sets the counter.
	 *
	 * @param counter the new counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * Diese Methode erhöht den Counter um eins.
	 */
	public void increaseCounter() {
		this.counter++;
	}

	/**
	 * Gets the counter.
	 *
	 * @return the counter
	 */
	public int getCounter() {
		return this.counter;
	}
	
	public void naechsterSpieler() {
		aktuellerSpieler += 1;
		if(aktuellerSpieler == anzahlSpieler) {
			aktuellerSpieler -= anzahlSpieler;
		}
	}

	/**
	 * Testet ob die gegeben Antwort die richtige ist und aktualisieert dann damit den Punktestand.
	 *
	 * @param Die gegeben Antwort
	 */
	public boolean checkAntwort(String antwort) {
		// TODO: kontrolliere die Antwort und aktualisiere in Spieler den Punktestand...
		ArrayList<String> antworten = fragen.get(counter).getAntwortenArrayList();
		boolean ubereinstimmung = false;
		int antwortZahl = antworten.size();
		while(!ubereinstimmung && antwortZahl>0) {
			antwortZahl -= 1;
			ubereinstimmung = antworten.get(antwortZahl).equalsIgnoreCase(antwort);
		}
		spieler.get(aktuellerSpieler).beantwortet(ubereinstimmung);
		return ubereinstimmung;
	}
	
	public String getRichtigeAntwort() {
		return fragen.get(counter).getAntwortString();
	}
}
