package main.ihm;

public enum CodeSituationFamilale {

	CELIB_CE(0, "0", "Célibataire CE"),
	MARIE_CE(1, "1", "Marié CE"),
	CONCUBIN_CE(2, "2", "Concubin CE"),
	SEPARE_CE(3, "3", "Séparé ou en instance de séparation (suite à jugement) CE"),
	VEUF_CE(4, "4", "Veuf CE"),
	DIVORCE_CE(5, "5", "Divorcé CE"),
	PACS_CE(6, "6", "Pacsé CE"),
	AUTRE_CE(7, "X", "Indéterminé CE"),
	AUTRE_BP(8, "0", "Non renseigné ou Inconnu BP"),
	CELIB_BP(9, "1", "Célibataire BP"),
	MARIE_BP(10, "2", "Marié BP"),
	CONCUBIN_BP(11, "3", "Concubin BP"),
	SEPARE_BP(12, "4", "Séparé de bien ou de corps BP"),
	VEUF_BP(13, "5", "Veuf BP"),
	DIVORCE_BP(14, "6", "Divorcé BP"),
	INSTANCE_DIVORCE_BP(15, "7", "Instance de divorce BP"),
	PACS_BP(16, "8", "Pacsé BP");

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
	private CodeSituationFamilale(int index, String code, String libelle) {
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
