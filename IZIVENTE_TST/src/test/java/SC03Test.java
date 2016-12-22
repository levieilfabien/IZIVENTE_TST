package test.java;

import java.io.File;

import main.bean.CasEssaiIziventeBean;
import main.constantes.Cibles;
import main.constantes.Constantes;
import main.constantes.Environements;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import outils.SeleniumOutils;
import beans.ObjectifBean;
import exceptions.SeleniumException;

/**
 * Scénario 3 des tests pour IZIVENTE.
 * @author levieilfa
 *
 */
public class SC03Test extends SC00Test {

	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void accesIzivente() throws SeleniumException {
		
		CasEssaiIziventeBean scenario3 = new CasEssaiIziventeBean();
		scenario3.setNomCasEssai("SC03-" + getTime());
		scenario3.setDescriptif("SC03 - Souscription distributeur TRAVAUX CE");
		scenario3.setAlm(false);
		scenario3.setIdUniqueTestLab(49375);
		scenario3.setNomTestLab("SC03 - Souscription distributeur TRAVAUX CE");
		scenario3.setNomTestPlan("SC03 - IZIVENTE_Distributeur_Travaux");
		scenario3.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		// Configuration du dirver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		
		// Création et configuration du repertoire de téléchargement
//		File repertoireTelechargement = new File(".\\" + scenario3.getNomCasEssai());
//		repertoireTelechargement.mkdir();
//		profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		String repertoire = creerRepertoireTelechargement(scenario3, profile);
		scenario3.setRepertoireTelechargement(repertoire);
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario3.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario3.getNomCasEssai() + scenario3.getTime()));
		scenario3.ajouterObjectif(new ObjectifBean("Génération du bouchon", "BOUCHON", "BOUCHON" + scenario3.getTime(), "Génération du bouchon", ""));
		scenario3.ajouterObjectif(new ObjectifBean("Ajout du conjoint", "CONJOINT", "CONJOINT" + scenario3.getTime(), "Ajout du conjoint lors de la souscription", ""));
		scenario3.ajouterObjectif(new ObjectifBean("Ajout d'un tiers", "TIERS", "TIERS" + scenario3.getTime(), "Ajout du tiers lors de la souscription", ""));
		scenario3.ajouterObjectif(new ObjectifBean("Validation de l'OPC", "VALIDEROPC", "VALIDEROPC" + scenario3.getTime(), "Validation de l'OPC", ""));
		scenario3.ajouterObjectif(new ObjectifBean("Impression de la synthèse", "IMPRIMERSYNTHESE", "IMPRIMERSYNTHESE" + scenario3.getTime(), "Impression de la synthèse", ""));
		scenario3.ajouterObjectif(new ObjectifBean("Impression de la liasse complète", "IMPRIMERLIASSE", "IMPRIMERLIASSE" + scenario3.getTime(), "Impression de la liasse", ""));
		scenario3.ajouterObjectif(new ObjectifBean("Mise en force", "MISEENFORCE", "MISEENFORCE" + scenario3.getTime(), "Mise en force", ""));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		
		try {
		
			// Chargement d'une page de test
			//driver.get("https://open-workplace.intranatixis.com/nfi/front-middle/wiki-izivente/Rfrentiel/Liens%20Izivente.aspx");		
			//driver.get("https://nfi80.rec.intranatixis.com/izivente-bp_recB_current/reroutage.action");
			driver.get(Constantes.URL_CE_FUTURE_REROUTAGE);
			
			// Attente de l'affichage du titre de la page
			outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
			
			System.out.println("TOTO");
			
			// Récupération de l'élément contenant le jeton de reretoutage.
			String idClient = generationBouchon(Constantes.CAS_CE, "942500364");
			scenario3.setIdClient(idClient);
			// Attention nous sommes en producteur
			String chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + idClient + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>11315</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>1131500030000135</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>1131500030000135</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>DISTRIBUTEUR</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>";	
			outil.viderEtSaisir(chaineCible, Cibles.SAISIE_JETON);
		
			// Débuggage : La saisie est elle une succès ?
			//WebElement zoneJeton = outil.obtenirElementVisible(new CibleBean(Clefs.NAME, "reroutage:data:data"));
			//System.out.println(zoneJeton.getText());
			
			outil.cliquer(Cibles.VALIDER_SAISIE_JETON);
			
			outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
			
			/////////////////////////////////////////////// CHOIX DU MODE DE VENTE ////////////////////////////////////////////////
			// Cette etape est optionelle, le "Face à face" ne sera pas toujours visible
			outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
			// Cette étape n'as lieu que si le dossier est déjà porteur d'un autre dossier
			outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
			scenario3.validerObjectif(driver, "BOUCHON", true);
			
			/////////////////////////////////////////////// GESTION DE LA POPUP ////////////////////////////////////////////////
			// On vérifie la présence de la popup
			outil.attendrePresenceTexte("Attention");
			// On femre la popup
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
			
			/////////////////////////////////////////////// SAISIE DU DOSSIER ////////////////////////////////////////////////
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			outil.cliquer(Cibles.BOUTON_VALIDER);		
			// On remplis le formulaire.
			outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT, true, true);
			outil.selectionner("TRAVAUX", Cibles.SELECTEUR_UNIVERS_CREDIT);
			// A ce moment on recharge le formulaire, il faut faire patienter le test.
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
			outil.selectionner("PROJET TRAVAUX", Cibles.SELECTEUR_OFFRE_CREDIT, false);
			// A ce moment on recharge le formulaire, il faut faire patienter le test.
			outil.attendre(2);
			outil.selectionner("CUISINE", Cibles.SELECTEUR_OBJET_FINANCE);
			outil.viderEtSaisir("5000", Cibles.SAISIE_COUT_PROJET);
			//outil.selectionner("Aucun", Cibles.SELECTEUR_REPORT_PREMIERE_MENS, false);
			outil.viderEtSaisir("5000", Cibles.SAISIE_MONTANT_DEMANDE);
			outil.viderEtSaisir("48", Cibles.SAISIE_DUREE_DEMANDE);
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			
			/////////////////////////////////////////////// SAISIE DES PARTICIPANTS ////////////////////////////////////////////////
			// Ajout du participant conjoint
			outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
			outil.cliquer(Cibles.BOUTON_AJOUT_CONJOINT);
			
			// On gère l'assurance du co emprunteur conjoint
			outil.attendrePresenceTexte("Co-Emprunteur conjoint");
			scenario3.validerObjectif(driver, "CONJOINT", true);
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON);
			
			// On gère l'assurance de l'emprunteur
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendre(1);
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_OUI);
			
			/////////////////////////////////////////////// RECHERCHE D'UN TIERS ////////////////////////////////////////////////
			// On cherche à ajouter un nouveau participant tiers
			outil.attendreChargementElement(Cibles.ELEMENT_ENTETE_TABLEAU_ASSURANCE_CE);
			outil.cliquer(Cibles.BOUTON_AJOUT_AUTRE_PARTICIPANT);
			outil.attendreChargementElement(Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, true, true);
			// on fait une recherche par numéro de personne physique
			outil.selectionner("RCHNUMERO", Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, false);
			outil.attendreChargementElement(Cibles.SAISIE_NUMERO_PERS_PHY);
			//211000526 313002036
			outil.viderEtSaisir("313002036", Cibles.SAISIE_NUMERO_PERS_PHY);
			outil.cliquer(Cibles.BOUTON_RECHERCHER);
			
			/////////////////////////////////////////////// AJOUT TIERS RECHERCHE ////////////////////////////////////////////////
			// TODO la personne n'existe plus :(
			outil.attendreChargementElement(Cibles.BOUTON_AJOUT_TIERS);
			outil.cliquer(Cibles.BOUTON_AJOUT_TIERS);
			
			/////////////////////////////////////////////// GESTION DE LA POPUP ////////////////////////////////////////////////
			// On vérifie la présence de la popup
			outil.attendrePresenceTexte("Attention");
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
			
			/////////////////////////////////////////////// VALIDATION DU TIERS 1 ////////////////////////////////////////////////
			outil.cliquer(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_1);
			outil.attendrePresenceTexte(Constantes.TITRE_BLOC_SYNTHESE_TIERS);		
			outil.attendre(2);
			WebElement element = outil.obtenirElement(Cibles.BOUTON_VALIDER_SYNTHESE_TIERS);
			element.click();
			scenario3.validerObjectif(driver, "TIERS", true);
			/////////////////////////////////////////////// GESTION DES TIERS ////////////////////////////////////////////////
			// le premier tiers
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT2);
			outil.attendre(2);
			outil.cliquer(Cibles.LIBELLE_CHOIX_OUI);
			
			// Assurance du premier tiers
			outil.attendreChargementElement(Cibles.BOUTON_OUI_DES);
			outil.cliquer(Cibles.BOUTON_OUI_DES);
			outil.attendreChargementElement(Cibles.RADIO_ASS_OUI);
					
			// Role du premier tiers
			outil.cliquer(Cibles.RADIO_ASS_OUI);
			outil.attendrePresenceTexte("Choix du rôle de clienttiers036 DARIUS");
			outil.selectionner("Co-emprunteur", Cibles.SELECTEUR_ROLE_PARTICIPANT, true);
			outil.attendre(2);
			
			// Role du second tiers
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT3);
			outil.attendrePresenceTexte("Choix du rôle de clienttiers036 LOLA");
			outil.selectionner("Co-emprunteur", Cibles.SELECTEUR_ROLE_PARTICIPANT, true);
			outil.attendre(2);
			
			outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
			
			/////////////////////////////////////////////// GESTION DE LA SIMULATION ////////////////////////////////////////////////
			outil.attendreChargementElement(Cibles.ELEMENT_TABLEAU_NOTATION);
			outil.cliquer(Cibles.BOUTON_ACCES_VALIDATION_OPC);
			
			outil.attendreChargementElement(Cibles.BOUTON_VALIDER_OPC, true, true);
			outil.cliquer(Cibles.BOUTON_VALIDER_OPC);
			scenario3.validerObjectif(driver, "VALIDEROPC", true);
			/////////////////////////////////////////////// VERIFICATION DES PIECES JUSTIFICATIFS////////////////////////////////////////////////
			outil.attendreChargementElement(Cibles.ELEMENT_FIN_INSTRUCTION);
			outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
			outil.attendre(1);
			outil.cliquer(Cibles.BOUTON_POPUP_VALIDER_JUSTIFICATIFS);
			
			/////////////////////////////////////////////// EDITION ////////////////////////////////////////////////
			// Récupération du numéro FFI
			String numeroFFI = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMERO_FFI);
			scenario3.setNumeroFFI(numeroFFI);
			
			outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_SYNTHESE);
			outil.cliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE);
			scenario3.validerObjectif(driver, "IMPRIMERSYNTHESE", true);
			// Pas de demande de financement à 8 jours.
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
			
			outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_LIASSE, true, true);
			outil.cliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
			scenario3.validerObjectif(driver, "IMPRIMERLIASSE", true);
			
			// Dès l'impression de la liasse on à passe en mode signature electronique et nous n'avons pas à "terminer".
			//outil.attendreChargementElement(Cibles.BOUTON_TERMINER_EDITION, true, true);
			//outil.cliquer(Cibles.BOUTON_TERMINER_EDITION);
			
			// En mode distributeur on a une signature electronique
			outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
			outil.cliquer(Cibles.BOUTON_POPUP_SUIVANT);
			
			/////////////////////////////////////////////// RETOUR SUR L'IHM ////////////////////////////////////////////////
			//A ce moment la fenêtre devient vierge : on a quitter IZIVENTE. On y retourne
			driver.get(Constantes.URL_CE_FUTURE_REROUTAGE);
			// Attente de l'affichage du titre de la page
			outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
			// Attention nous sommes en producteur	
			outil.viderEtSaisir(chaineCible, Cibles.SAISIE_JETON);
			outil.cliquer(Cibles.VALIDER_SAISIE_JETON);
			outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
			outil.attendrePresenceTexte("Liste des dossiers");
			/////////////////////////////////////////////// PASSAGE A L'OCTROI ////////////////////////////////////////////////
			//TODO Prendre en charge sous une autre forme ces contrôles non disponible en version "distributeur".
			//Demande de financement à 8 jours cochée ?
			//		Liasse contractuelle datée et signée y compris éléments relatifs à l’assurance ?

			// On cherche le numéro FFI dans la page
			for (WebElement coche : outil.obtenirElements(Cibles.COCHES_LISTE_DOSSIER)) {
				coche.click();
				outil.attendre(1);
				if (outil.testerPresenceTexte(numeroFFI, true)) {
					break;
				}
			}	
			outil.cliquer(Cibles.BOUTON_PASSAGE_OCTROI);
			// On valide la popup de confirmation
			outil.attendrePresenceTexte("Demande de confirmation");
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			// On valide la complétude
			outil.attendrePresenceTexte("Complétude et justificatifs");
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			// On réalise l'acceptation
			outil.attendrePresenceTexte("Choisir la décision");
			outil.cliquer(Cibles.LIBELLE_ACCEPTATION);
			// Finalisation de l'octroi
			outil.attendreChargementElement(Cibles.SAISIE_DATE_DECISION, true, true);
			outil.cliquer(Cibles.BOUTON_FINALISATION_OCTROI);
			// Popup de finalisation de l'octroi
			outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI, true, true);
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI);
			outil.attendreChargementElement(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI, true, true);
			outil.cliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
			
			/////////////////////////////////////////////// VERIFICATION FINALE ////////////////////////////////////////////////
			outil.attendrePresenceTexte("Liste des dossiers");
			outil.attendrePresenceTexte("FORC");
			scenario3.validerObjectif(driver, "MISEENFORCE", true);
				
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, scenario3, ex, scenario3.getNomCasEssai() + scenario3.getDateCreation().getTime());
			throw ex;
		}
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario3, scenario3.getNomCasEssai() + scenario3.getDateCreation().getTime());
	}

}
