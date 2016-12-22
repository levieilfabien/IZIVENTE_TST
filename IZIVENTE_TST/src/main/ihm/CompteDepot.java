package main.ihm;

import main.constantes.Constantes;
import main.outils.RIBOutils;
import annotations.BaliseXml;

public class CompteDepot {

	//  <_2_1:CdTypeUsg>1</_2_1:CdTypeUsg>
	@BaliseXml(prefixe="_2_1", nom="CdTypeUsg", obligatoire=true, libelle="Type d'usage")
	public String codeTypeUsage = new String("1");
	
//  <_2_1:NumrCpteIban>FR7614707014092010141636056</_2_1:NumrCpteIban>
	@BaliseXml(prefixe="_2_1", nom="NumrCpteIban", obligatoire=true, libelle="Numéro de compte IBAN")
	public String numeroCompteIBAN = new String("FR7614707014092010141636056");
	
//  <_2_1:CdBic>CCBPFRPPMTZ</_2_1:CdBic>
	@BaliseXml(prefixe="_2_1", nom="CdBic", obligatoire=true, libelle="Code BIC")
	public String codeBIC = new String("CCBPFRPPMTZ");
	
//  <_2_1:InttlCont>Compte a Vue</_2_1:InttlCont>
	@BaliseXml(prefixe="_2_1", nom="InttlCont", obligatoire=false, libelle="Intitulé du compte")
	public String intituleCompte = new String("Compte a Vue");
	
//  <_2_1:DtDebuEfftCont>20100101</_2_1:DtDebuEfftCont>
	@BaliseXml(prefixe="_2_1", nom="DtDebuEfftCont", obligatoire=true, libelle="Date début effet du compte")
	public String dateDebutEffetCompte = new String("20100101");
	
//  <_2_1:CdModeCompo>1</_2_1:CdModeCompo>
	@BaliseXml(prefixe="_2_1", nom="CdModeCompo", obligatoire=false, listeValeur={"1:Compte simple" , "2:Compte joint entre époux", "3:Compte joint entre tiers"}, libelle="Code mode compo")
	public String codeModeCompo = new String("1");
	
//  <_2_1:IndCpteDef></_2_1:IndCpteDef>
	@BaliseXml(prefixe="_2_1", nom="IndCpteDef", obligatoire=false, listeValeur={"O:Par défaut" , "N:Non"}, libelle="Indice compte defaut")
	public String indiceCompteDef = new String("N");
	
//  <_2_1:MntDecvAutr>200000</_2_1:MntDecvAutr>
	@BaliseXml(prefixe="_2_1", nom="MntDecvAutr", obligatoire=false, libelle="Montant decouvert autre")
	public String montantDecouvert = new String("200000");
	
	/**
	 * Constructeur du compte de dépôt à partir du client.
	 * @param idClient le client.
	 */
	public CompteDepot(String idClient) {
		// Comme cela n'a pas d'importance réelle, on choisit un BIC/IBAN CE.
		numeroCompteIBAN = RIBOutils.genererIbanFR76("11315", "00001", idClient);
		codeBIC = RIBOutils.genererBIC(Constantes.CAS_CE);
		//codeBIC = "CEPAFRPP131";
	}

	/**
	 * Constructeur par défaut.
	 */
	public CompteDepot() {

	}
}
