package main.ihm;

import annotations.BaliseXml;

@BaliseXml(prefixe="_3_3", nom="Habt", balisable=false)
public class Habitat {

	//  <_3_3:Habt>
	//  <_2_2:TypHabt>21</_2_2:TypHabt>
	@BaliseXml(prefixe="_2_2", nom="TypHabt", obligatoire=true, enumeration=CodeTypeHabitat.class, libelle="Type d'habitation")
	public String typeHabitation = new String("21");
	
	//  <_2_2:DtHabt>20080101</_2_2:DtHabt>
	@BaliseXml(prefixe="_2_2", nom="DtHabt", obligatoire=true, libelle="Date d'entrée dans l'habitat")
	public String dateEntreeHabitat = new String("20080101");
	
	//  <_2_2:DtDernPretImmb></_2_2:DtDernPretImmb>
	@BaliseXml(prefixe="_2_2", nom="DtDernPretImmb", obligatoire=false, libelle="Date du dernier prêt immo")
	public String dateDernierPretImmo = new String("");
	
	//  <_2_2:InIncdPretImmb></_2_2:InIncdPretImmb>
	@BaliseXml(prefixe="_2_2", nom="InIncdPretImmb", obligatoire=false, listeValeur={"O", "N"}, libelle="Indicateur d'incident sur le prêt immo")
	public String indicateurIndicentPretImmo = new String("");
	//</_3_3:Habt>
}
