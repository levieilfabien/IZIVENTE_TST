package main.ihm;

import annotations.BaliseXml;

public class Agrement {
	
//  <_2_2:CdAgrt></_2_2:CdAgrt>
	@BaliseXml(prefixe="_2_2", nom="CdAgrt", obligatoire=false, enumeration=CodeAgrement.class, libelle="Code Agrément")
	public String codeAgrement = new String("");
	
//  <_2_2:ValAgrt></_2_2:ValAgrt>
	@BaliseXml(prefixe="_2_2", nom="ValAgrt", obligatoire=false, libelle="Valeur agrément")
	public String valeurAgrement = new String("");
}
