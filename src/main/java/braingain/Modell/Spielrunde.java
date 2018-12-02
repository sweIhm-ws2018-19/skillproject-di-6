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
	
	public void getFragen() {
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
		Kategorie temp = Kategorie.getKategorie(categorie);
		if(temp!= null) {
			this.kategorie = temp;
			kategorieIsSet = true;
		}	
		return kategorieIsSet;
	}
	
	public void setLevel(String level) {
		String lvl = level.toLowerCase();
		//TODO: finde das richtige Level im Enum und setze this.level auf dieses Enumobjekt...
	}
	
}
