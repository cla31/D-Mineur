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
	/**
	 * attribut case cr�e gr�ce � la classe case, c'est un tableau.
	 */
    /**
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
    	
    	// D�finitions des propri�t�s
    	this.rows = rows;
    	this.cols = cols;
    	this.mines = mines;
    	/**
    	 * la case cliqu�e:
    	 */
    	this.cases = new Case[rows][cols];
    	
    	this.setCases();
    	this.setValues();
    }
    
    
    /**
     *  Cr�ation des cases: �coute des classes (en fonction de clique droit ou gauche, 
     *  cf m�thode onRigntClic ou OnLeftClic)
     *  et placement des mines avec random.
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
    				    if (SwingUtilities.isRightMouseButton(e)) {
    				    	/**
    				    	 * La m�thode getSource() renvoie l'objet qui a g�n�r� l'�v�nement
    				    	 */
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
    	
    	/**
    	 *  Placement des mines (avec la fonction random)
    	 */
    	for(int m = 0; m < this.mines; m++) {
    		this.cases[this.random(rows)][this.random(cols)].setMine(); // d'o� il sort ce setMine()? cf classe Case
    	}
    }
    
    /**
     * G�n�re un nombre al�atoire (sert � placer les mines)
     * @param max
     * @return un nombre au hasard < ou = � max
     */
    private int random(int max) {
    	return (int)(Math.random() * ((max - 1) + 1));
    }
    
    /**
     *  D�finie le nombre de mines adjacentes des cases
     */
    private void setValues() {
    	for(int i = 0; i < this.rows; i++) {
    		for(int j = 0; j < this.cols; j++) {
    			int mines = 0;
    			
    			/**
    			 *  V�rifie chaque case adjacente (cherche les mines sur les voisins de this.cases ),
    			 *  regarde les voisins un par un, d'o� 8 lignes
    			 *  rappel:
    			 *  <code>public boolean isMine() {
				 *		return this.isMine;
				 *	}</code>
				 *	@see classe Case
    			 */
    			
    			/**
    			 * D�tails:
    			 * On se place sur une case qui a pour coordonn�e i et j
    			 * Penser ce code comme un tableau Excel o� i = des lignes et j = des colonnes.
    			 * 
    			 * pour avoir i-1 et j -1 il faut forc�ment que i et j soit superieur � 0
    			 * et si il y a une mine, on incr�mente de 1.
    			 * mines++ est un compteur de mines qui est ensuite transmis � setValue(mines).
    			 * 
    			 *  case voisine en haut � gauche ( voisine nord ouest):
    			 */
    			if(i > 0 && j > 0 && this.cases[i - 1][j - 1].isMine()) mines++;
    			/**
    			 *  case voisine centre ouest : 
    			 */
    			if(j > 0 && this.cases[i][j - 1].isMine()) mines++;
    			/**
    			 * cas particulier pour chaque bord
    			 * this.row -1 correspond � la derni�re ligne.
    			 * Voisine en bas � gauche (sud ouest)
    			 */
    			if(i < this.rows - 1 && j > 0 && this.cases[i + 1][j - 1].isMine()) mines++;
    			
    			/**
    			 * Test de la ligne du haut
    			 * case voisine nord centre:
    			 */
    			if(i > 0 && this.cases[i - 1][j].isMine()) mines++;
    			/**
    			 * case voisine sud centre
    			 */
    			
    			if(i < this.rows - 1 && this.cases[i + 1][j].isMine()) mines++;
    			/**
    			 * case voisine nord est
    			 */
    			
    			if(i > 0 && j < this.cols - 1 && this.cases[i - 1][j + 1].isMine()) mines++;
    			/**
    			 * case voisine centre est
    			 */
    			
    			if(j < this.cols - 1 && this.cases[i][j + 1].isMine()) mines++;
    			/**
    			 * casevoisine sud est 
    			 */
    			
    			if(i < this.rows - 1 && j < this.cols - 1 && this.cases[i + 1][j + 1].isMine()) mines++;
    			
    			this.cases[i][j].setValue(mines);
        	}
    	}
    }

    /**
     *  Getters
     * @return
     */
	public boolean getLoose() {
		return this.loose;
	}
	public JButton getCase(int i, int j) {
		return this.cases[i][j];
	}

	/**
	 *  Partie perdue
	 */
	public void loose() {
		this.loose = false;
		
		/**
		 *  Ouvre toutes les cases
		 */
		for(int i = 0; i < this.rows; i++) {
    		for(int j = 0; j < this.cols; j++) {
    			this.cases[i][j].unflag();
    			this.cases[i][j].open();
        	}
    	}
	}
	
	/**
	 *  Clic gauche sur une case
	 * @param c
	 */
	private void onLeftClick(Case c) {
		/**
		 *  V�rifie que la case n'est pas marqu�e ni ouverte 
		 *
		 * et que la partie n'est pas termin�e 
		 */
		
		if(!(c.isFlaged() || c.isOpened()) && !this.loose) {
			/**
			 *  Ouvre la case
			 */
			c.open();
			
			if(c.isMine()) {
				/**
				 *  Partie perdue
				 */
				this.loose();
			} else {
				if(c.getValue() == 0) {
					
					/**
					 *  Ouvre les cases adjacentes si aucune mine autour
					 *  rappel:
					 *  <code>private int posX;
	                 *   private int posY;</code>
	                 *   <code>public int getPosX() {
					 *		return this.posX;
					 *	}
					 *	public int getPosY() {
					 *		return this.posY;
					 *	}</code>
					 * @see classe Case
	                 *   
	                 *   
					 */
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

	/**
	 *  Clic droit sur une case
	 * @param c
	 */
	private void onRightClick(Case c) {
		/**
		 *  V�rifie que la case n'est pas ouverte 
		 *
		 * et que la partie n'est pas termin�e 
		 */
		if(!c.isOpened() && !this.loose) {
			// Marque d'un drapeau la case
			if(!c.isFlaged()) c.flag();
			else c.unflag();
		}
	}
}