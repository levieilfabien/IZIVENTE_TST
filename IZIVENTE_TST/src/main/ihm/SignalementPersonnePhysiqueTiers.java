package main.ihm;

import java.util.LinkedList;
import java.util.List;

import outils.XMLOutils;
import annotations.BaliseXml;

public class SignalementPersonnePhysiqueTiers {
	@BaliseXml(prefixe="re", nom="NomPatr", obligatoire=true, libelle="Nom Patronimique")
	public String nomPatronimique = new String("Nom Patronimique");
	
	@BaliseXml(prefixe="re", nom="NomMart", obligatoire=false, libelle="Nom marital")
	public String nomMarital = new String("Nom maritral");

	@BaliseXml(prefixe="re", nom="Prnm", obligatoire=true, libelle="Prénom")
	public String prenom = new String("Prénom");
	
	@BaliseXml(prefixe="re", nom="CdCivl", obligatoire=true, enumeration=Civilite.class, libelle="Code civilité")
	public String codeCivilite = new String("X");
	
//  <_3_3:DtNais>19740202</_3_3:DtNais>
	@BaliseXml(prefixe="re", nom="DtNais", obligatoire=true, libelle="Date de naissance")
	public String dateNaissance = new String("19841010");
	
	@BaliseXml(prefixe="re", nom="LieuNais", obligatoire=true, libelle="Commune ou pays de naissance")
	public String lieuNaissance = new String("Paris");
	

	@BaliseXml(prefixe="re", nom="AdrsPers", obligatoire=false, multiple=true, complexe=true, libelle="Adresse", contenu=Adresse.class)
	public List<Adresse> adresses = new LinkedList<Adresse>();
	

	@BaliseXml(prefixe="re", nom="MdiaPers", obligatoire=false, multiple=true, complexe=true, libelle="Média", contenu=Media.class)
	public List<Media> medias = new LinkedList<Media>();
	
	public String toString() {
		String retour = XMLOutils.toXml(this);
		return retour;
	}
}
