package Modell;

public class Spieler {
	
	private String Name;
	private int punktestand;
	private int highscore;
	
	Spieler() {
		
		
	}

	void beantwortet(boolean richtigBaentwortet){
		if(richtigBaentwortet) {
			punktestand++;
		}else {
			punktestand--;
		}
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
