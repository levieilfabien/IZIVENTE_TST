package test.java;

import java.io.File;

import main.bean.CasEssaiIziventeBean;
import main.bean.ModificateurBouchon;
import main.constantes.Cibles;
import main.constantes.Constantes;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import outils.SeleniumOutils;
import beans.ObjectifBean;
import exceptions.SeleniumException;

/**
 * Scénario 1 des tests pour IZIVENTE - Editique FACELIA
 * @author levieilfa bardouma
 *
 */

public class SC01Max extends SC00Test {

	/**
	 * Id de sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	@Test
	public void accesIzivente() throws SeleniumException {
		// Description du scénario
		CasEssaiIziventeBean scenario1 = new CasEssaiIziventeBean();
		scenario1.setAlm(true);
		scenario1.setIdUniqueTestLab(49506);
		scenario1.setNomCasEssai("SC01-" + getTime());
		scenario1.setDescriptif("SC01 - BP - IZIVENTE_Editique FACELIA CR Debit Credit");
		scenario1.setNomTestLab("SC01 - BP - IZIVENTE_Editique FACELIA CR Debit Credit");
		//scenario1.setNomTestPlan("SC01 - ????");
		scenario1.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		// Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		
		// Création et configuration du repertoire de téléchargement, ce repertoire est commun pour tous les CT du scénario
		scenario1.setRepertoireTelechargement(creerRepertoireTelechargement(scenario1, profile));
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario1.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario1.getNomCasEssai() + scenario1.getTime()));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		outil.setRepertoireRacine(scenario1.getRepertoireTelechargement());
		
		try {
			//CT01BIS - Initialisation et instruction de la vente jusque la validation
			//CT19 - Préparation contrat Edition de la liasse
			//CT07 - Validation électronique du contrat
			//CT11 - Mise en force (signature électronique)
			//CT11BIS - Contrôle Mise en force

			scenario1.getTests().add(CT01Bis(scenario1, outil));
			
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, scenario1, ex, scenario1.getNomCasEssai() + scenario1.getDateCreation().getTime());
			throw ex;
		}
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario1, scenario1.getNomCasEssai() + scenario1.getDateCreation().getTime());
	}
	

	
	/**
	 * Partie du scenario1 regroupant la saisie, l'instruction et la validation d'un dossier FACELIA CR dans IZIVENTE.
	 * @param scenario le scenario englobant.
	 * @param outil l'outil de manipulation du navigateur.
	 * @return le cas d'essai documenté pour ajout au scénario.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT01Bis(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
		//Paramètrage du CT01
		CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
		CT01.setAlm(true);
		CT01.setNomCasEssai("CT01-" + getTime());
		CT01.setDescriptif("CT01BIS - Initialisation et instruction de la vente jusque la validation");
		CT01.setNomTestPlan("CT01BIS - Initialisation et instruction de la vente jusque la validation");
		
		//Information issues du scénario.
		CT01.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
		CT01.setNomTestLab(scenario.getNomTestLab());
		CT01.setCheminTestLab(scenario.getCheminTestLab());
		CT01.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
		
		//Gestion des step
		CT01.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT01.getNomCasEssai() + CT01.getTime()));
		CT01.ajouterStep("Lancer l'URL Izivente en fonction du réseau testé et injecter le jeton du scénario", "ACCES", "Ecran d'INSTRUCTION");
		CT01.ajouterStep("Dans l'onglet 'Dossier Crédit Facelia', sélectionner l'ouverture d'un dossier ", "OUVERTURE", "Ecran données client ou liste dossier");
		CT01.ajouterStep("Vérifier que les données du client, du conjoint si existant et du budget sont cohérentes avec le bouchon. Valider les données du client", "VERIFICATION", "Données clients validées et Ecran de la 'demande du crédit'");
		CT01.ajouterStep("Choisir les participants en fonction de la fiche de prêt et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le numéro de personne physique, cliquer sur rechercher, vérifier la cohérence des données du tiers  et  valider les données du tiers. ", "PARTICIPANTS", "Ecran 'Synthèse des participants'");
		CT01.ajouterStep("Choisir un prêt en suivant les hypothèses de la fiche de prêt. Pour le regroupement de crédit suivre le mode opératoire. Valider", "SAISIEDOSSIER", "Ecran des 'Participants'");
		CT01.ajouterStep("Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses. Valider la listes des participants et confirmer l'assurance.", "ASSURANCEROLE", "Ecran de 'Proposition' avec la grille alternative commerciale");
		CT01.ajouterStep("Valider et confirmer le contrat de crédit pour passer à l'Ecran de 'finalisation de l'instruction'. Confirmer en cochant 'vérifié' pour chaque type de document Puis valider", "VALIDATIONINSTRUCTION", "Ecran 'Préparation contrat' qui affiche la synthèse de l'offre de crédit");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES A L'APPLICATION
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// Saisie du jeton
		// Saisie du jeton
		ModificateurBouchon modificateur = new ModificateurBouchon();
		modificateur.emprunteurSenior = true;
		modificateur.coEmprunteurJeune = true;
		//"9384466"
		String idClient = saisieJeton(outil, null, false, Constantes.CAS_BP, modificateur);
		// Récupération de l'élément contenant le jeton de reretoutage.
		scenario.setIdClient(idClient);
		// Accès à IZIVENTE
		CT01.validerObjectif(outil.getDriver(), "ACCES", true);
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES A L'OUVERTURE DU DOSSIER FACELIA
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cette etape est optionelle, le "Face à face" ne sera pas toujours visible
		outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
		outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FACELIA);
		//Vérification Objectif ?//
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// VERIFICATIONS
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		//Fermeture POPUP d'Information
		outil.attendrePresenceTexte("INFORMATION");
		outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		//Vérification Objectif ?//
		//Fermeture POPUP Attention
		outil.attendrePresenceTexte("ATTENTION");
		outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
		//Vérification Objectif ? - Arrivée sur fenêtre "Ouverture FACELIA" avec infos client//
		outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
		//Ouverture pop up avec synthèse info client
		outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
		return CT01;
	}
}
