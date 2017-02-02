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
	 * id de sÚrialisation.
	 */
	private static final long serialVersionUID = -3737267445392145659L;

	@Test
	public void transverseCR() throws SeleniumException {
		
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		SeleniumOutils outil = new SeleniumOutils(driver);

		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.CREODIS;
		this.aucunCoEmp = true;
		this.conjointCoEmp = false;
		this.tiersCoEmp = false;
		this.assuranceEmp = false;
		this.assuranceTiers = false;
		this.montantCredit = "8000";
		//this.idClient = "942501348";
		this.coutProjet = "8000";
		this.mensualite = "300";
		//this.numPersPhysTiers = "942500502";
		
		miseAEdit();
		miseEnForce();
		murissement(outil);
		consultationIZIGATE();
		
	}
	
	@Test
	public void transversePP() throws SeleniumException {
		
		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.etablissement = "056";
		this.agence = "00009";
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.typeUnivers = "TRESORERIE";
		this.typeOffre = "CREDIT TRESORERIE";
		this.typeObjet = "TRESORERIE";
		this.coutProjet = "18000";
		this.mensualite = "500";
		this.montantCredit = "17000";
		//simulation();
		//validation();
		miseAEdit();
		//miseEnForce();
		
	}
	
	@Test
	public void TranserveMiseEnForce() throws SeleniumException {
		
		//simulation();
		//validation();
		//miseAEdit();
		miseEnForce();
		
	}
}
