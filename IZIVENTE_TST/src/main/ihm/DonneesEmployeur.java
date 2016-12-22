package main.ihm;

import annotations.BaliseXml;

public class DonneesEmployeur {
//	<_3_3:DonnEmplr>
//    <_3_3:DtMajDonnEmpl>20120707</_3_3:DtMajDonnEmpl>
	@BaliseXml(prefixe="_3_3", nom="DtMajDonnEmpl", obligatoire=true, libelle="Date de mise à jour")
	public String dateMaj = new String("20120707");
	
//    <_3_3:NomEmplr>LA BONNE BOUFFE</_3_3:NomEmplr>
	@BaliseXml(prefixe="_3_3", nom="NomEmplr", obligatoire=false, libelle="Nom de l'employeur")
	public String nomEmployeur = new String("LA BONNE BOUFFE");
	
//    <_3_3:NumrSiren>666777888</_3_3:NumrSiren>
	@BaliseXml(prefixe="_3_3", nom="NumrSiren", obligatoire=false, libelle="Numéro de SIREN")
	public String numeroSiren = new String("666777888");
	
//    <_3_3:CdNafRev2>5610C</_3_3:CdNafRev2>
	@BaliseXml(prefixe="_3_3", nom="CdNafRev2", obligatoire=true, libelle="Code NAF rev2")
	public String codeNAF = new String("5610C");
	
//    <_3_3:CdTypeContTrvl>1</_3_3:CdTypeContTrvl>
	@BaliseXml(prefixe="_3_3", nom="CdTypeContTrvl", obligatoire=true, enumeration=CodeContratTravail.class, libelle="Code type contrat de travail")
	public String codeTypeContratTravail = new String("1");
	
//    <_3_3:DtEmbc>20100101</_3_3:DtEmbc>
	@BaliseXml(prefixe="_3_3", nom="DtEmbc", obligatoire=false, libelle="Date d'embauche")
	public String dateEmbauche = new String("20100101");
	
//    <_3_3:DtFinContTrvl></_3_3:DtFinContTrvl>
	@BaliseXml(prefixe="_3_3", nom="DtFinContTrvl", obligatoire=false, libelle="Date de fin de contrat de travail")
	public String dateFinContrat = new String("");
// </_3_3:DonnEmplr>
}
