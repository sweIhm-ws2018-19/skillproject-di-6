package main.java.braingain.Modell;

import java.util.ArrayList;

public class Spielrunde {
	ArrayList<Spieler> spieler;
	ArrayList<Frage> fragen;
	private	int anzahlSpieler;
	Kategorie kategorie;
	Level level;
	
	public Spielrunde(){
		spieler = new ArrayList<Spieler>();
		fragen = new ArrayList<Frage>();
	}

	public boolean addPlayer(Spieler spieler){
		return this.spieler.add(spieler);
	}
	
	
	public ArrayList<Spieler> getHighscoreSpieler() {
		ArrayList<Spieler> highscoreSpieler = new ArrayList<Spieler>();
		highscoreSpieler.add(spieler.get(0));
		for(Spieler s:spieler) {
			if(s.getPunktestand() > highscoreSpieler.get(0).getPunktestand()) {
				highscoreSpieler.clear();
				highscoreSpieler.add(s);
			}else if(s.getPunktestand() == highscoreSpieler.get(0).getPunktestand()) {
				highscoreSpieler.add(s);
			}
		}
		return highscoreSpieler;
	}
	
	public String[] getHighscore() {
		ArrayList<Spieler> highscoreSpieler = getHighscoreSpieler();
		int highscore = highscoreSpieler.get(0).getPunktestand();
		int anzahlGewinner = highscoreSpieler.size();
		String[] ausgaben = new String[anzahlGewinner+1];
		ausgaben[0] = Integer.toString(highscore);
		for(int i = 0; i< anzahlGewinner; i++) {
			ausgaben[i+1] = highscoreSpieler.get(i).getName();
		}
		
		return ausgaben;
	}
	
	public void fetchFragen() {
		//TODO
		//Nachdem wir Level und Kategorie bekommen haben, ziehen wir die Fragen aus unserer Datenbank.
	}
	
	public int getAnzahlSpieler() {
		return anzahlSpieler;
	}
	
	public void setAnzahlDerSpieler(int anzahlDerSpieler) {
		this.anzahlSpieler = anzahlDerSpieler;
	}
	
	
		public boolean setKategorie(String kategorie) {
		boolean kategorieIsSet=false;
		String categorie = kategorie.toLowerCase();
		Kategorie temp = getKategorie(categorie);
		if(temp!= null) {
			this.kategorie = temp;
			kategorieIsSet = true;
		}	
		return kategorieIsSet;
	}
	
	public Kategorie getKategorie(String propose) {
		for(Kategorie k: Kategorie.values()) {
			for(String s: k.value) {
				if(s.equals(propose)) return k;
			}
		}
		return null;
	}
	
	public boolean setLevel(String level) {
		boolean levelIsSet=false;
		String level_1 = level.toLowerCase();
		Level temp = getLevel(level_1);
		if(temp!= null) {
			this.level = temp;
			levelIsSet = true;
		}	
		return levelIsSet;
	}
	
	public Level getLevel(String propose) {
		for(Level k: Level.values()) {
			for(String s: k.value) {
				if(s.equals(propose)) return k;
			}
		}
		return null;
	}
	
	public void checkAntwort(String antwort){
		//TODO: kontrolliere die Antwort und aktualisiere in Spieler den Punktestand...
	}
	
	public String getNaechsteFrage(){
		//TODO: gebe die Frage aus dem Frage-Objekt der naechsten Frage in der Liste an den Handler weiter...
		return "";
	}	
}
