package main.ihm;

public class ContexteDeVente {

	private Foyer foyer;
	
	private Compte compte;
	
	private Risque risque;
	
	private Creance creance;
	
	public ContexteDeVente() {
		super();
	}

	public Foyer getFoyer() {
		return foyer;
	}

	public void setFoyer(Foyer foyer) {
		this.foyer = foyer;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Risque getRisque() {
		return risque;
	}

	public void setRisque(Risque risque) {
		this.risque = risque;
	}

	public Creance getCreance() {
		return creance;
	}

	public void setCreance(Creance creance) {
		this.creance = creance;
	}
	
}
