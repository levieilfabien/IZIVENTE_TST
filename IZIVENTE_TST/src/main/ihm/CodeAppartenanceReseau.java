package main.ihm;

public enum CodeAppartenanceReseau {

	HORS_CE(0, "0", "Hors reseau CE"),
	ETAB_CE(1, "1", "Person. d'un etab. d'un réseau distributeur (BP, CE, ...). CE"),
	CONJOINT_ETAB_CE(2, "2", "Conjoint du personnel de l'etablissement CE"),
	ENFANT_ETAB_CE(3, "3", "Enfant du personnel de l'etablissement CE"),
	RETRAITE_CE(4, "4", "Retraité de l'etablissement CE"),
	ADMIN_CE(5, "5", "Administrateur de l'etablissement CE"),
	BPCE_NON_ETAB_CE(6, "6", "Person. BPCE non employé d'un etab. d'un réseau. CE"),
	CONJOINT_BCPCE_NON_ETAB_CE(7, "A", "Conjoint person. BPCE non employé d’un étab. réseau CE"),
	AUTRE_CE(8, "Z", "Autre CE"),
	HORS_BP(9, "0", "HORS RESEAU BP"),
	AGENT_ETAB_BP(10, "1", "AGENT DE L'ETABLISSEMENT BP"),
	ENFANT_ETAB_BP(11, "2", "ENFANT DU PERSONNEL DE L'ETABLISSEMENT BP"),
	MEMBRE_COS_BP(12, "3", "MEMBRE DU COS DE L'ETABLISSEMENT - ADMINISTRATEUR BP"),
	CONJOINT_ETAB_BP(13, "4", "CONJOINT DU PERSONNEL DE L'ETABLISSEMENT BP"),
	PERSONNEL_GROUPE_BP(14, "5", "PERSONNEL  GROUPE NON Employé BQ BP"),
	TRESOR_PUBLIC_BP(15, "7", "TRESOR PUBLIC BP"),
	RETRAITER_BP(16, "8", "RETRAITE DE L'ETABLISSEMENT BP"),
	SOC_LOCALE_EPARGNE_BP(17, "9", "SOCIETE LOCALE d'EPARGNE BP"),
	CONJOINT_GROUPE_AUTRE_BP(18, "A", "CONJOINT DU PERSONNEL GROUPE NON EMPLOYE BQUE BP");

	
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
	private CodeAppartenanceReseau(int index, String code, String libelle) {
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
