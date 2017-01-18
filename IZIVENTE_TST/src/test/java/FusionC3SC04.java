package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * Scénario 1 des tests automatisés pour IZIVENTE - Projet Fusion
 * Editique FACELIA (BP)
 * @author levieilfa bardouma
 */
public class FusionC3SC04 extends TNRSC00 {

	/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void fusionC3SC04() throws SeleniumException {

		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.FACELIA;
		this.aucunCoEmp = true; 
		this.assuranceEmp = false;
		this.montantCredit = "8000";
		this.mensualite = "240";

		validation();
	}
}