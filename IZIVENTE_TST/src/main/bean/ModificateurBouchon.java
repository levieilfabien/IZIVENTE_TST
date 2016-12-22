package main.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import main.ihm.Agrement;
import main.ihm.CapaciteJuridique;
import main.ihm.Foyer;
import main.ihm.ListeAgrement;

/**
 * Classe ayant pour objectif de modifier un bouchon.
 * @author levieilfa
 *
 */
public class ModificateurBouchon {

	/**
	 * Indique que l'emprunteur doit être jeune dans le foyer (18-25).
	 */
	public Boolean emprunteurJeune = false;
	
	/**
	 * Indique que le co-emprunteur doit être jeune dans le foyer (18-25).
	 */
	public Boolean coEmprunteurJeune = false;
	
	/**
	 * Indique que l'emprunteur doit avoir plus de 61 ans dans le foyer.
	 */
	public Boolean emprunteurSenior = false;
	
	/**
	 * Indique que l'emprunteur à l'agrégat CASDEN.
	 */
	public Boolean emprunteurCasden = false;
	
	
	/**
	 * Indique que l'on doit supprimer le conjoint du bouchon.
	 */
	public Boolean sansConjoint = false;
	
	/**
	 * On applique les modifications du modificateur de bouchon au foyer paramètre.
	 * @param foyer le foyer paramètre à modifier.
	 * @return le foyer modifié.
	 */
	public Foyer modifierFoyer(Foyer foyer) {
		// Formatteur de date.
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Foyer retour = foyer;
		
		// On traite le cas ou on a besoin d'un emprunteur jeune.
		if (emprunteurJeune) {
			// On retranche 19 ans à la date du jour.
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(GregorianCalendar.YEAR, calendar.get(GregorianCalendar.YEAR) - 19);
			// On affecte à la date de naissance cette date au format suivant : 19841010 
			foyer.personnePhy.signalement.dateNaissance = fmt.format(calendar.getTime());
			//- L'année d'entrée doit être supérieure ou égale à l'année de naissance du client + 17 ans.
			foyer.personnePhy.signalement.donneesEmployeur.dateEmbauche  = fmt.format(new Date());
			//- La date d'entrée doit être inférieure ou égale à la date du jour et doit être supérieure ou égale à l'année de la date de naissance + 16 ans.
			foyer.personnePhy.connaissance.informationHabitat.dateEntreeHabitat = fmt.format(new Date());
		}
		
		// On traite le cas ou on a besoin d'un co-emprunteur jeune.
		if (coEmprunteurJeune && !sansConjoint) {
			// On retranche 19 ans à la date du jour.
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(GregorianCalendar.YEAR, calendar.get(GregorianCalendar.YEAR) - 19);
			// On affecte à la date de naissance cette date au format suivant : 19841010 
			foyer.personnePhyCjt.signalement.dateNaissance = fmt.format(calendar.getTime());
			//- L'année d'entrée doit être supérieure ou égale à l'année de naissance du client + 17 ans.
			foyer.personnePhyCjt.signalement.donneesEmployeur.dateEmbauche  = fmt.format(new Date());
			//- La date d'entrée doit être inférieure ou égale à la date du jour et doit être supérieure ou égale à l'année de la date de naissance + 16 ans.
			foyer.personnePhyCjt.connaissance.informationHabitat.dateEntreeHabitat = fmt.format(new Date());
		}
		
		// On traite le cas ou on a besoin d'un emprunteur senior.
		if (emprunteurSenior) {
			// On retranche 19 ans à la date du jour.
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(GregorianCalendar.YEAR, calendar.get(GregorianCalendar.YEAR) - 62);
			// On affecte à la date de naissance cette date au format suivant : 19841010 
			foyer.personnePhy.signalement.dateNaissance = fmt.format(calendar.getTime());
			//- L'année d'entrée doit être supérieure ou égale à l'année de naissance du client + 17 ans.
			foyer.personnePhy.signalement.donneesEmployeur.dateEmbauche  = fmt.format(new Date());
			//- La date d'entrée doit être inférieure ou égale à la date du jour et doit être supérieure ou égale à l'année de la date de naissance + 16 ans.
			foyer.personnePhy.connaissance.informationHabitat.dateEntreeHabitat = fmt.format(new Date());
		}
		
		if (emprunteurCasden) {
			Agrement casden = new Agrement();
			casden.codeAgrement = "021";
			if (foyer.personnePhy.signalement.listeAgrement == null) {
				foyer.personnePhy.signalement.listeAgrement = new ListeAgrement();
			} 
			foyer.personnePhy.signalement.listeAgrement.agrements.add(casden);
		}
		
		if (sansConjoint) {
			foyer.personnePhyCjt = null;
			foyer.personnePhy.signalement.codeSitFam = "0";
		}
		
		return retour;
	}
}
