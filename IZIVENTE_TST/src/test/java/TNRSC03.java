package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * Scénario 3 des tests automatisés pour IZIVENTE - 11/2016
 * Editique CREODIS (BP)
 * @author levieilfa bardouma
 */
public class TNRSC03 extends TNRSC00 {

	/**
	 * Id de sérialisation.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario3 = new CasEssaiIziventeBean();
		this.setAlm(true);
		this.setIdUniqueTestLab(54300);
		this.setNomCasEssai("TNRSC03-" + getTime());
		this.setDescriptif("TNRSC03 - BP - IZIVENTE_Editique CREODIS Full Credit");
		this.setNomTestLab("TNRSC03 - BP - IZIVENTE_Editique CREODIS Full Credit");
		//this.setNomTestPlan("TNRSC03 - BP - IZIVENTE_Editique CREODIS Full Credit");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.CREODIS;
		this.modificateur.sansConjoint = true;
		this.aucunCoEmp = true;
		this.assuranceEmp = false;
		this.montantCredit = "2000";
		this.mensualite = "80,00";
		this.situationDeVente = "AUTR";
	
	miseAEdit();
	//miseEnForce();
	}
}
