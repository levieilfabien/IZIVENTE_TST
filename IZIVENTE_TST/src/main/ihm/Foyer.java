package main.ihm;

import java.util.LinkedList;
import java.util.List;

import outils.XMLOutils;
import annotations.BaliseXml;

@BaliseXml(prefixe="re", nom="Foyer", entete="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header/><soapenv:Body><_1:obtenirFoyerAvecRessourcesEtChargesResponse xmlns:_1=\"http://www.banquepopulaire.fr/wsdl/vcc/foyer-3\" xmlns:_3_3=\"http://www.bpce.fr/xsd/vcc/vcc-3\" xmlns:_2_2=\"http://www.bpce.fr/xsd/vcc/vcc-2\" xmlns:re=\"http://www.bpce.fr/xsd/vcc/foyer-3/response\">", enqueue="</_1:obtenirFoyerAvecRessourcesEtChargesResponse></soapenv:Body></soapenv:Envelope>")
public class Foyer {

//	public String xsdCE = "http://www.caisse-epargne.fr/wsdl/vcc/foyer-3";
//	public String xsdBP = "http://www.banquepopulaire.fr/wsdl/vcc/foyer-3";
	public String xsdCE = "caisse-epargne";
	public String xsdBP = "banquepopulaire";
	
	public static final int CAS_CE = 1;
	public static final int CAS_IOM = 2;
	public static final int CAS_BP = 3;
	public static final int CAS_BRED = 4;
	
	public int distributeur = CAS_CE;
	
	@BaliseXml(prefixe="_3_3", nom="PersPhys", obligatoire=true, complexe=true, libelle="Personne physique")
	public PersonnePhysique personnePhy = new PersonnePhysique();
	
	@BaliseXml(prefixe="_3_3", nom="Cnjt", obligatoire=false, complexe=true, libelle="Conjoint")
	public PersonnePhysique personnePhyCjt = new PersonnePhysique();
	
	@BaliseXml(prefixe="_3_3", nom="Chrg", obligatoire=false, multiple=true, complexe=true, libelle="Charge", contenu=Charge.class)
	public List<Charge> charges = new LinkedList<Charge>();
	
	@BaliseXml(prefixe="_3_3", nom="DtMajDonnChrg", obligatoire=false, multiple=false, complexe=false, libelle="Date maj charge")
	public String dateMajCharges = new String("20140101");

	@BaliseXml(prefixe="_3_3", nom="SrfcFinn", obligatoire=true, multiple=false, complexe=true, libelle="Surface financière")
	public SurfaceFinanciere surfaceFinanciere = new SurfaceFinanciere();
	
	public Foyer() {
		super();
	}
	
	
//	* pour CE : identifiant sur 9 caractères et NUMERIQUES sur bouchon CE
//	* pour BP :  identifiant sur 7 caractères et NUMERIQUES sur bouchon BP
//	* pour IOM: identifiant sur 7 caractères et NUMERIQUES sur bouchon CE
//	* pour BRED: identifiant sur 8 caractères et NUMERIQUES sur bouchon BP
//
//	- 1er caractère = Equipe
//	                1 MOA/METIERS
//	                2 CSD SOGETI - PARIS
//	                3 ou 4 CDS SOGETI – PESSAC
//	                7 DSI CCO
//	- 2ème caractère = Distributeur
//	                1 CE
//	                2 BP
//	                3 BRED
//	- 3ème caractère = Type Personne
//	                1 Emprunteur
//	                2 Conjoint Emprunteur
//	                3 Tiers
//	                4 Conjoint Tiers
//	- 4ème au 9ème caractères = n° séquentiel pour CE
//	- 4ème au 7ème caractères = n° séquentiel pour BP

	
	public Foyer(String identifiant, int distributeur) {
		super();
		personnePhy = new PersonnePhysique(identifiant, false);		
		personnePhyCjt = new PersonnePhysique((Integer.parseInt(identifiant) + 1) + "", true);
		
		// Si on est dans un cas BP, on utilise des civilités en conséquence
		if (distributeur == CAS_BP || distributeur == CAS_BRED) {
			personnePhy.signalement.codeCivilite = Civilite.MADAME_BP.getCode();
			personnePhy.signalement.sexe = "F";
			personnePhy.signalement.codeSitFam = CodeSituationFamilale.MARIE_BP.getCode();
			personnePhy.signalement.regimeMatrimonial = CodeRegimeMatrimonial.AUTRE_BP.getCode();
			personnePhyCjt.signalement.codeCivilite = Civilite.MONSIEUR_BP.getCode();
			personnePhyCjt.signalement.sexe = "M";
			personnePhyCjt.signalement.codeSitFam = CodeSituationFamilale.MARIE_BP.getCode();
			personnePhyCjt.signalement.regimeMatrimonial = CodeRegimeMatrimonial.AUTRE_BP.getCode();
		} else {
			personnePhy.signalement.codeCivilite = Civilite.MADAME_CE.getCode();
			personnePhy.signalement.sexe = "F";
			personnePhy.signalement.codeSitFam = CodeSituationFamilale.MARIE_CE.getCode();
			personnePhyCjt.signalement.codeCivilite = Civilite.MONSIEUR_CE.getCode();
			personnePhyCjt.signalement.sexe = "M";
			personnePhyCjt.signalement.codeSitFam = CodeSituationFamilale.MARIE_CE.getCode();
		}
		
		this.distributeur = distributeur;
		// On s'assure qu'il y a au moins une charge "loyer résidence principale".
		charges.add(new Charge());
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
