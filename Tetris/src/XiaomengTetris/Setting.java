package XiaomengTetris;

import javax.imageio.ImageIO;
import javax.jws.Oneway;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.JCheckBox;

public class Setting extends JFrame implements ActionListener {
	private JButton playButton;
	private JPanel panel;
	private boolean playGame = false;

	public static int scoreFactor = 10;
	public static int levelDifficult = 20;
	public static float speedFactor = 0.2F;
	public static float myWidth = 10.0F;
	public static float myHeight = 20.0F;
	public static String extraShape = null;

	public static String getExtraShape() {
		return extraShape;
	}

	public static void setExtraShape(String extraShape) {
		Setting.extraShape = extraShape;
	}

	private static String one = "0";
	private static String two = "0";
	private static String three = "0";
	private static String four = "0";
	private ImageIcon icon;

	public static int getScoreFactor() {
		return scoreFactor;
	}

	public static void setScoreFactor(int scoreFactor) {
		Setting.scoreFactor = scoreFactor;
	}

	public static int getLevelDifficult() {
		return levelDifficult;
	}

	public static void setLevelDifficult(int levelDifficult) {
		Setting.levelDifficult = levelDifficult;
	}

	public static float getSpeedFactor() {
		return speedFactor;
	}

	public static void setSpeedFactor(float speedFactor) {
		Setting.speedFactor = speedFactor;
	}

	public static float getMyWidth() {
		return myWidth;
	}

	public static void setMyWidth(float myWidth) {
		Setting.myWidth = myWidth;
	}

	public static float getMyHeight() {
		return myHeight;
	}

	public static void setMyHeight(float myHeight) {
		Setting.myHeight = myHeight;
	}

	public Setting() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setSize(800, 800);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Score Factor (1-10)");
		lblNewLabel.setBounds(220, 110, 132, 16);
		panel.add(lblNewLabel);

		JLabel txtScoreFactor = new JLabel("Level Difficlty(20-50)");
		txtScoreFactor.setBounds(211, 171, 141, 16);
		panel.add(txtScoreFactor);

		JLabel lblSpeedFactor = new JLabel("Speed Factor(0.1-1)");
		lblSpeedFactor.setBounds(220, 234, 125, 16);
		panel.add(lblSpeedFactor);

		playButton = new JButton("Play!");
		playButton.setBounds(670, 692, 75, 29);
		panel.add(playButton);

		String[] scoreArray = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10" };
		JComboBox scoreComboBox = new JComboBox(scoreArray);
		scoreComboBox.setSelectedIndex(9);
		scoreComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String score = (String) scoreComboBox.getSelectedItem();
				scoreFactor = Integer.parseInt(score);
				System.out.println("score:!!!" + scoreFactor);

			}
		});

		scoreComboBox.setBounds(383, 106, 102, 27);
		panel.add(scoreComboBox);

		String[] levelArray = { "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31", "32", "33", "34", "35", "36", "37",
				"38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
				"48", "49", "50" };
		JComboBox levelComboBox = new JComboBox(levelArray);
		levelComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String levelDifficultytmp = (String) levelComboBox
						.getSelectedItem();
				levelDifficult = Integer.parseInt(levelDifficultytmp);
				System.out.println("difficulty:!!!" + levelDifficult);

			}
		});
		levelComboBox.setBounds(383, 167, 102, 27);
		panel.add(levelComboBox);

		String[] speedArray = { "0.1", "0.2", "0.3", "0.4", "0.5", "0.6",
				"0.7", "0.8", "0.9", "1" };
		JComboBox speedComboBox = new JComboBox(speedArray);
		speedComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String speed = (String) speedComboBox.getSelectedItem();
				speedFactor = Float.parseFloat(speed);
				System.out.println("speed:!!!" + speedFactor);

			}
		});
		speedComboBox.setBounds(383, 230, 102, 27);
		panel.add(speedComboBox);

		JLabel windowSize = new JLabel("Window Size");
		windowSize.setBounds(262, 298, 90, 16);
		panel.add(windowSize);

		String[] windowArray = { "10*20 Grids", "15*30 Grids" };
		JComboBox windowSizeComboBox = new JComboBox(windowArray);
		windowSizeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String windowSize = (String) windowSizeComboBox
						.getSelectedItem();
				if (windowSize.equalsIgnoreCase("10*20 Grids")) {
					myWidth = 10.0F;
					myHeight = 20.0F;
					System.out.println("window:!!!" + myWidth + myHeight);
				} else {
					myWidth = 15.0F;
					myHeight = 30.0F;
					System.out.println("window:!!!" + myWidth + myHeight);
				}

			}
		});
		windowSizeComboBox.setBounds(383, 294, 102, 27);
		panel.add(windowSizeComboBox);

		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extraShape = one + "," + two + "," + three + "," + four;
				System.out.println("shap: " + extraShape);
				Game game = new Game();
				setVisible(false);
				game.setVisible(true);

			}
		});

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("src/cube.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		icon = new ImageIcon(myPicture);
		JLabel picLabel = new JLabel(icon);
		picLabel.setBounds(95, 444, 669, 139);
		panel.add(picLabel);

		JLabel lblChooseExtraShape = new JLabel("Choose Extra Shape");
		lblChooseExtraShape.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblChooseExtraShape.setBounds(275, 378, 210, 67);
		panel.add(lblChooseExtraShape);

		JCheckBox shapeoneCheckBox = new JCheckBox("");
		shapeoneCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean check = shapeoneCheckBox.isSelected();
				if (check) {
					one = "1";
				} else {
					one = "0";
				}
				System.out.println("!!!choose: " + one);

			}
		});
		shapeoneCheckBox.setBounds(144, 616, 35, 23);
		panel.add(shapeoneCheckBox);

		JCheckBox shapetwoCheckBox = new JCheckBox("");
		shapetwoCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean check = shapetwoCheckBox.isSelected();
				if (check) {
					two = "1";
				} else {
					two = "0";
				}
				System.out.println("!!!choose: " + two);

			}
		});
		shapetwoCheckBox.setBounds(317, 616, 35, 23);
		panel.add(shapetwoCheckBox);

		JCheckBox shapethreeCheckBox = new JCheckBox("");
		shapethreeCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean check = shapethreeCheckBox.isSelected();
				if (check) {
					three = "1";
				} else {
					three = "0";
				}
				System.out.println("!!!choose: " + three);

			}
		});
		shapethreeCheckBox.setBounds(495, 616, 35, 23);
		panel.add(shapethreeCheckBox);

		JCheckBox shapefourCheckBox = new JCheckBox("");
		shapefourCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean check = shapefourCheckBox.isSelected();
				if (check) {
					four = "1";
				} else {
					four = "0";
				}
				System.out.println("!!!choose: " + four);

			}
		});
		shapefourCheckBox.setBounds(662, 616, 35, 23);
		panel.add(shapefourCheckBox);

		// JLabel lblNewLabel_1 = new JLabel("New label");
		// lblNewLabel_1.setBounds(94, 366, 569, 139);
		// panel.add(lblNewLabel_1);

	}

	public void display() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
