package test.java;

import main.bean.CasEssaiIziventeBean;
import main.constantes.Constantes;

import org.junit.Test;

import exceptions.SeleniumException;

public class TranserveSC01Test {
	
	@Test
	public void lancement() throws SeleniumException {
		
		// D�clarer une instance de test IZIVENTE
		TNRSC00 generateurSimu = new TNRSC00();
		
		// Configurer le g�n�rateur :
		generateurSimu.setAlm(false);
		generateurSimu.distributeur = Constantes.CAS_BP;
		generateurSimu.edition = true;
		generateurSimu.miseEnGestion = false;
		generateurSimu.aucunCoEmp = true;
		generateurSimu.assuranceEmp = false;
		generateurSimu.typeDossier = Constantes.FACELIA;
		
		// Lancement la simulation.
		CasEssaiIziventeBean simulationEdit = generateurSimu.lancement(null);
		
		//SOLUTION 1 : On s�rialise simulationEdit
		//SOLUTION 2 : Les infos utiles de simulationEdit sont dans le fichier de donn�es clients
		
		
		// Mettre en gestion une instance de test IZIVENTE
		generateurSimu.edition = false;
		generateurSimu.miseEnGestion = true;
		
		CasEssaiIziventeBean simulationForc = generateurSimu.lancement(simulationEdit);
		
	}
}
