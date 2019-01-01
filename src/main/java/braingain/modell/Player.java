package braingain.modell;

public class Player {

	private String name;
	private int points = 0;
	private int highscore = 0;
	private int backPackPoints = 0;
	private int backPackHighscore = 0;
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

	@Override
	public String toString() {
		return this.name;
	}

	public int getPoints() {
		return this.points;
	}

	public int getHighscore() {
		return this.highscore;
	}

	public boolean setHighscore() {
		if (this.points > this.highscore) {
			this.highscore = this.points;
			return true;
		}
		return false;
	}
	
	public void setBackPackPoints(int backPackPoints) {
		this.backPackPoints = backPackPoints;
	}
	
	public int getBackPackPoints() {
		return this.backPackPoints;
	}
	
	public int getBackPackHigscore() {
		return this.backPackHighscore;
	}
	
	public boolean setBackPackHighscore() {
		if (this.backPackPoints > this.backPackHighscore) {
			this.backPackHighscore = this.backPackPoints;
			return true;
		}
		return false;
	}

	public int getNumberOfQuestionsAsked() {
		return this.numberOfQuestionsAsked;
	}

	public void increaseNumberOfQuestionsAskedByOne() {
		this.numberOfQuestionsAsked++;
	}

}
