package com.paquet.essai06;

/**
 * essai pour g�n�rer smiley au clic gr�ce � la fonction Random+
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
	 * attribut case cr�e gr�ce � la classe case, c'est un tableau.
     * Cr�er une classe ne sert pas forc�ment � cr�er des objets, sert aussi � cr�er des attributs?
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
    	 *  D�finitions des propri�t�s
    	 */
    	this.rows = rows;
    	this.cols = cols;
    	this.mines = mines;
    	this.cases = new Case[rows][cols];
    	
    	this.setCases();

    }
    
    
    /**
     *  Cr�ation des cases
     */
    private void setCases() {
    	for(int i = 0; i < this.rows; i++) {
    		for(int j = 0; j < this.cols; j++) {
    			this.cases[i][j] = new Case(i, j);
    			
    			/**
    			 *  Ajouts des �v�nements sur les cases
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
    				    	 * La m�thode getSource() dit que c'est l'objet Case qui a g�n�r� l'�v�nement
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
  *  G�n�re un nombre al�atoire (sert � placer les mines)
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
		/* V�rifie que la case n'est pas marqu�e ni ouverte 
		   et que la partie n'est pas termin�e */
		
		/**
		 *  Ouvre toutes les cases en fonction de la fonction random
		 */
				for(int k = 0; k < mines; k++) {
		    		
		    			this.cases[this.random(rows)][this.random(cols)].open();
		        	}
		    	
		
		
	}

	
}