package main.ihm;

import annotations.BaliseXml;

@BaliseXml(prefixe="re", nom="Eqpm", balisable=false)
public class Equipement {
	
//    <_2_1:IdntClntDistr>1210009</_2_1:IdntClntDistr>
	@BaliseXml(prefixe="_2_1", nom="IdntClntDistr", obligatoire=true, libelle="Id client distributeur")
	public String idClientDistributeur = new String("1210009");
	 
//    <_2_1:ContDep>
//       <_2_1:CdTypeUsg>1</_2_1:CdTypeUsg>
//       <_2_1:NumrCpteIban>FR7614707014092010141636056</_2_1:NumrCpteIban>
//       <_2_1:CdBic>CCBPFRPPMTZ</_2_1:CdBic>
//       <_2_1:InttlCont>Compte a Vue</_2_1:InttlCont>
//       <_2_1:DtDebuEfftCont>20100101</_2_1:DtDebuEfftCont>
//       <_2_1:CdModeCompo>1</_2_1:CdModeCompo>
//       <_2_1:IndCpteDef></_2_1:IndCpteDef>
//       <_2_1:MntDecvAutr>200000</_2_1:MntDecvAutr>
//   </_2_1:ContDep>
	@BaliseXml(prefixe="_2_1", nom="ContDep", obligatoire=true, complexe=true, libelle="Compte dépôt")
	public CompteDepot compteDepot = new CompteDepot();
	
	public Equipement() {
		super();
	}
	
	public Equipement(String idClient, int distributeur) {
		super();
		idClientDistributeur = idClient;
		compteDepot = new CompteDepot(idClient, distributeur);
	}
}
