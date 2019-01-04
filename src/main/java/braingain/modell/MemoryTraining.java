package braingain.modell;

public enum MemoryTraining {
	
	/** Training Koffer packen. */
	KOFFERPACKEN("Kofferpacken"),
	
	/**Training Mikado.*/
	WORTDOMINO("Doppelwortkette"),
	
	;
	
	private String value;
	
	MemoryTraining(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String toString() {
		return this.value;
	}
	
	public static MemoryTraining getRandomMemoryTraining() {
		return MemoryTraining.values()[(int) (Math.random() * MemoryTraining.values().length)];
	}
	
	public static String getAllMemoryTrainings() {
		String s = "";
		int k = values().length;
		for (int i = 0; i < k - 2; i++) {
			s += MemoryTraining.values()[i] + ", ";
		}
		s += values()[k - 2] + " und " + values()[k - 1];
		return s;
	}
	
}
