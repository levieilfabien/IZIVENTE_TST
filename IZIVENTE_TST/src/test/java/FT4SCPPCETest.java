package test.java;

import main.constantes.Constantes;
import main.constantes.TypeProduit;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * Scénario 4 des tests automatisés pour IZIVENTE - Projet Fusion
 * Editique Credit Amortissable (CE)
 * @author levieilfa bardouma
 */
public class FT4SCPPCETest extends TNRSC00 {

	/**
 * Id de sérialisation par défaut.v
 */
private static final long serialVersionUID = 1L;

@Test
public void fT4SCPPCETest() throws SeleniumException {
		
		this.setAlm(false);
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = TypeProduit.CREDIT_AMORT;
		this.edition = true;
		this.miseEnGestion = false;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		this.typeUnivers = "TRAVAUX";
		this.typeOffre = "TRAVAUX ECHELONNE DIFF TOTAL";
		this.typeObjet = "TRAVAUX RESIDENCE PRINCIPALE";
		this.coutProjet = "22000";
		this.mensualite = "367";
		this.montantCredit = "22000";
		this.dureeDiffere = "12";
		this.modificateur.emprunteurJeune = true;
		
		//simulation();
		//validation();
		miseAEdit();
		miseEnForce();
		//murissement();
	}
}