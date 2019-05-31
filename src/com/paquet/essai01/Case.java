package com.paquet.essai01;



import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;



public class Case extends JButton {
	private static final long serialVersionUID = -8024370386357385856L;
	
	// Images utilis�es
	private final static ImageIcon imgDefault = loadImage("images/defaultCase.png");
	private final static ImageIcon imgFlag = loadImage("images/flag.png");
	private final static ImageIcon imgMine = loadImage("images/mine.png");
	private final static ImageIcon[] imgsOpened = loadImages();
		
	// Propri�t�s de la case
	private int value = 0;
	private int posX;
	private int posY;
	private boolean isMine = false;
	private boolean flaged = false;
	private boolean opened = false;
	
	// Chargement des images
	private static ImageIcon loadImage(String path) {
		try {
			return ResourceUtility.loadImage(path);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Chargement des images nombres
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
	
	// Constructeur
	public Case(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.setIcon(imgDefault);
    }

	// Getters
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
	
	// Marque la case comme mine
	public void setMine() {
		this.isMine = true;
	}

	// Marquer d'un drapeau la case
	public void flag() {
		this.flaged = true;
		this.setIcon(imgFlag);
	}
	
	// Enl�ve le drapeau d'une case
	public void unflag() {
		this.flaged = false;
		this.setIcon(imgDefault);
	}

	// Ouvre une case
	public void open() {
		this.opened = true;
		
		if(this.isMine) this.setIcon(imgMine);
		else this.setIcon(imgsOpened[this.value]);
	}
	
	// Affiche le nombre de mines adjacentes
	public void setValue(int i) {
		this.value = i;
	}
	
	// R�cup�re la position de la case
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
}