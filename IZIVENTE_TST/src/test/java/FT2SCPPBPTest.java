package test.java;

import main.constantes.Constantes;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * Sc�nario 2 des tests automatis�s pour IZIVENTE - Projet Fusion
 * Editique Credit Amortissable Tr�sorerie (BP)
 * @author levieilfa bardouma
 */
public class FT2SCPPBPTest extends TNRSC00 {

	/**
 * Id de s�rialisation par d�faut.
 */
private static final long serialVersionUID = 1L;

@Test
public void FT2SCPPBPTestLancement() throws SeleniumException {
			
		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = Constantes.FACELIA;
		this.edition = true;
		this.miseEnGestion = false;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		
		miseAEdit();
		miseEnForce();
		/*ModificateurBouchon modificateur = new ModificateurBouchon();
		modificateur.emprunteurJeune = true;
		outil.selectionner("JEUNES", Cibles.SELECTEUR_UNIVERS_CREDIT, false);
		outil.attendre(2);
		outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
		outil.selectionner("BPI ETUDIANT IMMEDIAT", Cibles.SELECTEUR_OFFRE_CREDIT, false);
		outil.attendre(2);
		CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
		outil.viderEtSaisir("3000", Cibles.SAISIE_COUT_PROJET);
		outil.selectionner("Aucun", Cibles.SELECTEUR_REPORT_PREMIERE_MENS, false);
		outil.viderEtSaisir("3000", Cibles.SAISIE_MONTANT_DEMANDE);
		outil.viderEtSaisir("36", Cibles.SAISIE_DUREE_DEMANDE);*/
			
	}
}