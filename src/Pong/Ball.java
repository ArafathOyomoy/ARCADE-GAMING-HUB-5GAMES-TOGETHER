package Pong;
import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {
	Random random;
	int xvelocity;
	int yvelocity;
	int s=5;
	public Ball(int x,int y,int w,int h) {
		super(x,y,w,h);
		random=new Random();

		 int xdirection = random.nextInt(2) * 2 - 1;
		 setx(xdirection*s);

		 int ydirection = random.nextInt(2) * 2 - 1;
		 sety(ydirection*s);

		}
		
		
		
		
		
	
	public void setx(int xdirection) {
	
		xvelocity=xdirection;
		
		
	}
	public void sety(int ydirection) {
		yvelocity=ydirection;
		
		
	}
	public void move() {
		x=x+xvelocity;
		y=y+yvelocity;
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(x, y, width, height);
		
	}

}
