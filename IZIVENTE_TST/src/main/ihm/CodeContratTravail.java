package main.ihm;

public enum CodeContratTravail {

	TITULAIRE(0,"1", "Titulaire (de la fonction public et assimil�e)"),
	NON_TITULAIRE(1, "2", "Non titulaire (de la fonction public et assimil�e)"),
	CDI(2, "3", "Salari� en CDI"),
	CDD_HORS_INTERIM(3, "4", "CDD (hors contrat d'int�rim)"),
	CDD_INTERIM(4, "5", "CDD (sous contrat d'int�rim)"), 
	PERIODE_ESSAI(5, "6", "P�riode d'essai"),
	SALARIE_CDD(6, "7", "Salari� en CDD"), 
	NON_CONCERNE(7, "N", "Non concern�"), 
	AUTRE(8, "Z", "Autres");

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
