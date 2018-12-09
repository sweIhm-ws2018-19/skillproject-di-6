package braingain.modell;

public enum Level {

	EINFACH(new String[] { "einfach", "simpel" }),
	MITTEL(new String[] { "mittel", "mittelschwer" }),
	ANSPRUCHSVOLL(new String[] { "anspruchsvoll" }),
	SCHWER(new String[] { "schwer", "schwierig" });

	private String[] value;

	Level(String[] kategorien) {
		this.value = kategorien;
	}

	public String[] value() {
		return value;
	}

	public String toString() {
		return value[0];
	}

}
