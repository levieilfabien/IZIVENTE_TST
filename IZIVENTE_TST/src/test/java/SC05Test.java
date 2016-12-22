package test.java;

import java.io.File;

import main.bean.CasEssaiIziventeBean;
import main.bean.ModificateurBouchon;
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

public class SC05Test extends SC00Test {
	
	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void accesIzivente() throws SeleniumException {
		
		// Mise en place d'information propre au scénario 5.
		CasEssaiIziventeBean scenario5 = new CasEssaiIziventeBean();
		scenario5.setNomCasEssai("SC05-" + getTime());
		scenario5.setDescriptif("SC05 - IZIVENTE_Producteur_Etudiant_Permis1euro");
		// Information propres à ALM
		scenario5.setAlm(true);
		scenario5.setIdUniqueTestLab(49395);
		scenario5.setNomTestLab("SC05 - CE - IZIVENTE_Producteur_ Permis 1 euro");
		scenario5.setNomTestPlan("SC05 - IZIVENTE_Producteur_Etudiant_Permis1euro");
		scenario5.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		// Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		
		// Création et configuration du repertoire de téléchargement
//		File repertoireTelechargement = new File(".\\" + scenario5.getNomCasEssai());
//		repertoireTelechargement.mkdir();
//		profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		String repertoire = creerRepertoireTelechargement(scenario5, profile);
		scenario5.setRepertoireTelechargement(repertoire);
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario5.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario5.getNomCasEssai() + scenario5.getTime()));
		scenario5.ajouterObjectif(new ObjectifBean("Instruction de la vente", "INSTRUCTION", "INSTRUCTION" + scenario5.getTime(), "Initialisation et instruction de la vente jusque la validation CA", ""));
		scenario5.ajouterObjectif(new ObjectifBean("Impression de la liasse", "EDITION", "EDITION" + scenario5.getTime(), "Edition de la liasse", ""));
		//scenario5.ajouterObjectif(new ObjectifBean("Mise en force du PP", "MISE_EN_FORCE", "MISE_EN_FORCE" + scenario5.getTime(), "Saisie complète du PP jusqu'à la mise en force", ""));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		
		try {
			// Chargement d'une page de test
			outil.chargerUrl(Constantes.URL_CE_FUTURE_REROUTAGE);
			
			// Attente de l'affichage du titre de la page
			outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
			
			// Création du modificateur de bouchon
			ModificateurBouchon modificateur = new ModificateurBouchon();
			modificateur.emprunteurJeune = true;
			String idClient = generationBouchon(Constantes.CAS_CE, null, modificateur);
			
			// Récupération de l'élément contenant le jeton de reretoutage.
			scenario5.setIdClient(idClient);
			// Attention nous sommes en producteur
			String chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + idClient + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>18315</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>1831500030000001</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>1831500030000001</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>PRODUCTEUR</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>";	
			outil.viderEtSaisir(chaineCible, Cibles.SAISIE_JETON);
		
			System.out.println(chaineCible);
			
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
			outil.selectionner("TRESORERIE", Cibles.SELECTEUR_UNIVERS_CREDIT, false);
			// A ce moment on recharge le formulaire, il faut faire patienter le test.
			outil.attendre(2);
			outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
			outil.selectionner("CREDIT PERMIS 1 EURO", Cibles.SELECTEUR_OFFRE_CREDIT, false);
			// A ce moment on recharge le formulaire, il faut faire patienter le test.
			outil.attendre(2);
			outil.viderEtSaisir("1200", Cibles.SAISIE_COUT_PROJET);
			//TODO  Selection de la demande à effectuer sur 1200 Euros / 40 mois => C'est la valeur par défaut.
			outil.selectionner("1240", Cibles.SELECTEUR_MONTANT_DUREE_DEMANDE);
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			
			/////////////////////////////////////////////// SAISIE DES PARTICIPANTS ////////////////////////////////////////////////
			// Pas d'ajout du participant conjoint
			outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
			
			// On ajoute un autre participant
			outil.attendreChargementElement(Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, true, true);
			// on fait une recherche par numéro de personne physique
			outil.selectionner("RCHNUMERO", Cibles.SELECTEUR_IDENTIFICATION_PARTICIPANT, false);
			outil.attendreChargementElement(Cibles.SAISIE_NUMERO_PERS_PHY);
			outil.viderEtSaisir("211000123", Cibles.SAISIE_NUMERO_PERS_PHY);
			outil.cliquer(Cibles.BOUTON_RECHERCHER);
			
			/////////////////////////////////////////////// AJOUT TIERS RECHERCHE ////////////////////////////////////////////////
			outil.attendreEtCliquer(Cibles.BOUTON_AJOUT_TIERS);
			
			/////////////////////////////////////////////// GESTION DE LA POPUP ////////////////////////////////////////////////
			// On vérifie la présence de la popup
			outil.attendrePresenceTexte("Attention");
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDATION_DETAILS_TIERS_1);
			outil.attendrePresenceTexte("Synthèse des informations sur le Tiers");
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_VALIDER_SYNTHESE_TIERS);
			
			// On gère l'assurance de l'emprunteur
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON);
			
			// On gère l'assurance de l'emprunteur
			outil.cliquer(Cibles.RADIO_SELECTION_PARTICIPANT1);
			outil.attendre(1);
			outil.attendreEtCliquer(Cibles.LIBELLE_CHOIX_NON);
			outil.attendreChargementElement(Cibles.SELECTEUR_ROLE_PARTICIPANT);
			outil.selectionner("C", Cibles.SELECTEUR_ROLE_PARTICIPANT);
			
			// Validation de la liste des participants
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
			// Popup note bale 2 incorrecte (ok)
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
			/////////////////////////////////////////////// GESTION DE LA SIMULATION ////////////////////////////////////////////////
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
			outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE_INSTRUCTION);
			//outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_OPC);
			scenario5.validerObjectif(driver, "INSTRUCTION", true);
			
			/////////////////////////////////////////////// VERIFICATION DES PIECES JUSTIFICATIFS////////////////////////////////////////////////
			outil.attendreChargementElement(Cibles.ELEMENT_FIN_INSTRUCTION, true, true);
			outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
			outil.attendre(1);
			outil.cliquer(Cibles.BOUTON_POPUP_VALIDER_JUSTIFICATIFS);
			
			/////////////////////////////////////////////// EDITION ////////////////////////////////////////////////
			// Récupération du numéro FFI
			String numeroFFI = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMERO_FFI);
			scenario5.setNumeroFFI(numeroFFI);
			// Gestion des impressions
			outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE);	
			outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
			scenario5.validerObjectif(driver, "EDITION", true);
			
			// LE CAS DE TEST s'arrête à la fin de l'édition, pas de mise en force.
				
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, scenario5, ex, scenario5.getNomCasEssai() + scenario5.getDateCreation().getTime());
			throw ex;
		}
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario5, scenario5.getNomCasEssai() + scenario5.getDateCreation().getTime());
	}
}
