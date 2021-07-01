package games;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// create a window of java

public class Display {
	
	public int map[][] ;   // a 2d array for 21 bricks
	public int brickwidth;
	public int brickheight;
	
	public Display(int row,int col)
	{
		map = new int[row][col];        // to arrange 21 bricks
		for(int i=0;i < map.length;i++ )  // making loop for row
		{
			for(int j=0;j < map.length;j++ ) // making loop for coloumn
			{
				map[i][j] = 1; // it must be shown on panel if 0 then it will not be shown 
			}
				
		}
		brickwidth = 540/col;    // to decide the height and width of according to total height and no. of bricks  
		brickheight = 150/row;
		
		
	}
	
	public void draw(Graphics2D g)
	{
		for(int i=0;i < map.length;i++ )  // making loop for row
		{
			for(int j=0;j < map[0].length;j++ ) // making loop for coloumn
			{
				if(map[i][j] > 0) // as all value will be one
				{
					g.setColor(Color.YELLOW);
					g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight); // +50 to leave the space
					g.setColor(Color.black);
					g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight); // for differentiation of bricks
					
				
				}
			}
				
		}
		
	}
	public void setBrickvalue(int value,int row,int col)
	{
		map[row][col] = value;
	}

}
