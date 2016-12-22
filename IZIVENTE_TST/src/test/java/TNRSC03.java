package test.java;

import java.io.File;

import main.bean.CasEssaiIziventeBean;
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
 * Scénario 3 des tests automatisés pour IZIVENTE - 11/2016
 * Editique CREODIS (BP)
 * @author levieilfa bardouma
 */
public class TNRSC03 extends SC00Test {

	/**
 * Id de sérialisation.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	// Description du scénario
	CasEssaiIziventeBean scenario3 = new CasEssaiIziventeBean();
	scenario3.setAlm(true);
	scenario3.setIdUniqueTestLab(54300);
	scenario3.setNomCasEssai("TNRSC03-" + getTime());
	scenario3.setDescriptif("TNRSC03 - BP - IZIVENTE_Editique CREODIS Full Credit");
	scenario3.setNomTestLab("TNRSC03 - BP - IZIVENTE_Editique CREODIS Full Credit");
	//scenario3.setNomTestPlan("TNRSC03 - BP - IZIVENTE_Editique CREODIS Full Credit");
	scenario3.setCheminTestLab("POC Selenium\\IZIVENTE");
	
	// Configuration du driver
	FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
	FirefoxProfile profile = configurerProfilNatixis();

	// Création et configuration du repertoire de téléchargement
	//File repertoireTelechargement = new File(".\\" + scenario2.getNomCasEssai());
	//repertoireTelechargement.mkdir();
	//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
	String repertoire = creerRepertoireTelechargement(scenario3, profile);
	scenario3.setRepertoireTelechargement(repertoire);
	// Initialisation du driver
	FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
	driver.get(Constantes.URL_BP_FUTURE_REROUTAGE);
	// LISTE DES OBJECTIFS DU CAS DE TEST
	scenario3.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario3.getNomCasEssai() + scenario3.getTime()));
	
	SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
	outil.setRepertoireRacine(scenario3.getRepertoireTelechargement());
	
	try {
		//CT01 - Accès Izivente et initialisation
		//CT02 - Ouverture du dossier et validation des informations client
		//CT03 - Saisie des paramètres relatifs au type de dossier et validation
		//CT04 - Choix des participants et des assurances associées et validation des participants
		//CT05 - Validation de l'instruction
		scenario3.getTests().add(CT01Initialisation(scenario3, outil));
		scenario3.getTests().add(CT02OuvertureDossier(scenario3, outil));
		scenario3.getTests().add(CT03SaisieDossier(scenario3, outil));
		scenario3.getTests().add(CT04Participants(scenario3, outil));
		scenario3.getTests().add(CT05FinalisationInstruction(scenario3, outil));
		
	} catch (SeleniumException ex) {
		// Finalisation en erreur du cas de test.
		finaliserTestEnErreur(outil, scenario3, ex, scenario3.getNomCasEssai() + scenario3.getDateCreation().getTime());
		throw ex;
	}
	// Finalisation normale du cas de test.
	finaliserTest(outil, scenario3, scenario3.getNomCasEssai() + scenario3.getDateCreation().getTime());
}



/**
 * Partie du scenario3 regroupant la saisie, l'instruction et la validation d'un dossier CR CREODIS dans IZIVENTE.
 * @param scenario le scenario englobant.
 * @param outil l'outil de manipulation du navigateur.
 * @return le cas d'essai documenté pour ajout au scénario.
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIziventeBean CT01Initialisation(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT01
	CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
	CT01.setAlm(true);
	CT01.setNomCasEssai("CT01 -" + getTime());
	CT01.setDescriptif("CT01 - Accès Izivente et Initialisation");
	CT01.setNomTestPlan("CT01 - Accès Izivente et Initialisation");
	//Information issues du scénario.
	CT01.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT01.setCheminTestLab(scenario.getCheminTestLab());
	CT01.setNomTestLab(scenario.getNomTestLab());
	CT01.setRepertoireTelechargement(scenario.getRepertoireTelechargement());		
	//Gestion des steps
	CT01.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT01.getNomCasEssai() + CT01.getTime()));
	CT01.ajouterStep("Génération du bouchon client Izivente", "GENERATION", "Création du bouchon terminée");
	CT01.ajouterStep("Lancer l'URL Izivente en fonction du réseau testé", "ACCESREROUTAGE", "Affichage de l'écran de reroutage");
	CT01.ajouterStep("Récupérer numéro de bouchon généré et injecter le jeton (copier/coller du code dans la zone de l'écran de reroutage)", "INJECTION", "Jeton collé dans la zone appropriée");
	CT01.ajouterStep("Valider le jeton et lancer le reroutage (clic sur bouton 'Reroutage')", "ACCESIZIVENTE", "Affichage d'Izivente (écran d'instruction ou pop up de mode de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCES IZIVENTE ET INITIALISATION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// Jeton OK SC01 : "9500429"
	//Steps 1,2,3,4 : Génération du bouchon - Accès à l'écran de reroutage et injection du jeton - Accès à Izivente
	String idClient = saisieJeton(outil, null, false, Constantes.CAS_BP, null, null, null);
	scenario.setIdClient(idClient);
	CT01.validerObjectif(outil.getDriver(), "GENERATION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESREROUTAGE", true);
	CT01.validerObjectif(outil.getDriver(), "INJECTION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESIZIVENTE", true);
	CT01.validerObjectif(outil.getDriver(), CT01.getNomCasEssai() + CT01.getTime(),true);
	return CT01;
}

public CasEssaiIziventeBean CT02OuvertureDossier(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT02
	CasEssaiIziventeBean CT02 = new CasEssaiIziventeBean();
	CT02.setAlm(true);
	CT02.setNomCasEssai("CT02 -" + getTime());
	CT02.setDescriptif("CT02 - Ouverture du dossier");
	CT02.setNomTestPlan("CT02 - Ouverture du dossier");	
	//Information issues du scénario.
	CT02.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT02.setCheminTestLab(scenario.getCheminTestLab());
	CT02.setNomTestLab(scenario.getNomTestLab());
	CT02.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
	//Gestion des steps
	CT02.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT02.getNomCasEssai() + CT02.getTime()));
	CT02.ajouterStep("Choisir le mode de vente (Face à face ou Vente à distance)", "MODE", "Affichage de l'écran d'instruction");
	CT02.ajouterStep("Sélectionner l'option d'ouverture d'un dossier correspondant au type voulu ", "OUVERTURE", "Affichage des pop ups de confirmation");
	CT02.ajouterStep("Fermeture des pop ups 'Information' et 'Attention' confirmant l'ouverture du dossier", "CONFIRMATION", "Affichage de l'écran de données client et de liste des dossiers");
	CT02.ajouterStep("Vérifier la cohérence des données du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'.", "SUIVANT", "Affichage de la pop un de Synthèse des informations client");
	CT02.ajouterStep("Valider les données client en cliquant sur le bouton 'Valider' dans la pop up de synthèse des information client.", "VALIDATIONDONNEESCLIENT", "Affichage de l'écran de demande de crédit");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// OUVERTURE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : Choisir le mode de vente (FàF ou VàD) - Cette etape est optionelle, le "Face à face" ne sera pas toujours visible
	outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
	CT02.validerObjectif(outil.getDriver(), "MODE", true);
	//Step 2 : Sélectionner l'option d'ouverture d'un dossier correspondant au type voulu. 
	outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FC);
	outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
	CT02.validerObjectif(outil.getDriver(), "OUVERTURE", true);
	//Step 3 : Fermeture des pop ups 'Information' et 'Attention' confirmant l'ouverture du dossier
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
	
public CasEssaiIziventeBean CT03SaisieDossier(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT03
	CasEssaiIziventeBean CT03 = new CasEssaiIziventeBean();
	CT03.setAlm(true);
	CT03.setNomCasEssai("CT03 -" + getTime());
	CT03.setDescriptif("CT03 - Saisie du dossier");
	CT03.setNomTestPlan("CT03 - Saisie du dossier");		
	//Information issues du scénario.
	CT03.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT03.setCheminTestLab(scenario.getCheminTestLab());
	CT03.setNomTestLab(scenario.getNomTestLab());
	CT03.setRepertoireTelechargement(scenario.getRepertoireTelechargement());		
	//Gestion des steps
	CT03.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT03.getNomCasEssai() + CT03.getTime()));
	CT03.ajouterStep("Sélectionner l'offre désirée dans le menu déroulant selon le scénario", "OFFRE", "Offre sélectionnée");
	CT03.ajouterStep("Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)", "PARAMETRES", "Paramètres cohérents avec le scénario");
	CT03.ajouterStep("Cliquer sur le bouton 'Suivant' pour valider les informations du dossier", "SAISIEDOSSIER", "Affichage de l'écran de sélection des participants");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// SAISIE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : Sélectionner l'offre désirée dans le menu déroulant selon le scénario
	//L'offre est unique pour un CREODIS
	CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
	//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
	outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR);
	outil.selectionner("Entrée en relation", Cibles.SELECTEUR_SITUATION_VENTE_CR);
	outil.viderEtSaisir("2000", Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
	outil.viderEtSaisir("80,00", Cibles.SAISIE_MENSUALITE_CR);
		CT03.validerObjectif(outil.getDriver(), "PARAMETRES", true);
	//Step 3 : Cliquer sur le bouton 'Suivant' pour valider les informations du dossier
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	CT03.validerObjectif(outil.getDriver(), "SAISIEDOSSIER", true);
	CT03.validerObjectif(outil.getDriver(), CT03.getNomCasEssai() + CT03.getTime(),true);
	return CT03;
}
	
public CasEssaiIziventeBean CT04Participants(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT04
	CasEssaiIziventeBean CT04 = new CasEssaiIziventeBean();
	CT04.setAlm(true);
	CT04.setNomCasEssai("CT04 -" + getTime());
	CT04.setDescriptif("CT04 - Choix des participants");
	CT04.setNomTestPlan("CT04 - Choix des participants");		
	//Information issues du scénario.
	CT04.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT04.setCheminTestLab(scenario.getCheminTestLab());
	CT04.setNomTestLab(scenario.getNomTestLab());
	CT04.setRepertoireTelechargement(scenario.getRepertoireTelechargement());		
	//Gestion des steps
	CT04.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT04.getNomCasEssai() + CT04.getTime()));
	CT04.ajouterStep("Choisir les participants en fonction de la fiche de prêt et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le numéro de personne physique, cliquer sur rechercher, vérifier la cohérence des données du tiers  et  valider les données du tiers. ", "PARTICIPANTS", "Affichage de l'écran 'Synthèse des participants'");
	CT04.ajouterStep("Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses.", "ASSURANCEROLE", "Les informations sont conformes et le bouton de validation des participants cliquable");
    CT04.ajouterStep("Valider la liste des participants (clic sur bouton correspondant)", "VALIDATIONPARTICIPANTS", "Affichage de l'écran de 'Proposition' avec la grille alternative commerciale");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// PARTICIPANTS
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : Choisir les participants en fonction de la fiche de prêt et Valider. Aucun co-emprunteur dans ce scénario
	//Il n'y a pas de co emprunteur sur un CREODIS
	CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
	//Step 2 : Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses. Valider la listes des participants et confirmer l'assurance
	outil.attendreChargementElement(Cibles.RADIO_SELECTION_SANS_ASS_CR);
	outil.attendreEtCliquer(Cibles.RADIO_SELECTION_SANS_ASS_CR);
	outil.attendre(1);
	CT04.validerObjectif(outil.getDriver(), "ASSURANCEROLE", true);
	//Step 3 : Valider la liste des participants
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	CT04.validerObjectif(outil.getDriver(),  "VALIDATIONPARTICIPANTS", true);
	CT04.validerObjectif(outil.getDriver(), CT04.getNomCasEssai() + CT04.getTime(),true);
	return CT04;
}
	
public CasEssaiIziventeBean CT05FinalisationInstruction(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT05
	CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
	CT05.setAlm(true);
	CT05.setNomCasEssai("CT05 -" + getTime());
	CT05.setDescriptif("CT05 - Finalisation de l instruction");
	CT05.setNomTestPlan("CT05 - Finalisation de l instruction");
	//Information issues du scénario.
	CT05.setIdUniqueTestLab(scenario.getIdUniqueTestLab());;
	CT05.setCheminTestLab(scenario.getCheminTestLab());
	CT05.setNomTestLab(scenario.getNomTestLab());
	CT05.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
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
	outil.attendreChargementElement(Cibles.ELEMENT_TABLEAU_PRELEVEMENT);
	outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_CREODIS_CR);
	CT05.validerObjectif(outil.getDriver(), "VALIDATION", true);
	//Step 2 : Finalisation de l'instruction
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	CT05.validerObjectif(outil.getDriver(), "VALIDATIONINSTRUCTION", true);
	//Step 3 : Remplir le questionnaire pour la demande de financement à 8 jours et la réception de sollicitations commerciales partenaires
	outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	CT05.validerObjectif(outil.getDriver(), "OPTIONS", true);
	//Step 4 : Imprimer la liasse de document
	outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_LIASSE);
	outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
	outil.attendrePresenceTexte("Préparation contrat");
	CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
	//Step 5 : Préparation du contrat et envoi à l'octroi	
	outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
	outil.attendreChargementElement(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE);
	outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT_FIN);
	CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
	CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
	return CT05;
	}
}