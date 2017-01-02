package main.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
	public JTextField saisieDate = new JTextField("dd/MM/yy");
	
	/**
	 * Combobox pour le choix du distributeur.
	 */
	public JComboBox distributeur = new JComboBox(new Object[] {"CE", "BP"});
	
	/**
	 * Champ pour le retour.
	 */
	public JTextField retour = new JTextField("<Pas encore murit>");
	
	/**
	 * Champ pour le date de signature.
	 */
	public JTextField consultSignature = new JTextField("dd/MM/yy");
	
	/**
	 * Champ pour le date de l'évènement.
	 */
	public JTextField consultEvenement = new JTextField("dd/MM/yy");
	
	/**
	 * Champ pour le date de financement.
	 */
	public JTextField consultFinancement = new JTextField("dd/MM/yy");
	
	/**
	 * Champ pour le date de retractation.
	 */
	public JTextField consultRetract = new JTextField("dd/MM/yy");
	
	/**
	 * Combobox pour le choix du mode.
	 */
	public JComboBox mode = new JComboBox(new Object[] {"CR", "PP"});
	
	/**
	 * Fonction main pour la préparation d'une génération de BIC/IBAN 
	 * @param argv inutilisé dans ce cas.
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
		ihm.siocID.setToolTipText("SIOCID de la simulation à murir");
		ihm.saisieDate.setPreferredSize(new Dimension(60, 20));
		ihm.saisieDate.setToolTipText("Date au format JJ/MM/AA, à laisse vide si date du jour-15");
		ihm.consultSignature.setPreferredSize(new Dimension(200, 20));
		ihm.consultSignature.setToolTipText("Date de signature en consultation");
		ihm.consultSignature.setEditable(false);
		ihm.consultSignature.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		ihm.consultEvenement.setPreferredSize(new Dimension(200, 20));
		ihm.consultEvenement.setToolTipText("Date de l'évènement en consultation");
		ihm.consultEvenement.setEditable(false);
		ihm.consultEvenement.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		ihm.consultFinancement.setPreferredSize(new Dimension(200, 20));
		ihm.consultFinancement.setToolTipText("Date de financement en consultation");
		ihm.consultFinancement.setEditable(false);
		ihm.consultFinancement.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		ihm.consultRetract.setPreferredSize(new Dimension(200, 20));
		ihm.consultRetract.setToolTipText("Date de retractation en consultation");
		ihm.consultRetract.setEditable(false);
		ihm.consultRetract.setAlignmentY(JComponent.LEFT_ALIGNMENT);
		ihm.retour.setPreferredSize(new Dimension(200, 20));
		
		// Ajout dans une IHM
		saisie.add(ihm.siocID);
		saisie.add(ihm.saisieDate);
		saisie.add(ihm.distributeur);
		saisie.add(ihm.mode);
		sortie.add(ihm.consultSignature);
		sortie.add(ihm.consultEvenement);
		sortie.add(ihm.consultFinancement);
		sortie.add(ihm.consultRetract);
		bouton.add(ihm.retour);
		
		// Bouton
		JButton generer = new JButton(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				boolean lancement = true;
				String date = ihm.saisieDate.getText();
				// On vérifie la validité des variables.
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
						ihm.retour.setText("Les 3 dates ont été modifiées");
					} else {
						ihm.retour.setBackground(Color.RED);
						ihm.retour.setText("Au moins une date n'as pas été modifiée");
					}
				}
			}
		});
		generer.setText("Murissement");
		
		// Bouton
		JButton consulter = new JButton(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				boolean lancement = true;
				// On vérifie la validité des variables.
				if ("".equals(ihm.siocID.getText())) {
					ihm.siocID.setBackground(Color.RED);
					lancement = false;
				} else {
					ihm.siocID.setBackground(Color.GREEN);
				}
			
				if(lancement) {
					List<String> dates = IZIVENTEOutils.consultation(ihm.siocID.getText(), ihm.distributeur.getSelectedIndex()==0?Constantes.CAS_CE:Constantes.CAS_BP);

						ihm.retour.setText("Extraction effectuée");
						ihm.consultSignature.setText(dates.get(0));
						ihm.consultEvenement.setText(dates.get(1));
						ihm.consultFinancement.setText(dates.get(2));
						ihm.consultRetract.setText(dates.get(3));

				}
			}
		});
		consulter.setText("Consulter");
		bouton.add(consulter);
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
