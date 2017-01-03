package test.java;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import beans.ObjectifBean;
import exceptions.SeleniumException;
import main.bean.CasEssaiIziventeBean;
import main.bean.ModificateurBouchon;
import main.constantes.Cibles;
import main.constantes.Constantes;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;
import outils.SeleniumOutils;

/**
 * Scénario 10 des tests automatisés pour IZIVENTE - 11/2016
 * Editique Prêt étudiant (BP)
 * @author levieilfa bardouma
 */
public class FT2SCPPBPTest extends SC00Test {

/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	// Description du scénario
	CasEssaiIziventeBean scenario2 = new CasEssaiIziventeBean();	
	// Configuration du driver
	FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
	FirefoxProfile profile = configurerProfilNatixis();
	
	// Création et configuration du repertoire de téléchargement
	//File repertoireTelechargement = new File(".\\" + scenario2.getNomCasEssai());
	//repertoireTelechargement.mkdir();
	//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
	String repertoire = creerRepertoireTelechargement(scenario2, profile);
	scenario2.setRepertoireTelechargement(repertoire);	
	// Initialisation du driver
	FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
	driver.get(Constantes.URL_BP_FUTURE_REROUTAGE);
	// LISTE DES OBJECTIFS DU CAS DE TEST
	scenario2.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario2.getNomCasEssai() + scenario2.getTime()));
   
    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
    outil.setRepertoireRacine(scenario2.getRepertoireTelechargement());
    
    try {
    	//CT01 - Accès Izivente et initialisation
    	//CT02 - Ouverture du dossier et validation des informations client
    	//CT03 - Saisie des paramètres relatifs au type de dossier et validation
    	//CT04 - Choix des participants et des assurances associées et validation des participants
    	//CT05 - Validation de l'instruction
		scenario2.getTests().add(CT01Initialisation(scenario2, outil));
		scenario2.getTests().add(CT02OuvertureDossier(scenario2, outil));
		scenario2.getTests().add(CT03SaisieDossier(scenario2, outil));
		scenario2.getTests().add(CT04Participants(scenario2, outil));
		scenario2.getTests().add(CT05FinalisationInstruction(scenario2, outil));
		scenario2.getTests().add(CT06MiseGestion(scenario2, outil));
		ecritureFichierDonnees("BP", scenario2.getNumeroFFI(), scenario2.getIdClient(), null, null, scenario2.getFlag());
	} catch (SeleniumException ex) {
		// Finalisation en erreur du cas de test.
		finaliserTestEnErreur(outil, scenario2, ex, scenario2.getNomCasEssai() + scenario2.getDateCreation().getTime());
		throw ex;
	}
	// Finalisation normale du cas de test.
	finaliserTest(outil, scenario2, scenario2.getNomCasEssai() + scenario2.getDateCreation().getTime());
}

/**
 * Partie du scenario2 regroupant la saisie, l'instruction et la validation d'un dossier crédit amortissable PP pour prêt étudiant dans IZIVENTE BP.
 * @param scenario le scenario englobant.
 * @param outil l'outil de manipulation du navigateur.
 * @return le cas d'essai documenté pour ajout au scénario.
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIziventeBean CT01Initialisation(CasEssaiIziventeBean scenario2, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT01
	CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
	//Gestion des steps
	CT01.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT01.getNomCasEssai() + CT01.getTime()));
	CT01.ajouterStep("Génération du bouchon client Izivente", "GENERATION", "Création du bouchon terminée");
	CT01.ajouterStep("Lancer l'URL Izivente en fonction du réseau testé", "ACCESREROUTAGE", "Affichage de l'écran de reroutage");
	CT01.ajouterStep("Récupérer numéro de bouchon généré et injecter le jeton (copier/coller du code dans la zone de l'écran de reroutage)", "INJECTION", "Jeton collé dans la zone appropriée");
	CT01.ajouterStep("Valider le jeton et lancer le reroutage (clic sur bouton 'Reroutage')", "ACCESIZIVENTE", "Affichage d'Izivente (écran d'instruction ou pop up de mode de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCES IZIVENTE ET INITIALISATION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// Jeton OK SC07 : 9384548
	ModificateurBouchon modificateur = new ModificateurBouchon();
	modificateur.emprunteurJeune = true;
	//Steps : Génération du bouchon - Accès à l'écran de reroutage et injection du jeton - Accès à Izivente
	String idClient = saisieJeton(outil, null, false, Constantes.CAS_BP, modificateur, null, null);
	scenario2.setIdClient(idClient);
	CT01.validerObjectif(outil.getDriver(), "GENERATION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESREROUTAGE", true);
	CT01.validerObjectif(outil.getDriver(), "INJECTION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESIZIVENTE", true);
	CT01.validerObjectif(outil.getDriver(), CT01.getNomCasEssai() + CT01.getTime(),true);
	return CT01;
}
public CasEssaiIziventeBean CT02OuvertureDossier(CasEssaiIziventeBean scenario2, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT02
	CasEssaiIziventeBean CT02 = new CasEssaiIziventeBean();
	//Gestion des steps
	CT02.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT02.getNomCasEssai() + CT02.getTime()));
	CT02.ajouterStep("Choisir le mode de vente (Face à face ou Vente à distance)", "MODE", "Affichage de l'écran d'instruction");
	CT02.ajouterStep("Sélectionner l'option d'ouverture d'un dossier correspondant au type voulu.", "OUVERTURE", "Affichage des pop ups de confirmation");
	CT02.ajouterStep("Fermeture des pop ups confirmant l'ouverture du dossier", "CONFIRMATION", "Affichage de l'écran de données client et de liste des dossiers");
	CT02.ajouterStep("Vérifier la cohérence des données du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'.", "SUIVANT", "Affichage de la pop un de Synthèse des informations client");
	CT02.ajouterStep("Valider les données client en cliquant sur le bouton 'Valider' dans la pop up de synthèse des information client.", "VALIDATIONDONNEESCLIENT", "Affichage de l'écran de demande de crédit");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// OUVERTURE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : Choisir le mode de vente (FàF ou VàD) - Cette etape est optionelle, le "Face à face" ne sera pas toujours visible
	outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
	CT02.validerObjectif(outil.getDriver(), "MODE", true);
	//Step 2 : Sélectionner l'option d'ouverture d'un dossier correspondant au type voulu. 
	outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
	// Cette étape n'as lieu que si le dossier est déjà porteur d'un autre dossier
	outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
	//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
	outil.attendrePresenceTexte("ATTENTION");
	outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
	CT02.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
	//Step 3 : Vérifier la cohérence des données du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'
	outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
	outil.attendrePresenceTexte("Synthèse");
	CT02.validerObjectif(outil.getDriver(),  "SUIVANT", true);
	//Step 4 : Valider les données client en cliquant sur le bouton 'Valider' dans la pop up de synthèse des information client.
	outil.cliquer(Cibles.BOUTON_VALIDER);
	CT02.validerObjectif(outil.getDriver(), "VALIDATIONDONNEESCLIENT", true);
	CT02.validerObjectif(outil.getDriver(), CT02.getNomCasEssai() + CT02.getTime(),true);
	return CT02;
}
	
public CasEssaiIziventeBean CT03SaisieDossier(CasEssaiIziventeBean scenario2, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT03
	CasEssaiIziventeBean CT03 = new CasEssaiIziventeBean();
	//Gestion des steps
	CT03.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT03.getNomCasEssai() + CT03.getTime()));
	CT03.ajouterStep("Sélectionner l'offre désirée dans le menu déroulant selon le scénario", "OFFRE", "Offre sélectionnée");
	CT03.ajouterStep("Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)", "PARAMETRES", "Paramètres cohérents avec le scénario");
	CT03.ajouterStep("Cliquer sur le bouton 'Suivant' pour valider les informations du dossier", "SAISIEDOSSIER", "Affichage de l'écran de sélection des participants");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// SAISIE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : Sélectionner l'offre désirée dans le menu déroulant selon le scénario
	scenario2.setFlag(1);
	outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT, true, true);
	outil.selectionner("JEUNES", Cibles.SELECTEUR_UNIVERS_CREDIT, false);
	outil.attendre(2);
	outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
	outil.selectionner("BPI ETUDIANT IMMEDIAT", Cibles.SELECTEUR_OFFRE_CREDIT, false);
	outil.attendre(2);
	CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
	//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
	outil.selectionner("AUTRE ETUDE", Cibles.SELECTEUR_OBJET_FINANCE);
	outil.viderEtSaisir("3000", Cibles.SAISIE_COUT_PROJET);
	outil.selectionner("Aucun", Cibles.SELECTEUR_REPORT_PREMIERE_MENS, false);
	outil.viderEtSaisir("3000", Cibles.SAISIE_MONTANT_DEMANDE);
	outil.viderEtSaisir("36", Cibles.SAISIE_DUREE_DEMANDE);
	CT03.validerObjectif(outil.getDriver(), "PARAMETRES", true);
	//Step 3 : Cliquer sur le bouton 'Suivant' pour valider les informations du dossier
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	CT03.validerObjectif(outil.getDriver(), "SAISIEDOSSIER", true);
	CT03.validerObjectif(outil.getDriver(), CT03.getNomCasEssai() + CT03.getTime(),true);
	return CT03;
}
	
public CasEssaiIziventeBean CT04Participants(CasEssaiIziventeBean scenario2, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT04
	CasEssaiIziventeBean CT04 = new CasEssaiIziventeBean();
	//Gestion des steps
	CT04.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT04.getNomCasEssai() + CT04.getTime()));
	CT04.ajouterStep("Choisir les participants en fonction de la fiche de prêt et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le numéro de personne physique, cliquer sur rechercher, vérifier la cohérence des données du tiers  et  valider les données du tiers. ", "PARTICIPANTS", "Affichage de l'écran 'Synthèse des participants'");
	CT04.ajouterStep("Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses.", "ASSURANCEROLE", "Les informations sont conformes et le bouton de validation des participants cliquable");
    CT04.ajouterStep("Valider la liste des participants (clic sur bouton correspondant)", "VALIDATIONPARTICIPANTS", "Affichage de l'écran de 'Proposition' avec la grille alternative commerciale");
	
    /////////////////////////////////////////////////////////////////////////////////////////////////////
	// PARTICIPANTS
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : Choisir les participants en fonction de la fiche de prêt et Valider. On ajoute un tiers pour ce scénario
    //Recherche du tiers
    outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
    outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
	CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
	//Step 2 : Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses. Valider la listes des participants et confirmer l'assurance
	//Assurance de l'emprunteur
	outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
	outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	outil.attendre(1);
	CT04.validerObjectif(outil.getDriver(), "ASSURANCEROLE", true);
	//Step 3 : Valider la liste des participants
	outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	CT04.validerObjectif(outil.getDriver(),  "VALIDATIONPARTICIPANTS", true);
	CT04.validerObjectif(outil.getDriver(), CT04.getNomCasEssai() + CT04.getTime(),true);
	return CT04;
}

public CasEssaiIziventeBean CT05FinalisationInstruction(CasEssaiIziventeBean scenario2, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT05
	CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
	//Gestion des steps
	CT05.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT05.getNomCasEssai() + CT05.getTime()));
	CT05.ajouterStep("Valider de l'offre contrat de crédit (clic sur le bouton 'Valider en contrat de crédit')", "VALIDATION", "Affichage de la pop up de finalisation de l'instruction ou de la page de dossier de vente");
	CT05.ajouterStep("Finalisation de l'instruction (clic sur le bouton 'Oui' dans la popup de finalisation de l'instruction", "VALIDATIONINSTRUCTION", "Affichage de l'écran de synthèse de l'offre de crédit");
	CT05.ajouterStep("Remplir le questionnaire pour la demande de financement à 8 jours et la réception de sollicitations commerciales partenaires (choix oui/non via boutons radio).", "OPTIONS", "Choix effectués conformément au scénario");
    CT05.ajouterStep("Imprimer la liasse de document (clic sur le bouton 'Suivant')", "IMPRESSION", "Affichage de la pop up 'Préparation contrat'");
    CT05.ajouterStep("Attendre la fin de la préparation du contrat puis cliquer sur suivant pour envoi à l'octroi", "PREPARATION", "Deconnexion d'Izivente");
	//Step PP
	CT05.ajouterStep("Imprimer la synthèse de l'offtre en pdf (clic sur bouton 'Imprimer')", "IMPRESSIONSYNTHESE", "Le fichier pdf est correctement télécharge");
	CT05.ajouterStep("Sélectionner le compte de prélèvement et valider l'offre de crédit (clic sur bouton 'Confirmer contrat de crédit')", "CONFIRMATION", "Affichage de la pop up 'Finalisation de l'instruction'");
	CT05.ajouterStep("Vérifier les justificatifs et valider (clic bouton radio 'Vérifié' pour chaque justificatif dans la pop up de finalisation de l'instruction' et clic sur bouton 'Valider'", "VERIFICATION", "Retour sur la page de dossier de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// FINALISATION DE L'INSTRUCTION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : Valider de l'offre contrat de crédit
	outil.attendrePresenceTexte("Alerte(s)");
	outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
	outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
	CT05.validerObjectif(outil.getDriver(), "VALIDATION", true);
	//Step 2 : Sélectionner le compte de prélèvement et valider l'offre de crédit
	outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_OPC);
	CT05.validerObjectif(outil.getDriver(), "VALIDATIONINSTRUCTION", true);
	//Step 3 : Vérifier les justificatifs et valider
	outil.attendreChargementElement(Cibles.ELEMENT_FIN_INSTRUCTION, true, true);
	outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
	outil.attendreEtCliquer(Cibles.BOUTON_POPUP_VALIDER_JUSTIFICATIFS);
	CT05.validerObjectif(outil.getDriver(), "VERIFICATION", true);
	//Step 4 : Remplir le questionnaire pour la demande de financement à 8 jours et la réception de sollicitations commerciales partenaires
	outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	CT05.validerObjectif(outil.getDriver(), "OPTIONS", true);
	//Step 5 : Imprimer la synthèse de l'offre
	outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE);
	CT05.validerObjectif(outil.getDriver(), "IMPRESSIONSYNTHESE", true);
	//Step 6 : Imprimer la liasse de document
	//Step optional : récupération du numéro FFI pour la mise en gestion
	String numeroFFI = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMERO_FFI);
	scenario2.setNumeroFFI(numeroFFI);
	outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
	outil.attendrePresenceTexte("Préparation contrat");
	CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
	//Step 7 : Préparation du contrat et envoi à l'octroi		
	outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT);
	scenario2.setFlag(2);
	CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
	CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
	return CT05;
	}
public CasEssaiIziventeBean CT06MiseGestion(CasEssaiIziventeBean scenario2, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT06
	CasEssaiIziventeBean CT06 = new CasEssaiIziventeBean();
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
	String chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + scenario2.getIdClient() + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>038</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>00022</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>00022</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>9</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>DISTRIBUTEUR</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx></ListCtx><ProtocoleTech><header><version>2</version><messageId></messageId><timestamp>22/05/2012</timestamp><language>FR</language><country>FR</country><otherElements><name>name1</name><value>value1</value></otherElements><otherElements><name>name2</name><value>value2</value></otherElements></header><context><company>BP</company><application>EQX</application><channel>Intranet</channel><bank>038</bank><agency></agency><goal></goal><userId></userId></context><actors><company>NFI</company><application>v45</application><versionAppli>1.0</versionAppli><channel>INTRANET</channel><bank>All</bank><agency>All</agency><goal></goal><userId>12345678</userId></actors></ProtocoleTech></fluxreroutage>";
	outil.viderEtSaisir(chaineCible, Cibles.SAISIE_JETON);
	outil.cliquer(Cibles.VALIDER_SAISIE_JETON);
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
	CT06.validerObjectif(outil.getDriver(), "RETOUR", true);
	//Step 2 : Ouverture du dossier et recherche du numéro FFI
	outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
	outil.attendrePresenceTexte("Liste des dossiers");
	for (WebElement coche : outil.obtenirElements(Cibles.COCHES_LISTE_DOSSIER)) {
		coche.click();
		outil.attendre(1);
		if (outil.testerPresenceTexte(scenario2.getNumeroFFI(), true)) {
			break;
		}
	}
	CT06.validerObjectif(outil.getDriver(), "RECHERCHE", true);
	// Step 3 : Passage à l'octroi
	outil.cliquer(Cibles.BOUTON_PASSAGE_OCTROI);
	outil.attendrePresenceTexte("Demande de confirmation");
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendrePresenceTexte("Complétude et justificatifs");
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	outil.attendrePresenceTexte("CHOISIR LA DECISION");
	outil.cliquer(Cibles.LIBELLE_ACCEPTATION);
	CT06.validerObjectif(outil.getDriver(), "OCTROI", true);
	//Step 4 : Finalisation de l'octroi
	outil.attendreChargementElement(Cibles.SAISIE_DATE_DECISION, true, true);
	outil.cliquer(Cibles.BOUTON_FINALISATION_OCTROI);
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI, true, true);
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI);
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI, true, true);
	outil.cliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
	CT06.validerObjectif(outil.getDriver(), "FINALISATION", true);
	//Step 5 : Vérification du passage à l'état FORC
	outil.attendrePresenceTexte("Liste des dossiers");
	outil.attendrePresenceTexte("FORC");
	scenario2.setFlag(3);
	CT06.validerObjectif(outil.getDriver(), "MISEENFORCE", true);
return CT06;
}
}