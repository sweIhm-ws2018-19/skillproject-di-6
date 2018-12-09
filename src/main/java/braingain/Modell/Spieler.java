package braingain.modell;

public class Spieler {
	
	private final String name;
	private int punktestand;
	private int highscore;
	
	public Spieler(String name, int punktestand, int highscore) {
		
		this.name = name;
		this.punktestand = punktestand;
		this.highscore = highscore;
		
	}

	public void beantwortet(boolean richtigBeantwortet){
		punktestand = richtigBeantwortet ? punktestand + 1 : punktestand - 1;
	}
	
	public String getName() {
		return name;
	}

	public int getPunktestand() {
		return punktestand;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore() {
		if(punktestand > highscore) {
			highscore = punktestand;
		}
	}

}
