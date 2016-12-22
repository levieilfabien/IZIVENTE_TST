package main.ihm;

import annotations.BaliseXml;

@BaliseXml(prefixe="_3_3", nom="SignlPersPhys", balisable=false)
public class SignalementPersonnePhysique {
	
	@BaliseXml(prefixe="_3_3", nom="NomPatr", obligatoire=true, libelle="Nom Patronimique")
	public String nomPatronimique = new String("Nom Patronimique");
	
	@BaliseXml(prefixe="_3_3", nom="NomMart", obligatoire=false, libelle="Nom marital")
	public String nomMarital = new String("Nom maritral");

//	 <_3_3:Prnm>JEAN</_3_3:Prnm>
	@BaliseXml(prefixe="_3_3", nom="Prnm", obligatoire=true, libelle="Prénom")
	public String prenom = new String("Prénom");
	
//  <_3_3:CdCivl>3</_3_3:CdCivl>
	@BaliseXml(prefixe="_3_3", nom="CdCivl", obligatoire=true, enumeration=Civilite.class, libelle="Code civilité")
	public String codeCivilite = new String("X");
	
//  <_3_3:CdSexe>M</_3_3:CdSexe>
	@BaliseXml(prefixe="_3_3", nom="CdSexe", obligatoire=true, listeValeur={"M:Homme", "F:Femme", "X:Indeterminé"}, libelle="Code Sexe")
	public String sexe = new String("X");
	
//  <_3_3:DtNais>19740202</_3_3:DtNais>
	@BaliseXml(prefixe="_3_3", nom="DtNais", obligatoire=true, libelle="Date de naissance")
	public String dateNaissance = new String("19841010");
	
//     <_3_3:LieuNais>PARIS</_3_3:LieuNais>
	@BaliseXml(prefixe="_3_3", nom="LieuNais", obligatoire=true, libelle="Commune ou pays de naissance")
	public String lieuNaissance = new String("Paris");
	
//     <_3_3:CdDeptNais>76</_3_3:CdDeptNais>
	@BaliseXml(prefixe="_3_3", nom="CdDeptNais", obligatoire=true, libelle="Département de naissance")
	public String departementNaissance = new String("99");
	
//     <_3_3:CdPaysNais>99000</_3_3:CdPaysNais>
	@BaliseXml(prefixe="_3_3", nom="CdPaysNais", obligatoire=false, libelle="Code INSEE du pays de naiss.")
	public String codeINSEEPaysNaissance = new String("99000");
	
//     <_3_3:CdComunNais>76540</_3_3:CdComunNais>
	@BaliseXml(prefixe="_3_3", nom="CdComunNais", obligatoire=false, libelle="Code commune de naiss.")
	public String codeCommuneNaissance = new String("75000");
	
//     <_3_3:CdPaysNatn>99000</_3_3:CdPaysNatn>
	@BaliseXml(prefixe="_3_3", nom="CdPaysNatn", obligatoire=false, libelle="Code pays de nationalité")
	public String codeINSEEPaysNationalite = new String("99000");
	
//     <_3_3:InDeces>N</_3_3:InDeces>
	@BaliseXml(prefixe="_3_3", nom="InDeces", obligatoire=true, listeValeur={"O", "N"}, libelle="Top décès")
	public String topDeces = new String("N");
	
//     <_3_3:DtDeces>00000000</_3_3:DtDeces>
	@BaliseXml(prefixe="_3_3", nom="DtDeces", obligatoire=false, libelle="Date de décès")
	public String dateDeces = new String("");
	
//     <_3_3:CdCapcJurd>07</_3_3:CdCapcJurd>
	@BaliseXml(prefixe="_3_3", nom="CdCapcJurd", obligatoire=true, enumeration=CapaciteJuridique.class, libelle="Code capacité juridique")
	public String codeCapaciteJuridique = new String("07");
	
//     <_3_3:CdPiecIdent>CNI</_3_3:CdPiecIdent>
	@BaliseXml(prefixe="_3_3", nom="CdPiecIdent", obligatoire=false, enumeration=PieceIdentite.class, libelle="Code pièce identité")
	public String pieceIdentite = new String("CNI");
	
//     <_3_3:RefrPiecIdent>000649102876</_3_3:RefrPiecIdent>
	@BaliseXml(prefixe="_3_3", nom="RefrPiecIdent", obligatoire=false, libelle="Référence pièce identité")
	public String refPieceIdentite = new String("000649102876");
	
//     <_3_3:CdResd>1</_3_3:CdResd>
	@BaliseXml(prefixe="_3_3", nom="CdResd", obligatoire=false, enumeration=CodeResidence.class, libelle="Code résidence")
	public String codeResisdence = new String("1");
	
//     <_3_3:CdSittFaml>2</_3_3:CdSittFaml>
	@BaliseXml(prefixe="_3_3", nom="CdSittFaml", obligatoire=true, enumeration=CodeSituationFamilale.class, libelle="Code situation familiale")
	public String codeSitFam = new String("2");
	
//     <_3_3:NbEnfnChrg></_3_3:NbEnfnChrg>
	@BaliseXml(prefixe="_3_3", nom="NbEnfnChrg", obligatoire=false, libelle="Nb Enfants à charge")
	public String nombreEnfant = new String("0");
	
//     <_3_3:CdRegmMatr>03</_3_3:CdRegmMatr>
	@BaliseXml(prefixe="_3_3", nom="CdRegmMatr", obligatoire=false, enumeration=CodeRegimeMatrimonial.class, libelle="Régime matrimonial")
	public String regimeMatrimonial = new String("0");
	
	// <_3_3:ListAgrgt>
	@BaliseXml(prefixe="_3_3", nom="ListAgrgt", obligatoire=false, complexe=true, libelle="Liste des agréments")
	public ListeAgrement listeAgrement = new ListeAgrement();
	
	// <_3_3:DonnEmplr>
	@BaliseXml(prefixe="_3_3", nom="DonnEmplr", obligatoire=true, complexe=true, libelle="Données sur l'employeur")
	public DonneesEmployeur donneesEmployeur = new DonneesEmployeur();

	public SignalementPersonnePhysique() {
		super();
	}
	
	public SignalementPersonnePhysique(String nom, boolean conjoint) {
		super();
		String temp = nom;
		sexe = "F";
		
		if (conjoint) {
			temp.concat(conjoint?"_conjoint":"");
			sexe = "M";
		} 
		
		nomPatronimique = "NP_".concat(temp);
		nomMarital = "NM_".concat(temp);
		prenom = "PR_".concat(temp);
	}

}

