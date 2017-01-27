package test.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import main.bean.CasEssaiIziventeBean;
import main.bean.ModificateurBouchon;
import main.constantes.Catalogue;
import main.constantes.Cibles;
import main.constantes.Constantes;
import main.constantes.TypeProduit;
import main.outils.IZIVENTEOutils;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import outils.SeleniumOutils;
import beans.CibleBean;
import beans.ObjectifBean;
import constantes.Erreurs;
import exceptions.SeleniumException;

/**
 * Scénario 00 des tests automatisés pour IZIVENTE - 11/2016
 * Editique 
 * @author levieilfa bardouma
 * @param <SCConsultation>
 */
public class TNRSC00 extends SC00Test {

	//Définir le distributeur Constantes.CAS_CE pour CE/Constantes.CAS_BP pour BP
	int distributeur = Constantes.CAS_BP;
	//Définir le type de dossier FACELIA/CREODIS/IZICARTE/CREDIT_AMORT
	//int typeDossier = Constantes.CREDIT_AMORT;
	TypeProduit typeDossier = TypeProduit.CREDIT_AMORT;
	//Définir le numéro de client/distributeur
	String idClient = null;
	//Définir l'établissement et l'agence (1871500030000302) - La valeur null rend des valeurs par défauts qui fonctionnent pour la plupart de nos scénarios
	String etablissement = null;
	String agence = null;
	Boolean producteur = false;
	//Définir l'univers, l'offre et le type d'objet de financement (échelonné, différé, immédiat).
	String typeUnivers = "TRESORERIE";
	String typeOffre = "CREDIT TRESORERIE";
	String typeObjet = "TRESORERIE";
	//Définir le cout et la mensualité du crédit
	String coutProjet = null;
	String mensualite = null;
	String montantCredit = null;
	String dureeDiffere = null;
	String situationDeVente = "Prêt immobilier";
	//Définir l'absence ou la présence de coemprunteur et leurs rôles.
	Boolean aucunCoEmp = false;
	Boolean conjointCoEmp = false;
	Boolean conjointCaution = false;
	Boolean tiersCoEmp = false;
	Boolean tiersCaution = false;
	//Renseigner le numéro de personne physique pour le coemprunteur tiers (BP : 9500855 P1E CE : 942500400).
	String numPersPhysTiers = "9500855";
	//Définir la présence d'assurance pour les emprunteurs (true = oui /false = non).
	Boolean assuranceEmp = false;
	Boolean assuranceConjointCoEmp = false;
	Boolean assuranceTiers = false;
	//Définir l'état de fin de saisie (EDIT = false ; FORCE = true)
	public Boolean simulation = false;
	public Boolean validation = false;
	public Boolean edition = false;
	Boolean miseEnGestion = false;
	Boolean murissement = false;
	ModificateurBouchon modificateur = new ModificateurBouchon();

	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;

	///**
	// * Fonction de lancement par défaut ne comportant aucun paramètre.
	// * @throws SeleniumException en cas d'erreur.
	// */
	//@Test
	//public void accesIzivente() throws SeleniumException {
	//	lancement();
	//}

	/**
	 * Fonction qui effectue le lancement du scénario à partir des informations contenue dans l'objet TNRSC00 et le scénario paramètre.
	 * @return l'objet cas d'essai contenant les données de test en sortie (ex : Numéro du client)
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean lancement() throws SeleniumException {
		return lancement(null);
	}

	/**
	 * Permet d'obtenir la boite à outil Selenium associé à un driver pour le scénario donné.
	 * @param scenario0 le scénario concerné.
	 * @return la boite à outil Selenium associée au scénario.
	 */
	public SeleniumOutils obtenirDriver(CasEssaiIziventeBean scenario0) {
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
		
		return outil;
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
			/*scenario0.setAlm(true);
			scenario0.setIdUniqueTestLab(49375);
			scenario0.setNomCasEssai("TNRSC00-" + getTime());
			scenario0.setDescriptif("TNRSC00 - IZIVENTE_Editique XX");
			scenario0.setNomTestLab("TNRSC00 - IZIVENTE_Editique XX");
			//scenario0.setNomTestPlan("TNRSC00 - IZIVENTE_Editique XX");
			scenario0.setCheminTestLab("POC Selenium\\IZIVENTE");*/
			scenario0 = new CasEssaiIziventeBean();
			scenario0.setNomCasEssai(this.getNomCasEssai());
			scenario0.setAlm(this.getAlm());
			scenario0.setIdUniqueTestLab(this.getIdUniqueTestLab());
			scenario0.setDescriptif(this.getDescriptif());
			scenario0.setNomTestLab(this.getNomTestLab());
			scenario0.setCheminTestLab(this.getCheminTestLab());
			// LISTE DES OBJECTIFS DU CAS DE TEST
			scenario0.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario0.getNomCasEssai() + scenario0.getTime()));
		}
		//On déclare les variables relatives au scénario (numéro client/distributeur, 
		declarationScenario(scenario0);
	
		SeleniumOutils outil = obtenirDriver(scenario0);
	    
	    try {
	    	if (simulation){
	    		scenario0.getTests().add(CT01Initialisation(scenario0, outil));
				scenario0.getTests().add(CT02OuvertureDossier(scenario0, outil));
				scenario0.getTests().add(CT03SaisieDossier(scenario0, outil));
				scenario0.getTests().add(CT04Participants(scenario0, outil));
	    	}
	    	if (validation){
	    		scenario0.getTests().add(CT01Initialisation(scenario0, outil));
				scenario0.getTests().add(CT02OuvertureDossier(scenario0, outil));
				scenario0.getTests().add(CT03SaisieDossier(scenario0, outil));
				scenario0.getTests().add(CT04Participants(scenario0, outil));
				scenario0.getTests().add(CT05Validation(scenario0, outil));
	    	}
	    	if (edition) {
				scenario0.getTests().add(CT01Initialisation(scenario0, outil));
				scenario0.getTests().add(CT02OuvertureDossier(scenario0, outil));
				scenario0.getTests().add(CT03SaisieDossier(scenario0, outil));
				scenario0.getTests().add(CT04Participants(scenario0, outil));
				scenario0.getTests().add(CT05Validation(scenario0, outil));
				scenario0.getTests().add(CT06FinalisationInstruction(scenario0, outil));
	    	}
			//Condition pour accéder au cas de test de mise en force
			if (miseEnGestion){
				scenario0.getTests().add(CT01Initialisation(scenario0, outil));
				scenario0.getTests().add(CT07MiseGestion(scenario0, outil));
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
		CT01.setAlm(scenario0.getAlm());
		CT01.setNomCasEssai("CT01 -" + getTime());
		CT01.setDescriptif(Catalogue.CT01_TITRE);
		CT01.setNomTestPlan(Catalogue.CT01_TITRE);
		//Information issues du scénario.
		CT01.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT01.setCheminTestLab(scenario0.getCheminTestLab());
		CT01.setNomTestLab(scenario0.getNomTestLab());
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
	
		//Steps 1,2,3,4 : Génération du bouchon - Accès à l'écran de reroutage et injection du jeton - Accès à Izivente
		String idClient = saisieJeton(outil, scenario0.getIdClient(), producteur, distributeur, modificateur, scenario0.getAgence(), scenario0.getEtablissement());
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
		CT02.setAlm(scenario0.getAlm());
		CT02.setNomCasEssai("CT02 -" + getTime());
		CT02.setDescriptif(Catalogue.CT02_TITRE);
		CT02.setNomTestPlan(Catalogue.CT02_TITRE);	
		//Information issues du scénario.
		CT02.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT02.setCheminTestLab(scenario0.getCheminTestLab());
		CT02.setNomTestLab(scenario0.getNomTestLab());
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
			case CREDIT_AMORT:
				outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
			break;
			case FACELIA : 
				outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FACELIA);
				//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
				outil.attendrePresenceTexte("INFORMATION");
				outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			break;
			case CREODIS : 
				outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FC);
			break;
			case IZICARTE : 
				outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_IZICARTE);
				//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
				outil.attendrePresenceTexte("Information");
				outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			break;
			case TEOZ : 
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
		outil.attendreChargementElement(Cibles.BOUTON_RAFRAICHISSEMENT_INFOS_CLIENT);
		outil.cliquer(Cibles.BOUTON_RAFRAICHISSEMENT_INFOS_CLIENT);
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
		CT03.setAlm(scenario0.getAlm());
		CT03.setNomCasEssai("CT03 -" + getTime());
		CT03.setDescriptif(Catalogue.CT03_TITRE);
		CT03.setNomTestPlan(Catalogue.CT03_TITRE);
		//Information issues du scénario.
		CT03.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT03.setCheminTestLab(scenario0.getCheminTestLab());
		CT03.setNomTestLab(scenario0.getNomTestLab());
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
		scenario0.setFlag(0);
		switch(this.typeDossier){
			case FACELIA : 
				//outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT_CR, true, true);
				//outil.selectionner("FACELIA", Cibles.SELECTEUR_OFFRE_CREDIT_CR, true);
				outil.attendre(2);
				CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
				//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
				outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
				outil.selectionner(situationDeVente, Cibles.SELECTEUR_SITUATION_VENTE_CR);
				outil.attendreChargementElement(Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR, true, true);
				outil.viderEtSaisir(montantCredit,  Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
				outil.attendreChargementElement(Cibles.SAISIE_MENSUALITE_CR, true, true); 
				outil.viderEtSaisir(mensualite, Cibles.SAISIE_MENSUALITE_CR);
			break;
			case CREODIS :
				outil.attendrePresenceTexte("INFORMATIONS DU CREDIT");
				CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
				//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
				outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
				outil.selectionner("Entrée en relation", Cibles.SELECTEUR_SITUATION_VENTE_CR);
				outil.viderEtSaisir(montantCredit, Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
				outil.viderEtSaisir(mensualite, Cibles.SAISIE_MENSUALITE_CR);
			break;
			case IZICARTE : 
				outil.attendre(2);
				CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
				//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
				outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
				outil.selectionner(situationDeVente, Cibles.SELECTEUR_SITUATION_VENTE_CR);
				outil.attendreChargementElement(Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR, true, true);
				outil.viderEtSaisir(montantCredit,  Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
				outil.attendreChargementElement(Cibles.SAISIE_MENSUALITE_CR, true, true); 
				outil.viderEtSaisir(mensualite, Cibles.SAISIE_MENSUALITE_CR);
				
				//outil.attendrePresenceTexte("Informations du crédit");
				//CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
				//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
				//outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
				//outil.selectionner("BANC", Cibles.SELECTEUR_SITUATION_VENTE_CR, true);
				//outil.attendre(2);
				//outil.attendreChargementElement(Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR, true, true);
				//outil.viderEtSaisir(montantCredit, Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
			break;
			case CREDIT_AMORT :
				outil.attendre(1);
				outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT, true, true);
				outil.selectionner(typeUnivers, Cibles.SELECTEUR_UNIVERS_CREDIT, false);
				outil.attendre(2); //2 secondes ne suffisent pas
				outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT, true, true);
				outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
				outil.selectionner(typeOffre, Cibles.SELECTEUR_OFFRE_CREDIT, false);
				outil.attendre(1);
				CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
				//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
				outil.attendreChargementElement(Cibles.SELECTEUR_OBJET_FINANCE, true, true);
				outil.selectionner(typeObjet, Cibles.SELECTEUR_OBJET_FINANCE, false);
				outil.attendreChargementElement(Cibles.SAISIE_COUT_PROJET);
				outil.viderEtSaisir(coutProjet, Cibles.SAISIE_COUT_PROJET);
				
				if (typeOffre != "PERMIS 1 EURO") {
				outil.viderEtSaisir(montantCredit, Cibles.SAISIE_MONTANT_DEMANDE);
				outil.viderEtSaisir(mensualite, Cibles.SAISIE_MENSUALITE_PP);
				}
				
				if (dureeDiffere != null){
				outil.viderEtSaisir(dureeDiffere, Cibles.SAISIE_DUREE_DIFFERE_PARTIEL);
				}
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
		CT04.setAlm(scenario0.getAlm());
		CT04.setNomCasEssai("CT04 -" + getTime());
		CT04.setDescriptif(Catalogue.CT04_TITRE);
		CT04.setNomTestPlan(Catalogue.CT04_TITRE);
		//Information issues du scénario.
		CT04.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT04.setCheminTestLab(scenario0.getCheminTestLab());
		CT04.setNomTestLab(scenario0.getNomTestLab());
		CT04.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des steps
		CT04.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT04.getNomCasEssai() + CT04.getTime()));
		CT04.ajouterStep("Choisir les participants en fonction de la fiche de prêt et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le numéro de personne physique, cliquer sur rechercher, vérifier la cohérence des données du tiers  et  valider les données du tiers. ", "PARTICIPANTS", "Affichage de l'écran 'Synthèse des participants'");
		CT04.ajouterStep("Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses.", "ASSURANCEROLE", "Les informations sont conformes et le bouton de validation des participants cliquable");
	    CT04.ajouterStep("Valider la liste des participants (clic sur bouton correspondant)", "VALIDATIONPARTICIPANTS", "Affichage de l'écran de 'Proposition' avec la grille alternative commerciale");
	    CT04.ajouterStep("Valider de l'offre contrat de crédit (clic sur le bouton 'Valider en contrat de crédit')", "VALIDATION", "Affichage de la pop up de finalisation de l'instruction ou de la page de dossier de vente");
	    CT04.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT04.getNomCasEssai() + CT04.getTime()));
	    
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
		if(typeDossier != TypeProduit.CREODIS){
			outil.attendreChargementElement(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
			outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
			}
		else { 
		outil.cliquer(Cibles.BOUTON_SUIVANT);
		}
		CT04.validerObjectif(outil.getDriver(),  "VALIDATIONPARTICIPANTS", true);
		outil.attendre(1);
		if(typeOffre == "PERMIS 1 EURO"){
			outil.attendreChargementElement(Cibles.LIBELLE_POPUP_ALERTES_);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_SIMULATION_PP);
			CT04.validerObjectif(outil.getDriver(), "VALIDATION", true);
		}
		else {
			CibleBean cibleAttenteValidationCredit = null;
			CibleBean cibleValidationCredit = null;
		
			if (typeDossier == TypeProduit.FACELIA || typeDossier == TypeProduit.IZICARTE) {
				cibleAttenteValidationCredit = Cibles.BOUTON_VALIDER_OPC_CR;
				cibleValidationCredit = Cibles.BOUTON_VALIDER_OPC_CR;
			}
			if (typeDossier == TypeProduit.CREODIS) {
				cibleAttenteValidationCredit = Cibles.ELEMENT_TABLEAU_PRELEVEMENT;
				cibleValidationCredit = Cibles.BOUTON_VALIDER_CREODIS_CR;
			}
			if (typeDossier == TypeProduit.CREDIT_AMORT) {
				cibleAttenteValidationCredit = Cibles.BOUTON_ACCES_VALIDATION_OPC;
				cibleValidationCredit = Cibles.BOUTON_ACCES_VALIDATION_OPC;
			}
			
			//Step 1 : Valider de l'offre contrat de crédit
			//Extraction du BIC et de l'IBAN du compte emprunteur CR
			if (typeDossier != TypeProduit.CREDIT_AMORT){
				scenario0.setNumeroBIC(outil.obtenirValeur(Cibles.ELEMENT_SPAN_BIC));
				scenario0.setNumeroIBAN(outil.obtenirValeur(Cibles.ELEMENT_SPAN_IBAN));
			}
			outil.attendreChargementElement(cibleAttenteValidationCredit, true, true);
			outil.attendreEtCliquer(cibleValidationCredit);
			CT04.validerObjectif(outil.getDriver(), "VALIDATION", true);
			//Step 2 : Finalisation de l'instruction : Validation de la popup pour les CR, validation de l'écran pour les PP
			//Extraction du BIC et de l'IBAN du compte emprunteur PP
			if (typeDossier == TypeProduit.CREDIT_AMORT){
				scenario0.setNumeroBIC(outil.obtenirValeur(Cibles.ELEMENT_SPAN_BIC));
				scenario0.setNumeroIBAN(outil.obtenirValeur(Cibles.ELEMENT_SPAN_IBAN));
			}
		}
		CT04.validerObjectif(outil.getDriver(), CT04.getNomCasEssai() + CT04.getTime(),true);
		scenario0.setFlag(Constantes.ETAPE_SUIVANTE_VALIDATION);
		return CT04;
	}
		
	public CasEssaiIziventeBean CT05Validation(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
		//Paramètrage du CT05
		CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
		CT05.setAlm(scenario0.getAlm());
		CT05.setNomCasEssai("CT05 -" + getTime());
		CT05.setDescriptif(Catalogue.CT05_TITRE);
		CT05.setNomTestPlan(Catalogue.CT05_TITRE);
		//Information issues du scénario.
		CT05.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT05.setCheminTestLab(scenario0.getCheminTestLab());
		CT05.setNomTestLab(scenario0.getNomTestLab());
		CT05.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des steps
		CT05.ajouterStep("Finalisation de l'instruction (clic sur le bouton 'Oui' dans la popup de finalisation de l'instruction", "VALIDATIONINSTRUCTION", "Affichage de l'écran de synthèse de l'offre de crédit");
		CT05.ajouterStep("Remplir le questionnaire pour la demande de financement à 8 jours et la réception de sollicitations commerciales partenaires (choix oui/non via boutons radio).", "OPTIONS", "Choix effectués conformément au scénario");
		if(typeDossier == TypeProduit.CREDIT_AMORT){
		CT05.ajouterStep("Vérifier les justificatifs et valider (clic bouton radio 'Vérifié' pour chaque justificatif dans la pop up de finalisation de l'instruction' et clic sur bouton 'Valider'", "VERIFICATION", "Retour sur la page de dossier de vente");
		}
		CT05.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT05.getNomCasEssai() + CT05.getTime()));

		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// FINALISATION DE L'INSTRUCTION
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		CibleBean cibleConfirmationValidationCredit = null;
		
		if (typeDossier != TypeProduit.CREDIT_AMORT) {
			cibleConfirmationValidationCredit = Cibles.BOUTON_POPUP_OUI_MAJ;
		}
		else {
			cibleConfirmationValidationCredit = Cibles.BOUTON_VALIDER_OPC;
		}
		outil.attendreChargementElement(cibleConfirmationValidationCredit);
		outil.attendreEtCliquer(cibleConfirmationValidationCredit);
		CT05.validerObjectif(outil.getDriver(), "VALIDATIONINSTRUCTION", true);
		// Pour les PP on effectue la vérification des justificatifs.
		if (typeDossier == TypeProduit.CREDIT_AMORT) {
			//Step 3 : Vérifier les justificatifs et valider
			outil.attendreChargementElement(Cibles.ELEMENT_FIN_INSTRUCTION, true, true);
			outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_VALIDER_JUSTIFICATIFS);
			CT05.validerObjectif(outil.getDriver(), "VERIFICATION", true);
		}
		//Step 4 : Remplir le questionnaire pour la demande de financement à 8 jours et la réception de sollicitations commerciales partenaires
		outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_NON_MAJ);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_NON_MAJ);
		CT05.validerObjectif(outil.getDriver(), "OPTIONS", true);
		CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
		scenario0.setFlag(Constantes.ETAPE_SUIVANTE_EDITION);
		
		return CT05;
	}

	/**
	 * Fonction générique traitant la finalisation de l'instruction d'une simulation.
	 * @param scenario0 le scénario devrivant le cas d'essai.
	 * @param outil la boite à outil.
	 * @return le cas de test à ajouter au scénario décrivant les étapes réalisées.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT06FinalisationInstruction(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
		//Paramètrage du CT06
		CasEssaiIziventeBean CT06 = new CasEssaiIziventeBean();
		CT06.setAlm(scenario0.getAlm());
		CT06.setNomCasEssai("CT06 -" + getTime());
		CT06.setDescriptif(Catalogue.CT06_TITRE);
		CT06.setNomTestPlan(Catalogue.CT06_TITRE);
		//Information issues du scénario.
		CT06.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT06.setCheminTestLab(scenario0.getCheminTestLab());
		CT06.setNomTestLab(scenario0.getNomTestLab());
		CT06.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des steps
		 if(typeDossier == TypeProduit.CREDIT_AMORT){
				CT06.ajouterStep("Imprimer la synthèse de l'offtre en pdf (clic sur bouton 'Imprimer')", "IMPRESSIONSYNTHESE", "Le fichier pdf est correctement télécharge");
		 }
		 
		CT06.ajouterStep("Imprimer la liasse de document (clic sur le bouton 'Suivant')", "IMPRESSION", "Affichage de la pop up 'Préparation contrat'");
		if(typeDossier == TypeProduit.IZICARTE){
	    CT06.ajouterStep("Choisir le mode de vente (face à face ou par téléphone) selon le scénario", "MODE", "Impression en pdf de la liasse");
	    CT06.ajouterStep("Sélectionner le compte de prélèvement et valider l'offre de crédit (clic sur bouton 'Confirmer contrat de crédit')", "CONFIRMATION", "Affichage de la pop up 'Finalisation de l'instruction'");
		}
		CT06.ajouterStep("Attendre la fin de la préparation du contrat puis cliquer sur suivant pour envoi à l'octroi", "PREPARATION", "Deconnexion d'Izivente");
			
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// FINALISATION DE L'INSTRUCTION
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		CibleBean cibleNumeroFFI = null;
		if (typeDossier != TypeProduit.CREDIT_AMORT) {
			cibleNumeroFFI = Cibles.ELEMENT_SPAN_NUMERO_FFI_CR;
		}else {
			cibleNumeroFFI = Cibles.ELEMENT_SPAN_NUMERO_FFI;
		}
		//TODO Fin des tests pour la validation
		// Pour les PP on effectue l'impression de la synthèse
		if (typeDossier == TypeProduit.CREDIT_AMORT) {
			//Step 5 : Imprimer la synthèse de l'offre
			outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE);
			CT06.validerObjectif(outil.getDriver(), "IMPRESSIONSYNTHESE", true);
		}
		// Extraction du numéro FFI depuis l'interface
		scenario0.setNumeroFFI(outil.obtenirValeur(cibleNumeroFFI));
		//Step 6 : Imprimer la liasse de document
		outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.attendrePresenceTexte("Préparation contrat");
		CT06.validerObjectif(outil.getDriver(), "IMPRESSION", true);
		
		// Dans le cas d'un IZICARTE on ne passe pas par les mêmes écrans après l'édition.
		if (typeDossier == TypeProduit.CREDIT_AMORT) {
			//Step 7 : Préparation du contrat et envoi à l'octroi
			//outil.attendre(8);
			outil.attendreChargementElement(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT);
		CT06.validerObjectif(outil.getDriver(), "PREPARATION", true);
		} else if (typeDossier == TypeProduit.IZICARTE) {
			//Step 7 : Accès à l'IHM pour reprise du dossier
			outil.attendreChargementElement(Cibles.BOUTON_POPUP_FACE_A_FACE_MAJ);
			outil.cliquer(Cibles.BOUTON_POPUP_FACE_A_FACE_MAJ);
			CT06.validerObjectif(outil.getDriver(), "MODE", true);
			//Step 8 : Fin de l'édition
			outil.attendreChargementElement(Cibles.BOUTON_PASSAGE_OCTROI_CR, true, true);
			outil.attendreChargementElement(Cibles.BOUTON_TERMINER_EDITION_CR, true, true);
			outil.cliquer(Cibles.BOUTON_TERMINER_EDITION_CR);
			CT06.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
		} else {
			//Step 7 : Préparation du contrat et envoi à l'octroi	
			outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
			outil.attendreChargementElement(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE);
			outil.attendreChargementElement(Cibles.BOUTON_POPUP_SUIVANT_FIN, true, true);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT_FIN);
			//outil.attendreChargementElement(Cibles.BOUTON_POPUP_SUIVANT, true, true);
			//outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT);
			CT06.validerObjectif(outil.getDriver(), "PREPARATION", true);
		}
		
		scenario0.setFlag(Constantes.ETAPE_SUIVANTE_MEF);
		CT06.validerObjectif(outil.getDriver(), CT06.getNomCasEssai() + CT06.getTime(),true);
		return CT06;
	}


	public CasEssaiIziventeBean CT07MiseGestion(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
		//Paramètrage du CT07
		CasEssaiIziventeBean CT07 = new CasEssaiIziventeBean();
		CT07.setAlm(scenario0.getAlm());
		CT07.setNomCasEssai("CT07 -" + getTime());
		CT07.setDescriptif(Catalogue.CT07_TITRE);
		CT07.setNomTestPlan(Catalogue.CT07_TITRE);
		//Information issues du scénario.
		CT07.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT07.setCheminTestLab(scenario0.getCheminTestLab());
		CT07.setNomTestLab(scenario0.getNomTestLab());
		CT07.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des steps
		CT07.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT07.getNomCasEssai() + CT07.getTime()));
		CT07.ajouterStep("Relancement d'Izivente et retour sur le dossier", "RETOUR", "Affichage de la page d'accueil d'Izivente avec injection du jeton");
		CT07.ajouterStep("Ouverture et du dossier et recherche du numéro FFI", "RECHERCHE", "Affichage des données dossier et client");
		CT07.ajouterStep("Passage à l'octroi et premières vérification", "OCTROI", "Dossier accepté pour l'octroi ");
		CT07.ajouterStep("Finalisation de l'octroi et dernières confirmations avant mise en gestion", "FINALISATION", "Affichage des données dossiers et client avec état FORC");
		CT07.ajouterStep("Vérification des données dossier et client", "MISENFORCE", "Dossier à l'état FORC");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////// MISE EN GESTION ////////////////////////////////////////////
		//if (typeDossier != Constantes.CREDIT_AMORT){
		//Step 1 : Rechargement de l'URL d'Izivente et réinjection du jeton
		//saisieJeton(outil, scenario0.getIdClient(), false, distributeur, null, agence, etablissement);
		CT07.validerObjectif(outil.getDriver(), "RETOUR", true);
		//Step 2 : Ouverture du dossier et recherche du numéro FFI
		if (typeDossier == TypeProduit.FACELIA || typeDossier == TypeProduit.IZICARTE) {
			outil.cliquer(Cibles.BOUTON_MENU_REPRISE_DOSSIER);
		} else if(typeDossier == TypeProduit.CREODIS) {
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FC);
		} else if(typeDossier == TypeProduit.CREDIT_AMORT) {
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
		CT07.validerObjectif(outil.getDriver(), "RECHERCHE", true);
		// Step 3 : Passage à l'octroi
		if (typeDossier == TypeProduit.CREDIT_AMORT) {
			outil.cliquer(Cibles.BOUTON_PASSAGE_OCTROI);
			outil.attendrePresenceTexte("Demande de confirmation");
		} else {
			outil.attendreChargementElement(Cibles.BOUTON_MISE_EN_FORCE_CR, true, true);
			outil.cliquer(Cibles.BOUTON_MISE_EN_FORCE_CR);
		}
		outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ, true, true);
		outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ, true, true);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
		CT07.validerObjectif(outil.getDriver(), "VERIFICATION", true);
		//Step 4 : Acceptation et finalisation de l'octroi
		outil.attendreChargementElement(Cibles.BOUTON_SUIVANT, true, true);
		outil.cliquer(Cibles.BOUTON_SUIVANT);
		outil.attendreChargementElement(Cibles.LIBELLE_ACCEPTATION);
		outil.cliquer(Cibles.LIBELLE_ACCEPTATION);
		outil.attendre(1);
		outil.attendreChargementElement(Cibles.BOUTON_FINALISATION_OCTROI_CR, true, true);
		outil.cliquer(Cibles.BOUTON_FINALISATION_OCTROI_CR);
		CT07.validerObjectif(outil.getDriver(), "OCTROI", true);
		outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		outil.attendreChargementElement(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI, true, true);
		outil.cliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
		CT07.validerObjectif(outil.getDriver(), "FINALISATION", true);
		//Step 5 : Vérification du passage à l'état FORC
		outil.attendrePresenceTexte("Liste des dossiers");
		CT07.validerObjectif(outil.getDriver(), "MISEENFORCE", true);
		scenario0.setFlag(3);
		//outil.fermerFenetreCourante();
		return CT07;
	}

	/**
	 * Cas de test relatif au murissement.
	 * @param scenario0 le scénario comportant les informations de configurations.
	 * @param outil lza boite à outil selenium
	 * @return le resultat sous forme de bean de l'éxécution.
	 */
	public CasEssaiIziventeBean CT08Murissement(CasEssaiIziventeBean scenario0, SeleniumOutils outil) {
		//Paramètrage du CT08
		CasEssaiIziventeBean CT08 = new CasEssaiIziventeBean();
		CT08.setAlm(scenario0.getAlm());
		CT08.setNomCasEssai("CT08 -" + getTime());
		CT08.setDescriptif(Catalogue.CT08_TITRE);
		CT08.setNomTestPlan(Catalogue.CT08_TITRE);
		//Information issues du scénario.
		CT08.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT08.setCheminTestLab(scenario0.getCheminTestLab());
		CT08.setNomTestLab(scenario0.getNomTestLab());
		CT08.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des objectifs
		CT08.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT08.getNomCasEssai() + CT08.getTime()));
		CT08.ajouterStep("Lancement de la fonction murissement", "Lancement de l'étape du murissement", "Fonction lancée");
		CT08.ajouterStep("Validation du murissement du dossier", "Murissement validé", "Murissement validé");
		//Lancement de la fonction de murissement d'un dossier
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String siocID = IZIVENTEOutils.derniersCaracteres(scenario0.getNumeroFFI(), 8);
		String date = sdf.format(new Date());
		Boolean retour = IZIVENTEOutils.murissement(siocID, this.distributeur, typeDossier != TypeProduit.CREDIT_AMORT, date);
		CT08.validerObjectif(outil.getDriver(), "Lancement de l'étape du murissement", true);
		if (retour){
			scenario0.setFlag(Constantes.ETAPE_SUIVANTE_MEG);
			CT08.validerObjectif(outil.getDriver(), "Murissement validé", true);
			return scenario0;
		} else {
			//TODO Gestion d'erreur non bloquante : permettrai de passer à un autre murissement
			CT08.validerObjectif(outil.getDriver(), "Murissement validé", false);
			return scenario0;
		}
		
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
		if (typeDossier != TypeProduit.CREODIS) {
	    	outil.attendreChargementElement(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
	    	outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
		}
	}
	
	/**
	 * Fonction dédiée à l'ajout d'un conjoint co emprunteur.
	 * @param outil la boite à outil selenium.
	 * @throws SeleniumException en cas d'impossibilité d'ajouter le conjoint co emprunteur.
	 */
	private void ajoutConjointCoEmprunteurUnique(SeleniumOutils outil) throws SeleniumException {
		//On clique sur le bouton "Ajouter Conjoint CoEmprunteur" si on ne veut que le conjoint coemprunteur sur le dossier.
		//Cette option n'est accessible que pour les PP et les CR IZICARTE dont l'emprunteur et le conjoint ont un compte joint
		if(typeDossier == TypeProduit.CREDIT_AMORT) {
			outil.cliquer(Cibles.BOUTON_AJOUT_CONJOINT);
		} else{
			//TODO Gérer le mode IZICARTE avec compte joint
			//outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
    		//outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
			outil.logger("On ne peut pas inscrire de co emprunteur sur un CR sauf Izicarte muni d'un compte joint");
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
			if (typeDossier != TypeProduit.CREODIS) {
				outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
				outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
				outil.attendre(2);
				switch (typeDossier) {
				case CREDIT_AMORT :
					outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ, true, true);
					outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
					//TODO Condition sur l'assurance si montant crédit supérieur à 21000 maintenant appliquée à tous les montants en recette future bouchonné.
					if (Integer.parseInt(montantCredit) > 21000 || modificateur.emprunteurSenior == true){
						outil.attendreChargementElement(Cibles.CASE_SELECTION_REPONSE_ASSURANCE_NON);
						outil.cliquerMultiple(Cibles.CASE_SELECTION_REPONSE_ASSURANCE_NON);
						outil.cliquer(Cibles.BOUTON_OUI_DES);
						outil.attendreEtCliquer(Cibles.RADIO_SELECTION_ASSURANCE_DIM);
					}
					break;
				case IZICARTE :
					//outil.attendreChargementElement(Cibles.RADIO_AVEC_ASS_CR, true, true);
					//outil.cliquer(Cibles.RADIO_AVEC_ASS_CR);
					outil.attendreChargementElement(Cibles.RADIO_AVEC_ASS_IZICARTE, true, true);	
					outil.cliquer(Cibles.RADIO_AVEC_ASS_IZICARTE);
					outil.attendreEtCliquer(Cibles.RADIO_ASSURANCE_DECES_IZICARTE_OUI);
					outil.attendreEtCliquer(Cibles.RADIO_ASSURANCE_INVALD_IZICARTE_NON);
					outil.attendreEtCliquer(Cibles.RADIO_ASSURANCE_MALA_IZICARTE_NON);
					outil.attendreEtCliquer(Cibles.RADIO_ASSURANCE_PERTE_IZICARTE_NON);
					outil.attendreChargementElement(Cibles.BOUTON_VALIDATION_ASS_CR, true, true);
					outil.cliquer(Cibles.BOUTON_VALIDATION_ASS_CR);
					break;
				case FACELIA :
					//outil.attendreChargementElement(Cibles.RADIO_AVEC_ASS_CR);
					//outil.cliquer(Cibles.RADIO_AVEC_ASS_CR);
					outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ, true, true);	
					outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
					outil.attendreEtCliquer(Cibles.RADIO_ASSURANCE_DECES_FACELIA_OUI);
					outil.attendreEtCliquer(Cibles.RADIO_ASSURANCE_INCAP_FACELIA_NON);
					outil.attendreEtCliquer(Cibles.RADIO_ASSURANCE_INVALD_FACELIA_NON);
					outil.attendreEtCliquer(Cibles.RADIO_ASSURANCE_PERTE_FACELIA_NON);
					outil.attendreChargementElement(Cibles.BOUTON_VALIDATION_ASS_CR, true, true);
					outil.cliquer(Cibles.BOUTON_VALIDATION_ASS_CR);
					break;
				}
			} else {
				//outil.attendreEtCliquer(Cibles.RADIO_SELECTION_ASS_1_CR);
				outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ, true, true);	
				outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
			}	
		} else {
			switch (typeDossier) {
				case CREDIT_AMORT :
					outil.attendre(2);
					outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
				break;
				case IZICARTE :
					outil.attendre(1);
					//outil.attendreChargementElement(Cibles.RADIO_SANS_ASS_CR);
					//outil.cliquer(Cibles.RADIO_SANS_ASS_CR);
					outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_NON_MAJ, true, true);
					outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
				break;
				case FACELIA :
					outil.attendre(2);
					//outil.attendreChargementElement(Cibles.RADIO_SANS_ASS_CR);
					//outil.cliquer(Cibles.RADIO_SANS_ASS_CR);
					outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_NON_MAJ, true, true);
					outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
				break;
				case CREODIS :
					outil.attendre(2);
					//outil.attendreEtCliquer(Cibles.RADIO_SELECTION_SANS_ASS_CR);
					outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_NON_MAJ, true, true);
					outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
				break;
				case TEOZ:
					// Le TEOZ n'est pas pris en compte.
					break;
				default:
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
			} else {
				outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
				outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			}
			// On attend que les choix d'assurance soient affichés et clicable.
			outil.attendre(1);
			outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ, true , true);
			outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_NON_MAJ, true, true);
			// On clique sur le choix d'assurance à oui ou non.
			outil.cliquer(assuranceConjointCoEmp?Cibles.LIBELLE_CHOIX_OUI_MAJ:Cibles.LIBELLE_CHOIX_NON_MAJ);	
			if (assuranceConjointCoEmp == true && (Integer.parseInt(montantCredit) > 21000)){
				outil.attendreChargementElement(Cibles.CASE_SELECTION_REPONSE_ASSURANCE_NON);
				outil.cliquerMultiple(Cibles.CASE_SELECTION_REPONSE_ASSURANCE_NON);
				outil.cliquer(Cibles.BOUTON_OUI_DES);
				outil.attendreEtCliquer(Cibles.RADIO_SELECTION_ASSURANCE_DIM);
			}
		}
	}
	
	/**
	 * Fonction générique pour le choix d'une assurance sur le tiers
	 * @param outil la boîte à outil
	 * @throws SeleniumException en cas d'erreur
	 */
	private void assuranceTiers(SeleniumOutils outil) throws SeleniumException {
		if((tiersCoEmp == true || tiersCaution == true) && (assuranceConjointCoEmp == false || assuranceEmp == false)) {
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			//TODO remplace le attendre 1 ne devrais pas être nécessaire.
			outil.attendre(1);
			
			if(assuranceTiers){
				outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
				outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);	
			} else{
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
				// Si le role du conjoint est co emp ou sélectionne "C" sinon "G"
				outil.selectionner(conjointCoEmp?"C":"G", Cibles.SELECTEUR_ROLE_PARTICIPANT);
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
		// Si le role du tiers est co emp ou sélectionne "C" sinon "G"
		outil.selectionner(tiersCoEmp?"C":"G", Cibles.SELECTEUR_ROLE_PARTICIPANT);
		}
	}
	
	/**
	 * Fonction permettant la déclaration des variables liées au scénario
	 */
	public void declarationScenario(CasEssaiIziventeBean scenario){
		//On déclare le numéro client/distributeur utilisé dans le scénario
		if (idClient != null){
			scenario.setIdClient(idClient);
		}
		//On déclare le numéro d'agence utilisé dans le scénario
		if(etablissement != null){
			scenario.setEtablissement(etablissement);
		} else {
			// Valeurs par défaut en l'absence de paramètrage
			scenario.setEtablissement(distributeur == Constantes.CAS_BP?"038":"11315");
		}
		//On déclare le numéro d'agence utilisé dans le scénario
		if (agence != null){
			scenario.setAgence(agence);
		} else {
			// Valeurs par défaut en l'absence de paramètrage
			scenario.setAgence(distributeur == Constantes.CAS_BP?"00022":"1131500030000135");
		}
	}
	
	/**
	 * Fonction permettant de récupérer une chaine de caractère (BP ou CE) en fonction du distributeur sélectionné
	 */
	private String chaineDistributeur (int casDistributeur) {
		String dist = "";
		if (casDistributeur == Constantes.CAS_CE){
			dist = "CE";
		} else {
			dist = "BP";
		}
		return dist;
	}
	
	/**
	 * Fonction permettant de récupérer une chaîne de caractère (CR ou PP) en fonction du type de dossier
	 * @param typeDossier le type de produit choisie.
	 * @return le code sur deux caractère correspondant au type de dossier.
	 */
	private String chaineProduit(TypeProduit typeDossier) {
		return typeDossier.getCode();
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
		String IUN = "";
		String typeDos = chaineProduit(this.typeDossier);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String numBIC = scenario.getNumeroBIC();
		String numIBAN = scenario.getNumeroIBAN();
		String etab = scenario.getEtablissement();
		String agce = scenario.getAgence();
		int flg = scenario.getFlag();
		String chaine = (distrib +";"+ FFI +";"+ idClnt +";"+ IUN +";"+ typeDos +";"+ flg +";"+ sdf.format(date) +";"+ numBIC +";"+ numIBAN +";"+ etab +";"+ agce);

		try {
			boolean existence = remplacer(scenario.getNumeroFFI(), chaine);
			if (!existence) {
				//TODO modifier le chemin vers le fichier, il doit être dans le properties.
				chaine = chaine + "\r\n";
				Files.write(Paths.get("src/test/DonneesClientDossier.txt"),chaine.getBytes(),StandardOpenOption.APPEND);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new SeleniumException(Erreurs.E020, "Impossible d'écrire dans DonneesClientDossier");
		} 
	}
	
	/**
	 * Initialise un cas d'essai IZIVENTE à partir d'une instance connue extraite du fichier de donnée client.
	 * @param instance la ligne issue du fichier de donnée servant à initaliser le cas d'essai IZIVENTE.
	 * @return le nouveau cas d'essai initialisé.
	 */
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
				//TODO simplifier ce test, l'esporté dans la classe type de produit ??
				switch (instanceDecoupee[4]) {
					case "PP" :
						typeDossier = TypeProduit.CREDIT_AMORT;
					break;
					case "CR" :
						typeDossier = TypeProduit.CREODIS;
					break;
					case "FA" :
						typeDossier = TypeProduit.FACELIA;
					break;
					case "IZ" :
						typeDossier = TypeProduit.IZICARTE;
				}
				scenario.setFlag(Integer.parseInt(instanceDecoupee[5]));
				scenario.setNumeroBIC(instanceDecoupee[7]);
				scenario.setNumeroIBAN(instanceDecoupee[8]);
				scenario.setEtablissement(instanceDecoupee[9]);
				scenario.setAgence(instanceDecoupee[10]);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scenario;
	}
	
	/**
	 * Fonction réalisant l'étape de simulation.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public void simulation() throws SeleniumException {
		this.simulation = true;
		this.validation = false;
		this.edition = false;
		this.miseEnGestion = false;
		this.murissement = false;
		
		// Lancement la simulation.
		CasEssaiIziventeBean simulationSimu = this.lancement();
		this.ecritureFichierDonnees(simulationSimu, new Date());
	}
	/**
	 * Fonction réalisant l'étape de validation.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public void validation() throws SeleniumException {
		this.simulation = false;
		this.validation = true;
		this.edition = false;
		this.miseEnGestion = false;
		this.murissement = false;
		
		// Lancement la simulation.
		CasEssaiIziventeBean simulationVali = this.lancement();
		this.ecritureFichierDonnees(simulationVali, new Date());
	}
	
	/**
	 * Fonction réalisant l'étape de mise à l'état EDIT.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public void miseAEdit() throws SeleniumException {
		// Déclarer une instance de test IZIVENTE
		//TNRSC00 generateurSimu = new TNRSC00();
		
		// Configurer le générateur :
//		generateurSimu.setAlm(false);
//		generateurSimu.distributeur = Constantes.CAS_CE;
//		generateurSimu.typeDossier = Constantes.IZICARTE;
//		generateurSimu.edition = true;
//		generateurSimu.miseEnGestion = false;
//		generateurSimu.aucunCoEmp = true;
//		generateurSimu.conjointCoEmp = false;
//		generateurSimu.tiersCoEmp = false;
//		generateurSimu.assuranceEmp = false;
//		generateurSimu.assuranceTiers = false;
		
		this.simulation = false;
		this.validation = false;
		this.edition = true;
		this.miseEnGestion = false;
		this.murissement = false;
		
		// Lancement la simulation.
		CasEssaiIziventeBean simulationEdit = this.lancement();
		this.ecritureFichierDonnees(simulationEdit, new Date());
	}
	
	/**
	 * Fonction permettant la mise en force de dossier identifié dans le fichier de donnée comme devant être mis en force.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public void miseEnForce() throws SeleniumException {
		// Déclarer une instance de test IZIVENTE
		//TNRSC00 generateurSimu = new TNRSC00();
		// Mettre en gestion une instance de test IZIVENTE
		this.simulation = false;
		this.validation = false;
		this.edition = false;
		this.miseEnGestion = true;
		this.murissement = false;
		
		// On récupère le contenu du fichier de donnée.
		List<String> listeInstances = this.renvoyerContenuFichierDonnee(Constantes.ETAPE_SUIVANTE_MEF);
		
		for (String instance : listeInstances) {
			// On initialise le scénario avec les données de l'instance
			CasEssaiIziventeBean simulationEdit = new CasEssaiIziventeBean();
			simulationEdit = this.initialiserScenario(instance);
			
			if (simulationEdit != null) {
				// Reprise de la simulation.
				CasEssaiIziventeBean simulationForc = this.lancement(simulationEdit);
				this.ecritureFichierDonnees(simulationForc, new Date());
			}
		}
	}
	
	/**
	 * Effectue le murissement de dossiers présents dans le fichier de donnée à l'étape de murissement.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public void murissement(SeleniumOutils outil) throws SeleniumException {
		this.simulation = false;
		this.validation = false;
		this.edition = false;
		this.miseEnGestion = false;
		this.murissement = true;
		List<String> listeInstances = this.renvoyerContenuFichierDonnee(Constantes.ETAPE_SUIVANTE_MURIR);
		
		for (String instance : listeInstances) {
			// On initialise le scénario avec les données de l'instance
			CasEssaiIziventeBean simulationForc = new CasEssaiIziventeBean();
			simulationForc = this.initialiserScenario(instance);
			
			if (simulationForc != null) {
				//Reprise du dossier pour murissement
				//CasEssaiIziventeBean simulationMuri = this.lancement(simulationForc);
				GregorianCalendar calendar = new GregorianCalendar();
				if (calendar.get(Calendar.DAY_OF_WEEK) >= Calendar.FRIDAY) {
					calendar.add(Calendar.DAY_OF_YEAR, 4);
				} else {
					calendar.add(Calendar.DAY_OF_YEAR, 2);
				}
				
				this.ecritureFichierDonnees(CT08Murissement(simulationForc, outil), calendar.getTime());
			}
			
		}
	}
	
	public void consultationIZIGATE(CasEssaiIziventeBean scenario) throws SeleniumException {
		//SCConsultation consultation = SCConsultation();
		//this.numeroFFI = scenario.getNumeroFFI();

	}
}
