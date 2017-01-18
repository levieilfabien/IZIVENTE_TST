package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * Sc�nario 1 des tests automatis�s pour IZIVENTE - Projet Fusion
 * Editique FACELIA (BP)
 * @author levieilfa bardouma
 */
public class FusionC2SC01 extends TNRSC00 {

	/**
 * Id de s�rialisation par d�faut.
 */
private static final long serialVersionUID = 1L;

@Test
public void fusionC2SC01() throws SeleniumException {

		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.CREODIS;
		this.aucunCoEmp = true;
		this.assuranceEmp = false;
		this.montantCredit = "3000";
		this.mensualite = "200";

		miseAEdit();
	}
}