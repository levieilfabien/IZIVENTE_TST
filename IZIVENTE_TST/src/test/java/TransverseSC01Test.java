package test.java;

import main.constantes.Constantes;

import org.junit.Test;

import exceptions.SeleniumException;

public class TransverseSC01Test extends TNRSC00 {
	
	/**
	 * id de sérialisation.
	 */
	private static final long serialVersionUID = -3737267445392145659L;

	@Test
	public void transverseSC01Test() throws SeleniumException {
		
		this.setAlm(false);
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = Constantes.CREDIT_AMORT;
		this.edition = true;
		this.miseEnGestion = false;
		this.aucunCoEmp = false;
		this.conjointCoEmp = false;
		this.tiersCoEmp = true;
		this.assuranceEmp = false;
		this.assuranceTiers = false;
		this.montantCredit = "8000";
		this.coutProjet = "8000";
		this.mensualite = "300";
		this.numPersPhysTiers = "942500502";
		
		//simulation();
		miseAEdit();
		//miseEnForce();
		//murissement();
		
	}
}
