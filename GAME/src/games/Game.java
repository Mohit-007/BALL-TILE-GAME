package games;
import java.lang.Thread;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements KeyListener, ActionListener   // key => bar ; action ball
{
	private boolean play = false ;  // start game will not play itself
	private int score = 0  ;         // start score = 0
	private int totalbricks = 50 ;   // total number of bricks
	
	private Timer time;    // ---
	private int delay = 8 ; // ---
	
	private int playerX = 310; // start position of bar in x ; y=0 ;
			
	private int ballX = 120 ;  // start position of ball in x 
	private int ballY = 350 ;  // start position of ball in y

	private int ballXdirec = -2 ; //---
	private int ballYdirec = -2 ; //---
	
	private Display map;
	
	public Game()
	{
		map = new Display(5,10);
		addKeyListener(this);  // adding key listner without adding button so that it can be directly called
		setFocusable(true);    // ---
		setFocusTraversalKeysEnabled(false); //---
		time = new Timer(delay,this); // ---
		time.start();  // it will start the time
	
	}
	
	public void paint(Graphics g)
	{
		// background color
		g.setColor(Color.BLACK);    // it will set the background color
		g.fillRect(1, 1, 692, 592); // it will make a rectangle
		
		//map
		map.draw((Graphics2D)g);
		
		// border
		g.setColor(Color.yellow);  // color will be yellow
		g.fillRect(0, 0, 3, 592);  // horizontal
		g.fillRect(0, 0, 692, 3);  // vertical
		g.fillRect(691, 0, 3, 592);
		
		//scores
		g.setColor(Color.white);
		g.drawString(""+score, 590, 30);
		
		// the paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 10);  // for movement of player in x,bottom position,width of bar, height of bar
		
		//the ball
		g.setColor(Color.WHITE);
		g.fillOval(ballX, ballY, 20, 20);  // x position and y postion variation of ball , size of ball
		
		
		// similar can be done for game win
		// for game over
				
		// brick making new class
		
		
		g.dispose(); // ---
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		time.start();  // it will start the timer as well as game
		// to repaint the ball
		if(play)  // if game started
		{
			// to check intersection between ball and bar
			if(new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(playerX,550,100,10))) // if intersect with it then
			{
				ballYdirec = -ballYdirec ;
			}
			
			//if ball touches the bricks
		A:	for(int i=0;i<map.map.length;i++)
			{
				for(int j=0;j<map.map[0].length;j++)
				{
					if(map.map[i][j] > 0) // then intersect
					{
						// take all value of brick to make a rectangle around it
						int brickX = j*map.brickwidth + 80;
						int brickY = i*map.brickheight + 50;
						int brickwidth = map.brickwidth;
						int brickheight = map.brickheight;
						
						// making brick rect and ball rect
						Rectangle rect = new Rectangle(brickX,brickY,brickwidth,brickheight);
						Rectangle ballrect = new Rectangle(ballX,ballY,20,20);
						
						if(ballrect.intersects(rect))// check intersect
						{
							map.setBrickvalue(0, i, j); // give 0 value to i,j position brick
							totalbricks--;
							score+=5;
							
							// it will create a problem for ball corner sides
							if(ballX + 19 <= rect.x || ballX + 1 >= rect.x + rect.width) // corner of brick .x
							{
								ballXdirec = -ballXdirec;
							}
							else
							{
								ballYdirec = -ballYdirec;
							}
							break A;  // it will take out of the loop
						}
						
						
					}
				}
			}
			
			// it will always move upward
			
			ballX += ballXdirec ;   // it will increase the ball direc in all direc 
			ballY += ballYdirec ;	// here ball direc came into picure
			if(ballX < 0)            // for left border
			{
				ballXdirec = -ballXdirec;
			}
			if(ballY < 0)				// for upper border
			{
				ballYdirec = -ballYdirec;
			}
			if(ballX > 670)             // for right border
			{
				ballXdirec = -ballXdirec;
			}
			
			
			
		}
		
		
		repaint(); // it will recall paint method and draw everything again and we will see it moving
		// actually it is drawing it again and again
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		// if right arrow key and left arrow key pressed
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)  // it will check what key is pressed
		{
			if(playerX >= 600 )  // if greater than window or frame then keep it to the corner
				playerX = 600;   
			else
				moveRight();
				
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)  // it will check what key is pressed
		{
			if(playerX <= 10 )  // if greater than window or frame then keep it to the corner
				playerX = 10;   
			else
				moveLeft();
		}
		
		// to restart game
		
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if(!play)
			{
				play = true;
				ballX = 120;
				ballY = 350;
				ballXdirec = -1;
				ballYdirec = -2;
				playerX = 310;
				score = 0;
				totalbricks = 21;
				map = new Display(3,7);
				repaint();
				
			}
		}
		
		
	}
	
	public void moveRight()
	{
		play = true;  // to start game
		playerX+=20 ; // to move it it is only incremented  and to redraw it calling repaint method
	}
	public void moveLeft()
	{
		play = true;  // to start game
		playerX-=20 ; // to move it
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
