package modellTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import braingain.modell.Spieler;

class SpielerTest {
	

	private static final String NAME = "Franz";
	private static final int HIGHSCORE = 5;
	private static final int PUNKTESTAND = 10;
	
	Spieler spieler = new Spieler(NAME, PUNKTESTAND, HIGHSCORE);
	
	
	@Test
	void testSpieler() {
		fail("Not yet implemented");
	}

	@Test
	void testBeantwortet() {
		spieler.beantwortet(true);
		Assert.assertSame("Die Methode beantworten funktioniert nicht richtig.", 11, spieler.getPunktestand());
		spieler.beantwortet(false);
		Assert.assertSame("Die Methode beantworten funktioniert nicht richtig.", 10, spieler.getPunktestand());
	}

	@Test
	void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPunktestand() {
		fail("Not yet implemented");
	}

	@Test
	void testGetHighscore() {
		fail("Not yet implemented");
	}

	@Test
	void testSetHighscore() {
		fail("Not yet implemented");
	}

}
