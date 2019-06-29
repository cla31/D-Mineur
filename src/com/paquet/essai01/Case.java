package com.paquet.essai01;

/**
 * gère uniquement l'apparence et l'état de la case
 */

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;



public class Case extends JButton {
	private static final long serialVersionUID = -8024370386357385856L;
	
	/**
	 *  Images utilisées
	 */
	private final static ImageIcon imgDefault = loadImage("images/defaultCase.png");
	private final static ImageIcon imgFlag = loadImage("images/flag.png");
	private final static ImageIcon imgMine = loadImage("images/mine.png");
	private final static ImageIcon[] imgsOpened = loadImages();
		
	/**
	 *  Propriétés de la case
	 */
	private int value = 0;
	private int posX;
	private int posY;
	private boolean isMine = false;
	private boolean flaged = false;
	private boolean opened = false;
	
	/**
	 *  Chargement des images
	 * @param path
	 * @return null
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
	 *  Chargement des images nombres
	 * @return null
	 */
	private static ImageIcon[] loadImages() {
		ImageIcon[] images = new ImageIcon[9];
		for(int i = 0; i <= 8; i++) {
			try {
				images[i] = ResourceUtility.loadImage("images/openedCase" + i + ".png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return images;
	}
	
	/**
	 *  Constructeur
	 * @param x
	 * @param y
	 */
	public Case(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.setIcon(imgDefault);
    }

	/**
	 *  Getters
	 * @return l'objet lui-même + ses booléens flaged (si c'est un drapeau), 
	 * opened (si la case est ouverte), isMine (si la case est une mine)
	 * et la value qui correspond au nombre de mines adjacentes à la case.
	 */
	public boolean isFlaged() {
		return this.flaged;
	}
	
	public boolean isOpened() {
		return this.opened;
	}
	public boolean isMine() {
		return this.isMine;
	}
	public int getValue() {
		return this.value;
	}
	
	/**
	 *  Marque la case comme mine
	 */
	public void setMine() {
		this.isMine = true;
	}

	/**
	 *  Marquer d'un drapeau la case
	 */
	public void flag() {
		this.flaged = true;
		this.setIcon(imgFlag);
	}
	
	/**
	 *  Enlève le drapeau d'une case
	 */
	public void unflag() {
		this.flaged = false;
		this.setIcon(imgDefault);
	}

	/**
	 *  Ouvre une case
	 */
	public void open() {
		this.opened = true;
		
		if(this.isMine) this.setIcon(imgMine);
		else this.setIcon(imgsOpened[this.value]);
	}
	
	/**
	 *  Affiche le nombre de mines adjacentes
	 * @param i
	 */
	public void setValue(int i) {
		this.value = i;
	}
	
	/**
	 *  Récupère la position de la case
	 * @return la position X (axe abscisses ou lignes) et Y (ordonnées ou colonnes)
	 */
	
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
}