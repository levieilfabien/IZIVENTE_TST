package main.ihm;

public enum CodeRegimeMatrimonial {

	CL_CE(0, "0", "Communaut� l�gale CE"),
	CR_CE(1, "1", "Communaut� r�duite aux acquets CE"),
	CU_CE(2, "2", "Communaut� universelle CE"),
	SEPERATION_CE(3, "3", "S�paration de biens CE"),
	CM_CE(4, "4", "Communaut� des meubles et acquets CE"),
	RP_CE(5, "5", "R�gime de participations aux acquets CE"),
	INC_CE(6, "X", "Ind�termin� CE"),
	AUTRE_CE(7, "Z", "Autres r�gimes CE"),
	NC_BP(8, "00", "Non concern� BP"),
	INC_BP(9, "IN", "r�gime inconnu BP"),
	CL_BP(10, "01", "Communaut� l�gale avant le 01/02/66 BP"),                      
	CL_SC_BP(11, "10", "Comm l�gale sans contrat apr�s le 01.02.66 BP"),
	CR_BP(12, "02", "Communaut� r�duite aux acqu�ts BP"),                          
	CU_BP(13, "03", "Communaut� universelle BP"),                                   
	SEPARATION_BP(14, "04", "S�paration de biens BP"),                                      
	RD_BP(15, "05", "R�gime dotal avant le 01/02/66 BP"),                           
	CM_BP(16, "06", "Communaut� de meubles et acqu�ts (apr�s le 01/02/66) BP"),     
	RP_BP(17, "07", "R�gime de participations aux acqu�ts BP"),                     
	PACS_BP(18, "09", "Pacs BP"),
	RM_BP(19, "98", "R�gime matrimonial �tranger BP"),
	AUTRE_BP(20, "08", "Autres r�gimes BP");

	
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
