package braingain.modell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FrageDokumentErstellen {
	
	static ArrayList<ArrayList<ArrayList<Frage>> > fragen;
	static Kategorie[] KategorieValues = Kategorie.values();
	static Level[] LevelValues = Level.values();
	
	public static void main(String...args) {
		
		iterate();
	}

	static void makeNewMap() {
		fragen = new ArrayList<>(); 
		for(int i = 0; i< KategorieValues.length; i++) {
			fragen.add(new ArrayList<>());
			for(int j = 0 ; j < LevelValues.length ; j++ ) {
				fragen.get(i).add(new ArrayList<Frage>());
			}
		}	
	}
	
	private static void newQuestion(String frage, String antwort, int kat, int level) {
		fragen.get(kat-1).get(level-1).add(new Frage(frage,antwort));
	}
	
	static void readQuestions() {
		String fileName = "resources"+ File.separator + "Fragen.txt";
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))){
			
			for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
				int zuordnung = Integer.parseInt(temp);
				int lvl = zuordnung % 10;
				int kat = (zuordnung-lvl)/10;
				String Frage = reader.readLine();
				String Antwort = reader.readLine();
				newQuestion(Frage,Antwort,kat,lvl);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void iterate() {
		for(int i = 0; i< KategorieValues.length; i++) {
			for(int j = 0 ; j < LevelValues.length ; j++ ) {
				for(Frage f : fragen.get(i).get(j)) {
					System.out.println(f.getFrage());
					System.out.println(f.getAntwortenArray());
								
				}
			}
		}
	}
	
	static void buildSystsem() {
		
		//f[r jede Kathegorie Ordner erstellen
		for(Kategorie k : Kategorie.values()) {
			String pathName = "resources"+ File.separator + k.name();
			File f = new File(pathName);
			f.mkdirs();
			for(Level l : Level.values()) {
				String path2Name = "resources" + File.separator + k.name() + l.name() + ".fragen";
				File file = new File(path2Name);
				try { 
					file.createNewFile();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		//
		
	}
	
}

