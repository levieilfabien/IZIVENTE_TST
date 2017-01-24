package main.bean;

import java.sql.Date;

import beans.CasEssaiBean;

/**
 * Classe d'extension des cas d'essai classique pour IZIVENTE
 * @author levieilfa
 *
 */
public class CasEssaiIziventeBean extends CasEssaiBean {

	/**
	 * Id de s�rialisation par d�faut.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Le num�ro FFI du dossier saisie si il y a lieu.
	 */
	private String numeroFFI = "";
	
	/**
	 * L'identifiant du client emprunteur si il y a lieu.
	 */
	private String idClient = "";
	
	private int flag = 0;
	
	/**
	 * Num�ro IUN tir� de la synth�se.
	 */
	private String numeroIUN = null;
	
	private String numeroBIC = null;
	
	private String numeroIBAN = null;
	
	

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

