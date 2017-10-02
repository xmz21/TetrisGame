//xiaomeng zhang
//xxz155330
//9.13.2016

package XiaomengTetris;

import java.awt.Color;

public class BaseCube {
	protected int[][][] positions;
	private int currentPosition = 0;
	private int rotateStatus = 0;

	public int getRotateStatus() {
		// System.out.println("status: " + rotateStatus);
		return rotateStatus;

	}

	public void setRotateStatus(int rotateStatus) {
		this.rotateStatus = rotateStatus;
	}

	protected Color color;

	public void rotationDown() {
		currentPosition++;
		rotateStatus++;
		if (currentPosition == 4) {
			// System.out.println("current pos:  " + currentPosition);
			currentPosition = 0;
			rotateStatus = 0;
		}

	}

	public void rotationUp() {

		currentPosition--;
		rotateStatus--;
		if (currentPosition == -1) {
			currentPosition = 3;
			rotateStatus = 3;
		}
	}

	public Color getColor() {
		return color;
	}

	public int[][] getCurrentPosition() {
		return positions[currentPosition];
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;

	}

}
