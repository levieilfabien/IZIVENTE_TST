package main.ihm;

public enum CodeResidence {

	METROPOLE(0, "1", "résident métropole"),
	DOM(1, "4", "résident DOM"),
	TOM(2, "5", "résident TOM"),
	AUTRE_CEE(3, "2", "résident CEE hors métropole DOM/TOM"),
	OCDE_HORS_CEE(4, "3", "résident OCDE hors CEE"),
	FMI_HORS_OCDE(5, "6", "résident FMI hors OCDE"),
	FMI(6, "7", "résident hors FMI");

	
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
	private CodeResidence(int index, String code, String libelle) {
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
