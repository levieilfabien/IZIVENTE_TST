package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Sc�nario 2 des tests automatis�s pour IZIVENTE - 11/2016
 * Editique IZICARTE (CE)
 * @author levieilfa bardouma
 */
public class TNRSC02 extends TNRSC00 {

	/**
	 * Id de s�rialisation.
	 */
	private static final long serialVersionUID = 1L;

	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du sc�nario
		//CasEssaiIziventeBean scenario2 = new CasEssaiIziventeBean();
		this.setAlm(Constantes.ACTIVATION_ALM);
		this.setIdConfluence("71303719");
		this.setIdUniqueTestLab(54391);
		this.setIdUniqueTestPlan(-1);
		this.setNomCasEssai("TNRSC02-" + getTime());
		this.setDescriptif("TNRSC02 - CE - IZIVENTE_Editique Izicarte");
		this.setNomTestLab("TNRSC02 - CE - IZIVENTE_Editique Izicarte");
		//scenario2.setNomTestPlan("TNRSC02 - IZIVENTE_Editique Izicarte");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.IZICARTE;
		this.aucunCoEmp = true;
		this.assuranceEmp = false;
		this.montantCredit = "5000";
		this.mensualite = "150";
		this.situationDeVente = "BANC";
		
		miseAEdit();
	}
}