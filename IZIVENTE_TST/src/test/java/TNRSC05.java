package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * Scénario 5 des tests automatisés pour IZIVENTE - 11/2016
 * Editique Prêt travaux echelonné différé partiel (CE)
 * @author levieilfa bardouma
 */
public class TNRSC05 extends TNRSC00 {
	
	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;
	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario5 = new CasEssaiIziventeBean();
		this.setAlm(true);
		this.setIdUniqueTestLab(54392);
		this.setNomCasEssai("TNRSC05-" + getTime());
		this.setDescriptif("TNRSC05 - CE - IZIVENTE_Prêt travaux echelonné différé partiel");
		this.setNomTestLab("TNRSC05 - CE - IZIVENTE_Prêt travaux echelonné différé partiel");
		//this.setNomTestPlan("TNRSC05 - IZIVENTE_Prêt travaux echelonné différé partiel");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.typeUnivers = "TRAVAUX";
		this.typeOffre = "TRAVAUX DIFFERE 12MOIS PARTIEL";
		this.typeObjet = "TRAVAUX RESIDENCE PRINCIPALE";
		this.coutProjet = "22000";
		this.montantCredit = "22000";
		this.mensualite = "333";
		
		miseAEdit();
	}
}