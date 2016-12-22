package main.ihm;

import annotations.BaliseXml;

public class Charge {

//    <_3_3:Chrg>
//    <_2_2:CdTypeChrg>CA</_2_2:CdTypeChrg>
	@BaliseXml(prefixe="_2_2", nom="CdTypeChrg", obligatoire=true, listeValeur={"VA:Pension Alim", "VB:Prestation compensatoire", "VZ:Autres pensions", "CA:Loyer res principale", "CB:Loyer hors res princ", "CC:Viagé", "CZ:Autres charges", "RA:Crédit auto", "RB:Crédit permanent", "RC:Crédit divers"}, libelle="Code type de charge")
	public String codeTypeCharge = new String("CA");
	
//    <_2_2:MtChrg>100000</_2_2:MtChrg>
	@BaliseXml(prefixe="_2_2", nom="MtChrg", obligatoire=true, libelle="Montant de la charge")
	public String montantCharge = new String("100000");
	
// </_3_3:Chrg>

}
