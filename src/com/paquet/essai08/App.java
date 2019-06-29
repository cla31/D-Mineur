package com.paquet.essai08;

import java.awt.BorderLayout;
import java.awt.Color;

/**
 * essai pour g�n�rer smiley au clic gr�ce � la fonction Random + champ pour rentrer le nombre de smile
 * + essai meilleur agencement fen�tre
 * Bouton valider  fonctionnel
 */

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class App extends FrameForDemoMaker {
	
	/* Explications: l'objet Case g�re uniquement l'apparence et les �tats de la case
	 * l'object Game initialise un tableau de Case et g�re les r�gles du jeu, les �v�nements et les relations entre ses cases
	 * Nb: les cases n'utilisent que des images pour des raisons est�tiques
	 */
	
	// Propri�t�s fixes pour la partie
    private static final int ROWS = 15;
	private static final int COLUMNS = 15;
	private static final int MAXSMILE = 0;
	private Game game;
	private JTextField textField;
	static int nbreSaisi;
	private JTextField texte;
	

	
	
	// Constructeur
    public App() throws IOException{
        super("Jeu du d�mineur");
        setDefaultBounds(100, 100, 50* COLUMNS, 50 * ROWS);
    }
     
     
    @Override
    public void init(JFrame frame) {
    	Container cp = frame.getContentPane();
    	/**
    	 * premier contenant cp = border layout
    	 */
    	cp.setLayout(new BorderLayout(0, 0));
    	/**
    	 * cr�ation d'un premier panel
    	 */
    	JPanel panel_1 = new JPanel();
    	/**
    	 * on rajoute un panel au centre de cp
    	 */
    	cp.add(panel_1,BorderLayout.CENTER);
    	
    	/**
    	 * on param�tre un GridLayout (GridLayout du jeu) au panel_1:
    	 */
    	
    	panel_1.setLayout(new GridLayout(ROWS, COLUMNS));
    	
		//texte.setBounds(1000,1000,1000,500);
		//texte.updateUI();
		//pour effacer le contenu du JTextField
		//texte.setText("");
		
    	/**
    	 * Cr�ation d'un deuxi�me panel
    	 */
    	JPanel panel_2 = new JPanel();
    	/**
    	 * on rajoute un panel au nord de cp
    	 */
    	cp.add(panel_2,BorderLayout.NORTH);
    	panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		String txt = "A gauche, rentrez le nombre de smiles maximum souhait�s, puis appuyez sur -->";
    	//texte = new JTextField();
		//texte.setText(txt);
		JLabel label = new JLabel(txt);
		 
		panel_2.add(label);
		//texte.setBounds(1000,1000,1000,500);
		panel_2.setBackground(Color.white);
		
		
		//panel_2.add(texte);
		
		JButton btnNewButton = new JButton("Valider");
		panel_2.add(btnNewButton);
		//textField.setVisible(false);
		// Cr�ation du jeu
//    	game = new Game(ROWS, COLUMNS, MAXSMILE);
//		// Remplissage de la grille
//        for(int i = 0; i < ROWS; i++) {
//        	for(int j = 0; j < COLUMNS; j++) {
//            	panel_1.add(game.getCase(i, j));
//            }	
//        }
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				/**
				 * on caste le String( r�cup�r� dans le textField) en integer
				 *
				 * pour ensuite pouvoir faire le calcul:
				 * <code>nbresaisi=Integer.valueOf(le string � caster).intValue();</code>
				 * */
				nbreSaisi = Integer.valueOf(textField.getText()).intValue();
				System.out.println(nbreSaisi);
				int MAXSMILE= nbreSaisi;
				
		      
		       
				
				/**
				 *  Cr�ation du jeu
				 */
				game = new Game(ROWS, COLUMNS, MAXSMILE);
				
				/**
				 *  Remplissage de la grille
				 */
		        for(int i = 0; i < ROWS; i++) {
		        	for(int j = 0; j < COLUMNS; j++) {
		        		
		            	panel_1.add(game.getCase(i, j));
		            	label.setVisible(false);
		            }	
		        }
		        
		        panel_1.updateUI();
				
			}});
		
        
   
//    public static textIntroduction(boolean)
//   {      if (textIntro = true)    	
//	   texte = new JTextField();
//		texte.setText("   bonkf");
//		panel_1.add(texte);
//		else
//			return false}
		
		
		
    }
    
    public static void main(String[] args) throws IOException {
        App example = new App();
        SwingUtilities.invokeLater(example);
    }
}