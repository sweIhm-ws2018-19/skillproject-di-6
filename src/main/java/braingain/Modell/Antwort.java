package main.java.braingain.Modell;

public class Antwort {
	
	private String[] schlagwoerter;
	private Frage frage;
	
	public Antwort(String[] schlagwoerter, Frage frage){
		
		this.schlagwoerter = schlagwoerter;
		this.frage = frage;
		
	}
	
	public boolean antwortBewerten(String anwort) {
		
		//TODO
		
		return false;
	}
	
	
	public String[] getSchlagwoerter() {
		return schlagwoerter;
	}

	public Frage getFrage() {
		return frage;
	}
	
}
