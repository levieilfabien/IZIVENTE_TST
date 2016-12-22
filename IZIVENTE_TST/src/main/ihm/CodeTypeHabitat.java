package main.ihm;

public enum CodeTypeHabitat {

	PROPRIETAIRE(0, "10", "Propriétaire"),
	LOCATAIRE_PAYANT(1, "21", "Locataire Payant"),
	LOCATAIRE_GRATUIT(2, "22", "Locataire Gratuit"),
	LOCATAIRE_HLM(3, "23", "Locataire HLM"),
	LOCATAIRE_MEUBLE(4, "24", "Locataire Meublé"),
	LOGE_EMPLOYEUR(5, "30", "Logé Employeur"),
	LOGE_FAMILLE(6, "40", "Logé par la Famille"),
	NOMADE(7, "50", "Nomade"),
	USUFRUITER(8, "60", "Usufruitier"),
	AUTRES(9, "99", "Autres");

	
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
	private CodeTypeHabitat(int index, String code, String libelle) {
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
