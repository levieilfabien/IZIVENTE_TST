package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Sc�nario 10 des tests automatis�s pour IZIVENTE - 11/2016
 * Editique Pr�t �tudiant (BP)
 * @author levieilfa bardouma
 */
public class TNRSC10 extends TNRSC00 {

/**
 * Id de s�rialisation par d�faut.
 */
private static final long serialVersionUID = 1L;

	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du sc�nario
		//CasEssaiIziventeBean scenario10 = new CasEssaiIziventeBean();
		this.setAlm(Constantes.ACTIVATION_ALM);
		this.setIdUniqueTestLab(54397);
		this.setIdUniqueTestPlan(-1);
		this.setNomCasEssai("TNRSC10-" + getTime());
		this.setDescriptif("TNRSC10 - BP - IZIVENTE_Editique Pr�t Etudiant");
		this.setNomTestLab("TNRSC10 - BP - IZIVENTE_Editique Pr�t Etudiant");
		//this.setNomTestPlan("TNRSC10 - BP - IZIVENTE_Editique Pr�t Etudiant");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		//TODO modifier pour la fusion
		this.etablissement = "068";
		this.agence = "00001";
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.modificateur.emprunteurJeune = true;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.typeUnivers = "JEUNES";
		this.typeOffre = "BPI ETUDIANT IMMEDIAT";
		this.typeObjet = "AUTRE ETUDE";
		this.coutProjet = "3000";
		this.montantCredit = "3000";
		this.mensualite = "83";
		
		miseAEdit();
	}
}