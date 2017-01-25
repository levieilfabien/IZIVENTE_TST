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
 * Scénario 10 des tests automatisés pour IZIVENTE - 11/2016
 * Editique Prêt étudiant (BP)
 * @author levieilfa bardouma
 */
public class TNRSC10 extends TNRSC00 {

/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario10 = new CasEssaiIziventeBean();
		this.setAlm(true);
		this.setIdUniqueTestLab(54397);
		this.setNomCasEssai("TNRSC10-" + getTime());
		this.setDescriptif("TNRSC10 - BP - IZIVENTE_Editique Prêt Etudiant");
		this.setNomTestLab("TNRSC10 - BP - IZIVENTE_Editique Prêt Etudiant");
		//this.setNomTestPlan("TNRSC10 - BP - IZIVENTE_Editique Prêt Etudiant");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		
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