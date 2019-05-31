package com.paquet.essai01;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class Game {
	private int rows;
	private int cols;
	private int mines;
	private boolean loose = false;
    private Case[][] cases;
    
    public Game(int rows, int cols, int mines) {
    	
    	// D�finitions des propri�t�s
    	this.rows = rows;
    	this.cols = cols;
    	this.mines = mines;
    	this.cases = new Case[rows][cols];
    	
    	this.setCases();
    	this.setValues();
    }
    
    // Cr�ation des cases
    private void setCases() {
    	for(int i = 0; i < this.rows; i++) {
    		for(int j = 0; j < this.cols; j++) {
    			this.cases[i][j] = new Case(i, j);
    			
    			// Ajouts des �v�nements sur les cases
    			this.cases[i][j].addMouseListener(new MouseAdapter() {
    				@Override
    				public void mouseReleased(MouseEvent e) {
    					super.mouseReleased(e);
    				    if (SwingUtilities.isRightMouseButton(e)) {
    				    	onRightClick((Case) e.getSource());
    				    }
    				}
    			});
    			this.cases[i][j].addMouseListener(new MouseAdapter() {
    				@Override
    				public void mouseReleased(MouseEvent e) {
    					super.mouseReleased(e);
    				    if (SwingUtilities.isLeftMouseButton(e)) {
    				    	onLeftClick((Case) e.getSource());
    				    }
    				}
    			});
        	}
    	}
    	
    	// Placement des mines
    	for(int m = 0; m < this.mines; m++) {
    		this.cases[this.random(rows)][this.random(cols)].setMine();
    	}
    }
    
    // G�n�re un nombre al�atoire
    private int random(int max) {
    	return (int)(Math.random() * ((max - 1) + 1));
    }
    
    // D�finie le nombre de mines adjacentes des cases
    private void setValues() {
    	for(int i = 0; i < this.rows; i++) {
    		for(int j = 0; j < this.cols; j++) {
    			int mines = 0;
    			
    			// V�rifie chaque cases adjacentes
    			if(i > 0 && j > 0 && this.cases[i - 1][j - 1].isMine()) mines++;
    			if(j > 0 && this.cases[i][j - 1].isMine()) mines++;
    			if(i < this.rows - 1 && j > 0 && this.cases[i + 1][j - 1].isMine()) mines++;
    			if(i > 0 && this.cases[i - 1][j].isMine()) mines++;
    			if(i < this.rows - 1 && this.cases[i + 1][j].isMine()) mines++;
    			if(i > 0 && j < this.cols - 1 && this.cases[i - 1][j + 1].isMine()) mines++;
    			if(j < this.cols - 1 && this.cases[i][j + 1].isMine()) mines++;
    			if(i < this.rows - 1 && j < this.cols - 1 && this.cases[i + 1][j + 1].isMine()) mines++;
    			
    			this.cases[i][j].setValue(mines);
        	}
    	}
    }

    // Getters
	public boolean getLoose() {
		return this.loose;
	}
	public JButton getCase(int i, int j) {
		return this.cases[i][j];
	}

	// Partie perdue
	public void loose() {
		this.loose = false;
		
		// Ouvre toutes les cases
		for(int i = 0; i < this.rows; i++) {
    		for(int j = 0; j < this.cols; j++) {
    			this.cases[i][j].unflag();
    			this.cases[i][j].open();
        	}
    	}
	}
	
	// Clic gauche sur une case
	private void onLeftClick(Case c) {
		/* V�rifie que la case n'est pas marqu�e ni ouverte 
		   et que la partie n'est pas termin�e */
		
		if(!(c.isFlaged() || c.isOpened()) && !this.loose) {
			// Ouvre la case
			c.open();
			
			if(c.isMine()) {
				// Partie perdue
				this.loose();
			} else {
				if(c.getValue() == 0) {
					
					// Ouvre les cases adjacentes si aucune mine autour
					int i = c.getPosX();
					int j = c.getPosY();
					if(i > 0 && j > 0) this.cases[i - 1][j - 1].open();
	    			if(j > 0) this.cases[i][j - 1].open();
	    			if(i < this.rows - 1 && j > 0) this.cases[i + 1][j - 1].open();
	    			if(i > 0) this.cases[i - 1][j].open();
	    			if(i < this.rows - 1) this.cases[i + 1][j].open();
	    			if(i > 0 && j < this.cols - 1) this.cases[i - 1][j + 1].open();
	    			if(j < this.cols - 1) this.cases[i][j + 1].open();
	    			if(i < this.rows - 1 && j < this.cols - 1) this.cases[i + 1][j + 1].open();
				}
			}
		}
	}

	// Clic droit sur une case
	private void onRightClick(Case c) {
		/* V�rifie que la case n'est pas ouverte 
		   et que la partie n'est pas termin�e */
		
		if(!c.isOpened() && !this.loose) {
			// Marque d'un drapeau la case
			if(!c.isFlaged()) c.flag();
			else c.unflag();
		}
	}
}