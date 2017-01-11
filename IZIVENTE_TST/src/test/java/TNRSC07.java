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
 * Sc�nario 7 des tests automatis�s pour IZIVENTE - 11/2016
 * Editique Permis 1 Euro (CE)
 * @author levieilfa bardouma
 */
public class TNRSC07 extends SC00Test {

/**
 * Id de s�rialisation par d�faut.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	// Description du sc�nario
	CasEssaiIziventeBean scenario7 = new CasEssaiIziventeBean();
	scenario7.setAlm(true);
	scenario7.setIdUniqueTestLab(54394);
	scenario7.setNomCasEssai("TNRSC07-" + getTime());
	scenario7.setDescriptif("TNRSC07 - CE - IZIVENTE_Editique Permis 1 Euro");
	scenario7.setNomTestLab("TNRSC07 - CE - IZIVENTE_Editique Permis 1 Euro");
	//scenario7.setNomTestPlan("TNRSC07 - IZIVENTE_Editique Permis 1 Euro");
	scenario7.setCheminTestLab("POC Selenium\\IZIVENTE");
	
	// Configuration du driver
	FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
	FirefoxProfile profile = configurerProfilNatixis();
	
	// Cr�ation et configuration du repertoire de t�l�chargement
	//File repertoireTelechargement = new File(".\\" + scenario2.getNomCasEssai());
	//repertoireTelechargement.mkdir();
	//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
	String repertoire = creerRepertoireTelechargement(scenario7, profile);
	scenario7.setRepertoireTelechargement(repertoire);
	// Initialisation du driver
	FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
	driver.get(Constantes.URL_CE_FUTURE_REROUTAGE);
	// LISTE DES OBJECTIFS DU CAS DE TEST
	scenario7.ajouterObjectif(new ObjectifBean("Test arriv� � terme", scenario7.getNomCasEssai() + scenario7.getTime()));
	
    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
    outil.setRepertoireRacine(scenario7.getRepertoireTelechargement());
    
    try {
    	//CT01 - Acc�s Izivente et initialisation
    	//CT02 - Ouverture du dossier et validation des informations client
    	//CT03 - Saisie des param�tres relatifs au type de dossier et validation
    	//CT04 - Choix des participants et des assurances associ�es et validation des participants
    	//CT05 - Validation de l'instruction
		scenario7.getTests().add(CT01Initialisation(scenario7, outil));
		scenario7.getTests().add(CT02OuvertureDossier(scenario7, outil));
		scenario7.getTests().add(CT03SaisieDossier(scenario7, outil));
		scenario7.getTests().add(CT04Participants(scenario7, outil));
		scenario7.getTests().add(CT05FinalisationInstruction(scenario7, outil));
		
	} catch (SeleniumException ex) {
		// Finalisation en erreur du cas de test.
		finaliserTestEnErreur(outil, scenario7, ex, scenario7.getNomCasEssai() + scenario7.getDateCreation().getTime());
		throw ex;
	}
	// Finalisation normale du cas de test.
	finaliserTest(outil, scenario7, scenario7.getNomCasEssai() + scenario7.getDateCreation().getTime());
}


/**
 * Partie du scenario7 regroupant la saisie, l'instruction et la validation d'un dossier Cr�dit Amortissable PP pour Permis 1 Euro dans IZIVENTE CE.
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
	// Jeton OK SC07 : 942500412
	ModificateurBouchon modificateur = new ModificateurBouchon();
	modificateur.emprunteurJeune = true;
	// Attente de l'affichage du titre de la page
	//Step 1 : G�n�ration du bouchon client Izivente + Step 2 : Lancement de l'URL Izivente
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
	String idClient = generationBouchon(Constantes.CAS_CE, null, modificateur);
	CT01.validerObjectif(outil.getDriver(), "GENERATION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESREROUTAGE", true);
	//Step 3 : R�cup�rer num�ro de bouchon g�n�r� et injecter le jeton
	scenario.setIdClient(idClient);
	String chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + idClient + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>11315</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>1131500030000135</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>1131500030000135</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>DISTRIBUTEUR</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>"; 
	outil.viderEtSaisir(chaineCible, Cibles.SAISIE_JETON);
	CT01.validerObjectif(outil.getDriver(), "INJECTION", true);
	//Step 4 : Valider le jeton et lancer le reroutage (clic sur bouton 'Reroutage')
	outil.cliquer(Cibles.VALIDER_SAISIE_JETON);
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
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
	outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
	// Cette �tape n'as lieu que si le dossier est d�j� porteur d'un autre dossier
	outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
	//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
	outil.attendrePresenceTexte("Attention");
	outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
	CT02.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
	//Step 3 : V�rifier la coh�rence des donn�es du client, du conjoint si existant et du budget. Cliquer sur le bouton 'Suivant'
	outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
	outil.attendrePresenceTexte("Synth�se");
	CT02.validerObjectif(outil.getDriver(),  "SUIVANT", true);
	//Step 4 : Valider les donn�es client en cliquant sur le bouton 'Valider' dans la pop up de synth�se des information client.
	outil.cliquer(Cibles.BOUTON_VALIDER);
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
	outil.attendrePresenceTexte("Informations du cr�dit");
	outil.attendre(2);
	outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT_CE, true, true);
	outil.selectionner("TRESORERIE", Cibles.SELECTEUR_UNIVERS_CREDIT_CE, false);
	outil.attendre(5); //2 secondes ne suffisent pas
	outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
	outil.selectionner("PERMIS 1 EURO", Cibles.SELECTEUR_OFFRE_CREDIT, false);
	outil.attendre(2);
	CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
	//Step 2 : S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)
	outil.selectionner("DIVERS", Cibles.SELECTEUR_OBJET_FINANCE);
	outil.viderEtSaisir("800", Cibles.SAISIE_COUT_PROJET);
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
	//Step 1 : Choisir les participants en fonction de la fiche de pr�t et Valider. On ajoute un tiers pour ce sc�nario
    //Recherche du tiers
    outil.attendreChargementElement(Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, true, true);
	outil.selectionner("RCHNUMERO", Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, false);
	outil.attendreChargementElement(Cibles.SAISIE_NUMERO_PERS_PHY);
	outil.viderEtSaisir("942500400", Cibles.SAISIE_NUMERO_PERS_PHY);
	outil.cliquer(Cibles.BOUTON_RECHERCHER);
	//Ajout du tiers
    outil.attendreChargementElement(Cibles.BOUTON_AJOUT_TIERS);
	outil.cliquer(Cibles.BOUTON_AJOUT_TIERS);
	outil.attendreChargementElement(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_1);
	outil.cliquer(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_1);
	outil.attendrePresenceTexte("Synth�se des informations sur le Tiers");
	outil.cliquer(Cibles.BOUTON_POPUP_VALIDER_SYNTHESE_TIERS);
	outil.attendrePresenceTexte("Attention");
	outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
	CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
	//Step 2 : Pour chaque participant, choisir le r�le et l'assurance en fonction des hypoth�ses. Valider la listes des participants et confirmer l'assurance
	//Assurance de l'emprunteur
	outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
	outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
	outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_NON_MAJ);
	outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
	outil.attendre(1);
	//Assurance du tiers
	outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
	outil.attendre(1);
	outil.selectionner("G", Cibles.SELECTEUR_ROLE_PARTICIPANT);
	outil.attendreEtCliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
	outil.attendre(2);
	//Suppression du conjoint de la liste des participants
	outil.cliquer(Cibles.BOUTON_SUPP_PARTICIPANT_2_CE);
	outil.attendrePresenceTexte("Demande de confirmation de suppression");
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
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
	CT05.ajouterStep("Valider de l'offre contrat de cr�dit (clic sur le bouton 'Valider en contrat de cr�dit')", "VALIDATION", "Affichage de la pop up de finalisation de l'instruction ou de la page de dossier de vente");
	CT05.ajouterStep("Finalisation de l'instruction (clic sur le bouton 'Oui' dans la popup de finalisation de l'instruction", "VALIDATIONINSTRUCTION", "Affichage de l'�cran de synth�se de l'offre de cr�dit");
	CT05.ajouterStep("Remplir le questionnaire pour la demande de financement � 8 jours et la r�ception de sollicitations commerciales partenaires (choix oui/non via boutons radio).", "OPTIONS", "Choix effectu�s conform�ment au sc�nario");
    CT05.ajouterStep("Imprimer la liasse de document (clic sur le bouton 'Suivant')", "IMPRESSION", "Affichage de la pop up 'Pr�paration contrat'");
    CT05.ajouterStep("Attendre la fin de la pr�paration du contrat puis cliquer sur suivant pour envoi � l'octroi", "PREPARATION", "Deconnexion d'Izivente");
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
	CT05.validerObjectif(outil.getDriver(), "VALIDATION", true);
	//Step 2 : S�lectionner le compte de pr�l�vement et valider l'offre de cr�dit
	outil.attendrePresenceTexte("Dossier de vente");
	outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_OPC);
	CT05.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
	//Step 3 : V�rifier les justificatifs et valider
	outil.attendreChargementElement(Cibles.ELEMENT_FIN_INSTRUCTION, true, true);
	outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
	outil.attendre(1);
	outil.cliquer(Cibles.BOUTON_POPUP_VALIDER_JUSTIFICATIFS);
	outil.attendre(4);
	CT05.validerObjectif(outil.getDriver(), "VERIFICATION", true);
	//Step 4 : Remplir le questionnaire pour la demande de financement � 8 jours et la r�ception de sollicitations commerciales partenaires
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