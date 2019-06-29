package com.paquet.essai06;

/**
 * essai pour générer smiley au clic grâce à la fonction Random + champ pour rentrer le nombre de smile max
 */

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class App extends FrameForDemoMaker {
	
	/* Explications: l'objet Case gère uniquement l'apparence et les états de la case
	 * l'object Game initialise un tableau de Case et gère les règles du jeu, les évènements et les relations entre ses cases
	 * Nb: les cases n'utilisent que des images pour des raisons estétiques
	 */
	
	/**
	 *  Propriétés fixes pour la partie
	 */
    private static final int ROWS = 15;
	private static final int COLUMNS = 15;
	private static final int MAXSMILE = 0;
	private Game game;
	private JTextField saisieTextField;
	static int nbreSaisi;
	
	/**
	 *  Constructeur
	 * @throws IOException
	 */
    public App() throws IOException{
        super("Jeu du démineur");
        setDefaultBounds(100, 100, 50* COLUMNS, 50 * ROWS);
    }
     
     
    @Override
    public void init(JFrame frame) {
    	/**
    	 *  Création de la grille
    	 */
        Container cp1 = frame.getContentPane();
        cp1.setLayout(new GridLayout(ROWS, COLUMNS));   
   
		saisieTextField = new JTextField("Effacer cette ligne et entrez un nombre de smile max souhaité et appuyer sur le bouton Valider");
		cp1.add(saisieTextField);
		JLabel Resultat = new JLabel("Resultat");
		Resultat.setBounds(145, 208, 125, 16);
		cp1.add(Resultat);
		JButton validerBouton = new JButton("Valider");
		validerBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * on caste le String( récupéré dans le textField) en integer
				 * pour ensuite pouvoir faire le calcul:
				 * <code> nbresaisi=Integer.valueOf(le string à caster).intValue();</code>
				 */
				nbreSaisi = Integer.valueOf(saisieTextField.getText()).intValue();
				System.out.println(nbreSaisi);
				int MAXSMILE= nbreSaisi;
				/**
				 *  Création du jeu
				 */
		    	game = new Game(ROWS, COLUMNS, MAXSMILE);
				/**
				 *  Remplissage de la grille
				 */
		        for(int i = 0; i < ROWS; i++) {
		        	for(int j = 0; j < COLUMNS; j++) {
		            	cp1.add(game.getCase(i, j));
		            }	
		        }
		        Resultat.setText(String.valueOf(game));
			}});
		
		
		
		cp1.add(validerBouton);
        
        
        
    }
    
    public static void main(String[] args) throws IOException {
        App example = new App();
        SwingUtilities.invokeLater(example);
    }
}