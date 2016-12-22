package main.ihm;

public enum CodeAgrement {
	
	RIEN(0, "", "Rien"),
	CASDEN(0, "021", "appartenance CASDEN"),
	ACEF(1, "008", "appartenance � l'ACEF"),
	LMDE(2, "019", "appartenance � La Mutuelle Des Etudiants (LMDE)"),
	OSEO(3, "01U", "OSEO Etudiant (pr�t �tudiant avec garantie OSEO)"),
	PARAPUBLIC(4, "08A", "Parapublic");

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
	private CodeAgrement(int index, String code, String libelle) {
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
}
