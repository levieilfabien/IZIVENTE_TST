package main.ihm;

import annotations.BaliseXml;

@BaliseXml(prefixe="_3_3", nom="AdrsPers", balisable=false)
public class Adresse {
	
//    <_3_3:AdrsPers>
//    <_2_2:CdTypeAdrs>1</_2_2:CdTypeAdrs>
	@BaliseXml(prefixe="_2_2", nom="CdTypeAdrs", obligatoire=true, listeValeur={"1:DOM", "2:CORR", "6:EMP"}, libelle="Code type d'adresse")
	public String codeTypeAdresse = new String("1");
	
//    <_2_2:Lign1AdrsAfnr>CKTRE-CENT-KRANTE-SEPT UN</_2_2:Lign1AdrsAfnr>
	@BaliseXml(prefixe="_2_2", nom="Lign1AdrsAfnr", obligatoire=true, libelle="Ligne adresse 1")
	public String ligneAdresse1 = new String("CKTRE-CENT-KRANTE-SEPT UN");
	
//    <_2_2:Lign2AdrsAfnr></_2_2:Lign2AdrsAfnr>
	@BaliseXml(prefixe="_2_2", nom="Lign2AdrsAfnr", obligatoire=false, libelle="Ligne adresse 2")
	public String ligneAdresse2 = new String("");
	
//    <_2_2:Lign3AdrsAfnr>RESIDENCE DU PALAIS</_2_2:Lign3AdrsAfnr>
	@BaliseXml(prefixe="_2_2", nom="Lign3AdrsAfnr", obligatoire=false, libelle="Ligne adresse 3")
	public String ligneAdresse3 = new String("RESIDENCE DU PALAIS");
	
//    <_2_2:Lign4AdrsAfnr>18 RUE JULES FERRY</_2_2:Lign4AdrsAfnr>
	@BaliseXml(prefixe="_2_2", nom="Lign4AdrsAfnr", obligatoire=true, libelle="Ligne adresse 4")
	public String ligneAdresse4 = new String("18 RUE JULES FERRY");
	
//    <_2_2:Lign5AdrsAfnr></_2_2:Lign5AdrsAfnr>
	@BaliseXml(prefixe="_2_2", nom="Lign5AdrsAfnr", obligatoire=false, libelle="Ligne adresse 5")
	public String ligneAdresse5 = new String("");
	
//    <_2_2:Lign6AdrsAfnr>76000 LE HAVRE</_2_2:Lign6AdrsAfnr>
	@BaliseXml(prefixe="_2_2", nom="Lign6AdrsAfnr", obligatoire=true, libelle="Ligne adresse 6")
	public String ligneAdresse6 = new String("76000 LE HAVRE");
	
//    <_2_2:CdPaysInsee>99000</_2_2:CdPaysInsee>
	@BaliseXml(prefixe="_2_2", nom="CdPaysInsee", obligatoire=true, libelle="Code INSEE du pays")
	public String codeINSEEPays = new String("99000");
	
//    <_2_2:CdRetrPtt></_2_2:CdRetrPtt>
	@BaliseXml(prefixe="_2_2", nom="CdRetrPtt", obligatoire=false, listeValeur={"O:NPAI", "1:Refuse", "2:Homonyne", "3:Non réclamé", "4:Adresse fausse", "5:Voie inconnue", "X:Indeterminé"}, libelle="Code retour du courrier")
	public String codeRetourCourrier = new String("");
	
//    <_2_2:CdInseeComun>76351</_2_2:CdInseeComun>
	@BaliseXml(prefixe="_2_2", nom="CdInseeComun", obligatoire=false, libelle="Code INSEE de la commune")
	public String codeINSEECommune = new String("76351");
// </_3_3:AdrsPers>

	public Adresse() {
		super();
	}
}
