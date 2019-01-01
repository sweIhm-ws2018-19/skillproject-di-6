package braingain.modell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import phrasesAndConstants.PhrasesAndConstants;

public class Gameround {

	private ArrayList<Player> player;
	private ArrayList<String> backPacking;
	private HashMap<Question, Integer> allNeededQuestions;
	private Player currentPlayer = null;
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
		Player nextPlayer = this.player.get((int) (Math.random() * this.numberOfPlayers));

		while (nextPlayer.equals(currentPlayer)) {
			nextPlayer = this.player.get((int) (Math.random() * this.numberOfPlayers));
		}
		currentPlayer = nextPlayer;
	}

	public boolean checkAnswer(String antwort) {
		// TODO: kontrolliere die Antwort und aktualisiere in Spieler den Punktestand.
		return false;
	}

	public String getRightAnswer() {
		// TODO
		return null;
	}

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

	public String getBackPackingAt(int position) {
		return this.backPacking.get(position);
	}

	public boolean checkWord(String word, int index) {
		String s = this.getWord(index);
		return word.toLowerCase().contains(s.toLowerCase());
	}

	private String getWord(int index) {
		String[] word = this.backPacking.get(index).split("\\W+");
		if (word.length == 1) {
			return word[0];
		} else {
			return word[1];
		}
	}

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

	public String backPackingAlexa() {
		String s = "Ich packe meinen Koffer und nehme mit";
		Iterator<String> it = backPacking.iterator();
		while(it.hasNext()) {
			s += ", " + it.next();
		}
		return s;
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

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public boolean setHighscore() {
		return player.get(0).setHighscore();
	}
	
	public boolean setBackPackHighscore() {
		return player.get(0).setBackPackHighscore();
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

	public int getBackPackSize() {
		return this.backPacking.size();
	}
	
	public Question getCurrentQuestion() {
		return this.currentQuestion;
	}

	public void setRandomNextCurrentQuestion() {
		// TODO Get a new Question with an integervalue of 0, no idea how to do this
	}

	public void buildQuestions() {
		String pathname = "resources" + File.separator + category + File.separator + level
				+ PhrasesAndConstants.QUESTION_ENDING;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pathname));
			// TODO unchecked Cast
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
