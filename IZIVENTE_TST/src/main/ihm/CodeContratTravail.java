package main.ihm;

public enum CodeContratTravail {

	TITULAIRE(0,"1", "Titulaire (de la fonction public et assimilée)"),
	NON_TITULAIRE(1, "2", "Non titulaire (de la fonction public et assimilée)"),
	CDI(2, "3", "Salarié en CDI"),
	CDD_HORS_INTERIM(3, "4", "CDD (hors contrat d'intérim)"),
	CDD_INTERIM(4, "5", "CDD (sous contrat d'intérim)"), 
	PERIODE_ESSAI(5, "6", "Période d'essai"),
	SALARIE_CDD(6, "7", "Salarié en CDD"), 
	NON_CONCERNE(7, "N", "Non concerné"), 
	AUTRE(8, "Z", "Autres");

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
	private CodeContratTravail(int index, String code, String libelle) {
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
