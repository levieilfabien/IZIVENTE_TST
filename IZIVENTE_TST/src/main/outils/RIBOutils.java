package main.outils;

import java.util.HashMap;
import java.util.LinkedHashMap;

import main.constantes.Constantes;

//Option Explicit
//'Ti, d'après un algo trouvé sur le net
//'algo:
//'  Demande A
//'  Demande B
//'  Demande C
//'  D = A * 8
//'  A = Partie entière de (D / 97)
//'  A = D - (A * 97)
//'  E = B * 15
//'  B = Partie entière de (E / 97)
//'  B = 97 - (E - (B * 97))
//'  F = C * 3
//'  C = Partie entière de (F / 97)
//'  C = 97 - (F - (C * 97))
//'  G = A + B + C
//'  H = Partie entière de (G / 97)
//'  I = G - (H * 97)
//'  Si I = 0 alors I = 97
//'  Afficher I
//
//Function CalculRib(ByVal CodeBanque As Double, ByVal CodeAgence As Double, _
//                   ByVal Numero As Double)
//Dim D As Double, E As Double, F As Double, G As Double
//Dim H As Double, I As Double
//  D = CodeBanque * 8
//  CodeBanque = Int(D / 97)
//  CodeBanque = D - (CodeBanque * 97)
//  
//  E = CodeAgence * 15
//  CodeAgence = Int(E / 97)
//  CodeAgence = 97 - (E - (CodeAgence * 97))
//  
//  F = Numero * 3
//  Numero = Int(F / 97)
//  Numero = 97 - (F - (Numero * 97))
//  G = CodeBanque + CodeAgence + Numero
//  H = Int(G / 97)
//  I = G - (H * 97)
//  If I = 0 Then I = 97
//  CalculRib = I
//End Function

public class RIBOutils {
	
	public static final String BIC_CODE_BANQUE_CE = "CEPA";
	
	public static final String BIC_CODE_BANQUE_BP = "CCBP";
	
	public static final String BIC_CODE_PAYS = "FR";
	
	public static final String BIC_CODE_EMPLACEMENT = "PP";
	/**
	 * Correspond au cas : 11315 00001
	 */
	public static final String BIC_CODE_BRANCHE_CE_PACA = "131";
	//TODO valeur temporaire à remplacer
	public static final String BIC_CODE_BRANCHE_BP_PACA = "131";
	
	public static final HashMap<String, String[]> listeBIC = new LinkedHashMap<String, String[]>();
	
	static {
		listeBIC.put("10011", new String[]{"00020","PSSTFRPPCNE"});
		listeBIC.put("10107", new String[]{"00683","BREDFRPPXXX"});
		listeBIC.put("10178", new String[]{"00018","CCBPFRPPCHX"});
		listeBIC.put("10206", new String[]{"15001","AGRIFRPP802"});
		listeBIC.put("10207", new String[]{"00034","CCBPFRPPMTG"});
		listeBIC.put("10228", new String[]{"02895","LAYDFR2WXXX"});
		listeBIC.put("10268", new String[]{"02579","COURFR2TXXX"});
		listeBIC.put("10468", new String[]{"02665","RALPFR2GXXX"});
		listeBIC.put("10548", new String[]{"00062","BSAVFR2CXXX"});
		listeBIC.put("10558", new String[]{"02547","TARNFR2LXXX"});
		listeBIC.put("10638", new String[]{"00391","CCBPFRPP555"});
		listeBIC.put("10807", new String[]{"00012","CCBPFRPPDJN"});
		listeBIC.put("10907", new String[]{"00042","CCBPFRPPBDX"});
		listeBIC.put("11006", new String[]{"41600","AGRIFRPP810"});
		listeBIC.put("11206", new String[]{"20004","AGRIFRPP812"});
		listeBIC.put("11306", new String[]{"00040","AGRIFRPP813"});
		listeBIC.put("11315", new String[]{"84116","CEPAFRPP131"});
		listeBIC.put("11425", new String[]{"00900","CEPAFRPP142"});
		listeBIC.put("11706", new String[]{"00021","AGRIFRPP817"});
		listeBIC.put("11907", new String[]{"00010","CCBPFRPPCFD"});
		listeBIC.put("12006", new String[]{"00040","AGRIFRPP820"});
		listeBIC.put("12135", new String[]{"00300","CEPAFRPP213"});
		listeBIC.put("12169", new String[]{"00021","REUBRERXXXX"});
		listeBIC.put("12206", new String[]{"01900","AGRIFRPP822"});
		listeBIC.put("12225", new String[]{"20200","CEPAFRPP222"});
		listeBIC.put("12240", new String[]{"00004","AGFBFRCCXXX"});
		listeBIC.put("12406", new String[]{"00084","AGRIFRPP824"});
		listeBIC.put("12506", new String[]{"39021","AGRIFRPP825"});
		listeBIC.put("12515", new String[]{"00100","CEPAFRPP251"});
		listeBIC.put("12548", new String[]{"02998","AXABFRPPXXX"});
		listeBIC.put("12579", new String[]{"00700","BPSMFRPPXXX"});
		listeBIC.put("12619", new String[]{"00026","CGDIFRPPXXX"});
		listeBIC.put("12879", new String[]{"00001","DELUFR22XXX"});
		listeBIC.put("12906", new String[]{"00030","AGRIFRPP829"});
		listeBIC.put("12939", new String[]{"00058","BDUPFR2SXXX"});
		listeBIC.put("13106", new String[]{"00500","AGRIFRPP831"});
		listeBIC.put("13135", new String[]{"00080","CEPAFRPP313"});
		listeBIC.put("13259", new String[]{"02328","KOLBFR21XXX"});
		listeBIC.put("13306", new String[]{"00022","AGRIFRPP833"});
		listeBIC.put("13335", new String[]{"00301","CEPAFRPP333"});
		listeBIC.put("13379", new String[]{"00004","BMRZFR21XXX"});
		listeBIC.put("13485", new String[]{"00800","CEPAFRPP348"});
		listeBIC.put("13489", new String[]{"02593","BNUGFR21XXX"});
		listeBIC.put("13506", new String[]{"10000","AGRIFRPP835"});
		listeBIC.put("13507", new String[]{"00057","CCBPFRPPLIL"});
		listeBIC.put("13529", new String[]{"10000","BPELFR2YXXX"});
		listeBIC.put("13606", new String[]{"00015","AGRIFRPP836"});
		listeBIC.put("13607", new String[]{"00533","CCBPFRPPNIO"});
		listeBIC.put("13807", new String[]{"00807","CCBPFRPPNAN"});
		listeBIC.put("13825", new String[]{"00200","CEPAFRPP382"});
		listeBIC.put("13906", new String[]{"00182","AGRIFRPP839"});
		listeBIC.put("13907", new String[]{"00000","CCBPFRPPLYO"});
		listeBIC.put("14006", new String[]{"00000","AGRIGPGXXXX"});
		listeBIC.put("14265", new String[]{"00600","CEPAFRPP426"});
		listeBIC.put("14406", new String[]{"00115","AGRIFRPP844"});
		listeBIC.put("14445", new String[]{"00400","CEPAFRPP444"});
		listeBIC.put("14505", new String[]{"00002","CEPAFRPP450"});
		listeBIC.put("14506", new String[]{"00001","AGRIFRPP845"});
		listeBIC.put("14518", new String[]{"29267","FTNOFRP1XXX"});
		listeBIC.put("14559", new String[]{"00100","IIDFFR21XXX"});
		listeBIC.put("14607", new String[]{"00041","CCBPFRPPMAR"});
		listeBIC.put("14690", new String[]{"00001","MONNFR22XXX"});
		listeBIC.put("14706", new String[]{"00187","AGRIFRPP847"});
		listeBIC.put("14707", new String[]{"00020","CCBPFRPPMTZ"});
		listeBIC.put("14806", new String[]{"00009","AGRIFRPP848"});
		listeBIC.put("14878", new String[]{"00001","GROUFRP1XXX"});
		listeBIC.put("15135", new String[]{"00500","CEPAFRPP513"});
		listeBIC.put("15455", new String[]{"00500","CEPAFRPP545"});
		listeBIC.put("15589", new String[]{"33577","CMBRFR2BXXX"});
		listeBIC.put("15607", new String[]{"00025","CCBPFRPPNCE"});
		listeBIC.put("15629", new String[]{"02649","CMCIFR2AXXX"});
		listeBIC.put("16006", new String[]{"05011","AGRIFRPP860"});
		listeBIC.put("16106", new String[]{"00021","AGRIFRPP861"});
		listeBIC.put("16275", new String[]{"50000","CEPAFRPP627"});
		listeBIC.put("16485", new String[]{"00040","CEPAFRPP648"});
		listeBIC.put("16606", new String[]{"10001","AGRIFRPP866"});
		listeBIC.put("16607", new String[]{"00344","CCBPFRPPPPG"});
		listeBIC.put("16705", new String[]{"09017","CEPAFRPP670"});
		listeBIC.put("16706", new String[]{"00020","AGRIFRPP867"});
		listeBIC.put("16707", new String[]{"00036","CCBPFRPPREN"});
		listeBIC.put("16806", new String[]{"04821","AGRIFRPP868"});
		listeBIC.put("16807", new String[]{"00116","CCBPFRPPGRE"});
		listeBIC.put("16906", new String[]{"10025","AGRIFRPP869"});
		listeBIC.put("16945", new String[]{"00400","CEPAFRPP694"});
		listeBIC.put("17106", new String[]{"00038","AGRIFRPP871"});
		listeBIC.put("17149", new String[]{"40200","CCBPFRPP149"});
		listeBIC.put("17169", new String[]{"40710","CCBPFRPP169"});
		listeBIC.put("17179", new String[]{"40102","CMMMFR21XXX"});
		listeBIC.put("17206", new String[]{"00013","AGRIFRPP872"});
		listeBIC.put("17219", new String[]{"40540","CCBPFRPP219"});
		listeBIC.put("17510", new String[]{"62278","CRTAFR21XXX"});
		listeBIC.put("17515", new String[]{"00600","CEPAFRPP751"});
		listeBIC.put("17515", new String[]{"00092","Lechat2014"});
		listeBIC.put("17607", new String[]{"00001","CCBPFRPPSTR"});
		listeBIC.put("17679", new String[]{"00450","SBEXFRP1XXX"});
		listeBIC.put("17695", new String[]{"00900","CEPAFRPP769"});
		listeBIC.put("17806", new String[]{"00212","AGRIFRPP878"});
		listeBIC.put("17807", new String[]{"00814","CCBPFRPPTLS"});
		listeBIC.put("17906", new String[]{"00112","AGRIFRPP879"});
		listeBIC.put("17959", new String[]{"00022","BAMYFR22XXX"});
		listeBIC.put("18025", new String[]{"20800","CEPAFRPP802"});
		listeBIC.put("18106", new String[]{"00044","AGRIFRPP881"});
		listeBIC.put("18206", new String[]{"00250","AGRIFRPP882"});
		listeBIC.put("18306", new String[]{"00081","AGRIFRPP883"});
		listeBIC.put("18315", new String[]{"10000","CEPAFRPP831"});
		listeBIC.put("18370", new String[]{"00001","GPBAFRPPXXX"});
		listeBIC.put("18645", new String[]{"00401","CEPAFRPP864"});
		listeBIC.put("18706", new String[]{"00000","AGRIFRPP887"});
		listeBIC.put("18707", new String[]{"00022","CCBPFRPPVER"});
		listeBIC.put("18715", new String[]{"00101","CEPAFRPP871"});
		listeBIC.put("18719", new String[]{"00091","BFCOYTYTXXX"});
		listeBIC.put("18729", new String[]{"00095","BFECGPGXXXX"});
		listeBIC.put("18950", new String[]{"84076","CSCAFR21XXX"});
		listeBIC.put("19106", new String[]{"00009","AGRIFRPP891"});
		listeBIC.put("19406", new String[]{"37053","AGRIFRPP894"});
		listeBIC.put("19506", new String[]{"00011","AGRIFRPP895"});
		listeBIC.put("19525", new String[]{"00092","CEPAFRPP952"});
		listeBIC.put("19806", new String[]{"00190","AGRIMQMXXXX"});
		listeBIC.put("19906", new String[]{"00974","AGRIRERXXXX"});
		listeBIC.put("20041", new String[]{"01008","PSSTFRPPMAR"});
		listeBIC.put("20041", new String[]{"01001","PSSTFRPPBOR"});
		listeBIC.put("20041", new String[]{"00001","PSSTFRPPPAR"});
		listeBIC.put("20041", new String[]{"01007","PSSTFRPPLYO"});
		listeBIC.put("20041", new String[]{"01014","PSSTFRPPROU"});
		listeBIC.put("20041", new String[]{"01017","PSSTFRPPGRE"});
		listeBIC.put("20041", new String[]{"01011","PSSTFRPPNTE"});
		listeBIC.put("20041", new String[]{"01012","PSSTFRPPSCE"});
		listeBIC.put("20041", new String[]{"01010","PSSTFRPPNCY"});
		listeBIC.put("20041", new String[]{"01013","PSSTFRPPREN"});
		listeBIC.put("20041", new String[]{"01000","PSSTFRPPAJA"});
		listeBIC.put("20041", new String[]{"01004","PSSTFRPPDIJ"});
		listeBIC.put("20041", new String[]{"01018","PSSTFRPPBTE"});
		listeBIC.put("20041", new String[]{"01005","PSSTFRPPLIL"});
		listeBIC.put("20041", new String[]{"01006","PSSTFRPPLIM"});
		listeBIC.put("20041", new String[]{"01009","PSSTFRPPMON"});
		listeBIC.put("20041", new String[]{"01015","PSSTFRPPSTR"});
		listeBIC.put("20041", new String[]{"01016","PSSTFRPPTOU"});
		listeBIC.put("20041", new String[]{"01021","PSSTFRPPSDR"});
		listeBIC.put("20041", new String[]{"01002","PSSTFRPPCHA"});
		listeBIC.put("20041", new String[]{"01003","PSSTFRPPCLE"});
		listeBIC.put("20041", new String[]{"01020","PSSTFRPPFDF"});
		listeBIC.put("23890", new String[]{"00024","BCMAFRPPXXX"});
		listeBIC.put("28570", new String[]{"45057","CMDIFR21XXX"});
		listeBIC.put("30002", new String[]{"00550","CRLYFRPPXXX"});
		listeBIC.put("30003", new String[]{"02500","SOGEFRPP"});
		listeBIC.put("30003", new String[]{"01062","SOGEFRPPXXX"});
		listeBIC.put("30004", new String[]{"00179","BNPAFRPPXXX"});
		listeBIC.put("30056", new String[]{"00270","CCFRFRPPXXX"});
		listeBIC.put("30066", new String[]{"10337","CMCIFRPPXXX"});
		listeBIC.put("30076", new String[]{"02666","NORDFRPPXXX"});
		listeBIC.put("30077", new String[]{"04817","SMCTFR2AXXX"});
		listeBIC.put("40031", new String[]{"00001","CDCGFRPPXXX"});
		listeBIC.put("41839", new String[]{"00032","BDAFGPGXXXX"});
		listeBIC.put("42559", new String[]{"00054","CCOPFRPPXXX"});
	}
	
	/**
	 * Génère un BIC par défaut pour une banque (cas 1131500001)
	 * @param banque quel distributeur est concerné ?
	 * @return un BIC valide.
	 */
	public static String genererBIC(int banque) {
		String retour = "";
		
		switch (banque) {
			case Constantes.CAS_BP : retour = BIC_CODE_BANQUE_BP + BIC_CODE_PAYS + BIC_CODE_EMPLACEMENT + BIC_CODE_BRANCHE_BP_PACA; break;
			case Constantes.CAS_IOM : retour = BIC_CODE_BANQUE_CE + BIC_CODE_PAYS + BIC_CODE_EMPLACEMENT + BIC_CODE_BRANCHE_CE_PACA; break;
			case Constantes.CAS_CE : retour = BIC_CODE_BANQUE_CE + BIC_CODE_PAYS + BIC_CODE_EMPLACEMENT + BIC_CODE_BRANCHE_CE_PACA; break;
			case Constantes.CAS_BRED : retour = BIC_CODE_BANQUE_BP + BIC_CODE_PAYS + BIC_CODE_EMPLACEMENT + BIC_CODE_BRANCHE_BP_PACA; break;
		}
		
		return retour;
	}
	
	/**
	 * Génère un BIC par défaut pour un établissement
	 * @param etablissement l'établissement pour lequel on veux un exemple de code BIC valide
	 * @return un BIC valide.
	 */
	public static String genererBIC(String etablissement) {
		String retour = new String();
		try {
			retour = listeBIC.get(etablissement)[1];
		} catch (NullPointerException ex) {
			retour = "";
		}
		return retour;
	}
	
	/**
	 * Génère un guichet par défaut pour un établissement
	 * @param etablissement l'établissement pour lequel on veux un exemple de code guichet valide
	 * @return un BIC valide.
	 */
	public static String genererGuichet(String etablissement) {
		String retour = new String();
		try {
			retour = listeBIC.get(etablissement)[0];
		} catch (NullPointerException ex) {
			retour = "";
		}
		return retour;
	}
	
	/**
	 * Génère un IBAN complet à partir des informations bancaires.
	 * @param codeBanque le code banque
	 * @param codeAgence le code agence
	 * @param numero le numero de compte
	 * @return l'IBAN complet.
	 */
	public static String genererIbanFR76(String codeBanque, String codeAgence, String numero) {
		return "FR76" + String.format("%1$05d", Integer.parseInt(codeBanque)) + String.format("%1$05d", Integer.parseInt(codeAgence)) + String.format("%1$011d", Integer.parseInt(numero)) + calculerClefRib(codeBanque, codeAgence, numero);
	}
	
	/**
	 * Génère un IBAN complet à partir des informations bancaires.
	 * @param codeBanque le code banque
	 * @param numero le numero de compte
	 * @return l'IBAN complet.
	 */
	public static String genererIbanFR76(String codeBanque, String numero) {
		String codeAgence = genererGuichet(codeBanque);
		return "FR76" + String.format("%1$05d", Integer.parseInt(codeBanque)) + String.format("%1$05d", Integer.parseInt(codeAgence)) + String.format("%1$011d", Integer.parseInt(numero)) + calculerClefRib(codeBanque, codeAgence, numero);
	}
	
	/**
	 * Permet d'obtenir la clef rib à partir des informations bancaires sous forme de chaine.
	 * @param codeBanque le code banque (11315)
	 * @param codeAgence le code agence (00001)
	 * @param numero le numéro de compte (12345678900)
	 * @return la clef rib sous forme de chaine de caracètre.
	 */
	public static String calculerClefRib(String codeBanque, String codeAgence, String numero) {
		Integer clefRib = calculerClefRib(Double.valueOf(codeBanque), Double.valueOf(codeAgence), Double.valueOf(numero));;
		return String.format("%1$02d", clefRib);//Integer.toString(clefRib);
	}

	/**
	 * Permet d'obtenir la clef rib à partir des informations bancaires sous forme numérique
	 * @param codeBanque le code banque (11315)
	 * @param codeAgence le code agence (00001)
	 * @param numero le numéro de compte (12345678900)
	 * @return la clef rib sous forme numéro (integer)
	 */
	public static Integer calculerClefRib(Double codeBanque, Double codeAgence, Double numero) {
		Integer retour = 0;

		Double D;
		Double E;
		Double F;
		Double G;
		Double H;
		Double I;

		// Traitement sur le code banque
		D = codeBanque * 8;
		codeBanque = Math.floor(D / 97);
		codeBanque = D - (codeBanque * 97);

		// Traitement sur le code agence
		E = codeAgence * 15;
		codeAgence = Math.floor(E / 97);
		codeAgence = 97 - (E - (codeAgence * 97));

		// Traitement sur le numéro
		F = numero * 3;
		numero = Math.floor(F / 97);
		numero = 97 - (F - (numero * 97));
		G = codeBanque + codeAgence + numero;
		H = Math.floor(G / 97);
		I = G - (H * 97);
		
		// Si le calcul donne zéro la valeur par défaut est 97
		if (I == 0) {
			I = (double) 97;
		}
	
		// On renvoie un INT.
		retour = I.intValue();

		return retour;
	}
	
	public static void main(String[] arg) {
		String clefRib = RIBOutils.calculerClefRib("11315", "00001", "12345678900");
		
		System.out.println("FR76" + "11315" + "00001" + "12345678900" + clefRib);
		
		String IBAN = RIBOutils.genererIbanFR76("112", "1", "4884");
		
		System.out.println(IBAN);
	}
}
