package main.ihm;

import java.util.LinkedList;
import java.util.List;

import outils.XMLOutils;
import annotations.BaliseXml;

@BaliseXml(prefixe="re", nom="ListSegmBale2", entete="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header/><soapenv:Body><_2:obtenirListeNoteBale2Response xmlns:_2=\"http://www.banquepopulaire.fr/wsdl/vcc/notationbale2-2\" xmlns:_2_1=\"http://www.bpce.fr/xsd/vcc/vcc-2\" xmlns:re=\"http://www.bpce.fr/xsd/vcc/notationbale2-2/response\">", enqueue="      </_2:obtenirListeNoteBale2Response></soapenv:Body></soapenv:Envelope>")
public class Risque {

	//public String xsdCE = "http://www.caisse-epargne.fr/wsdl/vcc/notationbale2-2";
	//public String xsdBP = "http://www.banquepopulaire.fr/wsdl/vcc/notationbale2-2";
	public String xsdCE = "caisse-epargne";
	public String xsdBP = "banquepopulaire";
	
	public static final int CAS_CE = 1;
	public static final int CAS_IOM = 2;
	public static final int CAS_BP = 3;
	public static final int CAS_BRED = 4;
	
	public int distributeur = CAS_CE;
	
	@BaliseXml(prefixe="_2_1", nom="SegmBale2", multiple=true, complexe=true, contenu=SegmentBale2.class, libelle="Risque")
	public List<SegmentBale2> listeSegmentBale2 = new LinkedList<SegmentBale2>();
	
	public Risque() {
		super();
	}
	
	public Risque(String idClient, int distributeur) {
		super();
		listeSegmentBale2.add(new SegmentBale2(idClient, distributeur));
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
