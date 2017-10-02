//xiaomeng zhang
//xxz155330
//9.13.2016

package XiaomengTetris;

import java.awt.Color;

public class CubeSix extends BaseCube{
	public CubeSix(){
		super.color = Color.cyan;
		int myPosition[][][] = {
				{
					
					{0,1,0,0},
					{1,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{  
					{0,1,0,0},
					{0,1,1,0},
					{0,1,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,0,0,0},
					{1,1,1,0},
					{0,1,0,0},
					{0,0,0,0},
					
				},
				
				{
					{0,1,0,0},
					{1,1,0,0},
					{0,1,0,0},
					{0,0,0,0},
					
				},
		};
		super.positions = myPosition;
	}

}
