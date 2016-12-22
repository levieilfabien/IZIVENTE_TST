package main.ihm;

import annotations.BaliseXml;

@BaliseXml(prefixe="_3_3", nom="ConnPersPhys", balisable=false)
public class ConnaissancePersonnePhysique {

//	<_3_3:ConnPersPhys>
//    <_3_3:CdApparRes>0</_3_3:CdApparRes>
	@BaliseXml(prefixe="_3_3", nom="CdApparRes", obligatoire=true, enumeration=CodeAppartenanceReseau.class, libelle="Code appartenance au réseau")
	public String appartenanceReseau = new String("0");
	
//    <_3_3:CdCsp>3700</_3_3:CdCsp>
	@BaliseXml(prefixe="_3_3", nom="CdCsp", obligatoire=true, enumeration=CodeCSP.class, libelle="Code CSP")
	public String codeCSP = new String("3700");
	
//    <_3_3:LiblProf></_3_3:LiblProf>
	@BaliseXml(prefixe="_3_3", nom="LiblProf", obligatoire=false, libelle="Libelle de la profession")
	public String libelleProfession = new String("");
	
//    <_3_3:SegmFrqncClt>AREN</_3_3:SegmFrqncClt>
	@BaliseXml(prefixe="_3_3", nom="SegmFrqncClt", obligatoire=true, enumeration=CodeSegmentationClient.class, libelle="Segmentation fréquence client")
	public String segmentationFrequence = new String("AREN");
	
//    <_3_3:AncnBanc>20100101</_3_3:AncnBanc>
	@BaliseXml(prefixe="_3_3", nom="AncnBanc", obligatoire=false, libelle="Ancienneté dans la banque")
	public String ancienneteBanque = new String("20100101");
	
//    <_3_3:Habt>
//       <_2_2:TypHabt>21</_2_2:TypHabt>
//       <_2_2:DtHabt>20080101</_2_2:DtHabt>
//       <_2_2:DtDernPretImmb></_2_2:DtDernPretImmb>
//       <_2_2:InIncdPretImmb></_2_2:InIncdPretImmb>
//    </_3_3:Habt>
	@BaliseXml(prefixe="_3_3", nom="Habt", complexe=true, obligatoire=true, libelle="Informations sur l'habitat")
	public Habitat informationHabitat = new Habitat();
	
//    <_3_3:SittFinn>
//       <_3_3:MtEncrPrdtEpar>3500000</_3_3:MtEncrPrdtEpar>
//       <_3_3:NumrCartBanc></_3_3:NumrCartBanc>
//    </_3_3:SittFinn>
	@BaliseXml(prefixe="_3_3", nom="SittFinn", complexe=true, obligatoire=false, libelle="Informations sur la situation financière")
	public InfoFinanciere informationFinanciere = new InfoFinanciere();
	
//    <_3_3:DtMajDonnRess>20120707</_3_3:DtMajDonnRess>
	@BaliseXml(prefixe="_3_3", nom="DtMajDonnRess", obligatoire=false, libelle="Date de mise à jour des données ressources")
	public String majDonneesRessources = new String("20120707");
	
//    <_3_3:Ress>
//       <_2_2:MtRevnPrnc>500000</_2_2:MtRevnPrnc>
//       <_2_2:NbMoisRevn>12</_2_2:NbMoisRevn>
//       <_2_2:MtRevnDivr></_2_2:MtRevnDivr>
//    </_3_3:Ress>
	@BaliseXml(prefixe="_3_3", nom="Ress", obligatoire=false, complexe=true, libelle="Ressource de la personne")
	public InfoRessource ressourcesPersonne = new InfoRessource();
	
//    <_3_3:TypPrdCR></_3_3:TypPrdCR>
	@BaliseXml(prefixe="_3_3", nom="TypPrdCR", obligatoire=false, listeValeur={"FC:Full Crédit", "DC:Débit Crédit"}, libelle="Type produits CR")
	public String typeDeProduitCR = new String("FC");
	
//    <_3_3:NumrCrteCR></_3_3:NumrCrteCR>
	@BaliseXml(prefixe="_3_3", nom="NumrCrteCR", obligatoire=false, libelle="Numéro carte CR")
	public String numeroCarteCR = new String("");
	
//    <_3_3:NumrDossCR></_3_3:NumrDossCR>
	@BaliseXml(prefixe="_3_3", nom="NumrDossCR", obligatoire=false, libelle="Numero dossier CR")
	public String numeroDossierCR = new String("");
// </_3_3:ConnPersPhys>
	

}
