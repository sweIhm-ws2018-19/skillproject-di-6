package braingain.modell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import phrasesAndConstants.PhrasesAndConstants;

/**
 * The Class Gameround.
 */
public class Gameround {

	/** The player. */
	private ArrayList<Player> player;
	
	/** The ArrayList for the backPackingGame. */
	private ArrayList<String> backPacking;
	
	/** The ArrayListArray for the Questions. */
	private ArrayList<Question> questionsAsked;
	private ArrayList<Question> questionsNotAsked;
	
	/** The current player. */
	private Player currentPlayer;
	
	/** The current question. */
	private Question currentQuestion;
	
	/** The category. */
	private Category category;
	
	/** The level. */
	private Level level;
	
	private int numberOfPlayers = 0, playersCounted = 0, questionsAskedInt = 0, maxQuestions = 0;
	
	/**
	 * Instantiates a new gameround.
	 */
	public Gameround() {
		this.player = new ArrayList<Player>();
		this.backPacking = new ArrayList<String>();
		this.questionsAsked = new ArrayList<Question>();
		this.questionsNotAsked = new ArrayList<Question>();
	}

	/**
	 * Adds the player.
	 *
	 * @param p the player to be added
	 * @return true, if successful
	 */
	public boolean addPlayer(Player p) {
		return this.player.add(p);
	}

	/**
	 * Sets the next random current player.
	 */
	public void setNextRandomCurrentPlayer() {
		Player nextPlayer = this.player.get((int) (Math.random() * this.numberOfPlayers));

		while (nextPlayer.equals(currentPlayer)) {
			nextPlayer = this.player.get((int) (Math.random() * this.numberOfPlayers));
		}
		currentPlayer = nextPlayer;
	}

	/**
	 * Checks if the given answer of the Player is correct.
	 *
	 * @param answer the answer
	 * @return true, if correct
	 */
	public boolean checkAnswer(String answer) {
		return this.currentQuestion.checkAnswer(answer);
	}

	/**
	 * Gets the right answer, of the current Question.
	 *
	 * @return the right answer, of the Current Question
	 */
	public String getRightAnswer() {
		return this.currentQuestion.getRightAnswer();
	}

	/**
	 * Adds the given item to back pack.
	 *
	 * @param item the item to be added to the Backpack
	 */
	public void addItemToBackPack(String item) {
		String[] word = item.split("\\W+");
		String wordAdd;
		if (word.length == 1) {
			wordAdd = word[0];
		} else {
			wordAdd = word[0] + " " + word[1];
		}
		this.backPacking.add(wordAdd);
	}

	/**
	 * Gets the item in the Backpack at the given position.
	 *
	 * @param position the position from which the item is in the Backpack
	 * @return The Item at the given position
	 */
	public String getBackPackingAt(int position) {
		return this.backPacking.get(position);
	}

	/**
	 * Checks if a word is saved at a particular position.
	 *
	 * @param word the word to be tested
	 * @param index the index at which the Word should be
	 * @return true, if the Word is at the given index
	 */
	public boolean checkWord(String word, int index) {
		String s = this.getWord(index);
		return word.toLowerCase().contains(s.toLowerCase());
	}

	/**
	 * Gets the word from the Backpack.
	 *
	 * @param index the index where the Word is in the Backpacking List
	 * @return the Word at the given index
	 */
	private String getWord(int index) {
		String[] word = this.backPacking.get(index).split("\\W+");
		if (word.length == 1) {
			return word[0];
		} else {
			return word[1];
		}
	}

	/**
	 * Checks if a given Word is inside the ArrayList.
	 *
	 * @param word the word to be checked
	 * @return true, if the given Word is inside
	 */
	private boolean isInside(String word) {
		for (int i = 0; i < this.backPacking.size(); i++) {
			if (this.getWord(i).equals(word)) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}

	/**
	 * Gets the random backpack word for alexa.
	 *
	 * @return the random back pack word for alexa, out of the Enum BackPackWords
	 */
	public String getRandomBackPackWordForAlexa() {
		int max = 0;
		String testWord = BackPackWords.values()[(int) (Math.random() * BackPackWords.values().length)].toString();
		while (this.isInside(testWord)) {
			max++;
			testWord = BackPackWords.values()[(int) (Math.random() * BackPackWords.values().length)].toString();
			if(max > 20) {
				break;
			}
		}
		return testWord;
	}

	/**
	 * Iterates through all Items in the BackPackArraList.
	 *
	 * @return All Items in the BackPackArrayList in one String
	 */
	public String backPackingAlexa() {
		String s = PhrasesAndConstants.ALEXA_BACK_PACK;
		Iterator<String> it = backPacking.iterator();
		while(it.hasNext()) {
			s += ", " + it.next();
		}
		return s;
	}
	
	/**
	 * Gets the ArrayList of all Players.
	 *
	 * @return the ArrayList of all Players
	 */
	public ArrayList<Player> getPlayerArrayList() {
		return this.player;
	}

	/**
	 * Gets the Array of Players.
	 *
	 * @return the Array of Players
	 */
	public Player[] getPlayerArray() {
		return this.player.toArray(new Player[player.size()]);
	}

	/**
	 * Gets the player at a given position.
	 *
	 * @param position the position of the Players
	 * @return the player at the given position
	 */
	public Player getPlayerAt(int position) {
		return this.player.get(position);
	}

	/**
	 * Tests if all Player are set.
	 *
	 * @return true, if all Players are set
	 */
	public boolean allPlayerSet() {
		return this.player.size() == this.numberOfPlayers;
	}

	/**
	 * Gets the current player.
	 *
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	/**
	 * Sets the Highscore of the first Player.
	 *
	 * @return true, if there is a new Highscore to be set
	 */
	public boolean setHighscore() {
		return this.player.get(0).setHighscore();
	}
	
	/**
	 * Sets the Backpack Highscore of the first Player.
	 *
	 * @return true, if there is a new Backpack Highscore to be set
	 */
	public boolean setBackPackHighscore() {
		return this.player.get(0).setBackPackHighscore();
	}
	
	/**
	 * Gets the Number of Players that have already been counted.
	 *
	 * @return the players counted
	 */
	public int getPlayersCounted() {
		return this.playersCounted;
	}

	/**
	 * Increases the number of Players counted by one.
	 */
	public void increasePlayerCount() {
		this.playersCounted++;
	}

	/**
	 * Sets the number of players.
	 *
	 * @param numberOfPlayers the new number of players
	 */
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	/**
	 * Gets the number of players.
	 *
	 * @return the number of players
	 */
	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public Level getLevel() {
		return this.level;
	}

	/**
	 * Increases questions asked by one.
	 */
	public void increaseQuestionsAsked() {
		this.questionsAskedInt++;
	}

	/**
	 * Gets the Number of Questions that have already been asked.
	 *
	 * @return the Number of Questions that have already been asked.
	 */
	public int getQuestionsAsked() {
		return this.questionsAskedInt;
	}

	/**
	 * Sets the maximum Number of Questions per Gameround.
	 *
	 * @param maxQuestions the new maximum Number of Questions
	 */
	public void setMaxQuestions(int maxQuestions) {
		this.maxQuestions = maxQuestions;
	}

	/**
	 * Gets the maximum Number of Questions.
	 *
	 * @return the maximum Number of Questions
	 */
	public int getMaxQuestions() {
		return this.maxQuestions;
	}

	/**
	 * Gets the Size of the Backpack, therefore the Number of Items in it.
	 *
	 * @return the Size of the Backpack
	 */
	public int getBackPackSize() {
		return this.backPacking.size();
	}
	
	/**
	 * Gets the current question.
	 *
	 * @return the current question
	 */
	public Question getCurrentQuestion() {
		return this.currentQuestion;
	}

	/**
	 * Sets random the next current question.
	 */
	public void setRandomNextCurrentQuestion() {
		if(!questionsNotAsked.isEmpty()) {
			this.currentQuestion = questionsNotAsked.get((int)(Math.random() * questionsNotAsked.size()));
			this.questionsAsked.add(currentQuestion);
		}else {
			this.currentQuestion = questionsAsked.get((int)(Math.random() * questionsAsked.size()));
		}
	}

	/**
	 * Builds all the Questions needed for the current Gameround.
	 */
	public void buildQuestions() {
		String pathname = "resources" + File.separator + category + File.separator + level
				+ PhrasesAndConstants.QUESTION_ENDING;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pathname));
			// TODO unchecked Cast
			// allNeededQuestions = (HashMap<Question, Integer>) objectInputStream.readObject();
			objectInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the player names in one String.
	 *
	 * @return the player names in one String
	 */
	public String getPlayerNames() {
		String speechText = PhrasesAndConstants.PLAYERNAMES;
		Player[] player = this.getPlayerArray();
		switch (getNumberOfPlayers()) {
		case 2:
			speechText += String.format("%s und %s. ", player[0].getName(), player[1].getName());
			break;
		case 3:
			speechText += String.format("%s, %s und %s. ", player[0].getName(), player[1].getName(),
					player[2].getName());
			break;
		case 4:
			speechText += String.format("%s, %s, %s und %s. ", player[0].getName(), player[1].getName(),
					player[2].getName(), player[3].getName());
			break;
		default:
			speechText += "Fehler. ";
			break;
		}
		return speechText;
	}

	/**
	 * Checks if everything is set.
	 *
	 * @return true, if everything is set
	 */
	public boolean isEverythingSet() {
		boolean numberOfPLayersNotZero = this.numberOfPlayers != 0;
		boolean allPlayersSet = this.allPlayerSet();
		boolean isCategorySet = this.category != null;
		boolean isLevelSet = this.level != null || category == Category.KOFFERPACKEN;

		return numberOfPLayersNotZero && allPlayersSet && isCategorySet && isLevelSet;
	}

	/**
	 * Resets the whole Gameround, as if you would create a new one.
	 */
	public void reset() {
		player.clear();
		backPacking.clear();
		this.currentQuestion = null;
		this.currentPlayer = null;
		this.currentQuestion = null;
		this.category = null;
		this.level = null;
		this.numberOfPlayers = 0;
		this.playersCounted = 0;
		this.questionsAskedInt = 0;
		this.maxQuestions = 0;
	}

}
