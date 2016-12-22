package main.ihm;

import interfaces.WrapLayout;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.xmlbeans.XmlObject;

import main.constantes.Constantes;
import outils.IHMOutils;
import outils.XMLOutils;
import test.java.SC00Test;
import exceptions.SeleniumException;

public class IHMGeneration {
	

	public static void main(String[] args) {

		// Valeur par défaut des numéros séquentiels
		String numeroSequentielBP = "9999";
		String numeroSequentielCE = "999999";

		String choix = numeroSequentielBP;

		// Demande à l'utilisateur

		JFrame demande = new JFrame("Quelle Action ?");
		demande.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		demande.setLayout(new WrapLayout());
		JButton boutonCE = new JButton(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choixDistributeur(Constantes.CAS_CE);
			}
		});
		boutonCE.setText("Ecriture CE");
		JButton boutonBP = new JButton(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choixDistributeur(Constantes.CAS_BP);
			}
		});
		boutonBP.setText("Ecriture BP");
		JButton boutonBRED = new JButton(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choixDistributeur(Constantes.CAS_BRED);
			}
		});
		boutonBRED.setText("Ecriture BRED");
		JButton lectureBouchon = new JButton(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String distributeur = JOptionPane.showInputDialog("Quel bouchon désirez vous lire?\n1 : CE\n2 : BP");
				int cas =Constantes.CAS_BRED;
				if ("1".equals(distributeur)) {
					cas = Constantes.CAS_CE;
				} else if ("2".equals(distributeur)) {
					cas = Constantes.CAS_BP;
				}
				String nom = JOptionPane.showInputDialog("Quel est le nom de votre bouchon ?");
				lectureBouchon(cas, nom);
			}
		});
		lectureBouchon.setText("Lecture");
		
		demande.add(boutonCE);
		demande.add(boutonBP);
		demande.add(boutonBRED);
		demande.add(lectureBouchon);
		
		demande.pack();
		demande.setVisible(true);

	}

	/**
	 * Permet de trouver un numéro libre pour un foyer.
	 * @param cas le type de cas (CE, BP, IOM, BRED....)
	 * @return le numéro libre.
	 */
	public static String trouverNumero(int cas) {
		File repertoire = new File("");
		int longueur = 0;
		
		switch (cas) {
		case Constantes.CAS_BP : longueur = Constantes.LONGUEUR_BP; repertoire = new File(Constantes.REPERTOIRE_BP_FOYER);break;
		case Constantes.CAS_CE : longueur = Constantes.LONGUEUR_CE; repertoire = new File(Constantes.REPERTOIRE_CE_FOYER);break;
		case Constantes.CAS_IOM : longueur = Constantes.LONGUEUR_IOM; repertoire = new File(Constantes.REPERTOIRE_CE_FOYER);break;
		case Constantes.CAS_BRED : longueur = Constantes.LONGUEUR_BRED; repertoire = new File(Constantes.REPERTOIRE_BP_FOYER);break;
		}
		
		int numero = -1;
		int temp = 0;

		File[] listOfFiles = repertoire.listFiles();
		
//		if(repertoire.isDirectory()) {
//			System.out.println("Le repertoire existe.");
//		} else {
//			System.out.println("Le repertoire existe pas.");
//		}
//		System.out.println("Repertoire : " + repertoire);
//		System.out.println("Repertoire Test1 : " + new File("\\\\swriziv01\\BouchonsBP\\foyer") + " " + (new File("\\\\swriziv01\\BouchonsBP\\foyer").exists()?"OUI":"NON"));
//		System.out.println("Repertoire Test2 : " + new File("\\swriziv01\\BouchonsBP\\foyer") + " " + (new File("\\swriziv01\\BouchonsBP\\foyer").exists()?"OUI":"NON"));
//		System.out.println("Repertoire Test3 : " + new File("C:\\work") + " " + (new File("C:\\work").exists()?"OUI":"NON"));
//		System.out.println("Repertoire Test4 : " + new File("Y:\\risque") + " " + (new File("Y:\\risque").exists()?"OUI":"NON"));
		
		for (int i = 0; i < listOfFiles.length; i++) {
			boolean conforme = true;
			boolean xml = false;
			
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().toUpperCase().contains("XML")) {
					xml = true;
				} else {
					xml = false;
				}		
				if (listOfFiles[i].getName().length() == longueur) {
					try {
						if (xml) {
							temp = Integer.parseInt(listOfFiles[i].getName().split(".xml")[0]);
						} else if (listOfFiles[i].getName().toUpperCase().contains("TXT")) {
							temp = Integer.parseInt(listOfFiles[i].getName().split(".txt")[0]);
						} else {
							temp = Integer.parseInt(listOfFiles[i].getName().split("/.")[0]);
						}
					} catch (NumberFormatException ex) {
						temp = -1;
						// Si le format n'est pas numérique, on archive le bouchon pour rendre plus rapide le fonctionnement plus tard
						conforme = false;
					}
					// Si le nouveau numéro est plus grand que l'ancien, on remplace.
					if (temp >= numero) {
						numero = temp;
					}
				} else {
					// Si la longueur est incorrecte ou que ce n'est pas un fichier XML, on pourrais le considére conforme. 
					// On prend en compte que les IOM sont heberger avec les CE et que les BRED sont hebergé avec les BP
					if (cas == Constantes.CAS_BP || cas == Constantes.CAS_BRED) {
						if (listOfFiles[i].getName().length() == Constantes.LONGUEUR_BP || listOfFiles[i].getName().length() == Constantes.LONGUEUR_BRED) {
							conforme = true;
						} else {
							conforme = false;
						}
					}
					if (cas == Constantes.CAS_CE || cas == Constantes.CAS_IOM) {
						if (listOfFiles[i].getName().length() == Constantes.LONGUEUR_IOM || listOfFiles[i].getName().length() == Constantes.LONGUEUR_CE) {
							conforme = true;
						} else {
							conforme = false;
						}
					}
				}
				// Si non conforme on pourrais archiver le document.
				if(!conforme) {
//					System.out.println(listOfFiles[i].getName() + " devrais être archivé");
					boolean moving = listOfFiles[i].renameTo(new File(repertoire + "\\archives\\" + listOfFiles[i].getName()));
//				    if (moving) {
//				    	System.out.println("Moved");
//				    } else {
//				    	System.out.println("Erreur");
//				    }
				}
			}
		}
		numero = numero + 2;
		
		System.out.println("Le numéro est " + numero);	
		
		return numero + "";
	}
	
	/**
	 * Permet de trouver le foyer associé au nom
	 * @param cas le type de cas (CE, BP, IOM, BRED....)
	 * @param nom le nom du fichier si il est connu
	 * @return le foyer obtenu du fichier
	 * @throws SeleniumException en cas d'erreur.
	 */
	public static Foyer trouverFoyer(int cas, String nom) throws SeleniumException {
		File repertoire = new File("");
		Foyer retour = new Foyer("1234", cas);
		File fichier = new File("");
		
		switch (cas) {
			case Constantes.CAS_BP : repertoire = new File(Constantes.REPERTOIRE_BP_FOYER);break;
			case Constantes.CAS_CE : repertoire = new File(Constantes.REPERTOIRE_CE_FOYER);break;
			case Constantes.CAS_BRED : repertoire = new File(Constantes.REPERTOIRE_BP_FOYER);break;
		}
		
		boolean existe = false;

		File[] listOfFiles = repertoire.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
				if (nom.equals(listOfFiles[i].getName().split(".xml")[0])) {
					existe = true;
					fichier = listOfFiles[i];
					break;
				}
		}

		if (existe) {
			try {
				// Lecture brute du fichier
				String contenu = "";
				InputStream flux=new FileInputStream(fichier); 
				InputStreamReader lecture=new InputStreamReader(flux);
				BufferedReader buff=new BufferedReader(lecture);
				String ligne;
				while ((ligne=buff.readLine())!=null){
					contenu = contenu.concat(ligne);
				}
				buff.close(); 
				System.out.println(XMLOutils.obtenirXml(contenu));
				XmlObject temp = XMLOutils.obtenirElementsXml(XMLOutils.obtenirXml(contenu), "soapenv:Envelope#soapenv:Body#_1:obtenirFoyerAvecRessourcesEtChargesResponse#re:Foyer", null, true)[0];
				//#re:Foyer
				return (Foyer) XMLOutils.toObject(retour, temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * Permet de trouver le compte associé au nom
	 * @param cas le type de cas (CE, BP, IOM, BRED....)
	 * @param nom le nom du fichier si il est connu
	 * @return le compte obtenu du fichier
	 * @throws SeleniumException en cas d'erreur.
	 */
	public static Compte trouverCompte(int cas, String nom) throws SeleniumException {
		File repertoire = new File("");
		Compte retour = new Compte("1234", cas);
		File fichier = new File("");
		
		switch (cas) {
			case Constantes.CAS_BP : repertoire = new File(Constantes.REPERTOIRE_BP_COMPTE);break;
			case Constantes.CAS_CE : repertoire = new File(Constantes.REPERTOIRE_CE_COMPTE);break;
			case Constantes.CAS_BRED : repertoire = new File(Constantes.REPERTOIRE_BP_COMPTE);break;
		}
		
		boolean existe = false;

		File[] listOfFiles = repertoire.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
				if (nom.equals(listOfFiles[i].getName().split(".xml")[0])) {
					existe = true;
					fichier = listOfFiles[i];
					break;
				}
		}

		if (existe) {
			try {
				// Lecture brute du fichier
				String contenu = "";
				InputStream flux=new FileInputStream(fichier); 
				InputStreamReader lecture=new InputStreamReader(flux);
				BufferedReader buff=new BufferedReader(lecture);
				String ligne;
				while ((ligne=buff.readLine())!=null){
					contenu = contenu.concat(ligne);
				}
				buff.close(); 
				System.out.println(XMLOutils.obtenirXml(contenu));
				XmlObject temp = XMLOutils.obtenirElementsXml(XMLOutils.obtenirXml(contenu), "soapenv:Envelope#soapenv:Body#_2:obtenirComptesListePersonnePhysiqueResponse#re:ListEqpm", null, true)[0];
				//#re:Foyer
				return (Compte) XMLOutils.toObject(retour, temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * Permet de trouver le compte associé au nom
	 * @param cas le type de cas (CE, BP, IOM, BRED....)
	 * @param nom le nom du fichier si il est connu
	 * @return le compte obtenu du fichier
	 * @throws SeleniumException en cas d'erreur.
	 */
	public static Risque trouverRisque(int cas, String nom) throws SeleniumException {
		File repertoire = new File("");
		Risque retour = new Risque("1234", cas);
		File fichier = new File("");
		
		switch (cas) {
			case Constantes.CAS_BP : repertoire = new File(Constantes.REPERTOIRE_BP_RISQUE);break;
			case Constantes.CAS_CE : repertoire = new File(Constantes.REPERTOIRE_CE_RISQUE);break;
			case Constantes.CAS_BRED : repertoire = new File(Constantes.REPERTOIRE_BP_RISQUE);break;
		}
		
		boolean existe = false;

		File[] listOfFiles = repertoire.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
				if (nom.equals(listOfFiles[i].getName().split(".xml")[0])) {
					existe = true;
					fichier = listOfFiles[i];
					break;
				}
		}

		if (existe) {
			try {
				// Lecture brute du fichier
				String contenu = "";
				InputStream flux=new FileInputStream(fichier); 
				InputStreamReader lecture=new InputStreamReader(flux);
				BufferedReader buff=new BufferedReader(lecture);
				String ligne;
				while ((ligne=buff.readLine())!=null){
					contenu = contenu.concat(ligne);
				}
				buff.close(); 
				System.out.println(XMLOutils.obtenirXml(contenu));
				XmlObject temp = XMLOutils.obtenirElementsXml(XMLOutils.obtenirXml(contenu), "soapenv:Envelope#soapenv:Body#_2:obtenirListeNoteBale2Response#re:ListSegmBale2", null, true)[0];
				//#re:Foyer
				return (Risque) XMLOutils.toObject(retour, temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * Permet d'ajouter un bouton de sauvegarde à l'IHM de génération.
	 * @param ihm L'IHM elle même.
	 * @param cas le type de cas (CE, BP ...)
	 * @param idClient l'identifiant du client (le numéro "libre")
	 * @param foyer le descriptif du foyer à sauvegarder
	 * @param risque le descriptif du risque à sauvegarder
	 * @param compte le descriptif du compte à sauvegarder
	 */
	private static void ajouterSauvegarde(final IHMOutils ihm, final int cas, final String idClient, final Foyer foyer, final Risque risque, final Compte compte) {
		JButton sauvegarde = new JButton(new AbstractAction() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ihm.majSaisies();
					SC00Test.ecritureFichier(cas, idClient, foyer, compte, risque);
				} catch (SeleniumException e1) {
					System.out.println("Impossible de permettre la sauvegarde");
					e1.printStackTrace();
				}
			}
		});
		sauvegarde.setText("Sauvegarder");
		ihm.ajouterBouton(sauvegarde);
	}

	/**
	 * Permet de lancer l'initialisation de l'IHM en fonction d'un distributeur.
	 * @param cas le distributeur concerné.
	 */
	public static void choixDistributeur(int cas) {
		IHMOutils outil = new IHMOutils();
		String nom = trouverNumero(cas);
		Foyer foyer = new Foyer(nom, cas);
		Risque risque = new Risque(nom, cas);
		Compte compte = new Compte(nom, cas);
		JFrame retour = outil.genererInterfaceXML(foyer, risque, compte);
		ajouterSauvegarde(outil, cas, nom, foyer, risque, compte);
		retour.pack();
		retour.setVisible(true);
	}
	
	/**
	 * Permet de lancer l'initialisation de l'IHM en fonction d'un bouchon.
	 * @param cas le distributeur concerné.
	 * @param bouchon le nom du bouchon à rechercher (un suffixe .xml sera ajouté)
	 */
	public static void lectureBouchon(int cas, String bouchon) {
		
		IHMOutils outil = new IHMOutils();
		Foyer foyer = new Foyer("1234", cas);
		Risque risque = new Risque("1234", cas);
		Compte compte = new Compte("1234", cas);

		try {
			foyer = trouverFoyer(cas, bouchon);
			compte = trouverCompte(cas, bouchon);
			risque = trouverRisque(cas, bouchon);
		} catch (SeleniumException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JFrame retour = outil.genererInterfaceXML(foyer, risque, compte);
		ajouterSauvegarde(outil, cas, bouchon, foyer, risque, compte);
		retour.pack();
		retour.setVisible(true);
	}
	
	
}
