package test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

import main.bean.CasEssaiIziventeBean;
import main.bean.ModificateurBouchon;
import main.constantes.Cibles;
import main.constantes.Constantes;
import main.ihm.Compte;
import main.ihm.Creance;
import main.ihm.Foyer;
import main.ihm.IHMGeneration;
import main.ihm.ListePersonnePhysique;
import main.ihm.Risque;
import main.ihm.PersonnePhysiqueTiers;

import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import com.itextpdf.text.pdf.codec.Base64.InputStream;

import outils.ALMOutils;
import outils.SeleniumOutils;
import outils.XLSOutils;
import outils.XMLOutils;
import beans.CasEssaiBean;
import constantes.Erreurs;
import exceptions.SeleniumException;

/**
 * Classe contenant les fonctions communes entre les différents scenario.
 * @author levieilfa
 *
 */
public class SC00Test extends CasEssaiBean {
	
//	/**
//	 * Le distributeur associé au test.
//	 */
//	private int distributeur = 0;
		
	/**
	 * Ide de sérialisation.
	 */
	private static final long serialVersionUID = -3998430627069819668L;

	/**
	 * Fonction permettant de générer un bouchon.
	 * @param cas le type de cas (CE, BP, BRED, IOM)
	 * @param id l'id du client si connu, si null on cherche un numéro libre
	 * @return l'id du client
	 * @throws SeleniumException en cas d'erreur.
	 */
	public String generationBouchon(int cas, String id) throws SeleniumException {
		return generationBouchon(cas, id, null);
	}
	
	
	/**
	 * Créer le repertoire de téléchargement pour le cas d'essai.
	 * @param profile le profil firefox utilisé
	 * @param casEssai le cas d'essai
	 * @return le chemin du repertoire.
	 */
	public String creerRepertoireTelechargement(CasEssaiBean casEssai, FirefoxProfile profile) {
		File repertoireTelechargement = new File(".\\" + casEssai.getNomCasEssai());
		repertoireTelechargement.mkdir();
		profile.setPreference(Constantes.PREF_FIREFOX_REPERTOIRE_TELECHARGEMENT, repertoireTelechargement.getAbsolutePath());
		return repertoireTelechargement.getAbsolutePath();
	}
	
	/**
	 * Permet d'écriture des fichiers de bouchons dans leurs repertoire respectifs.
	 * @param cas le type de cas
	 * @param idClient l'identifiant unique du client qui est aussi le nom des bouchons
	 * @param foyer le bouchon de foyer à sauvegarder
	 * @param compte le bouchon de compte à sauvegarder 
	 * @param risque le bouchon de risque à sauvegarder 
	 * @throws SeleniumException en cas d'erreur
	 */
	public static void ecritureFichier(int cas, String idClient, Foyer foyer, Compte compte, Risque risque) throws SeleniumException {
		// Déclaration des bouchons
		File fileFoyer = new File(((cas == Constantes.CAS_CE || cas == Constantes.CAS_IOM)?Constantes.REPERTOIRE_CE_FOYER:Constantes.REPERTOIRE_BP_FOYER) + "\\" + idClient + ".xml");
		File fileCompte = new File(((cas == Constantes.CAS_CE || cas == Constantes.CAS_IOM)?Constantes.REPERTOIRE_CE_COMPTE:Constantes.REPERTOIRE_BP_COMPTE) + "\\" + idClient + ".xml");
		File fileRisque = new File(((cas == Constantes.CAS_CE || cas == Constantes.CAS_IOM)?Constantes.REPERTOIRE_CE_RISQUE:Constantes.REPERTOIRE_BP_RISQUE) + "\\" + idClient + ".xml");
		File filePersonnePhy = new File(((cas == Constantes.CAS_CE || cas == Constantes.CAS_IOM)?Constantes.REPERTOIRE_CE_PERSONNE:Constantes.REPERTOIRE_BP_PERSONNE) + "\\" + idClient + ".xml");
		File filePersonnePhyCjt = null;
		if (foyer.personnePhyCjt != null && foyer.personnePhyCjt.idClientDistributeur != null) {
			filePersonnePhyCjt = new File(((cas == Constantes.CAS_CE || cas == Constantes.CAS_IOM)?Constantes.REPERTOIRE_CE_PERSONNE:Constantes.REPERTOIRE_BP_PERSONNE) + "\\" + foyer.personnePhyCjt.idClientDistributeur + ".xml");;
		}
		File fileCreance = null;
		// Pas de créance pour BP
//		if (cas == Constantes.CAS_CE || cas == Constantes.CAS_IOM) {
//			fileCreance = new File(((cas == Constantes.CAS_CE || cas == Constantes.CAS_IOM)?Constantes.REPERTOIRE_CE_CREANCE:Constantes.REPERTOIRE_BP_CREANCE) + "\\" + idClient + ".xml");
//		}
		// Ecriture dans les bouchons
		try {
			// Si les fichiers existent déjà on les purge
			if (fileFoyer.exists()) {
				fileFoyer.delete();
			}
			if (fileCompte.exists()) {
				fileCompte.delete();
			}
			if (fileCompte.exists()) {
				fileCompte.delete();
			}
			if (filePersonnePhy.exists()) {
				filePersonnePhy.delete();
			}
//			if (fileCreance != null && fileCreance.exists()) {
//				fileCreance.delete();
//			}
			if (filePersonnePhyCjt != null && filePersonnePhyCjt.exists()) {
				filePersonnePhyCjt.delete();
			}
			// Création des fichier vide avec droits d'écriture
			fileFoyer.setWritable(true);
			fileFoyer.createNewFile();
			fileCompte.setWritable(true);
			fileCompte.createNewFile();
			fileRisque.setWritable(true);
			fileRisque.createNewFile();
			filePersonnePhy.setWritable(true);
			filePersonnePhy.createNewFile();
			// Cas particulier de la creance existante uniquement pour CE
//			if (fileCreance != null) {
//				fileCreance.setWritable(true);
//				fileCreance.createNewFile();
//			}
			// Car particulier du conjoint facultatif
			if (filePersonnePhyCjt != null) {
				filePersonnePhyCjt.setWritable(true);
				filePersonnePhyCjt.createNewFile();
			}
			// Ecriture dans les fichier vide avec le charset UTF-8
			PrintWriter writer = new PrintWriter(fileFoyer, "UTF-8");
			writer.append(XMLOutils.toXml(foyer));
			writer.close();
			writer = new PrintWriter(fileCompte, "UTF-8");
			writer.append(XMLOutils.toXml(compte));
			writer.close();
			writer = new PrintWriter(fileRisque, "UTF-8");
			writer.append(XMLOutils.toXml(risque));
			writer.close();
			writer = new PrintWriter(filePersonnePhy, "UTF-8");
			writer.append(XMLOutils.toXml(new ListePersonnePhysique(new PersonnePhysiqueTiers(foyer, false))));
			writer.close();
//			if (fileCreance != null) {
//				writer = new PrintWriter(fileCreance, "UTF-8");
//				writer.append(XMLOutils.toXml(new Creance(idClient, cas)));
//				writer.close();
//			}
			if (filePersonnePhyCjt != null) {
				writer = new PrintWriter(filePersonnePhyCjt, "UTF-8");
				writer.append(XMLOutils.toXml(new ListePersonnePhysique(new PersonnePhysiqueTiers(foyer, true))));
				writer.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new SeleniumException(Erreurs.E025, "Impossible de creer un des bouchons.");
		} catch (IOException e) {
			e.printStackTrace();
			throw new SeleniumException(Erreurs.E025, "Droits insuffisant pour creer les fichiers.");
		}
		
		// Tests sur la création
		if (fileFoyer.exists() && fileCompte.exists() && fileRisque.exists()) {
			System.out.println("Création des bouchons OK.");
		} else {
			System.out.println("Création en échec.");
		}
		
	}
	
	/**
	 * Fonction permettant de générer un bouchon.
	 * @param cas le type de cas (CE, BP, BRED, IOM)
	 * @param id l'id du client si connu, si null on cherche un numéro libre
	 * @param modificateur un modificateur de bouchon.
	 * @return l'id du client
	 * @throws SeleniumException en cas d'erreur.
	 */
	public static String generationBouchon(int cas, String id, ModificateurBouchon modificateur) throws SeleniumException {
		// Recupération de l'id client distributeur
		String retour = "";
		if (id == null || "".equals(id)) {
			retour = IHMGeneration.trouverNumero(cas);
		} else {
			retour = id;
		}
		
		// Génération des objets
		Foyer foyer = new Foyer(retour, cas);
		Compte compte = new Compte(retour, cas);
		Risque risque = new Risque(retour, cas);
		
		// On applique les modificateur de bouchons.
		if (modificateur != null) {
			foyer = modificateur.modifierFoyer(foyer);
		}
		
		ecritureFichier(cas, retour, foyer, compte, risque);
		
		// Renvoie de l'id pour référence
		return retour;
	}
	
	/**
	 * Configuration du profil pour test.
	 * @return Le profil utiliser sur le modèle du profil "Automate"
	 */
	public static FirefoxProfile configurerProfilNatixis() {
		
		// Initialisation du profil avec le profil automate requis
		ProfilesIni profileIni = new ProfilesIni();
		//FirefoxProfile profile = profileIni.getProfile("Automate");
		FirefoxProfile profile = new FirefoxProfile(new File(Constantes.EMPLACEMENT_PROFIL));
		
		profile.setPreference("app.update.enabled", Boolean.FALSE);
		profile.setPreference("network.negotiate-auth.trusted-uris", "https://open-workplace.intranatixis.com/nfi/front-middle/wiki-izivente/Rfrentiel/Liens%20Izivente.aspx");
		profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "https://open-workplace.intranatixis.com/nfi/front-middle/wiki-izivente/Rfrentiel/Liens%20Izivente.aspx");
		
		profile.setPreference("browser.download.pluginOverrideTypes", "");
		profile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf,application/vnd.fdf,application/vnd.adobe.xfdf,application/vnd.adobe.xdp+xml");
		
		profile.setPreference("pdfjs.disabled", Boolean.TRUE);
		profile.setPreference("pdfjs.firstRun", Boolean.FALSE);
		profile.setPreference("pdfjs.previousHandler.alwaysAskBeforeHandling", Boolean.FALSE);
		profile.setPreference("pdfjs.previousHandler.preferredAction", 4);
		profile.setPreference("pdfjs.disabled", Boolean.TRUE);
		
		profile.setPreference("browser.download.useDownloadDir", Boolean.TRUE);
		profile.setPreference("browser.download.manager.focusWhenStarting", Boolean.FALSE);
		profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", new File(".\\").getAbsolutePath());
        
        //browser.download.panel.shown
        profile.setPreference("browser.helperApps.alwaysAsk.force", Boolean.FALSE);
        profile.setPreference("browser.download.manager.showWhenStarting", Boolean.FALSE);
        profile.setPreference("browser.download.manager.useWindow", Boolean.FALSE);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,text/pdf,application/octet-stream,application/x-pdf,text/plain,text/xml");

		return profile;
	}
	
	/**
	 * Permet de finaliser le cas d'essai en erreur.
	 * @param outil la boite à outil
	 * @param casEssai le cas d'essai
	 * @param ex l'exception générant l'erreur
	 * @throws SeleniumException en cas d'erreur.
	 */
	public final void finaliserTestEnErreur(final SeleniumOutils outil, final CasEssaiBean casEssai, final SeleniumException ex, final String idObjectif) throws SeleniumException {
		ex.printStackTrace();
		casEssai.setCommentaire(ex.toString());
		logger(ex.toString());
		finaliserTest(outil, casEssai, idObjectif, false);
	}

	/**
	 * Finalise l'execution d'un test en renseignant les log du cas d'essai et du test à  partir des
	 * logs du driver.
	 * 
	 * @param outils le driver.
	 * @param casEssai le cas d'essai concerné par le test.
	 * @throws SeleniumException en cas d'erreur lors de la génération du fichier excel de rapport.
	 */
	private void finaliserTest(SeleniumOutils outils, CasEssaiBean casEssai, final String idObjectif, boolean succes) throws SeleniumException {
		// On finalise aussi les sous cas.
		for(CasEssaiBean sousCas : casEssai.getTests()) {
			finaliserTest(outils, sousCas, casEssai.getNomCasEssai() + casEssai.getTime(), sousCas.getEtatFinal());
		}
		// Si le driver n'est pas nul on effectue des capture d'écran et on récupère les logs.
		if (outils != null) {
			casEssai.setRegistreExecution(outils.getDriver());
			logger(casEssai.getRegistreExecution() + "\n" + casEssai.toString());
			if (casEssai.getRepertoireTelechargement() != null) {
				outils.captureEcran("captureFinale" + casEssai.getNomCasEssai(), casEssai.getRepertoireTelechargement());
			} else {
				outils.captureEcran("captureFinale" + casEssai.getNomCasEssai(), casEssai.getNomCasEssai());
			}
		}
		// On valide l'objectif en fonction du succès du cas de test.
		casEssai.validerObjectif(outils.getDriver(), idObjectif, succes);
		//setCasEssai(casEssai);

		logger(casEssai.toString());

		//TODO A remettre
		if (outils != null) {
			outils.getDriver().quit();
		}

		// On renseigne le rapport d'execution avec les données du cas de test.
		XLSOutils.renseignerExcel(casEssai);
		
		// On tente de mettre à jour ALM
		if (casEssai.getAlm()) {
			ALMOutils.miseAJourTestSet(casEssai, succes);
			System.out.println("Mise à jour dans ALM");
		}
	}
	
	/**
	 * Fonction de finalisation de test.
	 * @param outil la boite à outil.
	 * @param casEssai le cas d'essai.
	 * @param idObjectif l'id de l'objectif final.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public final void finaliserTest(SeleniumOutils outil, CasEssaiBean casEssai, final String idObjectif) throws SeleniumException {
		finaliserTest(outil, casEssai, idObjectif, true);
	}
	
	/**
	 * Permet d'ajouter une ligne dans le registre d'execution pour apporter plus d'informations.
	 * 
	 * @param append la chaine de caractère à  ajouter dans le registre d'execution.
	 */
	public final void logger(final String append) {
		if (getLogs() != null) {
			setLogs(getLogs() + "\n" + append);
		} else {
			setLogs(append);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// FONCTION COMMUNES :
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Permet la première étape des scénario : la connection avec le jeton à l'application.
	 * @param outil l'outil de manipulation de selenium.
	 * @param idClient l'identifiant du client (et donc du bouchon) si il est connu, sinon il sera déterminer.
	 * @param producteur le cas d'essai est il producteur ? Sinon distributeur.
	 * @param distributeur quel distributeur ? (CE, BP, IOM, BRED) voir Constante.CAS_XX
	 * @param modificateur le modificateur de bouchon si il y a lieu.
	 * @return l'identifiant du client.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public String saisieJeton(SeleniumOutils outil, String idClient, boolean producteur, int distributeur, ModificateurBouchon modificateur) throws SeleniumException {
		return saisieJeton(outil, idClient, producteur, distributeur, modificateur, null, null);
	}
	
	/**
	 * Permet la première étape des scénario : la connection avec le jeton à l'application.
	 * @param outil l'outil de manipulation de selenium.
	 * @param idClient l'identifiant du client (et donc du bouchon) si il est connu, sinon il sera déterminer.
	 * @param producteur le cas d'essai est il producteur ? Sinon distributeur.
	 * @param distributeur quel distributeur ? (CE, BP, IOM, BRED) voir Constante.CAS_XX
	 * @param modificateur le modificateur de bouchon si il y a lieu.
	 * @return l'identifiant du client.
	 * @throws SeleniumException en cas d'erreur.
	 */
	public String saisieJeton(SeleniumOutils outil, String idClient, boolean producteur, int distributeur, ModificateurBouchon modificateur, String agence, String etablissement) throws SeleniumException {
		String retour = idClient;
		String chaineCible = "";
		String etab = etablissement;
		String agce = agence;
		
		// On génère le boucon pour le distributeur choisie.
		retour = generationBouchon(distributeur, idClient, modificateur);		
		
		// Chargement de la page d'injection de jeton
		switch(distributeur) {
			case Constantes.CAS_CE : 
				outil.chargerUrl(Constantes.URL_CE_FUTURE_REROUTAGE); 
				if(etab == null && agce == null){chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>"+ retour +"</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>11315</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>1131500030000135</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>1131500030000135</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>DISTRIBUTEUR</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>"; }
				else{chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + retour + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>"+ agce +"</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>"+ etab +"</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>"+ etab +"</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>" + (producteur?"PRODUCTEUR":"DISTRIBUTEUR") + "</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>";	}
				break;
			case Constantes.CAS_IOM : 
				outil.chargerUrl(Constantes.URL_CE_FUTURE_REROUTAGE); 
				chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + retour + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>18315</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>1831500030000001</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>1831500030000001</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>" + (producteur?"PRODUCTEUR":"DISTRIBUTEUR") + "</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>";
				break;
			case Constantes.CAS_BP : 
				outil.chargerUrl(Constantes.URL_BP_FUTURE_REROUTAGE); 
				if(etab == null && agce == null){chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + retour + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>038</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>00022</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>00022</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>9</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>" + (producteur?"PRODUCTEUR":"DISTRIBUTEUR") + "</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx></ListCtx><ProtocoleTech><header><version>2</version><messageId></messageId><timestamp>22/05/2012</timestamp><language>FR</language><country>FR</country><otherElements><name>name1</name><value>value1</value></otherElements><otherElements><name>name2</name><value>value2</value></otherElements></header><context><company>BP</company><application>EQX</application><channel>Intranet</channel><bank>038</bank><agency></agency><goal></goal><userId></userId></context><actors><company>NFI</company><application>v45</application><versionAppli>1.0</versionAppli><channel>INTRANET</channel><bank>All</bank><agency>All</agency><goal></goal><userId>12345678</userId></actors></ProtocoleTech></fluxreroutage>";}
				else{chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + retour + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>"+ agce +"</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>"+ etab +"</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>"+ etab +"</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>" + (producteur?"PRODUCTEUR":"DISTRIBUTEUR") + "</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>";	}
				//chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + retour + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>18315</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>1831500030000001</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>1831500030000001</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>" + (producteur?"PRODUCTEUR":"DISTRIBUTEUR") + "</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>";
				break;
			case Constantes.CAS_BRED : 
				outil.chargerUrl(Constantes.URL_BP_FUTURE_REROUTAGE);
				//chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + retour + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>18315</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>1831500030000001</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>1831500030000001</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>8</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>" + (producteur?"PRODUCTEUR":"DISTRIBUTEUR") + "</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>URL_MAJ_CLNT</Cd><Val><![CDATA[NOMAPPLI|CEFI|NUMPLAN|1]]></Val></Ctx></ListCtx><ProtocoleTech><CodePrlfAgnt></CodePrlfAgnt><IdntEtabEntt>CE</IdntEtabEntt><IdntInteEdsAgnt></IdntInteEdsAgnt><RefrPosteFoncAgnt></RefrPosteFoncAgnt><IdntAgnt></IdntAgnt><IdntAgntTech>T0092139</IdntAgntTech><TypeCanlAcces></TypeCanlAcces><IdntAgntAcces></IdntAgntAcces><RefrExtnAgnt></RefrExtnAgnt><CodeTypeIdntExtn>W</CodeTypeIdntExtn><IdntExtnConx>T0092139</IdntExtnConx><TypePrflAgnt>1</TypePrflAgnt></ProtocoleTech></fluxreroutage>";
				chaineCible = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>" + retour + "</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>038</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>00022</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>00022</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>9</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>" + (producteur?"PRODUCTEUR":"DISTRIBUTEUR") + "</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx></ListCtx><ProtocoleTech><header><version>2</version><messageId></messageId><timestamp>22/05/2012</timestamp><language>FR</language><country>FR</country><otherElements><name>name1</name><value>value1</value></otherElements><otherElements><name>name2</name><value>value2</value></otherElements></header><context><company>BP</company><application>EQX</application><channel>Intranet</channel><bank>038</bank><agency></agency><goal></goal><userId></userId></context><actors><company>NFI</company><application>v45</application><versionAppli>1.0</versionAppli><channel>INTRANET</channel><bank>All</bank><agency>All</agency><goal></goal><userId>12345678</userId></actors></ProtocoleTech></fluxreroutage>";
				break;
		}
		
		System.out.println(chaineCible);
		
		// Attente de l'affichage du titre de la page
		outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
		
		// Génération de la chaine du Jeton	
		outil.viderEtSaisir(chaineCible, Cibles.SAISIE_JETON);

		// Chargement d'IZIVENTE
		outil.cliquer(Cibles.VALIDER_SAISIE_JETON);		
		outil.attendreChargementPage(Constantes.TITRE_PAGE_IZIVENTE);
		
		return retour;
	}
	/**
	 * 
	 * @param distributeur le distributeur (CE ou BP)
	 * @param numFFI le numéro FFI du dossier
	 * @param idClient le numéro de personne physique de l'emprunteur
	 * @param numIUN le numéro IUN du client
	 * @param flag le flag du dossier indiquant son état d'avancée 
	 * @throws SeleniumException
	 */
	public void ecritureFichierDonnees(String distributeur, String numFFI, String numCltDist, String numIUN, String typeDossier, int flag) throws SeleniumException {
		String distrib = distributeur;
		String FFI = numFFI;
		String idClnt = numCltDist;
		String IUN = numIUN;
		String typeDos = typeDossier;
		int flg = flag;
		String chaine = (distrib +";"+ FFI + ";" + idClnt + ";" + IUN + ";"+ typeDos +";"+ flg +"\r\n");
		try {
			//TODO modifier le chemin vers le fichier, il doit être dans le propertie.
			Files.write(Paths.get("src/test/DonneesClientDossier.txt"),chaine.getBytes(),StandardOpenOption.APPEND);
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new SeleniumException(Erreurs.E020, "Impossible d'écrire dans DonneesClientDossier");
		} 
	}
	
	
	/**
	 * Cette fonction à pour objectif de lire le fichier de données.
	 * @return le contenu du fichier de donnée.
	 * @throws IOException en cas d'erreur d'accès au fichier.
	 */
	public String[] lectureFichierDonnees() throws IOException {
		String contenu = "";
		String pathfichierDonnees = "src/test/DonneesClientDossier.txt";
		FileInputStream flux = new FileInputStream(pathfichierDonnees); 
		InputStreamReader lecture = new InputStreamReader(flux);
		BufferedReader buff = new BufferedReader(lecture);
		String ligne;
		while ((ligne=buff.readLine())!=null){
			contenu = contenu.concat(ligne);
		}
		buff.close(); 
		String[] tableauDonneesClient = contenu.split(";");
		return tableauDonneesClient;
	}
	
	/**
	 * Cette fonction à pour objectif de lire le fichier de données.
	 * @return le contenu du fichier de données.
	 * @throws SeleniumException en cas d'erreur d'accès au fichier.
	 */
	public List<String> renvoyerContenuFichierDonnee(int etapeSouhaitee) throws SeleniumException {
		try {
			List<String> retour = new LinkedList<String>();
			String pathfichierDonnees = "src/test/DonneesClientDossier.txt";
			FileInputStream flux = new FileInputStream(pathfichierDonnees);
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			
			// Pour chaque ligne lue, on ajoute une chaine de caractère dans la liste.
			while ((ligne = buff.readLine()) != null){
				String[] temp = ligne.split(";");
				// On vérifie qu'une étape est spécifiée et que c'est bien cette étape qui est requise
				if (temp.length > 5 && temp[5].equals(Integer.toString(etapeSouhaitee))) {
					retour.add(ligne);
					//TODO à virer
					System.out.println("J'ai trouvé : " + ligne);
				}
			}
			buff.close(); 
			return retour;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new SeleniumException(Erreurs.E020, "Impossible de lire dans DonneesClientDossier");
		} catch (IOException e) {
			e.printStackTrace();
			throw new SeleniumException(Erreurs.E020, "Impossible de lire dans DonneesClientDossier");
		} 
	}
	
	/**
	 * Cette fonction à pour objectif de remplacer le contenu du fichier de données.
	 * @return le contenu du fichier de données.
	 * @throws SeleniumException en cas d'erreur d'accès au fichier.
	 */
	public Boolean remplacer(String numeroFFI, String chaine) throws SeleniumException {
		try {
			Boolean remplacement = false;
			List<String> contenu = new LinkedList<String>();
			String pathfichierDonnees = "src/test/DonneesClientDossier.txt";
			FileInputStream flux = new FileInputStream(pathfichierDonnees);
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			
			// Pour chaque ligne lue, on ajoute une chaine de caractère dans la liste.
			while ((ligne = buff.readLine()) != null){
				String[] temp = ligne.split(";");
				// On vérifie qu'une étape est spécifiée et que c'est bien cette étape qui est requise
				if (temp.length > 2 && temp[1].equals(numeroFFI)) {
					contenu.add(chaine);
					System.out.println("J'ai remplacé : " + ligne + " par " + chaine);
					remplacement = true;
				} 
				else {
					contenu.add(ligne);
				}
			}
			buff.close();
			
			// On vide le fichier actuel et on créer le nouveau fichier contenant le nouveau contenu
			PrintWriter writer = new PrintWriter(new File(pathfichierDonnees));
			writer.print("");
			writer.close();
			
			for (String instance : contenu) {
				Files.write(Paths.get(pathfichierDonnees),(instance + "\r\n").getBytes(),StandardOpenOption.APPEND);
			}
			
			return remplacement;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new SeleniumException(Erreurs.E020, "Impossible de lire dans DonneesClientDossier");
		} catch (IOException e) {
			e.printStackTrace();
			throw new SeleniumException(Erreurs.E020, "Impossible de lire dans DonneesClientDossier");
		} 
	}
}