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
 * Sc�nario 1 des tests pour IZIVENTE - Editique FACELIA
 * @author levieilfa bardouma
 *
 */

public class SC01Max extends SC00Test {

	/**
	 * Id de s�rialisation.
	 */
	private static final long serialVersionUID = 1L;

	@Test
	public void accesIzivente() throws SeleniumException {
		// Description du sc�nario
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
		
		// Cr�ation et configuration du repertoire de t�l�chargement, ce repertoire est commun pour tous les CT du sc�nario
		scenario1.setRepertoireTelechargement(creerRepertoireTelechargement(scenario1, profile));
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario1.ajouterObjectif(new ObjectifBean("Test arriv� � terme", scenario1.getNomCasEssai() + scenario1.getTime()));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		outil.setRepertoireRacine(scenario1.getRepertoireTelechargement());
		
		try {
			//CT01BIS - Initialisation et instruction de la vente jusque la validation
			//CT19 - Pr�paration contrat Edition de la liasse
			//CT07 - Validation �lectronique du contrat
			//CT11 - Mise en force (signature �lectronique)
			//CT11BIS - Contr�le Mise en force

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
	 * @return le cas d'essai document� pour ajout au sc�nario.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT01Bis(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
		//Param�trage du CT01
		CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
		CT01.setAlm(true);
		CT01.setNomCasEssai("CT01-" + getTime());
		CT01.setDescriptif("CT01BIS - Initialisation et instruction de la vente jusque la validation");
		CT01.setNomTestPlan("CT01BIS - Initialisation et instruction de la vente jusque la validation");
		
		//Information issues du sc�nario.
		CT01.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
		CT01.setNomTestLab(scenario.getNomTestLab());
		CT01.setCheminTestLab(scenario.getCheminTestLab());
		CT01.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
		
		//Gestion des step
		CT01.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT01.getNomCasEssai() + CT01.getTime()));
		CT01.ajouterStep("Lancer l'URL Izivente en fonction du r�seau test� et injecter le jeton du sc�nario", "ACCES", "Ecran d'INSTRUCTION");
		CT01.ajouterStep("Dans l'onglet 'Dossier Cr�dit Facelia', s�lectionner l'ouverture d'un dossier ", "OUVERTURE", "Ecran donn�es client ou liste dossier");
		CT01.ajouterStep("V�rifier que les donn�es du client, du conjoint si existant et du budget sont coh�rentes avec le bouchon. Valider les donn�es du client", "VERIFICATION", "Donn�es clients valid�es et Ecran de la 'demande du cr�dit'");
		CT01.ajouterStep("Choisir les participants en fonction de la fiche de pr�t et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le num�ro de personne physique, cliquer sur rechercher, v�rifier la coh�rence des donn�es du tiers  et  valider les donn�es du tiers. ", "PARTICIPANTS", "Ecran 'Synth�se des participants'");
		CT01.ajouterStep("Choisir un pr�t en suivant les hypoth�ses de la fiche de pr�t. Pour le regroupement de cr�dit suivre le mode op�ratoire. Valider", "SAISIEDOSSIER", "Ecran des 'Participants'");
		CT01.ajouterStep("Pour chaque participant, choisir le r�le et l'assurance en fonction des hypoth�ses. Valider la listes des participants et confirmer l'assurance.", "ASSURANCEROLE", "Ecran de 'Proposition' avec la grille alternative commerciale");
		CT01.ajouterStep("Valider et confirmer le contrat de cr�dit pour passer � l'Ecran de 'finalisation de l'instruction'. Confirmer en cochant 'v�rifi�' pour chaque type de document Puis valider", "VALIDATIONINSTRUCTION", "Ecran 'Pr�paration contrat' qui affiche la synth�se de l'offre de cr�dit");
		
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
		// R�cup�ration de l'�l�ment contenant le jeton de reretoutage.
		scenario.setIdClient(idClient);
		// Acc�s � IZIVENTE
		CT01.validerObjectif(outil.getDriver(), "ACCES", true);
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES A L'OUVERTURE DU DOSSIER FACELIA
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cette etape est optionelle, le "Face � face" ne sera pas toujours visible
		outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
		outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FACELIA);
		//V�rification Objectif ?//
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// VERIFICATIONS
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		//Fermeture POPUP d'Information
		outil.attendrePresenceTexte("INFORMATION");
		outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		//V�rification Objectif ?//
		//Fermeture POPUP Attention
		outil.attendrePresenceTexte("ATTENTION");
		outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
		//V�rification Objectif ? - Arriv�e sur fen�tre "Ouverture FACELIA" avec infos client//
		outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
		//Ouverture pop up avec synth�se info client
		outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
		return CT01;
	}
}
