package main.ihm;

public enum CodeSegmentationClient {

	NVO_CLIENT_BP(0, "NCLI", "Nouveaux clients (depuis moins de 6 mois) BP"),
	JEUNE_BP(1, "JEUN", "Jeunes BP"),
	JEUNE_ADULTE_BP(2, "JADU", "Jeunes adultes BP"),
	ISPD_BP(3, "ISPD", "Inactifs sans potentiel détecté BP"),
	IAPD_BP(4, "IAPD", "Inactifs avec potentiel détecté BP"),
	ASPD_BP(5, "ASPD", "Actifs sans potentiel détecté BP"),
	AAPD_BP(6, "AAPD", "Actifs avec potentiel détecté BP"),
	CPRV_BP(7, "CPRV", "Clients privés BP"),
	CNTX_BP(8, "CNTX", "Clients en contentieux BP"),
	AFID_CE(9, "AFID", "A FIDELISER CE"),
	APRI_CE(10, "APRI", "A PRIVILEGIER CE"),
	ARED_CE(11, "ARED", "A REDECOUVRIR CE"),
	AREN_CE(12, "AREN", "A RENTABILISER CE"),
	CBAC_CE(13, "CBAC", "A CONQUERIR BANCARISES ACTIFS CE"),
	CM40_CE(14, "CM40", "A CONQUERIR - 40 ANS CE"),
	CP40_CE(15, "CP40", "A CONQUERIR + 40 ANS CE"),
	JAAC_CE(16, "JAAC", "JEUNES A ACCOMPAGNER CE"),
	JABA_CE(17, "JABA", "JEUNES A BANCARISER CE"),
	PADR_CE(18, "PADR", "PAS DE RELATION CE"),
	XXAG_CE(19, "XXAG", "NON SEGMENTABLE (AGE INCONNU) CE"),
	XXPR_CE(20, "XXPR", "NON SEGMENTABLE (PAS DE PDT) CE");

	
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
	private CodeSegmentationClient(int index, String code, String libelle) {
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
