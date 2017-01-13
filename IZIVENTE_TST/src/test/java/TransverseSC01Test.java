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
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = Constantes.CREODIS;
		this.edition = true;
		this.miseEnGestion = false;
		this.aucunCoEmp = true;
		this.conjointCoEmp = false;
		this.tiersCoEmp = false;
		this.assuranceEmp = false;
		this.assuranceTiers = false;
		this.montantCredit = "8000";
		//this.coutProjet = "8000";
		//this.mensualite = "300";
		//this.numPersPhysTiers = "9500855";
		
		miseAEdit();
		//miseEnForce();
		//murissement();
		
	}
}
