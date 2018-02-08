package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Scénario 4 des tests automatisés pour IZIVENTE - 11/2016
 * Editique Travaux échelonné (CE)
 * @author levieilfa bardouma
 */
public class TNRSC04 extends TNRSC00 {

	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario3 = new CasEssaiIziventeBean();
		this.setAlm(Constantes.ACTIVATION_ALM);
		this.setIdConfluence("71304319");
		this.setIdUniqueTestLab(54204);
		this.setIdUniqueTestPlan(-1);
		this.setNomCasEssai("TNRSC04-" + getTime());
		this.setDescriptif("TNRSC04 - CE - IZIVENTE_Editique Travaux Echelonné");
		this.setNomTestLab("TNRSC04 - CE - IZIVENTE_Editique Travaux Echelonné");
		//this.setNomTestPlan("TNRSC04 - IZIVENTE_Editique Travaux Echelonné");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.modificateur.emprunteurJeune = true;
		this.modificateur.coEmprunteurJeune = true;
		this.conjointCoEmp = true;
		this.assuranceEmp = true;
		this.assuranceConjointCoEmp = false;
		this.typeUnivers = "TRAVAUX";
		this.typeOffre = "TRAVAUX ECHELONNE DIFF TOTAL";
		this.typeObjet = "TRAVAUX RESIDENCE PRINCIPALE";
		this.coutProjet = "22000";
		this.montantCredit = "22000";
		this.mensualite = "367";
		this.dureeDiffere = "12";
		
		miseAEdit();
		//miseEnForce();
		//murissement(null);
	}
}