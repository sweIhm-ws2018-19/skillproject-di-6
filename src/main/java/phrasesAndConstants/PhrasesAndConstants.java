package phrasesAndConstants;

import braingain.modell.Category;
import braingain.modell.Level;

public final class PhrasesAndConstants {

	private PhrasesAndConstants() {
		throw new IllegalStateException("Utility class");
	}

	public static final int MAX_NUMBER_OF_PLAYERS = 4;
	public static final int MAX_NUMBER_OF_QUESTIONS_MORE_PLAYER = 3;
	public static final int MAX_NUMBER_OF_QUESTIONS_ONE_PLAYER = 10;

	public static final String QUESTION_ENDING = ".fragen";

	public static final String CARD_TITLE = "Braingain";
	public static final String LIST_OF_PLAYERNUMBERS = "numberOfPlayers";
	public static final String LIST_OF_NAMES = "username";
	public static final String LIST_OF_CATEGORIES = "gewaehlteKategorie";
	public static final String LIST_OF_LEVEL = "gewaehltesLevel";
	public static final String LIST_OF_ANTWORT = "antwort";
	public static final String LIST_OF_BACK_PACKING = "kofferPacken";

	public static final String INTENT_NEW_ROUND = "NeueRundeIntent";
	public static final String INTENT_SET_NUMBER_OF_PLAYERS = "AnzahlDerSpielerSetzenIntent";
	public static final String INTENT_SAVE_USERNAME = "UsernamenSpeichernIntent";
	public static final String INTENT_SET_CATEGORY = "KategorieEinstellenIntent";
	public static final String INTENT_BACK_PACKING = "KofferpackenIntent";
	public static final String INTENT_SET_LEVEL = "LevelEinstellenIntent";
	public static final String INTENT_ASK_QUESTION = "FrageStellenIntent";
	public static final String INTENT_ANSWER = "AntwortIntent";

	public static final String EXAMPLE_NAME = "Sage zum Beispiel, Ich heisse Max.";
	public static final String EXAMPLE_CHANGE_NAME = "Sage zum Beispiel, Spieler 1 heisst Peter.";
	public static final String EXAMPLE_CATEGORY = "Sage zum Beispiel, Ich waehle die Kategorie Mathematik.";
	public static final String EXAMPLE_CHANGE_CATEGORY = "Sage zum Beispiel, Aendere die Kategorie zu Logik.";
	public static final String EXAMPLE_LEVEL = "Sage zum Beispiel, Aendere das Level Mittel.";
	public static final String EXAMPLE_CHANGE_LEVEL = "Sage zum Beispiel, Ich waehle lieber das Level Einfach.";
	public static final String EXAMPLE_BACK_PACKING = "Bitte fange deine Saetze an mit, Ich nehme mit. ";

	public static final String MAX_PLAYERNUMBERS = String.format("Es koennen maximal %s Spieler spielen.",
			MAX_NUMBER_OF_PLAYERS);

	public static final String WELCOME = "Hallo. Wie viele Spieler spielen. " + MAX_PLAYERNUMBERS;
	public static final String HELP = "Mit mir wird dein Gehirn trainiert. Zuerst muss ich wissen wie viele Leute spielen, dann Welche Kategorie ihr waehlt und zuletzt noch welches Level.";
	public static final String PLAY_ALONE = "OK, Du spielst alleine. Sage mir nun bitte deinen Namen. ";
	public static final String PLAY_WITH_MORE = "Sagt mir nun nacheinander eure Namen. ";
	public static final String SAY_NEXT_NAME = "Bitte sagt mir nun den naechsten Namen. ";

	public static final String LIST_ALL_CATEGORIES = "Waehle nun die Kategorie. Es gibt " + Category.getAllCategories();
	public static final String LIST_ALL_LEVELS = "Waehle nun das Level. Es gibt " + Level.getAllLevels();

	public static final String SET_NUMBER_OF_PLAYERS = "Bitte lege zuerst die Spieleranzahl fest. " + MAX_PLAYERNUMBERS;
	public static final String SET_PLAYER_NAMES = "Bitte lege zuerst die Spielernamen fest. ";
	public static final String SET_CATEGORY = "Bitte lege zuerst die Kategorie fest. " + LIST_ALL_CATEGORIES;
	public static final String SET_LEVEL = "Bitte lege zuerst das Level fest. " + LIST_ALL_LEVELS;
	public static final String ALL_PLAYERNAMES_SET = "Du hast bereits alle Spielernamen gesetzt. "
			+ LIST_ALL_CATEGORIES;

	public static final String REPROMPT_NUMBER_OF_PLAYERS = "Das habe ich leider nicht verstanden. Wie viele Spieler seid ihr?";
	public static final String REPROMPT_SAVE_USERNAME = "Ich habe deinen Namen leider nicht verstanden. "
			+ EXAMPLE_NAME;
	public static final String REPROMPT_CATEGORY = "Ich habe die Kategorie nicht verstanden. " + EXAMPLE_CATEGORY;
	public static final String REPROMPT_LEVEL = "Ich habe das Level nicht verstanden. " + EXAMPLE_LEVEL;
	public static final String REPROMPT_ANSWER = "Ich habe deine Antwort leider nicht verstanden. Bitte wiederhole deine Antwort. ";
	public static final String REPROMPT_DONT_UNDERSTAND = "Das habe ich leider nicht verstanden.";

	public static final String START = "Sage Los um zu beginnen.";
	public static final String START_BACK_PACKING_MORE_PLAYER = EXAMPLE_BACK_PACKING;
	public static final String START_BACK_PACKING_ONE_PLAYER = EXAMPLE_BACK_PACKING
			+ "Ich starte gleich. Ich packe meinen Koffer und nehmen mit,";
	public static final String RIGHT_PACKING = "Alles richtig.";
	public static final String WRONG_PACKING = "Das ist leider falsch. Das richtige Wort ist";
	public static final String ADD_ONE_ITEM = "Bitte fuege eine Sache hinzu.";

	public static final String RIGHT_ANSWER = "Deine Antwort ist richtig.";
	public static final String WRONG_ANSWER = "Deine Antwort ist leider falsch. Die richtige Antwort ist,";
	
	public static final String HIGHSCORE = "Dein highscore ist: ";
	public static final String NEW_HIGHSCORE = "Dein neuer Highscore ist:";
	
	public static final String GOOD_BYE = "Auf Wiedersehen. Trainiere bald wieder";

}
