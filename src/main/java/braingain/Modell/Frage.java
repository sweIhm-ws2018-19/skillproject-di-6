package braingain.modell;

import java.util.ArrayList;

public class Frage {
	
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
		for(int i = 0; i < antworten.length; i++){
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
	
	public String getAntwortString() throws Exception{
		if(antworten.size() > 1) {
			throw new Exception("The Method getAntwortString must only be called when there is only one answer.");
		}
		return antworten.get(0);
	}
	
	public ArrayList<String> getAntwortenArrayList(){
		return antworten;
	}
	
	public String[] getAntwortenArray() {
		String[] antwortenArray = new String[antworten.size()];
		for(int i = 0; i < antworten.size(); i++) {
			antwortenArray[i] = antworten.get(i);
		}
		return antwortenArray;
	}
}
