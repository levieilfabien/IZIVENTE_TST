package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * ScÚnario 3 des tests automatisÚs pour IZIVENTE - 11/2016
 * Editique CREODIS (BP)
 * @author levieilfa bardouma
 */
public class TNRSC03 extends TNRSC00 {

	/**
	 * Id de sÚrialisation.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du scÚnario
		//CasEssaiIziventeBean scenario3 = new CasEssaiIziventeBean();
		this.setAlm(Constantes.ACTIVATION_ALM);
		this.setIdConfluence("71303877");
		this.setIdUniqueTestLab(54300);
		this.setIdUniqueTestPlan(-1);
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
