package XiaomengTetris;

//xiaomeng zhang
//xxz155330
//9.13.2016

import java.awt.Color;

public class CubeFour extends BaseCube{
	public CubeFour(){
		super.color = Color.blue;
		int myPosition[][][] = {
				{
					
					{0,0,1,0},
					{1,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,1,0,0},
					{0,1,0,0},
					{0,1,1,0},
					{0,0,0,0},
					
				},
				
				{
					{0,0,0,0},
					{1,1,1,0},
					{1,0,0,0},
					{0,0,0,0},
					

					
				},
				
				{
					
					{1,1,0,0},
					{0,1,0,0},
					{0,1,0,0},
					{0,0,0,0},
					
				},
		};
		super.positions = myPosition;
	}

}
