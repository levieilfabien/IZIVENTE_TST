package main.ihm;

public enum CodeRegimeMatrimonial {

	CL_CE(0, "0", "Communauté légale CE"),
	CR_CE(1, "1", "Communauté réduite aux acquets CE"),
	CU_CE(2, "2", "Communauté universelle CE"),
	SEPERATION_CE(3, "3", "Séparation de biens CE"),
	CM_CE(4, "4", "Communauté des meubles et acquets CE"),
	RP_CE(5, "5", "Régime de participations aux acquets CE"),
	INC_CE(6, "X", "Indéterminé CE"),
	AUTRE_CE(7, "Z", "Autres régimes CE"),
	NC_BP(8, "00", "Non concerné BP"),
	INC_BP(9, "IN", "régime inconnu BP"),
	CL_BP(10, "01", "Communauté légale avant le 01/02/66 BP"),                      
	CL_SC_BP(11, "10", "Comm légale sans contrat après le 01.02.66 BP"),
	CR_BP(12, "02", "Communauté réduite aux acquêts BP"),                          
	CU_BP(13, "03", "Communauté universelle BP"),                                   
	SEPARATION_BP(14, "04", "Séparation de biens BP"),                                      
	RD_BP(15, "05", "Régime dotal avant le 01/02/66 BP"),                           
	CM_BP(16, "06", "Communauté de meubles et acquêts (après le 01/02/66) BP"),     
	RP_BP(17, "07", "Régime de participations aux acquêts BP"),                     
	PACS_BP(18, "09", "Pacs BP"),
	RM_BP(19, "98", "Régime matrimonial étranger BP"),
	AUTRE_BP(20, "08", "Autres régimes BP");

	
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
	private CodeRegimeMatrimonial(int index, String code, String libelle) {
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
