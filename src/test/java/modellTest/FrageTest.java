package modellTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import braingain.modell.Frage;

class FrageTest {
	
	String Befehl = "Nenne ein natuerlich radioaktives Elemente unter Blei?";
	
	String[] antwortenArray = {"Technetium", "Promethium"};
	
	Frage frage = new Frage(Befehl, antwortenArray);
	
	
	@Test
	void testGetFrage() {
		Assertions.assertEquals(Befehl, frage.getFrage(), "Die Frage stimmt nicht überein.");
	}

	@Test
	void testGetAntworten() {
		Assertions.assertEquals(antwortenArray, frage.getAntworten(), "Man kann ArrayList und Array nicht verlgeichen.");
	}

}
