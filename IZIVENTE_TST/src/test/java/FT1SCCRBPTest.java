package test.java;

import main.constantes.Constantes;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * Scénario 1 des tests automatisés pour IZIVENTE - Projet Fusion
 * Editique FACELIA (BP)
 * @author levieilfa bardouma
 */
public class FT1SCCRBPTest extends TNRSC00 {

	/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void FT1SCCRBPTestLancement() throws SeleniumException {

			
		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = Constantes.FACELIA;
		this.edition = true;
		this.miseEnGestion = false;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.montantCredit = "8000";

		miseAEdit();
		miseEnForce();
		//murissement();
	}
}