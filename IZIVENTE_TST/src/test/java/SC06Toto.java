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
 * Sc�nario 6 des tests pour IZIVENTE.
 * @author levieilfa bardouma
 *
 */
public class SC06Toto extends SC00Test {

	/**
	 * Id de s�rialisation par d�faut.
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
		
		// Cr�ation et configuration du repertoire de t�l�chargement
//		File repertoireTelechargement = new File(".\\" + scenario6.getNomCasEssai());
//		repertoireTelechargement.mkdir();
//		profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		String repertoire = creerRepertoireTelechargement(scenario6, profile);
		scenario6.setRepertoireTelechargement(repertoire);
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario6.ajouterObjectif(new ObjectifBean("Test arriv� � terme", scenario6.getNomCasEssai() + scenario6.getTime()));
		scenario6.ajouterObjectif(new ObjectifBean("G�n�ration du bouchon", "BOUCHON", "BOUCHON" + scenario6.getTime(), "G�n�ration du bouchon", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Ajout du conjoint", "CONJOINT", "CONJOINT" + scenario6.getTime(), "Ajout du conjoint lors de la souscription", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Ajout d'un tiers", "TIERS", "TIERS" + scenario6.getTime(), "Ajout du tiers lors de la souscription", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Validation de l'OPC", "VALIDEROPC", "VALIDEROPC" + scenario6.getTime(), "Validation de l'OPC", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Impression de la synth�se", "IMPRIMERSYNTHESE", "IMPRIMERSYNTHESE" + scenario6.getTime(), "Impression de la synth�se", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Impression de la liasse compl�te", "IMPRIMERLIASSE", "IMPRIMERLIASSE" + scenario6.getTime(), "Impression de la liasse", ""));
		scenario6.ajouterObjectif(new ObjectifBean("Mise en force", "MISEENFORCE", "MISEENFORCE" + scenario6.getTime(), "Mise en force", ""));
		

	}		
	
	
	
	
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
				
			// R�cup�ration de l'�l�ment contenant le jeton de reretoutage.
			//
			ModificateurBouchon modificateur = new ModificateurBouchon();
			modificateur.emprunteurJeune = true;
			modificateur.coEmprunteurJeune = true;
			String idClient = saisieJeton(outil, null, false, Constantes.CAS_CE, modificateur);
			scenario.setIdClient(idClient);
			CT01.validerObjectif(outil.getDriver(), "ACCES", true);
			
			outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
			
			/////////////////////////////////////////////// CHOIX DU MODE DE VENTE ////////////////////////////////////////////////
			// Cette etape est optionelle, le "Face � face" ne sera pas toujours visible
			outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
			// Cette �tape n'as lieu que si le dossier est d�j� porteur d'un autre dossier
			outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
			//Ouverture de la popup de souscription d'une Izicarte sans carte associ�e
			outil.attendrePresenceTexte("Information");
			//Fermeture de la popup
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			/////////////////////////////////////////////// GESTION DE LA POPUP ////////////////////////////////////////////////
			// On v�rifie la pr�sence de la popup
			outil.attendrePresenceTexte("Attention");
			// On ferme la popup
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
			/////////////////////////////////////////////// SAISIE DU DOSSIER ////////////////////////////////////////////////
			//Validation de la Synth�se des informations clients
			outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
			outil.attendrePresenceTexte("Synth�se");
			outil.cliquer(Cibles.BOUTON_VALIDER);
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			//Erreur apr�s cette �tape (08/11)//
			
			
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
			
			// On g�re l'assurance du co emprunteur conjoint
			outil.attendrePresenceTexte("Co-Emprunteur conjoint");
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON);
			
			// On g�re l'assurance de l'emprunteur
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendre(1);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_OUI);
			
			/////////////////////////////////////////////// RECHERCHE D'UN TIERS ////////////////////////////////////////////////
			// On cherche � ajouter un nouveau participant tiers
			outil.attendreChargementElement(Cibles.ELEMENT_ENTETE_TABLEAU_ASSURANCE_CE);
			outil.cliquer(Cibles.BOUTON_AJOUT_AUTRE_PARTICIPANT);
			outil.attendreChargementElement(Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, true, true);
			// on fait une recherche par num�ro de personne physique
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
			// On v�rifie la pr�sence de la popup
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
			outil.attendrePresenceTexte("Choix du r�le de clienttiers036 DARIUS");
			outil.selectionner("Co-emprunteur", Cibles.SELECTEUR_ROLE_PARTICIPANT, true);
			outil.attendre(2);
			
			// Role du second tiers
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT3);
			outil.attendrePresenceTexte("Choix du r�le de clienttiers036 LOLA");
			outil.selectionner("Co-emprunteur", Cibles.SELECTEUR_ROLE_PARTICIPANT, true);
			outil.attendre(2);
			
			outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
			
		return CT01;
	}

}
