package test.java;

import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Scénario 7 des tests automatisés pour IZIVENTE - 11/2016
 * Editique Permis 1 Euro (CE)
 * @author levieilfa bardouma
 */
public class TNRSC07 extends TNRSC00 {

/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

	@Test
	public void lancementTNR() throws SeleniumException {
		// Description du scénario
		//CasEssaiIziventeBean scenario7 = new CasEssaiIziventeBean();
		this.setAlm(Constantes.ACTIVATION_ALM);
		this.setIdUniqueTestLab(54394);
		this.setIdUniqueTestPlan(-1);
		this.setNomCasEssai("TNRSC07-" + getTime());
		this.setDescriptif("TNRSC07 - CE - IZIVENTE_Editique Permis 1 Euro");
		this.setNomTestLab("TNRSC07 - CE - IZIVENTE_Editique Permis 1 Euro");
		//this.setNomTestPlan("TNRSC07 - IZIVENTE_Editique Permis 1 Euro");
		this.setCheminTestLab("POC Selenium\\IZIVENTE");
		
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.modificateur.emprunteurJeune = true;
		this.aucunCoEmp = false;
		this.tiersCaution = true;
		this.assuranceEmp = false;
		this.assuranceTiers = false;
		this.numPersPhysTiers = "942500500";
		this.typeUnivers = "TRESORERIE";
		this.typeOffre = "PERMIS 1 EURO";
		this.typeObjet = "DIVERS";
		this.coutProjet = "800";
		
		miseAEdit();
	}
}