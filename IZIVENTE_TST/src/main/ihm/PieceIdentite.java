package main.ihm;

public enum PieceIdentite {

	PERMIS_CE(0, "28", "Permis de conduire CE"),
	CARTE_ID_CE(1, "29", "Carte d'identité CE"),  
	CARTE_SEJOUR_CE(2, "30", "Carte de séjour CE"),
	PASSEPORT_CE(3, "31", "Passeport CE"),     
	CARTE_INVALIDITE_CE(4,"32", "Carte d'invalidité CE"),
	AUTRE_CE(5, "33", "Autre CE"),
	CARTE_UE_BP(6, "CIU", "C.I. UNION EUROPENNE BP"),
	CARTE_ID_BP(7, "CNI", "CARTE D'IDENTITE BP"),
	CERTE_SEJOUR_BP(8, "CS", "CARTE DE SEJOUR BP"),
	LIVRET_CIRCU_BP(9, "LC", "LIVRET DE CIRCULATION BP"),
	PASSERPORT_BP(10, "PAS", "PASSEPORT"),
	PASSEPORT_UE_BP(11, "PAU", "PASSEPORT UNION EUROPEENNE"),
	PERMIS_BP(12, "PC", "PERMIS DE CONDUIRE"),
	AUTRE_BP(13, "XXX", "AUCUNE PIECE PRESENTEE");

	
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
	private PieceIdentite(int index, String code, String libelle) {
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
