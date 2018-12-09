package braingain.modell;

import java.util.ArrayList;

public class Spielrunde {
	public ArrayList<Spieler> spieler;
	public ArrayList<Frage> fragen;
	private int anzahlSpieler;
	private int counter;
	Kategorie kategorie;
	Level level;

	public Spielrunde() {
		spieler = new ArrayList<Spieler>();
		fragen = new ArrayList<Frage>();
	}
	
	public boolean addPlayer(Spieler s){
		boolean succeded = false;
		if(spieler.size() < anzahlSpieler){
			succeded = this.spieler.add(s);
		}
		return succeded;
	}
	
	public boolean addFrage(Frage f) {
		return fragen.add(f);
	}
	
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

	public String fetchFrage() {
		String newFrage = fragen.get(counter).getFrage();
		return newFrage;
	}

	public void setAnzahlSpieler(int anzahlSpieler) {
		this.anzahlSpieler = anzahlSpieler;
	}

	public int getAnzahlSpieler() {
		return anzahlSpieler;
	}

	public boolean setKategorie(String kategorie) {
		boolean isKategorieSet = false;
		Kategorie temp = getKategorie(kategorie.toLowerCase());
		if (temp != null) {
			this.kategorie = temp;
			isKategorieSet = true;
		}
		return isKategorieSet;
	}

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

	public boolean setLevel(String level) {
		boolean levelIsSet = false;
		Level temp = getLevel(level.toLowerCase());
		if (temp != null) {
			this.level = temp;
			levelIsSet = true;
		}
		return levelIsSet;
	}

	public Level getLevel(String propose) {
		for (Level k : Level.values()) {
			for (String s : k.value()) {
				if (s.equals(propose))
					return k;
			}
		}
		return null;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public void increaseCounter() {
		this.counter++;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public void checkAntwort(String antwort) {
		// TODO: kontrolliere die Antwort und aktualisiere in Spieler den Punktestand...
	}

	public String getNaechsteFrage() {
		// TODO: gebe die Frage aus dem Frage-Objekt der naechsten Frage in der Liste an
		// den Handler weiter...
		return "";
	}

}
