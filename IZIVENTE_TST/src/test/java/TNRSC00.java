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

import main.bean.CasEssaiIzigateBean;
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
 * Scenario 00 des tests automatises pour IZIVENTE - 11/2016
 * Editique 
 * @author levieilfa bardouma
 * @param <SCConsultation>
 */
public class TNRSC00 extends SC00Test {

	//Definir le distributeur Constantes.CAS_CE pour CE/Constantes.CAS_BP pour BP
	int distributeur;
	//Definir le type de dossier FACELIA/CREODIS/IZICARTE/CREDIT_AMORT
	//int typeDossier = Constantes.CREDIT_AMORT;
	TypeProduit typeDossier = TypeProduit.CREDIT_AMORT;
	//Definir le numero de client/distributeur
	String idClient = null;
	//Definir l'etablissement et l'agence (1871500030000302) - La valeur null rend des valeurs par defauts qui fonctionnent pour la plupart de nos scenarios
	String etablissement = null;
	String agence = null;
	Boolean producteur = false;
	//Definir l'univers, l'offre et le type d'objet de financement (echelonne, differe, immediat).
	String typeUnivers = null;
	String typeOffre = null;
	String typeObjet = null;
	//Definir le cout et la mensualite du credit
	String coutProjet = null;
	String mensualite = null;
	String montantCredit = null;
	String dureeDiffere = null;
	String situationDeVente = null;
	//Definir l'absence ou la presence de coemprunteur et leurs rôles.
	Boolean aucunCoEmp = false;
	Boolean conjointCoEmp = false;
	Boolean conjointCaution = false;
	Boolean tiersCoEmp = false;
	Boolean tiersCaution = false;
	//Renseigner le numero de personne physique pour le coemprunteur tiers (BP : 9500855 P1E CE : 942500400).
	String numPersPhysTiers = "9500855";
	//Definir la presence d'assurance pour les emprunteurs (true = oui /false = non).
	Boolean assuranceEmp = false;
	Boolean assuranceConjointCoEmp = false;
	Boolean assuranceTiers = false;
	//Definir l'etat de fin de saisie (EDIT = false ; FORCE = true)
	public Boolean simulation = false;
	public Boolean validation = false;
	public Boolean edition = false;
	Boolean miseEnGestion = false;
	Boolean murissement = false;
	ModificateurBouchon modificateur = new ModificateurBouchon();

	/**
	 * Id de serialisation par defaut.
	 */
	private static final long serialVersionUID = 1L;

	///**
	// * Fonction de lancement par defaut ne comportant aucun parametre.
	// * @throws SeleniumException en cas d'erreur.
	// */
	//@Test
	//public void accesIzivente() throws SeleniumException {
	//	lancement();
	//}

	/**
	 * Fonction qui effectue le lancement du scenario a partir des informations contenue dans l'objet TNRSC00 et le scenario parametre.
	 * @return l'objet cas d'essai contenant les donnees de test en sortie (ex : Numero du client)
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean lancement() throws SeleniumException {
		return lancement(null);
	}

	/**
	 * Permet d'obtenir la boite a outil Selenium associe a un driver pour le scenario donne.
	 * @param scenario0 le scenario concerne.
	 * @return la boite a outil Selenium associee au scenario.
	 */
	public SeleniumOutils obtenirDriver(CasEssaiIziventeBean scenario0) {
		//Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		//On declare les variables relatives au scenario (numero client/distributeur etc.)
		declarationScenario(scenario0);
		//Creation et configuration du repertoire de telechargement
		//File repertoireTelechargement = new File(".\\" + scenario0.getNomCasEssai());
		//repertoireTelechargement.mkdir();
		//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		String repertoire = creerRepertoireTelechargement(scenario0, profile);
		scenario0.setRepertoireTelechargement(repertoire);
		// Initialisation du driver
		//FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		FirefoxImpl driver = new FirefoxImpl(profile);
		
		if (scenario0.getDistributeur() == Constantes.CAS_CE){
			driver.get(Constantes.URL_CE_FUTURE_REROUTAGE);
		} else {
			driver.get(Constantes.URL_BP_FUTURE_REROUTAGE);
		}
		
	    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
	    outil.setRepertoireRacine(scenario0.getRepertoireTelechargement());
		
		return outil;
	}

	/**
	 * Fonction qui effectue le lancement du scenario a partir des informations contenue dans l'objet TNRSC00 et le scenario parametre.
	 * @param scenario0 le scenario parametre dont on recupere des informations si il y a lieue, null sinon.
	 * @return l'objet cas d'essai contenant les donnees de test en sortie (ex : Numero du client)
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean lancement(CasEssaiIziventeBean scenario0) throws SeleniumException {
		
		//Description du scenario
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
			scenario0.setIdUniqueTestPlan(this.getIdUniqueTestPlan());
			scenario0.setDescriptif(this.getDescriptif());
			scenario0.setNomTestLab(this.getNomTestLab());
			scenario0.setCheminTestLab(this.getCheminTestLab());
			// LISTE DES OBJECTIFS DU CAS DE TEST
			scenario0.ajouterObjectif(new ObjectifBean("Test arrive a terme", scenario0.getNomCasEssai() + scenario0.getTime()));
		}
		SeleniumOutils outil = obtenirDriver(scenario0);
	    
	    try {
	    	//TODO prévoir le cas d'une erreur pour signifier le failed
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
			//Condition pour acceder au cas de test de mise en force
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
	 * @return le cas d'essai documente pour ajout au scenario.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT01Initialisation(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
		//Parametrage du CT01
		CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
		CT01.setAlm(scenario0.getAlm());
		CT01.setNomCasEssai("CT01 -" + getTime());
		CT01.setDescriptif(Catalogue.CT01_TITRE);
		CT01.setNomTestPlan(Catalogue.CT01_TITRE);
		//Information issues du scenario.
		CT01.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT01.setCheminTestLab(scenario0.getCheminTestLab());
		CT01.setIdUniqueTestPlan(78516);
		CT01.setNomTestLab(scenario0.getNomTestLab());
		CT01.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());	
		//Gestion des steps
		CT01.ajouterObjectif(new ObjectifBean("Test arrive a terme", CT01.getNomCasEssai() + CT01.getTime()));
		CT01.ajouterStep("Generation du bouchon client Izivente", "GENERATION", "Creation du bouchon terminee");
		CT01.ajouterStep("Lancer l URL Izivente en fonction du reseau teste", "ACCESREROUTAGE", "Affichage de l ecran de reroutage");
		CT01.ajouterStep("Recuperer numero de bouchon genere et injecter le jeton (copier/coller du code dans la zone de l'ecran de reroutage)", "INJECTION", "Jeton colle dans la zone appropriee");
		CT01.ajouterStep("Valider le jeton et lancer le reroutage (clic sur bouton 'Reroutage')", "ACCESIZIVENTE", "Affichage d'Izivente (ecran d'instruction ou pop up de mode de vente");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES IZIVENTE ET INITIALISATION
		/////////////////////////////////////////////////////////////////////////////////////////////////////
	
		//Steps 1,2,3,4 : Generation du bouchon - Acces a l'ecran de reroutage et injection du jeton - Acces a Izivente
		String idClient = saisieJeton(outil, scenario0.getIdClient(), producteur, scenario0.getDistributeur(), modificateur, scenario0.getAgence(), scenario0.getEtablissement());
		scenario0.setIdClient(idClient);
		CT01.validerObjectif(outil.getDriver(), "GENERATION", true);
		CT01.validerObjectif(outil.getDriver(), "ACCESREROUTAGE", true);
		CT01.validerObjectif(outil.getDriver(), "INJECTION", true);
		CT01.validerObjectif(outil.getDriver(), "ACCESIZIVENTE", true);
		CT01.validerObjectif(outil.getDriver(), CT01.getNomCasEssai() + CT01.getTime(),true);
		return CT01;
	}

	public CasEssaiIziventeBean CT02OuvertureDossier(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
		//Parametrage du CT02
		CasEssaiIziventeBean CT02 = new CasEssaiIziventeBean();
		CT02.setAlm(scenario0.getAlm());
		CT02.setNomCasEssai("CT02 -" + getTime());
		CT02.setDescriptif(Catalogue.CT02_TITRE);
		CT02.setNomTestPlan(Catalogue.CT02_TITRE);	
		//Information issues du scenario.
		CT02.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT02.setIdUniqueTestPlan(78517);
		CT02.setCheminTestLab(scenario0.getCheminTestLab());
		CT02.setNomTestLab(scenario0.getNomTestLab());
		CT02.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des steps
		CT02.ajouterObjectif(new ObjectifBean("Test arrive a terme", CT02.getNomCasEssai() + CT02.getTime()));
		CT02.ajouterStep("Choisir le mode de vente (Face a face ou Vente a distance)", "MODE", "Affichage de l'ecran d'instruction");
		CT02.ajouterStep("Selectionner l'option d'ouverture d'un dossier correspondant au type voulu.", "OUVERTURE", "Affichage des pop ups de confirmation");
		CT02.ajouterStep("Fermeture des pop ups confirmant l'ouverture du dossier", "CONFIRMATION", "Affichage de l'ecran de donnees client et de liste des dossiers");
		CT02.ajouterStep("Verifier la coherence des donnees du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'.", "SUIVANT", "Affichage de la pop un de Synthese des informations client");
		CT02.ajouterStep("Valider les donnees client en cliquant sur le bouton 'Valider' dans la pop up de synthese des information client.", "VALIDATIONDONNEESCLIENT", "Affichage de l'ecran de demande de credit");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// OUVERTURE DU DOSSIER
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		//Step 1 : Choisir le mode de vente (FaF ou VaD) - Cette etape est optionelle, le "Face a face" ne sera pas toujours visible   
		outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
		CT02.validerObjectif(outil.getDriver(), "MODE", true);
		//Step 2 : Selectionner l'option d'ouverture d'un dossier correspondant au type voulu. 
		switch(typeDossier){
			case CREDIT_AMORT:
				outil.attendreEtCliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
			break;
			case FACELIA : 
				outil.attendreEtCliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FACELIA);
				//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
				outil.attendrePresenceTexte("INFORMATION");
				outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
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
		if(scenario0.getDistributeur() == Constantes.CAS_CE){
			outil.attendrePresenceTexte("Attention");
		}
		else {
			outil.attendrePresenceTexte("ATTENTION");
		}
		CT02.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
		// Cette etape n'as lieu que si le dossier est deja porteur d'un autre dossier
		outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
		outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
		//Step 4 : Verifier la coherence des donnees du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'
		outil.attendreChargementElement(Cibles.BOUTON_RAFRAICHISSEMENT_INFOS_CLIENT, true, true);
		outil.cliquer(Cibles.BOUTON_RAFRAICHISSEMENT_INFOS_CLIENT);
		outil.attendre(1);
		outil.attendreChargementElement(Cibles.BOUTON_SUIVANT_CLIENT, true, true);
		outil.cliquer(Cibles.BOUTON_SUIVANT_CLIENT);
		//outil.attendrePresenceTexte("Synthese");
		CT02.validerObjectif(outil.getDriver(),  "SUIVANT", true);
		outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
		CT02.validerObjectif(outil.getDriver(), "VALIDATIONDONNEESCLIENT", true);
		CT02.validerObjectif(outil.getDriver(), CT02.getNomCasEssai() + CT02.getTime(),true);
		return CT02;
	}
	
	public CasEssaiIziventeBean CT03SaisieDossier(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
		//Parametrage du CT03
		CasEssaiIziventeBean CT03 = new CasEssaiIziventeBean();
		CT03.setAlm(scenario0.getAlm());
		CT03.setNomCasEssai("CT03 -" + getTime());
		CT03.setDescriptif(Catalogue.CT03_TITRE);
		CT03.setNomTestPlan(Catalogue.CT03_TITRE);
		//Information issues du scenario.
		CT03.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT03.setIdUniqueTestPlan(78518);
		CT03.setCheminTestLab(scenario0.getCheminTestLab());
		CT03.setNomTestLab(scenario0.getNomTestLab());
		CT03.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des steps
		CT03.ajouterObjectif(new ObjectifBean("Test arrive a terme", CT03.getNomCasEssai() + CT03.getTime()));
		CT03.ajouterStep("Selectionner l'offre desiree dans le menu deroulant selon le scenario", "OFFRE", "Offre selectionnee");
		CT03.ajouterStep("Selectionner et saisir les parametres liees au scenario (ex : CMA, differe, mensualite, etc.)", "PARAMETRES", "Parametres coherents avec le scenario");
		CT03.ajouterStep("Cliquer sur le bouton 'Suivant' pour valider les informations du dossier", "SAISIEDOSSIER", "Affichage de l'ecran de selection des participants");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// SAISIE DU DOSSIER
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		//Step 1 : Selectionner l'offre desiree dans le menu deroulant selon le scenario
		scenario0.setFlag(0);
		switch(this.typeDossier){
			case FACELIA : 
				//outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT_CR, true, true);
				//outil.selectionner("FACELIA", Cibles.SELECTEUR_OFFRE_CREDIT_CR, true);
				outil.attendre(2);
				CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
				//Step 2 : Selectionner et saisir les parametres liees au scenario (ex : CMA, differe, mensualite, etc.)
				outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
				outil.attendreChargementElement(Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR, true, true);
				outil.viderEtSaisir(montantCredit,  Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
				outil.attendreChargementElement(Cibles.SAISIE_MENSUALITE_CR, true, true); 
				outil.viderEtSaisir(mensualite, Cibles.SAISIE_MENSUALITE_CR);
				outil.selectionner(situationDeVente, Cibles.SELECTEUR_SITUATION_VENTE_CR, false);
			break;
			case CREODIS :
				outil.attendrePresenceTexte("INFORMATIONS DU CREDIT");
				CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
				//Step 2 : Selectionner et saisir les parametres liees au scenario (ex : CMA, differe, mensualite, etc.)
				outil.attendre(1);
				outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
				outil.selectionner(situationDeVente, Cibles.SELECTEUR_SITUATION_VENTE_CR, false);
				outil.attendreChargementElement(Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
				outil.viderEtSaisir(montantCredit, Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
				outil.viderEtSaisir(mensualite, Cibles.SAISIE_MENSUALITE_CR);
			break;
			case IZICARTE : 
				outil.attendre(2);
				CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
				//Step 2 : Selectionner et saisir les parametres liees au scenario (ex : CMA, differe, mensualite, etc.
				outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
				outil.selectionner(situationDeVente, Cibles.SELECTEUR_SITUATION_VENTE_CR, false);
				outil.attendreChargementElement(Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR, true, true);
				outil.viderEtSaisir(montantCredit,  Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
				outil.attendreChargementElement(Cibles.SAISIE_MENSUALITE_CR, true, true); 
				outil.viderEtSaisir(mensualite, Cibles.SAISIE_MENSUALITE_CR);
							
				//outil.attendrePresenceTexte("Informations du credit");
				//CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
				//Step 2 : Selectionner et saisir les parametres liees au scenario (ex : CMA, differe, mensualite, etc.)
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
				//Step 2 : Selectionner et saisir les parametres liees au scenario (ex : CMA, differe, mensualite, etc.)
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
		
		// A ce moment , si une alerte FICP est levee, elle apparais :
		if (outil.testerPresenceTexte("Resultat FICP", true)) {
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
			logger("Une erreur FICP a ete rencontree");
		}
		
		CT03.validerObjectif(outil.getDriver(), "SAISIEDOSSIER", true);
		CT03.validerObjectif(outil.getDriver(), CT03.getNomCasEssai() + CT03.getTime(),true);
		
		return CT03;
	}
	
	public CasEssaiIziventeBean CT04Participants(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
		//Parametrage du CT04
		CasEssaiIziventeBean CT04 = new CasEssaiIziventeBean();
		CT04.setAlm(scenario0.getAlm());
		CT04.setNomCasEssai("CT04 -" + getTime());
		CT04.setDescriptif(Catalogue.CT04_TITRE);
		CT04.setNomTestPlan(Catalogue.CT04_TITRE);
		//Information issues du scenario.
		CT04.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT04.setIdUniqueTestPlan(78519);
		CT04.setCheminTestLab(scenario0.getCheminTestLab());
		CT04.setNomTestLab(scenario0.getNomTestLab());
		CT04.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des steps
		CT04.ajouterObjectif(new ObjectifBean("Test arrive a terme", CT04.getNomCasEssai() + CT04.getTime()));
		CT04.ajouterStep("Choisir les participants en fonction de la fiche de prêt et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le numero de personne physique, cliquer sur rechercher, verifier la coherence des donnees du tiers  et  valider les donnees du tiers. ", "PARTICIPANTS", "Affichage de l'ecran 'Synthese des participants'");
		CT04.ajouterStep("Pour chaque participant, choisir le rôle et l'assurance en fonction des hypotheses.", "ASSURANCEROLE", "Les informations sont conformes et le bouton de validation des participants cliquable");
	    CT04.ajouterStep("Valider la liste des participants (clic sur bouton correspondant)", "VALIDATIONPARTICIPANTS", "Affichage de l'ecran de 'Proposition' avec la grille alternative commerciale");
	    CT04.ajouterStep("Valider de l'offre contrat de credit (clic sur le bouton 'Valider en contrat de credit')", "VALIDATION", "Affichage de la pop up de finalisation de l'instruction ou de la page de dossier de vente");
	    CT04.ajouterObjectif(new ObjectifBean("Test arrive a terme", CT04.getNomCasEssai() + CT04.getTime()));
	    
	    /////////////////////////////////////////////////////////////////////////////////////////////////////
		// PARTICIPANTS
		/////////////////////////////////////////////////////////////////////////////////////////////////////		
		//Step 1 : Choisir les participants en fonction de la fiche de prêt et Valider. Aucun co-emprunteur dans ce scenario
	    
	    if (aucunCoEmp == true) {
	    	aucunCoEmprunteur(outil);
	    }
	    else if(conjointCoEmp == true && conjointCaution == false && tiersCoEmp == false && tiersCaution == false) {
	    	ajoutConjointCoEmprunteurUnique(outil);
	    }
	    else if(tiersCoEmp == true || tiersCaution == true){
	    	ajoutTiers(scenario0, outil);
	    }
	    
	    //Definition du rôle du tiers
	  	roleTiers(outil);
	    //Definition du rôle du conjoint
	  	roleConjoint(scenario0, outil);
		CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
		//Step 2 : Pour chaque participant, choisir le rôle et l'assurance en fonction des hypotheses. Valider la listes des participants et confirmer l'assurance
		//Assurance de l'emprunteur
		assuranceEmprunteur(outil);
		//Assurance du conjoint
		assuranceConjoint(outil);
		//Assurance du tiers
		assuranceTiers(outil);
		CT04.validerObjectif(outil.getDriver(), "ASSURANCEROLE", true);
		//Step 3 : Valider la liste des participants (etape differente pour une CREODIS)
		if(typeDossier != TypeProduit.CREODIS){
			//outil.attendreChargementElement(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT, true, true);
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
		} else { 
			//outil.attendreChargementElement(Cibles.BOUTON_SUIVANT, true, true);
			outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
		}
		CT04.validerObjectif(outil.getDriver(),  "VALIDATIONPARTICIPANTS", true);
		//TODO Trouver un moyen de temporiser la validation des participants.
		
		outil.attendre(1);
		if("PERMIS 1 EURO".equals(typeOffre)){
			outil.attendreChargementElement(Cibles.LIBELLE_POPUP_ALERTES_);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
			outil.attendrePresenceTexte("Liste des dossiers");
			outil.attendre(1);
			outil.attendreChargementElement(Cibles.BOUTON_VALIDER_SIMULATION_PP, true, true);
			outil.cliquer(Cibles.BOUTON_VALIDER_SIMULATION_PP);
			CT04.validerObjectif(outil.getDriver(), "VALIDATION", true);
		} else {
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
			
			//Step 1 : Valider de l'offre contrat de credit
			//Extraction du BIC et de l'IBAN du compte emprunteur CR
			if (typeDossier != TypeProduit.CREDIT_AMORT){
				outil.attendreChargementElement(Cibles.ELEMENT_SPAN_BIC, true, true);			
				scenario0.setNumeroBIC(outil.obtenirValeur(Cibles.ELEMENT_SPAN_BIC));
				scenario0.setNumeroIBAN(outil.obtenirValeur(Cibles.ELEMENT_SPAN_IBAN));
			}
			outil.attendreChargementElement(cibleAttenteValidationCredit);
			if (!outil.testerVisibiliteElementDiffere(cibleValidationCredit)) {
				CT04.validerObjectif(outil.getDriver(), "VALIDATION", false);
				throw new SeleniumException(Erreurs.E009, "Impossible de valider en contrat de crédit.");
			}
			outil.cliquer(cibleValidationCredit);
			CT04.validerObjectif(outil.getDriver(), "VALIDATION", true);
			//Step 2 : Finalisation de l'instruction : Validation de la popup pour les CR, validation de l'ecran pour les PP
			//Extraction du BIC et de l'IBAN du compte emprunteur PP
			if (typeDossier == TypeProduit.CREDIT_AMORT){
				outil.attendreChargementElement(Cibles.ELEMENT_SPAN_BIC, true, true);	
				scenario0.setNumeroBIC(outil.obtenirValeur(Cibles.ELEMENT_SPAN_BIC));
				scenario0.setNumeroIBAN(outil.obtenirValeur(Cibles.ELEMENT_SPAN_IBAN));
			}
		}
		CT04.validerObjectif(outil.getDriver(), CT04.getNomCasEssai() + CT04.getTime(),true);
		scenario0.setFlag(Constantes.ETAPE_SUIVANTE_VALIDATION);
		return CT04;
	}
		
	public CasEssaiIziventeBean CT05Validation(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
		//Parametrage du CT05
		CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
		CT05.setAlm(scenario0.getAlm());
		CT05.setNomCasEssai("CT05 -" + getTime());
		CT05.setDescriptif(Catalogue.CT05_TITRE);
		CT05.setNomTestPlan(Catalogue.CT05_TITRE);
		//Information issues du scenario.
		CT05.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT05.setIdUniqueTestPlan(78520);
		CT05.setCheminTestLab(scenario0.getCheminTestLab());
		CT05.setNomTestLab(scenario0.getNomTestLab());
		CT05.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des steps
		CT05.ajouterStep("Finalisation de l'instruction (clic sur le bouton 'Oui' dans la popup de finalisation de l'instruction", "VALIDATIONINSTRUCTION", "Affichage de l'ecran de synthese de l'offre de credit");
		CT05.ajouterStep("Remplir le questionnaire pour la demande de financement a 8 jours et la reception de sollicitations commerciales partenaires (choix oui/non via boutons radio).", "OPTIONS", "Choix effectues conformement au scenario");
		if(typeDossier == TypeProduit.CREDIT_AMORT){
		CT05.ajouterStep("Verifier les justificatifs et valider (clic bouton radio 'Verifie' pour chaque justificatif dans la pop up de finalisation de l'instruction' et clic sur bouton 'Valider'", "VERIFICATION", "Retour sur la page de dossier de vente");
		}
		CT05.ajouterObjectif(new ObjectifBean("Test arrive a terme", CT05.getNomCasEssai() + CT05.getTime()));

		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// FINALISATION DE L'INSTRUCTION
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		CibleBean cibleConfirmationValidationCredit = null;
		
		if (typeDossier != TypeProduit.CREDIT_AMORT) {
			cibleConfirmationValidationCredit = Cibles.BOUTON_POPUP_OUI_MAJ;
		} else {
			cibleConfirmationValidationCredit = Cibles.BOUTON_VALIDER_OPC;
		}
		outil.attendreChargementElement(cibleConfirmationValidationCredit);
		outil.attendreEtCliquer(cibleConfirmationValidationCredit);
		CT05.validerObjectif(outil.getDriver(), "VALIDATIONINSTRUCTION", true);
		// Pour les PP on effectue la verification des justificatifs.
		if (typeDossier == TypeProduit.CREDIT_AMORT) {
			//Step 3 : Verifier les justificatifs et valider
			outil.attendreChargementElement(Cibles.ELEMENT_FIN_INSTRUCTION, true, true);
			outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_VALIDER_JUSTIFICATIFS);
			CT05.validerObjectif(outil.getDriver(), "VERIFICATION", true);
		}
		//Step 4 : Remplir le questionnaire pour la demande de financement a 8 jours et la reception de sollicitations commerciales partenaires
		if (!outil.testerVisibiliteElementDiffere(Cibles.LIBELLE_CHOIX_NON_MAJ)) {
			CT05.validerObjectif(outil.getDriver(), "OPTIONS", false);
			throw new SeleniumException(Erreurs.E009, "Impossible de valorisation les options à non. Sont elles affichées ?");
		}
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_NON_MAJ);
		CT05.validerObjectif(outil.getDriver(), "OPTIONS", true);
		CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
		scenario0.setFlag(Constantes.ETAPE_SUIVANTE_EDITION);
		
		return CT05;
	}

	/**
	 * Fonction generique traitant la finalisation de l'instruction d'une simulation.
	 * @param scenario0 le scenario devrivant le cas d'essai.
	 * @param outil la boite a outil.
	 * @return le cas de test a ajouter au scenario decrivant les etapes realisees.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT06FinalisationInstruction(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
		//Parametrage du CT06
		CasEssaiIziventeBean CT06 = new CasEssaiIziventeBean();
		CT06.setAlm(scenario0.getAlm());
		CT06.setNomCasEssai("CT06 -" + getTime());
		CT06.setDescriptif(Catalogue.CT06_TITRE);
		CT06.setNomTestPlan(Catalogue.CT06_TITRE);
		//Information issues du scenario.
		CT06.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT06.setIdUniqueTestPlan(78876);
		CT06.setCheminTestLab(scenario0.getCheminTestLab());
		CT06.setNomTestLab(scenario0.getNomTestLab());
		CT06.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des steps
		 if(typeDossier == TypeProduit.CREDIT_AMORT){
				CT06.ajouterStep("Imprimer la synthese de l'offtre en pdf (clic sur bouton 'Imprimer')", "IMPRESSIONSYNTHESE", "Le fichier pdf est correctement telecharge");
		 }	 
		CT06.ajouterStep("Imprimer la liasse de document (clic sur le bouton 'Suivant')", "IMPRESSION", "Affichage de la pop up 'Preparation contrat'");
		if(typeDossier == TypeProduit.IZICARTE){
			CT06.ajouterStep("Choisir le mode de vente (face a face ou par telephone) selon le scenario", "MODE", "Impression en pdf de la liasse");
			CT06.ajouterStep("Selectionner le compte de prelevement et valider l'offre de credit (clic sur bouton 'Confirmer contrat de credit')", "CONFIRMATION", "Affichage de la pop up 'Finalisation de l'instruction'");
		}
		CT06.ajouterStep("Attendre la fin de la preparation du contrat puis cliquer sur suivant pour envoi a l'octroi", "PREPARATION", "Deconnexion d'Izivente");
			
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// FINALISATION DE L'INSTRUCTION
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		CibleBean cibleNumeroFFI = null;
		if (typeDossier != TypeProduit.CREDIT_AMORT) {
			cibleNumeroFFI = Cibles.ELEMENT_SPAN_NUMERO_FFI_CR;
		}else {
			cibleNumeroFFI = Cibles.ELEMENT_SPAN_NUMERO_FFI;
		}
		
		// Pour les PP on effectue l'impression de la synthese
		if (typeDossier == TypeProduit.CREDIT_AMORT) {
			//Step 5 : Imprimer la synthese de l'offre
			outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE);
			CT06.validerObjectif(outil.getDriver(), "IMPRESSIONSYNTHESE", true);
		}
		// Extraction du numero FFI depuis l'interface
		scenario0.setNumeroFFI(outil.obtenirValeur(cibleNumeroFFI));
		//Step 6 : Imprimer la liasse de document
		outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.attendrePresenceTexte("Préparation contrat");
		CT06.validerObjectif(outil.getDriver(), "IMPRESSION", true);
		outil.attendre(2);
		// Dans le cas d'un IZICARTE on ne passe pas par les mêmes ecrans apres l'edition.
		if (typeDossier == TypeProduit.CREDIT_AMORT) {
			//Step 7 : Preparation du contrat et envoi a l'octroi
			outil.attendrePresenceTexte("Traitement terminé", 30);
			if (!outil.testerPresenceElementDiffere(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE)) {
				CT06.validerObjectif(outil.getDriver(), "PREPARATION", false);
				throw new SeleniumException(Erreurs.E009, "L'impression via la signature electronique à pris trop de temps, la barre de chargmement n'est pas arrivée à terme.");
			}
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT);
			CT06.validerObjectif(outil.getDriver(), "PREPARATION", true);
		} else if (typeDossier == TypeProduit.IZICARTE) {
			//Step 7 : Acces a l'IHM pour reprise du dossier
			outil.attendreChargementElement(Cibles.BOUTON_POPUP_FACE_A_FACE_MAJ);
			outil.cliquer(Cibles.BOUTON_POPUP_FACE_A_FACE_MAJ);
			CT06.validerObjectif(outil.getDriver(), "MODE", true);
			//Step 8 : Fin de l'edition
			//outil.attendreChargementElement(Cibles.BOUTON_PASSAGE_OCTROI_CR, true, true);
			//outil.attendreChargementElement(Cibles.BOUTON_TERMINER_EDITION_CR, true, true);
			outil.attendreEtCliquer(Cibles.BOUTON_TERMINER_EDITION_CR);
			CT06.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
		} else {
			//Step 7 : Preparation du contrat et envoi a l'octroi	
			outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
			if(!outil.testerPresenceElementDiffere(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE) || !outil.testerVisibiliteElementDiffere(Cibles.BOUTON_POPUP_SUIVANT_FIN)) {
				CT06.validerObjectif(outil.getDriver(), "PREPARATION", false);
				throw new SeleniumException(Erreurs.E009, "L'impression de la signature electronique à pris trop de temps, la barre de chargmement n'est pas arrivée à terme.");
			}
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT_FIN);
			CT06.validerObjectif(outil.getDriver(), "PREPARATION", true);
		}
		
		scenario0.setFlag(Constantes.ETAPE_SUIVANTE_MEF);
		CT06.validerObjectif(outil.getDriver(), CT06.getNomCasEssai() + CT06.getTime(),true);
		return CT06;
	}

	/**
	 * Ce cas de test effectue la mise en gestion du dossier décrit dans les paramètre.
	 * @param scenario0 le scénario complet.
	 * @param outil la boite à outil.
	 * @return le cas d'essai de mise en gestion à ajouter au scénario complet.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT07MiseGestion(CasEssaiIziventeBean scenario0, SeleniumOutils outil) throws SeleniumException {
		//Parametrage du CT07
		CasEssaiIziventeBean CT07 = new CasEssaiIziventeBean();
		CT07.setAlm(scenario0.getAlm());
		CT07.setNomCasEssai("CT07 -" + getTime());
		CT07.setDescriptif(Catalogue.CT07_TITRE);
		CT07.setNomTestPlan(Catalogue.CT07_TITRE);
		//Information issues du scenario.
		CT07.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT07.setIdUniqueTestPlan(78877);
		CT07.setCheminTestLab(scenario0.getCheminTestLab());
		CT07.setNomTestLab(scenario0.getNomTestLab());
		CT07.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des steps
		CT07.ajouterObjectif(new ObjectifBean("Test arrive a terme", CT07.getNomCasEssai() + CT07.getTime()));
		CT07.ajouterStep("Relancement d'Izivente et retour sur le dossier", "RETOUR", "Affichage de la page d'accueil d'Izivente avec injection du jeton");
		CT07.ajouterStep("Ouverture et du dossier et recherche du numero FFI", "RECHERCHE", "Affichage des donnees dossier et client");
		CT07.ajouterStep("Passage a l'octroi et premieres verification", "OCTROI", "Dossier accepte pour l'octroi ");
		CT07.ajouterStep("Finalisation de l'octroi et dernieres confirmations avant mise en gestion", "FINALISATION", "Affichage des donnees dossiers et client avec etat FORC");
		CT07.ajouterStep("Verification des donnees dossier et client", "MISENFORCE", "Dossier a l'etat FORC");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////// MISE EN GESTION ////////////////////////////////////////////
		//if (typeDossier != Constantes.CREDIT_AMORT){
		//Step 1 : Rechargement de l'URL d'Izivente et reinjection du jeton
		//saisieJeton(outil, scenario0.getIdClient(), false, distributeur, null, agence, etablissement);
		CT07.validerObjectif(outil.getDriver(), "RETOUR", true);
		//Step 2 : Ouverture du dossier et recherche du numero FFI
		if (typeDossier == TypeProduit.FACELIA || typeDossier == TypeProduit.IZICARTE) {
			outil.attendreEtCliquer(Cibles.BOUTON_MENU_REPRISE_DOSSIER);
		} else if(typeDossier == TypeProduit.CREODIS) {
			outil.attendreEtCliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FC);
		} else if(typeDossier == TypeProduit.CREDIT_AMORT) {
			outil.attendreEtCliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
		}
		outil.attendre(1);
		outil.attendrePresenceTexte("Liste des dossiers");
		for (WebElement coche : outil.obtenirElements(Cibles.COCHES_LISTE_DOSSIER)) {
			coche.click();
			if (outil.testerPresenceTexte(scenario0.getNumeroFFI(), true)) {
				break;
			}
		}
		CT07.validerObjectif(outil.getDriver(), "RECHERCHE", true);
		// Step 3 : Passage a l'octroi
		if (typeDossier == TypeProduit.CREDIT_AMORT) {
			outil.cliquer(Cibles.BOUTON_PASSAGE_OCTROI);
			outil.attendrePresenceTexte("Demande de confirmation");
		} else {
			outil.attendreChargementElement(Cibles.BOUTON_MISE_EN_FORCE_CR, true, true);
			outil.cliquer(Cibles.BOUTON_MISE_EN_FORCE_CR);
		}
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		outil.attendre(2);
		// Si une popup s'affiche avec le texte relatif à la mise en force "Ok", c'est que la MEF est déjà effectuée.
		if (outil.testerPresenceElement(Cibles.BOUTON_POPUP_OK_MAJ)) {
			outil.cliquer(Cibles.BOUTON_POPUP_OK_MAJ);
		} else {
			// On clique sur le "Oui" de la popup de confirmation. Puis on attend la présence des coches.
			outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ, true, true);
			outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
			outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_VERIFIE, true, true);
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
			outil.cliquerEtAttendre(Cibles.BOUTON_POPUP_OUI_MAJ, Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
			outil.cliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
			CT07.validerObjectif(outil.getDriver(), "FINALISATION", true);
		}
		//Step 5 : Verification du passage a l'etat FORC
		outil.attendrePresenceTexte("Liste des dossiers");
		CT07.validerObjectif(outil.getDriver(), "MISEENFORCE", true);
		scenario0.setFlag(Constantes.ETAPE_SUIVANTE_MURIR);
		//outil.fermerFenetreCourante();
		return CT07;
	}

	/**
	 * Cas de test relatif au murissement.
	 * @param scenario0 le scenario comportant les informations de configurations.
	 * @param outil lza boite a outil selenium
	 * @return le resultat sous forme de bean de l'execution.
	 */
	public CasEssaiIziventeBean CT08Murissement(CasEssaiIziventeBean scenario0, SeleniumOutils outil) {
		//Parametrage du CT08
		CasEssaiIziventeBean CT08 = new CasEssaiIziventeBean();
		CT08.setAlm(scenario0.getAlm());
		CT08.setNomCasEssai("CT08 -" + getTime());
		CT08.setDescriptif(Catalogue.CT08_TITRE);
		CT08.setNomTestPlan(Catalogue.CT08_TITRE);
		//Information issues du scenario.
		CT08.setIdUniqueTestLab(scenario0.getIdUniqueTestLab());
		CT08.setIdUniqueTestPlan(78878);
		CT08.setCheminTestLab(scenario0.getCheminTestLab());
		CT08.setNomTestLab(scenario0.getNomTestLab());
		CT08.setRepertoireTelechargement(scenario0.getRepertoireTelechargement());
		//Gestion des objectifs
		CT08.ajouterObjectif(new ObjectifBean("Test arrive a terme", CT08.getNomCasEssai() + CT08.getTime()));
		CT08.ajouterStep("Lancement de la fonction murissement", "Lancement de l'etape du murissement", "Fonction lancee");
		CT08.ajouterStep("Validation du murissement du dossier", "Murissement valide", "Murissement valide");
		//Lancement de la fonction de murissement d'un dossier
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String siocID = IZIVENTEOutils.derniersCaracteres(scenario0.getNumeroFFI(), 8);
		String date = sdf.format(new Date());
		Boolean retour = IZIVENTEOutils.murissement(siocID, scenario0.getDistributeur(), typeDossier != TypeProduit.CREDIT_AMORT, date);
		if (outil != null) {
			CT08.validerObjectif(outil.getDriver(), "Lancement de l'etape du murissement", true);
		} else {
			CT08.validerObjectif("Lancement de l'etape du murissement", true);
		}
		if (retour){
			scenario0.setFlag(Constantes.ETAPE_SUIVANTE_MEG);
			if (outil != null) {
				CT08.validerObjectif(outil.getDriver(), "Murissement valide", true);
			} else {
				CT08.validerObjectif("Murissement valide", true);
			}
			return scenario0;
		} else {
			//TODO Gestion d'erreur non bloquante : permettrai de passer a un autre murissement
			if (outil != null) {
				CT08.validerObjectif(outil.getDriver(), "Murissement valide", false);
			} else {
				CT08.validerObjectif("Murissement valide", false);
			}
			return scenario0;
		}
		
	}

	/**
	 * Fonction generique permettant pour tout type de produit de ne positionner aucun coemprunteur.
	 * @param cas le cas de test concerne.
	 * @param outil la boite a outil selenium.
	 * @throws SeleniumException en cas d'erreur lors de l'interaction avec l'IHM.
	 */
	private void aucunCoEmprunteur(SeleniumOutils outil) throws SeleniumException {
		// Si le type de dossier est tout sauf CREODIS on clique sur le bouton "Aucun CoEmprunteur"
		//On passe automatiquement a l'etape de choix d'assurance pour le CREODIS
		if (typeDossier != TypeProduit.CREODIS) {
	    	outil.attendreChargementElement(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
	    	outil.attendreEtCliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
		}
	}
	
	/**
	 * Fonction dediee a l'ajout d'un conjoint co emprunteur.
	 * @param outil la boite a outil selenium.
	 * @throws SeleniumException en cas d'impossibilite d'ajouter le conjoint co emprunteur.
	 */
	private void ajoutConjointCoEmprunteurUnique(SeleniumOutils outil) throws SeleniumException {
		//On clique sur le bouton "Ajouter Conjoint CoEmprunteur" si on ne veut que le conjoint coemprunteur sur le dossier.
		//Cette option n'est accessible que pour les PP et les CR IZICARTE dont l'emprunteur et le conjoint ont un compte joint
		if(typeDossier == TypeProduit.CREDIT_AMORT) {
			outil.cliquer(Cibles.BOUTON_AJOUT_CONJOINT);
		} else{
			//TODO Gerer le mode IZICARTE avec compte joint
			//outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
    		//outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
			outil.logger("On ne peut pas inscrire de co emprunteur sur un CR sauf Izicarte muni d'un compte joint");
		}
	}
	
	/**
	 * Fonction generique d'ajout d'un tiers dont le numero de personne physique est connu et parametre dans la classe.
	 * @param outil la boite a outil.
	 * @throws SeleniumException en cas d'erreur.
	 */
	private void ajoutTiers(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
		
		// Recherche du tiers a partir du numero parametre
		outil.attendreChargementElement(Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, true, true);
		outil.selectionner("RCHNUMERO", Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, false);
		outil.attendreChargementElement(Cibles.SAISIE_NUMERO_PERS_PHY);
		outil.viderEtSaisir(numPersPhysTiers, Cibles.SAISIE_NUMERO_PERS_PHY);
		outil.cliquer(Cibles.BOUTON_RECHERCHER);
		//Ajout du tiers
	    //outil.attendreChargementElement(Cibles.BOUTON_AJOUT_TIERS);
		outil.attendreEtCliquer(Cibles.BOUTON_AJOUT_TIERS);
		
		// On valide la popup relative au tiers ajoute
		//TODO Amelioration possible sur le bouton de validation pour eviter la differenciation entre les distributeurs
		if(scenario.getDistributeur() == Constantes.CAS_CE) {
			outil.attendrePresenceTexte("Attention");
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_1);
		} else {
			outil.attendrePresenceTexte("ATTENTION");
    		outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
    		outil.attendreEtCliquer(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_BP);
    	}
		
		// Validation de la synthese du tiers
    	outil.attendrePresenceTexte("Synthèse des informations sur le Tiers");
		outil.cliquer(Cibles.BOUTON_POPUP_VALIDER_SYNTHESE_TIERS);
	}
	
	/**
	 * Fonction generique pour la mise en place ou non d'une assurance pour l'emprunteur
	 * @param outil la boîte a outil
	 * @throws SeleniumException en cas d'erreur
	 */
	private void assuranceEmprunteur(SeleniumOutils outil) throws SeleniumException {
		//TODO prevoir le choix de type d'assurance
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
					//TODO Condition sur l'assurance si montant credit superieur a 21000 maintenant appliquee a tous les montants en recette future bouchonne.
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
					outil.attendreEtCliquer(Cibles.RADIO_AVEC_ASS_IZICARTE);
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
					outil.attendreEtCliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
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
	 * Fonction generique pour le choix d'une assurance sur le conjoint
	 * @param outil la boîte a outil
	 * @throws SeleniumException en cas d'erreur
	 */
	private void assuranceConjoint(SeleniumOutils outil) throws SeleniumException {
		Boolean presenceTiers = (tiersCoEmp || tiersCaution);
		Boolean presenceConjoint = (conjointCoEmp || conjointCaution);
		if (presenceConjoint) {
			if (presenceTiers) {
				outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT2, true, true);
				outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT2);
			} else {
				outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1, true, true);
				outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			}
			// On attend que les choix d'assurance soient affiches et clicable.
			outil.attendre(1);
			outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ, true, true);
			outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_NON_MAJ, true, true);
			// On clique sur le choix d'assurance a oui ou non.
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
	 * Fonction generique pour le choix d'une assurance sur le tiers
	 * @param outil la boîte a outil
	 * @throws SeleniumException en cas d'erreur
	 */
	private void assuranceTiers(SeleniumOutils outil) throws SeleniumException {
		if((tiersCoEmp == true || tiersCaution == true) && (assuranceConjointCoEmp == false || assuranceEmp == false)) {
			outil.attendreEtCliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			//TODO remplace le attendre 1 ne devrais pas être necessaire.
			outil.attendre(1);
			
			if(assuranceTiers){
				outil.attendreEtCliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);	
			} else{
				outil.attendreEtCliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
			}
		}
	}
	/**
	 * Fonction permettant de choisir le rôle du conjoint (co emprunnteur ou caution) seulement dans le cas de la presence d'un tiers sur le dossier
	 * @param outil la boîte a outil
	 * @throws SeleniumException en cas d'erreur
	 */
	private void roleConjoint(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
		
		// Un tiers as t'il un rôle ?
		Boolean roleTiers = (tiersCoEmp == true || tiersCaution == true);
		
		// Quel rôle est reserve au conjoint ?
		if (conjointCoEmp == true && conjointCaution == true) {
			throw new SeleniumException(Erreurs.E030, "Impossible d'avoir deux rôles pour le conjoint.");
		}
		// Si le tiers dispose d'un rôle, soit on supprime le conjoint si il n'as pas de rôle, soit on choisit le rôle demande
		if (roleTiers) {
			if(conjointCoEmp == false && conjointCaution == false){
				//S'il n'il n'y a pas d'indication sur le conjoint avec presence de tiers, on supprime le conjoint du dossier
				boolean CE = (scenario.getDistributeur() == Constantes.CAS_CE);
				outil.attendreChargementElement(Cibles.TABLEAU_PARTICIPANTS);
				outil.obtenirElement(Cibles.TABLEAU_PARTICIPANTS, "./tr[2]/td[5]/table").click();			
				//outil.attendreChargementElement(CE?Cibles.BOUTON_SUPP_PARTICIPANT_2_CE:Cibles.BOUTON_SUPP_PARTICIPANT_2_BP);
				//outil.cliquer(CE?Cibles.BOUTON_SUPP_PARTICIPANT_2_CE:Cibles.BOUTON_SUPP_PARTICIPANT_2_BP);
				outil.attendrePresenceTexte("Demande de confirmation de suppression");
				outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
				outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
					
			} else {
				//outil.attendre(2);
				outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT2, true, true);
				outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT2);
				outil.attendre(1);
				outil.attendreChargementElement(Cibles.SELECTEUR_ROLE_PARTICIPANT, true, true);
				// Si le role du conjoint est co emp ou selectionne "C" sinon "G"
				outil.selectionner(conjointCoEmp?"C":"G", Cibles.SELECTEUR_ROLE_PARTICIPANT);
			}
		}
	}
	/**
	 * Fonction generique pour definir le rôle du tiers
	 * @param outil la boite à outils
	 * @throws SeleniumException en cas d'erreur
	 */
	private void roleTiers(SeleniumOutils outil) throws SeleniumException {
		
		// Un tiers as t'il un rôle ?
		Boolean roleTiers = (tiersCoEmp == true || tiersCaution == true);
		// Si le tiers dispose d'un rôle, on doit soit supprimer le conjoint si il n'as pas de rôle soit choisir son rôle
		if (roleTiers) {
			// Quel rôle est reserve au conjoint ?
			
		if (tiersCoEmp == true && tiersCaution == true) {
			throw new SeleniumException(Erreurs.E030, "Impossible d'avoir deux rôles pour le tiers.");
		}
		outil.attendre(1);
		outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1, true, true);
		outil.cliquerEtAttendre(Cibles.RADIO_SELECTION_PARTICIPANT1, Cibles.SELECTEUR_ROLE_PARTICIPANT);
		// Si le role du tiers est co emp ou selectionne "C" sinon "G"
		outil.selectionner(tiersCoEmp?"C":"G", Cibles.SELECTEUR_ROLE_PARTICIPANT);
		}
	}
	
	/**
	 * Fonction permettant la declaration des variables liees au scenario.
	 * Si le scenario est déjà renseigner on ne complète pas !
	 */
	public void declarationScenario(CasEssaiIziventeBean scenario){
		//On declare le numero client/distributeur utilise dans le scenario
		if (idClient != null && scenario.getIdClient() == null){
			scenario.setIdClient(idClient);
		}
		if (distributeur != -1 && scenario.getDistributeur() == -1) {
			scenario.setDistributeur(distributeur);
		}
		//On declare le numero d'agence utilise dans le scenario
		if (scenario.getEtablissement() == null) {
			if(etablissement != null){
				scenario.setEtablissement(etablissement);
			} else {
				// Valeurs par defaut en l'absence de parametrage
				scenario.setEtablissement(scenario.getDistributeur() == Constantes.CAS_BP?"038":"11315");
			}
		}
		//On declare le numero d'agence utilise dans le scenario
		if (scenario.getAgence() == null) {
			if (agence != null){
				scenario.setAgence(agence);
			} else {
				// Valeurs par defaut en l'absence de parametrage
				scenario.setAgence(scenario.getDistributeur() == Constantes.CAS_BP?"00022":"1131500030000135");
			}
		}
	}
	
	/**
	 * Fonction permettant de recuperer une chaine de caractere (BP ou CE) en fonction du distributeur selectionne
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
	 * Fonction permettant de recuperer une chaîne de caractere (CR ou PP) en fonction du type de dossier
	 * @param typeDossier le type de produit choisie.
	 * @return le code sur deux caractere correspondant au type de dossier.
	 */
	private String chaineProduit(TypeProduit typeDossier) {
		return typeDossier.getCode();
	}
	
	/**
	 * Fonction qui aliment le fichier de donnees a partir des informations en parametre.
	 * @param scenario les informations sur le scenario
	 * @param flag le flag du dossier indiquant son etat d'avancee 
	 * @param date a quelle date doit ont faire la prochaine action?
	 * @throws SeleniumException en cas d'erreur d'acces au fichier.
	 */
	public void ecritureFichierDonnees(CasEssaiIziventeBean scenario, Date date) throws SeleniumException {
		String distrib = chaineDistributeur(scenario.getDistributeur());
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
		String noDossUnited = scenario.getNumeroDossierUnited();
		String chaine = (distrib +";"+ FFI +";"+ idClnt +";"+ IUN +";"+ typeDos +";"+ flg +";"+ sdf.format(date) +";"+ numBIC +";"+ numIBAN +";"+ etab +";"+ agce+ ";"+ noDossUnited +";");

		try {
			boolean existence = remplacer(scenario.getNumeroFFI(), chaine);
			if (!existence) {
				chaine = chaine + "\r\n";
				Files.write(Paths.get("src/test/DonneesClientDossier.txt"),chaine.getBytes(),StandardOpenOption.APPEND);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new SeleniumException(Erreurs.E020, "Impossible d'ecrire dans DonneesClientDossier");
		} 
	}
	
	/**
	 * Initialise un cas d'essai IZIVENTE a partir d'une instance connue extraite du fichier de donnee client.
	 * @param instance la ligne issue du fichier de donnee servant a initaliser le cas d'essai IZIVENTE.
	 * @return le nouveau cas d'essai initialise.
	 * @throws SeleniumException 
	 */
	public CasEssaiIziventeBean initialiserScenario(String instance) throws SeleniumException {
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
				scenario.setDistributeur("CE".equals(instanceDecoupee[0])?Constantes.CAS_CE:Constantes.CAS_BP);
				scenario.setNumeroFFI(instanceDecoupee[1]);
				scenario.setIdClient(instanceDecoupee[2]);
				scenario.setNumeroIUN(instanceDecoupee[3]);
				//TODO simplifier ce test, l'exporte dans la classe type de produit ??
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
				scenario.setNumeroDossierUnited(instanceDecoupee[11]);
				
			} 
		} catch (ParseException ex) {
			throw new SeleniumException(Erreurs.E030, "Une date du fichier de donnee est incorrecte.");
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new SeleniumException(Erreurs.E030, "L'instance du fichier de donnee ne contient pas les informations necessaires.");
		}
		return scenario;
	}
	
	/**
	 * Fonction realisant l'etape de simulation.
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
	 * Fonction realisant l'etape de validation.
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
	 * Fonction realisant l'etape de mise a l'etat EDIT.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public void miseAEdit() throws SeleniumException {
		// Declarer une instance de test IZIVENTE
		//TNRSC00 generateurSimu = new TNRSC00();
		
		// Configurer le generateur :
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
	 * Fonction permettant la mise en force de dossier identifie dans le fichier de donnee comme devant être mis en force.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public void miseEnForce() throws SeleniumException {
		// Declarer une instance de test IZIVENTE
		//TNRSC00 generateurSimu = new TNRSC00();
		// Mettre en gestion une instance de test IZIVENTE
		this.simulation = false;
		this.validation = false;
		this.edition = false;
		this.miseEnGestion = true;
		this.murissement = false;
		
		// On recupere le contenu du fichier de donnee.
		List<String> listeInstances = this.renvoyerContenuFichierDonnee(Constantes.ETAPE_SUIVANTE_MEF);
		
		for (String instance : listeInstances) {
			// On initialise le scenario avec les donnees de l'instance
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
	 * Effectue le murissement de dossiers presents dans le fichier de donnee a l'etape de murissement.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public void murissement(SeleniumOutils outil) throws SeleniumException {
		this.simulation = false;
		this.validation = false;
		this.edition = false;
		this.miseEnGestion = false;
		this.murissement = true;
		List<String> listeInstances = this.renvoyerContenuFichierDonnee(Constantes.ETAPE_SUIVANTE_MURIR);
		try {
			for (String instance : listeInstances) {

				// On initialise le scenario avec les donnees de l'instance
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
			} catch(SeleniumException ex) {
				//TODO 
			}
		
		
	}
	
	public void consultationIZIGATE() throws SeleniumException {
			
			List<String> listeInstances = this.renvoyerContenuFichierDonnee(Constantes.ETAPE_SUIVANTE_MEG);
			CasEssaiIzigateBean consultation = new CasEssaiIzigateBean();
			GregorianCalendar calendar = new GregorianCalendar();
			CasEssaiIziventeBean reference = new CasEssaiIziventeBean();
			try {
				for (String instance : listeInstances) {
					// On initialise le scenario avec les donnees de l'instance
					reference = this.initialiserScenario(instance);		
					SCConsultation scconsultation = new SCConsultation();
					consultation.setNumeroFFI(reference.getNumeroFFI());
					consultation.setDistributeur(reference.getDistributeur());
					CasEssaiIzigateBean simuConsult = scconsultation.lancementTestIzigate(consultation);
					if (!"".equals(simuConsult.getNumeroDossierUnited())){
						reference.setNumeroDossierUnited(simuConsult.getNumeroDossierUnited());
					} else {
						reference.setNumeroDossierUnited("N/A");
					}
					reference.setFlag(Constantes.ETAPE_SUIVANTE_VERIF_SYNTHESE);
					this.ecritureFichierDonnees(reference, calendar.getTime());
				}
			} catch (SeleniumException ex) {
				//TODO 
			}
	}
}
