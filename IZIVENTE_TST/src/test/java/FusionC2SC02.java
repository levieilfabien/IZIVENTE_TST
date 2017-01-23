package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * PJNFIEVA11001 Fusion BP MED
 * Chantier 2 Scénario 2 IZIVENTE - Edition Facélia avec co emprunteur avant bascule
 * @author levieilfa
 * @author bardouma
 */
public class FusionC2SC02 extends TNRSC00 {

	/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void fusionC2SC02() throws SeleniumException {

		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.etablissement = "056";
		this.agence = "00009";
		this.typeDossier = TypeProduit.FACELIA;
		this.aucunCoEmp = true; 
		this.assuranceEmp = true;
		this.montantCredit = "5000";
		this.mensualite = "100";

		miseAEdit();
	}
}