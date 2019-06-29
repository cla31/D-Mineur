package com.paquet.essai04;

/**
 * Essai pour g�n�rer des smiley sur toute la grille apr�s avoir cliqu� sur l'une des cases.
 */

import java.awt.Container;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class App extends FrameForDemoMaker {
	
	/* Explications: l'objet Case g�re uniquement l'apparence et les �tats de la case
	 * l'object Game initialise un tableau de Case et g�re les r�gles du jeu, les �v�nements et les relations entre ses cases
	 * Nb: les cases n'utilisent que des images pour des raisons est�tiques
	 */
	
	/**
	 *  Propri�t�s fixes pour la partie
	 */
    private static final int ROWS = 15;
	private static final int COLUMNS = 15;
	private static final int MAXMINES = 30;
	private Game game;
	
	// Constructeur
    public App() throws IOException{
        super("Jeu du d�mineur");
        setDefaultBounds(100, 100, 50* COLUMNS, 50 * ROWS);
    }

    @Override
    public void init(JFrame frame) {
    	/**
    	 *  Cr�ation de la grille
    	 */
        Container cp = frame.getContentPane();
        cp.setLayout(new GridLayout(ROWS, COLUMNS));
        
        /**
         *  Cr�ation du jeu
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