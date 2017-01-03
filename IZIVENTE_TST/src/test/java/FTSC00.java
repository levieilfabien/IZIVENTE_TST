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
 * Sc�nario modularis� des tests automatis�s pour Fusion - 12/2016 permettant l'�dition de CR et PP
 * @author levieilfa bardouma
 */
public class FTSC00 extends SC00Test {
//Sc�nario modularis� pour production en masse de dossiers PP et CR sur les deux distributeurs et sur diff�rente agences/�tablissemlebt. 
//Pas de CEMP pour CR, CEMP conjoint sur PP, choix d'assurances par d�faut.
	//D�finir le distributeur Constantes.CAS_CE pour CE/Constantes.CAS_BP pour BP
	int distributeur = Constantes.CAS_CE;
	//D�finir le type de dossier FACELIA/CREODIS/IZICARTE/CREDIT_AMORT
	int typeDossier = Constantes.IZICARTE;
	//D�finir l'�tablissement et l'agence (ex : 1871500030000302) - La valeur null rend des valeurs par d�fauts qui fonctionnent pour la plupart de nos sc�narios
	String etablissement = null;
	String agence = null;
	
/**
 * Id de s�rialisation par d�faut.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	
	//Description du sc�nario
	CasEssaiIziventeBean scenario0 = new CasEssaiIziventeBean();
	//Configuration du driver
	FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
	FirefoxProfile profile = configurerProfilNatixis();
	//Cr�ation et configuration du repertoire de t�l�chargement
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
	scenario0.ajouterObjectif(new ObjectifBean("Test arriv� � terme", scenario0.getNomCasEssai() + scenario0.getTime()));
   
    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
    outil.setRepertoireRacine(scenario0.getRepertoireTelechargement());
    
    try {
		//CT01 - Acc�s Izivente et initialisation
		//CT02 - Ouverture du dossier et validation des informations client
		//CT03 - Saisie des param�tres relatifs a	u type de dossier et validation
		//CT04 - Choix des participants et des assurances associ�es et validation des participants
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
	

}

/**
 * Partie du scenario0 regroupant la saisie, l'instruction et la validation d'un dossier dans IZIVENTE CE.
 * @param scenario le scenario englobant.
 * @param outil l'outil de manipulation du navigateur.
 * @return le cas d'essai document� pour ajout au sc�nario.
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIziventeBean CT01Initialisation(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT01
	CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
	//Information issues du sc�nario.
	//Gestion des steps
	CT01.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT01.getNomCasEssai() + CT01.getTime()));
	CT01.ajouterStep("G�n�ration du bouchon client Izivente", "GENERATION", "Cr�ation du bouchon termin�e");
	CT01.ajouterStep("Lancer l'URL Izivente en fonction du r�seau test�", "ACCESREROUTAGE", "Affichage de l'�cran de reroutage");
	CT01.ajouterStep("R�cup�rer num�ro de bouchon g�n�r� et injecter le jeton (copier/coller du code dans la zone de l'�cran de reroutage)", "INJECTION", "Jeton coll� dans la zone appropri�e");
	CT01.ajouterStep("Valider le jeton et lancer le reroutage (clic sur bouton 'Reroutage')", "ACCESIZIVENTE", "Affichage d'Izivente (�cran d'instruction ou pop up de mode de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCES IZIVENTE ET INITIALISATION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	ModificateurBouchon modificateur = new ModificateurBouchon();
	//modificateur.emprunteurCasden = true;
	//Steps 1,2,3,4 : G�n�ration du bouchon - Acc�s � l'�cran de reroutage et injection du jeton - Acc�s � Izivente
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
	//Param�trage du CT02
	CasEssaiIziventeBean CT02 = new CasEssaiIziventeBean();
	//Information issues du sc�nario.
	//Gestion des steps
	CT02.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT02.getNomCasEssai() + CT02.getTime()));
	CT02.ajouterStep("Choisir le mode de vente (Face � face ou Vente � distance)", "MODE", "Affichage de l'�cran d'instruction");
	CT02.ajouterStep("S�lectionner l'option d'ouverture d'un dossier correspondant au type voulu.", "OUVERTURE", "Affichage des pop ups de confirmation");
	CT02.ajouterStep("Fermeture des pop ups confirmant l'ouverture du dossier", "CONFIRMATION", "Affichage de l'�cran de donn�es client et de liste des dossiers");
	CT02.ajouterStep("V�rifier la coh�rence des donn�es du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'.", "SUIVANT", "Affichage de la pop un de Synth�se des informations client");
	CT02.ajouterStep("Valider les donn�es client en cliquant sur le bouton 'Valider' dans la pop up de synth�se des information client.", "VALIDATIONDONNEESCLIENT", "Affichage de l'�cran de demande de cr�dit");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// OUVERTURE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : Choisir le mode de vente (F�F ou V�D) - Cette etape est optionelle, le "Face � face" ne sera pas toujours visible   
	outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
	CT02.validerObjectif(outil.getDriver(), "MODE", true);
	//Step 2 : S�lectionner l'option d'ouverture d'un dossier correspondant au type voulu. 
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
	// Cette �tape n'as lieu que si le dossier est d�j� porteur d'un autre dossier
	outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
	outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
	//Step 4 : V�rifier la coh�rence des donn�es du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'
	outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
	outil.attendrePresenceTexte("Synth�se");
	CT02.validerObjectif(outil.getDriver(),  "SUIVANT", true);
	outil.cliquer(Cibles.BOUTON_VALIDER);
	CT02.validerObjectif(outil.getDriver(), "VALIDATIONDONNEESCLIENT", true);
	CT02.validerObjectif(outil.getDriver(), CT02.getNomCasEssai() + CT02.getTime(),true);
	return CT02;
}
	
public CasEssaiIziventeBean CT03SaisieDossier(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT03
	CasEssaiIziventeBean CT03 = new CasEssaiIziventeBean();
	//Information issues du sc�nario.
	//Gestion des steps
	CT03.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT03.getNomCasEssai() + CT03.getTime()));
	CT03.ajouterStep("S�lectionner l'offre d�sir�e dans le menu d�roulant selon le sc�nario", "OFFRE", "Offre s�lectionn�e");
	CT03.ajouterStep("S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)", "PARAMETRES", "Param�tres coh�rents avec le sc�nario");
	CT03.ajouterStep("Cliquer sur le bouton 'Suivant' pour valider les informations du dossier", "SAISIEDOSSIER", "Affichage de l'�cran de s�lection des participants");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// SAISIE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : S�lectionner l'offre d�sir�e dans le menu d�roulant selon le sc�nario
	switch(typeDossier){
	case Constantes.FACELIA : 
		outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT_CR, true, true);
		outil.selectionner("FACELIA", Cibles.SELECTEUR_OFFRE_CREDIT_CR, false);
		outil.attendre(2);
		CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
		//Step 2 : S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)
		outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR);
		outil.selectionner("Pr�t immobilier", Cibles.SELECTEUR_SITUATION_VENTE_CR);
		outil.viderEtSaisir("7500",  Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
		outil.attendreChargementElement(Cibles.SAISIE_MENSUALITE_CR); 
		outil.viderEtSaisir("750", Cibles.SAISIE_MENSUALITE_CR);
	break;
	case Constantes.CREODIS :
		outil.attendrePresenceTexte("INFORMATIONS DU CREDIT");
		outil.attendre(2);
		CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
		//Step 2 : S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)
		outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR);
		outil.selectionner("Entr�e en relation", Cibles.SELECTEUR_SITUATION_VENTE_CR);
		outil.viderEtSaisir("2000", Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
		outil.viderEtSaisir("80,00", Cibles.SAISIE_MENSUALITE_CR);
	break;
	case Constantes.IZICARTE : 
		outil.attendrePresenceTexte("Informations du cr�dit");
		outil.attendre(2);
		CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
		//Step 2 : S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)
		outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
		outil.selectionner("BANC", Cibles.SELECTEUR_SITUATION_VENTE_CR);
		outil.attendre(2);
		outil.attendreChargementElement(Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR, true, true);
		outil.viderEtSaisir("8000", Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
	break;
	case Constantes.CREDIT_AMORT :
		switch(distributeur){
		case Constantes.CAS_CE :
			outil.attendrePresenceTexte("Informations du cr�dit");
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT_CE, true, true);
			outil.selectionner("TRESORERIE", Cibles.SELECTEUR_UNIVERS_CREDIT_CE, false);
			outil.attendre(5); //2 secondes ne suffisent pas
			outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
			outil.selectionner("CREDIT TRESORERIE", Cibles.SELECTEUR_OFFRE_CREDIT, false);
			outil.attendre(2);
			CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
			//Step 2 : S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)
			outil.selectionner("TRESORERIE", Cibles.SELECTEUR_OBJET_FINANCE);
			outil.attendre(2);
			outil.viderEtSaisir("20000", Cibles.SAISIE_COUT_PROJET);
			outil.viderEtSaisir("20000", Cibles.SAISIE_MONTANT_DEMANDE);
			outil.viderEtSaisir("60", Cibles.SAISIE_DUREE_DEMANDE);
		break;
		case Constantes.CAS_BP : 
			//Step 1 : S�lectionner l'offre d�sir�e dans le menu d�roulant selon le sc�nario
			outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT, true, true);
			outil.selectionner("TRESORERIE", Cibles.SELECTEUR_UNIVERS_CREDIT, false);
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
			outil.selectionner("CREDIT TRESORERIE", Cibles.SELECTEUR_OFFRE_CREDIT, false);
			CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
			outil.attendre(2);
			//Step 2 : S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)
			outil.selectionner("TRESORERIE", Cibles.SELECTEUR_OBJET_FINANCE);
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
	//Param�trage du CT04
	CasEssaiIziventeBean CT04 = new CasEssaiIziventeBean();
	CT04.setAlm(true);
	CT04.setNomCasEssai("CT04 -" + getTime());
	CT04.setDescriptif("CT04 - Choix des participants");
	CT04.setNomTestPlan("CT04 - Choix des participants");
	//Information issues du sc�nario.
	CT04.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT04.setCheminTestLab(scenario.getCheminTestLab());
	CT04.setNomTestLab(scenario.getNomTestLab());
	CT04.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
	//Gestion des steps
	CT04.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT04.getNomCasEssai() + CT04.getTime()));
	CT04.ajouterStep("Choisir les participants en fonction de la fiche de pr�t et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le num�ro de personne physique, cliquer sur rechercher, v�rifier la coh�rence des donn�es du tiers  et  valider les donn�es du tiers. ", "PARTICIPANTS", "Affichage de l'�cran 'Synth�se des participants'");
	CT04.ajouterStep("Pour chaque participant, choisir le r�le et l'assurance en fonction des hypoth�ses.", "ASSURANCEROLE", "Les informations sont conformes et le bouton de validation des participants cliquable");
    CT04.ajouterStep("Valider la liste des participants (clic sur bouton correspondant)", "VALIDATIONPARTICIPANTS", "Affichage de l'�cran de 'Proposition' avec la grille alternative commerciale");
	
    /////////////////////////////////////////////////////////////////////////////////////////////////////
	// PARTICIPANTS
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : Choisir les participants en fonction de la fiche de pr�t et Valider. Aucun co-emprunteur dans ce sc�nario
    switch (typeDossier){
    case Constantes.CREDIT_AMORT :
    	outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
		outil.cliquer(Cibles.BOUTON_AJOUT_CONJOINT);
	break;
    case Constantes.IZICARTE :
    	outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
    	outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
    break;
    case Constantes.FACELIA :
    	outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
    	outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
    	CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
    break;
    case Constantes.CREODIS :
    break;
    }
    CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
	//Step 2 : Pour chaque participant, choisir le r�le et l'assurance en fonction des hypoth�ses. Valider la listes des participants et confirmer l'assurance
	//Assurance de l'emprunteur
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
	//Assurance du coemprunteur (PP seulement)
	//CREDIT AMORT BP 
	if (typeDossier == Constantes.CREDIT_AMORT){
		switch (distributeur){
		case Constantes.CAS_CE :
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendreEtCliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		break;
		case Constantes.CAS_BP :
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendreEtCliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		break;}}
	else{}
	CT04.validerObjectif(outil.getDriver(), "ASSURANCEROLE", true);
	//Step 3 : Valider la liste des participants
	if (typeDossier == Constantes.CREODIS){
		outil.attendreChargementElement(Cibles.BOUTON_SUIVANT);
		outil.cliquer(Cibles.BOUTON_SUIVANT);}
	else{
	outil.attendreChargementElement(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);}
	CT04.validerObjectif(outil.getDriver(),  "VALIDATIONPARTICIPANTS", true);
	CT04.validerObjectif(outil.getDriver(), CT04.getNomCasEssai() + CT04.getTime(),true);
	return CT04;
}
	
public CasEssaiIziventeBean CT05FinalisationInstruction(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT05
	CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
	//Information issues du sc�nario.
	//Gestion des steps
	CT05.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT05.getNomCasEssai() + CT05.getTime()));
	CT05.ajouterStep("Valider de l'offre contrat de cr�dit (clic sur le bouton 'Valider en contrat de cr�dit')", "VALIDATION", "Affichage de la pop up de finalisation de l'instruction ou de la page de dossier de vente");
	CT05.ajouterStep("Finalisation de l'instruction (clic sur le bouton 'Oui' dans la popup de finalisation de l'instruction", "VALIDATIONINSTRUCTION", "Affichage de l'�cran de synth�se de l'offre de cr�dit");
	CT05.ajouterStep("Remplir le questionnaire pour la demande de financement � 8 jours et la r�ception de sollicitations commerciales partenaires (choix oui/non via boutons radio).", "OPTIONS", "Choix effectu�s conform�ment au sc�nario");
    CT05.ajouterStep("Imprimer la liasse de document (clic sur le bouton 'Suivant')", "IMPRESSION", "Affichage de la pop up 'Pr�paration contrat'");
    CT05.ajouterStep("Attendre la fin de la pr�paration du contrat puis cliquer sur suivant pour envoi � l'octroi", "PREPARATION", "Deconnexion d'Izivente");
	//Step Izicarte
    CT05.ajouterStep("Choisir le mode de vente (face � face ou par t�l�phone) selon le sc�nario", "MODE", "Impression en pdf de la liasse");
    //Step PP CE
	//CT05.ajouterStep("Imprimer la synth�se de l'offtre en pdf (clic sur bouton 'Imprimer')", "IMPRESSIONSYNTHESE", "Le fichier pdf est correctement t�l�charge");
	CT05.ajouterStep("S�lectionner le compte de pr�l�vement et valider l'offre de cr�dit (clic sur bouton 'Confirmer contrat de cr�dit')", "CONFIRMATION", "Affichage de la pop up 'Finalisation de l'instruction'");
	CT05.ajouterStep("V�rifier les justificatifs et valider (clic bouton radio 'V�rifi�' pour chaque justificatif dans la pop up de finalisation de l'instruction' et clic sur bouton 'Valider'", "VERIFICATION", "Retour sur la page de dossier de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// FINALISATION DE L'INSTRUCTION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	switch(typeDossier){
	case Constantes.FACELIA :
		//Step 1 : Valider de l'offre contrat de cr�dit
		outil.attendreChargementElement(Cibles.BOUTON_VALIDER_OPC_CR);
		outil.cliquer(Cibles.BOUTON_VALIDER_OPC_CR);
		outil.attendre(1);//Ne pas enlever
		CT05.validerObjectif(outil.getDriver(), "VALIDATION", true);
		//Step 2 : Finalisation de l'instruction
		outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
		outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		CT05.validerObjectif(outil.getDriver(), "VALIDATIONINSTRUCTION", true);
		//Step 3 : Remplir le questionnaire pour la demande de financement � 8 jours et la r�ception de sollicitations commerciales partenaires
		outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		CT05.validerObjectif(outil.getDriver(), "OPTIONS", true);
		//Step 4 : Imprimer la liasse de document
		outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.cliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.attendrePresenceTexte("Pr�paration contrat");
		CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
		//Step 5 : Pr�paration du contrat et envoi � l'octroi	
		outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
		outil.attendreChargementElement(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE);
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT_FIN);
		CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
	break;
	case Constantes.CREODIS :
		//Step 1 : Valider de l'offre contrat de cr�dit
		outil.attendreChargementElement(Cibles.ELEMENT_TABLEAU_PRELEVEMENT);
		outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_CREODIS_CR);
		CT05.validerObjectif(outil.getDriver(), "VALIDATION", true);
		//Step 2 : Finalisation de l'instruction
		outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		CT05.validerObjectif(outil.getDriver(), "VALIDATIONINSTRUCTION", true);
		//Step 3 : Remplir le questionnaire pour la demande de financement � 8 jours et la r�ception de sollicitations commerciales partenaires
		outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		CT05.validerObjectif(outil.getDriver(), "OPTIONS", true);
		//Step 4 : Imprimer la liasse de document
		outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.attendrePresenceTexte("Pr�paration contrat");
		CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
		//Step 5 : Pr�paration du contrat et envoi � l'octroi	
		outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
		outil.attendreChargementElement(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE);
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT_FIN);
		CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
	break;
	case Constantes.IZICARTE :
		//Step 1 : Valider de l'offre contrat de cr�dit (Etape non pr�sente dans un CR)
		outil.attendreChargementElement(Cibles.BOUTON_VALIDER_OPC_CR);
		outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_OPC_CR);
		CT05.validerObjectif(outil.getDriver(), "VALIDATION", true);
		//Step 2 : Finalisation de l'instruction
		outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		CT05.validerObjectif(outil.getDriver(), "VALIDATIONINSTRUCTION", true);
		//Step 3 : Remplir le questionnaire pour la demande de financement � 8 jours et la r�ception de sollicitations commerciales partenaires
		outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		CT05.validerObjectif(outil.getDriver(), "OPTIONS", true);
		//Step 4 : Imprimer la liasse de document
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.attendrePresenceTexte("Pr�paration contrat");
		CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
		//Step 5 : Pr�paration du contrat - choix du mode de vente	
		outil.attendreChargementElement(Cibles.BOUTON_POPUP_FACE_A_FACE_MAJ);
		outil.cliquer(Cibles.BOUTON_POPUP_FACE_A_FACE_MAJ);
		CT05.validerObjectif(outil.getDriver(), "MODE", true);
		//Step 6 : Pr�paration du contrat pour envoi � l'octroi
		outil.attendre(8);
		outil.attendreChargementElement(Cibles.BOUTON_PASSAGE_OCTROI_CR);
		outil.cliquer(Cibles.BOUTON_PASSAGE_OCTROI_CR);
		outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
		outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_NON_MAJ);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_VERIFIE);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
		CT05.validerObjectif(outil.getDriver(), "VERIFICATION", true);
		//Step 7 : Acceptation du contrat - envoi � l'octroi
		outil.attendreChargementElement(Cibles.BOUTON_SUIVANT);
		outil.cliquer(Cibles.BOUTON_SUIVANT);
		outil.attendreChargementElement(Cibles.LIBELLE_ACCEPTATION);
		outil.cliquer(Cibles.LIBELLE_ACCEPTATION);
		outil.attendre(8);
		outil.attendreChargementElement(Cibles.BOUTON_FINALISATION_OCTROI_CR);
		outil.cliquer(Cibles.BOUTON_FINALISATION_OCTROI_CR);
		outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
		outil.attendreChargementElement(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
		CT05.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
	break;
	case Constantes.CREDIT_AMORT :
		//Step 1 : Valider de l'offre contrat de cr�dit
		outil.attendrePresenceTexte("Alerte(s)");
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
		outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
		CT05.validerObjectif(outil.getDriver(), "VALIDATION", true);
		//Step 2 : S�lectionner le compte de pr�l�vement et valider l'offre de cr�dit
		outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_OPC);
		CT05.validerObjectif(outil.getDriver(), "VALIDATIONINSTRUCTION", true);
		//Step 3 : V�rifier les justificatifs et valider
		outil.attendreChargementElement(Cibles.ELEMENT_FIN_INSTRUCTION, true, true);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_VALIDER_JUSTIFICATIFS);
		CT05.validerObjectif(outil.getDriver(), "VERIFICATION", true);
		//Step 4 : Remplir le questionnaire pour la demande de financement � 8 jours et la r�ception de sollicitations commerciales partenaires
		outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
		CT05.validerObjectif(outil.getDriver(), "OPTIONS", true);
		//Step 5 : Imprimer la synth�se de l'offre
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE);
		CT05.validerObjectif(outil.getDriver(), "IMPRESSIONSYNTHESE", true);
		//Step 6 : Imprimer la liasse de document
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
		outil.attendrePresenceTexte("Pr�paration contrat");
		CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
		//Step 7 : Pr�paration du contrat et envoi � l'octroi		
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT);
		CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
	break;}
	CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
	return CT05;
	}
}