package phrasesAndConstants;

import braingain.modell.Kategorie;
import braingain.modell.Level;

public final class PhrasesAndConstants {

	private PhrasesAndConstants() {
		throw new IllegalStateException("Utility class");
	}

	public static final int MAX_NUMBER_OF_PLAYERS = 4;
	public static final int MAX_NUMBERS_OF_QUESTION = 3;
	
	public static final String CARD_TITLE = "Braingain";
	public static final String LIST_OF_PLAYERNUMBERS = "numberOfPlayers";
	
	public static final String EXAMPLE_NAME = "Sage zum Beispiel, Ich heisse Max.";
	public static final String EXAMPLE_CHANGE_NAME = "Sage zum Beispiel, Spieler 1 heisst Peter.";
	public static final String EXAMPLE_CATEGORY = "Sage zum Beispiel, Ich waehle die Kategorie Mathematik.";
	public static final String EXAMPLE_CHANGE_CATEGORY = "Sage zum Beispiel, Aendere die Kategorie Logik.";
	public static final String EXAMPLE_LEVEL = "Sage zum Beispiel, Aendere das Level Mittel.";
	public static final String EXAMPLE_CHANGE_LEVEL = "Sage zum Beispiel, Ich waehle lieber das Level Einfach.";
	
	public static final String MAX_PLAYERNUMBERS = String.format("Es koennen maximal %s Spieler spielen.", MAX_NUMBER_OF_PLAYERS);
	
	public static final String WELCOME = "Hallo. Mit mir gaint dein Brain. Bitte sage mir, wie viele Spieler spielen. " + MAX_PLAYERNUMBERS;
	public static final String HELP = "Mit mir wird dein Gehirn trainiert. Zuerst muss ich wissen wie viele Leute spielen, dann Welche Kategorie ihr waehlt und zuletzt noch welches Level.";
	public static final String PLAY_ALONE = "OK, Du spielst alleine. Sage mir nun bitte deinen Namen.";
	public static final String PLAY_WITH_MORE = "Sagt mir nun nacheinander eure Namen. " + EXAMPLE_NAME;
	public static final String SET_NEW_NAME = "Du willst einen Namen aendern? " + EXAMPLE_CHANGE_NAME;
	public static final String SET_NUMBER_OF_PLAYERS = "Bitte lege zuerst die Spieleranzahl fest. " + MAX_PLAYERNUMBERS;
	public static final String SET_PLAYER_NAMES = "Bitte lege zuerst die Spielernamen fest.";
	public static final String SET_CATEGORY = "Bitte lege zuerst die Kategorie fest. Es gibt " + Kategorie.getKategorien();
	public static final String RESET_CATEGORY = "Du willst die Kategorie aendern? " + EXAMPLE_CHANGE_CATEGORY;
	public static final String SET_LEVEL = "Bitte lege zuerst das Level fest. Es gibt " + Level.getLevels();
	public static final String RESET_LEVEL = "Du willst das Level aendern? " + EXAMPLE_CHANGE_LEVEL;
	public static final String REPROMPT_NUMBER_OF_PLAYERS = "Ich habe deine Antwort leider nicht verstanden. Wie viele Spieler seid ihr?";
	public static final String REPROMPT_SAVE_USERNAME = "Ich habe deinen Namen leider nicht verstanden. " + EXAMPLE_NAME;
	public static final String REPROMPT_CATEGORY = "Ich habe die Kategorie nicht verstanden. " + EXAMPLE_CATEGORY;
	public static final String REPROMPT_LEVEL = "Ich habe das Level nicht verstanden. " + EXAMPLE_LEVEL;
	public static final String REPROMPT_ANSWER = "Ich habe deine Antwort leider nicht verstanden. Bitte wiederhole deine Antwort.";
	public static final String RIGHT_ANSWER = "Deine Antwort ist richtig.";
	public static final String WRONG_ANSWER = "Deine Antwort ist leider falsch.";
	
	public static final String GOOD_BYE = "Auf Wiedersehen. Trainiere bald wieder";
	
}
