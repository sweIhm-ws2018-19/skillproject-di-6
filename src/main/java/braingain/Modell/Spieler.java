package main.java.braingain.Modell;

public class Spieler {
	
	private String Name;
	private int punktestand;
	private int highscore;
	
	Spieler() {
		
		
	}

	void beantwortet(boolean richtigBeantwortet){
//		if(richtigBeantwortet) {
//			punktestand++;
//		}else {
//			punktestand--;
//		}
		punktestand = richtigBeantwortet ? +1 : -1;
	}
	
	protected String getName() {
		return Name;
	}

	protected void setName(String name) {
		Name = name;
	}

	protected int getPunktestand() {
		return punktestand;
	}

	protected void setPunktestand(int punktestand) {
		this.punktestand = punktestand;
	}

	protected int getHighscore() {
		return highscore;
	}

	protected void setHighscore(int highscore) {
		this.highscore = highscore;
	}

}
