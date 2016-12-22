package main.ihm;

import annotations.BaliseXml;

@BaliseXml(prefixe="_3_3", nom="SignEntrIndv", balisable=false)
public class SignalementEntrepreneurIndividuel {

	// <_3_3:DonnEmplr>
	@BaliseXml(prefixe="_3_3", nom="DonnEmplr", obligatoire=true, complexe=true, libelle="Données entrepreneur individuel")
	public DonneesEmployeurIndividuel donneesEmployeur = new DonneesEmployeurIndividuel();
}
