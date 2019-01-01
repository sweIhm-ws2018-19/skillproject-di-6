package braingain.modell;

public enum BackPackWords {
	
	ALEXA_ECHO("ein Alexa Echo"),
	BETT("Bett"),
	COMPUTER("Computer"),
	DINO("Dino"),
	EISEN("Eisen"),
	FEUERZEUG("Feuerzeug"),
	GABEL("Gabel"),
	HAUS("Haus"),
	INGWER("Ingwer"),
	JACKE("Jacke"),
	KAMERA("Kamera"),
	LESELAMPE("Leselampe"),
	MUETZE("Muetze"),
	NACHTHEMD("Nachthemd"),
	OHRRINGE("Ohrringe"),
	POSTKARTE("Postkarte"),
	QUALLE("Qualle"),
	RUCKSACK("Rucksack"),
	SONNENSCHIRM("Sonnenschirm"),
	TROMPETE("Trompete"),
	UHR("Uhr"),
	VOGEL("Vogel"),
	WEIN("Wein"),
	XYLOPHON("Xylophon"),
	YOGAMATTE("Yogamatte"),
	ZITRONE("Zitrone"),
	
	;
	
	
	String name;
	
	BackPackWords(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	
	
}
