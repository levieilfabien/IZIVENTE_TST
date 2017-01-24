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
 * Sc�nario 5 des tests automatis�s pour IZIVENTE - 11/2016
 * Editique Pr�t travaux echelonn� diff�r� partiel (CE)
 * @author levieilfa bardouma
 */
public class TNRSC05 extends TNRSC00 {
	
	/**
	 * Id de s�rialisation par d�faut.
	 */
	private static final long serialVersionUID = 1L;
	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du sc�nario
		//CasEssaiIziventeBean scenario5 = new CasEssaiIziventeBean();
		this.setAlm(true);
		this.setIdUniqueTestLab(54392);
		this.setNomCasEssai("TNRSC05-" + getTime());
		this.setDescriptif("TNRSC05 - CE - IZIVENTE_Pr�t travaux echelonn� diff�r� partiel");
		this.setNomTestLab("TNRSC05 - CE - IZIVENTE_Pr�t travaux echelonn� diff�r� partiel");
		//this.setNomTestPlan("TNRSC05 - IZIVENTE_Pr�t travaux echelonn� diff�r� partiel");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.typeUnivers = "TRAVAUX";
		this.typeOffre = "TRAVAUX DIFFERE 12MOIS PARTIEL";
		this.typeObjet = "TRAVAUX RESIDENCE PRINCIPALE";
		this.coutProjet = "20000";
		this.montantCredit = "20000";
		this.mensualite = "333";
		
		miseAEdit();
	}
}