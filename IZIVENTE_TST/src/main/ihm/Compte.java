package main.ihm;

import java.util.LinkedList;
import java.util.List;

import outils.XMLOutils;
import annotations.BaliseXml;

@BaliseXml(prefixe="re", nom="ListEqpm", libelle="Compte", entete="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header/><soapenv:Body><_2:obtenirComptesListePersonnePhysiqueResponse xmlns:_2=\"http://www.banquepopulaire.fr/wsdl/vcc/compte-2\" xmlns:_2_1=\"http://www.bpce.fr/xsd/vcc/vcc-2\" xmlns:re=\"http://www.bpce.fr/xsd/vcc/compte-2/response\">", enqueue="</_2:obtenirComptesListePersonnePhysiqueResponse></soapenv:Body></soapenv:Envelope>")
public class Compte {

//	public String xsdCE = "http://www.caisse-epargne.fr/wsdl/vcc/compte-2";
//	public String xsdBP = "http://www.banquepopulaire.fr/wsdl/vcc/compte-2";
	public String xsdCE = "caisse-epargne";
	public String xsdBP = "banquepopulaire";
	
	public static final int CAS_CE = 1;
	public static final int CAS_IOM = 2;
	public static final int CAS_BP = 3;
	public static final int CAS_BRED = 4;
	
	public int distributeur = CAS_CE;
	
	@BaliseXml(prefixe="re", nom="Eqpm", multiple=true, complexe=true, contenu=Equipement.class, libelle="Equipement")
	public List<Equipement> listeEquipement = new LinkedList<Equipement>();
	
	public Compte() {
		super();
	}
	
	public Compte(String idClient, int distributeur) {
		super();
		listeEquipement.add(new Equipement(idClient));
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
