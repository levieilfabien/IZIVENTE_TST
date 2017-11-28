package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Scénario 1 des tests automatisés pour IZIVENTE - 11/2016
 * Editique FACELIA (BP)
 * @author levieilfa bardouma
 */
public class TNRSC01 extends TNRSC00 {

	/**
	 * Id de sérialisation.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario1 = new CasEssaiIziventeBean();
		this.setAlm(true /*Constantes.ACTIVATION_ALM*/);
		this.setIdConfluence("71303554");
		this.setIdUniqueTestLab(54199);
		this.setIdUniqueTestPlan(-1);
		this.setNomCasEssai("TNRSC01-" + getTime());
		this.setDescriptif("TNRSC01 - BP - IZIVENTE_Editique FACELIA CR Debit Credit");
		this.setNomTestLab("TNRSC01 - BP - IZIVENTE_Editique FACELIA CR Debit Credit");
		//scenario1.setNomTestPlan("TNRSC01 - BP - IZIVENTE_Editique FACELIA CR Debit Credit");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		this.setNomTestPlan(null);
		
		this.distributeur = Constantes.CAS_BP;
		//this.etablissement = "056";
		//this.agence = "00009";
		this.typeDossier = TypeProduit.FACELIA;
		this.situationDeVente = "AUTR";
		this.modificateur.sansConjoint = true;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.montantCredit = "7500";
		this.mensualite = "225,00";
		
		miseAEdit();
		//miseEnForce();
	}
}
