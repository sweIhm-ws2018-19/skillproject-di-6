package main.java.braingain.Modell;

enum Kategorie {
	LOGIK({"logik","logic"}),
	MATHE({"mathe","mathematik"}),
	GEOGRAFIE({"geo","geografie","geographie","erdkunde"}),
	GEDAECHTNISTRAINING({"gedaechtnistraining","gehirntraining"});
	
	private String[] value;
	
	public Kategorie(String[] kategorien){this.value = kategorien;}
	
	public String[] value() {return value;}

}
