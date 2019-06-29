package com.paquet.essai06;

/**
 * essai pour générer smiley au clic grâce à la fonction Random+
 * champ pour nombre de smile max
 * 
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;



public class Game {
    private int rows;
    private int cols;
	private int mines;
	//private boolean loose = false;
	
	/**
	 * attribut case crée grâce à la classe case, c'est un tableau.
     * Créer une classe ne sert pas forcément à créer des objets, sert aussi à créer des attributs?
	 */
    private Case[][] cases; 
    
    /**
     * constructeur:
     * @param rows
     * @param cols
     * @param mines
     */
    public Game(int rows, int cols, int mines) {
    	
    	/**
    	 *  Définitions des propriétés
    	 */
    	this.rows = rows;
    	this.cols = cols;
    	this.mines = mines;
    	this.cases = new Case[rows][cols];
    	
    	this.setCases();

    }
    
    
    /**
     *  Création des cases
     */
    private void setCases() {
    	for(int i = 0; i < this.rows; i++) {
    		for(int j = 0; j < this.cols; j++) {
    			this.cases[i][j] = new Case(i, j);
    			
    			/**
    			 *  Ajouts des évènements sur les cases
    			 */
    			this.cases[i][j].addMouseListener(new MouseAdapter() {
    				

					@Override
    				public void mouseReleased(MouseEvent e) {
    					super.mouseReleased(e);
				}
    			});
    			this.cases[i][j].addMouseListener(new MouseAdapter() {
    				@Override
    				public void mouseReleased(MouseEvent e) {
    					super.mouseReleased(e);
    				    if (SwingUtilities.isLeftMouseButton(e)) {
    				    	/**
    				    	 * La méthode getSource() dit que c'est l'objet Case qui a généré l'événement
    				    	 */
    				    	onLeftClick((Case) e.getSource());
    				    }
    				}
    			});
        	}
    	}
    	
    	/**
    	 *  Placement des mines (avec la fonction random)
    	 *
   	     *   for(int m = 0; m < this.mines; m++) {
    	 *	this.cases[this.random(rows)][this.random(cols)].setMine(); 
    	 * }
    	 * @see Classe Case pour setMine()
    	 */
    }
    
 /**
  *  Génère un nombre aléatoire (sert à placer les mines)
  * @param max
  * @return
  */
    private int random(int max) {
    	return (int)(Math.random() * ((max - 1) + 1));
    }
    
    

    
	public JButton getCase(int i, int j) {
		return this.cases[i][j];
	}

	
	
	/**
	 *  Clic gauche sur une case
	 * @param c
	 */
	private void onLeftClick(Case c) {
		/* Vérifie que la case n'est pas marquée ni ouverte 
		   et que la partie n'est pas terminée */
		
		/**
		 *  Ouvre toutes les cases en fonction de la fonction random
		 */
				for(int k = 0; k < mines; k++) {
		    		
		    			this.cases[this.random(rows)][this.random(cols)].open();
		        	}
		    	
		
		
	}

	
}