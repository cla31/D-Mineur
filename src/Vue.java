import java.awt.Container;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class Vue extends FrameForDemoMaker {
	private static final int COLUMN_COUNT = 10;
	private static final int ROW_COUNT = 10;

	public Vue(String title) throws IOException {
		super("Le jeu du Démineur");
		setDefaultBounds(100, 100, 900, 600);
	}

	public static void main(String[] args) throws IOException {
		Vue example = new Vue("GridLayout");
		SwingUtilities.invokeLater(example);

	}

	@Override
	public void init(JFrame frame) { //mise en place du GridLayout
		// TODO Auto-generated method stub

		Container cp = frame.getContentPane();
		cp.setLayout(new GridLayout(ROW_COUNT, COLUMN_COUNT));
		
		for (int i = 0; i < ROW_COUNT; i++) {

			for (int j = 0; j < COLUMN_COUNT; j++) {
				cp.add(createButton());

			}

		}
		

	}
	
	
	//méthode pour créer un bouton:
	public JComponent createButton() {
        JButton button = new JButton();
        return button;
    }
	
	
	

}
