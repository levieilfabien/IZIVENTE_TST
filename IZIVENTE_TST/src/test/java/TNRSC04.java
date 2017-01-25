package test.java;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import beans.ObjectifBean;
import exceptions.SeleniumException;
import main.bean.CasEssaiIziventeBean;
import main.bean.ModificateurBouchon;
import main.constantes.Cibles;
import main.constantes.Constantes;
import main.constantes.TypeProduit;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;
import outils.SeleniumOutils;

/**
 * Sc�nario 4 des tests automatis�s pour IZIVENTE - 11/2016
 * Editique Travaux �chelonn� (CE)
 * @author levieilfa bardouma
 */
public class TNRSC04 extends TNRSC00 {

	/**
	 * Id de s�rialisation par d�faut.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du sc�nario
		//CasEssaiIziventeBean scenario3 = new CasEssaiIziventeBean();
		this.setAlm(true);
		this.setAlm(true);
		this.setIdUniqueTestLab(54204);
		this.setNomCasEssai("TNRSC04-" + getTime());
		this.setDescriptif("TNRSC04 - CE - IZIVENTE_Editique Travaux Echelonn�");
		this.setNomTestLab("TNRSC04 - CE - IZIVENTE_Editique Travaux Echelonn�");
		//this.setNomTestPlan("TNRSC04 - IZIVENTE_Editique Travaux Echelonn�");
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
		
		miseAEdit();
	}
}