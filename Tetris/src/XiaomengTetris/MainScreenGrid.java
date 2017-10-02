package XiaomengTetris;

import java.awt.Canvas;
import java.awt.Color;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.xml.bind.annotation.W3CDomHandler;

public class MainScreenGrid {

	Setting st = new Setting();
	protected static int width = 10;
	protected static int height = 20;
	protected static int startCol = (int) ((width / 2) - 1);

	protected static int[][] grid = new int[100][100];

	public static int[][] getGrid() {
		return grid;

	}

	public static void setGrid(int[][] grid) {
		MainScreenGrid.grid = grid;
	}

	protected void initGrid() {
		setWindow((int) st.myWidth);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width + 1; j++) {
				grid[i][j] = 0;
			}
		}
	}

	protected boolean isMoveableRight(int type, BaseCube bc, int row, int col) {
		int[][] cp = bc.getCurrentPosition();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ((grid[row + i][col + j + 2] == 1 && cp[i][j] == 1)) {
					// System.out.println("cant move!!:" + i + ", j:" + j);
					return false;

				}
			}
		}

		if (col < width - 3 && type == 1
				&& (bc.getRotateStatus() == 0 || bc.getRotateStatus() == 2)) {
			return true;
		}

		if (col < width - 2 && type == 1 && (bc.getRotateStatus() == 1)) {
			return true;
		}

		if (col < width - 1 && type == 1 && (bc.getRotateStatus() == 3)) {
			return true;
		}

		if (col < width - 2 && (type == 2 || type == 8 || type == 11)) {
			return true;
		}

		if (col < width - 2
				&& (type == 3 || type == 4 || type == 5 || type == 7
						|| type == 6 || type == 9)
				&& (bc.getRotateStatus() == 0 || bc.getRotateStatus() == 1 || bc
						.getRotateStatus() == 2)) {
			return true;
		}
		if (col < width - 1
				&& (type == 3 || type == 4 || type == 5 || type == 7
						|| type == 6 || type == 9)
				&& (bc.getRotateStatus() == 3)) {
			return true;
		}

		if (col < width - 2 && (type == 10)
				&& (bc.getRotateStatus() == 0 || bc.getRotateStatus() == 2)) {
			return true;
		}

		if (col < width - 1 && type == 10
				&& (bc.getRotateStatus() == 3 || bc.getRotateStatus() == 1)) {
			return true;
		}

		return false;

	}

	protected boolean isMoveableLeft(int type, BaseCube bc, int row, int col) {

		int[][] cp = bc.getCurrentPosition();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// System.out.println("curren row:" + row + ".  curren col:" +
				// col);
				if ((grid[row + i][col + j] == 1 && cp[i][j] == 1)) {
					System.out.println("cant move!!:" + i + ", j:" + j);
					return false;
				}
			}
		}

		if (col > 1 && type == 1
				&& (bc.getRotateStatus() == 0 || bc.getRotateStatus() == 2)) {
			return true;
		}

		if (col > -1 && type == 1 && (bc.getRotateStatus() == 1)) {
			return true;
		}

		if (col > 0 && type == 1 && (bc.getRotateStatus() == 3)) {
			return true;
		}

		if (col > 0 && (type == 2 || type == 8 || type == 11)) {
			return true;
		}

		if (col > 1
				&& (type == 3 || type == 4 || type == 5 || type == 7
						|| type == 6 || type == 9)
				&& (bc.getRotateStatus() == 0 || bc.getRotateStatus() == 2 || bc
						.getRotateStatus() == 3)) {
			return true;
		}
		if (col > 0
				&& (type == 3 || type == 4 || type == 5 || type == 7
						|| type == 6 || type == 9)
				&& (bc.getRotateStatus() == 1)) {
			return true;
		}
		if (col > 1 && (type == 10)
				&& (bc.getRotateStatus() == 0 || bc.getRotateStatus() == 2)) {
			return true;
		}

		if (col > 0 && type == 10
				&& (bc.getRotateStatus() == 3 || bc.getRotateStatus() == 1)) {
			return true;
		}

		return false;

	}

	protected boolean isGameOver() {
		for (int i = 0; i < width; i++) {
			if (grid[2][i] == 1) {
				return true;
			}
		}
		return false;
	}

	protected boolean placeCube(int row, int col, BaseCube baseCube) {
		// int[][] tempGrid = new int [19][9];
		int[][] cp = baseCube.getCurrentPosition();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// base cube row 4 = 0 row 3 = 0
				if (cp[2][j] == 0 && cp[3][j] == 0) {
					if (row + 1 + i == height + 2
							|| (grid[row + 1 + i][col + j + 2] == 1 && cp[i][j] == 1)) {
						for (int u = 0; u < 4; u++) {
							for (int v = 0; v < 4; v++) {
								if (grid[row + u][col + v + 2] != 1)
									grid[row + u][col + v + 2] = cp[u][v];
							}
						}
						return true;
					}

				}
				// base cube row 4 = 0
				if (cp[3][j] == 0 && cp[2][j] != 0) {
					if (row + 1 + i == height + 1
							|| (grid[row + 1 + i][col + j + 2] == 1 && cp[i][j] == 1)) {
						for (int u = 0; u < 4; u++) {
							for (int v = 0; v < 4; v++) {
								if (grid[row + u][col + v + 2] != 1)
									grid[row + u][col + v + 2] = cp[u][v];

							}
						}
						return true;
					}

				}

				// base cube row 4 = 1
				if (cp[3][j] != 0) {
					if (row + 1 + i == height
							|| (grid[row + 1 + i][col + j + 2] == 1 && cp[i][j] == 1)) {
						for (int u = 0; u < 4; u++) {
							for (int v = 0; v < 4; v++) {
								if (grid[row + u][col + v + 2] != 1)
									grid[row + u][col + v + 2] = cp[u][v];

							}
						}
						return true;
					}

				}

			}
		}
		return false;
	}

	public void setWindow(int mywidth) {
		if (mywidth == 10) {
			width = 10;
			height = 20;
		} else {
			width = 15;
			height = 30;
		}
	}

	public static boolean isFullRow(int row) {
		for (int col = 2; col < width + 2; col++) {
			if (grid[row][col] == 0) {
				return false;
			}
		}
		return true;
	}

	public static void removeRow(int row) {
		while (!isEmptyRow(row - 1)) {
			rowReplace(row);
			row--;
		}
		emptyRow(row, 0);
	}

	public static void emptyRow(int row, int V) {
		for (int col = 2; col < width + 2; col++) {
			grid[row][col] = V;
		}
	}

	private static void rowReplace(int row) {
		for (int col = 2; col < width + 2; col++) {
			grid[row][col] = grid[row - 1][col];
		}
	}

	public static boolean isEmptyRow(int row) {
		for (int col = 2; col < width + 2; col++) {
			if (grid[row][col] != 0) {
				return false;
			}
		}
		return true;
	}

}
