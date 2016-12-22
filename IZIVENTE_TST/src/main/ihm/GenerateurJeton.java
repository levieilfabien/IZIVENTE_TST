package main.ihm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.stream.XMLStreamException;

import org.apache.xmlbeans.XmlObject;

import outils.XMLOutils;
import exceptions.SeleniumException;

public class GenerateurJeton {
	
	
	public static void readXML(String xml) throws SeleniumException, XMLStreamException {
		XmlObject trueXml = XMLOutils.obtenirXml(xml);
		
		XmlObject[] extractionPersonnes = XMLOutils.obtenirElementsXml(trueXml, "fluxreroutage#ListPart#PersPhys", null, true);
		XmlObject[] extractionContextes = XMLOutils.obtenirElementsXml(trueXml, "fluxreroutage#ListCtx#Ctx", null, true);
		XmlObject[] extractionProtocole = XMLOutils.obtenirElementsXml(trueXml, "fluxreroutage#LProtocoleTech", null, true);
		
		int i = 0;

		
		JFrame ihm = presenterJeton(extractionPersonnes, extractionContextes, extractionProtocole);
		
		ihm.setVisible(true);
		
		//XMLStreamReader temp = trueXml.newXMLStreamReader();
		
//		while (temp.hasNext()) {
//			i = temp.next();
//			
//			if (temp.getEventType() == temp.START_ELEMENT || temp.getEventType() == temp.ENTITY_REFERENCE) {
//				System.out.println(temp.getLocalName());
//			}
//			//temp.getText()
//		}
	}
	
	public static JFrame presenterJeton(XmlObject[] personnes, XmlObject[] contextes, XmlObject[] protocole) {
		JFrame retour = new JFrame("Edition du jeton");
		retour.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		retour.getContentPane().setLayout(new BorderLayout());
		JPanel personnes_pan = new JPanel();
		JPanel contextes_pan = new JPanel();
		personnes_pan.setLayout(new GridLayout(4,1));
		contextes_pan.setLayout(new GridLayout(0,4));
		retour.add(personnes_pan, BorderLayout.NORTH);
		retour.add(contextes_pan, BorderLayout.CENTER);
		int compteur = 1;
		
		for(XmlObject personne : personnes) {
			personnes_pan.add(new JLabel("Personne" + compteur + " : "));
			personnes_pan.add(new JTextField(XMLOutils.contenuTexte(personne, "IdntClntDistr")));
			personnes_pan.add(new JLabel("Rôle : "));
			personnes_pan.add(new JTextField(XMLOutils.contenuTexte(personne, "CdRole")));
			compteur++;
		}
		
		compteur = 1;
		
		for(XmlObject contexte : contextes) {
			contextes_pan.add(new JLabel("Contexte" + compteur + " : "));
			contextes_pan.add(new JTextField(XMLOutils.contenuTexte(contexte, "Cd"))); //XMLOutils.obtenirElementsXml(contexte,"Cd", null, false)[0].xmlText()));
			contextes_pan.add(new JLabel("Valeur :"));
			contextes_pan.add(new JTextField(XMLOutils.contenuTexte(contexte,"Val")));
			compteur++;
		}
		
		retour.pack();
		
		return retour;
	}
	
	public static void main(String[] argv) throws SeleniumException, XMLStreamException {
		
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><fluxreroutage><ListPart><PersPhys><IdntClntDistr>LRAL7BPE1</IdntClntDistr><CdRole>E</CdRole></PersPhys></ListPart><ListCtx><Ctx><Cd>CD_ETAB</Cd><Val>002</Val></Ctx><Ctx><Cd>CD_AGENCE_OP</Cd><Val>00001</Val></Ctx><Ctx><Cd>CD_AGENCE_DOM</Cd><Val>00001</Val></Ctx><Ctx><Cd>CD_AGENT_OP</Cd><Val>0092139</Val></Ctx><Ctx><Cd>CD_FCN_AGENT_OP</Cd><Val>381508</Val></Ctx><Ctx><Cd>CD_CANAL_DISTR</Cd><Val>AGEN</Val></Ctx><Ctx><Cd>CD_UNIV_PRD</Cd><Val>TRESORERIE</Val></Ctx><Ctx><Cd>CD_OFFRE</Cd><Val></Val></Ctx><Ctx><Cd>CD_BIN</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_CARTE</Cd><Val></Val></Ctx><Ctx><Cd>CPTE_SUPP_COMPO</Cd><Val></Val></Ctx><Ctx><Cd>NIV_DLG</Cd><Val>9</Val></Ctx><Ctx><Cd>CODE_TX</Cd><Val>izv</Val></Ctx><Ctx><Cd>MODE_VENTE</Cd><Val></Val></Ctx><Ctx><Cd>MODE_EDITIQUE</Cd><Val>DISTRIBUTEUR</Val></Ctx><Ctx><Cd>VENTE_COUPLEE</Cd><Val>O</Val></Ctx><Ctx><Cd>PROFIL</Cd><Val>izv.octroi</Val></Ctx></ListCtx><ProtocoleTech><header><version>2</version><messageId></messageId><timestamp>22/05/2012</timestamp><language>FR</language><country>FR</country><otherElements><name>name1</name><value>value1</value></otherElements><otherElements><name>name2</name><value>value2</value></otherElements></header><context><company>BP</company><application>EQX</application><channel>Intranet</channel><bank>038</bank><agency></agency><goal></goal><userId></userId></context><actors><company>NFI</company><application>v45</application><versionAppli>1.0</versionAppli><channel>INTRANET</channel><bank>All</bank><agency>All</agency><goal></goal><userId>12345678</userId></actors></ProtocoleTech></fluxreroutage>";
	
		GenerateurJeton.readXML(xml);
	}
}
