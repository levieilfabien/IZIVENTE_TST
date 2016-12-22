package main.ihm;

import outils.XMLOutils;
import annotations.BaliseXml;

@BaliseXml(prefixe="re", nom="ListCrdt", libelle="Creance", entete="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header/><soapenv:Body><_1:obtenirCreancesPersonnePhysiqueResponse xmlns:_1=\"http://www.banquepopulaire.fr/wsdl/vcc/creance-3\" xmlns:_2=\"http://www.bpce.fr/xsd/vcc/vcc-4\" xmlns:re=\"http://www.bpce.fr/xsd/vcc/creance-3/response\">", enqueue="</_1:obtenirCreancesPersonnePhysiqueResponse></soapenv:Body></soapenv:Envelope>")
public class Creance {

	
	public String xsdCE = "caisse-epargne";
	public String xsdBP = "banquepopulaire";
	
	public static final int CAS_CE = 1;
	public static final int CAS_IOM = 2;
	public static final int CAS_BP = 3;
	public static final int CAS_BRED = 4;
	
	public int distributeur = CAS_CE;
	
	
	public Creance() {
		super();
	}
	
	public Creance(String idClient, int distributeur) {
		super();
		this.distributeur = distributeur;
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
