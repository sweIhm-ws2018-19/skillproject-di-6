package main.java.braingain.Modell;

enum Kategorie {
	LOGIK(new String[]{"logik","logic"}),
	MATHE(new String[]{"mathe","mathematik"}),
	GEOGRAFIE(new String[]{"geo","geografie","geographie","erdkunde"}),
	GEDAECHTNISTRAINING(new String[]{"gedaechtnistraining","gehirntraining"});
	
	private String[] value;
	
	Kategorie(String[] kategorien){this.value = kategorien;}
	
	public String[] value() {return value;}
	
	public static Kategorie getKategorie(String propose) {
		for(Kategorie k: Kategorie.values()) {
			for(String s: k.value) {
				if(s.equals(propose)) return k;
			}
		}
		return null;
	}

}
