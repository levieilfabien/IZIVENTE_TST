package main.ihm;

public enum CodeSituationFamilale {

	CELIB_CE(0, "0", "C�libataire CE"),
	MARIE_CE(1, "1", "Mari� CE"),
	CONCUBIN_CE(2, "2", "Concubin CE"),
	SEPARE_CE(3, "3", "S�par� ou en instance de s�paration (suite � jugement) CE"),
	VEUF_CE(4, "4", "Veuf CE"),
	DIVORCE_CE(5, "5", "Divorc� CE"),
	PACS_CE(6, "6", "Pacs� CE"),
	AUTRE_CE(7, "X", "Ind�termin� CE"),
	AUTRE_BP(8, "0", "Non renseign� ou Inconnu BP"),
	CELIB_BP(9, "1", "C�libataire BP"),
	MARIE_BP(10, "2", "Mari� BP"),
	CONCUBIN_BP(11, "3", "Concubin BP"),
	SEPARE_BP(12, "4", "S�par� de bien ou de corps BP"),
	VEUF_BP(13, "5", "Veuf BP"),
	DIVORCE_BP(14, "6", "Divorc� BP"),
	INSTANCE_DIVORCE_BP(15, "7", "Instance de divorce BP"),
	PACS_BP(16, "8", "Pacs� BP");

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
