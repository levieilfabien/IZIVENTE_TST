package main.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.constantes.Constantes;
import main.outils.IZIVENTEOutils;

public class IHMMurissement {

	
	/**
	 * Champ pour la saisie du SIOC ID
	 */
	public JTextField siocID = new JTextField("5884749");
	
	/**
	 * Champ pour la saisie d'une date
	 */
	public JTextField saisieDate = new JTextField("21/12/16");
	
	/**
	 * Combobox pour le choix du distributeur.
	 */
	public JComboBox distributeur = new JComboBox(new Object[] {"CE", "BP"});
	
	/**
	 * Combobox pour le retour.
	 */
	public JTextField retour = new JTextField("N�ant");
	
	/**
	 * Combobox pour le choix du mode.
	 */
	public JComboBox mode = new JComboBox(new Object[] {"CR", "PP"});
	
	/**
	 * Fonction main pour la pr�paration d'une g�n�ration de BIC/IBAN 
	 * @param argv inutilis� dans ce cas.
	 */
	public static void main(String[] argv) {
		final IHMMurissement ihm =  new IHMMurissement();
		// Initialisation
		JFrame fenetre = new JFrame("Murissement d'un dossier");
		fenetre.setLayout(new BorderLayout());
		JPanel saisie = new JPanel(new FlowLayout());
		JPanel sortie = new JPanel(new FlowLayout());
		JPanel bouton = new JPanel(new FlowLayout());
		
		ihm.siocID.setPreferredSize(new Dimension(60, 20));
		ihm.siocID.setToolTipText("SIOCID de la simulation � murir");
		ihm.saisieDate.setPreferredSize(new Dimension(60, 20));
		ihm.saisieDate.setToolTipText("Date au format JJ/MM/AA, � laisse vider si date du jour-15");
		ihm.retour.setPreferredSize(new Dimension(200, 20));
		
		// Ajout dans une IHM
		saisie.add(ihm.siocID);
		saisie.add(ihm.saisieDate);
		saisie.add(ihm.distributeur);
		saisie.add(ihm.mode);
		sortie.add(ihm.retour);
		
		// Bouton
		JButton generer = new JButton(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				boolean lancement = true;
				String date = ihm.saisieDate.getText();
				// On v�rifie la validit� des variables.
				if ("".equals(ihm.siocID.getText())) {
					ihm.siocID.setBackground(Color.RED);
					lancement = false;
				} else {
					ihm.siocID.setBackground(Color.GREEN);
				}
			
				if(lancement) {
					boolean retour = IZIVENTEOutils.murissement(ihm.siocID.getText(), ihm.distributeur.getSelectedIndex()==0?Constantes.CAS_CE:Constantes.CAS_BP, ihm.mode.getSelectedIndex()==0?true:false, "".equals(date)?null:date);

					if (retour) {
						ihm.retour.setBackground(Color.GREEN);
						ihm.retour.setText("Les 3 dates ont �t� modifi�es");
					} else {
						ihm.retour.setBackground(Color.RED);
						ihm.retour.setText("Au moins une date n'as pas �t� modifi�e");
					}
				}
			}
		});
		
		generer.setText("Murissement");
		bouton.add(generer);
		
		// Mise en place
		fenetre.add(saisie, BorderLayout.NORTH);
		fenetre.add(sortie, BorderLayout.CENTER);
		fenetre.add(bouton, BorderLayout.SOUTH);
		
		// Affichage
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
