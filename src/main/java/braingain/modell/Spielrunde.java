package braingain.modell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * The Class Spielrunde.
 */
public class Spielrunde {

	public ArrayList<Spieler> spieler;
	public ArrayList<Frage> fragen;
	HashMap<String, Integer> spielerTrophy = new HashMap<>();
	private int anzahlSpieler;
	private int aktuellerSpieler;
	private int counter;
	Kategorie kategorie;
	Level level;
	Frage defaultFrage;
	HashMap<Frage, Integer> aktuelleFragen;
	

	/**
	 * Initialisiert eine neue Spielrunde, hier werden die Array-Lists erstellt fuer
	 * Spieler und Fragen.
	 */
	public Spielrunde() {
		spieler = new ArrayList<Spieler>();
		fragen = new ArrayList<Frage>();
		counter = 0;
		aktuellerSpieler = 0;
		defaultFrage = new Frage("Was ist 1 + 1?", "2");
	}

	/**
	 * Fuegt einen Spieler hinzu.
	 *
	 * @param Den Spieler der hinzugefuegt werden soll
	 * @return true, wenn der Spieler hinzugefuegt werden konnte
	 */
	public boolean addPlayer(Spieler s) {
		boolean succeded = false;
		if (spieler.size() < anzahlSpieler) {
			succeded = this.spieler.add(s);
		}
		return succeded;
	}
	
	
	void actualizeSpielerTrophy() {
		String fileName = System.getProperty("user.dir") + File.separator + "Spieler.txt";

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {

			for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
				String name = temp;
				int integer = Integer.parseInt(reader.readLine());
				spielerTrophy.put(name, integer);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void saveSpielerTrophy() {
		
		for (Spieler s : spieler) {
			if(spielerTrophy.get(s.getName()) == null) {
				spielerTrophy.put(s.getName(), s.getPunktestand());
			}
		}
		String fileName = System.getProperty("user.dir") + File.separator + "Spieler.txt";
		new File(fileName).mkdir();
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, false)))) {
			for(Entry<String, Integer> entry : spielerTrophy.entrySet()) {
			    writer.write(entry.getKey());
			    writer.write(entry.getValue());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fuegt eine Frage hinzu.
	 *
	 * @param Die Frage die hinzugefuegt werden soll
	 * @return true, wenn die Frage hinzugefuegt wernden konnte
	 */
	public boolean addFrage(Frage f) {
		return fragen.add(f);
	}

	/**
	 * Diese Methode ermittelt den/die Spieler mit der hoechsten Punktezahl.
	 *
	 * @return Den/Die Spieler mit der hoechsten Punktezahl
	 */
	public ArrayList<Spieler> getHighscoreSpieler() {
		ArrayList<Spieler> highscoreSpieler = new ArrayList<Spieler>();
		highscoreSpieler.add(spieler.get(0));
		for (Spieler s : spieler) {
			if (s.getPunktestand() > highscoreSpieler.get(0).getPunktestand()) {
				highscoreSpieler.clear();
				highscoreSpieler.add(s);
			} else if (s.getPunktestand() == highscoreSpieler.get(0).getPunktestand()) {
				highscoreSpieler.add(s);
			}
		}
		return highscoreSpieler;
	}

	/**
	 * Diese Methode ermittelt die hoechste Punktzahl und die Spieler, welche diese
	 * haben.
	 *
	 * @return Ein String-Array, an der ersten Stelle steht die Punktzahl und danach
	 *         die jeweiligen Spieler mit dieser Punktzahl
	 */
	public String[] getHighscore() {
		ArrayList<Spieler> highscoreSpieler = getHighscoreSpieler();
		int highscore = highscoreSpieler.get(0).getPunktestand();
		int anzahlGewinner = highscoreSpieler.size();
		String[] ausgaben = new String[anzahlGewinner + 1];
		ausgaben[0] = Integer.toString(highscore);
		for (int i = 1; i < anzahlGewinner; i++) {
			ausgaben[i] = highscoreSpieler.get(i).getName();
		}

		return ausgaben;
	}

	/**
	 * Ermittelt die naechste Frage.
	 *
	 * @return Die naechste Frage die hinzugefuegt wurde
	 */
	public String fetchFrage() {
		// String newFrage = fragen.get(counter).getFrage();
		String newFrage = defaultFrage.getFrage();
		return newFrage;
	}

	/**
	 * Aktualisiert alle Fragen
	 */
/*
	public void refreshFragen() {
		fragen = Frage.alleFragen.get(kategorie).get(level);
	}*/

	/**
	 * Sets the anzahl spieler.
	 *
	 * @param anzahlSpieler the new anzahl spieler
	 */
	public void setAnzahlSpieler(int anzahlSpieler) {
		this.anzahlSpieler = anzahlSpieler;
	}

	/**
	 * Gets the anzahl spieler.
	 *
	 * @return the anzahl spieler
	 */
	public int getAnzahlSpieler() {
		return anzahlSpieler;
	}

	/**
	 * Sets the kategorie.
	 *
	 * @param Die Kategorie die gesetzt werden soll
	 * @return true, wenn diese Kategorie existiert
	 */
	public boolean setKategorie(String kategorie) {
		boolean isKategorieSet = false;
		Kategorie temp = getKategorie(kategorie.toLowerCase());
		if (temp != null) {
			this.kategorie = temp;
			isKategorieSet = true;
		}
		return isKategorieSet;
	}

	/**
	 * Ermittelt den Namen der eingespeicherten Kategorie.
	 *
	 * @param Den eingespeicherten Namen der Kategorie den man haben will.
	 * @return Den eingespeicherten Namen der Kategorie
	 */
	public Kategorie getKategorie(String propose) {
		for (Kategorie k : Kategorie.values()) {
			for (String s : k.value()) {
				if (s.equals(propose)) {
					return k;
				}
			}
		}
		return null;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the level
	 * @return true, if successful
	 */
	public boolean setLevel(String level) {
		boolean levelIsSet = false;
		Level temp = getLevel(level.toLowerCase());
		if (temp != null) {
			this.level = temp;
			levelIsSet = true;
		}
		return levelIsSet;
	}

	/**
	 * Ermittelt den Namen des eingespeicherten Levels.
	 *
	 * @param Den eingespeicherten Namen des Levels den man haben will.
	 * @return Den eingespeicherten Namen des Levels
	 */
	public Level getLevel(String propose) {
		for (Level k : Level.values()) {
			for (String s : k.value()) {
				if (s.equals(propose))
					return k;
			}
		}
		return null;
	}

	/**
	 * Sets the counter.
	 *
	 * @param counter the new counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * Diese Methode erhoeht den Counter um eins.
	 */
	public void increaseCounter() {
		this.counter++;
	}

	/**
	 * Gets the counter.
	 *
	 * @return the counter
	 */
	public int getCounter() {
		return this.counter;
	}

	public void naechsterSpieler() {
		aktuellerSpieler += 1;
		if (aktuellerSpieler == anzahlSpieler) {
			aktuellerSpieler -= anzahlSpieler;
		}
	}

	/**
	 * Testet ob die gegeben Antwort die richtige ist und aktualisieert dann damit
	 * den Punktestand.
	 *
	 * @param Die gegeben Antwort
	 */
	public boolean checkAntwort(String antwort) {
		// TODO: kontrolliere die Antwort und aktualisiere in Spieler den Punktestand...
		/*
		 * ArrayList<String> antworten = fragen.get(counter).getAntwortenArrayList();
		 * boolean ubereinstimmung = false; int antwortZahl = antworten.size();
		 * while(!ubereinstimmung && antwortZahl>0) { antwortZahl -= 1; ubereinstimmung
		 * = antworten.get(antwortZahl).equalsIgnoreCase(antwort); }
		 * spieler.get(aktuellerSpieler).beantwortet(ubereinstimmung); return
		 * ubereinstimmung;
		 */
		return antwort.contains(defaultFrage.getAntwortString());
	}

	public String getRichtigeAntwort() {
		// return fragen.get(counter).getAntwortString();
		return defaultFrage.getAntwortString();
	}
	
	public String[] getPlayer() {
		String[] playerArray = new String[anzahlSpieler];
		for(int i = 0; i < anzahlSpieler; i++) {
			playerArray[i] = spieler.get(i).getName();
		}
		return playerArray;
	}
	
	/**
	 * Diese Methode fuellt unsere HashMap von int und fragen aus den TextDateien, im
	 * recourses Ordner
	 */
	public void buildQuestions() {
		
		String pathname = "resources"+ File.separator + kategorie + File.separator + level + ".fragen";
		try {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pathname));
				aktuelleFragen = (HashMap<Frage, Integer>)objectInputStream.readObject();
				objectInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
}
