package main.java.braingain.Modell;

enum Kategorie {
	LOGIK(new String[]{"logik","logic"}),
	MATHE(new String[]{"mathe","mathematik"}),
	GEOGRAFIE(new String[]{"geo","geografie","geographie","erdkunde"}),
	GEDAECHTNISTRAINING(new String[]{"gedaechtnistraining","gehirntraining"});
	
	private String[] value;
	
	public Kategorie(String[] kategorien){this.value = kategorien;}
	
	public String[] value() {return value;}

}
