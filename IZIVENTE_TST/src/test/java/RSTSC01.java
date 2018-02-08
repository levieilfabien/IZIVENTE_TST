package test.java;

import org.junit.Test;

import beans.CibleBean;
import constantes.Actions;
import constantes.Clefs;
import exceptions.SeleniumException;
import main.bean.CasEssaiIziventeBean;
import main.constantes.Catalogue;
import main.constantes.Cibles;
import main.constantes.Constantes;
import outils.SeleniumOutils;

public class RSTSC01 extends TNRSC00 {
	/**
	 * Id de sérialisation.
	 */
	private static final long serialVersionUID = 1L;
	@Test
	public void lancementRSTSC01() throws SeleniumException {
		// Description du scénario
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
		// Création et configuration du repertoire de téléchargement, ce repertoire est commun pour tous les CT du scénario
		scenario1.setRepertoireTelechargement(creerRepertoireTelechargement(scenario1, profile));
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario1.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario1.getNomCasEssai() + scenario1.getTime()));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		outil.setRepertoireRacine(scenario1.getRepertoireTelechargement());*/
		
		SeleniumOutils outil = obtenirDriver(scenario1);
		scenario1.getTests().add(CT01Initialisation(scenario1, outil));
		
		//A PARTIR D'ICI il faut se rendre dans restructuration en utilisant des ACTIONS
		outil.action(Actions.CLIQUER, Cibles.LIEN_SIMULATION_RST);
		
	/*	if (outil.testerPresenceTexte("Attention", true)) {
			System.out.println("La popup est présente");
			// On tente de cliquer sur le bouton ferme
			outil.attendreEtCliquer(Cibles.LIEN_MAJ_MYWAY);
		}*/
//		outil.attendreChargementElement(Cibles.LIEN_MAJ_MYWAY);
		outil.action(Actions.CLIQUER, Cibles.LIEN_MAJ_MYWAY);
	//Remplir le formulaire
	
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_REVENUESREG_EM, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_autresRevenusEM, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_REVENUESREG_CEM, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_autresRevenusCEM, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_RESID_PRINCIPALE, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_HORS_RESID_PRINCIPALE, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_CREDITS_RENOUVELABLE, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_CREDITS_AUTO, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_CREDITS_AUTRES, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_AUTRES_CHARGES, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_PENSION_ALIMENTAIRES, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_CRD_CREDITS_IMMO, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_CRD_CREDITS_CONSO_HORS_RENOUVELABLE, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_MT_UTILISE_CR, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_SOLDE_DEBITEUR, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_MONTANT_RETARDS_IMPOT, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_MONTANT_RETARDS_LOYERS, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_MONTANT_AUTRES_DETTES, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_NB_IMPAYES_IMMO, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_NB_IMPAYES_CONSO, "0");
		outil.action(Actions.VIDER_ET_SAISIR, Cibles.SAISIE_TRESORERIE, "0");
		
		
        //outil.action(Actions.CLIQUER, Cibles.VALIDER_FORMULAIRE_DEMO);
        
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario1, scenario1.getNomCasEssai() + scenario1.getDateCreation().getTime());
	}
	
}
