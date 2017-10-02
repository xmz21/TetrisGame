package XiaomengTetris;

import java.awt.Color;

public class ExtraShapeTwo extends BaseCube{
	public ExtraShapeTwo(){
		super.color = Color.DARK_GRAY;
		int myPosition[][][] = {
				{
					
					{1,0,0,0},
					{0,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,0,1,0},
					{0,1,0,0},
					{0,1,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,0,0,0},
					{1,1,0,0},
					{0,0,1,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,1,0,0},
					{0,1,0,0},
					{1,0,0,0},
					{0,0,0,0},
					
				},
		};
		super.positions = myPosition;
	}

}
