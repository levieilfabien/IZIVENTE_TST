package main.ihm;

public enum CodeCSP {

	CSP1100(0, "1100", "Agriculteurs sur petite exploitation"),
	CSP1200(1, "1200", "Agriculteurs sur moyenne exploitation"),
	CSP1300(2, "1300", "Agriculteurs sur grande exploitation"),
	CSP2100(3, "2100", "Artisans"),
	CSP2200(4, "2200", "Commer�ants et assimil�s"),
	CSP2300(5, "2300", "Chefs d'entreprise de 10 salari�s ou plus"),
	CSP3100(6, "3100", "Professions lib�rales"),
	CSP3300(7, "3300", "Cadres de la fonction publique"),
	CSP3400(8, "3400", "Professeurs, professions scientifiques"),
	CSP3500(9, "3500", "Professions de l'information, des arts et des spectacles"),
	CSP3700(10, "3700", "Cadres administratifs et commerciaux d'entreprise"),
	CSP3800(11, "3800", "Ing�nieurs et cadres techniques d'entreprise"),
	CSP4200(12, "4200", "Professeurs des �coles, instituteurs et assimil�s"),
	CSP4300(13, "4300", "Professions interm�diaires de la sant� et  du travail social"),
	CSP4400(14, "4400", "Clerg�, religieux"),
	CSP4500(15, "4500", "Professions interm�diaires administratives de la fonction publique"),
	CSP4600(16, "4600", "Professions interm�diaires administratives et commerciales des entreprises"),
	CSP4700(17, "4700", "Techniciens"),
	CSP4800(18, "4800", "ontrema�tres, agents de ma�trise"),
	CSP5200(19, "5200", "Employ�s civils et agents de service de la fonction publique"),
	CSP5300(20, "5300", "Policiers et militaires"),
	CSP5400(21, "5400", "Employ�s administratifs d'entreprise"),
	CSP5500(22, "5500", "Employ�s de commerce"),
	CSP5600(23, "5600", "Personnels des services directs aux particuliers"),
	CSP6200(24, "6200", "Ouvriers qualifi�s de type industriel"),
	CSP6300(25, "6300", "Ouvriers qualifi�s de type artisanal"),
	CSP6400(26, "6400", "Chauffeurs"),
	CSP6500(27, "6500", "Ouvriers qualifi�s de la manutention, du magasinage et du transport"),
	CSP6700(28, "6700", "Ouvriers non qualifi�s de type industriel"),
	CSP6800(29, "6800", "Ouvriers non qualifi�s de type artisanal"),
	CSP6900(30, "6900", "Ouvriers agricoles"),
	CSP7100(31, "7100", "Anciens agriculteurs exploitants"),
	CSP7200(32, "7200", "Anciens artisans, commer�ants, chefs d'entreprise"),
	CSP7400(33, "7400", "Anciens cadres"),
	CSP7500(34, "7500", "Anciennes professions interm�diaires"),
	CSP7700(35, "7700", "Anciens employ�s"),
	CSP7800(36, "7800", "Anciens ouvriers"),
	CSP8100(37, "8100", "Ch�meurs n'ayant jamais travaill�"),
	CSP8300(38, "8300", "Militaires du contingent"),
	CSP8400(39, "8400", "El�ves, �tudiants"),
	CSP8500(40, "8500", "Personnes diverses sans activit�  professionnelle de moins de 60 ans (sauf retrait�s)"),
	CSP8600(41, "8600", "Personnes diverses sans activit� professionnelle de 60 ans et plus (sauf retrait�s)"),
	CSP9900(42, "9900", "Inconnu");

	/**
	 * L'index unique pour la valeur dans l'�num�ration.
	 */
	private int index = -1;
	/**
	 * Le code unique pour la valeur de l'�num � utiliser lors de la construction du XML.
	 */
	private String code = "";
	/**
	 * Le libell� unique de la valeur de l'�num � utiliser lors de l'affichage dans l'interface.
	 */
	private String libelle = "";
	
	/**
	 * Le constructeur par d�faut.
	 * @param index l'index unique
	 * @param code le code � envoyer
	 * @param libelle le libell� � afficher
	 */
	private CodeCSP(int index, String code, String libelle) {
		this.index = index;
		this.code = code;
		this.libelle = libelle;
	}
	
	/**
	 * Remplace le toString "classique".
	 */
	public String toString() {
		return code + " : " + libelle;
	}
}
