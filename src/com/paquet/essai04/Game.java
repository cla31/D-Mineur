package com.paquet.essai04;

/**
 * Essai pour g�n�rer des smiley sur toute la grille apr�s avoir cliqu�
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
    				    	//La m�thode getSource() dit que c'est l'objet Case qui a g�n�r� l'�v�nement
    				    	onLeftClick((Case) e.getSource());
    				    }
    				}
    			});
        	}
    	}
    	
    	
    }
    

    
    

    
	public JButton getCase(int i, int j) {
		return this.cases[i][j];
	}

	
	
	/**
	 *  Clic gauche sur une case
	 * @param c
	 */
	private void onLeftClick(Case c) {
		/**
		 *  V�rifie que la case n'est pas marqu�e ni ouverte 
		   et que la partie n'est pas termin�e */
		
		/**
		 * Ouvre toutes les cases
		 * 
		 */
				for(int i = 0; i < this.rows; i++) {
		    		for(int j = 0; j < this.cols; j++) {
		   
		    			this.cases[i][j].open();
		        	}
		    	}
		
		
	}

	
}