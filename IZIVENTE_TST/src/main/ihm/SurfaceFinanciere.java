package main.ihm;

import annotations.BaliseXml;

public class SurfaceFinanciere {

//    <_2_2:CumlCrdCrdtImmb></_2_2:CumlCrdCrdtImmb>
	@BaliseXml(prefixe="_2_2", nom="CumlCrdCrdtImmb", obligatoire=true, libelle="Cumul crédit immobilier")
	public String cumulImmo = new String("");
	
//    <_2_2:CumlCrdCrdtConso></_2_2:CumlCrdCrdtConso>
	@BaliseXml(prefixe="_2_2", nom="CumlCrdCrdtConso", obligatoire=true, libelle="Cumul crédit conso")
	public String cumulConso = new String("");
	
//    <_2_2:CumlDmaCrdtRvlg></_2_2:CumlDmaCrdtRvlg>
	@BaliseXml(prefixe="_2_2", nom="CumlDmaCrdtRvlg", obligatoire=true, libelle="Cumul crédit DMA CR")
	public String cumulDMACR = new String("");
}
