package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * PJNFIEVA11001 Fusion BP MED
 * Chantier 3 Scénario 3 IZIVENTE - Validation Créodis sans co emprunteur avant bascule
 * @author levieilfa
 * @author bardouma
 */
public class FusionC3SC03 extends TNRSC00 {

	/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void fusionC3SC03() throws SeleniumException {
			
		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.etablissement = "056";
		this.agence = "00009";
		this.typeDossier = TypeProduit.CREODIS;
		this.edition = true;
		this.aucunCoEmp = true;
		this.assuranceEmp = false;
		this.montantCredit = "2000";
		this.mensualite = "100";

		validation();
	}
}