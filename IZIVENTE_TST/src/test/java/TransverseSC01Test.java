package test.java;

import java.util.Date;
import java.util.List;

import main.bean.CasEssaiIziventeBean;
import main.constantes.Constantes;

import org.junit.Test;

import exceptions.SeleniumException;

public class TranserveSC01Test {
	
	@Test
	public void lancement() throws SeleniumException {
		
		miseAEdit();
		//miseEnForce();
		
	}
	
	
	public void miseAEdit() throws SeleniumException {
		// Déclarer une instance de test IZIVENTE
		TNRSC00 generateurSimu = new TNRSC00();
		
		// Configurer le générateur :
		generateurSimu.setAlm(false);
		generateurSimu.distributeur = Constantes.CAS_BP;
		generateurSimu.typeDossier = Constantes.CREDIT_AMORT;
		generateurSimu.edition = true;
		generateurSimu.miseEnGestion = false;
		generateurSimu.aucunCoEmp = false;
		generateurSimu.conjointCoEmp = false;
		generateurSimu.tiersCoEmp = true;
		generateurSimu.assuranceEmp = false;
		generateurSimu.assuranceTiers = false;
		
		
		// Lancement la simulation.
		CasEssaiIziventeBean simulationEdit = generateurSimu.lancement();
		generateurSimu.ecritureFichierDonnees(simulationEdit, new Date());
	}
	
	public void miseEnForce() throws SeleniumException {
		// Déclarer une instance de test IZIVENTE
		TNRSC00 generateurSimu = new TNRSC00();
		// Mettre en gestion une instance de test IZIVENTE
		generateurSimu.edition = false;
		generateurSimu.miseEnGestion = true;
		
		// On récupère le contenu du fichier de donnée.
		List<String> listeInstances = generateurSimu.renvoyerContenuFichierDonnee(Constantes.ETAPE_SUIVANTE_MEG);
		
		for (String instance : listeInstances) {
			// On initialise le scénario avec les données de l'instance
			CasEssaiIziventeBean simulationEdit = new CasEssaiIziventeBean();
			simulationEdit = generateurSimu.initialiserScenario(instance);
			
			if (simulationEdit != null) {
				// Reprise de la simulation.
				CasEssaiIziventeBean simulationForc = generateurSimu.lancement(simulationEdit);
				generateurSimu.ecritureFichierDonnees(simulationForc, new Date());
			}
		}
	}
}
