package main.ihm;

import annotations.BaliseXml;

public class DonneesEmployeurIndividuel {
	@BaliseXml(prefixe="_3_3", nom="DtMajDonnEmpl", obligatoire=false, libelle="Date de mise à jour des données employeurs")
	public String dtMajDonnEmpl = new String("20140101");
	
//  <_3_3:NumrSiren>666777888</_3_3:NumrSiren>
	@BaliseXml(prefixe="_3_3", nom="NumrSiren", obligatoire=false, libelle="Numéro de SIREN")
	public String numeroSiren = new String("666777888");
	
//  <_3_3:CdNafRev2>5610C</_3_3:CdNafRev2>
	@BaliseXml(prefixe="_3_3", nom="CdNafRev2", obligatoire=true, libelle="Code NAF rev2")
	public String codeNAF = new String("5610C");
	
	@BaliseXml(prefixe="_3_3", nom="FndsComm", obligatoire=false, listeValeur={":Néant", "O:Oui","N:Non"}, libelle="Indicateur propriétaire du fond de commerce")
	public String indicateurFondCommerce = new String("");
	
	@BaliseXml(prefixe="_3_3", nom="MrsComm", obligatoire=false, listeValeur={":Néant", "O:Oui","N:Non"}, libelle="Indicateur propriétaire des murs")
	public String indicateurProprietaireMur = new String("");
	
	@BaliseXml(prefixe="_3_3", nom="NbSalr", obligatoire=false, libelle="Nombre de salariés")
	public String nombreSalarie = new String("");
	//TODO le reste
	@BaliseXml(prefixe="_3_3", nom="DtCrtnEntrp", obligatoire=false, libelle="Date de création de l'entreprise")
	public String dateCreationEntreprise = new String("");
	
	@BaliseXml(prefixe="_3_3", nom="CdCatgJurd", obligatoire=false, libelle="Code catégorie juridique")
	public String codeCategorieJuridique = new String("");
	
	@BaliseXml(prefixe="_3_3", nom="CdStttEntrpr", obligatoire=false, listeValeur={":Néant", "1:Gérant","2:Associé","3:Salarié","9:Autre"}, libelle="Code situation dans l'entreprise")
	public String codeSituationEntreprise = new String("");
}
