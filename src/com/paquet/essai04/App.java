package com.paquet.essai04;

/**
 * Essai pour générer des smiley sur toute la grille après avoir cliqué sur l'une des cases.
 */

import java.awt.Container;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
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
	private static final int MAXMINES = 30;
	private Game game;
	
	// Constructeur
    public App() throws IOException{
        super("Jeu du démineur");
        setDefaultBounds(100, 100, 50* COLUMNS, 50 * ROWS);
    }

    @Override
    public void init(JFrame frame) {
    	/**
    	 *  Création de la grille
    	 */
        Container cp = frame.getContentPane();
        cp.setLayout(new GridLayout(ROWS, COLUMNS));
        
        /**
         *  Création du jeu
         */
    	game = new Game(ROWS, COLUMNS, MAXMINES);
        
        /**
         *  Remplissage de la grille
         */
        for(int i = 0; i < ROWS; i++) {
        	for(int j = 0; j < COLUMNS; j++) {
            	cp.add(game.getCase(i, j));
            }	
        }
    }
    
    public static void main(String[] args) throws IOException {
        App example = new App();
        SwingUtilities.invokeLater(example);
    }
}