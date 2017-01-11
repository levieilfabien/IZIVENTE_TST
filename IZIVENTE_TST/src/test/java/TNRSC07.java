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
 * Scénario 7 des tests automatisés pour IZIVENTE - 11/2016
 * Editique Permis 1 Euro (CE)
 * @author levieilfa bardouma
 */
public class TNRSC07 extends SC00Test {

/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	// Description du scénario
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
	
	// Création et configuration du repertoire de téléchargement
	//File repertoireTelechargement = new File(".\\" + scenario2.getNomCasEssai());
	//repertoireTelechargement.mkdir();
	//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
	String repertoire = creerRepertoireTelechargement(scenario7, profile);
	scenario7.setRepertoireTelechargement(repertoire);
	// Initialisation du driver
	FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
	driver.get(Constantes.URL_CE_FUTURE_REROUTAGE);
	// LISTE DES OBJECTIFS DU CAS DE TEST
	scenario7.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario7.getNomCasEssai() + scenario7.getTime()));
	
    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
    outil.setRepertoireRacine(scenario7.getRepertoireTelechargement());
    
    try {
    	//CT01 - Accès Izivente et initialisation
    	//CT02 - Ouverture du dossier et validation des informations client
    	//CT03 - Saisie des paramètres relatifs au type de dossier et validation
    	//CT04 - Choix des participants et des assurances associées et validation des participants
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
 * Partie du scenario7 regroupant la saisie, l'instruction et la validation d'un dossier Crédit Amortissable PP pour Permis 1 Euro dans IZIVENTE CE.
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
	// Jeton OK SC07 : 942500412
	ModificateurBouchon modificateur = new ModificateurBouchon();
	modificateur.emprunteurJeune = true;
	// Attente de l'affichage du titre de la page
	//Step 1 : Génération du bouchon client Izivente + Step 2 : Lancement de l'URL Izivente
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
	String idClient = generationBouchon(Constantes.CAS_CE, null, modificateur);
	CT01.validerObjectif(outil.getDriver(), "GENERATION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESREROUTAGE", true);
	//Step 3 : Récupérer numéro de bouchon généré et injecter le jeton
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
	outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
	// Cette étape n'as lieu que si le dossier est déjà porteur d'un autre dossier
	outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
	//Step 3 : Fermeture des pop ups 'Attention' confirmant l'ouverture du dossier
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
	outil.attendrePresenceTexte("Informations du crédit");
	outil.attendre(2);
	outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT_CE, true, true);
	outil.selectionner("TRESORERIE", Cibles.SELECTEUR_UNIVERS_CREDIT_CE, false);
	outil.attendre(5); //2 secondes ne suffisent pas
	outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
	outil.selectionner("PERMIS 1 EURO", Cibles.SELECTEUR_OFFRE_CREDIT, false);
	outil.attendre(2);
	CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
	//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
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
	//Step 1 : Choisir les participants en fonction de la fiche de prêt et Valider. On ajoute un tiers pour ce scénario
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
	outil.attendrePresenceTexte("Synthèse des informations sur le Tiers");
	outil.cliquer(Cibles.BOUTON_POPUP_VALIDER_SYNTHESE_TIERS);
	outil.attendrePresenceTexte("Attention");
	outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
	CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
	//Step 2 : Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses. Valider la listes des participants et confirmer l'assurance
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
	//Step PP
	CT05.ajouterStep("Imprimer la synthèse de l'offtre en pdf (clic sur bouton 'Imprimer')", "IMPRESSIONSYNTHESE", "Le fichier pdf est correctement télécharge");
	CT05.ajouterStep("Sélectionner le compte de prélèvement et valider l'offre de crédit (clic sur bouton 'Confirmer contrat de crédit')", "CONFIRMATION", "Affichage de la pop up 'Finalisation de l'instruction'");
	CT05.ajouterStep("Vérifier les justificatifs et valider (clic bouton radio 'Vérifié' pour chaque justificatif dans la pop up de finalisation de l'instruction' et clic sur bouton 'Valider'", "VERIFICATION", "Retour sur la page de dossier de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// FINALISATION DE L'INSTRUCTION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//Step 1 : Valider de l'offre contrat de crédit
	outil.attendrePresenceTexte("Alerte(s)");
	//outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
	outil.cliquerSiPossible(Cibles.BOUTON_POPUP_OK_MAJ);
	//outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
	CT05.validerObjectif(outil.getDriver(), "VALIDATION", true);
	//Step 2 : Sélectionner le compte de prélèvement et valider l'offre de crédit
	outil.attendrePresenceTexte("Dossier de vente");
	outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_OPC);
	CT05.validerObjectif(outil.getDriver(), "CONFIRMATION", true);
	//Step 3 : Vérifier les justificatifs et valider
	outil.attendreChargementElement(Cibles.ELEMENT_FIN_INSTRUCTION, true, true);
	outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
	outil.attendre(1);
	outil.cliquer(Cibles.BOUTON_POPUP_VALIDER_JUSTIFICATIFS);
	outil.attendre(4);
	CT05.validerObjectif(outil.getDriver(), "VERIFICATION", true);
	//Step 4 : Remplir le questionnaire pour la demande de financement à 8 jours et la réception de sollicitations commerciales partenaires
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
	CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
	return CT05;
	}
}