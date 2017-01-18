package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * PJNFIEVA11001 Fusion BP MED
 * Chantier 3 Scénario 2 IZIVENTE - Simulation Facélia avec co emprunteur avant bascule
 * @author levieilfa
 * @author bardouma
 */
public class FusionC3SC02 extends TNRSC00 {

	/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void fusionC3SC02() throws SeleniumException {

		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.etablissement = "056";
		this.agence = "00009";
		this.typeDossier = TypeProduit.FACELIA;
		this.aucunCoEmp = true; 
		this.assuranceEmp = true;
		this.montantCredit = "7500";
		this.mensualite = "300";

		simulation();
	}
}