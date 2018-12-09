package modellTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import braingain.modell.Spieler;

class SpielerTest {
	

	private static final String NAME_1 = "Franz";
	private static final int PUNKTESTAND_1 = 10;
	private static final int HIGHSCORE_1 = 5;
	
	private static final String NAME_2 = "Michael";
	private static final int PUNKTESTAND_2 = 30;
	private static final int HIGHSCORE_2 = 24;
	
	Spieler spieler1 = new Spieler(NAME_1, PUNKTESTAND_1, HIGHSCORE_1);
	Spieler spieler2 = new Spieler(NAME_2, PUNKTESTAND_2, HIGHSCORE_2);
	
	@Test
	void testBeantwortet() {
		spieler1.beantwortet(true);
		Assertions.assertEquals(11, spieler1.getPunktestand(), "Die Methoden beantworten funktioniert nicht richtig.");
		spieler1.beantwortet(false);
		Assertions.assertEquals(10, spieler1.getPunktestand(), "Die Methoden beantworten funktioniert nicht richtig.");
		spieler2.beantwortet(true);
		Assertions.assertEquals(31, spieler2.getPunktestand(), "Die Methoden beantworten funktioniert nicht richtig.");
		spieler2.beantwortet(false);
		Assertions.assertEquals(30, spieler2.getPunktestand(), "Die Methoden beantworten funktioniert nicht richtig.");
	}

	@Test
	void testGetName() {
		Assertions.assertEquals(NAME_1, spieler1.getName(), "Der Name stimmt nicht ueberein.");
		Assertions.assertEquals(NAME_2, spieler2.getName(), "Der Name stimmt nicht ueberein.");
	}

	@Test
	void testGetPunktestand() {
		Assertions.assertEquals(PUNKTESTAND_1, spieler1.getPunktestand(), "Der Punktestand ist nicht richtig.");
		Assertions.assertEquals(PUNKTESTAND_2, spieler2.getPunktestand(), "Der Punktestand ist nicht richtig.");
	}

	@Test
	void testGetHighscore() {
		Assertions.assertEquals(HIGHSCORE_1, spieler1.getHighscore(), "Der Highscore stimmt nicht ueberein.");
		Assertions.assertEquals(HIGHSCORE_2, spieler2.getHighscore(), "Der Highscore stimmt nicht ueberein.");
	}

	@Test
	void testSetHighscore() {
		spieler1.setHighscore();
		Assertions.assertEquals(spieler1.getHighscore(), spieler1.getPunktestand(), "Der Highscore und der Punktestand muessten gleich sein.");
		spieler1.setHighscore();
		Assertions.assertEquals(PUNKTESTAND_1, spieler1.getHighscore(), "Der Punktestand und der Highscore muessten gleich sein.");
		spieler1.setHighscore();
		Assertions.assertEquals(spieler2.getHighscore(), spieler2.getHighscore(), "Der Highscore und der Punktestand muessten gleich sein.");
		spieler1.setHighscore();
		Assertions.assertEquals(HIGHSCORE_2, spieler2.getHighscore(), "Der Punktestand und der Highscore muessten gleich sein.");
	}

}
