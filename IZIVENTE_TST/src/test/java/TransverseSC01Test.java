package test.java;

import main.constantes.Constantes;

import org.junit.Test;

import exceptions.SeleniumException;

public class TransverseSC01Test extends TNRSC00 {
	
	/**
	 * id de sérialisation.
	 */
	private static final long serialVersionUID = -3737267445392145659L;

	@Test
	public void transverseSC01Test() throws SeleniumException {
		
		this.setAlm(false);
		this.distributeur = Constantes.CAS_BP;
		this.typeDossier = Constantes.CREDIT_AMORT;
		this.edition = true;
		this.miseEnGestion = false;
		this.aucunCoEmp = true;
		this.conjointCoEmp = false;
		this.tiersCoEmp = false;
		this.assuranceEmp = false;
		this.assuranceTiers = false;
		
		miseAEdit();
		miseEnForce();
		
	}
	
	
//	public void miseAEdit() throws SeleniumException {
//		// Déclarer une instance de test IZIVENTE
//		TNRSC00 generateurSimu = new TNRSC00();
//		
//		// Configurer le générateur :
//		generateurSimu.setAlm(false);
//		generateurSimu.distributeur = Constantes.CAS_CE;
//		generateurSimu.typeDossier = Constantes.IZICARTE;
//		generateurSimu.edition = true;
//		generateurSimu.miseEnGestion = false;
//		generateurSimu.aucunCoEmp = true;
//		generateurSimu.conjointCoEmp = false;
//		generateurSimu.tiersCoEmp = false;
//		generateurSimu.assuranceEmp = false;
//		generateurSimu.assuranceTiers = false;
//		
//		
//		// Lancement la simulation.
//		CasEssaiIziventeBean simulationEdit = generateurSimu.lancement();
//		generateurSimu.ecritureFichierDonnees(simulationEdit, new Date());
//	}
	
//	public void miseEnForce() throws SeleniumException {
//		// Déclarer une instance de test IZIVENTE
//		TNRSC00 generateurSimu = new ();
//		// Mettre en gestion une instance de test IZIVENTE
//		generateurSimu.edition = false;
//		generateurSimu.miseEnGestion = true;
//		
//		// On récupère le contenu du fichier de donnée.
//		List<String> listeInstances = generateurSimu.renvoyerContenuFichierDonnee(Constantes.ETAPE_SUIVANTE_MEG);
//		
//		for (String instance : listeInstances) {
//			// On initialise le scénario avec les données de l'instance
//			CasEssaiIziventeBean simulationEdit = new CasEssaiIziventeBean();
//			simulationEdit = generateurSimu.initialiserScenario(instance);
//			
//			if (simulationEdit != null) {
//				// Reprise de la simulation.
//				CasEssaiIziventeBean simulationForc = generateurSimu.lancement(simulationEdit);
//				generateurSimu.ecritureFichierDonnees(simulationForc, new Date());
//			}
//		}
//	}
}
