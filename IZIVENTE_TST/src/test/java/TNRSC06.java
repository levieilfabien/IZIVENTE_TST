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
 * Sc�nario 6 des tests automatis�s pour IZIVENTE - 11/2016
 * Editique Tr�sorerie imm�diat CASDEN (BP)
 * @author levieilfa bardouma
 */
public class TNRSC06 extends SC00Test {

/**
 * Id de s�rialisation.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	// Description du sc�nario
	CasEssaiIziventeBean scenario6 = new CasEssaiIziventeBean();
	scenario6.setAlm(true);
	scenario6.setIdUniqueTestLab(54393);
	scenario6.setNomCasEssai("TNRSC06-" + getTime());
	scenario6.setDescriptif("TNRSC06 - BP - IZIVENTE_Editique Tr�sorerie imm�diat CASDEN");
	scenario6.setNomTestLab("TNRSC06 - BP - IZIVENTE_Editique Tr�sorerie imm�diat CASDEN");
	//scenario6.setNomTestPlan("TNRSC06 - BP - IZIVENTE_Editique Tr�sorerie imm�diat CASDEN");
	scenario6.setCheminTestLab("POC Selenium\\IZIVENTE");
	
	// Configuration du driver
	FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
	FirefoxProfile profile = configurerProfilNatixis();
	
	// Cr�ation et configuration du repertoire de t�l�chargement
	//File repertoireTelechargement = new File(".\\" + scenario2.getNomCasEssai());
	//repertoireTelechargement.mkdir();
	//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
	String repertoire = creerRepertoireTelechargement(scenario6, profile);
	scenario6.setRepertoireTelechargement(repertoire);
	// Initialisation du driver
	FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
	driver.get(Constantes.URL_BP_FUTURE_REROUTAGE);
	// LISTE DES OBJECTIFS DU CAS DE TEST
	scenario6.ajouterObjectif(new ObjectifBean("Test arriv� � terme", scenario6.getNomCasEssai() + scenario6.getTime()));
	
	SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
	outil.setRepertoireRacine(scenario6.getRepertoireTelechargement());
	
	try {
		//CT01 - Acc�s Izivente et initialisation
		//CT02 - Ouverture du dossier et validation des informations client
		//CT03 - Saisie des param�tres relatifs au type de dossier et validation
		//CT04 - Choix des participants et des assurances associ�es et validation des participants
		//CT05 - Validation de l'instruction
		scenario6.getTests().add(CT01Initialisation(scenario6, outil));
		scenario6.getTests().add(CT02OuvertureDossier(scenario6, outil));
		scenario6.getTests().add(CT03SaisieDossier(scenario6, outil));
		scenario6.getTests().add(CT04Participants(scenario6, outil));
		scenario6.getTests().add(CT05FinalisationInstruction(scenario6, outil));
		
	} catch (SeleniumException ex) {
		// Finalisation en erreur du cas de test.
		finaliserTestEnErreur(outil, scenario6, ex, scenario6.getNomCasEssai() + scenario6.getDateCreation().getTime());
		throw ex;
	}
	// Finalisation normale du cas de test.
	finaliserTest(outil, scenario6, scenario6.getNomCasEssai() + scenario6.getDateCreation().getTime());
}

/**
 * Partie du scenario6 regroupant la saisie, l'instruction et la validation d'un dossier cr�dit amortissable PP pour pr�t tr�sorerie imm�diat CASDEN dans IZIVENTE BP.
 * @param scenario le scenario englobant.
 * @param outil l'outil de manipulation du navigateur.
 * @return le cas d'essai document� pour ajout au sc�nario.
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIziventeBean CT01Initialisation(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT01
	CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
	CT01.setAlm(true);
	CT01.setNomCasEssai("CT01 -" + getTime());
	CT01.setDescriptif("CT01 - Acc�s Izivente et Initialisation");
	CT01.setNomTestPlan("CT01 - Acc�s Izivente et Initialisation");
	//Information issues du sc�nario.
	CT01.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT01.setCheminTestLab(scenario.getCheminTestLab());
	CT01.setNomTestLab(scenario.getNomTestLab());
	CT01.setRepertoireTelechargement(scenario.getRepertoireTelechargement());		
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
	modificateur.emprunteurSenior = true;
	modificateur.coEmprunteurJeune = true;
	modificateur.emprunteurCasden = true;
	//Steps : G�n�ration du bouchon - Acc�s � l'�cran de reroutage et injection du jeton - Acc�s � Izivente
	// Saisie du jeton 9384544
	String idClient = saisieJeton(outil, null, false, Constantes.CAS_BP, modificateur, null, null);
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
	CT02.setAlm(true);
	CT02.setNomCasEssai("CT02 -" + getTime());
	CT02.setDescriptif("CT02 - Ouverture du dossier");
	CT02.setNomTestPlan("CT02 - Ouverture du dossier");	
	//Information issues du sc�nario.
	CT02.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT02.setCheminTestLab(scenario.getCheminTestLab());
	CT02.setNomTestLab(scenario.getNomTestLab());
	CT02.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
	//Gestion des steps
	CT02.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT02.getNomCasEssai() + CT02.getTime()));
	CT02.ajouterStep("Choisir le mode de vente (Face � face ou Vente � distance)", "MODE", "Affichage de l'�cran d'instruction");
	CT02.ajouterStep("S�lectionner l'option d'ouverture d'un dossier correspondant au type voulu ", "OUVERTURE", "Affichage des pop ups de confirmation");
	CT02.ajouterStep("Fermeture des pop ups 'Information' et 'Attention' confirmant l'ouverture du dossier", "CONFIRMATION", "Affichage de l'�cran de donn�es client et de liste des dossiers");
	CT02.ajouterStep("V�rifier la coh�rence des donn�es du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'.", "SUIVANT", "Affichage de la pop un de Synth�se des informations client");
	CT02.ajouterStep("Valider les donn�es client en cliquant sur le bouton 'Valider' dans la pop up de synth�se des information client.", "VALIDATIONDONNEESCLIENT", "Affichage de l'�cran de demande de cr�dit");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// OUVERTURE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : Choisir le mode de vente (F�F ou V�D) - Cette etape est optionelle, le "Face � face" ne sera pas toujours visible
	outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
	CT02.validerObjectif(outil.getDriver(), "MODE", true);
	//Step 2 : S�lectionner l'option d'ouverture d'un dossier correspondant au type voulu. 
	outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
	CT02.validerObjectif(outil.getDriver(), "OUVERTURE", true);
	//Step 3 : Fermeture des pop ups 'Information' et 'Attention' confirmant l'ouverture du dossier
	outil.attendrePresenceTexte("ATTENTION");
	outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
	CT02.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
	//Step 4 : V�rifier la coh�rence des donn�es du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'
	outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
	CT02.validerObjectif(outil.getDriver(), "SUIVANT", true);
	//Step 5 : Valider les donn�es client en cliquant sur le bouton 'Valider' dans la pop up de synth�se des information client.
	outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
	CT02.validerObjectif(outil.getDriver(), "VALIDATIONDONNEESCLIENT", true);
	CT02.validerObjectif(outil.getDriver(), CT02.getNomCasEssai() + CT02.getTime(),true);
	return CT02;
}
	
public CasEssaiIziventeBean CT03SaisieDossier(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT03
	CasEssaiIziventeBean CT03 = new CasEssaiIziventeBean();
	CT03.setAlm(true);
	CT03.setNomCasEssai("CT03 -" + getTime());
	CT03.setDescriptif("CT03 - Saisie du dossier");
	CT03.setNomTestPlan("CT03 - Saisie du dossier");		
	//Information issues du sc�nario.
	CT03.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT03.setCheminTestLab(scenario.getCheminTestLab());
	CT03.setNomTestLab(scenario.getNomTestLab());
	CT03.setRepertoireTelechargement(scenario.getRepertoireTelechargement());		
	//Gestion des steps
	CT03.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT03.getNomCasEssai() + CT03.getTime()));
	CT03.ajouterStep("S�lectionner l'offre d�sir�e dans le menu d�roulant selon le sc�nario", "OFFRE", "Offre s�lectionn�e");
	CT03.ajouterStep("S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)", "PARAMETRES", "Param�tres coh�rents avec le sc�nario");
	CT03.ajouterStep("Cliquer sur le bouton 'Suivant' pour valider les informations du dossier", "SAISIEDOSSIER", "Affichage de l'�cran de s�lection des participants");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// SAISIE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : S�lectionner l'offre d�sir�e dans le menu d�roulant selon le sc�nario
	outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT, true, true);
	outil.selectionner("TRESORERIE", Cibles.SELECTEUR_UNIVERS_CREDIT, false);
	outil.attendre(2);
	outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
	outil.selectionner("CREDIT TRESORERIE CASDEN", Cibles.SELECTEUR_OFFRE_CREDIT, false);
	CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
	outil.attendre(2);
	//Step 2 : S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)
	outil.selectionner("TRESORERIE", Cibles.SELECTEUR_OBJET_FINANCE);
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
	outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
	outil.cliquer(Cibles.BOUTON_AJOUT_CONJOINT);
	CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
	//Step 2 : Pour chaque participant, choisir le r�le et l'assurance en fonction des hypoth�ses. Valider la listes des participants et confirmer l'assurance
	//Emprunteur :
	outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
	outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	outil.attendre(1);
	outil.cliquerMultiple(Cibles.CASE_SELECTION_REPONSE_ASSURANCE_NON);
	outil.cliquer(Cibles.BOUTON_OUI_DES);
	outil.attendreEtCliquer(Cibles.RADIO_SELECTION_ASSURANCE_DIM);
	//Co-emprunteur :
	outil.attendre(2);
	outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
	outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
	outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	CT04.validerObjectif(outil.getDriver(), "ASSURANCEROLE", true);
	//Step 3 : Valider la liste des participants
	outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	CT04.validerObjectif(outil.getDriver(),  "VALIDATIONPARTICIPANTS", true);
	CT04.validerObjectif(outil.getDriver(), CT04.getNomCasEssai() + CT04.getTime(),true);
	return CT04;
}
	
public CasEssaiIziventeBean CT05FinalisationInstruction(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT05
	CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
	CT05.setAlm(true);
	CT05.setNomCasEssai("CT05 -" + getTime());
	CT05.setDescriptif("CT05 - Finalisation de l instruction");
	CT05.setNomTestPlan("CT05 - Finalisation de l instruction");
	//Information issues du sc�nario.
	CT05.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT05.setCheminTestLab(scenario.getCheminTestLab());
	CT05.setNomTestLab(scenario.getNomTestLab());
	CT05.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
	//Gestion des steps
	CT05.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT05.getNomCasEssai() + CT05.getTime()));
	CT05.ajouterStep("Valider de l'offre contrat de cr�dit (clic sur le bouton 'Valider en contrat de cr�dit')", "VALIDATION", "Affichage de la pop up de finalisation de l'instruction");
	CT05.ajouterStep("Imprimer la synth�se en pdf (clic sur bouton 'Imprimer')", "IMPRESSIONSYNTHESE", "Le fichier pdf est correctement t�l�charge"); //Step facultatif
	CT05.ajouterStep("Finalisation de l'instruction (clic sur le bouton 'Oui' dans la popup de finalisation de l'instruction", "VALIDATIONINSTRUCTION", "Affichage de l'�cran de synth�se de l'offre de cr�dit");
	CT05.ajouterStep("Remplir le questionnaire pour la demande de financement � 8 jours et la r�ception de sollicitations commerciales partenaires (choix oui/non via boutons radio).", "OPTIONS", "Choix effectu�s conform�ment au sc�nario");
    CT05.ajouterStep("Impression de la liasse de document (clic sur le bouton 'Suivant')", "IMPRESSION", "Affichage de la pop up 'Pr�paration contrat'");
    CT05.ajouterStep("Attendre la fin de la pr�paration du contrat puis cliquer sur suivant pour envoi de la liasse", "PREPARATION", "Deconnexion d'Izivente");
    //Step PP
  	CT05.ajouterStep("Imprimer la synth�se de l'offtre en pdf (clic sur bouton 'Imprimer')", "IMPRESSIONSYNTHESE", "Le fichier pdf est correctement t�l�charge");
  	CT05.ajouterStep("S�lectionner le compte de pr�l�vement et valider l'offre de cr�dit (clic sur bouton 'Confirmer contrat de cr�dit')", "CONFIRMATION", "Affichage de la pop up 'Finalisation de l'instruction'");
  	CT05.ajouterStep("V�rifier les justificatifs et valider (clic bouton radio 'V�rifi�' pour chaque justificatif dans la pop up de finalisation de l'instruction' et clic sur bouton 'Valider'", "VERIFICATION", "Retour sur la page de dossier de vente");
  	
    /////////////////////////////////////////////////////////////////////////////////////////////////////
	// FINALISATION DE L'INSTRUCTION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : Valider de l'offre contrat de cr�dit
	outil.attendrePresenceTexte("Alerte(s)");
	//outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
	outil.cliquerSiPossible(Cibles.BOUTON_POPUP_OK_MAJ);
	//outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
	outil.attendreChargementElement(Cibles.BOUTON_ACCES_VALIDATION_OPC,true, true);
	outil.cliquer(Cibles.BOUTON_ACCES_VALIDATION_OPC);
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
	CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
	return CT05;
	}
}