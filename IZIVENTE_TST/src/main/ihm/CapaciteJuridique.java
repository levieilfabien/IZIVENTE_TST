package main.ihm;

public enum CapaciteJuridique {
	

		MINEUR_EMANCIPE(0,"01","01 mineur émancipé"),
		MINEUR_ADM_LEGALE(1,"02","02 mineur ss adm légale pure et simple"),
		MINEUR_CTRL_JUDICIAIRE(2,"03","03 mineur ss adm ss contrôle judiciaire"),
		MINEUR_TUTELLE(3,"04","04 mineur ss tutelle"),
		MINEUR_PUPILLE_ETAT(4,"05","05 pupille d'état"),
		MINEUR_PUPILLE_NATION(5,"06","06 pupille de la nation"),
		MAJEUR_CAPABLE(6,"07","07 majeur capable"),
		MAJEUR_MANDAT_ORDINAIRE(7,"08","08 majeur ss svg de justice mandat ordinaire"),
		MAJEUR_MANDAT_JUSTICE(8,"09","09 majeur ss svg justice mandat donné en cour mise ss svg justice"),
		MAJEUR_MANDAT_SPECIAL(9,"10","10 majeur ss svg de justice mandat spécial"),
		MAJEUR_CURATELLE_SIMPLE(10,"11","11 majeur ss curatelle simple"),
		MAJEUR_CURATELLE_RENFORCEE(11,"12","12 majeur ss curatelle renforcée"),
		MAJEUR_GERANCE_TUTELLE(12,"13","13 majeur ss gérance de tutelle"),
		MAJEUR_CTRL_JUDICIAIRE(13,"14","14 majeur ss contrôle judiciaire"),
		MAJEUR_TUTELLE(14,"15","15 majeur ss tutelle"),
		MAJEUR_SVG_JUSTICE(15,"16","16 majeur ss svg de justice"),
		MAJEUR_CURATELLE(16,"17","17 majeur ss curatelle"),
		INCONNU(17,"99","99 inconnu");

	
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
	private CapaciteJuridique(int index, String code, String libelle) {
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
