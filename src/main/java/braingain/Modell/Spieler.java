package main.java.braingain.Modell;

public class Spieler {
	
	private String name;
	private int punktestand;
	private int highscore;
	
	public Spieler(String name, int punktestand, int highscore) {
		
		this.name = name;
		this.punktestand = punktestand;
		this.highscore = highscore;
		
	}

	public void beantwortet(boolean richtigBeantwortet){
		if(richtigBeantwortet) {
			punktestand++;
		}else {
			punktestand--;
		}
		//punktestand = richtigBeantwortet ? +1 : -1;
	}
	
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public int getPunktestand() {
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
