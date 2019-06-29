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
	 * attribut case crée grâce à la classe case, c'est un tableau.
	 */
    /**
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
    	
    	// Définitions des propriétés
    	this.rows = rows;
    	this.cols = cols;
    	this.mines = mines;
    	/**
    	 * la case cliquée:
    	 */
    	this.cases = new Case[rows][cols];
    	
    	this.setCases();
    	this.setValues();
    }
    
    
    /**
     *  Création des cases: écoute des classes (en fonction de clique droit ou gauche, 
     *  cf méthode onRigntClic ou OnLeftClic)
     *  et placement des mines avec random.
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
    				    if (SwingUtilities.isRightMouseButton(e)) {
    				    	/**
    				    	 * La méthode getSource() renvoie l'objet qui a généré l'événement
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
    		this.cases[this.random(rows)][this.random(cols)].setMine(); // d'où il sort ce setMine()? cf classe Case
    	}
    }
    
    /**
     * Génère un nombre aléatoire (sert à placer les mines)
     * @param max
     * @return un nombre au hasard < ou = à max
     */
    private int random(int max) {
    	return (int)(Math.random() * ((max - 1) + 1));
    }
    
    /**
     *  Définie le nombre de mines adjacentes des cases
     */
    private void setValues() {
    	for(int i = 0; i < this.rows; i++) {
    		for(int j = 0; j < this.cols; j++) {
    			int mines = 0;
    			
    			/**
    			 *  Vérifie chaque case adjacente (cherche les mines sur les voisins de this.cases ),
    			 *  regarde les voisins un par un, d'où 8 lignes
    			 *  rappel:
    			 *  <code>public boolean isMine() {
				 *		return this.isMine;
				 *	}</code>
				 *	@see classe Case
    			 */
    			
    			/**
    			 * Détails:
    			 * On se place sur une case qui a pour coordonnée i et j
    			 * Penser ce code comme un tableau Excel où i = des lignes et j = des colonnes.
    			 * 
    			 * pour avoir i-1 et j -1 il faut forcément que i et j soit superieur à 0
    			 * et si il y a une mine, on incrémente de 1.
    			 * mines++ est un compteur de mines qui est ensuite transmis à setValue(mines).
    			 * 
    			 *  case voisine en haut à gauche ( voisine nord ouest):
    			 */
    			if(i > 0 && j > 0 && this.cases[i - 1][j - 1].isMine()) mines++;
    			/**
    			 *  case voisine centre ouest : 
    			 */
    			if(j > 0 && this.cases[i][j - 1].isMine()) mines++;
    			/**
    			 * cas particulier pour chaque bord
    			 * this.row -1 correspond à la dernière ligne.
    			 * Voisine en bas à gauche (sud ouest)
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
		 *  Vérifie que la case n'est pas marquée ni ouverte 
		 *
		 * et que la partie n'est pas terminée 
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
		 *  Vérifie que la case n'est pas ouverte 
		 *
		 * et que la partie n'est pas terminée 
		 */
		if(!c.isOpened() && !this.loose) {
			// Marque d'un drapeau la case
			if(!c.isFlaged()) c.flag();
			else c.unflag();
		}
	}
}