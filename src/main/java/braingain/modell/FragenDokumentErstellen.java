package braingain.modell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class FragenDokumentErstellen {

	public static HashMap<Kategorie, HashMap<Level, HashMap<Frage, Integer>>> alleFragen;
	static Kategorie[] KategorieValues = Kategorie.values();
	static Level[] LevelValues = Level.values();

	public static void main(String... args) {
		
		 makeNewMap(); 
		 readQuestions(); 
		 //iterate();
		 buildSystem();
		 

		Spielrunde spielrunde = new Spielrunde();
		spielrunde.setKategorie("Mathe");
		spielrunde.setLevel("einfach");
		spielrunde.buildQuestions();
		
		Iterator it = spielrunde.aktuelleFragen.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(((Frage) pair.getKey()).getFrage() + " = " + pair.getValue());
	        it.remove();
	    }
		//spielrunde.refreshFragen();
/*		for (Frage f : spielrunde.fragen) {
			System.out.println(f.getFrage());
			System.out.println(f.getAntwortString());
		}*/

		
		
	}

	static void makeNewMap() {
		alleFragen = new HashMap<>();
		for (Kategorie k : KategorieValues) {
			alleFragen.put(k, new HashMap<>());
			for (Level l : LevelValues) {
				alleFragen.get(k).put(l, new HashMap<Frage, Integer>());
			}
		}
	}

	private static void newQuestion(String frage, String antwort, Kategorie kat, Level level) {
		alleFragen.get(kat).get(level).put(new Frage(frage, antwort),0);
	}

	static void readQuestions() {
		String fileName = "resources"/* System.getProperty("user.dir") */ + File.separator + "Fragen.txt";

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {

			for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
				int zuordnung = Integer.parseInt(temp);
				int lvl = zuordnung % 10;
				int kate = (zuordnung - lvl) / 10;
				Kategorie kat = KategorieValues[kate - 1];
				Level level = LevelValues[lvl - 1];
				String Frage = reader.readLine();
				String Antwort = reader.readLine();
				newQuestion(Frage, Antwort, kat, level);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void iterate() {
		for (Kategorie k : KategorieValues) {
			for (Level l : LevelValues) {
				Iterator it = alleFragen.get(k).get(l).entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        System.out.println(pair.getKey() + " = " + pair.getValue());
			        it.remove();
				
				
				/*for (Frage f : alleFragen.get(k).get(l)) {
					System.out.println(f.getFrage());
					Iterator<String> it = f.getAntwortenArrayList().iterator();
					System.out.println(it.next());

				}*/
			}
			}
			}
	}

	static void buildSystem() {
		
		//f[r jede Kathegorie Ordner erstellen
		for(Kategorie k : Kategorie.values()) {
			String pathName = "resources"+ File.separator + k.name();
			File f = new File(pathName);
			f.mkdirs();
			for(Level l : Level.values()) {
				String path2Name = "resources" + File.separator + k.name() + File.separator+ l.name() + ".fragen";
				File file = new File(path2Name);
				try { 
					file.createNewFile();
				}catch(IOException e) {
					e.printStackTrace();
				}
				try {
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path2Name));
					objectOutputStream.writeObject(alleFragen.get(k).get(l));
					objectOutputStream.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				
			}
		}		
	}	
	
	void buildFrage() {
		//String pathname = "resources"+ File.pathSeparator + 
		
		
	}

}
