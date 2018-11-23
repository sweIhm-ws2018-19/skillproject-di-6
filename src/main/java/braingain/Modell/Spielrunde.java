package main.java.braingain.Modell;

import java.util.ArrayList;

public class Spielrunde {
	ArrayList<Spieler> spieler;
	ArrayList<Frage> fragen;
	private	int anzahlSpieler;
	
	public Spielrunde(){
		spieler=new ArrayList<Spieler>();
		fragen=new ArrayList<Frage>();
	}

	ArrayList<Spieler> getHighscore() {
		ArrayList<Spieler> highscoreSpieler= new ArrayList<Spieler>();
		highscoreSpieler.add(spieler.get(0));
		for(Spieler s:spieler) {
			if(s.getPunktestand()>highscoreSpieler.get(0).getPunktestand()) {
				highscoreSpieler.clear();
				highscoreSpieler.add(s);
			}else if(s.getPunktestand()==highscoreSpieler.get(0).getPunktestand()) {
				highscoreSpieler.add(s);
			}
		}
		return highscoreSpieler;
	}
	
	public int getAnzahlSpieler() {
		return anzahlSpieler;
	}
	
	public void setAnzahlDerSpieler(int anzahlDerSpieler) {
		this.anzahlSpieler = anzahlDerSpieler;
	}
	
}
