package modellTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import braingain.modell.Spieler;

class SpielerTest {
	

	private static final String NAME = "Franz";
	private static final int PUNKTESTAND = 10;
	private static final int HIGHSCORE = 5;
	
	Spieler spieler = new Spieler(NAME, PUNKTESTAND, HIGHSCORE);
	
	@Test
	void testBeantwortet() {
		spieler.beantwortet(true);
		Assertions.assertEquals(11, spieler.getPunktestand(), "Die Methoden beantworten funktioniert nicht richtig.");
		spieler.beantwortet(false);
		Assertions.assertEquals(10, spieler.getPunktestand(), "Die Methoden beantworten funktioniert nicht richtig.");
	}

	@Test
	void testGetName() {
		Assertions.assertEquals(NAME, spieler.getName(), "Der Name stimmt nicht ueberein.");
	}

	@Test
	void testGetPunktestand() {
		Assertions.assertEquals(PUNKTESTAND, spieler.getPunktestand(), "Der Punktestand ist nicht richtig.");
	}

	@Test
	void testGetHighscore() {
		Assertions.assertEquals(HIGHSCORE, spieler.getHighscore(), "Der Highscore stimmt nicht ueberein.");
	}

	@Test
	void testSetHighscore() {
		spieler.setHighscore();
		Assertions.assertEquals(spieler.getHighscore(), spieler.getPunktestand(), "Der Highscore und der Punktestand muessten gleich sein.");
		spieler.setHighscore();
		Assertions.assertEquals(PUNKTESTAND, spieler.getHighscore(), "Der Punktestand und der Highscore muessten gleich sein.");
	}

}
