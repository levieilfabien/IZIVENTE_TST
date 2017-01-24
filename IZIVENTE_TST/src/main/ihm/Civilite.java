package main.ihm;

public enum Civilite {

	
	MADAME_CE(0, "0", "Madame CE"),
	MADAME_BP(1, "1", "Madame BP"),
	MADEMOISELLE_CE(2, "1", "Mademoiselle CE"),
	MADEMOISELLE_BP(3, "2", "Mademoiselle BP"),
	MONSIEUR_CE(4, "2", "Monsieur CE"),
	MONSIEUR_BP(5, "3", "Monsieur BP"),
	INDETERMINE(6, "X", "Indéterminé");
	
	/**
	 * L'index unique pour la valeur dans l'énumération.
	 */
	private int index = -1;
	/**
	 * Le code unique pour la valeur de l'énum à utiliser lors de la construction du XML.
	 */
	private String code = "";
	/**
	 * Le libellé unique de la valeur de l'énum à utiliser lors de l'affichage dans l'interface.
	 */
	private String libelle = "";
	
	/**
	 * Le constructeur par défaut.
	 * @param index l'index unique
	 * @param code le code à envoyer
	 * @param libelle le libellé à afficher
	 */
	private Civilite(int index, String code, String libelle) {
		this.index = index;
		this.code = code;
		this.libelle = libelle;
	}
	
	/**
	 * Remplace le toString "classique".
	 */
	public String toString() {
		return code + " : " + libelle;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
