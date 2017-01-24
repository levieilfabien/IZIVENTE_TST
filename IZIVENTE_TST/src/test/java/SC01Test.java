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
	 * Id de sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	@Test
	public void accesIzivente() throws SeleniumException {
		// Description du scénario
		CasEssaiIziventeBean scenario1 = new CasEssaiIziventeBean();
		scenario1.setAlm(true);
		scenario1.setIdUniqueTestLab(49506);
		scenario1.setNomCasEssai("SC01-" + getTime());
		scenario1.setDescriptif("SC01 - BP - IZIVENTE_Distributeur électronique_Vente couplée");
		scenario1.setNomTestLab("SC01 - BP - IZIVENTE_Distributeur électronique_Vente couplée");
		//scenario2.setNomTestPlan("SC02 - IZIVENTE_Producteur_ Prêt étudiant différé partiel");
		scenario1.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		// Configuration du driver
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		
		// Création et configuration du repertoire de téléchargement, ce repertoire est commun pour tous les CT du scénario
		scenario1.setRepertoireTelechargement(creerRepertoireTelechargement(scenario1, profile));
		
		// Initialisation du driver
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		
		// LISTE DES OBJECTIFS DU CAS DE TEST
		scenario1.ajouterObjectif(new ObjectifBean("Test arrivé à terme", scenario1.getNomCasEssai() + scenario1.getTime()));
		
		SeleniumOutils outil = new SeleniumOutils(driver, GenericDriver.FIREFOX_IMPL);
		outil.setRepertoireRacine(scenario1.getRepertoireTelechargement());
		
		try {
			//CT01BIS - Initialisation et instruction de la vente jusque la validation
			//CT19 - Préparation contrat Edition de la liasse
			//CT07 - Validation électronique du contrat
			//CT11 - Mise en force (signature électronique)
			//CT11BIS - Contrôle Mise en force
			
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
	 * @return le cas d'essai documenté pour ajout au scénario.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT07(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
		//Paramètrage du CT07
		CasEssaiIziventeBean CT07 = new CasEssaiIziventeBean();
		CT07.setAlm(true);
		CT07.setNomCasEssai("CT07-" + getTime());
		CT07.setDescriptif("CT07 - Validation électronique du contrat");
		CT07.setNomTestPlan("CT07 - Validation électronique du contrat");
		
		//Information issues du scénario.
		CT07.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
		CT07.setNomTestLab(scenario.getNomTestLab());
		CT07.setCheminTestLab(scenario.getCheminTestLab());
		CT07.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
		CT07.setIdClient(scenario.getIdClient());
		
		//Gestion des step
		CT07.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT07.getNomCasEssai() + CT07.getTime()));
		CT07.ajouterStep("Retour sur IZIVENTE", "ACCES", "Retour sur IZIVENTE");
		CT07.ajouterStep("Se connecter au serveur afin de contrôler le stockage de la liasse contrat.", "CONTROLE", "La liasse est bien enregistrée.");
		//Se connecter au serveur afin de contrôler le stockage de la liasse contrat.
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// RETOUR SUR IZIVENTE
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// Saisie du jeton (on réutilise le jeton du client déjà générer)
		saisieJeton(outil, scenario.getIdClient(), false, Constantes.CAS_BP, null);
		// Accès à IZIVENTE
		CT07.validerObjectif(outil.getDriver(), "ACCES", true);
		
		return CT07;
	}
	
	/**
	 * Partie du scenario1 regroupant la gestion de l'édition jusqu'à l'envoi.
	 * @param scenario le scenario englobant.
	 * @param outil l'outil de manipulation du navigateur.
	 * @return le cas d'essai documenté pour ajout au scénario.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT19(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
		//Paramètrage du CT19
		CasEssaiIziventeBean CT19 = new CasEssaiIziventeBean();
		CT19.setAlm(true);
		CT19.setNomCasEssai("CT19-" + getTime());
		CT19.setDescriptif("CT19 - Préparation contrat Edition de la liasse");
		CT19.setNomTestPlan("CT19 - Préparation contrat Edition de la liasse");
		
		//Information issues du scénario.
		CT19.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
		CT19.setNomTestLab(scenario.getNomTestLab());
		CT19.setCheminTestLab(scenario.getCheminTestLab());
		CT19.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
		
		//Gestion des step
		CT19.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT19.getNomCasEssai() + CT19.getTime()));
		CT19.ajouterStep("Ecran 'Préparation contrat' qui affiche la synthèse de l'offre de crédit Cliquer sur 1. Imprimer la synthèse", "ACCES", "Les boutons de l'écran se dégrisent");
		CT19.ajouterStep("Saisir les choix de l'option et les Crédits renouvelables à clôturer selon la fiche des prêts \nCliquer sur 2.Suivant afin de déclencher la production des contrats et l’envoi des documents au Distributeur.", "IMPRESSION", "Une pop-up permet de suivre l’avancée de ce processus via une barre de progression");
		CT19.ajouterStep("L'envoi de liasse est fini. Cliquer sur Suivant", "ENVOILIASSE", "On est déconnecté d'Izivente");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES A L'ECRAN D'IMPRESSION + IMPRESSION SYNTHESE
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_SYNTHESE);
		// Pas de demande de financement à 8 jours.
		outil.cliquer(Cibles.LIBELLE_CHOIX_NON_MAJ);
		CT19.validerObjectif(outil.getDriver(), "ACCES", true);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// IMPRESSION DE LA LIASSE EN MODE DISTRIBUTEUR
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		outil.attendreEtCliquer(Cibles.BOUTON_IMPRIMER_LIASSE);
		
		// En mode distributeur on a une signature electronique, on cherche à vendre un CR Couplé
		outil.attendrePresenceTexte("Passage vers le choix du mode de signature");
		outil.attendreChargementElement(Cibles.ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE);
		CT19.validerObjectif(outil.getDriver(), "IMPRESSION", true);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// FINALISATION DE L'EDITION DU PP
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		outil.attendreEtCliquer(Cibles.BOUTON_POPUP_SUIVANT);
		// TODO Vérifier que l'on est sur une page blanche ?
		CT19.validerObjectif(outil.getDriver(), "ENVOILIASSE", true);
		
		return CT19;
	}
	
	
	/**
	 * Partie du scenario1 regroupant la saisie, l'instruction et la validation d'un dossier PP dans IZIVENTE.
	 * @param scenario le scenario englobant.
	 * @param outil l'outil de manipulation du navigateur.
	 * @return le cas d'essai documenté pour ajout au scénario.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public CasEssaiIziventeBean CT01Bis(CasEssaiIziventeBean scenario, SeleniumOutils outil) throws SeleniumException {
		//Paramètrage du CT01
		CasEssaiIziventeBean CT01 = new CasEssaiIziventeBean();
		CT01.setAlm(true);
		CT01.setNomCasEssai("CT01-" + getTime());
		CT01.setDescriptif("CT01BIS - Initialisation et instruction de la vente jusque la validation");
		CT01.setNomTestPlan("CT01BIS - Initialisation et instruction de la vente jusque la validation");
		
		//Information issues du scénario.
		CT01.setIdUniqueTestLab(scenario.getIdUniqueTestLab());
		CT01.setNomTestLab(scenario.getNomTestLab());
		CT01.setCheminTestLab(scenario.getCheminTestLab());
		CT01.setRepertoireTelechargement(scenario.getRepertoireTelechargement());
		
		//Gestion des step
		CT01.ajouterObjectif(new ObjectifBean("Test arrivé à terme", CT01.getNomCasEssai() + CT01.getTime()));
		CT01.ajouterStep("Lancer l'URL Izivente en fonction du réseau testé et injecter le jeton du scénario", "ACCES", "Ecran d'INSTRUCTION");
		CT01.ajouterStep("Dans l'onglet 'Dossier Crédit Amortissable', sélectionner l'ouverture d'un dossier ", "OUVERTURE", "Ecran données client ou liste dossier");
		CT01.ajouterStep("Vérifier que les données du client, du conjoint si existant et du budget sont cohérentes avec le bouchon. Valider les données du client", "VERIFICATION", "Données clients validées et Ecran de la 'demande du crédit'");
		CT01.ajouterStep("Choisir les participants en fonction de la fiche de prêt et Valider: \n -Pour ajouter le conjoint, Cliquer sur Ajouter le conjoint. \n -Pour ajouter un tiers, entrer le numéro de personne physique, cliquer sur rechercher, vérifier la cohérence des données du tiers  et  valider les données du tiers. ", "PARTICIPANTS", "Ecran 'Synthèse des participants'");
		CT01.ajouterStep("Choisir un prêt en suivant les hypothèses de la fiche de prêt. Pour le regroupement de crédit suivre le mode opératoire. Valider", "SAISIEDOSSIER", "Ecran des 'Participants'");
		CT01.ajouterStep("Pour chaque participant, choisir le rôle et l'assurance en fonction des hypothèses. Valider la listes des participants et confirmer l'assurance.", "ASSURANCEROLE", "Ecran de 'Proposition' avec la grille alternative commerciale");
		CT01.ajouterStep("Valider et confirmer le contrat de crédit pour passer à l'Ecran de 'finalisation de l'instruction'. Confirmer en cochant 'vérifié' pour chaque type de document Puis valider", "VALIDATIONINSTRUCTION", "Ecran 'Préparation contrat' qui affiche la synthèse de l'offre de crédit");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES A L'APPLICATION
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// Saisie du jeton
		//"9384466"
		String idClient = saisieJeton(outil, null, false, Constantes.CAS_BP, null, null, null);
		// Récupération de l'élément contenant le jeton de reretoutage.
		scenario.setIdClient(idClient);
		// Accès à IZIVENTE
		CT01.validerObjectif(outil.getDriver(), "ACCES", true);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// ACCES A L'OUVERTURE DU DOSSIER
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cette etape est optionelle, le "Face à face" ne sera pas toujours visible
		outil.cliquerSiPossible(Cibles.BOUTON_POPUP_FACE_A_FACE);
		outil.cliquer(Cibles.BOUTON_MENU_OUVERTURE_DOSSIER);
		// Cette étape n'as lieu que si le dossier est déjà porteur d'un autre dossier
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
		// On gère l'assurance de l'emprunteur
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
