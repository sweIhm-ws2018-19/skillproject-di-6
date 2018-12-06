package main.java.braingain.Modell;

	EINFACH(new String[] {"einfach", "simpel"}),
	MITTEL(new String[] {"mittel", "mittelschwer"}),
	ANSPRUCHSVOLL(new String[] {"anspruchsvoll"}),
	SCHWER(new String[] {"schwer", "schwierig"});
	
	
	String[] value;
	
	Level(String[] kategorien){this.value = kategorien;}
	
	public String[] value() {return value;}
	

}
