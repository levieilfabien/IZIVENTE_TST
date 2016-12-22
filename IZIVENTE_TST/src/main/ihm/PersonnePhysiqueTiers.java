package main.ihm;

import outils.XMLOutils;
import annotations.BaliseXml;

/**
 * Représente unique le contenu du bouchon "personne physique" indépendant du foyer.
 * @author levieilfa
 *
 */
public class PersonnePhysiqueTiers {
	
	public String xsdCE = "caisse-epargne";
	public String xsdBP = "banquepopulaire";
	
	public static final int CAS_CE = 1;
	public static final int CAS_IOM = 2;
	public static final int CAS_BP = 3;
	public static final int CAS_BRED = 4;
	
	public int distributeur = CAS_CE;
	
	@BaliseXml(prefixe="re", nom="IdntClntDistr", obligatoire=true, libelle="Id client distributeur")
	public String idClientDistributeur = new String("1210009");
	
	@BaliseXml(prefixe="re", nom="SignlPersPhys", obligatoire=true, libelle="Signalement personne physique unique")
	public SignalementPersonnePhysiqueTiers personnePhysiqueUnique = new SignalementPersonnePhysiqueTiers();
	

	
	public PersonnePhysiqueTiers() {
		super();
	}
	
	public PersonnePhysiqueTiers(String nom, boolean conjoint) {
		super();
		String temp = nom;
		
		if (conjoint) {
			temp.concat(conjoint?"_conjoint":"");
		} 
		
		personnePhysiqueUnique.nomPatronimique = "NP_".concat(temp);
		personnePhysiqueUnique.nomMarital = "NM_".concat(temp);
		personnePhysiqueUnique.prenom = "PR_".concat(temp);
	}

	/**
	 * Permet de contruire un bouchon de personne physique à partir d'un bouchon foyer.
	 * @param foyer le foyer contenant les informations à récupérer.
	 * @param conjoint s'intéresse t'on au conjoint ou à la première personne du foyer.
	 */
	public PersonnePhysiqueTiers(Foyer foyer, boolean conjoint) {
		super();
		
		if (conjoint) {
			idClientDistributeur = foyer.personnePhyCjt.idClientDistributeur;
			personnePhysiqueUnique.nomPatronimique = foyer.personnePhyCjt.signalement.nomPatronimique;
			personnePhysiqueUnique.nomMarital = foyer.personnePhyCjt.signalement.nomMarital;
			personnePhysiqueUnique.prenom = foyer.personnePhyCjt.signalement.prenom;
			personnePhysiqueUnique.codeCivilite = foyer.personnePhyCjt.signalement.codeCivilite;
			personnePhysiqueUnique.dateNaissance = foyer.personnePhyCjt.signalement.dateNaissance;
			personnePhysiqueUnique.lieuNaissance = foyer.personnePhyCjt.signalement.lieuNaissance;
			personnePhysiqueUnique.adresses.add(foyer.personnePhyCjt.adresses.get(0));
			personnePhysiqueUnique.medias.add(foyer.personnePhyCjt.medias.get(0));
		} else {		
			idClientDistributeur = foyer.personnePhy.idClientDistributeur;
			personnePhysiqueUnique.nomPatronimique = foyer.personnePhy.signalement.nomPatronimique;
			personnePhysiqueUnique.nomMarital = foyer.personnePhy.signalement.nomMarital;
			personnePhysiqueUnique.prenom = foyer.personnePhy.signalement.prenom;
			personnePhysiqueUnique.codeCivilite = foyer.personnePhy.signalement.codeCivilite;
			personnePhysiqueUnique.dateNaissance = foyer.personnePhy.signalement.dateNaissance;
			personnePhysiqueUnique.lieuNaissance = foyer.personnePhy.signalement.lieuNaissance;
			personnePhysiqueUnique.adresses.add(foyer.personnePhy.adresses.get(0));
			personnePhysiqueUnique.medias.add(foyer.personnePhy.medias.get(0));
		}
		
		distributeur = foyer.distributeur;
	}
	
	public String toString() {
		String retour = XMLOutils.toXml(this);
		
		if (distributeur == CAS_BP || distributeur == CAS_BRED) {
			retour = retour.replace(xsdCE, xsdBP);
		} else {
			retour = retour.replace(xsdBP, xsdCE);
		}
		
		return retour;
	}
}

