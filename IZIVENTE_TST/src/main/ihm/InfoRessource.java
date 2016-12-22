package main.ihm;

import annotations.BaliseXml;

@BaliseXml(prefixe="_3_3", nom="Ress", balisable=false)
public class InfoRessource {
//  <_3_3:Ress>
//  <_2_2:MtRevnPrnc>500000</_2_2:MtRevnPrnc>
	@BaliseXml(prefixe="_2_2", nom="MtRevnPrnc", obligatoire=true, libelle="Montant du revenu principal")
	public String montantRevenuPrincipal = new String("500000");
	
//  <_2_2:NbMoisRevn>12</_2_2:NbMoisRevn>
	@BaliseXml(prefixe="_2_2", nom="NbMoisRevn", obligatoire=true, libelle="Nombre de mois de revenu")
	public String nbMoisRevenu = new String("12");
	
//  <_2_2:MtRevnDivr></_2_2:MtRevnDivr>
	@BaliseXml(prefixe="_2_2", nom="MtRevnDivr", obligatoire=false, libelle="Somme des revenus divers")
	public String sommeRevenuDivers = new String("");
//</_3_3:Ress>

}
