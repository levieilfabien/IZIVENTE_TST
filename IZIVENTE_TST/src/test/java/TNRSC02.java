package test.java;

import java.io.File;

import main.bean.CasEssaiIziventeBean;
import main.constantes.Cibles;
import main.constantes.Constantes;
import main.constantes.TypeProduit;
import moteurs.FirefoxImpl;
import moteurs.GenericDriver;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxProfile;

import outils.SeleniumOutils;
import beans.ObjectifBean;
import exceptions.SeleniumException;

/**
 * Scénario 2 des tests automatisés pour IZIVENTE - 11/2016
 * Editique IZICARTE (CE)
 * @author levieilfa bardouma
 */
public class TNRSC02 extends TNRSC00 {

	/**
	 * Id de sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario2 = new CasEssaiIziventeBean();
		this.setAlm(true);
		this.setIdUniqueTestLab(54199);
		this.setNomCasEssai("TNRSC02-" + getTime());
		this.setDescriptif("TNRSC02 - CE - IZIVENTE_Editique Izicarte");
		this.setNomTestLab("TNRSC02 - CE - IZIVENTE_Editique Izicarte");
		//scenario2.setNomTestPlan("TNRSC02 - IZIVENTE_Editique Izicarte");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.IZICARTE;
		this.aucunCoEmp = true;
		this.assuranceEmp = false;
		this.montantCredit = "8000";
		this.situationDeVente = "BANC";
		
		miseAEdit();
	}
}