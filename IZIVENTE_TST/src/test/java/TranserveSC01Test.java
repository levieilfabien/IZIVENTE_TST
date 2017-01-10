package test.java;

import main.bean.CasEssaiIziventeBean;
import main.constantes.Constantes;

import org.junit.Test;

import exceptions.SeleniumException;

public class TranserveSC01Test {
	
	@Test
	public void lancement() throws SeleniumException {
		
		// Déclarer une instance de test IZIVENTE
		TNRSC00 generateurSimu = new TNRSC00();
		
		// Configurer le générateur :
		generateurSimu.setAlm(false);
		generateurSimu.distributeur = Constantes.CAS_CE;
		generateurSimu.edition = true;
		generateurSimu.miseEnGestion = false;
		generateurSimu.aucunCoEmp = false;
		generateurSimu.assuranceEmp = false;
		generateurSimu.tiersCoEmp = true;
		generateurSimu.typeDossier = Constantes.CREDIT_AMORT;
		
		// Lancement la simulation.
		CasEssaiIziventeBean simulationEdit = generateurSimu.lancement(null);
		
		//SOLUTION 1 : On sérialise simulationEdit
		//SOLUTION 2 : Les infos utiles de simulationEdit sont dans le fichier de données clients
		
		
		// Mettre en gestion une instance de test IZIVENTE
		generateurSimu.edition = false;
		generateurSimu.miseEnGestion = true;
		
		CasEssaiIziventeBean simulationForc = generateurSimu.lancement(simulationEdit);
		
	}
}
