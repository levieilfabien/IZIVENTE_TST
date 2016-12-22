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
}

