package com.paquet.essai04;

/**
 * Essai pour g�n�rer des smiley sur toute la grille apr�s avoir cliqu� sur l'une des cases.
 */

/**
 * Classe qui g�re uniquement l'apparence et l'�tat de la case
 */

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;



public class Case extends JButton {
	private static final long serialVersionUID = -8024370386357385856L;
	
	/**
	 *  Images utilis�es
	 */
	private final static ImageIcon imgDefault = loadImage("images/defaultCase.png");
	private final static ImageIcon imgSmile = loadImage("images/smiley.png");
	
		
	/**
	 *  Propri�t�s de la case
	 */
	private int value = 0;
	private boolean isMine = false;
	
	
	/**
	 *  Chargement des images
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
	

	
	/**
	 * Constructeur
	 * @param x
	 * @param y
	 */
	public Case(int x, int y) {

		this.setIcon(imgDefault);
    }

	
	/**
	 *  Ouvre une case
	 */
	public void open() {
		
		
		 this.setIcon(imgSmile);
		
	}
	
	
	
}