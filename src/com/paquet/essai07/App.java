package com.paquet.essai07;
/**
 * essai pour générer smiley au clic grâce à la fonction Random + champ pour nombre de smile
 * + essai meilleur agencement fenêtre
 * Bouton valider non fonctionnel
 */

import java.awt.BorderLayout;

/**
 * essai pour générer smiley au clic grâce à la fonction Random + champ pour rentrer le nombre de smile
 * + essai meilleur agencement fenêtre
 * Bouton valider non fonctionnel
 */

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private static final int MAXSMILE = 20;
	private Game game;
	private JTextField textField;
	
	
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
    	Container cp = frame.getContentPane();
    	/**
    	 * premier contenant cp = border layout
    	 */
    	cp.setLayout(new BorderLayout(0, 0));
    	/**
    	 * création d'un premier panel
    	 */
    	JPanel panel_1 = new JPanel();
    	/**
    	 * on rajoute un panel au centre de cp
    	 */
    	cp.add(panel_1,BorderLayout.CENTER);
    	/**
    	 * on paramètre un GridLayout au panel_1:
    	 */
    	panel_1.setLayout(new GridLayout(ROWS, COLUMNS));
    	/**
    	 * Création d'un deuxième panel
    	 */
    	JPanel panel_2 = new JPanel();
    	/**
    	 * on rajoute un panel au nord de cp
    	 */
    	cp.add(panel_2,BorderLayout.NORTH);
    	panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		JButton btnNewButton = new JButton("Valider");
		panel_2.add(btnNewButton);
//    	Container cp1 = frame.getContentPane();
    	//panel_2.setLayout( new GridLayout(ROWS, COLUMNS));
    	// Création de la grille
//        Container cp1 = frame.getContentPane();
//        cp1.setLayout(new GridLayout(ROWS, COLUMNS));   
   
        
        
        
		
				/**
				 *  Création du jeu
				 */
		    	game = new Game(ROWS, COLUMNS, MAXSMILE);
				// Remplissage de la grille
		        for(int i = 0; i < ROWS; i++) {
		        	for(int j = 0; j < COLUMNS; j++) {
		            	panel_1.add(game.getCase(i, j));
		            }	
		        }
		       
			
		
		
		
	
        
        
        
    }
    
    public static void main(String[] args) throws IOException {
        App example = new App();
        SwingUtilities.invokeLater(example);
    }
}