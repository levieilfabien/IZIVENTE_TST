package main.ihm;

import main.constantes.Constantes;
import annotations.BaliseXml;

@BaliseXml(prefixe="_2_1", nom="SegmBale2", balisable=false)
public class SegmentBale2 {

//    <_2_1:SegmBale2>
//<_2_1:IdntClntDistr>1210009</_2_1:IdntClntDistr>
//       <_2_1:DtSegmRisqClnt>20120530</_2_1:DtSegmRisqClnt>
//       <_2_1:CdSegmRisqClnt></_2_1:CdSegmRisqClnt>
//       <_2_1:ValrNoteReglBale2>_001   01234   10000PRPR006</_2_1:ValrNoteReglBale2>
//    </_2_1:SegmBale2>
	
	@BaliseXml(prefixe="_2_1", nom="IdntClntDistr", obligatoire=true, libelle="Id client distributeur")
	public String idClientDistributeur = new String("1210009");
	
	@BaliseXml(prefixe="_2_1", nom="DtSegmRisqClnt", obligatoire=true, libelle="Date du risque")
	public String dateRisque = new String("20120530");
	
	@BaliseXml(prefixe="_2_1", nom="CdSegmRisqClnt", obligatoire=true, libelle="Code du risque")
	public String codeRisque = new String("");
	
	@BaliseXml(prefixe="_2_1", nom="ValrNoteReglBale2", enumeration=NoteBale.class, obligatoire=true, libelle="Valeur de la note")
	public String valeurNote = new String("");
	//_001   01234   10000PRPR006
	
	public SegmentBale2() {
		super();
	}
	
	public SegmentBale2(String idClient, int distributeur) {
		super();
		if ("".equals(valeurNote)) {
			if(distributeur == Constantes.CAS_BP || distributeur == Constantes.CAS_BRED) {
				valeurNote = NoteBale.BPPP003.getCode();
			} else {
				valeurNote = NoteBale.CEPP003.getCode();
			}
		}
		idClientDistributeur = idClient;
	}
}
