package test.java;

import org.openqa.selenium.firefox.FirefoxProfile;

import beans.ObjectifBean;
import exceptions.SeleniumException;
import main.bean.CasEssaiIziventeBean;
import main.constantes.Catalogue;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;
import outils.SeleniumOutils;

public class RSTSC01 extends SC00Test {
	/**
	 * Id de s�rialisation.
	 */
	private static final long serialVersionUID = 1L;
	
	public void accesIzivente() throws SeleniumException {
		// Description du sc�nario
		CasEssaiIziventeBean scenario1 = new CasEssaiIziventeBean();
		scenario1.setAlm(true);
		scenario1.setIdUniqueTestLab(49506);
		scenario1.setNomCasEssai("RSTSC01-" + getTime());
		scenario1.setDescriptif(Catalogue.RSTSC01_TITRE);
		scenario1.setNomTestLab(Catalogue.RSTSC01_TITRE);

		scenario1.setCheminTestLab(Catalogue.CHEMIN_TEST_LAB_RST);
		
		// Configuration du driver
		FirefoxProfile profile = configurerProfilNatixis();
		
		// Cr�ation et configuration du repertoire de t�l�chargement, ce repertoire est commun pour tous les CT du sc�nario
		scenario1.setRepertoireTelechargement(creerRepertoireTelechargement(scenario1, profile));
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario1.ajouterObjectif(new ObjectifBean("Test arriv� � terme", scenario1.getNomCasEssai() + scenario1.getTime()));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		outil.setRepertoireRacine(scenario1.getRepertoireTelechargement());
		
		
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario1, scenario1.getNomCasEssai() + scenario1.getDateCreation().getTime());
	}
	
}
