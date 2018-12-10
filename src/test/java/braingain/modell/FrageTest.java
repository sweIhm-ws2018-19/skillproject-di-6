package braingain.modell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import braingain.modell.Frage; 

class FrageTest {

	String befehl1 = "Wie viele Seiten hat ein Wuerfel?";
	String befehl2 = "Nenne ein natuerlich radioaktives Elemente unter Blei?";
	String befehl3 = "Nenne einen Platonischen Körper";

	String antwort = "6";
	String[] antwortenArray = { "Technetium", "Promethium" };
	ArrayList<String> antwortenArrayList = new ArrayList<String>(
			Arrays.asList("Tetraeder", "Hexaeder", "Oktaeder", "Dodekaeder", "Ikosaeder"));

	Frage frage1 = new Frage(befehl1, antwort);
	Frage frage2 = new Frage(befehl2, antwortenArray);
	Frage frage3 = new Frage(befehl3, antwortenArrayList);
	
	@Test
	void testFrage() {
		Assertions.assertNotNull(frage1, "Die frage existiert, mit dem ersten Konstruktor.");
		Assertions.assertNotNull(frage2, "Die frage existiert, mit dem zweiten Konstruktor.");
		Assertions.assertNotNull(frage3, "Die frage existiert, mit dem dritten Konstruktor.");
	}
	
	@Test
	void testGetFrage() {
		Assertions.assertEquals(befehl1, frage1.getFrage(), "Die Frage stimmt nicht ueberein.");
		Assertions.assertEquals(befehl2, frage2.getFrage(), "Die Frage stimmt nicht ueberein.");
		Assertions.assertEquals(befehl3, frage3.getFrage(), "Die Frage stimmt nicht ueberein.");
	}

	@Test
	void testGetAntworten() {

		Assertions.assertEquals(antwort, frage1.getAntwortString(), "Die Antwort stimmt nicht.");
		Assertions.assertArrayEquals(antwortenArray, frage2.getAntwortenArray(), "Die Antworten stimmt nicht.");

		for (int i = 0; i < antwortenArrayList.size(); i++) {
			Assertions.assertEquals(antwortenArrayList.get(i), frage3.getAntwortenArrayList().get(i),
					"Die Antworten stimmen nicht ueberein");
		}
		// Zweiter Test für eigentlich das gleiche, aber die Sicherheit.
		Iterator<String> it = antwortenArrayList.iterator();
		Iterator<String> itTest = frage3.getAntwortenArrayList().iterator();

		Assertions.assertEquals(it.next(), itTest.next(), "Die Antworten stimmen nicht ueberein.");
		Assertions.assertEquals(frage2.getAntwortString(), antwortenArray[0], "Es muesste die erste Antwort im Array sein.");
	}
}
