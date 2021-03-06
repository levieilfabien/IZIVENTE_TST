package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * Sc�nario 3 des tests automatis�s pour IZIVENTE - Projet Fusion
 * Editique IZICARTE (CE)
 * @author levieilfa bardouma
 */
public class FT3SCCRCETest extends TNRSC00 {

	/**
 * Id de s�rialisation par d�faut.
 */
private static final long serialVersionUID = 1L;

@Test
public void FT3SCCRCETestLancement() throws SeleniumException {
		
		this.setAlm(false);
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.IZICARTE;
		this.edition = true;
		this.miseEnGestion = false;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.montantCredit = "3000";
		this.mensualite = "240";
		
		simulation();
		//validation();
		//miseAEdit();
		//miseEnForce();
		//murissement();
	}
	
}