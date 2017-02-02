package main.bean;

import beans.CasEssaiBean;

/**
 * Classe d'extension des cas d'essai classique pour IZIVENTE
 * @author levieilfa
 *
 */
public class CasEssaiIziventeBean extends CasEssaiBean {

	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Le numéro FFI du dossier saisie si il y a lieu.
	 */
	private String numeroFFI = "";
	
	/**
	 * L'identifiant du client emprunteur si il y a lieu.
	 */
	private String idClient = "";
	
	private int flag = 0;
	
	/**
	 * Numéro IUN tiré de la synthèse.
	 */
	private String numeroIUN = null;
	
	private String numeroBIC = null;
	
	private String numeroIBAN = null;
	
	private String agence = null;
	
	private String etablissement = null;
	
	private String numeroDossierUnited = "N/A";
	
	private int distributeur;

	public int getDistributeur() {
		return distributeur;
	}

	public void setDistributeur(int distributeur) {
		this.distributeur = distributeur;
	}

	public String getNumeroDossierUnited() {
		return numeroDossierUnited;
	}

	public void setNumeroDossierUnited(String numeroDossierUnited) {
		this.numeroDossierUnited = numeroDossierUnited;
	}

	public String getAgence() {
		return agence;
	}

	public void setAgence(String agence) {
		this.agence = agence;
	}

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getNumeroBIC() {
		return numeroBIC;
	}

	public void setNumeroBIC(String numeroBIC) {
		this.numeroBIC = numeroBIC;
	}

	public String getNumeroIBAN() {
		return numeroIBAN;
	}

	public void setNumeroIBAN(String numeroIBAN) {
		this.numeroIBAN = numeroIBAN;
	}

	public String getNumeroIUN() {
		return numeroIUN;
	}

	public void setNumeroIUN(String numeroIUN) {
		this.numeroIUN = numeroIUN;
	}

	public CasEssaiIziventeBean() {
		super();
	}

	public String getNumeroFFI() {
		return numeroFFI;
	}

	public void setNumeroFFI(String numeroFFI) {
		this.numeroFFI = numeroFFI;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}

