package main.java.braingain.Modell;

import java.util.ArrayList;

public class Frage {
	
	String frage;
	ArrayList<String> antworten;
	
	public Frage(String frage, String antwort) {
		this.frage = frage;
		antworten = new ArrayList<String>();
		antworten.add(antwort);
	}
	
	public String getFrage() {
		return frage;
	}
	
	public ArrayList<String> getAntworten(){
		return antworten;
	}
	
	
}
