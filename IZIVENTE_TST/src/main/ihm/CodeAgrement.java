package main.ihm;

public enum CodeAgrement {
	
	RIEN(0, "", "Rien"),
	CASDEN(0, "021", "appartenance CASDEN"),
	ACEF(1, "008", "appartenance à l'ACEF"),
	LMDE(2, "019", "appartenance à La Mutuelle Des Etudiants (LMDE)"),
	OSEO(3, "01U", "OSEO Etudiant (prêt étudiant avec garantie OSEO)"),
	PARAPUBLIC(4, "08A", "Parapublic");

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
