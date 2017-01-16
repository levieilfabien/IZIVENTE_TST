package main.constantes;

/**
 * Enumération listant l'ensemble des produit utilisés dans le cadre des tests automatisé utilisant IZIVENTE.
 * @author levieilfa
 *
 */
public enum TypeProduit {

	CREDIT_AMORT(1, "PP", "Crédit Amortissable", false),
	FACELIA(2, "FA", "Crédit Renouvelable FACELIA", true),
	CREODIS(3, "CR", "Crédit Renouvelable CREODIS", true),
	IZICARTE(4, "IZ", "Crédit Renouvelable IZICARTE", true),
	TEOZ(5, "TE", "Crédit Renouvelable TEOZ", true);
	
	/**
	 * L'index unique pour la valeur dans l'énumération.
	 */
	private int index = -1;
	/**
	 * Le valeur sous forme de texte de l'index pour éviter les transcodification
	 */
	private String code = "";
	/**
	 * Le libellé unique du produit permettant son identification visuelle.
	 */
	private String libelle = "";
	/**
	 * S'agit il d'un PP ou d'un CR?
	 */
	private Boolean creditRenouvelable = false;
	
	/**
	 * Le constructeur par défaut.
	 * @param index l'index unique
	 * @param code le code à envoyer
	 * @param libelle le libellé à afficher
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
