package test.java;

import java.util.Date;
import java.util.List;

import main.bean.CasEssaiIziventeBean;
import main.constantes.Constantes;

import org.junit.Test;

import exceptions.SeleniumException;

/**
 * Sc�nario 2 des tests automatis�s pour IZIVENTE - Projet Fusion
 * Editique IZICARTE (CE)
 * @author levieilfa bardouma
 */
public class FT3SCCRCETest2 extends SC00Test {

	/**
 * Id de s�rialisation par d�faut.
 */
private static final long serialVersionUID = 1L;

@Test
public void lancement() throws SeleniumException {
		
		miseAEdit();
		miseEnForce();
		
	}
	
	public void miseAEdit() throws SeleniumException {
		// D�clarer une instance de test IZIVENTE
		TNRSC00 generateurSimu = new TNRSC00();
		
		// Configurer le g�n�rateur :
		generateurSimu.setAlm(false);
		generateurSimu.distributeur = Constantes.CAS_CE;
		generateurSimu.typeDossier = Constantes.IZICARTE;
		generateurSimu.edition = true;
		generateurSimu.miseEnGestion = false;
		generateurSimu.aucunCoEmp = true;
		generateurSimu.assuranceEmp = true;
		
		
		// Lancement la simulation.
		CasEssaiIziventeBean simulationEdit = generateurSimu.lancement();
		generateurSimu.ecritureFichierDonnees(simulationEdit, new Date());
	}
	
	public void miseEnForce() throws SeleniumException {
		// D�clarer une instance de test IZIVENTE
		TNRSC00 generateurSimu = new TNRSC00();
		// Mettre en gestion une instance de test IZIVENTE
		generateurSimu.edition = false;
		generateurSimu.miseEnGestion = true;
		
		// On r�cup�re le contenu du fichier de donn�e.
		List<String> listeInstances = generateurSimu.renvoyerContenuFichierDonnee(Constantes.ETAPE_SUIVANTE_MEG);
		
		for (String instance : listeInstances) {
			// On initialise le sc�nario avec les donn�es de l'instance
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