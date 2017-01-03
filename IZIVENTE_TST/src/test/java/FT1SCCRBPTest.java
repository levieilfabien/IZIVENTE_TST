package test.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import beans.ObjectifBean;
import exceptions.SeleniumException;
import main.bean.CasEssaiIziventeBean;
import main.constantes.Cibles;
import main.constantes.Constantes;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;
import outils.SeleniumOutils;

/**
 * Scénario 2 des test automatisé pour la mise en gestion de dossier
 * FACELIA (BP CR)
 * @author levieilfa bardouma
 */
public class FT1SCCRBPTest extends SC00Test {

	/**
 * Id de sérialisation.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	// Description du scénario
	CasEssaiIziventeBean scenario1 = new CasEssaiIziventeBean();
	// Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();

		// Création et configuration du repertoire de téléchargement
		//File repertoireTelechargement = new File(".\\" + scenario2.getNomCasEssai());
		//repertoireTelechargement.mkdir();
		//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		String repertoire = creerRepertoireTelechargement(scenario1, profile);
		scenario1.setRepertoireTelechargement(repertoire);
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		driver.get(Constantes.URL_BP_FUTURE_REROUTAGE);
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario1.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario1.getNomCasEssai() + scenario1.getTime()));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		outil.setRepertoireRacine(scenario1.getRepertoireTelechargement());
	
		try {
			//CT01 - Accès Izivente et initialisation
			//CT02 - Ouverture du dossier et validation des informations client
			//CT03 - Saisie des paramètres relatifs au type de dossier et validation
			//CT04 - Choix des participants et des assurances associées et validation des participants
			//CT05 - Validation de l'instruction
			scenario1.getTests().add(CT01Initialisation(scenario1, outil));
			scenario1.getTests().add(CT02OuvertureDossier(scenario1, outil));
			scenario1.getTests().add(CT03SaisieDossier(scenario1, outil));
			scenario1.getTests().add(CT04Participants(scenario1, outil));
			scenario1.getTests().add(CT05FinalisationInstruction(scenario1, outil));
			scenario1.getTests().add(CT06MiseGestion(scenario1, outil));
			ecritureFichierDonnees("BP", scenario1.getNumeroFFI(), scenario1.getIdClient(), null, null, scenario1.getFlag());
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, scenario1, ex, scenario1.getNomCasEssai() + scenario1.getDateCreation().getTime());
			throw ex;

		}
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario1, scenario1.getNomCasEssai() + scenario1.getDateCreation().getTime());
	}

/**
 * Partie du scenario1 regroupant la saisie, l'instruction et la validation d'un dossier CR FACELIA dans IZIVENTE.
 * @param scenario1 le scenario1 englobant.
 * @param outil l'outil de manipulation du navigateur.
 * @return le cas d'essai documenté pour ajout au scénario.
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIziventeBean CT01Initialisation(CasEssaiIziventeBean scenario1, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT01
	CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
	//Information issues du scénario.	
	//Gestion des steps
	CT01.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT01.getNomCasEssai() + CT01.getTime()));
	CT01.ajouterStep("Génération du bouchon client Izivente", "GENERATION", "Création du bouchon terminée");
	CT01.ajouterStep("Lancer l'URL Izivente en fonction du réseau testé", "ACCESREROUTAGE", "Affichage de l'écran de reroutage");
	CT01.ajouterStep("Récupérer numéro de bouchon généré et injecter le jeton (copier/coller du code dans la zone de l'écran de reroutage)", "INJECTION", "Jeton collé dans la zone appropriée");
	CT01.ajouterStep("Valider le jeton et lancer le reroutage (clic sur bouton 'Reroutage')", "ACCESIZIVENTE", "Affichage d'Izivente (écran d'instruction ou pop up de mode de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCES IZIVENTE ET INITIALISATION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// Jeton OK SC01 : "9500425"
	//Steps 1,2,3,4 : Génération du bouchon - Accès à l'écran de reroutage et injection du jeton - Accès à Izivente
	String idClient = saisieJeton(outil, null, false, Constantes.CAS_BP, null, null, null);
	scenario1.setIdClient(idClient);
	CT01.validerObjectif(outil.getDriver(), "GENERATION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESREROUTAGE", true);
	CT01.validerObjectif(outil.getDriver(), "INJECTION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESIZIVENTE", true);
	CT01.validerObjectif(outil.getDriver(), CT01.getNomCasEssai() + CT01.getTime(),true);
	return CT01;
}

public CasEssaiIziventeBean CT02OuvertureDossier(CasEssaiIziventeBean scenario1, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT02
	CasEssaiIziventeBean CT02 = new CasEssaiIziventeBean();
	//Information issues du scénario.
	//Gestion des steps
	CT02.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT02.getNomCasEssai() + CT02.getTime()));
	CT02.ajouterStep("Choisir le mode de vente (Face à face ou Vente à distance)", "MODE", "Affichage de l'écran d'instruction");
	CT02.ajouterStep("Sélectionner l'option d'ouverture d'un dossier correspondant au type voulu ", "OUVERTURE", "Affichage des pop ups de confirmation");
	CT02.ajouterStep("Fermeture des pop ups onfirmant l'ouverture du dossier", "CONFIRMATION", "Affichage de l'écran de données client et de liste des dossiers");
	CT02.ajouterStep("Vérifier la cohérence des données du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'.", "SUIVANT", "Affichage de la pop un de Synthèse des informations client");
	CT02.ajouterStep("Valider les données client en cliquant sur le bouton 'Valider' dans la pop up de synthèse des information client.", "VALIDATIONDONNEESCLIENT", "Affichage de l'écran de demande de crédit");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// OUVERTURE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : Choisir le mode de vente (FàF ou VàD) - Cette etape est optionelle, le "Face à face" ne sera pas toujours visible
	outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
	CT02.validerObjectif(outil.getDriver(), "MODE", true);
	//Step 2 : Sélectionner l'option d'ouverture d'un dossier correspondant au type voulu. 
	outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FACELIA);
	// Cette étape n'as lieu que si le dossier est déjà porteur d'un autre dossier
	outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
	CT02.validerObjectif(outil.getDriver(), "OUVERTURE", true);
	//Step 3 : Fermeture des pop ups 'Information' et 'Attention' confirmant l'ouverture du dossier
	outil.attendrePresenceTexte("INFORMATION");
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendrePresenceTexte("ATTENTION");
	outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
	CT02.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
	//Step 4 : Vérifier la cohérence des données du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'
	outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
	CT02.validerObjectif(outil.getDriver(), "SUIVANT", true);
	//Step 5 : Valider les données client en cliquant sur le bouton 'Valider' dans la pop up de synthèse des information client.
	outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
	CT02.validerObjectif(outil.getDriver(), "VALIDATIONDONNEESCLIENT", true);
	CT02.validerObjectif(outil.getDriver(), CT02.getNomCasEssai() + CT02.getTime(),true);
	return CT02;
}
	
public CasEssaiIziventeBean CT03SaisieDossier(CasEssaiIziventeBean scenario1, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT03
	CasEssaiIziventeBean CT03 = new CasEssaiIziventeBean();		
	//Information issues du scénario.	
	//Gestion des steps
	CT03.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT03.getNomCasEssai() + CT03.getTime()));
	CT03.ajouterStep("Sélectionner l'offre désirée dans le menu déroulant selon le scénario", "OFFRE", "Offre sélectionnée");
	CT03.ajouterStep("Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)", "PARAMETRES", "Paramètres cohérents avec le scénario");
	CT03.ajouterStep("Cliquer sur le bouton 'Suivant' pour valider les informations du dossier", "SAISIEDOSSIER", "Affichage de l'écran de sélection des participants");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// SAISIE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : Sélectionner l'offre désirée dans le menu déroulant selon le scénario
	scenario1.setFlag(1);
	outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT_CR, true, true);
	outil.selectionner("FACELIA", Cibles.SELECTEUR_OFFRE_CREDIT_CR, false);
	outil.attendre(2);
	CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
	//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
	outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR);
	outil.selectionner("Prêt immobilier", Cibles.SELECTEUR_SITUATION_VENTE_CR);
	outil.viderEtSaisir("7500",  Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
	outil.attendreChargementElement(Cibles.SAISIE_MENSUALITE_CR); 
	outil.viderEtSaisir("750", Cibles.SAISIE_MENSUALITE_CR);
	CT03.validerObjectif(outil.getDriver(), "PARAMETRES", true);
	//Step 3 : Cliquer sur le bouton 'Suivant' pour valider les informations du dossier
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	CT03.validerObjectif(outil.getDriver(), "SAISIEDOSSIER", true);
	CT03.validerObjectif(outil.getDriver(), CT03.getNomCasEssai() + CT03.getTime(),true);
	return CT03;
}
	
public CasEssaiIziventeBean CT04Participants(CasEssaiIziventeBean scenario1, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT04
	CasEssaiIziventeBean CT04 = new CasEssaiIziventeBean();		
	//Information issues du scénario.	
	//Gestion des steps
	CT04.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT04.getNomCasEssai() + CT04.getTime()));
	CT04.ajouterStep("Choisir les participants en fonction de la fiche de prêt et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le numéro de personne physique, cliquer sur rechercher, vérifier la cohérence des données du tiers  et  valider les données du tiers. ", "PARTICIPANTS", "Affichage de l'écran 'Synthèse des participants'");
	CT04.ajouterStep("Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses.", "ASSURANCEROLE", "Les informations sont conformes et le bouton de validation des participants cliquable");
    CT04.ajouterStep("Valider la liste des participants (clic sur bouton correspondant)", "VALIDATIONPARTICIPANTS", "Affichage de l'écran de 'Proposition' avec la grille alternative commerciale");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// PARTICIPANTS
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : Choisir les participants en fonction de la fiche de prêt et Valider. Aucun co-emprunteur dans ce scénario
	outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
	outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
	CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
	//Step 2 : Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses. Valider la listes des participants et confirmer l'assurance
	//Assurance de l'emprunteur
	outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
	outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
	outil.attendreChargementElement(Cibles.RADIO_AVEC_ASS_CR);
	outil.cliquer(Cibles.RADIO_AVEC_ASS_CR);
	CT04.validerObjectif(outil.getDriver(), "ASSURANCEROLE", true);
	//Step 3 : Valider la liste des participants
	outil.attendreChargementElement(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	CT04.validerObjectif(outil.getDriver(),  "VALIDATIONPARTICIPANTS", true);
	CT04.validerObjectif(outil.getDriver(), CT04.getNomCasEssai() + CT04.getTime(),true);
	return CT04;
}
	
public CasEssaiIziventeBean CT05FinalisationInstruction(CasEssaiIziventeBean scenario1, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT05
	CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
	//Information issues du scénario.
	//Gestion des steps
	CT05.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT05.getNomCasEssai() + CT05.getTime()));
	CT05.ajouterStep("Valider de l'offre contrat de crédit (clic sur le bouton 'Valider en contrat de crédit')", "VALIDATION", "Affichage de la pop up de finalisation de l'instruction");
	CT05.ajouterStep("Imprimer la synthèse en pdf (clic sur bouton 'Imprimer')", "IMPRESSIONSYNTHESE", "Le fichier pdf est correctement télécharge"); //Step facultatif
	CT05.ajouterStep("Finalisation de l'instruction (clic sur le bouton 'Oui' dans la popup de finalisation de l'instruction", "VALIDATIONINSTRUCTION", "Affichage de l'écran de synthèse de l'offre de crédit");
	CT05.ajouterStep("Remplir le questionnaire pour la demande de financement à 8 jours et la réception de sollicitations commerciales partenaires (choix oui/non via boutons radio).", "OPTIONS", "Choix effectués conformément au scénario");
    CT05.ajouterStep("Impression de la liasse de document (clic sur le bouton 'Suivant')", "IMPRESSION", "Affichage de la pop up 'Préparation contrat'");
    CT05.ajouterStep("Attendre la fin de la préparation du contrat puis cliquer sur suivant pour envoi de la liasse", "PREPARATION", "Deconnexion d'Izivente");
	
    /////////////////////////////////////////////////////////////////////////////////////////////////////
	// FINALISATION DE L'INSTRUCTION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : Valider de l'offre contrat de crédit
	outil.attendreChargementElement(Cibles.BOUTON_VALIDER_OPC_CR);
	outil.cliquer(Cibles.BOUTON_VALIDER_OPC_CR);
	outil.attendre(1);//Ne pas enlever
	CT05.validerObjectif(outil.getDriver(), "VALIDATION", true);
	//Step 2 : Finalisation de l'instruction
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	CT05.validerObjectif(outil.getDriver(), "VALIDATIONINSTRUCTION", true);
	//Step 3 : Remplir le questionnaire pour la demande de financement à 8 jours et la réception de sollicitations commerciales partenaires
	outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	CT05.validerObjectif(outil.getDriver(), "OPTIONS", true);
	//Step optionnel : récupération du numéro FFI pour la mise en gestion
	String numeroFFI = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMERO_FFI_CR);
	scenario1.setNumeroFFI(numeroFFI);
	//Step 4 : Imprimer la liasse de document
	outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_LIASSE);
	outil.cliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
	outil.attendrePresenceTexte("Préparation contrat");
	CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
	//Step 5 : Préparation du contrat et envoi à l'octroi	
	outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
	outil.attendreChargementElement(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE);
	outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT_FIN);
	scenario1.setFlag(2);
	CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
	CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
	return CT05;
	}
public CasEssaiIziventeBean CT06MiseGestion(CasEssaiIziventeBean scenario1, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT06
	CasEssaiIziventeBean CT06 = new CasEssaiIziventeBean();
	//Information issues du scénario.
	//Gestion des steps
	CT06.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT06.getNomCasEssai() + CT06.getTime()));
	CT06.ajouterStep("Relancement d'Izivente et retour sur le dossier", "RETOUR", "Affichage de la page d'accueil d'Izivente avec injection du jeton");
	CT06.ajouterStep("Ouverture et du dossier et recherche du numéro FFI", "RECHERCHE", "Affichage des données dossier et client");
	CT06.ajouterStep("Passage à l'octroi et premières vérification", "OCTROI", "Dossier accepté pour l'octroi ");
	CT06.ajouterStep("Finalisation de l'octroi et dernières confirmations avant mise en gestion", "FINALISATION", "Affichage des données dossiers et client avec état FORC");
	CT06.ajouterStep("Vérification des données dossier et client", "MISENFORCE", "Dossier à l'état FORC");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////// RETOUR SUR L'IHM ////////////////////////////////////////////////
	//Step 1 : Rechargement de l'URL d'Izivente et réinjection du jeton
	outil.chargerUrl(Constantes.URL_BP_FUTURE_REROUTAGE); 
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
	String chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + scenario1.getIdClient() + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>038</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>00022</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>00022</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>9</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>DISTRIBUTEUR</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx></ListCtx><ProtocoleTech><header><version>2</version><messageId></messageId><timestamp>22/05/2012</timestamp><language>FR</language><country>FR</country><otherElements><name>name1</name><value>value1</value></otherElements><otherElements><name>name2</name><value>value2</value></otherElements></header><context><company>BP</company><application>EQX</application><channel>Intranet</channel><bank>038</bank><agency></agency><goal></goal><userId></userId></context><actors><company>NFI</company><application>v45</application><versionAppli>1.0</versionAppli><channel>INTRANET</channel><bank>All</bank><agency>All</agency><goal></goal><userId>12345678</userId></actors></ProtocoleTech></fluxreroutage>";
	outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
	outil.viderEtSaisir(chaineCible, Cibles.SAISIE_JETON);
	outil.cliquer(Cibles.VALIDER_SAISIE_JETON);
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
	CT06.validerObjectif(outil.getDriver(), "RETOUR", true);
	//Step 2 : Ouverture du dossier et recherche du numéro FFI
	outil.cliquer(Cibles.BOUTON_MENU_REPRISE_DOSSIER);
	outil.attendrePresenceTexte("Liste des dossiers");
	for (WebElement coche : outil.obtenirElements(Cibles.COCHES_LISTE_DOSSIER)) {
		coche.click();
		outil.attendre(1);
		if (outil.testerPresenceTexte(scenario1.getNumeroFFI(), true)) {
			break;
		}
	}
	CT06.validerObjectif(outil.getDriver(), "RECHERCHE", true);
	// Step 3 : Passage à l'octroi
	outil.cliquer(Cibles.BOUTON_MISE_EN_FORCE_CR);
	outil.attendrePresenceTexte("Demande de confirmation");
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendrePresenceTexte("Complétude et justificatifs");
	outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
	outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
	outil.attendreChargementElement(Cibles.LIBELLE_ACCEPTATION);
	outil.cliquer(Cibles.LIBELLE_ACCEPTATION);
	outil.attendreEtCliquer(Cibles.BOUTON_FINALISATION_OCTROI_CR);
	CT06.validerObjectif(outil.getDriver(), "OCTROI", true);
	//Step 4 : Finalisation de l'octroi
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI, true, true);
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI);
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI, true, true);
	outil.cliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
	CT06.validerObjectif(outil.getDriver(), "FINALISATION", true);
	//Step 5 : Vérification du passage à l'état FORC et renseignement du fichier texte
	outil.attendrePresenceTexte("Liste des dossiers");
	CT06.validerObjectif(outil.getDriver(), "MISEENFORCE", true);
	scenario1.setFlag(3);
	return CT06;
}

}