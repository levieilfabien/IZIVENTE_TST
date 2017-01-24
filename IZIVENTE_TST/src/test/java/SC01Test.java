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

public class SC01Test extends SC00Test {

	/**
	 * Id de s�rialisation.
	 */
	private static final long serialVersionUID = 1L;

	@Test
	public void accesIzivente() throws SeleniumException {
		// Description du sc�nario
		CasEssaiIziventeBean scenario1 = new CasEssaiIziventeBean();
		scenario1.setAlm(true);
		scenario1.setIdUniqueTestLab(49506);
		scenario1.setNomCasEssai("SC01-" + getTime());
		scenario1.setDescriptif("SC01 - BP - IZIVENTE_Distributeur �lectronique_Vente coupl�e");
		scenario1.setNomTestLab("SC01 - BP - IZIVENTE_Distributeur �lectronique_Vente coupl�e");
		//scenario2.setNomTestPlan("SC02 - IZIVENTE_Producteur_ Pr�t �tudiant diff�r� partiel");
		scenario1.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		// Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		
		// Cr�ation et configuration du repertoire de t�l�chargement, ce repertoire est commun pour tous les CT du sc�nario
		scenario1.setRepertoireTelechargement(creerRepertoireTelechargement(scenario1, profile));
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario1.ajouterObjectif(new ObjectifBean("Test arriv� � terme", scenario1.getNomCasEssai() + scenario1.getTime()));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		outil.setRepertoireRacine(scenario1.getRepertoireTelechargement());
		
		try {
			//CT01BIS - Initialisation et instruction de la vente jusque la validation
			//CT19 - Pr�paration contrat Edition de la liasse
			//CT07 - Validation �lectronique du contrat
			//CT11 - Mise en force (signature �lectronique)
			//CT11BIS - Contr�le Mise en force
			
			scenario1.getTests().add(CT01Bis(scenario1, outil));
			scenario1.getTests().add(CT19(scenario1, outil));
			scenario1.getTests().add(CT07(scenario1, outil));
			
		} catch (SeleniumException ex) {
			// Finalisation en erreur du cas de test.
			finaliserTestEnErreur(outil, scenario1, ex, scenario1.getNomCasEssai() + scenario1.getDateCreation().getTime());
			throw ex;
		}
		// Finalisation normale du cas de test.
		finaliserTest(outil, scenario1, scenario1.getNomCasEssai() + scenario1.getDateCreation().getTime());
	}
	
	/**
	 * Partie du scenario1 regroupant la finalisation de l'impression
	 * @param scenario le scenario englobant.
	 * @param outil l'outil de manipulation du navigateur.
	 * @return le cas d'essai document� pour ajout au sc�nario.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT07(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
		//Param�trage du CT07
		CasEssaiIziventeBean CT07 = new CasEssaiIziventeBean();
		CT07.setAlm(true);
		CT07.setNomCasEssai("CT07-" + getTime());
		CT07.setDescriptif("CT07 - Validation �lectronique du contrat");
		CT07.setNomTestPlan("CT07 - Validation �lectronique du contrat");
		
		//Information issues du sc�nario.
		CT07.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
		CT07.setNomTestLab(scenario.getNomTestLab());
		CT07.setCheminTestLab(scenario.getCheminTestLab());
		CT07.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
		CT07.setIdClient(scenario.getIdClient());
		
		//Gestion des step
		CT07.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT07.getNomCasEssai() + CT07.getTime()));
		CT07.ajouterStep("Retour sur IZIVENTE", "ACCES", "Retour sur IZIVENTE");
		CT07.ajouterStep("Se connecter au serveur afin de contr�ler le stockage de la liasse contrat.", "CONTROLE", "La liasse est bien enregistr�e.");
		//Se connecter au serveur afin de contr�ler le stockage de la liasse contrat.
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// RETOUR SUR IZIVENTE
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// Saisie du jeton (on r�utilise le jeton du client d�j� g�n�rer)
		saisieJeton(outil, scenario.getIdClient(), false, Constantes.CAS_BP, null);
		// Acc�s � IZIVENTE
		CT07.validerObjectif(outil.getDriver(), "ACCES", true);
		
		return CT07;
	}
	
	/**
	 * Partie du scenario1 regroupant la gestion de l'�dition jusqu'� l'envoi.
	 * @param scenario le scenario englobant.
	 * @param outil l'outil de manipulation du navigateur.
	 * @return le cas d'essai document� pour ajout au sc�nario.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT19(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
		//Param�trage du CT19
		CasEssaiIziventeBean CT19 = new CasEssaiIziventeBean();
		CT19.setAlm(true);
		CT19.setNomCasEssai("CT19-" + getTime());
		CT19.setDescriptif("CT19 - Pr�paration contrat Edition de la liasse");
		CT19.setNomTestPlan("CT19 - Pr�paration contrat Edition de la liasse");
		
		//Information issues du sc�nario.
		CT19.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
		CT19.setNomTestLab(scenario.getNomTestLab());
		CT19.setCheminTestLab(scenario.getCheminTestLab());
		CT19.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
		
		//Gestion des step
		CT19.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT19.getNomCasEssai() + CT19.getTime()));
		CT19.ajouterStep("Ecran 'Pr�paration contrat' qui affiche la synth�se de l'offre de cr�dit Cliquer sur 1. Imprimer la synth�se", "ACCES", "Les boutons de l'�cran se d�grisent");
		CT19.ajouterStep("Saisir les choix de l'option et les Cr�dits renouvelables � cl�turer selon la fiche des pr�ts \nCliquer sur 2.Suivant afin de d�clencher la production des contrats et l�envoi des documents au Distributeur.", "IMPRESSION", "Une pop-up permet de suivre l�avanc�e de ce processus via une barre de progression");
		CT19.ajouterStep("L'envoi de liasse est fini. Cliquer sur Suivant", "ENVOILIASSE", "On est d�connect� d'Izivente");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES A L'ECRAN D'IMPRESSION + IMPRESSION SYNTHESE
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE);
		// Pas de demande de financement � 8 jours.
		outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
		CT19.validerObjectif(outil.getDriver(), "ACCES", true);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// IMPRESSION DE LA LIASSE EN MODE DISTRIBUTEUR
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
		
		// En mode distributeur on a une signature electronique, on cherche � vendre un CR Coupl�
		outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
		outil.attendreChargementElement(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE);
		CT19.validerObjectif(outil.getDriver(), "IMPRESSION", true);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// FINALISATION DE L'EDITION DU PP
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT);
		// TODO V�rifier que l'on est sur une page blanche ?
		CT19.validerObjectif(outil.getDriver(), "ENVOILIASSE", true);
		
		return CT19;
	}
	
	
	/**
	 * Partie du scenario1 regroupant la saisie, l'instruction et la validation d'un dossier PP dans IZIVENTE.
	 * @param scenario le scenario englobant.
	 * @param outil l'outil de manipulation du navigateur.
	 * @return le cas d'essai document� pour ajout au sc�nario.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT01Bis(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
		//Param�trage du CT01
		CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
		CT01.setAlm(true);
		CT01.setNomCasEssai("CT01-" + getTime());
		CT01.setDescriptif("CT01BIS - Initialisation et instruction de la vente jusque la validation");
		CT01.setNomTestPlan("CT01BIS - Initialisation et instruction de la vente jusque la validation");
		
		//Information issues du sc�nario.
		CT01.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
		CT01.setNomTestLab(scenario.getNomTestLab());
		CT01.setCheminTestLab(scenario.getCheminTestLab());
		CT01.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
		
		//Gestion des step
		CT01.ajouterObjectif(new ObjectifBean("Test arriv� � terme", CT01.getNomCasEssai() + CT01.getTime()));
		CT01.ajouterStep("Lancer l'URL Izivente en fonction du r�seau test� et injecter le jeton du sc�nario", "ACCES", "Ecran d'INSTRUCTION");
		CT01.ajouterStep("Dans l'onglet 'Dossier Cr�dit Amortissable', s�lectionner l'ouverture d'un dossier ", "OUVERTURE", "Ecran donn�es client ou liste dossier");
		CT01.ajouterStep("V�rifier que les donn�es du client, du conjoint si existant et du budget sont coh�rentes avec le bouchon. Valider les donn�es du client", "VERIFICATION", "Donn�es clients valid�es et Ecran de la 'demande du cr�dit'");
		CT01.ajouterStep("Choisir les participants en fonction de la fiche de pr�t et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le num�ro de personne physique, cliquer sur rechercher, v�rifier la coh�rence des donn�es du tiers  et  valider les donn�es du tiers. ", "PARTICIPANTS", "Ecran 'Synth�se des participants'");
		CT01.ajouterStep("Choisir un pr�t en suivant les hypoth�ses de la fiche de pr�t. Pour le regroupement de cr�dit suivre le mode op�ratoire. Valider", "SAISIEDOSSIER", "Ecran des 'Participants'");
		CT01.ajouterStep("Pour chaque participant, choisir le r�le et l'assurance en fonction des hypoth�ses. Valider la listes des participants et confirmer l'assurance.", "ASSURANCEROLE", "Ecran de 'Proposition' avec la grille alternative commerciale");
		CT01.ajouterStep("Valider et confirmer le contrat de cr�dit pour passer � l'Ecran de 'finalisation de l'instruction'. Confirmer en cochant 'v�rifi�' pour chaque type de document Puis valider", "VALIDATIONINSTRUCTION", "Ecran 'Pr�paration contrat' qui affiche la synth�se de l'offre de cr�dit");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES A L'APPLICATION
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// Saisie du jeton
		//"9384466"
		String idClient = saisieJeton(outil, null, false, Constantes.CAS_BP, null, null, null);
		// R�cup�ration de l'�l�ment contenant le jeton de reretoutage.
		scenario.setIdClient(idClient);
		// Acc�s � IZIVENTE
		CT01.validerObjectif(outil.getDriver(), "ACCES", true);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES A L'OUVERTURE DU DOSSIER
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cette etape est optionelle, le "Face � face" ne sera pas toujours visible
		outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
		outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
		// Cette �tape n'as lieu que si le dossier est d�j� porteur d'un autre dossier
		outil.cliquerSiPossible(Cibles.BOUTON_MENU_NOUVEAU_DOSSIER);
		CT01.validerObjectif(outil.getDriver(), "OUVERTURE", true);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// VERIFICATIONS
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		outil.attendrePresenceTexte("ATTENTION");
		outil.cliquer(Cibles.BOUTON_POPUP_FERMER);
		outil.attendreEtCliquer(Cibles.BOUTON_SUIVANT);
		outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
		CT01.validerObjectif(outil.getDriver(), "VERIFICATION", true);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// SAISIE DU DOSSIER
		/////////////////////////////////////////////////////////////////////////////////////////////////////		
		// On remplis le formulaire	.
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
		CT01.validerObjectif(outil.getDriver(), "SAISIEDOSSIER", true);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// SAISIE DU DOSSIER
		/////////////////////////////////////////////////////////////////////////////////////////////////////		
		// Ajout du participant conjoint
		outil.attendreChargementElement(Cibles.LIBELLE_ONGLET_AJOUT_PARTICIPANT);
		// Cliquer sur "Aucun co emprunteur"
		outil.cliquer(Cibles.BOUTON_AUCUN_COEMPRUNTEUR);
		CT01.validerObjectif(outil.getDriver(), "PARTICIPANTS", true);
		// On g�re l'assurance de l'emprunteur
		outil.attendreChargementElement(Cibles.RADIO_SELECTION_PARTICIPANT0);
		outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
		outil.cliquer(Cibles.BOUTON_VALIDER_LISTE_PARTICIPANT);
		CT01.validerObjectif(outil.getDriver(), "ASSURANCEROLE", true);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// VALIDATION DE L'INSTRUCTION
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		outil.attendrePresenceTexte("Alerte(s)");
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_OK_MAJ);
		outil.attendreEtCliquer(Cibles.BOUTON_VALIDER);
		
		outil.attendreEtCliquer(Cibles.BOUTON_VALIDER_OPC);
		outil.attendreChargementElement(Cibles.ELEMENT_FIN_INSTRUCTION, true, true);
		outil.cliquerMultiple(Cibles.LIBELLE_CHOIX_VERIFIE);
		outil.attendre(1);
		outil.cliquer(Cibles.BOUTON_POPUP_VALIDER_JUSTIFICATIFS);
		CT01.validerObjectif(outil.getDriver(), "VALIDATIONINSTRUCTION", true);
		
		return CT01;
	}
}
