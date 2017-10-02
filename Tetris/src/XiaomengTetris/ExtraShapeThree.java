package XiaomengTetris;

import java.awt.Color;

public class ExtraShapeThree extends BaseCube{
	public ExtraShapeThree(){
		super.color = Color.GRAY;
		int myPosition[][][] = {
				{
					
					{0,0,0,0},
					{1,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,1,0,0},
					{0,1,0,0},
					{0,1,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,0,0,0},
					{1,1,1,0},
					{0,0,0,0},
					{0,0,0,0},
					
				},
				
				{
					
					{0,1,0,0},
					{0,1,0,0},
					{0,1,0,0},
					{0,0,0,0},
					
				},
		};
		super.positions = myPosition;
	}

}

