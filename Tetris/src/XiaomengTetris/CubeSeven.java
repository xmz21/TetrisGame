//xiaomeng zhang
//xxz155330
//9.13.2016

package XiaomengTetris;

import java.awt.Color;

public class CubeSeven extends BaseCube{
	public CubeSeven(){
		super.color = Color.yellow;
		int myPosition[][][] = {
				{
					
					{1,1,0,0},
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,0,1,0},
					{0,1,1,0},
					{0,1,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,0,0,0},
					{1,1,0,0},
					{0,1,1,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,1,0,0},
					{1,1,0,0},
					{1,0,0,0},
					{0,0,0,0},
					
				},
		};
		super.positions = myPosition;
	}

}
