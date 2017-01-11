package test.java;

import main.bean.ModificateurBouchon;
import main.constantes.Constantes;

import org.junit.Test;

import exceptions.SeleniumException;

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
public void FT2SCPPBPTestLancement() throws SeleniumException {
		
		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = Constantes.CREDIT_AMORT;
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
		ModificateurBouchon modificateur = new ModificateurBouchon();
		modificateur.emprunteurJeune = true;
		miseAEdit();
		miseEnForce();
		
	}
}