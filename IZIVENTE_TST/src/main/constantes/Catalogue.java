package main.constantes;

/**
 * Ensemble des constantes de configuration ALM et de titre de CT manipulées par les tests IZIVENTES
 * Certaines variables pointent directement sur le fichier properties, celui ci doit être dans les resources.
 * @author levieilfa
 *
 */
public class Catalogue {
	
	////////////////////////////////////////////////////CONFIGURATION ALM	 ////////////////////////////////////////////////////////////
	public static final String CHEMIN_TEST_LAB_IZIVENTE = "POC Selenium\\IZIVENTE";
	
	
	////////////////////////////////////////////////////TITRES DES TESTS 	 ////////////////////////////////////////////////////////////
	public static final String SC01_TITRE 		= "SC01 - BP - IZIVENTE_Distributeur électronique_Vente couplée";
	
	public static final String CT07_TITRE_OLD	= "CT07 - Validation électronique du contrat";
	public static final String CT19_TITRE 		= "CT19 - Préparation contrat Edition de la liasse";
	public static final String CT01BIS_TITRE 	= "CT01BIS - Initialisation et instruction de la vente jusque la validation";
	
	////////////////////////////////////////////////////CAS DE TESTS GENERIQUES	 ////////////////////////////////////////////////////////////
	public static final String CT01_TITRE 		= "CT01 - Accès Izivente et Initialisation";
	public static final String CT02_TITRE 		= "CT02 - Ouverture du dossier";
	public static final String CT03_TITRE 		= "CT03 - Saisie du dossier";
	public static final String CT04_TITRE 		= "CT04 - Choix des participants et fin de simulation";
	public static final String CT05_TITRE 		= "CT05 - Validation du dossier";
	public static final String CT06_TITRE 		= "CT06 - Finalisation de l instruction";
	public static final String CT07_TITRE 		= "CT07 - Mise en force du dossier";
	public static final String CT08_TITRE 		= "CT08 - Murissement du dossier";
	
	
	
	// Catalogue pour RST
	public static final String RSTSC01_TITRE 		= "RSTSC01 - Avant vente simulation";
	//rep à créer dans ALM
	public static final String CHEMIN_TEST_LAB_RST = "POC Selenium\\RST ";
	
}
