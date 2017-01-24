package main.ihm;

public enum Civilite {

	
	MADAME_CE(0, "0", "Madame CE"),
	MADAME_BP(1, "1", "Madame BP"),
	MADEMOISELLE_CE(2, "1", "Mademoiselle CE"),
	MADEMOISELLE_BP(3, "2", "Mademoiselle BP"),
	MONSIEUR_CE(4, "2", "Monsieur CE"),
	MONSIEUR_BP(5, "3", "Monsieur BP"),
	INDETERMINE(6, "X", "Ind�termin�");
	
	/**
	 * L'index unique pour la valeur dans l'�num�ration.
	 */
	private int index = -1;
	/**
	 * Le code unique pour la valeur de l'�num � utiliser lors de la construction du XML.
	 */
	private String code = "";
	/**
	 * Le libell� unique de la valeur de l'�num � utiliser lors de l'affichage dans l'interface.
	 */
	private String libelle = "";
	
	/**
	 * Le constructeur par d�faut.
	 * @param index l'index unique
	 * @param code le code � envoyer
	 * @param libelle le libell� � afficher
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
