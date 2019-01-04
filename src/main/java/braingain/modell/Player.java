package braingain.modell;

/**
 * The Class Player.
 */
public class Player {

	/** The name of the Player. */
	private String name;
	
	/** The points of the Player. */
	private int points = 0;
	
	/** The highscore of the Player. */
	private int highscore = 0;
	
	/** The back pack points of the Player. */
	private int backPackPoints = 0;
	
	/** The back pack highscore of the Player. */
	private int backPackHighscore = 0;
	
	/** The number of questions asked. */
	private int numberOfQuestionsAsked = 0;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name of the Player
	 * @param points the points of the Player
	 * @param highscore the highscore of the Player
	 */
	
	public Player(String name, int points, int highscore) {
		this.name = name;
		this.points = points;
		this.highscore = highscore;
	}
	
	public Player(String name, int highscore) {
		new Player(name, 0, highscore);
	}
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name of the Player
	 */
	public Player(String name) {
		new Player(name, 0, 0);
	}

	/**
	 * Rename player.
	 *
	 * @param name the new name of the Player
	 */
	public void renamePlayer(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of the Player.
	 *
	 * @return the name of the Player
	 */
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Gets the points of the Player.
	 *
	 * @return the points of the Player
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * Gets the highscore of the Player.
	 *
	 * @return the highscore of the Player
	 */
	public int getHighscore() {
		return this.highscore;
	}

	/**
	 * Sets a new highscore of the Player.
	 *
	 * @return true, if there is a new Highscore
	 */
	public boolean setHighscore() {
		if (this.points > this.highscore) {
			this.highscore = this.points;
			return true;
		}
		return false;
	}
	
	/**
	 * Sets the back pack points of the Player.
	 *
	 * @param backPackPoints the new back pack points of the Player
	 */
	public void setBackPackPoints(int backPackPoints) {
		this.backPackPoints = backPackPoints;
	}
	
	/**
	 * Gets the back pack points of the Player.
	 *
	 * @return the back pack points of the Player
	 */
	public int getBackPackPoints() {
		return this.backPackPoints;
	}
	
	/**
	 * Gets the back pack higscore of the Player.
	 *
	 * @return the back pack higscore of the Player
	 */
	public int getBackPackHigscore() {
		return this.backPackHighscore;
	}
	
	/**
	 * Sets the new back pack highscore of the Player.
	 *
	 * @return true, if there is a new Highscore of BackpackPoints
	 */
	public boolean setBackPackHighscore() {
		if (this.backPackPoints > this.backPackHighscore) {
			this.backPackHighscore = this.backPackPoints;
			return true;
		}
		return false;
	}

	/**
	 * Gets the number of questions asked.
	 *
	 * @return the number of questions asked
	 */
	public int getNumberOfQuestionsAsked() {
		return this.numberOfQuestionsAsked;
	}

	/**
	 * Increase number of questions asked by one.
	 */
	public void increaseNumberOfQuestionsAsked() {
		this.numberOfQuestionsAsked++;
	}

}
