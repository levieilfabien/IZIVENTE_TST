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
 * Scénario 6 des tests pour IZIVENTE.
 * @author levieilfa bardouma
 *
 */
public class SC06Toto extends SC00Test {

	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void accesIzivente() throws SeleniumException {
		

		CasEssaiIziventeBean scenario6 = new CasEssaiIziventeBean();
		scenario6.setNomCasEssai("SC06-" + getTime());
		scenario6.setDescriptif("SC06 - IZIVENTE_Editique IZICARTE");
		scenario6.setAlm(true);
		scenario6.setIdUniqueTestLab(49375);
		scenario6.setNomTestLab("SC06 - IZIVENTE_Editique IZICARTE");
		scenario6.setNomTestPlan("SC06 - IZIVENTE_Editique IZICARTE");
		scenario6.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		// Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		
		// Création et configuration du repertoire de téléchargement
//		File repertoireTelechargement = new File(".\\" + scenario6.getNomCasEssai());
//		repertoireTelechargement.mkdir();
//		profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		String repertoire = creerRepertoireTelechargement(scenario6, profile);
		scenario6.setRepertoireTelechargement(repertoire);
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario6.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario6.getNomCasEssai() + scenario6.getTime()));
		scenario6.ajouterObjectif(new ObjectifBean("Génération du bouchon", "BOUCHON", "BOUCHON" + scenario6.getTime(), "Génération du bouchon", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Ajout du conjoint", "CONJOINT", "CONJOINT" + scenario6.getTime(), "Ajout du conjoint lors de la souscription", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Ajout d'un tiers", "TIERS", "TIERS" + scenario6.getTime(), "Ajout du tiers lors de la souscription", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Validation de l'OPC", "VALIDEROPC", "VALIDEROPC" + scenario6.getTime(), "Validation de l'OPC", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Impression de la synthèse", "IMPRIMERSYNTHESE", "IMPRIMERSYNTHESE" + scenario6.getTime(), "Impression de la synthèse", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Impression de la liasse complète", "IMPRIMERLIASSE", "IMPRIMERLIASSE" + scenario6.getTime(), "Impression de la liasse", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Mise en force", "MISEENFORCE", "MISEENFORCE" + scenario6.getTime(), "Mise en force", ""));
		

	}		
	
	
	
	
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
				
			// Récupération de l'élément contenant le jeton de reretoutage.
			//
			ModificateurBouchon modificateur = new ModificateurBouchon();
			modificateur.emprunteurJeune = true;
			modificateur.coEmprunteurJeune = true;
			String idClient = saisieJeton(outil, null, false, Constantes.CAS_CE, modificateur);
			scenario.setIdClient(idClient);
			CT01.validerObjectif(outil.getDriver(), "ACCES", true);
			
			outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
			
			/////////////////////////////////////////////// CHOIX DU MODE DE VENTE ////////////////////////////////////////////////
			// Cette etape est optionelle, le "Face à face" ne sera pas toujours visible
			outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
			// Cette étape n'as lieu que si le dossier est déjà porteur d'un autre dossier
			outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
			//Ouverture de la popup de souscription d'une Izicarte sans carte associée
			outil.attendrePresenceTexte("Information");
			//Fermeture de la popup
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			/////////////////////////////////////////////// GESTION DE LA POPUP ////////////////////////////////////////////////
			// On vérifie la présence de la popup
			outil.attendrePresenceTexte("Attention");
			// On ferme la popup
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
			/////////////////////////////////////////////// SAISIE DU DOSSIER ////////////////////////////////////////////////
			//Validation de la Synthèse des informations clients
			outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
			outil.attendrePresenceTexte("Synthèse");
			outil.cliquer(Cibles.BOUTON_VALIDER);
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			//Erreur après cette étape (08/11)//
			
			
			// On remplis le formulaire.
			outil.selectionner("TRAVAUX", Cibles.SELECTEUR_UNIVERS_CREDIT);
			// A ce moment on recharge le formulaire, il faut faire patienter le test.
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
			outil.selectionner("TRAVAUX ECHELONNE", Cibles.SELECTEUR_OFFRE_CREDIT, false);
			// A ce moment on recharge le formulaire, il faut faire patienter le test.
			outil.attendre(2);
			outil.selectionner("TRAVAUX", Cibles.SELECTEUR_OBJET_FINANCE);
			outil.viderEtSaisir("5000", Cibles.SAISIE_COUT_PROJET);
			outil.selectionner("Aucun", Cibles.SELECTEUR_REPORT_PREMIERE_MENS, false);
			outil.viderEtSaisir("5000", Cibles.SAISIE_MONTANT_DEMANDE);
			outil.viderEtSaisir("48", Cibles.SAISIE_DUREE_DEMANDE);
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			
			/////////////////////////////////////////////// SAISIE DES PARTICIPANTS ////////////////////////////////////////////////
			// Ajout du participant conjoint
			outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
			outil.cliquer(Cibles.BOUTON_AJOUT_CONJOINT);
			
			// On gère l'assurance du co emprunteur conjoint
			outil.attendrePresenceTexte("Co-Emprunteur conjoint");
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON);
			
			// On gère l'assurance de l'emprunteur
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendre(1);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_OUI);
			
			/////////////////////////////////////////////// RECHERCHE D'UN TIERS ////////////////////////////////////////////////
			// On cherche à ajouter un nouveau participant tiers
			outil.attendreChargementElement(Cibles.ELEMENT_ENTETE_TABLEAU_ASSURANCE_CE);
			outil.cliquer(Cibles.BOUTON_AJOUT_AUTRE_PARTICIPANT);
			outil.attendreChargementElement(Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, true, true);
			// on fait une recherche par numéro de personne physique
			outil.selectionner("RCHNUMERO", Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, false);
			outil.attendreChargementElement(Cibles.SAISIE_NUMERO_PERS_PHY);
			//211000526 313002036
			outil.viderEtSaisir("313002036", Cibles.SAISIE_NUMERO_PERS_PHY);
			outil.cliquer(Cibles.BOUTON_RECHERCHER);
			
			/////////////////////////////////////////////// AJOUT TIERS RECHERCHE ////////////////////////////////////////////////
			// TODO la personne n'existe plus :(
			outil.attendreChargementElement(Cibles.BOUTON_AJOUT_TIERS);
			outil.cliquer(Cibles.BOUTON_AJOUT_TIERS);
			
			/////////////////////////////////////////////// GESTION DE LA POPUP ////////////////////////////////////////////////
			// On vérifie la présence de la popup
			outil.attendrePresenceTexte("Attention");
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
			
			/////////////////////////////////////////////// VALIDATION DU TIERS 1 ////////////////////////////////////////////////
			outil.cliquer(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_1);
			outil.attendrePresenceTexte(Constantes.TITRE_BLOC_SYNTHESE_TIERS);		
			outil.attendre(2);
			WebElement element = outil.obtenirElement(Cibles.BOUTON_VALIDER_SYNTHESE_TIERS);
			element.click();
			/////////////////////////////////////////////// GESTION DES TIERS ////////////////////////////////////////////////
			// le premier tiers
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT2);
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_OUI);
			
			// Assurance du premier tiers
			outil.attendreChargementElement(Cibles.BOUTON_OUI_DES);
			outil.cliquer(Cibles.BOUTON_OUI_DES);
			outil.attendreChargementElement(Cibles.RADIO_ASS_OUI);
					
			// Role du premier tiers
			outil.cliquer(Cibles.RADIO_ASS_OUI);
			outil.attendrePresenceTexte("Choix du rôle de clienttiers036 DARIUS");
			outil.selectionner("Co-emprunteur", Cibles.SELECTEUR_ROLE_PARTICIPANT, true);
			outil.attendre(2);
			
			// Role du second tiers
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT3);
			outil.attendrePresenceTexte("Choix du rôle de clienttiers036 LOLA");
			outil.selectionner("Co-emprunteur", Cibles.SELECTEUR_ROLE_PARTICIPANT, true);
			outil.attendre(2);
			
			outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
			
		return CT01;
	}

}
