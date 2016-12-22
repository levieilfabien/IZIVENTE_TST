package main.ihm;

import java.util.LinkedList;
import java.util.List;

import annotations.BaliseXml;

@BaliseXml(prefixe="_3_3", nom="ListAgrgt", balisable=false)
public class ListeAgrement {

	@BaliseXml(prefixe="_2_2", nom="Agrt", obligatoire=false, multiple=true, complexe=true, libelle="Agrément", contenu = Agrement.class)
	public List<Agrement> agrements = new LinkedList<Agrement>();
}
