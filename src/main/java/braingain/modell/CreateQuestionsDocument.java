package braingain.modell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import phrasesAndConstants.PhrasesAndConstants;

public class CreateQuestionsDocument {

	public static HashMap<Category, HashMap<Level, ArrayList<Question>>> alleFragen;
	static Category[] KategorieValues = Category.values();
	static Level[] LevelValues = Level.values();

	public static void main(String... args) {

		/*makeNewMap();
		readQuestions();*/
		//iterate();
		//buildSystem();

		Gameround spielrunde = new Gameround();
		spielrunde.setCategory(Category.MATHE);
		spielrunde.setLevel(Level.EINFACH);
		spielrunde.buildQuestions();

		for (Question f : spielrunde.questionsNotAsked) {
			System.out.println(f.getQuestion());
			System.out.println(f.getAnswer());
			System.out.println(f.getKeyWords().toString());
		}

	}

	static void makeNewMap() {
		alleFragen = new HashMap<>();
		for (Category k : KategorieValues) {
			alleFragen.put(k, new HashMap<>());
			for (Level l : LevelValues) {
				alleFragen.get(k).put(l, new ArrayList<Question>());
			}
		}
	}

	private static void newQuestion(String frage, String antwort, Category kat, Level level) {
		alleFragen.get(kat).get(level).add(new Question(frage, antwort));
	}
	
	private static void newQuestion(String frage, String antwort, ArrayList<String> keyWords, Category kat, Level level) {
		alleFragen.get(kat).get(level).add(new Question(frage, antwort, keyWords));
	}

	static void readQuestions() {
		String fileName = "resources"/* System.getProperty("user.dir") */ + File.separator + "Questions.txt";

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
			for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
				int zuordnung = Integer.parseInt(temp);
				int lvl = zuordnung % 10;
				int kate = (zuordnung - lvl) / 10;
				Category kat = KategorieValues[kate - 1];
				Level level = LevelValues[lvl - 1];
				String Frage = reader.readLine();
				String Antwort = reader.readLine();
				String tmp= reader.readLine();
				ArrayList<String> keyWords = new ArrayList<String>(Arrays.asList(tmp.split(",")));
				newQuestion(Frage , Antwort, keyWords , kat, level );
				
				/*System.out.println(Frage);
				System.out.println(Antwort);
				System.out.println(keyWords);*/
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void iterate() {
		for (Category k : KategorieValues) {
			for (Level l : LevelValues) {
				for (Question f : alleFragen.get(k).get(l)) {
					System.out.println(f.getQuestion());
					System.out.println(f.getAnswer());
					System.out.println(f.getKeyWords().toArray().toString());

				}
			}
		}
	}

	static void buildSystem() {

		// f[r jede Kathegorie Ordner erstellen
		for (Category k : Category.values()) {
			String pathName = "resources" + File.separator + k.name();
			File f = new File(pathName);
			f.mkdirs();
			for (Level l : Level.values()) {
				String path2Name = "resources" + File.separator + k.name() + File.separator + l.name()
						+ PhrasesAndConstants.QUESTION_ENDING;
				File file = new File(path2Name);
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path2Name));
					objectOutputStream.writeObject(alleFragen.get(k).get(l));
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
