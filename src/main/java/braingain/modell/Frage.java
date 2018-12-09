package braingain.modell;

import java.util.ArrayList;

/**
 * The Class Frage.
 */
public class Frage {
	
	private String frage;
	private ArrayList<String> antworten;
	
	/**
	 * Initialisiert eine neue Frage, mit einer Frage und einer Antwort als String.
	 *
	 * @param frage the frage
	 * @param antwort the antwort
	 */
	public Frage(String frage, String antwort) {
		this.frage = frage;
		antworten = new ArrayList<String>();
		antworten.add(antwort);
	}
	
	/**
	 * Initialisiert eine neue Frage, mit einem String-Array als Antworten.
	 *
	 * @param frage the frage
	 * @param antworten the antworten
	 */
	public Frage(String frage, String[] antworten) {
		this.frage = frage;
		this.antworten = new ArrayList<String>();
		for(int i = 0; i < antworten.length; i++){
			this.antworten.add(antworten[i]);	
		}
	}
	
	/**
	 * Initialisiert eine neue Frage mit einer Array-List als antwort.
	 *
	 * @param frage the frage
	 * @param antworten the antworten
	 */
	public Frage(String frage, ArrayList<String> antworten){
		this.frage = frage;
		this.antworten = antworten;
	}
	
	/**
	 * Gets the frage.
	 *
	 * @return the frage
	 */
	public String getFrage() {
		return frage;
	}
	
	/**
	 * Diese Methode sollte aufgerufen werden, wenn es nur eine Antwort gibt.
	 *
	 * @return the antwort string
	 */
	public String getAntwortString() {
		if(antworten.size() > 1) {
			System.out.println("Es gibt mehr als nur eine Antwort, es wird nur die erste zurueckgegeben.");;
		}
		return antworten.get(0);
	}
	
	/**
	 * Diese Methode sollte aufgerufen werden, wenn man ein String-Array als Antwort haben will.
	 *
	 * @return the antworten array
	 */
	public String[] getAntwortenArray() {
		String[] antwortenArray = new String[antworten.size()];
		for(int i = 0; i < antworten.size(); i++) {
			antwortenArray[i] = antworten.get(i);
		}
		return antwortenArray;
	}
	/**
	 * Mit dieser Methode bekommt man die Array-List als Antwort wieder.
	 *
	 * @return the antworten array list
	 */
	public ArrayList<String> getAntwortenArrayList(){
		return antworten;
	}
}
