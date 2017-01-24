package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;
import outils.SeleniumOutils;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import exceptions.SeleniumException;

/**
 * Scénario 1 des tests automatisés pour IZIVENTE - Projet Fusion
 * Editique FACELIA (BP)
 * @author levieilfa bardouma
 */
public class FT1SCCRBPTest extends TNRSC00 {

	/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void FT1SCCRBPTestLancement() throws SeleniumException {
	
		FirefoxBinary ffBinary = new FirefoxBinary(new File(Constantes.EMPLACEMENT_FIREFOX));
		FirefoxProfile profile = configurerProfilNatixis();
		FirefoxImpl driver = new FirefoxImpl(ffBinary, profile);
		/*this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.FACELIA;
		this.edition = true;
		this.miseEnGestion = false;
		this.aucunCoEmp = true; 
		this.assuranceEmp = false;
		this.montantCredit = "8000";
		this.mensualite = "240";*/
		//simulation();
		//validation();
		//miseAEdit();
		//miseEnForce();
		SeleniumOutils outil = new SeleniumOutils(driver);
		murissement(outil);
	}
}