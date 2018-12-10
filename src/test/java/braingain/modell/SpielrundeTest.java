package braingain.modell;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import braingain.modell.*;

class SpielrundeTest {

	private static final int HIGHESTPOINTS = 10;
	private static final int ANZAHL_DER_SPIELER = 4;
	private static final int COUNTER = 2;
	private static final Kategorie KATEGORIE = Kategorie.LOGIK;
	private static final Level LEVEL = Level.MITTEL;
	private static final Frage FRAGE_1 = new Frage("Wie viele Seiten hat ein wuerfel?", "6");
	private static final Frage FRAGE_2 = new Frage("Wie viele Protonen hat Helium?", "2");

	Spielrunde sr = new Spielrunde();

	// Peter und Karl haben den gleichen und auch hoechsten Highscore.
	Spieler marc = new Spieler("Marc", HIGHESTPOINTS, 15);
	Spieler peter = new Spieler("Peter", 5, 29);
	Spieler karl = new Spieler("Karl", HIGHESTPOINTS, 29);

	@Test
	void testSpielrunde() {
		Assertions.assertNotNull(sr.spieler, "Der Konstruktor funktioniert nicht.");
		Assertions.assertNotNull(sr.fragen, "Der Konstruktor funktioniert nicht.");
	}

	@Test
	void testAddPlayer() {
		sr.setAnzahlSpieler(ANZAHL_DER_SPIELER);
		Assertions.assertTrue(sr.addPlayer(marc), "Der Spieler muesste hinzugefuegt werden koennen.");
		Assertions.assertTrue(sr.addPlayer(peter), "Der Spieler muessten hinzugefuegt werden koennen.");
		Assertions.assertTrue(sr.addPlayer(karl), "Der Spieler muesste hinzugefuegt werden koennen.");
	}

	@Test
	void testAddFrage() {
		Assertions.assertTrue(sr.addFrage(FRAGE_1), "Die Frage muesste hinzugefuegt werden koennen.");
		Assertions.assertTrue(sr.addFrage(FRAGE_2), "Die Frage muesste hinzugefuegt werden koennen.");
	}

	@Test
	void testGetHighscoreSpieler() {
		sr.setAnzahlSpieler(ANZAHL_DER_SPIELER);
		sr.addPlayer(marc);
		sr.addPlayer(peter);
		sr.addPlayer(karl);
		ArrayList<Spieler> testHighscoreSpieler = new ArrayList<>();
		testHighscoreSpieler.add(marc);
		testHighscoreSpieler.add(karl);
		ArrayList<Spieler> highscoreSpieler = sr.getHighscoreSpieler();

		for (int i = 0; i < highscoreSpieler.size(); i++) {
			Assertions.assertEquals(highscoreSpieler.get(i).getName(), testHighscoreSpieler.get(i).getName(),
					"Die Spieler stimmen nicht ueberein.");
		}

	}

	@Test
	void testGetHighscore() {
		sr.setAnzahlSpieler(ANZAHL_DER_SPIELER);
		sr.addPlayer(marc);
		sr.addPlayer(peter);
		sr.addPlayer(karl);
		String highscore = Integer.toString(HIGHESTPOINTS);
		String[] winners = { marc.getName(), karl.getName() };
		String[] toTest = sr.getHighscore();
		
		System.out.println(winners[0]);
		System.out.println(winners[1]);
		System.out.println(toTest[1]);
		System.out.println(toTest[2]);
		
		Assertions.assertEquals(highscore, toTest[0], "Der Highscore stimmt nicht.");
		for (int i = 0; i < winners.length; i++) {
			Assertions.assertEquals(winners[i], toTest[i + 1], "Die Gewinner sind nicht korrekt.");
		}

	}

	@Test
	void testFetchFrage() {
		sr.addFrage(FRAGE_1);
		sr.addFrage(FRAGE_2);
		sr.setCounter(0);
		Assertions.assertEquals(FRAGE_1.getFrage(), sr.fetchFrage(), "Die erste Frage stimmt nicht.");
		sr.increaseCounter();
		Assertions.assertEquals(FRAGE_2.getFrage(), sr.fetchFrage(), "Die zweite Frage stimmt nicht.");
	}

	@Test
	void testGetAnzahlSpieler() {
		sr.setAnzahlSpieler(ANZAHL_DER_SPIELER);
		Assertions.assertEquals(ANZAHL_DER_SPIELER, sr.getAnzahlSpieler(),
				"Der Getter fuer Anzahl der Spieler funktioniert nicht richtig.");
	}

	@Test
	void testSetAnzahlDerSpieler() {
		sr.setAnzahlSpieler(ANZAHL_DER_SPIELER);
		Assertions.assertEquals(ANZAHL_DER_SPIELER, sr.getAnzahlSpieler(),
				"Die Anzahl der Spieler wird nicht gesetzt.");
	}

	@Test
	void testSetKategorie() {
		Assertions.assertTrue(sr.setKategorie(KATEGORIE.toString()));
		Assertions.assertFalse(sr.setKategorie("nonKategorie"), "Die Kategorie nonKategorie gibt es nicht.");
	}

	@Test
	void testGetKategorie() {
		sr.setKategorie(KATEGORIE.toString());
		Assertions.assertEquals(KATEGORIE, sr.getKategorie(KATEGORIE.toString()),
				"Die Methode getKategorie ist falsch.");
	}

	@Test
	void testSetLevel() {
		Assertions.assertTrue(sr.setLevel(LEVEL.toString()));
		Assertions.assertFalse(sr.setLevel("nonLevel"), "Das Level nonLevel gibt es nicht.");
	}

	@Test
	void testGetLevel() {
		sr.setLevel(LEVEL.toString());
		Assertions.assertEquals(LEVEL, sr.getLevel(LEVEL.toString()), "Die Methode getLevel ist inkorrekt.");
	}

	@Test
	void testSetCounter() {
		sr.setCounter(COUNTER);
		Assertions.assertEquals(COUNTER, sr.getCounter(), "Counter stimmt nicht");
	}

	@Test
	void testIncreaseCounter() {
		sr.setCounter(COUNTER);
		sr.increaseCounter();
		sr.increaseCounter();
		Assertions.assertEquals(COUNTER + 2, sr.getCounter(),
				"Die Methode increaseCounter funktioniert nicht richtig.");
	}

	@Test
	void testGetCounter() {
		sr.setCounter(COUNTER);
		sr.increaseCounter();
		Assertions.assertEquals(COUNTER + 1, sr.getCounter(), "Counter stimmt nicht");
	}

	@Test
	void testCheckAntwort() {
		// TODO
	}
}
