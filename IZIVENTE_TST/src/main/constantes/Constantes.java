package main.constantes;

import outils.PropertiesOutil;

/**
 * Ensemble des constantes manipul�es par les tests IZIVENTES et les IHMS.
 * Certaines variables pointent directement sur le fichier properties, celui ci doit �tre dans les resources.
 * @author levieilfa
 *
 */
public class Constantes {
	
	//////////////////////////////////////////////////// INFORMATIONS TECHNIQUES ////////////////////////////////////////////////////////////////
	public static final String EMPLACEMENT_FIREFOX = PropertiesOutil.getInfoConstante("EMPLACEMENT_FIREFOX");
	public static final String EMPLACEMENT_PROFIL = PropertiesOutil.getInfoConstante("EMPLACEMENT_PROFILE");
	
	
	//////////////////////////////////////////////////// INFORMATIONS POUR LES TESTS ////////////////////////////////////////////////////////////
	public static final int LONGUEUR_CE = 13;
	public static final int LONGUEUR_BP = 11;
	public static final int LONGUEUR_IOM = 11;
	public static final int LONGUEUR_BRED = 12;
	
	public static final int CAS_CE = 1;
	public static final int CAS_IOM = 2;
	public static final int CAS_BP = 3;
	public static final int CAS_BRED = 4;
	
	public static final int ETAPE_SUIVANTE_MEF = 2;
	public static final int ETAPE_SUIVANTE_MURIR = 3;
	public static final int ETAPE_SUIVANTE_MEG = 4;
	
	public static final String REPERTOIRE_CE_FOYER = PropertiesOutil.getInfoConstante("REPERTOIRE_CE_FOYER");
	public static final String REPERTOIRE_BP_FOYER = PropertiesOutil.getInfoConstante("REPERTOIRE_BP_FOYER");
	
	public static final String REPERTOIRE_CE_COMPTE = PropertiesOutil.getInfoConstante("REPERTOIRE_CE_COMPTE");
	public static final String REPERTOIRE_BP_COMPTE = PropertiesOutil.getInfoConstante("REPERTOIRE_BP_COMPTE");
	
	public static final String REPERTOIRE_CE_RISQUE = PropertiesOutil.getInfoConstante("REPERTOIRE_CE_RISQUE");
	public static final String REPERTOIRE_BP_RISQUE = PropertiesOutil.getInfoConstante("REPERTOIRE_BP_RISQUE");
	
	public static final String REPERTOIRE_CE_CREANCE = PropertiesOutil.getInfoConstante("REPERTOIRE_CE_CREANCE");
	public static final String REPERTOIRE_BP_CREANCE = PropertiesOutil.getInfoConstante("REPERTOIRE_BP_CREANCE");
	
	public static final String REPERTOIRE_CE_PERSONNE = PropertiesOutil.getInfoConstante("REPERTOIRE_CE_PERSONNE");
	public static final String REPERTOIRE_BP_PERSONNE = PropertiesOutil.getInfoConstante("REPERTOIRE_BP_PERSONNE");
	
//	public static final String REPERTOIRE_TEST = PropertiesOutil.getInfoConstante("REPERTOIRE_TEST");
//	public static final String REPERTOIRE_TEST2 = PropertiesOutil.getInfoConstante("REPERTOIRE_TEST2");

	public static final String URL_CE_FUTURE_REROUTAGE = PropertiesOutil.getInfoEnvConstante("DNS_CE_FUTURE");
	public static final String URL_BP_FUTURE_REROUTAGE = PropertiesOutil.getInfoEnvConstante("DNS_BP_FUTURE");
	public static final String URL_BP_CURRENT_REROUTAGE = "https://nfi80.rec.intranatixis.com/izivente-bp_recB_current/reroutage.action";
	
	public static final String TITRE_PAGE_IZIVENTE = "izivente";
	public static final String TITRE_BLOC_SYNTHESE_TIERS = "Synth�se des informations sur le Tiers";
	
	////////////////////////////////////////////////////INFORMATIONS POUR LES PREFERENCES ////////////////////////////////////////////////////////////
	public static final String PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT = "browser.download.dir";

	public static final int CREDIT_AMORT = 1;
	public static final int FACELIA = 2;
	public static final int CREODIS = 3;
	public static final int IZICARTE = 4;
	public static final int TEOZ = 5;
}
