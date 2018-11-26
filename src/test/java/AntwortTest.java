package test.java;

import org.junit.Assert;

import main.java.braingain.Modell.Antwort;
import main.java.braingain.Modell.Frage;

public class AntwortTest {
	
	String[] antworten = {"Fuenf", "Sechs"};
	Frage frage = new Frage("Wie viele Seiten hat ein Wuerfel?");
	Antwort at = new Antwort(antworten, frage) ;
	
	public void getSchlagwoerter() {
		
		Assert.assertArrayEquals("Schlagwoerter sind nicht richtig", antworten, at.getSchlagwoerter());
		
	}
	
	public void testgetFrage() {
		
		Assert.assertEquals("Die Frage ist nicht richtig", frage, at.getFrage());
	}
	
}
