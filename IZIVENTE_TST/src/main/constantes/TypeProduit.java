package main.constantes;

/**
 * Enum�ration listant l'ensemble des produit utilis�s dans le cadre des tests automatis� utilisant IZIVENTE.
 * @author levieilfa
 *
 */
public enum TypeProduit {

	CREDIT_AMORT(1, "PP", "Cr�dit Amortissable", false),
	FACELIA(2, "FA", "Cr�dit Renouvelable FACELIA", true),
	CREODIS(3, "CR", "Cr�dit Renouvelable CREODIS", true),
	IZICARTE(4, "IZ", "Cr�dit Renouvelable IZICARTE", true),
	TEOZ(5, "TE", "Cr�dit Renouvelable TEOZ", true);
	
	/**
	 * L'index unique pour la valeur dans l'�num�ration.
	 */
	private int index = -1;
	/**
	 * Le valeur sous forme de texte de l'index pour �viter les transcodification
	 */
	private String code = "";
	/**
	 * Le libell� unique du produit permettant son identification visuelle.
	 */
	private String libelle = "";
	/**
	 * S'agit il d'un PP ou d'un CR?
	 */
	private Boolean creditRenouvelable = false;
	
	/**
	 * Le constructeur par d�faut.
	 * @param index l'index unique
	 * @param code le code � envoyer
	 * @param libelle le libell� � afficher
	 */
	private TypeProduit(int index, String code, String libelle, Boolean creditRenouvelable) {
		this.index = index;
		this.code = code;
		this.libelle = libelle;
		this.creditRenouvelable = creditRenouvelable;
	}
	
	/**
	 * Remplace le toString "classique".
	 */
	public String toString() {
		return code + " : " + libelle;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Boolean getCreditRenouvelable() {
		return creditRenouvelable;
	}

	public void setCreditRenouvelable(Boolean creditRenouvelable) {
		this.creditRenouvelable = creditRenouvelable;
	}

}
