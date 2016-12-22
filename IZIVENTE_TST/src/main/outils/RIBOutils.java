package main.outils;

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
