package main.ihm;

public enum CodeCSP {

	CSP1100(0, "1100", "Agriculteurs sur petite exploitation"),
	CSP1200(1, "1200", "Agriculteurs sur moyenne exploitation"),
	CSP1300(2, "1300", "Agriculteurs sur grande exploitation"),
	CSP2100(3, "2100", "Artisans"),
	CSP2200(4, "2200", "Commerçants et assimilés"),
	CSP2300(5, "2300", "Chefs d'entreprise de 10 salariés ou plus"),
	CSP3100(6, "3100", "Professions libérales"),
	CSP3300(7, "3300", "Cadres de la fonction publique"),
	CSP3400(8, "3400", "Professeurs, professions scientifiques"),
	CSP3500(9, "3500", "Professions de l'information, des arts et des spectacles"),
	CSP3700(10, "3700", "Cadres administratifs et commerciaux d'entreprise"),
	CSP3800(11, "3800", "Ingénieurs et cadres techniques d'entreprise"),
	CSP4200(12, "4200", "Professeurs des écoles, instituteurs et assimilés"),
	CSP4300(13, "4300", "Professions intermédiaires de la santé et  du travail social"),
	CSP4400(14, "4400", "Clergé, religieux"),
	CSP4500(15, "4500", "Professions intermédiaires administratives de la fonction publique"),
	CSP4600(16, "4600", "Professions intermédiaires administratives et commerciales des entreprises"),
	CSP4700(17, "4700", "Techniciens"),
	CSP4800(18, "4800", "ontremaîtres, agents de maîtrise"),
	CSP5200(19, "5200", "Employés civils et agents de service de la fonction publique"),
	CSP5300(20, "5300", "Policiers et militaires"),
	CSP5400(21, "5400", "Employés administratifs d'entreprise"),
	CSP5500(22, "5500", "Employés de commerce"),
	CSP5600(23, "5600", "Personnels des services directs aux particuliers"),
	CSP6200(24, "6200", "Ouvriers qualifiés de type industriel"),
	CSP6300(25, "6300", "Ouvriers qualifiés de type artisanal"),
	CSP6400(26, "6400", "Chauffeurs"),
	CSP6500(27, "6500", "Ouvriers qualifiés de la manutention, du magasinage et du transport"),
	CSP6700(28, "6700", "Ouvriers non qualifiés de type industriel"),
	CSP6800(29, "6800", "Ouvriers non qualifiés de type artisanal"),
	CSP6900(30, "6900", "Ouvriers agricoles"),
	CSP7100(31, "7100", "Anciens agriculteurs exploitants"),
	CSP7200(32, "7200", "Anciens artisans, commerçants, chefs d'entreprise"),
	CSP7400(33, "7400", "Anciens cadres"),
	CSP7500(34, "7500", "Anciennes professions intermédiaires"),
	CSP7700(35, "7700", "Anciens employés"),
	CSP7800(36, "7800", "Anciens ouvriers"),
	CSP8100(37, "8100", "Chômeurs n'ayant jamais travaillé"),
	CSP8300(38, "8300", "Militaires du contingent"),
	CSP8400(39, "8400", "Elèves, étudiants"),
	CSP8500(40, "8500", "Personnes diverses sans activité  professionnelle de moins de 60 ans (sauf retraités)"),
	CSP8600(41, "8600", "Personnes diverses sans activité professionnelle de 60 ans et plus (sauf retraités)"),
	CSP9900(42, "9900", "Inconnu");

	/**
	 * L'index unique pour la valeur dans l'énumération.
	 */
	private int index = -1;
	/**
	 * Le code unique pour la valeur de l'énum à utiliser lors de la construction du XML.
	 */
	private String code = "";
	/**
	 * Le libellé unique de la valeur de l'énum à utiliser lors de l'affichage dans l'interface.
	 */
	private String libelle = "";
	
	/**
	 * Le constructeur par défaut.
	 * @param index l'index unique
	 * @param code le code à envoyer
	 * @param libelle le libellé à afficher
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
