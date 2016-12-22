package main.constantes;

import beans.EnvironementDeTest;

/**
 * Enumération des environements possible.
 * @author EYC862
 *
 */
public enum Environements implements EnvironementDeTest {
	
	DEV("DEV"),
	RECETTE_PROJET("Recette projet"),
	RECETTE("Recette"),
	PREPILOTE("Pré-production pilote"),
	PREPROD("Pré-production"),
	PRODPILOTE("Pilote production"),
	PROD("Production"),
	QUALIF("Qualif"),
	ASSEMBLAGE("Assemblage");

	
	/**
	 * Libelle de l'environement.
	 */
	private final String environement;
	
	/**
	 * Constructeur privé.
	 * @param code le code associé a l'environement.
	 */
	private Environements(String environement) {
		this.environement = environement;
	}
	
	public String getNom() {
		return this.name();
	}

	public String getCode() {
		return this.name() + ".DNS";
	}

	public String getEnvironement() {
		return environement;
	}

	@Override
	public String toString() {
		return environement;
	}

	//@Override
	public String getCodeBase() {
		return null;
	}

	//@Override
	public String getCodeLogin() {
		return null;
	}

	//@Override
	public String getCodePassword() {
		return null;
	}

	//@Override
	public String getCodeBaseLogin() {
		return null;
	}

	//@Override
	public String getCodeBasePassword() {
		return null;
	}
}
