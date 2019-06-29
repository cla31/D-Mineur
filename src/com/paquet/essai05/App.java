package com.paquet.essai05;

/**
 * essai pour générer smiley au clic grâce à la fonction Random
 * et en fonction d'un nombre choisi de smile (mais dansle code en dur)
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
	/**
	 * j'en aurai peut-être pas exactement le nombre indiquer car avec random peut tomber plusieurs fois la même case?
	 */
	private static final int MAXSMILE = 20;
	private Game game;
	
	
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
    	// Création de la grille
        Container cp1 = frame.getContentPane();
        cp1.setLayout(new GridLayout(ROWS, COLUMNS));   
   
		
		
		
		
		/**
		 *  Création du jeu
		 */
        /**
         * notre constructeur prend en paramètre les valeurs des attributs de App
         */
        /**
         * c'est comme ça qu'il a été conçu dans Game.
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
		
		
        
        
        
    }
    
    public static void main(String[] args) throws IOException {
        App example = new App();
        SwingUtilities.invokeLater(example);
    }
}