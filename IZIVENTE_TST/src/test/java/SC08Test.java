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

public class SC08Test extends SC00Test {
	
	/**
	 * Id de sérialisation par défaut.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void accesIzivente() throws SeleniumException {
		
		// Mise en place d'information propre au scénario 8.
		CasEssaiIziventeBean scenario8 = new CasEssaiIziventeBean();
		scenario8.setNomCasEssai("SC08-" + getTime());
		//Inspirer de SC02 - CE - IZIVENTE_Distributeur papier_IZICARTE / 33814
		scenario8.setDescriptif("SC08 - CE - IZIVENTE_Distributeur papier_IZICARTE");
		// Information propres à ALM
		scenario8.setAlm(true);
		scenario8.setIdUniqueTestLab(49503);
		scenario8.setNomTestLab("SC08 - CE - IZIVENTE_Distributeur_papier_IZICARTE");
		scenario8.setNomTestPlan("SC08 - IZIVENTE_Distributeur papier_IZICARTE");
		scenario8.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		// Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		
		// Création et configuration du repertoire de téléchargement
		String repertoire = creerRepertoireTelechargement(scenario8, profile);
		scenario8.setRepertoireTelechargement(repertoire);
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario8.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario8.getNomCasEssai() + scenario8.getTime()));
		scenario8.ajouterObjectif(new ObjectifBean("Instruction de la vente", "INSTRUCTION", "INSTRUCTION" + scenario8.getTime(), "Initialisation et instruction de la vente jusque la validation CR", ""));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		
		try {
			// Saisie du jeton
			String idClient = saisieJeton(outil, null, false, Constantes.CAS_CE, null);
			// Récupération de l'élément contenant le jeton de reretoutage.
			scenario8.setIdClient(idClient);
			
			/////////////////////////////////////////////// CHOIX DU MODE DE VENTE ////////////////////////////////////////////////
			// Cette etape est optionelle, le "Face à face" ne sera pas toujours visible
			outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
			outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_IZICARTE);
			
			// Gestion de la popup de confirmation ("Vous allez souscrire un crédit renouvelable IZICARTEsans carte associée.Voulez-vous continuer ?")
			outil.attendrePresenceTexte("Information");
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			//outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
			
			/////////////////////////////////////////////// GESTION DE LA POPUP ////////////////////////////////////////////////
			outil.attendrePresenceTexte("Attention");
			outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
			
			/////////////////////////////////////////////// SAISIE DU DOSSIER ////////////////////////////////////////////////
			outil.cliquer(Cibles.BOUTON_SUIVANT);
			outil.cliquer(Cibles.BOUTON_VALIDER);		
//			// On remplis le formulaire.
			outil.attendreChargementElement(Cibles.SELECTEUR_SITUATION_VENTE_CR, true, true);
			outil.selectionner("CONS", Cibles.SELECTEUR_SITUATION_VENTE_CR, false);
			outil.viderEtSaisir("1200", Cibles.SAISIE_MONTANT_PREMIER_FINANCEMENT_CR);
			// On force la valorisation de la mensualité
			outil.cliquer(Cibles.SAISIE_MENSUALITE_CR);
			outil.attendreValeur(Cibles.SAISIE_MENSUALITE_CR, "36,00");
			outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
			
			/////////////////////////////////////////////// SAISIE DES PARTICIPANTS ////////////////////////////////////////////////
//			// Pas d'ajout du participant conjoint
			outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
			outil.attendreEtCliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
//			// Validation de la liste des participants
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
//			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
//			/////////////////////////////////////////////// GESTION DE VALIDATION DE LA SIMULATION ////////////////////////////////////////////////
			outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_OPC_CR);
			// Gestion de la popup
			outil.attendreChargementElement(Cibles.ELEMENT_POPUP_CONFIRMATION_VALIDATION_OPC_CR, true, true);
			outil.cliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			scenario8.validerObjectif(driver, "INSTRUCTION", true);
			
			/////////////////////////////////////////////// EDITION ////////////////////////////////////////////////
			// Récupération du numéro FFI
			String numeroFFI = outil.obtenirValeur(Cibles.ELEMENT_SPAN_NUMERO_FFI);
			scenario8.setNumeroFFI(numeroFFI);
			// Pas de demande de financement à 8 jours.
			outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_NON_MAJ);
			
			// Gestion des impressions
			outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_FACE_A_FACE_MAJ);
			scenario8.validerObjectif(driver, "EDITION", true);
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
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_CONFIRMATION_OCTROI);
			outil.attendreChargementElement(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI, true, true);
			outil.cliquer(Cibles.BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI);
			outil.attendrePresenceTexte("Liste des dossiers");
			outil.attendrePresenceTexte("EDIT");
			
			outil.attendreEtCliquer(Cibles.BOUTON_MISE_EN_FORCE_CR);
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OUI_MAJ);
			
			outil.attendrePresenceTexte("La mise en force a bien été enregistrée");
			outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
				
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, scenario8, ex, scenario8.getNomCasEssai() + scenario8.getDateCreation().getTime());
			throw ex;
		}
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario8, scenario8.getNomCasEssai() + scenario8.getDateCreation().getTime());
	}
}
