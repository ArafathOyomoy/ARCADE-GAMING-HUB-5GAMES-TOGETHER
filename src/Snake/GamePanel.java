package Snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener
{
	static final int SCREEN_WIDTH =700;
	static final int SCREEN_HEIGHT =700;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY=150;
	final int x[] = new int [GAME_UNITS];
	final int y[] = new int [GAME_UNITS];
	int bodyParts =6;
	int applesEaten;
	int appleX;
	int appleY;
	int direction ='R';
	boolean running = false;
	boolean collisionOccurred = false;
	
	
	Timer timer;
	Random random;
	private static final int WALL_WIDTH = 700;
	private static final int WALL_HEIGHT = 700;
	private static final int WALL_Y_OFFSET = 100;
	 private static final int MAX_SCORES_TO_DISPLAY = 10; // Maximum number of scores to display on the screen
	 private int highScore;

	GamePanel()
	{
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new GameKeyAdapter());
		startGame();
		highScore = ScoreFile.readScore();
		
		
	}
	
	
	public void startGame()
	{		
		    newApple();
		    running = false; // Set initial state to paused
		    timer = new Timer(DELAY, this); // Initialize the timer
		    timer.stop(); // Stop the timer initially
		    repaint();
	        x[0] = WALL_WIDTH / 2; // Start at the horizontal center of the wall
	        y[0] = WALL_Y_OFFSET + WALL_HEIGHT / 2; // Start at the vertical center of the wall	
	        
	        

	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		 draw(g);
		 drawWalls(g);
		 
		 if (!running) 
		 {
			    if (applesEaten == 0 && !collisionOccurred) 
			    {
					//Highest Score
					
					g.setColor(Color.red);
					g.setFont(new Font("Arial",Font.BOLD,40));
					FontMetrics metrics2 = getFontMetrics(g.getFont());
					//g.drawString("Score: "+applesEaten,(SCREEN_WIDTH- metrics1.stringWidth("Score: "+applesEaten))/2 ,g.getFont().getSize());
					g.drawString("Highest Score: "+highScore,(600-metrics2.stringWidth("Higest Score: "+highScore)) ,g.getFont().getSize());

			    	
			    	
			        // Display "Press Spacebar to play" message
			        g.setColor(Color.RED);
			        g.setFont(new Font("Arial", Font.BOLD, 40));
			        FontMetrics metrics = getFontMetrics(g.getFont());
			        g.drawString("Press Spacebar to play", (SCREEN_WIDTH - metrics.stringWidth("Press Spacebar to play")) / 2, SCREEN_HEIGHT / 2);
			    } 
			    else if (collisionOccurred) 
			    {
			        // Display "Game Over" message
			        gameOver(g);

			        
			        JLabel titleLabel1 = new JLabel("GAME OVER");
			        titleLabel1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 50));
			        titleLabel1.setForeground(Color.RED);
			        titleLabel1.setBounds(200, 250, 500, 50); // Set bounds for the label
			        add(titleLabel1);

			        JButton button2 = new JButton("Game Menu");
			        button2.setFont(new Font("Arial", Font.BOLD, 25)); // Set the font size to 25
			        button2.setBounds(250, 500, 200, 60); // Set bounds for button 3
			        
			        button2.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			                Frame frame = (Frame) SwingUtilities.getWindowAncestor(GamePanel.this);

			                frame.remove(GamePanel.this); // Remove PanelUI1 from the frame
			                UIPanel1 panel1 = new UIPanel1();
			                frame.add(panel1);
			                frame.pack();
			                frame.setVisible(true);
			                panel1.requestFocus(); // Set focus on Panel to receive key events

			            
			            
			            
			            
			            
			            }
			        });
			        
			        add(button2);
			        
			        
			    }
			} 
		 			        

		
	}

	
	
	
	
	public void draw(Graphics g)
	{
		if(running== true)
		{
			
			/*	
			 //For the row and column-wise lines
			
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++)
			{
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE,SCREEN_HEIGHT);
				g.drawLine(0,i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}	
			   
			*/
			
		g.setColor(Color.red);
		g.fillOval(appleX, appleY,UNIT_SIZE,UNIT_SIZE);

		for(int i=0;i<bodyParts;i++)
		{
			if(i==0)
			{
				g.setColor(new Color(45,180,0));
				g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);		
			}
			else
			{
				g.setColor(new Color(45,180,0));
				g.setColor(Color.GREEN);				
				g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
			}
		}
		
		//Score
		g.setColor(Color.red);
		g.setFont(new Font("Arial",Font.BOLD,40));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten,(SCREEN_WIDTH- metrics.stringWidth("Score: "+applesEaten))/2 ,g.getFont().getSize());
		

		}	
		else
		{
			gameOver(g);	
		}
		
	}

	
	private void drawWalls(Graphics g) 
	{
	    g.setColor(Color.GRAY);

	    // Draw top wall
	    g.fillRect(0, WALL_Y_OFFSET, WALL_WIDTH, UNIT_SIZE);

	    // Draw bottom wall
	    g.fillRect(0, WALL_Y_OFFSET + WALL_HEIGHT - UNIT_SIZE, WALL_WIDTH, UNIT_SIZE);

	    // Draw left wall
	    g.fillRect(0, WALL_Y_OFFSET, UNIT_SIZE, WALL_HEIGHT);

	    // Draw right wall
	    g.fillRect(WALL_WIDTH - UNIT_SIZE, WALL_Y_OFFSET, UNIT_SIZE, WALL_HEIGHT);
	}

	public void newApple()
	{		
        int minX = UNIT_SIZE; // Left wall offset
        int maxX = WALL_WIDTH - UNIT_SIZE; // Right wall offset
        int minY = WALL_Y_OFFSET + UNIT_SIZE; // Top wall offset
        int maxY = WALL_Y_OFFSET + WALL_HEIGHT - UNIT_SIZE; // Bottom wall offset

        boolean onSnake = true;
        while (onSnake) 
        {  	
            int appleX = random.nextInt((maxX - minX + 1) / UNIT_SIZE) * UNIT_SIZE + minX;
            int appleY = random.nextInt((maxY - minY + 1) / UNIT_SIZE) * UNIT_SIZE + minY;

            onSnake = false;
            for (int i = 0; i < bodyParts; i++) 
            {
                if (x[i] == appleX && y[i] == appleY) 
                {
                    onSnake = true;
                    break;
                }
            }

            if (!onSnake) 
            {
                this.appleX = appleX;
                this.appleY = appleY;
            }
        }
	}
	
	public void move()
	{
		for(int i = bodyParts;i>0;i--)
		{
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		
		switch(direction) 
		{
		case 'U':y[0]=y[0]-UNIT_SIZE;
	    break;
		
		case 'D':y[0]=y[0]+UNIT_SIZE;
		break;
		
		case 'L':x[0]=x[0]-UNIT_SIZE;
		break;
		
		case 'R':x[0]=x[0]+UNIT_SIZE;
		break;
		
		}
		checkAppleCollision();
		checkCollisions();		
	}
	
    public void checkAppleCollision() 
    {
        for (int i = 0; i < bodyParts; i++) 
        {
            if (x[i] == appleX && y[i] == appleY) 
            {
                bodyParts++;
                applesEaten++;
            	newApple();              
            }
        }
        
        
    }

	public void checkApple()
	{
		if((x[0]==appleX)&& (y[0]==appleY))
		{
			bodyParts++;
			applesEaten++;
			newApple();
		}	
	}
	
	public void checkCollisions()
	{		
	    // if head collides with body
	    for (int i = bodyParts; i > 0; i--) 
	    {
	        if ((x[0] == x[i]) && (y[0] == y[i])) 
	        {
	            running = false;
	            
	        }

	    }
	    
	    

	    
	    // if head touches left border
	    if (x[0] < UNIT_SIZE) 
	    {
	        running = false;
	    }
	    
	    // if head touches right border
	    if (x[0] >= SCREEN_WIDTH - UNIT_SIZE) 
	    {
	        running = false;
	    }
	    
	    // if head touches top border
	    if (y[0] < WALL_Y_OFFSET + UNIT_SIZE) 
	    {
	        running = false;
	    }
	    
	    // if head touches bottom border
	    if (y[0] >= WALL_Y_OFFSET + WALL_HEIGHT - UNIT_SIZE) 
	    {
	        running = false;
	    }
	    	    
	    if (!running) 
	    {
	        if (applesEaten > highScore) {
	            highScore = applesEaten;
	            ScoreFile.writeScore(highScore);
	        }
	        collisionOccurred = true;
	        timer.stop();
	    }

	}
	
	public void gameOver(Graphics g)
	{	
		//Score
		
		g.setColor(Color.red);
		g.setFont(new Font("Arial",Font.BOLD,40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
  	    //g.drawString("Score: "+applesEaten,(SCREEN_WIDTH- metrics1.stringWidth("Score: "+applesEaten))/2 ,g.getFont().getSize());
		g.drawString("Score: "+applesEaten,(200-metrics1.stringWidth("Score: "+applesEaten)) ,g.getFont().getSize());

		
		//Highest Score
		
		g.setColor(Color.red);
		g.setFont(new Font("Arial",Font.BOLD,40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		//g.drawString("Score: "+applesEaten,(SCREEN_WIDTH- metrics1.stringWidth("Score: "+applesEaten))/2 ,g.getFont().getSize());
		g.drawString("Highest Score: "+highScore,(600-metrics2.stringWidth("Higest Score: "+highScore)) ,g.getFont().getSize());
	    
		

		
		ScoreFile.writeScore(applesEaten);

	
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(running)
		{
			move();
			checkApple();
			checkCollisions();
		}
		repaint();

	
		
		
	}
	

	public class GameKeyAdapter extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e) 
		{
			switch(e.getKeyCode())
			{
			
			case KeyEvent.VK_LEFT:
				if(direction != 'R')
				{
					direction ='L';
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				if(direction != 'L')
				{
					direction ='R';
				}
				break;
				
			case KeyEvent.VK_UP:
				if(direction != 'D')
				{
					direction ='U';
				}
				break;
				
			case KeyEvent.VK_DOWN:
				if(direction != 'U')
				{
					direction ='D';
				}
				break;
				
			}
			
	        if (e.getKeyCode() == KeyEvent.VK_SPACE && collisionOccurred == false) 
	        {
	            if (running) 
	            {
	                // Pause the game
	                timer.stop();
	                running = false;
	            } 
	            else 
	            {
	                // Resume the game
	                timer.start();
	                running = true;
	            }
	        }	
		
		}

	}

}
