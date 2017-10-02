//xiaomeng zhang
//xxz155330
//9.13.2016

package XiaomengTetris;

import java.awt.Color;

public class CubeTwo extends BaseCube{
	public CubeTwo(){
		super.color = Color.pink;
		int myPosition[][][] = {
				{
					
					{0,1,1,0},
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{
					{0,1,1,0},
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				
				{
					{0,1,1,0},
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
				},
				
				{
					{0,1,1,0},
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
				},
		};
		super.positions = myPosition;
	}

}
