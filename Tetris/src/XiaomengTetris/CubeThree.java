package XiaomengTetris;

//xiaomeng zhang
//xxz155330
//9.13.2016

import java.awt.Color;

public class CubeThree extends BaseCube{
	public CubeThree(){
		super.color = Color.orange;
		int myPosition[][][] = {
				{
					
					{1,0,0,0},
					{1,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,1,1,0},
					{0,1,0,0},
					{0,1,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,0,0,0},
					{1,1,1,0},
					{0,0,1,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,1,0,0},
					{0,1,0,0},
					{1,1,0,0},
					{0,0,0,0},
					
				},
		};
		super.positions = myPosition;
	}

}
