package main.ihm;

import annotations.BaliseXml;

@BaliseXml(prefixe="_3_3", nom="MdiaPers", balisable=false)
public class Media {
	
//    <_3_3:MdiaPers>	
//    <_2_2:CdNatrMdiaPers>1</_2_2:CdNatrMdiaPers>
	@BaliseXml(prefixe="_2_2", nom="CdNatrMdiaPers", obligatoire=true, listeValeur={":Néant", "1:Fixe", "2:Portable", "3:Mail"}, libelle="Code nature du média")
	public String codeNatureMedia = new String("1");
	
//    <_2_2:CdNatrCorrPers>P</_2_2:CdNatrCorrPers>
	@BaliseXml(prefixe="_2_2", nom="CdNatrCorrPers", obligatoire=true, listeValeur={":Néant", "0:Privé CE", "1:Professionel CE", "3:Indeterminé CE", "P:Personnel BP", "T:Travail BP"}, libelle="Code nature de correspondance")
	public String codeNatureCorrespondance = new String("0");
	
//    <_2_2:RefrAccesMdia>0386966133</_2_2:RefrAccesMdia>
	@BaliseXml(prefixe="_2_2", nom="RefrAccesMdia", obligatoire=false, libelle="Numéro/Mail")
	public String referenceMedia = new String("0386966133");
	
//    <_2_2:DtDebuValdMdiaPers></_2_2:DtDebuValdMdiaPers>
	@BaliseXml(prefixe="_2_2", nom="DtDebuValdMdiaPers", obligatoire=false, libelle="Date début de validité")
	public String dateDebutValidite = new String("");
	
//    <_2_2:DtFinValdMdiaPers></_2_2:DtFinValdMdiaPers>
	@BaliseXml(prefixe="_2_2", nom="DtFinValdMdiaPers", obligatoire=false, libelle="Date fin de validité")
	public String dateFinValidite = new String("");
// </_3_3:MdiaPers>
	
	public Media() {
		super();
	}
}
