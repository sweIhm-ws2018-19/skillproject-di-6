package modellTest;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import braingain.modell.*;

class SpielrundeTest {
	
	private static final int ANZAHL_DER_SPIELER = 4;
	private static final Kategorie KATEGORIE = Kategorie.LOGIK;
	private static final Level LEVEL = Level.MITTEL;
	
	Spielrunde sr = new Spielrunde();
	
	Spieler marc = new Spieler("Marc", 10, 15);
	Spieler peter = new Spieler("Peter", 5, 29);
	
	@Test
	void testSpielrunde() {
		Assertions.assertNotNull(sr.spieler, "Der Konstruktor funktioniert nicht.");
		Assertions.assertNotNull(sr.fragen, "Der Konstruktor funktioniert nicht.");
	}
	
	@Test
	void testAddPlayer() {
		Assertions.assertTrue(sr.addPlayer(marc), "Der Spieler muesste hinzugefuegt werden koennen.");
		Assertions.assertTrue(sr.addPlayer(peter), "Der Spieler muessten hinzugefuegt werden koennen.");
	}
	
	@Test
	void testGetHighscoreSpieler() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHighscore() {
		fail("Not yet implemented");
	}

	@Test
	void testFetchFragen() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAnzahlSpieler() {
		sr.setAnzahlDerSpieler(ANZAHL_DER_SPIELER);
		Assertions.assertEquals(ANZAHL_DER_SPIELER, sr.getAnzahlSpieler(), "Der Getter fuer Anzahl der Spieler funktioniert nicht richtig.");
	}

	@Test
	void testSetAnzahlDerSpieler() {
		sr.setAnzahlDerSpieler(ANZAHL_DER_SPIELER);
		Assertions.assertEquals(ANZAHL_DER_SPIELER, sr.getAnzahlSpieler(), "Die Anzahl der Spieler wird nicht gesetzt.");
	}

	@Test
	void testSetKategorie() {
		Assertions.assertTrue(sr.setKategorie(KATEGORIE.toString()));
		Assertions.assertFalse(sr.setKategorie("nonKategorie"), "Die Kategorie nonKategorie gibt es nicht.");
	}

	@Test
	void testGetKategorie() {
		sr.setKategorie(KATEGORIE.toString());
		Assertions.assertEquals(KATEGORIE, sr.getKategorie(KATEGORIE.toString()), "Die Methode getKategorie ist falsch.");
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
	void testCheckAntwort() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNaechsteFrage() {
		fail("Not yet implemented");
	}

}
