package braingain.modell;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class Spielrunde.
 */
public class Spielrunde {

	public ArrayList<Spieler> player;
	private HashMap<Integer, Frage> Questions;
	
	private Spieler currentPlayer;
	private Kategorie category;
	private Level level;
	
	private int numberOfPlayers;
	//private int counter;
	
	private static final int MAX_QUESTIONS = 5;


	/**
	 * Initialisiert eine neue Spielrunde, hier werden die Array-Lists erstellt fuer
	 * Spieler und Fragen.
	 */
	public Spielrunde() {
		player = new ArrayList<Spieler>();
		category = null;
		level = null;
		numberOfPlayers = 0;
		//counter = 0;
	}

	/**
	 * Fuegt einen Spieler hinzu.
	 *
	 * @param Den Spieler der hinzugefuegt werden soll
	 * @return true, wenn der Spieler hinzugefuegt werden konnte
	 */
	public boolean addPlayer(Spieler s) {
		return this.player.add(s);
	}
	
	/**
	 * Diese Methode ermittelt den/die Spieler mit der hoechsten Punktezahl.
	 *
	 * @return Den/Die Spieler mit der hoechsten Punktezahl
	 */
	public ArrayList<Spieler> getHighscoreSpieler() {
		ArrayList<Spieler> highscoreSpieler = new ArrayList<Spieler>();
		highscoreSpieler.add(player.get(0));
		for (Spieler s : player) {
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
	 * Diese Methode ermittelt die hoechste Punktzahl und die Spieler, welche diese
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
	 * Ermittelt die naechste Frage.
	 *
	 * @return Die naechste Frage die hinzugefuegt wurde
	 */
	public Frage nextRandomQuestion() {
		// TODO
		return null;
	}

	/**
	 * Sets the anzahl spieler.
	 *
	 * @param anzahlSpieler the new anzahl spieler
	 */
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	/**
	 * Gets the anzahl spieler.
	 *
	 * @return the anzahl spieler
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
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
			this.category = temp;
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

	public Kategorie getCategory() {
		return this.category;
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

	public Level getLevel() {
		return this.level;
	}
//
//	/**
//	 * Sets the counter.
//	 *
//	 * @param counter the new counter
//	 */
//	public void setCounter(int counter) {
//		this.counter = counter;
//	}
//
//	/**
//	 * Diese Methode erhoeht den Counter um eins.
//	 */
//	public void increaseCounter() {
//		this.counter++;
//	}
//
//	/**
//	 * Gets the counter.
//	 *
//	 * @return the counter
//	 */
//	public int getCounter() {
//		return this.counter;
//	}

	/**
	 * Es wir ein naechster Spieler gesetzt, der zufaellig gefunden wird, allerdings
	 * nur, wenn der Count des Spielers nicht groesser als einen bestimmten Wert
	 * ist.
	 */
	public void setNextRandomCurrentPlayer() {
		currentPlayer = player.get((int) Math.random() * numberOfPlayers);

		while (currentPlayer.getNumberOfQuestionsAsked() == MAX_QUESTIONS) {
			currentPlayer = player.get((int) Math.random() * numberOfPlayers);
		}
		currentPlayer.increaseNumberOfQuestionsAskedByOne();
	}

	/**
	 * Testet ob die gegeben Antwort die richtige ist und aktualisieert dann damit
	 * den Punktestand.
	 *
	 * @param Die gegeben Antwort
	 */
	public boolean checkAnswer(String antwort) {
		// TODO: kontrolliere die Antwort und aktualisiere in Spieler den Punktestand.
		return false;
	}
	
	/**
	 * 
	 * @return The right answer.
	 */
	public String getRightAnswer() {
		// TODO
		return null;
	}

	public String[] getPlayer() {
		String[] playerArray = new String[player.size()];
		for (int i = 0; i < player.size(); i++) {
			playerArray[i] = player.get(i).getName();
		}
		return playerArray;
	}
	
	public String getPlayerAt(int position) {
		return player.get(position).getName();
	}
	
	public boolean allPlayersSet() {
		return player.size() == numberOfPlayers;
	}
	
	public void reset() {
		player = new ArrayList<Spieler>();
		currentPlayer = null;
		category = null;
		level = null;
		numberOfPlayers = 0;
	}
	
	/**
	 * Diese Methode fuellt unsere HasMap von int und fragen aus den TextDateien, im
	 * recourses Ordner
	 */
	public void buildQuestions() {
		// TODO Auto-generated method stub

	}

}
