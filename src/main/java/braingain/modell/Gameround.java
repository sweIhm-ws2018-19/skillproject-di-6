package braingain.modell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import phrasesAndConstants.PhrasesAndConstants;

public class Gameround {

	private ArrayList<Player> player;
	private ArrayList<String> backPacking;
	private HashMap<Question, Integer> allNeededQuestions;
	private Player currentPlayer;
	private Question currentQuestion;
	private Category category;
	private Level level;
	private int numberOfPlayers = 0;
	private int playersCounted = 0;
	private int questionsAsked = 0;
	private int maxQuestions = 0;

	public Gameround() {
		this.player = new ArrayList<Player>();
		this.allNeededQuestions = new HashMap<Question, Integer>();
		this.backPacking = new ArrayList<String>();
	}

	public boolean addPlayer(Player p) {
		return this.player.add(p);
	}

	public void setNextRandomCurrentPlayer() {
		currentPlayer = this.player.get((int) Math.random() * this.numberOfPlayers);

		while (currentPlayer.getNumberOfQuestionsAsked() == PhrasesAndConstants.MAX_NUMBER_OF_QUESTIONS_MORE_PLAYER) {
			currentPlayer = this.player.get((int) Math.random() * this.numberOfPlayers);
		}
		currentPlayer.increaseNumberOfQuestionsAskedByOne();
	}

	public boolean checkAnswer(String antwort) {
		// TODO: kontrolliere die Antwort und aktualisiere in Spieler den Punktestand.
		return false;
	}

	public String getRightAnswer() {
		// TODO
		return null;
	}
	
	public boolean itemAtIndexInBackPack(String Item, int index) {
		return backPacking.get(index).equals(Item);
	}
	
	public void addItemToBackPack(String item) {
		backPacking.add(item);
	}
	
	public String getBackPackingAt(int position) {
		return backPacking.get(position);
	}
	
	public ArrayList<Player> getPlayerArrayList() {
		return this.player;
	}

	public Player[] getPlayerArray() {
		return this.player.toArray(new Player[player.size()]);
	}

	public Player getPlayerAt(int position) {
		return this.player.get(position);
	}

	public boolean allPlayerSet() {
		return this.player.size() == this.numberOfPlayers;
	}

	public int getPlayersCounted() {
		return this.playersCounted;
	}

	public void increasePlayerCount() {
		this.playersCounted++;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Level getLevel() {
		return this.level;
	}
	
	public void increaseQuestionsAsked() {
		this.questionsAsked++;
	}
	
	public int getQuestionsAsked() {
		return this.questionsAsked;
	}
	
	public void setMaxQuestions(int maxQuestions) {
		this.maxQuestions = maxQuestions;
	}
	
	public int getMaxQuestions() {
		return this.maxQuestions;
	}
	
	public Question getCurrentQuestion() {
		return this.currentQuestion;
	}
	
	public void setRandomNextCurrentQuestion() {
		//TODO Get a new Question with an integervalue of 0, no idea how to do this
	}
	
	public void buildQuestions() {
		String pathname = "resources" + File.separator + category + File.separator + level + PhrasesAndConstants.QUESTION_ENDING;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pathname));
			//TODO unchecked Cast
			allNeededQuestions = (HashMap<Question, Integer>) objectInputStream.readObject();
			objectInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public String getPlayerNames() {
		String speechText = "Das sind nun alle Spieler. Eure Namen sind: ";
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

	public boolean isEverythingSet() {
		boolean numberOfPLayersNotZero = this.numberOfPlayers != 0;
		boolean allPlayersSet = this.allPlayerSet();
		boolean isCategorySet = this.category != null;
		boolean isLevelSet = this.level != null || category == Category.KOFFERPACKEN;

		return numberOfPLayersNotZero && allPlayersSet && isCategorySet && isLevelSet;
	}

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
		this.questionsAsked = 0;
		this.maxQuestions = 0;
	}

}
