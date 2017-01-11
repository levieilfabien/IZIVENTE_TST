package test.java;

import main.constantes.Constantes;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * Scénario 4 des tests automatisés pour IZIVENTE - Projet Fusion
 * Editique Credit Amortissable (CE)
 * @author levieilfa bardouma
 */
public class FT4SCPPCETest extends TNRSC00 {

	/**
 * Id de sérialisation par défaut.
 */
private static final long serialVersionUID = 1L;

@Test
public void FT4SCPPCETestLancement() throws SeleniumException {
		
		this.setAlm(false);
		this.distributeur = Constantes.CAS_CE;
		this.typeDossier = Constantes.IZICARTE;
		this.edition = true;
		this.miseEnGestion = false;
		this.aucunCoEmp = true;
		this.assuranceEmp = true;
		
		miseAEdit();
		miseEnForce();
		
		/*
		 * ModificateurBouchon modificateur = new ModificateurBouchon();
		modificateur.emprunteurJeune = true;
		modificateur.coEmprunteurJeune = true;
		
		outil.attendreChargementElement(Cibles.SELECTEUR_UNIVERS_CREDIT_CE, true, true);
		outil.selectionner("TRAVAUX", Cibles.SELECTEUR_UNIVERS_CREDIT_CE, false);
		outil.attendre(5); //2 secondes ne suffisent pas
		outil.attendreChargementElement(Cibles.SELECTEUR_OFFRE_CREDIT, true, true);
		outil.selectionner("TRAVAUX ECHELONNE DIFF TOTAL", Cibles.SELECTEUR_OFFRE_CREDIT, false);
		outil.attendre(2);
		CT03.validerObjectif(outil.getDriver(), "OFFRE", true);
		//Step 2 : Sélectionner et saisir les paramètres liées au scénario (ex : CMA, différé, mensualité, etc.)
		outil.selectionner("TRAVAUX RESIDENCE PRINCIPALE", Cibles.SELECTEUR_OBJET_FINANCE);
		outil.attendre(2);
		outil.viderEtSaisir("22000", Cibles.SAISIE_COUT_PROJET);
		outil.viderEtSaisir("12", Cibles.SAISIE_DUREE_DIFFERE_PARTIEL);
		outil.viderEtSaisir("22000", Cibles.SAISIE_MONTANT_DEMANDE);
		outil.viderEtSaisir("60", Cibles.SAISIE_DUREE_DEMANDE);
		 */
	}
}