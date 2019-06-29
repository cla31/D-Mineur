package com.paquet.essai03;

/**
 * Paquet pour tester l'ouverture des mines sur une case (au clic droit)
 * @author claire
 */

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;




/**
 * <p>ComposantCase correspond aux objets bouton.
 * On utilise un JToggleButton.
 * Un JButton est un bouton classique.
 * Un JToggleButton réagit comme une boite coché ou un interrupteur.
 * Si tu appuie dessus il reste enfoncé, situ ré-appuie dessus,il reprendra son état initial.
 * 
 * On implémente l'interface MouseListener ainsi que toutes les méthodes liées à l'interface.<p>
 * 
 */

public class ComposantCase extends JToggleButton implements MouseListener {
	
	private String name;
	/**
	 * Insruction qui définit les bordures du bouton,
	 * ainsi que la couleur de la bordure.
	 */
	Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
	private final static ImageIcon imgMine = loadImage("images/mine.png");
	
	
	
	
	
	/**
	 *  Chargement de l'image
	 * @param path
	 * @return ResourceUtility.loadImage(path);
	 */
		private static ImageIcon loadImage(String path) {
			try {
				return ResourceUtility.loadImage(path);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

	public ComposantCase(String str) {

		super(str);
		/**
		 * Insruction qui définit les bordures du bouton
		 */
		this.setBorder(lineBorder);
		this.setOpaque(true);
		
		this.name=str;
		
    	
		
		
		/**
		 * Grâce à cette instruction, notre objet va s'écouter
		 * pour repérer les actions de la souris(appui sur le bouton, entrée/sortie du  curseur)
		 * @param this (c'est à dire l'objet lui-même)
		 */
		this.addMouseListener(this);
	}
    
	/**
      * cliquer
      */
	@Override
	public void mouseClicked(MouseEvent e) {
//		 setBackground(Color.green);
//		 System.out.println("Mouse is Clicked");

	}

	/**
	 * Invoqué lorsque la souris entre dans un composant.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
//		setBackground(Color.cyan);
//	    System.out.println("Mouse is Entered");
	  

	}
	/**
	 * Invoqué lorsque la souris quitte un composant.
	 */

	@Override
	public void mouseExited(MouseEvent e) {
		
//		 setBackground(Color.magenta);
//		 System.out.println("Mouse is Exited");
	}
	
	/**
	 * Appuyer sur le bouton
	 */

	@Override
	public void mousePressed(MouseEvent e) {
//		if (this.isEnabled()) {
            /**
             * SwingUtilities?
             * Quand on crée un composant, un thread specifique est chargé de la gestion des évenements et
             * à partir du moment où notre composant est visible
             * toutes les modifications affectant ce composant doit etre faite par ce thread,
             * c'est là qu'intervient notre classe SwingUtilities. 
             */
//			if (SwingUtilities.isRightMouseButton(e)) {
//				System.out.println("Bouton droit pressé");
//				this.setBackground(Color.YELLOW);}
//		}
//
//		else{
//			this.setText("P");
//			this.setBackground(Color.BLACK);
//
//		}


	}
	/**
	 * Relâcher le bouton
	 *
	 */

	@Override
	public void mouseReleased(MouseEvent e) {

//		if(this.isEnabled()) {
//			if (SwingUtilities.isRightMouseButton(e)) {
//				System.out.println("Bouton droit relâché");
//				this.setBackground(Color.RED);
//			}
//			else {
//				this.setText("R");
//				this.setEnabled(false);
//				this.setBackground(Color.RED);
//
//			}
//		}

		
		if(this.isEnabled()) {
			if (SwingUtilities.isRightMouseButton(e)) {
				System.out.println("Bouton droit relâché");
				this.setBackground(Color.RED);
				this.setIcon(imgMine);
				// Ouvre toutes les cases
//				this.rows = rows;
//		     	this.cols = cols;
//				for(int i = 0; i < this.rows; i++) {
//		    		for(int j = 0; j < this.cols; j++) {
//		    			
//		   			    this.setIcon(imgMine);
//		        	}
//		    	}
			}
			else {
				this.setText("R");
				this.setEnabled(false);
				this.setBackground(Color.RED);

			}

	}
		}
	

	
	 
//	private void onLeftClick(Case c) {
//		// Ouvre toutes les cases
//		for(int i = 0; i < this.rows; i++) {
//    		for(int j = 0; j < this.cols; j++) {
//    			this.opened = true;
//    			
//   			    this.setIcon(imgMine);
//        	}
//    	}
//	}
	
	
	



}

