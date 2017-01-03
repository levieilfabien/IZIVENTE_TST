	package test.java;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.WebElement;
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
 * Sc�nario 2 des test automatis� pour la mise en gestion de dossier
 * Editique Travaux �chelonn� (CE PP)
 * @author levieilfa bardouma
 */
public class FT4SCPPCETest extends SC00Test {

/**
 * Id de s�rialisation par d�faut.
 */
private static final long serialVersionUID = 1L;

@Test
public void accesIzivente() throws SeleniumException {
	// Description du sc�nario
	CasEssaiIziventeBean scenario4 = new CasEssaiIziventeBean();
	scenario4.setAlm(true);
	scenario4.setIdUniqueTestLab(54204);
	scenario4.setNomCasEssai("TNRSC04-" + getTime());
	scenario4.setDescriptif("TNRSC04 - CE - IZIVENTE_Editique Travaux Echelonn�");
	scenario4.setNomTestLab("TNRSC04 - CE - IZIVENTE_Editique Travaux Echelonn�");
	//scenario4.setNomTestPlan("TNRSC04 - IZIVENTE_Editique Travaux Echelonn�");
	scenario4.setCheminTestLab("POC Selenium\\IZIVENTE");
	
	// Configuration du driver
	FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
	FirefoxProfile profile = configurerProfilNatixis();
	
	// Cr�ation et configuration du repertoire de t�l�chargement
	//File repertoireTelechargement = new File(".\\" + scenario2.getNomCasEssai());
	//repertoireTelechargement.mkdir();
	//profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
	String repertoire = creerRepertoireTelechargement(scenario4, profile);
	scenario4.setRepertoireTelechargement(repertoire);
	// Initialisation du driver
	FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
	driver.get(Constantes.URL_CE_FUTURE_REROUTAGE);
	// LISTE DES OBJECTIFS DU CAS DE TEST
	scenario4.ajouterObjectif(new ObjectifBean("Test arriv� � terme", scenario4.getNomCasEssai() + scenario4.getTime()));
   
    SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
    outil.setRepertoireRacine(scenario4.getRepertoireTelechargement());
    
    try {
		//CT01 - Acc�s Izivente et initialisation
		//CT02 - Ouverture du dossier et validation des informations client
		//CT03 - Saisie des param�tres relatifs au type de dossier et validation
		//CT04 - Choix des participants et des assurances associ�es et validation des participants
		//CT05 - Validation de l'instruction
    	//CT06 - Mise en gestion
		scenario4.getTests().add(CT01Initialisation(scenario4, outil));
		scenario4.getTests().add(CT02OuvertureDossier(scenario4, outil));
		scenario4.getTests().add(CT03SaisieDossier(scenario4, outil));
		scenario4.getTests().add(CT04Participants(scenario4, outil));
		scenario4.getTests().add(CT05FinalisationInstruction(scenario4, outil));
		scenario4.getTests().add(CT06MiseGestion(scenario4, outil));
		ecritureFichierDonnees("CE", scenario4.getNumeroFFI(), scenario4.getIdClient(), null, "PP", scenario4.getFlag());
	} catch (SeleniumException ex) {
		// Finalisation en erreur du cas de test.
		finaliserTestEnErreur(outil, scenario4, ex, scenario4.getNomCasEssai() + scenario4.getDateCreation().getTime());
		throw ex;
	}
	// Finalisation normale du cas de test.
	finaliserTest(outil, scenario4, scenario4.getNomCasEssai() + scenario4.getDateCreation().getTime());
}

/**
 * Partie du scenario4 regroupant la saisie, l'instruction et la validation d'un dossier cr�dit amortissable PP pour travaux �chelonn� dans IZIVENTE CE.
 * @param scenario4 le scenario englobant.
 * @param outil l'outil de manipulation du navigateur.
 * @return le cas d'essai document� pour ajout au sc�nario.
 * @throws SeleniumException en cas d'erreur.
 */
public CasEssaiIziventeBean CT01Initialisation(CasEssaiIziventeBean scenario4, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT01
	CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
	//Gestion des steps
	CT01.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT01.getNomCasEssai() + CT01.getTime()));
	CT01.ajouterStep("G�n�ration du bouchon client Izivente", "GENERATION", "Cr�ation du bouchon termin�e");
	CT01.ajouterStep("Lancer l'URL Izivente en fonction du r�seau test�", "ACCESREROUTAGE", "Affichage de l'�cran de reroutage");
	CT01.ajouterStep("R�cup�rer num�ro de bouchon g�n�r� et injecter le jeton (copier/coller du code dans la zone de l'�cran de reroutage)", "INJECTION", "Jeton coll� dans la zone appropri�e");
	CT01.ajouterStep("Valider le jeton et lancer le reroutage (clic sur bouton 'Reroutage')", "ACCESIZIVENTE", "Affichage d'Izivente (�cran d'instruction ou pop up de mode de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// ACCES IZIVENTE ET INITIALISATION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// Jeton OK SC04 : 942500370
	ModificateurBouchon modificateur = new ModificateurBouchon();
	modificateur.emprunteurJeune = true;
	modificateur.coEmprunteurJeune = true;
	// Attente de l'affichage du titre de la page
	//Step 1 : G�n�ration du bouchon client Izivente + Step 2 : Lancement de l'URL Izivente
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
	String idClient = generationBouchon(Constantes.CAS_CE, null, modificateur);
	CT01.validerObjectif(outil.getDriver(), "GENERATION", true);
	CT01.validerObjectif(outil.getDriver(), "ACCESREROUTAGE", true);
	//Step 3 : R�cup�rer num�ro de bouchon g�n�r� et injecter le jeton
	scenario4.setIdClient(idClient);
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
public CasEssaiIziventeBean CT02OuvertureDossier(CasEssaiIziventeBean scenario4, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT02
	CasEssaiIziventeBean CT02 = new CasEssaiIziventeBean();
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
	CT02.validerObjectif(outil.getDriver(), "OUVERTURE", true);
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
	
public CasEssaiIziventeBean CT03SaisieDossier(CasEssaiIziventeBean scenario4, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT03
	CasEssaiIziventeBean CT03 = new CasEssaiIziventeBean();
	//Gestion des steps
	CT03.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT03.getNomCasEssai() + CT03.getTime()));
	CT03.ajouterStep("S�lectionner l'offre d�sir�e dans le menu d�roulant selon le sc�nario", "OFFRE", "Offre s�lectionn�e");
	CT03.ajouterStep("S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)", "PARAMETRES", "Param�tres coh�rents avec le sc�nario");
	CT03.ajouterStep("Cliquer sur le bouton 'Suivant' pour valider les informations du dossier", "SAISIEDOSSIER", "Affichage de l'�cran de s�lection des participants");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// SAISIE DU DOSSIER
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : S�lectionner l'offre d�sir�e dans le menu d�roulant selon le sc�nario
	scenario4.setFlag(1);
	outil.attendrePresenceTexte("Informations du cr�dit");
	outil.attendre(2);
	outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT_CE, true, true);
	outil.selectionner("TRAVAUX", Cibles.SELECTEUR_UNIVERS_CREDIT_CE, false);
	outil.attendre(5); //2 secondes ne suffisent pas
	outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
	outil.selectionner("TRAVAUX ECHELONNE DIFF TOTAL", Cibles.SELECTEUR_OFFRE_CREDIT, false);
	outil.attendre(2);
	CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
	//Step 2 : S�lectionner et saisir les param�tres li�es au sc�nario (ex : CMA, diff�r�, mensualit�, etc.)
	outil.selectionner("TRAVAUX RESIDENCE PRINCIPALE", Cibles.SELECTEUR_OBJET_FINANCE);
	outil.attendre(2);
	outil.viderEtSaisir("22000", Cibles.SAISIE_COUT_PROJET);
	outil.viderEtSaisir("12", Cibles.SAISIE_DUREE_DIFFERE_PARTIEL);
	outil.viderEtSaisir("22000", Cibles.SAISIE_MONTANT_DEMANDE);
	outil.viderEtSaisir("60", Cibles.SAISIE_DUREE_DEMANDE);
	CT03.validerObjectif(outil.getDriver(), "PARAMETRES", true);
	//Step 3 : Cliquer sur le bouton 'Suivant' pour valider les informations du dossier
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	CT03.validerObjectif(outil.getDriver(), "SAISIEDOSSIER", true);
	CT03.validerObjectif(outil.getDriver(), CT03.getNomCasEssai() + CT03.getTime(),true);
	return CT03;
}
	
public CasEssaiIziventeBean CT04Participants(CasEssaiIziventeBean scenario4, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT04
	CasEssaiIziventeBean CT04 = new CasEssaiIziventeBean();
	//Gestion des steps
	CT04.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT04.getNomCasEssai() + CT04.getTime()));
	CT04.ajouterStep("Choisir les participants en fonction de la fiche de pr�t et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le num�ro de personne physique, cliquer sur rechercher, v�rifier la coh�rence des donn�es du tiers  et  valider les donn�es du tiers. ", "PARTICIPANTS", "Affichage de l'�cran 'Synth�se des participants'");
	CT04.ajouterStep("Pour chaque participant, choisir le r�le et l'assurance en fonction des hypoth�ses.", "ASSURANCEROLE", "Les informations sont conformes et le bouton de validation des participants cliquable");
    CT04.ajouterStep("Valider la liste des participants (clic sur bouton correspondant)", "VALIDATIONPARTICIPANTS", "Affichage de l'�cran de 'Proposition' avec la grille alternative commerciale");
	
    /////////////////////////////////////////////////////////////////////////////////////////////////////
	// PARTICIPANTS
	/////////////////////////////////////////////////////////////////////////////////////////////////////		
	//Step 1 : Choisir les participants en fonction de la fiche de pr�t et Valider.
	outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
	outil.cliquer(Cibles.BOUTON_AJOUT_CONJOINT);
	CT04.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
	//Step 2 : Pour chaque participant, choisir le r�le et l'assurance en fonction des hypoth�ses. Valider la listes des participants et confirmer l'assurance
	//Assurance de l'emprunteur
	outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
	outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT0);
	outil.attendreChargementElement(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	outil.cliquer(Cibles.LIBELLE_CHOIX_OUI_MAJ);
	outil.attendreChargementElement(Cibles.CASE_SELECTION_REPONSE_ASSURANCE_NON);
	outil.cliquerMultiple(Cibles.CASE_SELECTION_REPONSE_ASSURANCE_NON);
	outil.cliquer(Cibles.BOUTON_OUI_DES);
	outil.attendreEtCliquer(Cibles.RADIO_SELECTION_ASSURANCE_DIM);
	//Assurance du co emprunteur
	outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
	outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
	outil.attendre(2);
	outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
	CT04.validerObjectif(outil.getDriver(), "ASSURANCEROLE", true);
	//Step 3 : Valider la liste des participants
	outil.attendreChargementElement(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
	CT04.validerObjectif(outil.getDriver(),  "VALIDATIONPARTICIPANTS", true);
	CT04.validerObjectif(outil.getDriver(), CT04.getNomCasEssai() + CT04.getTime(),true);
	return CT04;
}
	
public CasEssaiIziventeBean CT05FinalisationInstruction(CasEssaiIziventeBean scenario4, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT05
	CasEssaiIziventeBean CT05 = new CasEssaiIziventeBean();
	//Gestion des steps
	CT05.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT05.getNomCasEssai() + CT05.getTime()));
	CT05.ajouterStep("Valider de l'offre contrat de cr�dit (clic sur le bouton 'Valider en contrat de cr�dit')", "VALIDATION", "Affichage de la pop up de finalisation de l'instruction ou de la page de dossier de vente");
	CT05.ajouterStep("Finalisation de l'instruction (clic sur le bouton 'Oui' dans la popup de finalisation de l'instruction", "VALIDATIONINSTRUCTION", "Affichage de l'�cran de synth�se de l'offre de cr�dit");
	CT05.ajouterStep("Remplir le questionnaire pour la demande de financement � 8 jours et la r�ception de sollicitations commerciales partenaires (choix oui/non via boutons radio).", "OPTIONS", "Choix effectu�s conform�ment au sc�nario");
    CT05.ajouterStep("Imprimer la liasse de document (clic sur le bouton 'Suivant')", "IMPRESSION", "Affichage de la pop up 'Pr�paration contrat'");
    CT05.ajouterStep("Attendre la fin de la pr�paration du contrat puis cliquer sur suivant pour envoi � l'octroi", "PREPARATION", "Deconnexion d'Izivente");
	//Step PP
	CT05.ajouterStep("Imprimer la synth�se de l'offtre en pdf (clic sur bouton 'Imprimer')", "IMPRESSIONSYNTHESE", "Le fichier pdf est correctement t�l�charge");
	//CT05.ajouterStep("S�lectionner le compte de pr�l�vement et valider l'offre de cr�dit (clic sur bouton 'Confirmer contrat de cr�dit')", "CONFIRMATION", "Affichage de la pop up 'Finalisation de l'instruction'");
	CT05.ajouterStep("V�rifier les justificatifs et valider (clic bouton radio 'V�rifi�' pour chaque justificatif dans la pop up de finalisation de l'instruction' et clic sur bouton 'Valider'", "VERIFICATION", "Retour sur la page de dossier de vente");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// FINALISATION DE L'INSTRUCTION
	/////////////////////////////////////////////////////////////////////////////////////////////////////
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
	//Step optional : r�cup�ration du num�ro FFI pour la mise en gestion
	String numeroFFI = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMERO_FFI);
	scenario4.setNumeroFFI(numeroFFI);
	//Step 5 : Imprimer la synth�se de l'offre
	outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE);
	CT05.validerObjectif(outil.getDriver(), "IMPRESSIONSYNTHESE", true);
	//Step 6 : Imprimer la liasse de document
	outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
	outil.attendrePresenceTexte("Pr�paration contrat");
	CT05.validerObjectif(outil.getDriver(), "IMPRESSION", true);
	//Step 7 : Pr�paration du contrat et envoi � l'octroi		
	outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT);
	scenario4.setFlag(2);
	CT05.validerObjectif(outil.getDriver(), "PREPARATION", true);
	CT05.validerObjectif(outil.getDriver(), CT05.getNomCasEssai() + CT05.getTime(),true);
	return CT05;
	}

public CasEssaiIziventeBean CT06MiseGestion(CasEssaiIziventeBean scenario4, SeleniumOutils outil) throws SeleniumException {
	//Param�trage du CT06
	CasEssaiIziventeBean CT06 = new CasEssaiIziventeBean();
	CT06.setAlm(true);
	CT06.setNomCasEssai("CT06 -" + getTime());
	CT06.setDescriptif("CT06 - Mise en gestion");
	CT06.setNomTestPlan("CT06 - Mise en gestion");
	//Information issues du sc�nario.
	CT06.setIdUniqueTestLab(scenario4.getIdUniqueTestLab());
	CT06.setCheminTestLab(scenario4.getCheminTestLab());
	CT06.setNomTestLab(scenario4.getNomTestLab());
	CT06.setRepertoireTelechargement(scenario4.getRepertoireTelechargement());
	//Gestion des steps
	CT06.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT06.getNomCasEssai() + CT06.getTime()));
	CT06.ajouterStep("Relancement d'Izivente et retour sur le dossier", "RETOUR", "Affichage de la page d'accueil d'Izivente avec injection du jeton");
	CT06.ajouterStep("Ouverture et du dossier et recherche du num�ro FFI", "RECHERCHE", "Affichage des donn�es dossier et client");
	CT06.ajouterStep("Passage � l'octroi et premi�res v�rification", "OCTROI", "Dossier accept� pour l'octroi ");
	CT06.ajouterStep("Finalisation de l'octroi et derni�res confirmations avant mise en gestion", "FINALISATION", "Affichage des donn�es dossiers et client avec �tat FORC");
	CT06.ajouterStep("V�rification des donn�es dossier et client", "MISENFORCE", "Dossier � l'�tat FORC");
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////// RETOUR SUR L'IHM ////////////////////////////////////////////////
	//Step 1 : Rechargement de l'URL d'Izivente et r�injection du jeton
	outil.chargerUrl(Constantes.URL_CE_FUTURE_REROUTAGE); 
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
	String chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + scenario4.getIdClient() + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>11315</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>1131500030000135</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>1131500030000135</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>DISTRIBUTEUR</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>"; 
	outil.viderEtSaisir(chaineCible, Cibles.SAISIE_JETON);
	outil.cliquer(Cibles.VALIDER_SAISIE_JETON);
	outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
	CT06.validerObjectif(outil.getDriver(), "RETOUR", true);
	//Step 2 : Ouverture du dossier et recherche du num�ro FFI
	outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
	outil.attendrePresenceTexte("Liste des dossiers");
	for (WebElement coche : outil.obtenirElements(Cibles.COCHES_LISTE_DOSSIER)) {
		coche.click();
		outil.attendre(1);
		if (outil.testerPresenceTexte(scenario4.getNumeroFFI(), true)) {
			break;
		}
	}
	CT06.validerObjectif(outil.getDriver(), "RECHERCHE", true);
	// Step 3 : Passage � l'octroi
	outil.cliquer(Cibles.BOUTON_PASSAGE_OCTROI);
	outil.attendrePresenceTexte("Demande de confirmation");
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
	outil.attendrePresenceTexte("Compl�tude et justificatifs");
	outil.cliquer(Cibles.BOUTON_SUIVANT);
	outil.attendrePresenceTexte("Choisir la d�cision");
	outil.cliquer(Cibles.LIBELLE_ACCEPTATION);
	CT06.validerObjectif(outil.getDriver(), "OCTROI", true);
	//Step 4 : Finalisation de l'octroi
	outil.attendreChargementElement(Cibles.SAISIE_DATE_DECISION, true, true);
	outil.cliquer(Cibles.BOUTON_FINALISATION_OCTROI);
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI, true, true);
	outil.cliquer(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI);
	outil.attendreChargementElement(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI, true, true);
	outil.cliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
	CT06.validerObjectif(outil.getDriver(), "FINALISATION", true);
	//Step 5 : V�rification du passage � l'�tat FORC
	outil.attendrePresenceTexte("Liste des dossiers");
	outil.attendrePresenceTexte("FORC");
	scenario4.setFlag(3);
	CT06.validerObjectif(outil.getDriver(), "MISEENFORCE", true);
	return CT06;
}
}