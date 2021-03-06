package test.java;
import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Sc�nario 2 des tests automatis�s pour IZIVENTE - Projet Fusion
 * Editique Cr�dit Amortissable Etudiant Imm�diat (BP)
 * @author levieilfa bardouma
 */
public class FT2SCPPBPTest extends TNRSC00 {

	/**
 * Id de s�rialisation par d�faut.
 */
private static final long serialVersionUID = 1L;

@Test
public void fT2SCPPBPTest() throws SeleniumException {
		
		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.typeUnivers = "TRESORERIE";
		this.typeOffre = "CREDIT TRESORERIE";
		this.typeObjet = "TRESORERIE";
		this.coutProjet = "3000";
		this.mensualite = "83";
		this.montantCredit = "3000";
		//this.modificateur.emprunteurJeune = true;
		//this.modificateur.coEmprunteurJeune = true;
		
		//simulation();
		//validation();
		miseAEdit();
		//miseEnForce();
		//murissement();
	}
}