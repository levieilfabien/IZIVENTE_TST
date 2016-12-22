package main.constantes;

import beans.EnvironementDeTest;

/**
 * Enum�ration des environements possible.
 * @author EYC862
 *
 */
public enum Environements implements EnvironementDeTest {
	
	DEV("DEV"),
	RECETTE_PROJET("Recette projet"),
	RECETTE("Recette"),
	PREPILOTE("Pr�-production pilote"),
	PREPROD("Pr�-production"),
	PRODPILOTE("Pilote production"),
	PROD("Production"),
	QUALIF("Qualif"),
	ASSEMBLAGE("Assemblage");

	
	/**
	 * Libelle de l'environement.
	 */
	private final String environement;
	
	/**
	 * Constructeur priv�.
	 * @param code le code associ� a l'environement.
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
