package XiaomengTetris;

//xiaomeng zhang
//xxz155330
//9.13.2016

import java.awt.Color;

public class CubeOne extends BaseCube{
	public CubeOne(){
		super.color = Color.green;
		int myPosition[][][] = {
				{
					{0,0,0,0},
					{1,1,1,1},
					{0,0,0,0},
					{0,0,0,0},
				},
				
				{
					{0,0,1,0},
					{0,0,1,0},
					{0,0,1,0},
					{0,0,1,0},
				},
				
				{
					{0,0,0,0},
					{0,0,0,0},
					{1,1,1,1},
					{0,0,0,0},
				},
				
				{
					{0,1,0,0},
					{0,1,0,0},
					{0,1,0,0},
					{0,1,0,0},
				},
		};
		super.positions = myPosition;
	}

}
