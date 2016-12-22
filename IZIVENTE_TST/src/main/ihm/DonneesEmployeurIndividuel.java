package main.ihm;

import annotations.BaliseXml;

public class DonneesEmployeurIndividuel {
	@BaliseXml(prefixe="_3_3", nom="DtMajDonnEmpl", obligatoire=false, libelle="Date de mise � jour des donn�es employeurs")
	public String dtMajDonnEmpl = new String("20140101");
	
//  <_3_3:NumrSiren>666777888</_3_3:NumrSiren>
	@BaliseXml(prefixe="_3_3", nom="NumrSiren", obligatoire=false, libelle="Num�ro de SIREN")
	public String numeroSiren = new String("666777888");
	
//  <_3_3:CdNafRev2>5610C</_3_3:CdNafRev2>
	@BaliseXml(prefixe="_3_3", nom="CdNafRev2", obligatoire=true, libelle="Code NAF rev2")
	public String codeNAF = new String("5610C");
	
	@BaliseXml(prefixe="_3_3", nom="FndsComm", obligatoire=false, listeValeur={":N�ant", "O:Oui","N:Non"}, libelle="Indicateur propri�taire du fond de commerce")
	public String indicateurFondCommerce = new String("");
	
	@BaliseXml(prefixe="_3_3", nom="MrsComm", obligatoire=false, listeValeur={":N�ant", "O:Oui","N:Non"}, libelle="Indicateur propri�taire des murs")
	public String indicateurProprietaireMur = new String("");
	
	@BaliseXml(prefixe="_3_3", nom="NbSalr", obligatoire=false, libelle="Nombre de salari�s")
	public String nombreSalarie = new String("");
	//TODO le reste
	@BaliseXml(prefixe="_3_3", nom="DtCrtnEntrp", obligatoire=false, libelle="Date de cr�ation de l'entreprise")
	public String dateCreationEntreprise = new String("");
	
	@BaliseXml(prefixe="_3_3", nom="CdCatgJurd", obligatoire=false, libelle="Code cat�gorie juridique")
	public String codeCategorieJuridique = new String("");
	
	@BaliseXml(prefixe="_3_3", nom="CdStttEntrpr", obligatoire=false, listeValeur={":N�ant", "1:G�rant","2:Associ�","3:Salari�","9:Autre"}, libelle="Code situation dans l'entreprise")
	public String codeSituationEntreprise = new String("");
}
