package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Scénario 11 des tests automatisés pour IZIVENTE - 11/2016
 * Editique Prêt étudiant différé partiel BPI (BP)
 * @author levieilfa bardouma
 */
public class TNRSC11 extends TNRSC00 {

/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void lancementTNR() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario11 = new CasEssaiIziventeBean();
		this.setAlm(Constantes.ACTIVATION_ALM);
		this.setIdUniqueTestLab(54398);
		this.setIdUniqueTestPlan(-1);
		this.setNomCasEssai("TNRSC11-" + getTime());
		this.setDescriptif("TNRSC11 - BP - IZIVENTE_Editique Prêt étudiant différé partiel BPI");
		this.setNomTestLab("TNRSC11 - BP - IZIVENTE_Editique Prêt étudiant différé partiel BPI");
		//this.setNomTestPlan("TNRSC11 - BP - IZIVENTE_Editique Prêt étudiant différé partiel BPI");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.modificateur.emprunteurJeune = true;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.typeUnivers = "JEUNES";
		this.typeOffre = "BPI ETUDIANT ECH DIF PART";
		this.typeObjet = "ECOLE INGENIEURS";
		this.coutProjet = "4000";
		this.montantCredit = "4000";
		this.mensualite = "100";
		this.dureeDiffere = "12";
		
		miseAEdit();
	}
}
