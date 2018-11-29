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
		(richtigBeantwortet) {
			punktestand++;
		}else {
			punktestand--;
		}
		punktestand = richtigBeantwortet ? punktestand +1 : punktestand -1;
	}
	
	public String getName() {
		return name;
	}

	/*protected void setName(String name) {
		this.name = name;
	}*/

	public int getPunktestand() {
		return punktestand;
	}

	/*protected void setPunktestand(int punktestand) {
		this.punktestand = punktestand;
	}*/

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore() {
		if(punktestand > highscore) {
			highscore = punktestand;
		}
	}

}
