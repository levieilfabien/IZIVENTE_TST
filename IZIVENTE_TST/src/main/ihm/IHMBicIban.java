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

import main.outils.RIBOutils;

/**
 * Classe permettant la création et l'affichage d'une IHM simple de génération de BIC/IBAN.
 * @author levieilfa
 *
 */
public class IHMBicIban {
	
	/**
	 * Champ pour la saisie d'un identifiant unique.
	 */
	public JTextField idClient;
	
	/**
	 * Champ pour la saisie d'un numéro d'agence.
	 */
	public JTextField agence;
	
	/**
	 * Champ pour la saisie du numéro d'établissement.
	 */
	public JTextField etablissement;
	
	/**
	 * Combobox pour le choix du distributeur.
	 */
	public JComboBox distributeur = new JComboBox(new Object[] {"CE", "IOM", "BP", "BRED"});
	
	/**
	 * Champ de sortie pour l'IBAN.
	 */
	public JTextField iban;
	
	/**
	 * Champ de sortie pour le BIC.
	 */
	public JTextField bic;
	
	/**
	 * Fonction main pour la préparation d'une génération de BIC/IBAN 
	 * @param argv inutilisé dans ce cas.
	 */
	public static void main(String[] argv) {
		final IHMBicIban ihm =  new IHMBicIban();
		// Initialisation
		JFrame fenetre = new JFrame("Générateur de RIB");
		fenetre.setLayout(new BorderLayout());
		JPanel saisie = new JPanel(new FlowLayout());
		JPanel sortie = new JPanel(new FlowLayout());
		JPanel bouton = new JPanel(new FlowLayout());
		
		// Champs de saisie
		ihm.idClient = new JTextField("123456789");
		ihm.idClient.setPreferredSize(new Dimension(200, 20));
		ihm.idClient.setToolTipText("Nombre de référence (ex : IdClient)");
		ihm.etablissement = new JTextField("11315");
		ihm.etablissement.setPreferredSize(new Dimension(60, 20));
		ihm.etablissement.setToolTipText("Identifiant de l'établissement");
		ihm.agence = new JTextField("");
		ihm.agence.setEditable(true);
		ihm.agence.setBorder(null);
		ihm.agence.setBackground(null);
		ihm.agence.setPreferredSize(new Dimension(60, 20));
		ihm.agence.setToolTipText("Identifiant de l'agence");
		
		saisie.add(ihm.idClient);
		saisie.add(ihm.etablissement);
		saisie.add(ihm.agence);
		saisie.add(ihm.distributeur);
		
		// Champs de sortie
		ihm.iban = new JTextField("IBAN");
		ihm.iban.setEditable(false);
		ihm.iban.setBorder(null);
		ihm.iban.setBackground(null);
		ihm.iban.setPreferredSize(new Dimension(300, 20));
		ihm.bic = new JTextField("BIC");
		ihm.bic.setEditable(false);
		ihm.bic.setBorder(null);
		ihm.bic.setBackground(null);
		ihm.bic.setPreferredSize(new Dimension(100, 20));
		
		sortie.add(ihm.iban);
		sortie.add(ihm.bic);
		
		// Bouton
		JButton generer = new JButton(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				boolean lancement = true;
				// On vérifie la validité des variables.
				if ("".equals(ihm.etablissement.getText())) {
					ihm.etablissement.setBackground(Color.RED);
					lancement = false;
				} else {
					if ("".equals(ihm.agence.getText())) {
						ihm.agence.setText(RIBOutils.genererGuichet(ihm.etablissement.getText()));
					}
					ihm.etablissement.setBackground(Color.GREEN);
				}
				if ("".equals(ihm.agence.getText())) {
					ihm.agence.setBackground(Color.RED);
					lancement = false;
				} else {
					ihm.agence.setBackground(Color.GREEN);
				}
				if ("".equals(ihm.idClient.getText())) {
					ihm.idClient.setBackground(Color.RED);
					lancement = false;
				} else {
					ihm.idClient.setBackground(Color.GREEN);
				}
				// Si les données saisies sont valides on génère.
				if (lancement) {
					String numeroCompteIBAN = RIBOutils.genererIbanFR76(ihm.etablissement.getText(), ihm.agence.getText(), ihm.idClient.getText());
					//String codeBIC = RIBOutils.genererBIC(ihm.distributeur.getSelectedIndex() + 1);
					String codeBIC = RIBOutils.genererBIC(ihm.etablissement.getText());
					
					ihm.iban.setText(numeroCompteIBAN);
					ihm.bic.setText(codeBIC);
				}
			}
		});
		generer.setText("Generation");
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
