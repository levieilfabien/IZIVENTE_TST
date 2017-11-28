package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Sc�nario 6 des tests automatis�s pour IZIVENTE - 11/2016
 * Editique Tr�sorerie imm�diat CASDEN (BP)
 * @author levieilfa bardouma
 */
public class TNRSC06 extends TNRSC00 {

	/**
	 * Id de s�rialisation.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du sc�nario
		//CasEssaiIziventeBean scenario6 = new CasEssaiIziventeBean();
		this.setAlm(Constantes.ACTIVATION_ALM);
		this.setIdUniqueTestLab(54393);
		this.setIdUniqueTestPlan(-1);
		this.setIdConfluence("71305128");
		this.setNomCasEssai("TNRSC06-" + getTime());
		this.setDescriptif("TNRSC06 - BP - IZIVENTE_Editique Tr�sorerie imm�diat CASDEN");
		this.setNomTestLab("TNRSC06 - BP - IZIVENTE_Editique Tr�sorerie imm�diat CASDEN");
		//this.setNomTestPlan("TNRSC06 - BP - IZIVENTE_Editique Tr�sorerie imm�diat CASDEN");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.modificateur.emprunteurSenior = true;
		this.modificateur.coEmprunteurJeune = true;
		this.modificateur.emprunteurCasden = true;
		this.aucunCoEmp = false;
		this.conjointCoEmp = true;
		this.assuranceEmp = true;
		this.assuranceConjointCoEmp = true;
		this.typeUnivers = "TRESORERIE";
		this.typeOffre = "CREDIT TRESORERIE CASDEN";
		this.typeObjet = "TRESORERIE";
		this.coutProjet = "3000";
		this.montantCredit = "3000";
		this.mensualite = "83";
		
		miseAEdit();
	}
}