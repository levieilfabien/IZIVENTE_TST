package test.java;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import beans.ObjectifBean;
import exceptions.SeleniumException;
import main.bean.CasEssaiIziventeBean;
import main.constantes.Cibles;
import main.constantes.Constantes;
import main.constantes.TypeProduit;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;
import outils.SeleniumOutils;

/**
 * Sc�nario 1 des tests automatis�s pour IZIVENTE - 11/2016
 * Editique FACELIA (BP)
 * @author levieilfa bardouma
 */
public class TNRSC01 extends TNRSC00 {

	/**
	 * Id de s�rialisation.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du sc�nario
		//CasEssaiIziventeBean scenario1 = new CasEssaiIziventeBean();
		this.setAlm(true);
		this.setIdUniqueTestLab(54199);
		this.setNomCasEssai("TNRSC01-" + getTime());
		this.setDescriptif("TNRSC01 - BP - IZIVENTE_Editique FACELIA CR Debit Credit");
		this.setNomTestLab("TNRSC01 - BP - IZIVENTE_Editique FACELIA CR Debit Credit");
		//scenario1.setNomTestPlan("TNRSC01 - BP - IZIVENTE_Editique FACELIA CR Debit Credit");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		this.setNomTestPlan(null);
		
		this.distributeur = Constantes.CAS_BP;
		//this.etablissement = "056";
		//this.agence = "00009";
		this.typeDossier = TypeProduit.FACELIA;
		this.situationDeVente = "AUTR";
		this.modificateur.sansConjoint = true;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.montantCredit = "7500";
		this.mensualite = "225,00";
		
		miseAEdit();
	}
}
