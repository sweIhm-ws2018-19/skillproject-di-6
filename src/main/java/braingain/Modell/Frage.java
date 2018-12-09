package main.java.braingain.Modell;

import java.io.Serializable;
import java.util.ArrayList;

public class Frage implemets Serializable{
	
	private String frage;
	private ArrayList<String> antworten;
	public static ArrayList<ArrayList<ArrayList<Frage>>> alleFragen;
	
	static{
		makeNewMap();
		readQuestions();
	}
	
	public Frage(String frage, String antwort) {
		this.frage = frage;
		antworten = new ArrayList<String>();
		antworten.add(antwort);
	}
	
	public Frage(String frage, String[] antworten) {
		this.frage = frage;
		this.antworten = new ArrayList<String>();
		for(int i = 0; i<antworten.length; i++){
			this.antworten.add(antworten[i]);	
		}
	}
	
	public Frage(String frage, ArrayList<String> antworten){
		this.frage = frage;
		this.antworten = antworten;
	}
	
	public String getFrage() {
		return frage;
	}
	
	public ArrayList<String> getAntworten(){
		return antworten;
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
	
	//Hilfsfunktion, um die Richtigkeit der Uebernahme zu ueberpruefen
	private static void iterate() {
		for(int i = 0; i< KategorieValues.length; i++) {
			for(int j = 0 ; j < LevelValues.length ; j++ ) {
				for(Frage f : fragen.get(i).get(j)) {
					System.out.println(f.getFrage());
					System.out.println(f.getAntworten());
								
				}
			}
		}
	}	
}
