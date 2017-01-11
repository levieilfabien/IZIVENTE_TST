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
		
		miseAEdit();
		miseEnForce();
		/*
		ModificateurBouchon modificateur = new ModificateurBouchon();
		modificateur.emprunteurJeune = true;
		
		outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT, true, true);
		outil.selectionner("JEUNES", Cibles.SELECTEUR_UNIVERS_CREDIT, false);
		outil.attendre(2);
		outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
		outil.selectionner("BPI ETUDIANT IMMEDIAT", Cibles.SELECTEUR_OFFRE_CREDIT, false);
		outil.attendre(2);
		CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
		//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
		outil.selectionner("AUTRE ETUDE", Cibles.SELECTEUR_OBJET_FINANCE);
		outil.viderEtSaisir("3000", Cibles.SAISIE_COUT_PROJET);
		outil.selectionner("Aucun", Cibles.SELECTEUR_REPORT_PREMIERE_MENS, false);
		outil.viderEtSaisir("3000", Cibles.SAISIE_MONTANT_DEMANDE);
		outil.viderEtSaisir("36", Cibles.SAISIE_DUREE_DEMANDE);
	*/
	}
}