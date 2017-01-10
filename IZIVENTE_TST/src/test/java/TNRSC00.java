package test.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import constantes.Erreurs;
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
 * Scénario 00 des tests automatisés pour IZIVENTE - 11/2016
 * Editique 
 * @author levieilfa bardouma
 */
public class TNRSC00 extends SC00Test {

	//Définir le distributeur Constantes.CAS_CE pour CE/Constantes.CAS_BP pour BP
	int distributeur = Constantes.CAS_BP;
	//TODO Notion fonctionnelle dernière ces libellés ?
	//Définir le type de dossier FACELIA/CREODIS/IZICARTE/CREDIT_AMORT
	int typeDossier = Constantes.CREDIT_AMORT;
	//Définir l'établissement et l'agence (1871500030000302) - La valeur null rend des valeurs par défauts qui fonctionnent pour la plupart de nos scénarios
	String etablissement = null;
	String agence = null;
	//Définir l'univers, l'offre et le type d'objet de financement (échelonné, différé, immédiat).
	String typeUnivers = "TRESORERIE";
	String typeOffre = "CREDIT TRESORERIE";
	String typeObjet = "TRESORERIE";
	//Définir l'absence ou la présence de coemprunteur et leurs rôles.
	Boolean aucunCoEmp = false;
	Boolean conjointCoEmp = false;
	Boolean conjointCaution = false;
	Boolean tiersCoEmp = true;
	Boolean tiersCaution = false;

	//Renseigner le numéro de personne physique pour le coemprunteur tiers (BP : 9500855 P1E CE : 942500400).
	String numPersPhysTiers = "942500400";
	//Définir la présence d'assurance pour les emprunteurs (true = oui /false = non).
	Boolean assuranceEmp = true;
	Boolean assuranceConjointCoEmp = false;
	Boolean assuranceTiers = false;
	//Définir l'état de fin de saisie (EDIT = false ; FORCE = true)
	public Boolean edition = true;
	Boolean miseEnGestion = true;

/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	lancement(null);
}

/**
 * Fonction qui effectue le lancement du scénario à partir des informations contenue dans l'objet TNRSC00 et le scénario paramètre.
 * @return l'objet cas d'essai contenant les données de test en sortie (ex : Numéro du client)
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIziventeBean lancement() throws SeleniumException {
	return lancement(null);
}

/**
 * Fonction qui effectue le lancement du scénario à partir des informations contenue dans l'objet TNRSC00 et le scénario paramètre.
 * @param scenario0 le scénario paramètre dont on récupère des informations si il y à lieue, null sinon.
 * @return l'objet cas d'essai contenant les données de test en sortie (ex : Numéro du client)
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIziventeBean lancement(CasEssaiIziventeBean scenario0) throws SeleniumException {
	//Description du scénario
	if (scenario0 == null) {
		scenario0 = new CasEssaiIziventeBean();
		scenario0.setAlm(false);
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario0.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario0.getNomCasEssai() + scenario0.getTime()));
	}
		
		/*scenario0.setAlm(true);
		scenario0.setIdUniqueTestLab(49375);
		scenario0.setNomCasEssai("TNRSC00-" + getTime());
		scenario0.setDescriptif("TNRSC00 - IZIVENTE_Editique XX");
		scenario0.setNomTestLab("TNRSC00 - IZIVENTE_Editique XX");
		//scenario0.setNomTestPlan("TNRSC00 - IZIVENTE_Editique XX");
		scenario0.setCheminTestLab("POC Selenium\\IZIVENTE");*/
		
		//Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		
		//Création et configuration du repertoire de téléchargement
		//File repertoireTelechargement = new File(".\\" + scenario0.getNomCasEssai());
		//repertoireTelechargement.mkdir();
		//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		String repertoire = creerRepertoireTelechargement(scenario0, profile);
		scenario0.setRepertoireTelechargement(repertoire);
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		if (distributeur == Constantes.CAS_CE){
			driver.get(Constantes.URL_CE_FUTURE_REROUTAGE);
		}
		else {
			driver.get(Constantes.URL_BP_FUTURE_REROUTAGE);
		}
		

	   
	    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
	    outil.setRepertoireRacine(scenario0.getRepertoireTelechargement());
	    
	    try {
			//CT01 - Accès Izivente et initialisation
			//CT02 - Ouverture du dossier et validation des informations client
			//CT03 - Saisie des paramètres relatifs a	u type de dossier et validation
			//CT04 - Choix des participants et des assurances associées et validation des participants
			//CT05 - Validation de l'instruction
	    	//CT06 - Mise en Gestion
	    	if (edition) {
				scenario0.getTests().add(CT01Initialisation(scenario0, outil));
				scenario0.getTests().add(CT02OuvertureDossier(scenario0, outil));
				scenario0.getTests().add(CT03SaisieDossier(scenario0, outil));
				scenario0.getTests().add(CT04Participants(scenario0, outil));
				scenario0.getTests().add(CT05FinalisationInstruction(scenario0, outil));
	    	}
			//Condition pour accéder au cas de test de mise en force
			if (miseEnGestion){
				scenario0.getTests().add(CT01Initialisation(scenario0, outil));
				scenario0.getTests().add(CT06MiseGestion(scenario0, outil));
			}
			
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, scenario0, ex, scenario0.getNomCasEssai() + scenario0.getDateCreation().getTime());
			throw ex;
		}
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario0, scenario0.getNomCasEssai() + scenario0.getDateCreation().getTime());
		
		return scenario0;
}

/**
 * Partie du scenario0 regroupant la saisie, l'instruction et la validation d'un dossier dans IZIVENTE CE.
 * @param scenario le scenario englobant.
 * @param outil l'outil de manipulation du navigateur.
 * @return le cas d'essai documenté pour ajout au scénario.
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIziventeBean CT01Initialisation(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT01
	CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
	CT01.setAlm(false);
	CT01.setNomCasEssai("CT01 -" + getTime());
	CT01.setDescriptif("CT01 - Accès Izivente et Initialisation");
	CT01.setNomTestPlan("CT01 - Accès Izivente et Initialisation");
	//Information issues du scénario.
	CT01.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
	CT01.setCheminTestLab(scenario0.getCheminTestLab());
	CT01.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());	
	//Gestion des steps
	CT01.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT01.getNomCasEssai() + CT01.getTime()));
	CT01.ajouterStep("Génération du bouchon client Izivente", "GENERATION", "Création du bouchon terminée");
	CT01.ajouterStep("Lancer l'URL Izivente en fonction du réseau testé", "ACCESREROUTAGE", "Affichage de l'écran de reroutage");
	CT01.ajouterStep("Récupérer numéro de bouchon généré et injecter le jeton (copier/coller du code dans la zone de l'écran de reroutage)", "INJECTION", "Jeton collé dans la zone appropriée");
	CT01.ajouterStep("Valider le jeton et lancer le reroutage (clic sur bouton 'Reroutage')", "ACCESIZIVENTE", "Affichage d'Izivente (écran d'instruction ou pop up de mode de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCES IZIVENTE ET INITIALISATION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	ModificateurBouchon modificateur = new ModificateurBouchon();
	//modificateur.emprunteurCasden = true;
	//Steps 1,2,3,4 : Génération du bouchon - Accès à l'écran de reroutage et injection du jeton - Accès à Izivente
	String idClient = saisieJeton(outil, scenario0.getIdClient(), false, distributeur, modificateur, agence, etablissement);
	scenario0.setIdClient(idClient);
	CT01.validerObjectif(outil.getDriver(), "GENERATION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESREROUTAGE", true);
	CT01.validerObjectif(outil.getDriver(), "INJECTION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESIZIVENTE", true);
	CT01.validerObjectif(outil.getDriver(), CT01.getNomCasEssai() + CT01.getTime(),true);
	return CT01;
}

public CasEssaiIziventeBean CT02OuvertureDossier(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT02
	CasEssaiIziventeBean CT02 = new CasEssaiIziventeBean();
	CT02.setAlm(false);
	CT02.setNomCasEssai("CT02 -" + getTime());
	CT02.setDescriptif("CT02 - Ouverture du dossier");
	CT02.setNomTestPlan("CT02 - Ouverture du dossier");	
	//Information issues du scénario.
	CT02.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
	CT02.setCheminTestLab(scenario0.getCheminTestLab());
	CT02.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
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
	switch(typeDossier){
		case Constantes.CREDIT_AMORT :
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
		break;
		case Constantes.FACELIA : 
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FACELIA);
			//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
			outil.attendrePresenceTexte("INFORMATION");
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		break;
		case Constantes.CREODIS : 
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FC);
		break;
		case Constantes.IZICARTE : 
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_IZICARTE);
			//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
			outil.attendrePresenceTexte("Information");
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		break;
		case Constantes.TEOZ : 
			//outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_TEOZ);
			//outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
		break;
	 }
	CT02.validerObjectif(outil.getDriver(), "OUVERTURE", true);
	if(distributeur == Constantes.CAS_CE){
		outil.attendrePresenceTexte("Attention");
	}
	else {
		outil.attendrePresenceTexte("ATTENTION");
	}
	CT02.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
	// Cette étape n'as lieu que si le dossier est déjà porteur d'un autre dossier
	outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
	outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
	//Step 4 : Vérifier la cohérence des données du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'
	outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
	outil.attendrePresenceTexte("Synthèse");
	CT02.validerObjectif(outil.getDriver(),  "SUIVANT", true);
	outil.cliquer(Cibles.BOUTON_VALIDER);
	CT02.validerObjectif(outil.getDriver(), "VALIDATIONDONNEESCLIENT", true);
	CT02.validerObjectif(outil.getDriver(), CT02.getNomCasEssai() + CT02.getTime(),true);
	return CT02;
}
	
public CasEssaiIziventeBean CT03SaisieDossier(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT03
	CasEssaiIziventeBean CT03 = new CasEssaiIziventeBean();
	CT03.setAlm(false);
	CT03.setNomCasEssai("CT03 -" + getTime());
	CT03.setDescriptif("CT03 - Saisie du dossier");
	CT03.setNomTestPlan("CT03 - Saisie du dossier");
	//Information issues du scénario.
	CT03.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
	CT03.setCheminTestLab(scenario0.getCheminTestLab());
	CT03.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
	//Gestion des steps
	CT03.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT03.getNomCasEssai() + CT03.getTime()));
	CT03.ajouterStep("Sélectionner l'offre désirée dans le menu déroulant selon le scénario", "OFFRE", "Offre sélectionnée");
	CT03.ajouterStep("Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)", "PARAMETRES", "Paramètres cohérents avec le scénario");
	CT03.ajouterStep("Cliquer sur le bouton 'Suivant' pour valider les informations du dossier", "SAISIEDOSSIER", "Affichage de l'écran de sélection des participants");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// SAISIE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : Sélectionner l'offre désirée dans le menu déroulant selon le scénario
	scenario0.setFlag(1);
	switch(typeDossier){
		case Constantes.FACELIA : 
			outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT_CR, true, true);
			outil.selectionner("FACELIA", Cibles.SELECTEUR_OFFRE_CREDIT_CR, false);
			outil.attendre(2);
			CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
			//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
			outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR);
			outil.selectionner("Prêt immobilier", Cibles.SELECTEUR_SITUATION_VENTE_CR);
			outil.viderEtSaisir("7500",  Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
			outil.attendreChargementElement(Cibles.SAISIE_MENSUALITE_CR, true, true); 
			outil.viderEtSaisir("750", Cibles.SAISIE_MENSUALITE_CR);
		break;
		case Constantes.CREODIS :
			outil.attendrePresenceTexte("INFORMATIONS DU CREDIT");
			outil.attendre(2);
			CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
			//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
			outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR);
			outil.selectionner("Entrée en relation", Cibles.SELECTEUR_SITUATION_VENTE_CR);
			outil.viderEtSaisir("2000", Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
			outil.viderEtSaisir("80,00", Cibles.SAISIE_MENSUALITE_CR);
		break;
		case Constantes.IZICARTE : 
			outil.attendrePresenceTexte("Informations du crédit");
			outil.attendre(2);
			CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
			//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
			outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
			outil.selectionner("BANC", Cibles.SELECTEUR_SITUATION_VENTE_CR);
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR, true, true);
			outil.viderEtSaisir("8000", Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
		break;
		case Constantes.CREDIT_AMORT :
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT, true, true);
			outil.selectionner(typeUnivers, Cibles.SELECTEUR_UNIVERS_CREDIT, false);
			outil.attendre(5); //2 secondes ne suffisent pas
			outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
			outil.selectionner(typeOffre, Cibles.SELECTEUR_OFFRE_CREDIT, false);
			outil.attendre(2);
			CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
			//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
			outil.selectionner(typeObjet, Cibles.SELECTEUR_OBJET_FINANCE);
			outil.attendre(2);
			outil.viderEtSaisir("20000", Cibles.SAISIE_COUT_PROJET);
			outil.viderEtSaisir("20000", Cibles.SAISIE_MONTANT_DEMANDE);
			outil.viderEtSaisir("60", Cibles.SAISIE_DUREE_DEMANDE);
		break;
	}
	CT03.validerObjectif(outil.getDriver(), "PARAMETRES", true);
	//Step 3 : Cliquer sur le bouton 'Suivant' pour valider les informations du dossier
	outil.attendre(2);
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	CT03.validerObjectif(outil.getDriver(), "SAISIEDOSSIER", true);
	CT03.validerObjectif(outil.getDriver(), CT03.getNomCasEssai() + CT03.getTime(),true);
	return CT03;
}
	
public CasEssaiIziventeBean CT04Participants(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT04
	CasEssaiIziventeBean CT04 = new CasEssaiIziventeBean();
	CT04.setAlm(false);
	CT04.setNomCasEssai("CT04 -" + getTime());
	CT04.setDescriptif("CT04 - Choix des participants");
	CT04.setNomTestPlan("CT04 - Choix des participants");
	//Information issues du scénario.
	CT04.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
	CT04.setCheminTestLab(scenario0.getCheminTestLab());
	CT04.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
	//Gestion des steps
	CT04.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT04.getNomCasEssai() + CT04.getTime()));
	CT04.ajouterStep("Choisir les participants en fonction de la fiche de prêt et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le numéro de personne physique, cliquer sur rechercher, vérifier la cohérence des données du tiers  et  valider les données du tiers. ", "PARTICIPANTS", "Affichage de l'écran 'Synthèse des participants'");
	CT04.ajouterStep("Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses.", "ASSURANCEROLE", "Les informations sont conformes et le bouton de validation des participants cliquable");
    CT04.ajouterStep("Valider la liste des participants (clic sur bouton correspondant)", "VALIDATIONPARTICIPANTS", "Affichage de l'écran de 'Proposition' avec la grille alternative commerciale");
	
    /////////////////////////////////////////////////////////////////////////////////////////////////////
	// PARTICIPANTS
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : Choisir les participants en fonction de la fiche de prêt et Valider. Aucun co-emprunteur dans ce scénario
    
    if (aucunCoEmp == true) {
    		aucunCoEmprunteur(outil);
    }
    else if(conjointCoEmp == true && conjointCaution == false && tiersCoEmp == false && tiersCaution == false) {
    	ajoutConjointCoEmprunteurUnique(outil);
    }
    else if(tiersCoEmp == true || tiersCaution == true){
    	ajoutTiers(outil);
    }
    
    //Définition du rôle du tiers
  	roleTiers(outil);
    //Définition du rôle du conjoint
  	roleConjoint(outil);
	CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
	//Step 2 : Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses. Valider la listes des participants et confirmer l'assurance
	//Assurance de l'emprunteur
	assuranceEmprunteur(outil);
	//Assurance du conjoint
	assuranceConjoint(outil);
	//Assurance du tiers
	assuranceTiers(outil);
	CT04.validerObjectif(outil.getDriver(), "ASSURANCEROLE", true);
	//Step 3 : Valider la liste des participants (étape différente pour une CREODIS)
	if(typeDossier != Constantes.CREODIS){
	outil.attendreChargementElement(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	}
	else { 
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	}
	CT04.validerObjectif(outil.getDriver(),  "VALIDATIONPARTICIPANTS", true);
	CT04.validerObjectif(outil.getDriver(), CT04.getNomCasEssai() + CT04.getTime(),true);
	return CT04;
}
	
public CasEssaiIziventeBean CT05FinalisationInstruction(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT05
	CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
	CT05.setAlm(false);
	CT05.setNomCasEssai("CT05 -" + getTime());
	CT05.setDescriptif("CT05 - Finalisation de l instruction");
	CT05.setNomTestPlan("CT05 - Finalisation de l instruction");
	//Information issues du scénario.
	CT05.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
	CT05.setCheminTestLab(scenario0.getCheminTestLab());
	CT05.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
	//Gestion des steps
	CT05.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT05.getNomCasEssai() + CT05.getTime()));
	CT05.ajouterStep("Valider de l'offre contrat de crédit (clic sur le bouton 'Valider en contrat de crédit')", "VALIDATION", "Affichage de la pop up de finalisation de l'instruction ou de la page de dossier de vente");
	CT05.ajouterStep("Finalisation de l'instruction (clic sur le bouton 'Oui' dans la popup de finalisation de l'instruction", "VALIDATIONINSTRUCTION", "Affichage de l'écran de synthèse de l'offre de crédit");
	CT05.ajouterStep("Remplir le questionnaire pour la demande de financement à 8 jours et la réception de sollicitations commerciales partenaires (choix oui/non via boutons radio).", "OPTIONS", "Choix effectués conformément au scénario");
    CT05.ajouterStep("Imprimer la liasse de document (clic sur le bouton 'Suivant')", "IMPRESSION", "Affichage de la pop up 'Préparation contrat'");
    CT05.ajouterStep("Attendre la fin de la préparation du contrat puis cliquer sur suivant pour envoi à l'octroi", "PREPARATION", "Deconnexion d'Izivente");
	//Step Izicarte
    CT05.ajouterStep("Choisir le mode de vente (face à face ou par téléphone) selon le scénario", "MODE", "Impression en pdf de la liasse");
    //Step PP CE
	//CT05.ajouterStep("Imprimer la synthèse de l'offtre en pdf (clic sur bouton 'Imprimer')", "IMPRESSIONSYNTHESE", "Le fichier pdf est correctement télécharge");
	CT05.ajouterStep("Sélectionner le compte de prélèvement et valider l'offre de crédit (clic sur bouton 'Confirmer contrat de crédit')", "CONFIRMATION", "Affichage de la pop up 'Finalisation de l'instruction'");
	CT05.ajouterStep("Vérifier les justificatifs et valider (clic bouton radio 'Vérifié' pour chaque justificatif dans la pop up de finalisation de l'instruction' et clic sur bouton 'Valider'", "VERIFICATION", "Retour sur la page de dossier de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// FINALISATION DE L'INSTRUCTION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	switch(typeDossier){
		case Constantes.FACELIA :
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
			//Récupération du numéro FFI
			String numeroFFIFacelia = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMERO_FFI_CR);
			scenario0.setNumeroFFI(numeroFFIFacelia);
			//Step 4 : Imprimer la liasse de document
			outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_LIASSE);
			outil.cliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
			outil.attendrePresenceTexte("Préparation contrat");
			CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
			//Step 5 : Préparation du contrat et envoi à l'octroi	
			outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
			outil.attendreChargementElement(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT_FIN);
			CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
		break;
		case Constantes.CREODIS :
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
			String numeroFFICREODIS = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMERO_FFI_CR);
			scenario0.setNumeroFFI(numeroFFICREODIS);
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
		break;
		case Constantes.IZICARTE :
			//Step 1 : Valider de l'offre contrat de crédit (Etape non présente dans un CR)
			//Step 1 : Valider de l'offre contrat de crédit 
			outil.attendreChargementElement(Cibles.BOUTON_VALIDER_OPC_CR);
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_OPC_CR);
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
			outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
			outil.attendrePresenceTexte("Préparation contrat");
			CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
			//Step 5 : Préparation du contrat - choix du mode de vente	
			outil.attendreChargementElement(Cibles.BOUTON_POPUP_FACE_A_FACE_MAJ);
			outil.cliquer(Cibles.BOUTON_POPUP_FACE_A_FACE_MAJ);
			CT05.validerObjectif(outil.getDriver(), "MODE", true);
			//Step 6 : Fin de l'édition
			outil.attendreChargementElement(Cibles.BOUTON_PASSAGE_OCTROI_CR, true, true);
			outil.attendreChargementElement(Cibles.BOUTON_TERMINER_EDITION_CR, true, true);
			outil.cliquer(Cibles.BOUTON_TERMINER_EDITION_CR);
			//Récupération du numéro FFI
			String numeroFFIIzicarte = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMEOR_FFI_IZICARTE);
			scenario0.setNumeroFFI(numeroFFIIzicarte);
			CT05.validerObjectif(outil.getDriver(), "VERIFICATION", true);
			CT05.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
		break;
		case Constantes.CREDIT_AMORT :
			//Step 1 : Valider de l'offre contrat de crédit
			//outil.attendrePresenceTexte("Alerte(s)");
			//outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
			//outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
			outil.attendreChargementElement(Cibles.BOUTON_ACCES_VALIDATION_OPC,true, true);
			outil.cliquer(Cibles.BOUTON_ACCES_VALIDATION_OPC);
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
			//Récupération du numéro FFI
			String numeroFFIPP = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMERO_FFI);
			scenario0.setNumeroFFI(numeroFFIPP);
			//Step 6 : Imprimer la liasse de document
			outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
			outil.attendrePresenceTexte("Préparation contrat");
			CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
			//Step 7 : Préparation du contrat et envoi à l'octroi		
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT);
			CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
		break;
	}
	scenario0.setFlag(2);
	CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
	return CT05;
}

public CasEssaiIziventeBean CT06MiseGestion(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT06
	CasEssaiIziventeBean CT06 = new CasEssaiIziventeBean();
	CT06.setAlm(false);
	//Information issues du scénario.
	//Gestion des steps
	CT06.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT06.getNomCasEssai() + CT06.getTime()));
	CT06.ajouterStep("Relancement d'Izivente et retour sur le dossier", "RETOUR", "Affichage de la page d'accueil d'Izivente avec injection du jeton");
	CT06.ajouterStep("Ouverture et du dossier et recherche du numéro FFI", "RECHERCHE", "Affichage des données dossier et client");
	CT06.ajouterStep("Passage à l'octroi et premières vérification", "OCTROI", "Dossier accepté pour l'octroi ");
	CT06.ajouterStep("Finalisation de l'octroi et dernières confirmations avant mise en gestion", "FINALISATION", "Affichage des données dossiers et client avec état FORC");
	CT06.ajouterStep("Vérification des données dossier et client", "MISENFORCE", "Dossier à l'état FORC");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////// MISE EN GESTION ////////////////////////////////////////////
	//if (typeDossier != Constantes.CREDIT_AMORT){
	//Step 1 : Rechargement de l'URL d'Izivente et réinjection du jeton
	//saisieJeton(outil, scenario0.getIdClient(), false, distributeur, null, agence, etablissement);
	CT06.validerObjectif(outil.getDriver(), "RETOUR", true);
	//Step 2 : Ouverture du dossier et recherche du numéro FFI
	if (typeDossier == Constantes.FACELIA || typeDossier == Constantes.IZICARTE) {
		outil.cliquer(Cibles.BOUTON_MENU_REPRISE_DOSSIER);
	}
	else if(typeDossier == Constantes.CREODIS) {
		outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FC);
	}
	else if(typeDossier == Constantes.CREDIT_AMORT) {
		outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
	}
	outil.attendrePresenceTexte("Liste des dossiers");
	for (WebElement coche : outil.obtenirElements(Cibles.COCHES_LISTE_DOSSIER)) {
		coche.click();
		outil.attendre(1);
		if (outil.testerPresenceTexte(scenario0.getNumeroFFI(), true)) {
			break;
		}
	}
	CT06.validerObjectif(outil.getDriver(), "RECHERCHE", true);
	// Step 3 : Passage à l'octroi
	if (typeDossier == Constantes.CREDIT_AMORT) {
		outil.cliquer(Cibles.BOUTON_PASSAGE_OCTROI);
		outil.attendrePresenceTexte("Demande de confirmation");
	}
	else {
		outil.attendreChargementElement(Cibles.BOUTON_MISE_EN_FORCE_CR, true, true);
		outil.cliquer(Cibles.BOUTON_MISE_EN_FORCE_CR);
	}
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ, true, true);
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ, true, true);
	outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
	CT06.validerObjectif(outil.getDriver(), "VERIFICATION", true);
	//Step 4 : Acceptation et finalisation de l'octroi
	outil.attendreChargementElement(Cibles.BOUTON_SUIVANT, true, true);
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	outil.attendreChargementElement(Cibles.LIBELLE_ACCEPTATION);
	outil.cliquer(Cibles.LIBELLE_ACCEPTATION);
	outil.attendre(1);
	outil.attendreChargementElement(Cibles.BOUTON_FINALISATION_OCTROI_CR, true, true);
	outil.cliquer(Cibles.BOUTON_FINALISATION_OCTROI_CR);
	CT06.validerObjectif(outil.getDriver(), "OCTROI", true);
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI, true, true);
	outil.cliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
	CT06.validerObjectif(outil.getDriver(), "FINALISATION", true);
	//Step 5 : Vérification du passage à l'état FORC
	outil.attendrePresenceTexte("Liste des dossiers");
	CT06.validerObjectif(outil.getDriver(), "MISEENFORCE", true);
	scenario0.setFlag(3);
	//outil.fermerFenetreCourante();
	return CT06;
}

	/**
	 * Fonction générique permettant pour tout type de produit de ne positionner aucun coemprunteur.
	 * @param cas le cas de test concerné.
	 * @param outil la boite à outil selenium.
	 * @throws SeleniumException en cas d'erreur lors de l'interaction avec l'IHM.
	 */
	private void aucunCoEmprunteur(SeleniumOutils outil) throws SeleniumException {
		// Si le type de dossier est tout sauf CREODIS on clique sur le bouton "Aucun CoEmprunteur"
		//On passe automatiquement à l'étape de choix d'assurance pour le CREODIS
		if (typeDossier != Constantes.CREODIS) {
	    	outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
	    	outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
		}
	}
	
	private void ajoutConjointCoEmprunteurUnique(SeleniumOutils outil) throws SeleniumException {
		//On clique sur le bouton "Ajouter Conjoint CoEmprunteur" si on ne veut que le conjoint coemprunteur sur le dossier.
		//Cette option n'est accessible que pour les PP et les CR IZICARTE dont l'emprunteur et le conjoint ont un compte joint
		if(typeDossier == Constantes.CREDIT_AMORT) {
			outil.cliquer(Cibles.BOUTON_AJOUT_CONJOINT);
		} else{
			//TODO Gérer le mode IZICARTE avec compte joint
			System.out.println("On ne peut pas inscrire de co emprunteur sur un CR sauf Izicarte muni d'un compte joint");
			//outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
    		//outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
		}
	}
	
	/**
	 * Fonction générique d'ajout d'un tiers dont le numéro de personne physique est connu et paramètré dans la classe.
	 * @param outil la boite à outil.
	 * @throws SeleniumException en cas d'erreur.
	 */
	private void ajoutTiers(SeleniumOutils outil) throws SeleniumException {
		
		// Recherche du tiers à partir du numéro paramètre
		outil.attendreChargementElement(Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, true, true);
		outil.selectionner("RCHNUMERO", Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, false);
		outil.attendreChargementElement(Cibles.SAISIE_NUMERO_PERS_PHY);
		outil.viderEtSaisir(numPersPhysTiers, Cibles.SAISIE_NUMERO_PERS_PHY);
		outil.cliquer(Cibles.BOUTON_RECHERCHER);
		//Ajout du tiers
	    outil.attendreChargementElement(Cibles.BOUTON_AJOUT_TIERS);
		outil.cliquer(Cibles.BOUTON_AJOUT_TIERS);
		
		// On valide la popup relative au tiers ajouté
		//TODO Amélioration possible sur le bouton de validation pour éviter la différenciation entre les distributeurs
		if(distributeur == Constantes.CAS_CE) {
			outil.attendrePresenceTexte("Attention");
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
			outil.attendreChargementElement(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_1);
			outil.cliquer(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_1);
		} else {
			outil.attendrePresenceTexte("ATTENTION");
    		outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
    		outil.attendreChargementElement(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_BP);
    		outil.cliquer(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_BP);
    	}
		
		// Validation de la synthèse du tiers
    	outil.attendrePresenceTexte("Synthèse des informations sur le Tiers");
		outil.cliquer(Cibles.BOUTON_POPUP_VALIDER_SYNTHESE_TIERS);
	}
	
	/**
	 * Fonction générique pour la mise en place ou non d'une assurance pour l'emprunteur
	 * @param outil la boîte à outil
	 * @throws SeleniumException en cas d'erreur
	 */
	private void assuranceEmprunteur(SeleniumOutils outil) throws SeleniumException {
		//TODO prévoir le choix de type d'assurance
		outil.attendre(1);
		
		if (assuranceEmp == true ) {
				if (typeDossier != Constantes.CREODIS) {
					outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
					outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
					outil.attendre(2);
					switch (typeDossier) {
					case Constantes.CREDIT_AMORT :
						outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ, true, true);
						outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
					break;
					case Constantes.IZICARTE :
						outil.attendreChargementElement(Cibles.RADIO_AVEC_ASS_CR, true, true);
						outil.cliquer(Cibles.RADIO_AVEC_ASS_CR);
					break;
					case Constantes.FACELIA :
							outil.attendreChargementElement(Cibles.RADIO_AVEC_ASS_FACELIA);
						outil.cliquer(Cibles.RADIO_AVEC_ASS_FACELIA);
					break;
					}
				}
				else {
					outil.attendreEtCliquer(Cibles.RADIO_SELECTION_ASS_1_CR);
				}	
		}
		else{
			switch (typeDossier) {
					case Constantes.CREDIT_AMORT :
						outil.attendre(2);
						outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
					break;
					case Constantes.IZICARTE :
						outil.attendre(1);
						outil.attendreChargementElement(Cibles.RADIO_SANS_ASS_CR);
						outil.cliquer(Cibles.RADIO_SANS_ASS_CR);
					break;
					case Constantes.FACELIA :
						outil.attendreChargementElement(Cibles.RADIO_SANS_ASS_FACELIA);
						outil.cliquer(Cibles.RADIO_SANS_ASS_FACELIA);
					break;
					case Constantes.CREODIS :
						outil.attendreEtCliquer(Cibles.RADIO_SELECTION_SANS_ASS_CR);
					break;
				}
		}
		}

	/**
	 * Fonction générique pour le choix d'une assurance sur le conjoint
	 * @param outil la boîte à outil
	 * @throws SeleniumException en cas d'erreur
	 */
	private void assuranceConjoint(SeleniumOutils outil) throws SeleniumException {
		Boolean presenceTiers = (tiersCoEmp || tiersCaution);
		Boolean presenceConjoint = (conjointCoEmp || conjointCaution);
		if (presenceConjoint) {
		if (presenceTiers) {
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT2);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT2);
		}
		else {
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
		}
		if (assuranceConjointCoEmp == true){
			outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
			outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		}
		else if(assuranceConjointCoEmp == false){
			outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_NON_MAJ);
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
		}
		}
	}
	/**
	 * Fonction générique pour le choix d'une assurance sur le tiers
	 * @param outil la boîte à outil
	 * @throws SeleniumException en cas d'erreur
	 */
	private void assuranceTiers(SeleniumOutils outil) throws SeleniumException {
		if((tiersCoEmp == true || tiersCaution == true) && (assuranceConjointCoEmp == false || assuranceEmp == false)){
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendre(1);
			
			if(assuranceTiers){
				outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
				outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);			
			}
			else{
				outil.attendre(1);
				outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_NON_MAJ);
				outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
			}
		}
	}
	/**
	 * Fonction permettant de choisir le rôle du conjoint (co emprunnteur ou caution) seulement dans le cas de la présence d'un tiers sur le dossier
	 * @param outil la boîte à outil
	 * @throws SeleniumException en cas d'erreur
	 */
	private void roleConjoint(SeleniumOutils outil) throws SeleniumException {
		
		// Un tiers as t'il un rôle ?
		Boolean roleTiers = (tiersCoEmp == true || tiersCaution == true);
		
		// Quel rôle est réservé au conjoint ?
		if (conjointCoEmp == true && conjointCaution == true) {
			throw new SeleniumException(Erreurs.E030, "Impossible d'avoir deux rôles pour le conjoint.");
		}
		// Si le tiers dispose d'un rôle, soit on supprime le conjoint si il n'as pas de rôle, soit on choisit le rôle demandé
		if (roleTiers) {
			if(conjointCoEmp == false && conjointCaution == false){
				//S'il n'il n'y a pas d'indication sur le conjoint avec présence de tiers, on supprime le conjoint du dossier
				boolean CE = (distributeur == Constantes.CAS_CE);
				outil.attendre(2);
				outil.attendreChargementElement(CE?Cibles.BOUTON_SUPP_PARTICIPANT_2_CE:Cibles.BOUTON_SUPP_PARTICIPANT_2_BP);
				outil.cliquer(CE?Cibles.BOUTON_SUPP_PARTICIPANT_2_CE:Cibles.BOUTON_SUPP_PARTICIPANT_2_BP);
				outil.attendrePresenceTexte("Demande de confirmation de suppression");
				outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
				outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
					
			} else {
				//outil.attendre(2);
				outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT2, true, true);
				outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT2);
				outil.attendre(1);
				outil.attendreChargementElement(Cibles.SELECTEUR_ROLE_PARTICIPANT, true, true);
				String roleDuConjoint = "";
				
				if (conjointCoEmp) {
					roleDuConjoint = "C";
				} else {
					roleDuConjoint = "G";
				}
				
				outil.selectionner(roleDuConjoint, Cibles.SELECTEUR_ROLE_PARTICIPANT);
			}
		}
	}
	/**
	 * Fonction générique pour définir le rôle du tiers
	 * @param outil
	 * @throws SeleniumException
	 */
	private void roleTiers(SeleniumOutils outil) throws SeleniumException {
		
		// Un tiers as t'il un rôle ?
		Boolean roleTiers = (tiersCoEmp == true || tiersCaution == true);
		// Si le tiers dispose d'un rôle, on doit soit supprimer le conjoint si il n'as pas de rôle soit choisir son rôle
		if (roleTiers) {
			// Quel rôle est réservé au conjoint ?
			
		if (tiersCoEmp == true && tiersCaution == true) {
			throw new SeleniumException(Erreurs.E030, "Impossible d'avoir deux rôles pour le tiers.");
		}
		outil.attendre(1);
		outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1, true, true);
		outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
		outil.attendre(1);
		String roleDuTiers = "";
		
		if (tiersCoEmp) {
			roleDuTiers = "C";
		} else {
			roleDuTiers = "G";
		}

		outil.selectionner(roleDuTiers, Cibles.SELECTEUR_ROLE_PARTICIPANT);
		}
	}
	/**
	 * Fonction permettant de récupérer une chaine de caractère (BP ou CE) en fonction du distributeur sélectionné
	 */
	private String chaineDistributeur (int casDistributeur) {
		String dist = "";
		if (casDistributeur == Constantes.CAS_CE){
			dist = "CE";
		}
		else {
			dist = "BP";
		}
		return dist;
		
	}
	/**
	 * Fonction permettant de récupérer une chaîne de caractère (CR ou PP) en fonction du type de dossier
	 * @param typeDossier2
	 * @return
	 */
	private String chaineProduit(int typeDossier2) {
		String nomProduit = "";
		if (typeDossier2 == Constantes.CREDIT_AMORT){
			nomProduit = "PP";
		}
		if (typeDossier2 == Constantes.CREODIS) {
			nomProduit = "CR";
		}
		if (typeDossier2 == Constantes.FACELIA) {
			nomProduit = "FA";
		}
		if (typeDossier2 == Constantes.IZICARTE) {
			nomProduit = "IZ";
		}
		return nomProduit;
	}
	
	/**
	 * Fonction qui aliment le fichier de données à partir des informations en paramètre.
	 * @param scenario les informations sur le scénario
	 * @param flag le flag du dossier indiquant son état d'avancée 
	 * @param date à quelle date doit ont faire la prochaine action?
	 * @throws SeleniumException en cas d'erreur d'accès au fichier.
	 */
	public void ecritureFichierDonnees(CasEssaiIziventeBean scenario, Date date) throws SeleniumException {
		String distrib = chaineDistributeur(this.distributeur);
		String FFI = scenario.getNumeroFFI();
		String idClnt = scenario.getIdClient();
		String IUN = null;
		String typeDos = chaineProduit(this.typeDossier);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		int flg = scenario.getFlag();
		String chaine = (distrib +";"+ FFI + ";" + idClnt + ";" + IUN + ";"+ typeDos +";"+ flg +";" + sdf.format(date) + "\r\n");

		
		try {
			boolean existence = remplacer(scenario.getNumeroFFI(), chaine);
			if (!existence) {
				//TODO modifier le chemin vers le fichier, il doit être dans le propertie.
				Files.write(Paths.get("src/test/DonneesClientDossier.txt"),chaine.getBytes(),StandardOpenOption.APPEND);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new SeleniumException(Erreurs.E020, "Impossible d'écrire dans DonneesClientDossier");
		} 
	}
	
	public CasEssaiIziventeBean initialiserScenario(String instance) {
		CasEssaiIziventeBean scenario = null;
		try {
			String[] instanceDecoupee = instance.split(";");		
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			Date date = sdf.parse(instanceDecoupee[6]);
			GregorianCalendar jourCible = new GregorianCalendar();
			GregorianCalendar jour = new GregorianCalendar();
			jourCible.setTime(date);
			jour.setTime(new Date());
			// Si la date est la date du jour, alors on effectue l'action, sinon on passe.
			if (jourCible.get(GregorianCalendar.DAY_OF_YEAR) <= jour.get(GregorianCalendar.DAY_OF_YEAR)) {
				scenario = new CasEssaiIziventeBean();
				distributeur = "CE".equals(instanceDecoupee[0])?Constantes.CAS_CE:Constantes.CAS_BP;
				scenario.setNumeroFFI(instanceDecoupee[1]);
				scenario.setIdClient(instanceDecoupee[2]);
				scenario.setNumeroIUN(instanceDecoupee[3]);
				//TODO Attention préciser le type de produit dans le fichier à écrire.
				switch (instanceDecoupee[4]) {
					case "PP" :
						typeDossier = Constantes.CREDIT_AMORT;
					break;
					case "CR" :
						typeDossier = Constantes.CREODIS;
					break;
					case "FA" :
						typeDossier = Constantes.FACELIA;
					break;
					case "IZ" :
						typeDossier = Constantes.IZICARTE;
				}
				scenario.setFlag(Integer.parseInt(instanceDecoupee[5]));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scenario;
	}
}