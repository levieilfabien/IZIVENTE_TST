package test.java;

import java.io.File;

import main.bean.CasEssaiIziventeBean;
import main.bean.ModificateurBouchon;
import main.constantes.Cibles;
import main.constantes.Constantes;
import main.constantes.Environements;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import outils.SeleniumOutils;
import beans.ObjectifBean;
import exceptions.SeleniumException;

/**
 * Scénario 3 des tests pour IZIVENTE.
 * @author levieilfa
 *
 */
public class SC02Test extends SC00Test {

	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void accesIzivente() throws SeleniumException {
		
		CasEssaiIziventeBean scenario2 = new CasEssaiIziventeBean();
		scenario2.setNomCasEssai("SC02-" + getTime());
		scenario2.setDescriptif("SC02 - BP - IZIVENTE_Producteur_ Prêt étudiant différé partiel");
		scenario2.setAlm(true);
		scenario2.setIdUniqueTestLab(49504);
		scenario2.setNomTestLab("SC02 - BP - IZIVENTE_Producteur_ Prêt étudiant différé partiel");
		scenario2.setNomTestPlan("SC02 - IZIVENTE_Producteur_ Prêt étudiant différé partiel");
		scenario2.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		// Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		
		// Création et configuration du repertoire de téléchargement
		String repertoire = creerRepertoireTelechargement(scenario2, profile);
		scenario2.setRepertoireTelechargement(repertoire);
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario2.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario2.getNomCasEssai() + scenario2.getTime()));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		outil.setRepertoireRacine(repertoire);
		
		try {
		
			// Saisie du jeton
			ModificateurBouchon modificateur = new ModificateurBouchon();
			modificateur.emprunteurJeune = true;
			String idClient = saisieJeton(outil, "9384464", false, Constantes.CAS_BP, modificateur);
			// Récupération de l'élément contenant le jeton de reretoutage.
			scenario2.setIdClient(idClient);
			
			/////////////////////////////////////////////// CHOIX DU MODE DE VENTE ////////////////////////////////////////////////
			// Cette etape est optionelle, le "Face à face" ne sera pas toujours visible
			outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
			// Cette étape n'as lieu que si le dossier est déjà porteur d'un autre dossier
			outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
			
			/////////////////////////////////////////////// GESTION DE LA POPUP ////////////////////////////////////////////////
			outil.attendrePresenceTexte("ATTENTION");
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);

			/////////////////////////////////////////////// SAISIE DU DOSSIER ////////////////////////////////////////////////
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			outil.cliquer(Cibles.BOUTON_VALIDER);	
			
			outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT);
			outil.selectionner("JEUNES", Cibles.SELECTEUR_UNIVERS_CREDIT, false);
			outil.attendre(1);
			outil.selectionner("PER5", Cibles.SELECTEUR_OFFRE_CREDIT, false);
			outil.attendre(1);
			outil.viderEtSaisir("1500", Cibles.SAISIE_COUT_PROJET);
			outil.viderEtSaisir("1500", Cibles.SAISIE_MONTANT_DEMANDE);
			outil.viderEtSaisir("30", Cibles.SAISIE_DUREE_DEMANDE);
			outil.viderEtSaisir("12", Cibles.SAISIE_DUREE_DIFFERE_PARTIEL_TOTAL);
			
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			
			/////////////////////////////////////////////// SAISIE DES PARTICIPANTS ////////////////////////////////////////////////
			// Pas d'ajout du participant conjoint
			outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
			outil.attendreEtCliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON);
			
			// Validation de la liste des participants
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
			
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, scenario2, ex, scenario2.getNomCasEssai() + scenario2.getDateCreation().getTime());
			throw ex;
		}
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario2, scenario2.getNomCasEssai() + scenario2.getDateCreation().getTime());
	}

}
