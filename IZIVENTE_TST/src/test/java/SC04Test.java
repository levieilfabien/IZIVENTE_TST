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

import outils.PropertiesOutil;
import outils.SeleniumOutils;
import beans.ObjectifBean;
import exceptions.SeleniumException;

public class SC04Test extends SC00Test {
	
	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void accesIzivente() throws SeleniumException {
		
		// Mise en place d'information propre au scénario 4.
		CasEssaiIziventeBean scenario4 = new CasEssaiIziventeBean();
		scenario4.setNomCasEssai("SC04-" + getTime());
		scenario4.setDescriptif("SC04 - Souscription couplée distributeur TRESORERIE CE");
		// Information propres à ALM
		scenario4.setAlm(true);
		scenario4.setIdUniqueTestLab(49394);
		scenario4.setNomTestLab("SC04 - Souscription couplée distributeur TRESORERIE CE");
		scenario4.setNomTestPlan("SC04 - IZIVENTE_Distributeur_VenteCouplee_TRESORERIE");
		scenario4.setCheminTestLab("POC Selenium\\IZIVENTE");

		// Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		
		// Création et configuration du repertoire de téléchargement
//		File repertoireTelechargement = new File(".\\" + scenario4.getNomCasEssai());
//		repertoireTelechargement.mkdir();
//		profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		String repertoire = creerRepertoireTelechargement(scenario4, profile);
		scenario4.setRepertoireTelechargement(repertoire);
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario4.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario4.getNomCasEssai() + scenario4.getTime()));
		scenario4.ajouterObjectif(new ObjectifBean("Validation de l'OPC", "VALIDEROPC", "VALIDEROPC" + scenario4.getTime(), "La validation de l'OPC est OK", ""));
		scenario4.ajouterObjectif(new ObjectifBean("Impression synthèse", "IMPRIMERSYNTHESE", "IMPRIMERSYNTHESE" + scenario4.getTime(), "Impression de la synthèse", ""));
		scenario4.ajouterObjectif(new ObjectifBean("Saisie complète CR", "SAISIEDUCRCOUPLE", "SAISIEDUCRCOUPLE" + scenario4.getTime(), "Saisie complète du CR couplé jusqu'à la mise à l'octroi", ""));
		scenario4.ajouterObjectif(new ObjectifBean("Mise en force du PP", "MEFPP", "MEFPP" + scenario4.getTime(), "Saisie complète du PP jusqu'à la mise en force", ""));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		
		try {
			// Chargement d'une page de test
			driver.get(Constantes.URL_CE_FUTURE_REROUTAGE);
			
			// Attente de l'affichage du titre de la page
			outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
			
			// Récupération de l'élément contenant le jeton de reretoutage.
			String idClient = generationBouchon(Constantes.CAS_CE, null);
			scenario4.setIdClient(idClient);
			// Attention nous sommes en producteur
			String chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + idClient + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>18315</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>1831500030000001</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>1831500030000001</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>DISTRIBUTEUR</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>";	
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
			outil.selectionner("CREDIT TRESORERIE", Cibles.SELECTEUR_OFFRE_CREDIT, false);
			// A ce moment on recharge le formulaire, il faut faire patienter le test.
			outil.attendre(2);
			outil.selectionner("TRESORERIE", Cibles.SELECTEUR_OBJET_FINANCE);
			outil.viderEtSaisir("3000", Cibles.SAISIE_COUT_PROJET);
			outil.selectionner("Aucun", Cibles.SELECTEUR_REPORT_PREMIERE_MENS, false);
			outil.viderEtSaisir("3000", Cibles.SAISIE_MONTANT_DEMANDE);
			outil.viderEtSaisir("12", Cibles.SAISIE_DUREE_DEMANDE);
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			
			/////////////////////////////////////////////// SAISIE DES PARTICIPANTS ////////////////////////////////////////////////
			// Ajout du participant conjoint
			outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
			
			// Cliquer sur "Aucun co emprunteur"
			outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
			
			// On gère l'assurance de l'emprunteur
			outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON);
			outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
			
			/////////////////////////////////////////////// GESTION DE LA SIMULATION ////////////////////////////////////////////////
			outil.attendreChargementElement(Cibles.ELEMENT_TABLEAU_NOTATION);
			outil.cliquer(Cibles.BOUTON_ACCES_VALIDATION_OPC);
			
			outil.attendreChargementElement(Cibles.BOUTON_VALIDER_OPC, true, true);
			outil.cliquer(Cibles.BOUTON_VALIDER_OPC);
			scenario4.validerObjectif(driver, "VALIDEROPC", true);
			
			/////////////////////////////////////////////// VERIFICATION DES PIECES JUSTIFICATIFS////////////////////////////////////////////////
			outil.attendreChargementElement(Cibles.ELEMENT_FIN_INSTRUCTION, true, true);
			outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
			outil.attendre(1);
			outil.cliquer(Cibles.BOUTON_POPUP_VALIDER_JUSTIFICATIFS);
			
			/////////////////////////////////////////////// EDITION ////////////////////////////////////////////////
			// Récupération du numéro FFI
			outil.attendreChargementElement(Cibles.ELEMENT_SPAN_NUMERO_FFI);
			String numeroFFI = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMERO_FFI);
			scenario4.setNumeroFFI(numeroFFI);
			
			outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_SYNTHESE);
			outil.cliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE);
			scenario4.validerObjectif(driver, "IMPRIMERSYNTHESE", true);
			// Pas de demande de financement à 8 jours.
			outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
			
			outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_LIASSE, true, true);
			outil.cliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
			
			// En mode distributeur on a une signature electronique, on cherche à vendre un CR Couplé
			outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
			outil.attendreChargementElement(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_INSTRUIRE_CR_COUPLE);
			// On lance le processus pour IZICARTE
			outil.attendreEtCliquer(Cibles.BOUTON_INSTRUIRE_IZICARTE_COUPLE);
			
			// Gestion de la popup de confirmation de la vente couplée
			outil.attendrePresenceTexte("Souscrire un IZICARTE");
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);

			/////////////////////////////////////////////// SAISIE DU DOSSIER ////////////////////////////////////////////////	
			// On remplis le formulaire.
			outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
			outil.selectionner("Bancarisation", Cibles.SELECTEUR_SITUATION_VENTE_CR, false);
			outil.attendreChargementElement(Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR, true, true);
			// A ce endroit la saisie depend de la CMA. Si le cas d'essai à déjà subit plusieurs saisie le CMA peut être trop bas pour le cas..
			String montantCMA = outil.obtenirValeur(Cibles.SAISIE_MONTANT_CMA_CR, false);
			// On supprime tous les espaces du montant cas IZIVENTE ne traite pas les montants avec des espaces.
			if (montantCMA != null) {
				montantCMA = montantCMA.replaceAll("[^\\d.,]", "");
			} else {
				montantCMA = "1000";
			}
			
			outil.viderEtSaisir(montantCMA, Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
			//outil.attendre(2);
			//outil.viderEtSaisir("100,00", Cibles.SAISIE_MENSUALITE_CR);
			outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
			
			/////////////////////////////////////////////// SAISIE DES PARTICIPANTS ////////////////////////////////////////////////
			// Cliquer sur "Aucun co emprunteur"
			outil.attendreEtCliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
			
			/////////////////////////////////////////////// VALIDATION DES PARTICIPANTS ////////////////////////////////////////////////		
			// On gère l'assurance de l'emprunteur
			outil.attendrePresenceTexte("Liste des participants au prêt");
			outil.attendreChargementElement(Cibles.RADIO_AVEC_ASS_CR);
			outil.cliquer(Cibles.RADIO_SANS_ASS_CR);
			outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
			
			/////////////////////////////////////////////// VALIDATION EN CONTRAT DE CREDIT ////////////////////////////////////////////////	
			outil.attendreChargementElement(Cibles.BOUTON_VALIDER_OPC_CR);
			outil.cliquer(Cibles.BOUTON_VALIDER_OPC_CR);
			
			outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_MAJ, true, true);
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			
			/////////////////////////////////////////////// COCHES ECRAN EDITION ////////////////////////////////////////////////
			outil.attendrePresenceTexte("Contrats à imprimer");
			outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_NON_MAJ);
			
			/////////////////////////////////////////////// EDITION ////////////////////////////////////////////////
			//outil.attendreChargementElement(Cibles.BOUTON_IMPRIMER_LIASSE_CR);
			//outil.cliquer(Cibles.BOUTON_IMPRIMER_LIASSE_CR);
			
			// En mode signature électronique on ne peux pas directement imprimer la liasse.
			outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
			
			// On valide la popup en mode face à face (car ce produit n'est pas éligible signature electronique) :
			outil.attendrePresenceTexte("Produit non éligible");
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_FACE_A_FACE_MAJ);
			
			// On fait le passage à l'octroi
			outil.attendreEtCliquer(Cibles.BOUTON_PASSAGE_OCTROI_CR);
			
			// On valide la popup 
			//outil.attendrePresenceTexte("Passage vers l'octroi > Demande de confirmation");
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			
			///////////////////////////////////////////// OCTROI ////////////////////////////////////////////////
			outil.attendreChargementElement(Cibles.ELEMENT_FORMULAIRE_COMPLETUDE);
			outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_OUI_MAJ);
			outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
			
			outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
			
			///////////////////////////////////////////// FINALISATION DU CR ////////////////////////////////////////////////
			// On réalise l'acceptation
			outil.attendrePresenceTexte("Choisir la décision");
			outil.cliquer(Cibles.LIBELLE_ACCEPTATION);
			// Finalisation de l'octroi
			outil.attendre(1);
			outil.attendreEtCliquer(Cibles.BOUTON_FINALISATION_OCTROI_CR);
			// Popup de finalisation de l'octroi
			outil.attendreChargementElement(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI, true, true);
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI);
			outil.attendreChargementElement(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI, true, true);
			outil.cliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
			outil.attendrePresenceTexte("Liste des dossiers");
			outil.attendrePresenceTexte("EDIT");
			scenario4.validerObjectif(driver, "SAISIEDUCRCOUPLE", true);
			
			///////////////////////////////////////////// MISE EN FORCE DU PP ////////////////////////////////////////////////
			outil.cliquer(Cibles.ELEMENT_MENU_CREDIT_AMORTISSABLE);
			outil.attendreEtCliquer(Cibles.ELEMENT_MENU_LISTE_CA);
			
			// On cherche le numéro FFI dans la page
			for (WebElement coche : outil.obtenirElements(Cibles.COCHES_LISTE_DOSSIER)) {
				coche.click();
				outil.attendre(1);
				if (outil.testerPresenceTexte(numeroFFI, true)) {
					break;
				}
			}	
			// Une fois qu'on est sur le bon numéro FFI on procède à la mise à l'octroi
			outil.attendreEtCliquer(Cibles.BOUTON_PASSAGE_OCTROI);
			// On valide la popup de confirmation
			outil.attendrePresenceTexte("Demande de confirmation");
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			// On valide la complétude
			outil.attendrePresenceTexte("Complétude et justificatifs");
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			// On réalise l'acceptation
			outil.attendrePresenceTexte("Choisir la décision");
			outil.attendreEtCliquer(Cibles.LIBELLE_ACCEPTATION);
			// Finalisation de l'octroi
			outil.attendreEtCliquer(Cibles.BOUTON_FINALISATION_OCTROI);
			// Popup de finalisation de l'octroi
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);

			/////////////////////////////////////////////// VERIFICATION FINALE ////////////////////////////////////////////////
			outil.attendrePresenceTexte("Liste des dossiers");
			outil.attendrePresenceTexte("FORC");
			scenario4.validerObjectif(driver, "MEFPP", true);
				
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, scenario4, ex, scenario4.getNomCasEssai() + scenario4.getDateCreation().getTime());
			throw ex;
		}
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario4, scenario4.getNomCasEssai() + scenario4.getDateCreation().getTime());
	}
}
