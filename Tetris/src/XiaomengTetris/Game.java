package XiaomengTetris;

//xiaomeng zhang
//xxz155330
//9.13.2016

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Game extends JFrame {

	private static final long serialVersionUID = 5422190100274313471L;

	public Game() {
		super();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setSize(800, 800);
		// this.getContentPane().add(stpanel, BorderLayout.CENTER);
		Cv cv = new Cv();
		add("Center", cv);
		this.setVisible(true);
	}
}
