package Pong;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	static final int game_width=1000;
	static final int game_height=(int)(game_width*(0.5555));
	static final Dimension screen=new Dimension(game_width,game_height);
	 static final int ball_diameter=20;
	 static final int paddle_w=25;
	 
	 static final int paddle_h=100;
	 Thread gameThread;
	 Image image;
	 Graphics graphics;
	 Random random;
	 Paddle paddle1;
	 Ball ball;
	 Paddle paddle2;
	 Score score;
	
	GamePanel(){
		paddles();
		newBall();
		score=new Score(game_width,game_height);
		
		this.addKeyListener(new ActionListener(paddle1,paddle2));
		this.setFocusable(true);
		this.setPreferredSize(screen);
		
		gameThread=new Thread(this);
		gameThread.start();
	}
public void newBall() {
	
	random=new Random();
	ball=new Ball((game_width/2)-(ball_diameter/2),random.nextInt(game_height-ball_diameter ),ball_diameter,ball_diameter);

	
}
public void paddles() {
	paddle1=new Paddle(0,(game_height/2)-(paddle_h/2),paddle_w,paddle_h,1);
	
	paddle2=new Paddle((game_width-paddle_w),(game_height/2)-(paddle_h/2),paddle_w,paddle_h,2);
	
}
public void paint(Graphics g) {
	image=createImage(getWidth(),getHeight());
	graphics=image.getGraphics();
	draw(graphics);
	g.drawImage(image,0,0,this);
	
	
	
}
public void draw(Graphics g) {
	paddle1.draw(g);
	paddle2.draw(g);
	ball.draw(g);
	score.draw(g);

	
}

public void move() {
	paddle1.move();
	paddle2.move();
	ball.move();

}
public void collision() {
	//fall ball y-axis
	
	if(ball.y<=0) {
		ball.sety(Math.abs(ball.yvelocity));
	}
	if(ball.y>=game_height-ball_diameter) {
		ball.sety(-Math.abs(ball.yvelocity));
	}
	

	//ball to paddle bounce
	if(ball.intersects(paddle1)) {
		ball.xvelocity=Math.abs(ball.xvelocity);
		ball.setx(ball.xvelocity);
		ball.sety(ball.yvelocity);
	}
	
	if(ball.intersects(paddle2)) {
		ball.xvelocity=-Math.abs(ball.xvelocity);
		ball.setx(ball.xvelocity);
		ball.sety(ball.yvelocity);
	}
	
	
	//paddle
	if(paddle1.y<=0) {
		paddle1.y=0;
	}
	if(paddle1.y>=(game_height-paddle_h)){
	paddle1.y=game_height-paddle_h;	
	}
	
	
	
	
	if(paddle2.y<=0) {
		paddle2.y=0;
	}
	if(paddle2.y>=(game_height-paddle_h)){
		paddle2.y=game_height-paddle_h;	
		
	}
	//score
	if(ball.x<=0) {
		score.player2++;
		paddles();
		newBall();
		this.addKeyListener(new ActionListener(paddle1,paddle2));
		System.out.println("Player 2-"+score.player2);
	}
	
	if(ball.x>=(game_width-ball_diameter)) {
		score.player1++;
		paddles();
		newBall();
		this.addKeyListener(new ActionListener(paddle1,paddle2));
		System.out.println("Player 1-"+score.player1);
	}
	
	
}
public void run() {
	long lastTime=System.nanoTime();
	double amountoftick=60.0;
	double n=1000000000/amountoftick;
	double delta=0;
	boolean gameRunning = true;
	while (gameRunning) {
		long now=System.nanoTime();
		delta+=(now-lastTime)/n;
		lastTime=now;
		if(delta>=1) {
			move();
			collision();
			repaint();
			delta--;
			
			if (score.player1 >= 10 || score.player2 >= 10) {
	        gameRunning = false;
			
			
			
	}

	
}





}
}
}

