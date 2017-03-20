package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Sc�nario 8 des tests automatis�s pour IZIVENTE - 11/2016
 * Editique Pr�t �tudiant diff�r� total (CE)
 * @author levieilfa bardouma
 */
public class TNRSC08 extends TNRSC00 {

	/**
	 * Id de s�rialisation par d�faut.
	 */
	private static final long serialVersionUID = 1L;
	
	@Test
	public void lancementTNR() throws SeleniumException {
	// Description du sc�nario
	//CasEssaiIziventeBean scenario8 = new CasEssaiIziventeBean();
	this.setAlm(Constantes.ACTIVATION_ALM);
	this.setIdUniqueTestLab(54395);
	this.setIdUniqueTestPlan(-1);
	this.setNomCasEssai("TNRSC08-" + getTime());
	this.setDescriptif("TNRSC08 - CE - IZIVENTE_Editique Pr�t �tudiant diff�r� total");
	this.setNomTestLab("TNRSC08 - CE - IZIVENTE_Editique Pr�t �tudiant diff�r� total");
	//this.setNomTestPlan("TNRSC08 - IZIVENTE_Editique Pr�t �tudiant diff�r� total");
	this.setCheminTestLab("POC Selenium\\IZIVENTE");
	
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

	miseAEdit();
	}
}