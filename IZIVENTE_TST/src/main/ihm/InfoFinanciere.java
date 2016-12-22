package main.ihm;

import annotations.BaliseXml;

public class InfoFinanciere {
	
//  <_3_3:MtEncrPrdtEpar>3500000</_3_3:MtEncrPrdtEpar>
	@BaliseXml(prefixe="_3_3", nom="MtEncrPrdtEpar", obligatoire=true, libelle="Somme des encours des produits d'épargne")
	public String montantEncours = new String("3500000");
	
//  <_3_3:NumrCartBanc></_3_3:NumrCartBanc>
	@BaliseXml(prefixe="_3_3", nom="NumrCartBanc", obligatoire=false, libelle="Liste des numéros de carte bancaire")
	public String numeroCarteBancaire = new String("");
}
