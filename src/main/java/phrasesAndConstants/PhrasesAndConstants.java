package phrasesAndConstants;

import braingain.modell.Kategorie;
import braingain.modell.Level;

public class PhrasesAndConstants {

	private PhrasesAndConstants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String CARD_TITLE = "Braingain";

	public static final String WELCOME = "Hallo. Mit mir gaint dein Brain. Bitte sage mir, wie viele Spieler spielen. Es koennen maximal 4 Spieler spielen.";
	public static final String HELP = "Mit mir wird dein Gehirn trainiert. Zuerst muss ich wissen wie viele Leute spielen, dann Welche Kategorie ihr wählt und zuletzt noch welches Level.";
	public static final String USERNAMES_ARE_SET = "Wenn du einem Namen aendern willst, musst du zum Beispiel sagen Spieler 1 heisst Peter.";
	public static final String SET_NUMBER_OF_PLAYERS = "Bitte lege zuerst die Spieleranzahl fest. Es koennen maximal 4 Spieler spielen.";
	public static final String SET_PLAYER_NAMES = "Bitte lege zuerst die Spielernamen fest.";
	public static final String SET_CATEGORY = "Bitte lege zuerst die Kategorie fest. Es gibt " + Kategorie.getKategorien();
	public static final String RESET_CATEGORY = "Um die Kategorie zu aendern sage zum Beispiel Aendere die Kategorie auf Mathe.";
	public static final String SET_LEVEL = "Bitte lege zuerst das Level fest. Es gibt " + Level.getLevels();
	public static final String RESET_LEVEL = "Um das Level zu aenden sage zum Beispiel Aendere das Level auf Schwer";
	public static final String REPROMPT_NUMBER_OF_PLAYERS = "Ich habe deine Antwort leider nicht verstanden. Wie viele Spieler seid ihr?";
	public static final String REPROMPT_SAVE_USERNAME = "Ich habe deinen Namen leider nicht verstanden. Bitte wiederhole deinen Namen.";
	public static final String REPROMPT_CATEGORY = "Ich habe die Kategorie nicht verstanden. Sage zum Beispiel: ich waehle die Katgorie Logik.";
	public static final String REPROMPT_LEVEL = "Ich habe das Level nicht verstanden. Sage mir das Level, in welchem du abgefragt werden willst. Sage zum Beispiel: Mittel.";

	public static final String GOOD_BYE = "Auf Wiedersehen. Trainiere bald wieder";
	
}
