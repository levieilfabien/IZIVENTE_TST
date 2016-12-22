package main.ihm;

public enum CodeResidence {

	METROPOLE(0, "1", "r�sident m�tropole"),
	DOM(1, "4", "r�sident DOM"),
	TOM(2, "5", "r�sident TOM"),
	AUTRE_CEE(3, "2", "r�sident CEE hors m�tropole DOM/TOM"),
	OCDE_HORS_CEE(4, "3", "r�sident OCDE hors CEE"),
	FMI_HORS_OCDE(5, "6", "r�sident FMI hors OCDE"),
	FMI(6, "7", "r�sident hors FMI");

	
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
