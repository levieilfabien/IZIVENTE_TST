package test.java;

import java.io.File;

import org.junit.Test;
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
	//Définir s'il y a ou non un coemprenteur ("" si aucun CEMP, "conjoint" si CEMP conjoint, "tiers" si CEMP tiers).
	String coemprunteur = "";
	
	Boolean conjointCoEmp = false;
	Boolean tiersCoEmp = false;
	Boolean tiersCaution = false;
	
	//Renseigner le numéro de personne physique pour le coemprunteur tiers (BP : 9500855 P1E CE : 942500400).
	String numPersPhysTiers = "942500400";
	//Définir la présence d'assurance pour les emprunteurs (oui/non).
	String empAssurance = "non";
	String coempAssurance = "oui";
	
/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	
	//Description du scénario
	CasEssaiIziventeBean scenario0 = new CasEssaiIziventeBean();
/*	scenario0.setAlm(true);
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
	
	switch(distributeur){
	case Constantes.CAS_CE :
		driver.get(Constantes.URL_CE_FUTURE_REROUTAGE);
	break;
	case Constantes.CAS_BP :
		driver.get(Constantes.URL_BP_FUTURE_REROUTAGE);
	break;}
	
	// LISTE DES OBJECTIFS DU CAS DE TEST
	scenario0.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario0.getNomCasEssai() + scenario0.getTime()));
   
    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
    outil.setRepertoireRacine(scenario0.getRepertoireTelechargement());
    
    try {
		//CT01 - Accès Izivente et initialisation
		//CT02 - Ouverture du dossier et validation des informations client
		//CT03 - Saisie des paramètres relatifs a	u type de dossier et validation
		//CT04 - Choix des participants et des assurances associées et validation des participants
		//CT05 - Validation de l'instruction
		scenario0.getTests().add(CT01Initialisation(scenario0, outil));
		scenario0.getTests().add(CT02OuvertureDossier(scenario0, outil));
		scenario0.getTests().add(CT03SaisieDossier(scenario0, outil));
		scenario0.getTests().add(CT04Participants(scenario0, outil));
		scenario0.getTests().add(CT05FinalisationInstruction(scenario0, outil));
		
	} catch (SeleniumException ex) {
		// Finalisation en erreur du cas de test.
		finaliserTestEnErreur(outil, scenario0, ex, scenario0.getNomCasEssai() + scenario0.getDateCreation().getTime());
		throw ex;
	}
	// Finalisation normale du cas de test.
	finaliserTest(outil, scenario0, scenario0.getNomCasEssai() + scenario0.getDateCreation().getTime());
}

/**
 * Partie du scenario0 regroupant la saisie, l'instruction et la validation d'un dossier dans IZIVENTE CE.
 * @param scenario le scenario englobant.
 * @param outil l'outil de manipulation du navigateur.
 * @return le cas d'essai documenté pour ajout au scénario.
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIziventeBean CT01Initialisation(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT01
	CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
	CT01.setAlm(true);
	CT01.setNomCasEssai("CT01 -" + getTime());
	CT01.setDescriptif("CT01 - Accès Izivente et Initialisation");
	CT01.setNomTestPlan("CT01 - Accès Izivente et Initialisation");
	//Information issues du scénario.
	CT01.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT01.setCheminTestLab(scenario.getCheminTestLab());
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
	ModificateurBouchon modificateur = new ModificateurBouchon();
	//modificateur.emprunteurCasden = true;
	//Steps 1,2,3,4 : Génération du bouchon - Accès à l'écran de reroutage et injection du jeton - Accès à Izivente
	String idClient = saisieJeton(outil, null, false, distributeur, modificateur, agence, etablissement);
	scenario.setIdClient(idClient);
	CT01.validerObjectif(outil.getDriver(), "GENERATION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESREROUTAGE", true);
	CT01.validerObjectif(outil.getDriver(), "INJECTION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESIZIVENTE", true);
	CT01.validerObjectif(outil.getDriver(), CT01.getNomCasEssai() + CT01.getTime(),true);
	return CT01;
}

public CasEssaiIziventeBean CT02OuvertureDossier(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT02
	CasEssaiIziventeBean CT02 = new CasEssaiIziventeBean();
	CT02.setAlm(true);
	CT02.setNomCasEssai("CT02 -" + getTime());
	CT02.setDescriptif("CT02 - Ouverture du dossier");
	CT02.setNomTestPlan("CT02 - Ouverture du dossier");	
	//Information issues du scénario.
	CT02.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT02.setCheminTestLab(scenario.getCheminTestLab());
	CT02.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
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
		CT02.validerObjectif(outil.getDriver(), "OUVERTURE", true);
		 break;
	 case Constantes.FACELIA : 
		outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FACELIA);
		CT02.validerObjectif(outil.getDriver(), "OUVERTURE", true);
		//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
		outil.attendrePresenceTexte("INFORMATION");
		outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	break;
	 case Constantes.CREODIS : 
		outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER_FC);
		CT02.validerObjectif(outil.getDriver(), "OUVERTURE", true);
	 break;
	 case Constantes.IZICARTE : 
		outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_IZICARTE);
		CT02.validerObjectif(outil.getDriver(), "OUVERTURE", true);
		//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
		outil.attendrePresenceTexte("Information");
		outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	 break;
	 case Constantes.TEOZ : 
		//outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_TEOZ);
		//outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
	 break;}
	if(distributeur == Constantes.CAS_CE)
	{outil.attendrePresenceTexte("Attention");}
	else
	{outil.attendrePresenceTexte("ATTENTION");}
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
	
public CasEssaiIziventeBean CT03SaisieDossier(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT03
	CasEssaiIziventeBean CT03 = new CasEssaiIziventeBean();
	CT03.setAlm(true);
	CT03.setNomCasEssai("CT03 -" + getTime());
	CT03.setDescriptif("CT03 - Saisie du dossier");
	CT03.setNomTestPlan("CT03 - Saisie du dossier");
	//Information issues du scénario.
	CT03.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT03.setCheminTestLab(scenario.getCheminTestLab());
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
		outil.attendreChargementElement(Cibles.SAISIE_MENSUALITE_CR); 
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
		switch(distributeur){
		case Constantes.CAS_CE :
			outil.attendrePresenceTexte("Informations du crédit");
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT_CE, true, true);
			outil.selectionner(typeUnivers, Cibles.SELECTEUR_UNIVERS_CREDIT_CE, false);
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
		case Constantes.CAS_BP : 
			//Step 1 : Sélectionner l'offre désirée dans le menu déroulant selon le scénario
			outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT, true, true);
			outil.selectionner(typeUnivers, Cibles.SELECTEUR_UNIVERS_CREDIT, false);
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
			outil.selectionner(typeOffre, Cibles.SELECTEUR_OFFRE_CREDIT, false);
			CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
			outil.attendre(2);
			//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
			outil.selectionner(typeObjet, Cibles.SELECTEUR_OBJET_FINANCE);
			outil.viderEtSaisir("20000", Cibles.SAISIE_COUT_PROJET);
			outil.selectionner("Aucun", Cibles.SELECTEUR_REPORT_PREMIERE_MENS, false);
			outil.viderEtSaisir("20000", Cibles.SAISIE_MONTANT_DEMANDE);
			outil.viderEtSaisir("60", Cibles.SAISIE_DUREE_DEMANDE);
		break;}
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
	
public CasEssaiIziventeBean CT04Participants(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT04
	CasEssaiIziventeBean CT04 = new CasEssaiIziventeBean();
	CT04.setAlm(true);
	CT04.setNomCasEssai("CT04 -" + getTime());
	CT04.setDescriptif("CT04 - Choix des participants");
	CT04.setNomTestPlan("CT04 - Choix des participants");
	//Information issues du scénario.
	CT04.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT04.setCheminTestLab(scenario.getCheminTestLab());
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
    switch(coemprunteur){
    	case "" :
    		aucunCoEmprunteur(CT04, outil);
    	break;
    	case "conjoint" :
    		if(typeDossier == Constantes.CREDIT_AMORT) {
    			outil.cliquer(Cibles.BOUTON_AJOUT_CONJOINT);
    		} else{
    			//TODO Gérer le mode IZICARTE avec compte joint
    			System.out.println("On ne peut pas inscrire de co emprunteur sur un CR sauf Izicarte muni d'un compte joint");
    			//outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
        		//outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
    		}
    	break;
    	case "tiers" :
    		ajoutTiers(outil);
    	break;}
    
	CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
	//Step 2 : Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses. Valider la listes des participants et confirmer l'assurance
	//Assurance de l'emprunteur
	outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
	outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
	switch(empAssurance){
	case "oui" :
		switch (typeDossier){
		case Constantes.CREDIT_AMORT :
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		break;
		case Constantes.IZICARTE :
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.attendre(1);
			outil.attendreChargementElement(Cibles.RADIO_AVEC_ASS_CR);
			outil.cliquer(Cibles.RADIO_AVEC_ASS_CR);
		break;
		case Constantes.FACELIA :
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.attendreChargementElement(Cibles.RADIO_AVEC_ASS_FACELIA);
			outil.cliquer(Cibles.RADIO_AVEC_ASS_FACELIA);
		break;
		case Constantes.CREODIS :
			outil.attendreEtCliquer(Cibles.RADIO_SELECTION_ASS_1_CR);
		break;}
	break;
	case "non" :
		switch (typeDossier){
		case Constantes.CREDIT_AMORT :
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
		break;
		case Constantes.IZICARTE :
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.attendre(1);
			outil.attendreChargementElement(Cibles.RADIO_SANS_ASS_CR);
			outil.cliquer(Cibles.RADIO_SANS_ASS_CR);
		break;
		case Constantes.FACELIA :
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.attendreChargementElement(Cibles.RADIO_SANS_ASS_FACELIA);
			outil.cliquer(Cibles.RADIO_SANS_ASS_FACELIA);
		break;
		case Constantes.CREODIS :
			outil.attendreEtCliquer(Cibles.RADIO_SELECTION_SANS_ASS_CR);
		break;}
	break;}
	if (coemprunteur == "conjoint"){
		switch(coempAssurance){
		case "oui" :
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		break;
		case "non" :
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
		break;}}
		else if(coemprunteur == "tiers"){
			switch (distributeur){
			case Constantes.CAS_BP :
				outil.selectionner("CAUTION PERSONNE PHYSIQUE", Cibles.SELECTEUR_ROLE_PARTICIPANT);
				outil.attendreChargementElement(Cibles.BOUTON_SUPP_PARTICIPANT_2_BP);
				outil.cliquer(Cibles.BOUTON_SUPP_PARTICIPANT_2_BP);
				outil.attendrePresenceTexte("Demande de confirmation de suppression");
				outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			break;
			case Constantes.CAS_CE :
				outil.selectionner("CAUTION PERSONNE PHYSIQUE", Cibles.SELECTEUR_ROLE_PARTICIPANT);
				outil.attendreChargementElement(Cibles.BOUTON_SUPP_PARTICIPANT_2_CE);
				outil.cliquer(Cibles.BOUTON_SUPP_PARTICIPANT_2_CE);
				outil.attendrePresenceTexte("Demande de confirmation de suppression");
				outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			break;}
		}
		else{}
	CT04.validerObjectif(outil.getDriver(), "ASSURANCEROLE", true);
	//Step 3 : Valider la liste des participants
	outil.attendreChargementElement(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	CT04.validerObjectif(outil.getDriver(),  "VALIDATIONPARTICIPANTS", true);
	CT04.validerObjectif(outil.getDriver(), CT04.getNomCasEssai() + CT04.getTime(),true);
	return CT04;
}
	
public CasEssaiIziventeBean CT05FinalisationInstruction(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramètrage du CT05
	CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
	CT05.setAlm(true);
	CT05.setNomCasEssai("CT05 -" + getTime());
	CT05.setDescriptif("CT05 - Finalisation de l instruction");
	CT05.setNomTestPlan("CT05 - Finalisation de l instruction");
	//Information issues du scénario.
	CT05.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT05.setCheminTestLab(scenario.getCheminTestLab());
	CT05.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
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
		//Step 6 : Préparation du contrat pour envoi à l'octroi
		outil.attendre(7);
		outil.attendreChargementElement(Cibles.BOUTON_TERMINER_EDITION_CR);
		outil.cliquer(Cibles.BOUTON_TERMINER_EDITION_CR);
		CT05.validerObjectif(outil.getDriver(), "VERIFICATION", true);
		CT05.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
	break;
	case Constantes.CREDIT_AMORT :
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
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.attendrePresenceTexte("Préparation contrat");
		CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
		//Step 7 : Préparation du contrat et envoi à l'octroi		
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT);
		CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
	break;
	}
	CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
	return CT05;
	}


	/**
	 * Fonction générique permettant pour tout type de produit de ne positionner aucun coemprunteur.
	 * @param cas le cas de test concerné.
	 * @param outil la boite à outil selenium.
	 * @throws SeleniumException en cas d'erreur lors de l'interaction avec l'IHM.
	 */
	private void aucunCoEmprunteur(CasEssaiIziventeBean cas, SeleniumOutils outil) throws SeleniumException {
		
		// Si le type de dossier est CREODIS
		if (typeDossier != Constantes.CREODIS) {
	    	outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
	    	outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
		}
		cas.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
		
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
}