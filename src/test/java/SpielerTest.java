package test.java;

import org.junit.Assert;
import org.junit.Test;

import main.java.braingain.Modell.Spieler;

public class SpielerTest {
	
	private static final String NAME = "Franz";
	private static final int HIGHSCORE = 5;
	private static final int PUNKTESTAND = 10;
	
	Spieler spieler = new Spieler(NAME, PUNKTESTAND, HIGHSCORE);
	
	@Test
	public void testBeantwortet() {
		spieler.beantwortet(true);
		Assert.assertSame("Die Methode beantworten funktioniert nicht richtig.", 11, spieler.getPunktestand());
		spieler.beantwortet(false);
		Assert.assertSame("Die Methode beantworten funktioniert nicht richtig.", 10, spieler.getPunktestand());
	}

}
