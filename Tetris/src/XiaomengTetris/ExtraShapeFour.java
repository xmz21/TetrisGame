package XiaomengTetris;

import java.awt.Color;

public class ExtraShapeFour extends BaseCube{
	public ExtraShapeFour(){
		super.color = Color.black;
		int myPosition[][][] = {
				{
					
					{0,0,1,0},
					{0,1,0,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,1,0,0},
					{0,0,1,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,0,1,0},
					{0,1,0,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,1,0,0},
					{0,0,1,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
		};
		super.positions = myPosition;
	}

}
