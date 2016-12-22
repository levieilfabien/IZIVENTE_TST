package main.ihm;

import java.util.LinkedList;
import java.util.List;

import outils.XMLOutils;
import annotations.BaliseXml;

@BaliseXml(prefixe="re", nom="ListPersPhys", entete="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header/><soapenv:Body><_2:rechercherPersonnePhysiqueResponse xmlns:_2=\"http://www.caisse-epargne.fr/wsdl/vcc/personnephysique-2\" xmlns:_2_2=\"http://www.bpce.fr/xsd/vcc/vcc-2\" xmlns:re=\"http://www.bpce.fr/xsd/vcc/personnephysique-2/response\">", enqueue="</_2:rechercherPersonnePhysiqueResponse></soapenv:Body></soapenv:Envelope>")
public class ListePersonnePhysique {

	public String xsdCE = "caisse-epargne";
	public String xsdBP = "banquepopulaire";
	
	public static final int CAS_CE = 1;
	public static final int CAS_IOM = 2;
	public static final int CAS_BP = 3;
	public static final int CAS_BRED = 4;
	
	public int distributeur = CAS_CE;
	
	@BaliseXml(prefixe="re", nom="PersPhys", obligatoire=true, multiple=true, complexe=true, libelle="Personne physique", contenu=PersonnePhysiqueTiers.class)
	public List<PersonnePhysiqueTiers> personnesPhysiques = new LinkedList<PersonnePhysiqueTiers>();
	
	
	
	public ListePersonnePhysique(String identifiant, int distributeur) {
		super();
		personnesPhysiques.add(new PersonnePhysiqueTiers());
	}
	
	public ListePersonnePhysique(PersonnePhysiqueTiers... personnes) {
		super();
		for(PersonnePhysiqueTiers personne : personnes) {
			personnesPhysiques.add(personne);
		}
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
