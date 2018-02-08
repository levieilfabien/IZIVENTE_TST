package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.bean.CasEssaiIziventeBean;
import main.constantes.Catalogue;
import main.constantes.Constantes;
import outils.SeleniumOutils;

public class RSTSC01 extends TNRSC00 {
	/**
	 * Id de s�rialisation.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void lancementRSTSC01() throws SeleniumException {
		// Description du sc�nario
		CasEssaiIziventeBean scenario1 = new CasEssaiIziventeBean();
		scenario1.setAlm(false);
		scenario1.setIdUniqueTestLab(-1);
		scenario1.setNomCasEssai("RSTSC01-" + getTime());
		scenario1.setDescriptif(Catalogue.RSTSC01_TITRE);
		scenario1.setNomTestLab(Catalogue.RSTSC01_TITRE);
		scenario1.setCheminTestLab(Catalogue.CHEMIN_TEST_LAB_RST);
		scenario1.setDistributeur(Constantes.CAS_CE);
		scenario1.setEtablissement("17515");
		scenario1.setAgence("1751500030000007");
		
		/*// Configuration du driver
		FirefoxProfile profile = configurerProfilNatixis();
		// Cr�ation et configuration du repertoire de t�l�chargement, ce repertoire est commun pour tous les CT du sc�nario
		scenario1.setRepertoireTelechargement(creerRepertoireTelechargement(scenario1, profile));
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario1.ajouterObjectif(new ObjectifBean("Test arriv� � terme", scenario1.getNomCasEssai() + scenario1.getTime()));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		outil.setRepertoireRacine(scenario1.getRepertoireTelechargement());*/
		
		SeleniumOutils outil = obtenirDriver(scenario1);
		scenario1.getTests().add(CT01Initialisation(scenario1, outil));
		
		//A PARTIR D'ICI il faut se rendre dans restructuration en utilisant des ACTIONS
		
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario1, scenario1.getNomCasEssai() + scenario1.getDateCreation().getTime());
	}
	
}
