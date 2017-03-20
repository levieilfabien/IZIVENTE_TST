package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Scénario 9 des tests automatisés pour IZIVENTE - 11/2016
 * Editique Prêt étudiant echelonné différé total FEI (CE)
 * @author levieilfa bardouma
 */
public class TNRSC09 extends TNRSC00 {

/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;
	@Test
	public void lancementTNR() throws SeleniumException {
	// Description du scénario
	//CasEssaiIziventeBean scenario9 = new CasEssaiIziventeBean();
	this.setAlm(Constantes.ACTIVATION_ALM);
	this.setIdUniqueTestLab(54396);
	this.setIdUniqueTestPlan(-1);
	this.setNomCasEssai("TNRSC09-" + getTime());
	this.setDescriptif("TNRSC09 - CE - IZIVENTE_Editique Prêt étudiant echelonné différé total FEI");
	this.setNomTestLab("TNRSC09 - CE - IZIVENTE_Editique Prêt étudiant echelonné différé total FEI");
	//this.setNomTestPlan("TNRSC09 - IZIVENTE_Editique Prêt étudiant echelonné différé total FEI");
	this.setCheminTestLab("POC Selenium\\IZIVENTE");
	
	this.distributeur = Constantes.CAS_CE;
	this.typeDossier = TypeProduit.CREDIT_AMORT;
	this.etablissement = "18715";
	this.agence = "1871500030000302";
	this.modificateur.emprunteurJeune = true;
	this.aucunCoEmp = true;
	this.assuranceEmp = false;
	this.typeUnivers = "TRESORERIE";
	this.typeOffre = "PEE3";
	this.typeObjet = "AUTRE ETUDE";
	this.coutProjet = "2000";
	this.montantCredit = "2000";
	this.mensualite = "100";
	this.dureeDiffere = "12";
	
	miseAEdit();
	}
}