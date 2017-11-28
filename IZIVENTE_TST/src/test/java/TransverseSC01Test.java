package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;
import moteurs.FirefoxImpl;
import outils.SeleniumOutils;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import exceptions.SeleniumException;

public class TransverseSC01Test extends TNRSC00 {
	
	/**
	 * id de sérialisation.
	 */
	private static final long serialVersionUID = -3737267445392145659L;

	
	@Test
	public void editiqueFACELIA() throws SeleniumException {
		
		this.typeDossier = TypeProduit.FACELIA;
		this.setIdConfluence("71303550");
		this.situationDeVente = "AUTR";
		this.modificateur.sansConjoint = true;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.montantCredit = "7500";
		this.mensualite = "225,00";
		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.tiersCoEmp = false;
		this.assuranceEmp = false;
		this.assuranceTiers = false;
		// On fixe le client pour toujours utilisé le même
		this.idClient = "9503253";
		this.coutProjet = "8000";
		//this.situationDeVente = "Autre";
		this.comparaisonLiasse = true;
		//this.numPersPhysTiers = "942500502";
		this.setNomCasEssai("EditiqueFACELIA -" + getTime());
		
		miseAEdit();
		suppression();
		comparaisonLiasse();
		//miseEnForce();
		//murissement(null);
		//consultationIZIGATE();
	}
	
	@Test
	public void editiqueCREODIS() throws SeleniumException {
		this.setIdConfluence("70455570");
		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.CREODIS;
		this.aucunCoEmp = true;
		this.conjointCoEmp = false;
		this.tiersCoEmp = false;
		this.assuranceEmp = false;
		this.assuranceTiers = false;
		this.montantCredit = "8000";
		// On fixe le client pour toujours utilisé le même
		this.idClient = "9503177";
		this.coutProjet = "8000";
		this.mensualite = "300";
		//this.situationDeVente = "Autre";
		this.situationDeVente = "AUTR";
		this.comparaisonLiasse = true;
		//this.numPersPhysTiers = "942500502";
		this.setNomCasEssai("EditiqueCREODIS -" + getTime());
		
		miseAEdit();
		suppression();
		comparaisonLiasse();
		//miseEnForce();
		//murissement(null);
		//consultationIZIGATE();
	}
	
	@Test
	public void editiqueIZICARTE() throws SeleniumException {
		this.setAlm(false);
		this.setNomCasEssai("EditiqueIZICARTE -" + getTime());
		this.setIdConfluence("71303939");
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.IZICARTE;
		this.aucunCoEmp = true;
		this.assuranceEmp = false;
		this.montantCredit = "5000";
		this.mensualite = "150";
		this.situationDeVente = "BANC";
		this.comparaisonLiasse = true;
		this.idClient = "942502332";
		
		miseAEdit();
		suppression();
		comparaisonLiasse();
	}
	
	@Test
	public void editiqueTRAVAUX() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario3 = new CasEssaiIziventeBean();
		this.setAlm(false);
		this.setNomCasEssai("EditiqueTRAVAUX -" + getTime());
		this.setIdConfluence("71304002");
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.modificateur.emprunteurJeune = true;
		this.modificateur.coEmprunteurJeune = true;
		this.conjointCoEmp = true;
		this.assuranceEmp = true;
		this.assuranceConjointCoEmp = false;
		this.typeUnivers = "TRAVAUX";
		this.typeOffre = "TRAVAUX ECHELONNE DIFF TOTAL";
		this.typeObjet = "TRAVAUX RESIDENCE PRINCIPALE";
		this.coutProjet = "22000";
		this.montantCredit = "22000";
		this.mensualite = "367";
		this.comparaisonLiasse = true;
		this.idClient = "942502334";
		this.dureeDiffere = "12";
		
		miseAEdit();
		suppression();
		comparaisonLiasse();
	}
	
	@Test
	public void editiqueTRESORERIE() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario6 = new CasEssaiIziventeBean();
		this.setAlm(false);
		this.setNomCasEssai("EditiqueTRESORERIE -" + getTime());
		this.setIdConfluence("71304208");
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.modificateur.emprunteurSenior = true;
		this.modificateur.coEmprunteurJeune = true;
		this.modificateur.emprunteurCasden = true;
		this.aucunCoEmp = false;
		this.conjointCoEmp = true;
		this.assuranceEmp = true;
		this.assuranceConjointCoEmp = true;
		this.typeUnivers = "TRESORERIE";
		this.typeOffre = "CREDIT TRESORERIE CASDEN";
		this.typeObjet = "TRESORERIE";
		this.coutProjet = "3000";
		this.montantCredit = "3000";
		this.mensualite = "83";
		
		this.comparaisonLiasse = true;
		this.idClient = "9503275";
		
		miseAEdit();
		suppression();
		comparaisonLiasse();
	}
	
	@Test
	public void editiquePERMIS1EURO() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario6 = new CasEssaiIziventeBean();
		this.setAlm(false);
		this.setNomCasEssai("EditiquePERMIS1EURO -" + getTime());
		this.setIdConfluence("71304712");
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.modificateur.emprunteurJeune = true;
		this.modificateur.sansConjoint = true;
		this.aucunCoEmp = false;
		this.tiersCaution = true;
		this.assuranceEmp = false;
		this.assuranceTiers = false;
		this.numPersPhysTiers = "942500500";
		this.typeUnivers = "TRESORERIE";
		this.typeOffre = "PERMIS 1 EURO";
		this.typeObjet = "DIVERS";
		this.coutProjet = "800";
		
		this.comparaisonLiasse = true;
		this.idClient = "942502556";
		
		miseAEdit();
		suppression();
		comparaisonLiasse();
	}
	
	@Test
	public void editiqueETUDIANT() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario6 = new CasEssaiIziventeBean();
		this.setAlm(false);
		this.setNomCasEssai("EditiqueETUDIANT -" + getTime());
		this.setIdConfluence("71304751");
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.modificateur.emprunteurJeune = true;
		this.aucunCoEmp = true;
		this.assuranceEmp = false;
		this.typeUnivers = "TRESORERIE";
		this.typeOffre = "ETUDIANT NON PART DIFF TOTAL";
		this.typeObjet = "AUTRE ETUDE";
		this.coutProjet = "2000";
		this.montantCredit = "2000";
		this.mensualite = "100";
		this.dureeDiffere = "12";
		
		this.comparaisonLiasse = true;
		this.idClient = "942502558";
		
		miseAEdit();
		suppression();
		comparaisonLiasse();
	}
	
//	@Test
//	public void transverseCR() throws SeleniumException {
//		
//		//FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
////		FirefoxProfile profile = configurerProfilNatixis();
////		FirefoxImpl driver = new FirefoxImpl(profile);
////		SeleniumOutils outil = new SeleniumOutils(driver);
//
//		this.setAlm(false);
//		this.distributeur = Constantes.CAS_BP;
//		this.typeDossier = TypeProduit.CREODIS;
//		this.aucunCoEmp = true;
//		this.conjointCoEmp = false;
//		this.tiersCoEmp = false;
//		this.assuranceEmp = false;
//		this.assuranceTiers = false;
//		this.montantCredit = "8000";
//		//this.idClient = "942501348";
//		this.coutProjet = "8000";
//		this.mensualite = "300";
//		//this.situationDeVente = "Autre";
//		this.situationDeVente = "AUTR";
//		//this.comparaisonLiasse = true;
//		//this.numPersPhysTiers = "942500502";
//		this.setNomCasEssai("TransverseCR -" + getTime());
//		
//		miseAEdit();
//		//miseEnForce();
//		//murissement(null);
//		//consultationIZIGATE();
//		
//	}
//	
//	@Test
//	public void transversePP() throws SeleniumException {
//		
//		this.setAlm(false);
//		this.distributeur = Constantes.CAS_BP;
//		this.etablissement = "056";
//		this.agence = "00009";
//		this.typeDossier = TypeProduit.CREDIT_AMORT;
//		this.aucunCoEmp = true;
//		this.assuranceEmp = true;
//		this.typeUnivers = "TRESORERIE";
//		this.typeOffre = "CREDIT TRESORERIE";
//		this.typeObjet = "TRESORERIE";
//		this.coutProjet = "18000";
//		this.mensualite = "500";
//		this.montantCredit = "17000";
//		//simulation();
//		//validation();
//		miseAEdit();
//		//miseEnForce();
//		
//	}
	
//	@Test
//	public void TranserveMiseEnForce() throws SeleniumException {
//		
//		//simulation();
//		//validation();
//		//miseAEdit();
//		miseEnForce();
//		murissement(null);
//	}
}
