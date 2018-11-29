package main.java.braingain.Modell;

enum Kategorie {
	LOGIK({"logik"}),
	MATHE({"mathe","mathematik"}),
	GEOGRAFIE({"geo","geografie","geographie","erdkunde"}),
	GEDAECHTNISTRAINING({"gedaechtnistraining","gehirntraining"});
	
	private String[] Value;
	
	Kategorie(String[] kategorien){this.Value = kategorien;}
	
	public String[] Value() {return Value;}

}
