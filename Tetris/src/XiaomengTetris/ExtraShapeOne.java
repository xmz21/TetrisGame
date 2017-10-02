package XiaomengTetris;

import java.awt.Color;

public class ExtraShapeOne extends BaseCube{
	public ExtraShapeOne(){
		super.color = Color.MAGENTA;
		int myPosition[][][] = {
				{
					{0,0,1,0},
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				
				{
					{0,1,0,0},
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				
				{
					{0,1,1,0},
					{0,1,0,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				
				{
					{0,1,1,0},
					{0,0,1,0},
					{0,0,0,0},
					{0,0,0,0},
				},
		};
		super.positions = myPosition;
	}

}
