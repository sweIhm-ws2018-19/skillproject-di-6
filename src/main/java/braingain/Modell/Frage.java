package main.java.braingain.Modell;

import java.io.Serializable;
import java.util.ArrayList;

public class Frage implemets Serializable{
	
	private String frage;
	private ArrayList<String> antworten;
	
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
	
}
