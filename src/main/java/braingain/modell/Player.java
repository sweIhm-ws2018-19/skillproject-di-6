package braingain.modell;

public class Player {
	
	private String name;
	private int points = 0;
	private int highscore = 0;
	private int numberOfQuestionsAsked = 0;
	
	public Player(String name, int points, int highscore) {
		this.name = name;
		this.points = points;
		this.highscore = highscore;
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public void renamePlayer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public int getHighscore() {
		return this.highscore;
	}
	
	public void setHighscore() {
		if(points > highscore) {
			highscore = points;
		}
	}
	
	public int getNumberOfQuestionsAsked() {
		return this.numberOfQuestionsAsked;
	}
	
	public void increaseNumberOfQuestionsAskedByOne() {
		this.numberOfQuestionsAsked++;
	}
	
}