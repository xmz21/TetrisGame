package XiaomengTetris;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Polygon;

import javax.swing.JOptionPane;

public class Cv extends Canvas implements MouseListener, MouseMotionListener {
	Setting st = new Setting();
	protected float rWidth = st.getMyWidth() + 2;
	protected float rHeight = st.getMyHeight() + 2;
	protected int scoreFactor = st.getScoreFactor();
	protected int score = 0;
	protected int levelDifficulty = st.getLevelDifficult();
	protected int level = 1;
	protected float speedFactor = st.getSpeedFactor();
	protected int numberOfRow = 0;
	protected int totalRowRemoved = 0;
	protected float fallingSpeed = 5.0F;
	protected int currentLevel = 1;
	protected String extraShape = st.getExtraShape();

	private static final long serialVersionUID = 1146805453590160893L;

	public static float x0, y0, pixelSize;

	int centerX, centerY;
	int maxX, maxY, minMaxXY, xCenter, yCenter;
	int buttonRatio = 0;
	boolean Paused = false;
	int gameStatus;
	int currentCol = (int) (rWidth - 2) / 2 - 1;
	int currentRow = 2;
	int numbersOfCube = 1;
	int currentType = 0;
	int cubeSize = 0;

	Thread myThread;
	boolean isThreadStart = false;
	boolean isAtBottom = false;
	boolean isRotatable = true;
	boolean changeShape = false;

	int typeNumber;
	int typeNumberNext;

	// int typeNumber = 2;
	// int typeNumberNext = 2;

	BaseCube bc;
	MainScreenGrid msg;
	BaseCube bcNext;

	public BaseCube setCube(int cubeType) {
		switch (cubeType) {
		case 1:
			CubeOne c1 = new CubeOne();
			return c1;
		case 2:
			CubeTwo c2 = new CubeTwo();
			return c2;
		case 3:
			CubeThree c3 = new CubeThree();
			return c3;
		case 4:
			CubeFour c4 = new CubeFour();
			return c4;
		case 5:
			CubeFive c5 = new CubeFive();
			return c5;
		case 6:
			CubeSix c6 = new CubeSix();
			return c6;
		case 7:
			CubeSeven c7 = new CubeSeven();
			return c7;
		case 8:
			ExtraShapeOne c8 = new ExtraShapeOne();
			return c8;
		case 9:
			ExtraShapeTwo c9 = new ExtraShapeTwo();
			return c9;
		case 10:
			ExtraShapeThree c10 = new ExtraShapeThree();
			return c10;
		case 11:
			ExtraShapeFour c11 = new ExtraShapeFour();
			return c11;
		}
		return null;
	}

	private static int getRandomNumber(ArrayList<Integer> list) {
		return list.get((new Random()).nextInt(list.size()));
	}

	public void startGame() {
		// System.out.println("extra shape: " + extraShape);
		ArrayList<Integer> shapeList = new ArrayList<>();
		for (int i = 1; i < 8; i++) {
			shapeList.add(i);
		}
		String[] ary = extraShape.split(",");
		if (ary[0].equalsIgnoreCase("1")) {
			shapeList.add(8);
		}
		if (ary[1].equalsIgnoreCase("1")) {
			shapeList.add(9);
		}
		if (ary[2].equalsIgnoreCase("1")) {
			shapeList.add(10);
		}
		if (ary[3].equalsIgnoreCase("1")) {
			shapeList.add(11);
		}

		typeNumber = getRandomNumber(shapeList);
		typeNumberNext = getRandomNumber(shapeList);

		fallingSpeed = fallingSpeed * speedFactor;
		bcNext = setCube(typeNumberNext);
		bc = setCube(typeNumber);
		currentType = typeNumber;
		msg = new MainScreenGrid();
		msg.initGrid();
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(new MouseAdapter() {
			public void mouseWheelMoved(MouseWheelEvent evt2) {
				rotateCube(evt2);
			}
		});

		myThread = new Thread() {

			public void run() {

				while (true) {
					try {
						Thread.sleep((int) (1000 / fallingSpeed));
						// System.out.println("falling speed :" + fallingSpeed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// if cubes can't keep falling
					if (msg.placeCube(currentRow - 1, currentCol - 1, bc)) {
						int row = (int) rHeight - 3;
						while (row > 1 && !msg.isEmptyRow(row)) {
							if (msg.isFullRow(row)) {
								numberOfRow++;
								totalRowRemoved++;
								// removeRowAnimation(row, rowRemoved * 100);
								msg.removeRow(row);
								repaint();
								try {
									Thread.sleep(80);
								} catch (InterruptedException E) {
								}
								row++;
								score = score + level * scoreFactor;
							}
							row--;
						}
						currentLevel = level;
						numbersOfCube++;
						currentRow = 2;
						currentCol = (int) (rWidth - 2) / 2 - 1;
						isAtBottom = true;

						bc = setCube(typeNumberNext);
						currentType = typeNumberNext;
						typeNumberNext = getRandomNumber(shapeList);
						// typeNumberNext = 2;

						if (numberOfRow >= levelDifficulty) {

							level++;
							numberOfRow = numberOfRow - levelDifficulty;
							fallingSpeed = (fallingSpeed * (1 + speedFactor
									* level));
							System.out.println("number of row" + numberOfRow);
						}

					} else {
						currentRow++;
						isAtBottom = false;

						if (changeShape == true) {

							// System.out.println("in!!!!!!!!");
							int typeTmp = currentType + 1;
							if (currentType == 11) {
								typeTmp = 1;
							}
							currentType = typeTmp;
							score = score - level * scoreFactor;
							bc = setCube(currentType);
							changeShape = false;

						}

					}

					if (msg.isGameOver()) {
						System.out.println("game over!!!");
						JOptionPane.showMessageDialog(null, "Game Over!!!");
						myThread.stop();

					}

					repaint();
				}
			}
		};
		myThread.start();

	}

	public void initgr() {

		Dimension d = getSize();
		int maxX = d.width - 1, maxY = d.height - 1;
		pixelSize = Math.max(rWidth / maxX, rHeight / maxY);
		centerX = maxX / 2;
		centerY = maxY / 2;
		buttonRatio = Math.min(d.width, d.height) / 33;

	}

	int iX(float x) {
		return Math.round(centerX + x / pixelSize);
	}

	int iY(float y) {
		return Math.round(centerY - y / pixelSize);
	}

	float fx(int X) {
		return (X - centerX) * pixelSize;
	}

	float fy(int Y) {
		return (centerY - Y) * pixelSize;
	}

	public void paint(Graphics g) {
		cubeSize = (iX(-rWidth + 1 + 1) - iX(-rWidth + 1));
		// System.out.println("size:" + cubeSize);
		initgr();

		if (!isThreadStart) {
			startGame();
			isThreadStart = true;
		}
		// game main view
		int left = iX(-rWidth + 1), right = iX(-1), bottom = iY(-rHeight / 2 + 1), top = iY(rHeight / 2 - 1);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g.drawRect(left, top, right - left, bottom - top);

		paintGrid(g);

		// next shape
		int nextShapeLeft = iX(0), nextShapeRight = iX(7), nextShapeBottom = iY(rHeight / 2 - 5), nextShapeTop = iY(rHeight / 2 - 1);
		g.drawRect(nextShapeLeft, nextShapeTop, nextShapeRight - nextShapeLeft,
				nextShapeBottom - nextShapeTop);

		if (numbersOfCube == 1) {
			setCube(typeNumber);
			paintNext(g, bcNext.getColor(), bcNext.getCurrentPosition());
		}

		if (isAtBottom == false && numbersOfCube != 1) {
			bcNext = setCube(typeNumberNext);
			paintNext(g, bcNext.getColor(), bcNext.getCurrentPosition());
		}

		// next shape component
		// paintNext(g, bc.getColor(), bc.getCurrentPosition());

		// score, level, lines

		g.setFont(new Font("Sogoe UI", Font.BOLD, buttonRatio));
		g.drawString("Level:", iX(2), iY(rHeight / 2 - 9));
		g.drawString(Integer.toString(level), iX(5), iY(rHeight / 2 - 9));
		g.drawString("Lines:", iX(2), iY(rHeight / 2 - 11));
		g.drawString(Integer.toString(totalRowRemoved), iX(5),
				iY(rHeight / 2 - 11));
		g.drawString("Score:", iX(2), iY(rHeight / 2 - 13));
		g.drawString(Integer.toString(score), iX(5), iY(rHeight / 2 - 13));

		// quit button
		int quitLeft = iX(1), quitRight = iX(5), quitBottom = iY(-rHeight / 2 + 1), quitTop = iY(-rHeight / 2 + 3);
		g.drawRect(quitLeft, quitTop, quitRight - quitLeft, quitBottom
				- quitTop);
		g.setFont(new Font("Sogoe UI", Font.BOLD, buttonRatio));
		g.drawString("QUIT", iX(2), iY(-rHeight / 2 + 2));

		paintMyself(g, bc.getColor(), bc.getCurrentPosition());

	}

	public void paintGrid(Graphics g) {

		for (int i = 0; i < rHeight; i++) {
			for (int j = 0; j < rWidth; j++) {
				if (msg.getGrid()[i][j] == 1) {
					int col = j - 1;
					int row = i + 1;
					// System.out.println("gride[i][j]:" + i + " " + j );
					g.setColor(Color.gray);
					g.fillRect(iX(-rWidth + col), iY(rHeight / 2 - row),
							iX(-rWidth + col + 1) - iX(-rWidth + col),
							iY(rHeight / 2 - (row + 1)) - iY(rHeight / 2 - row));
					g.setColor(Color.black);
					g.drawRect(iX(-rWidth + col), iY(rHeight / 2 - row),
							iX(-rWidth + col + 1) - iX(-rWidth + col),
							iY(rHeight / 2 - (row + 1)) - iY(rHeight / 2 - row));

				}
			}
		}
	}

	private boolean inSidePoly(int col, int row, int mousePosX, int mousePosY) {
		if (mousePosX > iX(-rWidth + col) && mousePosX < iX(-rWidth + col + 3)
				&& mousePosY > iY(rHeight / 2 - row)
				&& mousePosY < iY(rHeight / 2 - (row + 3))) {
			return true;

		}
		return false;
	}

	public void paintMyself(Graphics g, Color color, int position[][]) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (position[i][j] == 1) {
					int col = j + currentCol;
					int row = i + currentRow;
					g.setColor(color);
					g.fillRect(iX(-rWidth + col), iY(rHeight / 2 - row),
							iX(-rWidth + col + 1) - iX(-rWidth + col),
							iY(rHeight / 2 - (row + 1)) - iY(rHeight / 2 - row));
					g.setColor(Color.black);
					g.drawRect(iX(-rWidth + col), iY(rHeight / 2 - row),
							iX(-rWidth + col + 1) - iX(-rWidth + col),
							iY(rHeight / 2 - (row + 1)) - iY(rHeight / 2 - row));
				}

			}
		}
	}

	public void paintNext(Graphics g, Color color, int position[][]) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (position[i][j] == 1) {
					int col = j + (int) rWidth + 2, row = i + 2;
					g.setColor(color);
					g.fillRect(iX(-rWidth + col), iY(rHeight / 2 - row),
							iX(-rWidth + col + 1) - iX(-rWidth + col),
							iY(rHeight / 2 - (row + 1)) - iY(rHeight / 2 - row));
					g.setColor(Color.black);
					g.drawRect(iX(-rWidth + col), iY(rHeight / 2 - row),
							iX(-rWidth + col + 1) - iX(-rWidth + col),
							iY(rHeight / 2 - (row + 1)) - iY(rHeight / 2 - row));
				}
			}

		}

	}

	public void drawPauseButton(Graphics g) {

		int pauseLeft = iX(-rWidth + 4), pauseRight = iX(-rWidth + 8), pauseBottom = iY(-1), pauseTop = iY(1);

		g.drawRect(pauseLeft, pauseTop, pauseRight - pauseLeft, pauseBottom
				- pauseTop);
		g.setFont(new Font("Sogoe UI", Font.BOLD, buttonRatio));
		g.drawString("PAUSE", iX(-rWidth + 5), iY(0));

	}

	private void rotateCube(java.awt.event.MouseWheelEvent evt2) {
		isRotatable = true;
		int wheel = evt2.MOUSE_WHEEL;
		int left = iX(-rWidth + 1), right = iX(-1), bottom = iY(-rHeight / 2 + 1), top = iY(rHeight / 2 - 1);
		if (wheel == evt2.MOUSE_WHEEL) {
			if ((getMousePosition().getX() < left
					|| getMousePosition().getX() > right
					|| getMousePosition().getY() < top || getMousePosition()
					.getY() > bottom) && Paused == false) {
				if (!msg.isMoveableRight(currentType, bc, currentRow,
						currentCol)
						|| !msg.isMoveableLeft(currentType, bc, currentRow,
								currentCol)) {
					if ((bc.getRotateStatus() == 1 || bc.getRotateStatus() == 3)) {
						isRotatable = false;
					}
				}

				if (isRotatable == true) {
					// System.out.println("current status: " +
					// bc.getRotateStatus());
					// bc.rotationDown();
					int notches = evt2.getWheelRotation();
					if (notches > 0) {
						bc.rotationUp();
						// System.out.println("current position:  "
						// + bc.getRotateStatus());
					} else {
						bc.rotationDown();
						// System.out.println("down!!!!!");
					}
				}

			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println("x:" + e.getX());
		// System.out.println("y:" + e.getY());
		if (inSidePoly(currentCol, currentRow, e.getX(), e.getY())) {
			System.out.println("in!!!!!!!!");
			changeShape = true;
		}

		int left = iX(-rWidth + 1), right = iX(-1), bottom = iY(-rHeight / 2 + 1), top = iY(rHeight / 2 - 1);
		int click = e.getButton();
		float mousePosX = (float) getMousePosition().getX();
		float mousePosY = (float) getMousePosition().getY();
		if (getMousePosition().getX() > iX(1)
				&& getMousePosition().getX() < iX(5)
				&& getMousePosition().getY() > iY(-rHeight / 2 + 3)
				&& getMousePosition().getY() < iY(-rHeight / 2 + 1)) {
			System.out.println("QUIT click!!!");
			System.exit(0);
		}

		// System.out.println("typenum:  " + currentType);

		if ((getMousePosition().getX() < left
				|| getMousePosition().getX() > right
				|| getMousePosition().getY() < top || getMousePosition().getY() > bottom)
				&& click == MouseEvent.BUTTON3 && Paused == false) {
			if (msg.isMoveableRight(currentType, bc, currentRow, currentCol)) {
				currentCol++;
				// System.out.println("current col: " + currentCol);
			}

		}

		if ((getMousePosition().getX() < left
				|| getMousePosition().getX() > right
				|| getMousePosition().getY() < top || getMousePosition().getY() > bottom)
				&& click == MouseEvent.BUTTON1 && Paused == false) {

			if (msg.isMoveableLeft(currentType, bc, currentRow, currentCol)) {
				currentCol--;
				// System.out.println("current col: " + currentCol);
			}

		}

	}

	private boolean showButton = false;

	@Override
	public void mouseMoved(MouseEvent e) {
		myThread.resume();
		int left = iX(-rWidth + 1), right = iX(-1), bottom = iY(-rHeight / 2 + 1), top = iY(rHeight / 2 - 1);
		// TODO Auto-generated method stub
		if (e.getX() > left && e.getX() < right && e.getY() > top
				&& e.getY() < bottom) {
			Paused = true;
			// System.out.println("curren row: " + currentRow +
			// ".  curren col: "+ currentCol);
			drawPauseButton(getGraphics());
			showButton = true;
			myThread.suspend();
		} else if (showButton) {
			repaint();
			Paused = false;
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		// int click = e.getButton();

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
