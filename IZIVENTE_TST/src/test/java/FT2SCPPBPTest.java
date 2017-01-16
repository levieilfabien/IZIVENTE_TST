package test.java;
import org.junit.Test;

import exceptions.SeleniumException;
import main.constantes.Constantes;
import main.constantes.TypeProduit;

/**
 * Scénario 2 des tests automatisés pour IZIVENTE - Projet Fusion
 * Editique Crédit Amortissable Etudiant Immédiat (BP)
 * @author levieilfa bardouma
 */
public class FT2SCPPBPTest extends TNRSC00 {

	/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void fT2SCPPBPTest() throws SeleniumException {
		
		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.edition = true;
		this.miseEnGestion = false;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.typeUnivers = "JEUNES";
		this.typeOffre = "BPI ETUDIANT IMMEDIAT";
		this.typeObjet = "AUTRE ETUDE";
		this.coutProjet = "3000";
		this.mensualite = "83";
		this.montantCredit = "3000";
		this.modificateur.emprunteurJeune = true;
		this.modificateur.coEmprunteurJeune = true;
		
		simulation();
		//miseAEdit();
		//miseEnForce();
	}
}