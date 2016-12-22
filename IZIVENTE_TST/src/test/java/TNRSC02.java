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
 * Scénario 2 des tests automatisés pour IZIVENTE - 11/2016
 * Editique IZICARTE (CE)
 * @author levieilfa bardouma
 */
public class TNRSC02 extends SC00Test {

	/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	// Description du scénario
	CasEssaiIziventeBean scenario2 = new CasEssaiIziventeBean();
	scenario2.setAlm(true);
	scenario2.setIdUniqueTestLab(54391);
	scenario2.setNomCasEssai("TNRSC02-" + getTime());
	scenario2.setDescriptif("TNRSC02 - CE - IZIVENTE_Editique Izicarte");
	scenario2.setNomTestLab("TNRSC02 - CE - IZIVENTE_Editique Izicarte");
	//scenario2.setNomTestPlan("TNRSC02 - IZIVENTE_Editique Izicarte");
	scenario2.setCheminTestLab("POC Selenium\\IZIVENTE");
	
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
	driver.get(Constantes.URL_CE_FUTURE_REROUTAGE);
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
		
	} catch (SeleniumException ex) {
		// Finalisation en erreur du cas de test.
		finaliserTestEnErreur(outil, scenario2, ex, scenario2.getNomCasEssai() + scenario2.getDateCreation().getTime());
		throw ex;
	}
	// Finalisation normale du cas de test.
	finaliserTest(outil, scenario2, scenario2.getNomCasEssai() + scenario2.getDateCreation().getTime());
}



/**
 * Partie du scenario2 regroupant la saisie, l'instruction et la validation d'un dossier CR IZICARTE dans IZIVENTE CE.
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
	// Jeton OK SC02 : 942500370
	//Step 1 : Génération du bouchon client Izivente + Step 2 : Lancement de l'URL Izivente
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
	String idClient = generationBouchon(Constantes.CAS_CE, null);
	CT01.validerObjectif(outil.getDriver(), "GENERATION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESREROUTAGE", true);
	//Step 3 : Récupérer numéro de bouchon généré et injecter le jeton
	scenario.setIdClient(idClient);
	String chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>"+ idClient +"</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>11315</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>1131500030000135</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>1131500030000135</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>DISTRIBUTEUR</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>"; 
	outil.viderEtSaisir(chaineCible, Cibles.SAISIE_JETON);
	CT01.validerObjectif(outil.getDriver(), "INJECTION", true);
	//Step 4 : Valider le jeton et lancer le reroutage (clic sur bouton 'Reroutage')
	outil.cliquer(Cibles.VALIDER_SAISIE_JETON);
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
	CT01.validerObjectif(outil.getDriver(), "ACCESIZIVENTE", true);
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
	outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_IZICARTE);
	// Cette étape n'as lieu que si le dossier est déjà porteur d'un autre dossier
	outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
	CT02.validerObjectif(outil.getDriver(), "OUVERTURE", true);
	//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
	outil.attendrePresenceTexte("Information");
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendrePresenceTexte("Attention");
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
	
public CasEssaiIziventeBean CT03SaisieDossier(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT03
	CasEssaiIziventeBean CT03 = new CasEssaiIziventeBean();
	CT03.setAlm(true);
	CT03.setNomCasEssai("CT03 -" + getTime());
	CT03.setDescriptif("CT03 - Saisie du dossier");
	CT03.setNomTestPlan("CT03 - Saisie du dossier");
	//Information issues du scénario.
	CT03.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT03.setNomTestLab(scenario.getNomTestLab());
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
	outil.attendrePresenceTexte("Informations du crédit");
	outil.attendre(2);
	CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
	//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
	outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
	outil.selectionner("BANC", Cibles.SELECTEUR_SITUATION_VENTE_CR);
	outil.attendre(2);
	outil.attendreChargementElement(Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR, true, true);
	outil.viderEtSaisir("8000", Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
	CT03.validerObjectif(outil.getDriver(), "PARAMETRES", true);
	//Step 3 : Cliquer sur le bouton 'Suivant' pour valider les informations du dossier
	outil.attendre(2);
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
	
public CasEssaiIziventeBean CT05FinalisationInstruction(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
	//Paramétrage du CT05
	CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
	CT05.setAlm(true);
	CT05.setNomCasEssai("CT05 -" + getTime());
	CT05.setDescriptif("CT05 - Finalisation de l instruction");
	CT05.setNomTestPlan("CT05 - Finalisation de l instruction");
	//Information issues du scénario.
	CT05.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
	CT05.setCheminTestLab(scenario.getCheminTestLab());
	CT05.setNomTestLab(scenario.getNomTestLab());
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
	//Step 7 : Acceptation du contrat - envoi à l'octroi
	outil.attendreChargementElement(Cibles.BOUTON_SUIVANT);
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	outil.attendreChargementElement(Cibles.LIBELLE_ACCEPTATION);
	outil.cliquer(Cibles.LIBELLE_ACCEPTATION);
	outil.attendre(7);
	outil.attendreChargementElement(Cibles.BOUTON_FINALISATION_OCTROI_CR);
	outil.cliquer(Cibles.BOUTON_FINALISATION_OCTROI_CR);
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
	outil.attendreEtCliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
	CT05.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
	CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
	return CT05;
	}
}